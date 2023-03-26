package com.cafeteria.bedu.controller

import com.cafeteria.bedu.model.entities.Discount
import com.cafeteria.bedu.model.entities.Order
import com.cafeteria.bedu.model.entities.Product
import com.cafeteria.bedu.model.interactor.ProductInteractor

class ProductControllerImpl(var interactor: ProductInteractor): ProductController {
    override fun showOrder(): Order {
        val products = interactor.showProductList()
        val subtotal = interactor.orderSubTotal

        val order = Order(products, subtotal, null)
        return order
    }

    override fun addProductToOrder(product: Product?) {
        val currentCount = interactor.addProductToOrder(product)

        if (currentCount > 0) {
            println("Producto agregado a tu orden")
        } else {
            println("No se pudo agregar tu producto.")
        }
    }

    override fun removeProductFromOrder(product: Product?) {
        interactor.removeProductFromOrder(product)
    }

    override fun payOrder(discount: Discount?, cash: Float?): Float {
        val total = interactor.getTotal(discount, cash)

        return cash!! - total
    }
}