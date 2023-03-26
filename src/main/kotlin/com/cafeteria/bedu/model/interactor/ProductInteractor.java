package com.cafeteria.bedu.model.interactor;

import com.cafeteria.bedu.model.entities.Discount;
import com.cafeteria.bedu.model.entities.Order;
import com.cafeteria.bedu.model.entities.OrderProduct;
import com.cafeteria.bedu.model.entities.Product;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interfaz que define las operaciones que puede realizar el modelo.
 * @version 1.0.0 25/03/2023
 */
public interface ProductInteractor {
    Integer addProductToOrder(Product product);
    void removeProductFromOrder(Product product);
    Float getTotal(@Nullable Discount discount, Float cash);
    List<OrderProduct> showProductList();
}
