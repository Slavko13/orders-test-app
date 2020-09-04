package ru.orders.dbtools.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.orders.dbtools.domains.Product;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {

    boolean existsBySerialNumber(String serialNumber);
    List<Product> findAll();
    Product findBySerialNumber(String serialNumber);
}
