package edu.csumb.cst438.finalizeservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.csumb.cst438.finalizeservice.api.cart.Item;
import edu.csumb.cst438.finalizeservice.api.products.Product;
import edu.csumb.cst438.finalizeservice.api.users.User;
import edu.csumb.cst438.finalizeservice.business.Manager;

@CrossOrigin("*")
@RestController
public class FinalizeController {
    @Autowired
    Manager manager;

    @RequestMapping(value="/finalize/{username}", method=RequestMethod.POST)
    @ResponseBody
    ResponseEntity<String> finalizeData(@PathVariable String username, @RequestBody List<Item> items) {
        if (items == null) { return new ResponseEntity<String>("Shopping cart is empty!", HttpStatus.NOT_FOUND); }
        User user = callUserDB(username);
        if (user == null) {  return new ResponseEntity<String>("No user found.", HttpStatus.NOT_FOUND); }
        double cost = 0;
        for (Item item : items) {
            Product product = callProductDB(item.getId());
            if (product == null) {  return new ResponseEntity<String>("No product found.", HttpStatus.NOT_FOUND); }
            cost += (product.getPrice().getPrice() * item.getAmount());
            if (product.getStock().getStock() < item.getAmount()) { 
                return new ResponseEntity<String>("Not enough stock for: " + product.getProductName().getProductName(), HttpStatus.FORBIDDEN); 
            }
        }
        if (user.getCredit().getCredit() < cost) { 
            return new ResponseEntity<String>("Insufficient credit!", HttpStatus.PAYMENT_REQUIRED);
        }
        User newUserData = reduceCredit(username, cost);
        if (newUserData == null) { return new ResponseEntity<String>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR); }
        for (Item item : items) {
            ResponseEntity<String> productResponse = reduceStock(item.getId(), item.getAmount());
            HttpStatus productStatusCode = productResponse.getStatusCode();
            if (productStatusCode != HttpStatus.OK) { return new ResponseEntity<String>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR); }
        }
        return new ResponseEntity<String>("Transaction successful!", HttpStatus.OK);
    }
    // Returns a User object given a username
    private User callUserDB (String username) {
        String uri = "https://shopdb-service.herokuapp.com/username/"+username;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> user = restTemplate.exchange(uri,
        HttpMethod.GET,null, 
        new ParameterizedTypeReference<User>(){});
        return user.getBody();
    }
    // Returns a Product object given a product id
    private Product callProductDB (String id) {
        String uri = "https://productsdb-service.herokuapp.com/id/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product> product = restTemplate.exchange(uri,
        HttpMethod.GET,null, 
        new ParameterizedTypeReference<Product>(){});
        return product.getBody();
    }
    // Decriments a product's stock given a product id and amount to decriment
    private ResponseEntity<String> reduceStock(String id, int amount) {
        String uri = "https://shopdb-service.herokuapp.com/update/"+id+"/"+amount;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> reply = restTemplate.exchange(uri,
        HttpMethod.GET,null, 
        new ParameterizedTypeReference<String>(){});
        return reply;
    }
    // Decriments a User's credit given a username and amount to decriment
    private User reduceCredit(String username, double amount) {
        String uri = "https://shopdb-service.herokuapp.com/update/"+username+"/"+amount;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> reply = restTemplate.exchange(uri,
        HttpMethod.GET,null, 
        new ParameterizedTypeReference<User>(){});
        return reply.getBody();
    }

}