package com.cafeteria.bedu.model.entities

/**
 * Esta clase representa un artículo en la orden del usuario.
 */
class OrderProduct(
    name: String,
    price: Float,
    flavor: String,
    size: Size,
    var quantity: Int
): Product(name = name, price = price, flavor = flavor, size = size) {
}