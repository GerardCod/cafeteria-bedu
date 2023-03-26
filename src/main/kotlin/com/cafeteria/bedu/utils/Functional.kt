package com.cafeteria.bedu.utils

import com.cafeteria.bedu.model.entities.OrderProduct

/**
 * Función de orden superior para componer funciones que apliquen descuentos a la operación de
 * obtener el precio total de la orden.
 */
val composeGetTotalWithDiscount = fun(
                getTotalOp: (List<OrderProduct>) -> Float,
                discountOp: (Float) -> Float
): (List<OrderProduct>) -> Float {
    return {
        products -> discountOp(getTotalOp(products))
    }
}

/**
 * Función de orden superior que compone una función con un IVA aplicado
 */
val composeGetTotal = fun(
    getSubtotalOp: (List<OrderProduct>) -> Float,
    iva: Float
): (List<OrderProduct>) -> Float {
    return fun(products: List<OrderProduct>): Float {
        val subTotal = getSubtotalOp(products)
        return subTotal + (subTotal * iva)
    }
}

/**
 * Obtiene el total del precio de una orden de acuerdo a una lista de productos y a una función que realiza
 * la operación.
 */
val getOrderTotal = fun (products: List<OrderProduct>, consumer: (List<OrderProduct>) -> Float): Float {
    return consumer(products)
}

/**
 * Obtiene el precio total de la orden sin IVA.
 */
val getSubTotal = { products: List<OrderProduct> ->
    products.map { product -> product.price * product.quantity }
        .reduce { prev, next -> prev + next }
}

/**
 * Obtiene el precio normal de la orden con el IVA del %16
 */
val normalPrice = composeGetTotal(getSubTotal, 0.16f)

/**
 * Obtiene el precio total de la orden con la promoción
 * de medio IVA aplicado.
 */
val halfIva = composeGetTotal(getSubTotal, 0.08f)

/**
 * Lambda auxiliar para aplicar el descuento del 20% al total
 * de la orden.
 */
val applyPromo20 = { total: Float -> total * 0.8f }

/**
 * Lambda auxiliar para aplicar el descuento del 15% al total
 * de la orden
 */
val applyPromo15 = { total: Float -> total * 0.85f }

/**
 * Función que aplica la promoción del 20% de descuento a la compra.
 */
val promo20 = composeGetTotalWithDiscount(normalPrice, applyPromo20)

/**
 * Función que aplica la promoción del 15% de descuento a la compra.
 */
val promo15 = composeGetTotalWithDiscount(normalPrice, applyPromo15)