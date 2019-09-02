package com.brave.controller;

import com.brave.model.Applicant;
import com.brave.model.Product;
import com.brave.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {
    @Autowired ShopService shopService;

    @RequestMapping(value = "/product/{type}",method = RequestMethod.GET)
    public Product product(@PathVariable String type) {
        Product product = new Product();
        product.setType(type);
        shopService.getProductDiscount(product);
        return product;
    }

    @GetMapping("/applicant/{age}")
    public Applicant applicant(@PathVariable int age) {
        Applicant applicant = new Applicant();
        applicant.setAge(age);
        shopService.getApplicant(applicant);
        return applicant;
    }

}
