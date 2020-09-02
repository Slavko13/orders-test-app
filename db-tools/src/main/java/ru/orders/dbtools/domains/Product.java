package ru.orders.dbtools.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.apache.tomcat.jni.Local;
import ru.orders.dbtools.json.views.UserViews;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "product")
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonView(UserViews.UpdateProducts.class)
    @Column(unique = true)
    private String serialNumber;
    @JsonView(UserViews.UpdateProducts.class)
    private String productName;
    @JsonView(UserViews.UpdateProducts.class)
    private String description;
    @JsonView(UserViews.UpdateProducts.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date serialProductionDate;
    @JsonView(UserViews.FullProductInfo.class)
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetailsList = new ArrayList<>();


    public Product( String serialNumber, String productName, String description, Date serialProductionDate) {
        this.serialNumber = serialNumber;
        this.productName = productName;
        this.description = description;
        this.serialProductionDate = serialProductionDate;
    }

    public Integer getId() {
        return id;
    }

    @XmlElement
    public String getSerialNumber() {
        return serialNumber;
    }

    @XmlElement
    public String getProductName() {
        return productName;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public Date getSerialProductionDate() {
        return serialProductionDate;
    }

    @XmlElement
    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

}
