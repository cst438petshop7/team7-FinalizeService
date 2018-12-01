package edu.csumb.cst438.finalizeservice.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.csumb.cst438.finalizeservice.api.products.Description;
import edu.csumb.cst438.finalizeservice.api.products.Image;
import edu.csumb.cst438.finalizeservice.api.products.Price;
import edu.csumb.cst438.finalizeservice.api.products.Product;
import edu.csumb.cst438.finalizeservice.api.products.ProductName;
import edu.csumb.cst438.finalizeservice.api.products.Stock;
import edu.csumb.cst438.finalizeservice.business.Manager;

/* TODO:
    When user presses “Confirm Purchase”:
    confirms the User has sufficient “credit” to make the purchase, 
    confirms that the purchase can be made given the # of items in stock, 
    and appropriately decrements the user’s “credit” and the “product stock” (in the backend) 
    before forwarding the user to a confirmation page if all was successful.
*/
@RestController
public class FinalizeController {
    @Autowired
    Manager manager;

    @GetMapping ("/Products")
    @ResponseBody
    List<Product> getProducts () {
        // Product Catnip = new Product("1", new ProductName("Catnip"), new Description("Catnip for cats"), new Image(""), new Price(10), new Stock(5));
        // Product Collar = new Product("2", new ProductName("Collar"), new Description("Collar for a cat"), new Image(""), new Price(10), new Stock(5));
        // Product Kong = new Product("3", new ProductName("Kong"), new Description("Toy for dogs"), new Image(""), new Price(10), new Stock(5));
        // List<Product> result = Arrays.asList(Catnip, Collar, Kong);
        List<Product> result = callDB();
        return result;
    }

    private List<Product> callDB () {
        String uri = "https://productsdb-service.herokuapp.com/allProducts";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Product>> result = restTemplate.exchange(uri,
        HttpMethod.GET,null, 
        new ParameterizedTypeReference<List<Product>>(){});
        return result.getBody();
    }
}