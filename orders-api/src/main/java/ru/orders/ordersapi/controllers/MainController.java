package ru.orders.ordersapi.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.orders.dbtools.domains.Product;
import ru.orders.dbtools.json.views.UserViews;
import ru.orders.ordersapi.services.ProductService;

import java.util.List;

@Controller
public class MainController {


   private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @JsonView(UserViews.UpdateProducts.class)
    @GetMapping("/updateProducts")
    public ResponseEntity<List<Product>> updateProductsList() {
        return new ResponseEntity<>(productService.updateProducts(), HttpStatus.OK);
    }

    @JsonView(UserViews.FullProductInfo.class)
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }





}
