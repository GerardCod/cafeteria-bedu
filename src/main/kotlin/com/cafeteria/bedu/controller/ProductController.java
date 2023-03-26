package com.cafeteria.bedu.controller;

import com.cafeteria.bedu.model.entities.Discount;
import com.cafeteria.bedu.model.entities.Order;
import com.cafeteria.bedu.model.entities.Product;

public interface ProductController {
    Order showOrder();
    void addProductToOrder(Product product);
    void removeProductFromOrder(Product product);
    Float payOrder(Discount discount, Float cash);
}
