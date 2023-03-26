package com.cafeteria.bedu.model.interactor;

import com.cafeteria.bedu.model.entities.Discount;
import com.cafeteria.bedu.model.entities.Order;
import com.cafeteria.bedu.model.entities.Product;
import org.jetbrains.annotations.Nullable;

/**
 * Interfaz que define las operaciones que puede realizar el modelo.
 * @version 1.0.0 25/03/2023
 */
public interface ProductInteractor {
    int addProductToOrder(Product product);
    void removeProductFromOrder(Product product);
    void payOrder(@Nullable Discount discount, Float cash);
    Order showOrder();
}
