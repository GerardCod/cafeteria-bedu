package com.cafeteria.bedu.model.entities

/**
 * Clase que representa una la orden del usuario.
 */
data class Order(
    var products: List<OrderProduct>,
    var total: Float,
    var discount: Discount?
)
