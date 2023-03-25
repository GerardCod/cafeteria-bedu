package com.cafeteria.bedu.model.entities

/**
 * Clase que representa un producto de la cafetería.
 */
open class Product(
    var name: String,
    var price: Float,
    var flavor: String,
    var size: Size
)
