package ru.orders.dbtools.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.orders.dbtools.domains.OrderDetails;

public interface OrderDetailsRepo extends CrudRepository<OrderDetails, Integer> {

}
