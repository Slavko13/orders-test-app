package ru.orders.ordersapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.orders.dbtools.domains.Order;
import ru.orders.dbtools.domains.OrderDetails;
import ru.orders.dbtools.domains.Product;
import ru.orders.dbtools.dto.OrderDTO;
import ru.orders.dbtools.dto.OrderDetailsListDTO;
import ru.orders.dbtools.repositories.OrderDetailsRepo;
import ru.orders.dbtools.repositories.OrderRepo;
import ru.orders.dbtools.repositories.ProductRepo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final OrderDetailsRepo orderDetailsRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, ProductRepo productRepo, OrderDetailsRepo orderDetailsRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.orderDetailsRepo = orderDetailsRepo;
    }


    @Override
    public Order makeAnOrder(OrderDTO orderDTO) {

        Double totalCost = 0.0;
        Date dateNow = new Date();

        Order order = Order.builder()
                .clientAddress(orderDTO.getClientAddress())
                .clientName(orderDTO.getClientName())
                .creationDate(dateNow)
                .build();
        order =  orderRepo.save(order);

        for (OrderDetailsListDTO orderDetailsListDTO: orderDTO.getOrderDetailsList()) {
            Product product = productRepo.findBySerialNumber(orderDetailsListDTO.getSerialNumber());
            totalCost += product.getPricePerOne() * orderDetailsListDTO.getCount();
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProduct(product);
            orderDetails.setOrder(order);
            orderDetails.setProductQuantity(orderDetailsListDTO.getCount());
            orderDetailsRepo.save(orderDetails);
        }

        order.setOrderCost(totalCost);
        orderRepo.save(order);
        return order;
    }
}
