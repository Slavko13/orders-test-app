package ru.orders.dbtools.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.orders.dbtools.domains.Order;

public interface OrderRepo extends CrudRepository<Order, Integer> {


}
