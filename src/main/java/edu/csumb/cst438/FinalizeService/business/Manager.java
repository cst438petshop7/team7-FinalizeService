package edu.csumb.cst438.finalizeservice.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csumb.cst438.finalizeservice.api.products.Product;
import edu.csumb.cst438.finalizeservice.data.ProductDbClient;

@Service
public class Manager {
    @Autowired
    ProductDbClient productDbClient;

    public List<Product> getProductList () {
        return null;
    }
}