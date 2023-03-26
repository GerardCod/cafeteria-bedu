package com.cafeteria.bedu.model.interactor

import com.cafeteria.bedu.model.entities.Discount
import com.cafeteria.bedu.model.entities.Order
import com.cafeteria.bedu.model.entities.OrderProduct
import com.cafeteria.bedu.model.entities.Product
import com.cafeteria.bedu.model.exceptions.ProductNotAddedException

class ProductInteractorImpl: ProductInteractor {
    var products: MutableList<OrderProduct>
    var count = 0

    init {
        products = mutableListOf()
    }

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

    override fun removeProductFromOrder(product: Product?) {
        val productAt = contains(product)

        if (productAt == -1) {
            throw ProductNotAddedException("El producto no está en su orden", product)
        }

        products.removeAt(productAt)
        count--
    }

    override fun getTotal(discount: Discount?, cash: Float?): Float {

        return 0.0f
    }

    override fun showProductList(): List<OrderProduct> {
        return products
    }
}