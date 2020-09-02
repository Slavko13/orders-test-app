package ru.orders.ordersapi.services;

import org.springframework.stereotype.Service;
import ru.orders.dbtools.domains.Product;
import ru.orders.dbtools.jixb.ProductJAXBParser;
import ru.orders.dbtools.repositories.ProductRepo;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductJAXBParser productJAXBParser;
    private final ProductRepo productRepo;


    public ProductServiceImpl(ProductJAXBParser productJAXBParser, ProductRepo productRepo) {
        this.productJAXBParser = productJAXBParser;
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> updateProducts() {
        return productJAXBParser.marshal();
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }
}
