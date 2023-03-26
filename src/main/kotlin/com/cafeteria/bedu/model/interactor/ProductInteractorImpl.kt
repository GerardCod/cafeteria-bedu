package com.cafeteria.bedu.model.interactor

import com.cafeteria.bedu.model.entities.Discount
import com.cafeteria.bedu.model.entities.Order
import com.cafeteria.bedu.model.entities.OrderProduct
import com.cafeteria.bedu.model.entities.Product
import com.cafeteria.bedu.model.exceptions.InsufficientCashException
import com.cafeteria.bedu.model.exceptions.ProductNotAddedException
import com.cafeteria.bedu.utils.*

/**
 * Implementa la interfaz ProductInteractor para definir el comportamiento de las operaciones del modelo.
 * @property products Lleva el registro de los productos agregados a la orden del cliente.
 * @property count Lleva el registro de la cantidad de productos que agrega el cliente a su orden.
 * @see ProductInteractor
 */
class ProductInteractorImpl: ProductInteractor {
    var products: MutableList<OrderProduct>
    var count = 0

    init {
        products = mutableListOf()
    }

    /**
     * Agrega un producto a la orden del cliente.
     * @param product Es el producto que se va a agregar a la orden.
     * @return Regresa la cantidad de productos que ya tiene el cliente en su orden.
     */
    override fun addProductToOrder(product: Product?): Int {
        val productAt = contains(product)
        if (productAt > -1) {
            products[productAt].quantity++
        } else {
            val orderProduct = product?.let {
                OrderProduct(
                    it.name,
                    it.price,
                    it.flavor,
                    it.size,
                    1
                )
            }
            products.add(orderProduct!!)
        }
        count++
        return count
    }

    /**
     * Método de ayuda para realizar la búsqueda de un producto en la lista de productos en
     * la orden del cliente.
     * @param product El producto a buscar en la lista de productos en la orden.
     * @return El indice donde se encuentra el producto en la lista de la orden.
     */
    private fun contains(product: Product?): Int {
        var index = 0

        while (index < products.size) {
            if (products[index].equals(product)) {
                return index
            }
            index++
        }

        return -1
    }

    /**
     * Retira un producto de la orden del cliente.
     * @param product El producto que será retirado de la orden del cliente.
     * @throws ProductNotAddedException en caso de que el producto que se quiere borrar no exista.
     * @see ProductNotAddedException
     */
    override fun removeProductFromOrder(product: Product?) {
        val productAt = contains(product)

        if (productAt == -1) {
            throw ProductNotAddedException("El producto no está en su orden", product)
        }

        products.removeAt(productAt)
        count--
    }

    /**
     * Obtiene el total de la orden y aplica un descuento en caso de que haya uno.
     * @param discount Descuento que aplica el cliente.
     * @param cash El dinero que recibimos del cliente.
     * @throws InsufficientCashException en caso de que el dinero del cliente sea insuficiente para pagar la orden.
     * @return El total de la compra.
     * @see InsufficientCashException
     */
    override fun getTotal(discount: Discount?, cash: Float?): Float {
        val cashNormal = cash!!

        val total = when(discount) {
            Discount.PROMO_20 -> getOrderTotal(products, promo20)
            Discount.PROMO_15 -> getOrderTotal(products, promo15)
            Discount.HALF_IVA -> getOrderTotal(products, halfIva)
            else -> getOrderTotal(products, normalPrice)
        }

        if (cashNormal < total) {
            throw InsufficientCashException("No tienes suficiente dinero para pagar tu orden")
        }

        return total
    }

    /**
     * Regresa la lista de productos agregados a la orden.
     * @return Regresa un objeto List con los productos agregados a la orden.
     * @see List
     */
    override fun showProductList(): List<OrderProduct> {
        return products
    }

    /**
     * Obtiene el subtotal del precio de la compra.
     * @return el subtotal de la compra del cliente.
     */
    override fun getOrderSubTotal(): Float {
        return getSubTotal(products)
    }
}