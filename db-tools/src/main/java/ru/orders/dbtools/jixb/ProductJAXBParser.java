package ru.orders.dbtools.jixb;


import org.springframework.stereotype.Component;
import ru.orders.dbtools.domains.Product;
import ru.orders.dbtools.repositories.ProductRepo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductJAXBParser {

    private static List<Product> productsListToReturn = new ArrayList<>();
    private final ProductRepo productRepo;

    public ProductJAXBParser(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> marshal() {
        try {
            File file = new File("product.xml").getAbsoluteFile();
            System.out.println(file.getAbsolutePath());

            JAXBContext jaxbContext = JAXBContext.newInstance(ListProductsJABX.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ListProductsJABX listProductsJABX = (ListProductsJABX) unmarshaller.unmarshal(file);

            productsListToReturn.clear();
            for (Product tempProduct : listProductsJABX.getProductList()) {
                if (!productRepo.existsBySerialNumber(tempProduct.getSerialNumber())) {
                    Product product = new Product(tempProduct.getSerialNumber(), tempProduct.getProductName(), tempProduct.getDescription(), tempProduct.getSerialProductionDate());
                    productsListToReturn.add(product);
                    productRepo.save(product);
                }
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }


        return productsListToReturn;
    }


}
