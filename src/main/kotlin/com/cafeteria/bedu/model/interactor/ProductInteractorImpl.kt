package com.cafeteria.bedu.model.interactor

import com.cafeteria.bedu.model.entities.Discount
import com.cafeteria.bedu.model.entities.Order
import com.cafeteria.bedu.model.entities.OrderProduct
import com.cafeteria.bedu.model.entities.Product

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

    }

    override fun payOrder(discount: Discount?, cash: Float?) {
        TODO("Not yet implemented")
    }

    override fun showOrder(): Order {
        TODO("Not yet implemented")
    }
}