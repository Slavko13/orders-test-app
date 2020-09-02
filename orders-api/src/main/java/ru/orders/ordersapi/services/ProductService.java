package ru.orders.ordersapi.services;

import ru.orders.dbtools.domains.Product;

import java.util.List;

public interface ProductService {

    List<Product> updateProducts();
    Product addProduct(Product product);
    List<Product> getAllProducts();

}
