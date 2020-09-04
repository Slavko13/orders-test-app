package ru.orders.ordersapi.services;

import ru.orders.dbtools.domains.Order;
import ru.orders.dbtools.dto.OrderDTO;

public interface OrderService {

        Order makeAnOrder(OrderDTO orderDTO);


}
