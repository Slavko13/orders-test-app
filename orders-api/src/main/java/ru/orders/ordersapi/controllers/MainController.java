package ru.orders.ordersapi.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.orders.dbtools.repositories.ProductRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
public class MainController {

    private final ProductRepo productRepo;

    public MainController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }



    @GetMapping("/")
    public String main(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd");
        data.put("products", productRepo.findAll());
        data.put("currentDate",formatForDateNow.format(dateNow));
        model.addAttribute("frontendData", data);

        return "index";
    }

}
