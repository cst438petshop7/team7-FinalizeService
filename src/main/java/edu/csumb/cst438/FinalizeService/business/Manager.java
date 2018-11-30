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

    //TODO: use Business entities rather than api/presentation layer enteties.
    public List<Product> getHeroList () {
        //do some business validation
        //call the data layer
        //data validation
        //return data from data layer
        return null;
    }
}