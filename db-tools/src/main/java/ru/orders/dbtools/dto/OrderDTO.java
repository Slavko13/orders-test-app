package ru.orders.dbtools.dto;


import lombok.Data;


import java.util.List;

@Data
public class OrderDTO {

    private List<OrderDetailsListDTO> orderDetailsList;
    private String clientAddress;
    private String clientName;
}
