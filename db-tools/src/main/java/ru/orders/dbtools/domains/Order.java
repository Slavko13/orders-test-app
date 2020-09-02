package ru.orders.dbtools.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_order")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clientName;
    private String clientAddress;
    private Long orderCost;
    private Date creationDate;


    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetailsList = new ArrayList<>();

}
