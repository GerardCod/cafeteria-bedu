package com.cafeteria.bedu.model.entities

/**
 * Clase que representa un producto de la cafetería.
 */
open class Product(
    var name: String,
    var price: Float,
    var flavor: String,
    var size: Size
) {
    override fun equals(other: Any?): Boolean {
        val otherProduct = other as Product
        return (this.name == otherProduct.name) &&
                (this.price == otherProduct.price) &&
                (this.flavor == otherProduct.flavor) &&
                (this.size == otherProduct.size)
    }
}
