package edu.csumb.cst438.finalizeservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.csumb.cst438.finalizeservice.api.products.Product;
import edu.csumb.cst438.finalizeservice.api.users.User;
import edu.csumb.cst438.finalizeservice.business.Manager;

/* TODO:
    appropriately decrements the user’s “credit” and the “product stock” (in the backend)
*/
@RestController
public class FinalizeController {
    @Autowired
    Manager manager;
    
    @GetMapping ("/Finalize/product/{name}/{amount}")
    @ResponseBody
    Boolean getProductData (@PathVariable String name, @PathVariable int amount) {
        Product result = callProductDB(name);
        if (result.getStock().getStock() >= amount) {
            return true;
        }
        return false;
    }

    private Product callProductDB (String name) {
        String uri = "https://productsdb-service.herokuapp.com/name/"+name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product> product = restTemplate.exchange(uri,
        HttpMethod.GET,null, 
        new ParameterizedTypeReference<Product>(){});
        return product.getBody();
    }

    @GetMapping ("/Finalize/user/{username}/{cost}")
    @ResponseBody
    Boolean getUserData (@PathVariable String username, @PathVariable double cost) {
        User result = callUserDB(username);
        if (result.getCredit().getCredit() >= cost) {
            return true;
        }
        return false;
    }

    private User callUserDB (String username) {
        String uri = "https://shopdb-service.herokuapp.com/username/"+username;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> user = restTemplate.exchange(uri,
        HttpMethod.GET,null, 
        new ParameterizedTypeReference<User>(){});
        return user.getBody();
    }

}