package com.cafeteria.bedu.model.exceptions;

import com.cafeteria.bedu.model.entities.Product;

/**
 * Esta excepción representa el caso de cuando se intenta quitar un
 * producto que no existe en la orden del usuario.
 */
public class ProductNotAddedException extends RuntimeException {
    private Product product;

    /**
     *
     * @param message Mensaje de error de la excepción.
     * @param product Producto inexistente en la orden del usuario.
     */
    public ProductNotAddedException(String message, Product product) {
        super(message);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
