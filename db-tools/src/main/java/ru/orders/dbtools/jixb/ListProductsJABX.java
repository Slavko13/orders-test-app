package ru.orders.dbtools.jixb;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.orders.dbtools.domains.Product;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListProductsJABX {

    private List<Product> productList = new ArrayList<>();

    @XmlElement(name = "product")
    public List<Product> getProductList() {
        return productList;
    }
}
