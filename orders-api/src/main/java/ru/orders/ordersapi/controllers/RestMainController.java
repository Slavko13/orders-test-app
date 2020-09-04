package ru.orders.ordersapi.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.orders.dbtools.domains.Order;
import ru.orders.dbtools.domains.Product;
import ru.orders.dbtools.dto.OrderDTO;
import ru.orders.dbtools.json.views.UserViews;
import ru.orders.ordersapi.services.OrderService;
import ru.orders.ordersapi.services.ProductService;

import java.util.List;

@RestController
public class RestMainController {


   private final ProductService productService;
   private final OrderService orderService;

    public RestMainController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @JsonView(UserViews.UpdateProducts.class)
    @PostMapping("/updateProducts")
    public ResponseEntity<List<Product>> updateProductsList() {
        return new ResponseEntity<>(productService.updateProducts(), HttpStatus.OK);
    }

    @JsonView(UserViews.FullProductInfo.class)
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/makeAnOrder")
    public ResponseEntity<Order> makeAnOrder(@RequestBody OrderDTO orderDetails) {
        System.out.println(orderDetails);
        return new ResponseEntity<>(orderService.makeAnOrder(orderDetails) ,HttpStatus.OK);
    }

}
