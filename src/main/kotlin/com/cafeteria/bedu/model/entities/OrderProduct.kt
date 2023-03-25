package com.cafeteria.bedu.model.entities

/**
 * Esta clase representa un artículo en la orden del usuario.
 */
class OrderProduct(
    name: String,
    price: Float,
    flavor: String,
    size: Size,
    var quantity: Integer
): Product(name = name, price = price, flavor = flavor, size = size) {
}