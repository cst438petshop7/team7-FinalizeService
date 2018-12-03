package edu.csumb.cst438.finalizeservice.business;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.csumb.cst438.finalizeservice.api.products.Description;
import edu.csumb.cst438.finalizeservice.api.products.Image;
import edu.csumb.cst438.finalizeservice.api.products.Price;
import edu.csumb.cst438.finalizeservice.api.products.Product;
import edu.csumb.cst438.finalizeservice.api.products.ProductName;
import edu.csumb.cst438.finalizeservice.api.products.Stock;
import edu.csumb.cst438.finalizeservice.data.ProductDbClient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerTester {

    @Autowired
    Manager manager;

    @MockBean
    ProductDbClient productDb;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getProductListReturnsEmptyListWhenNullProductesInDb () {
        when(productDb.getProductData()).thenReturn(null);
        Assert.assertEquals(null, manager.getProductList());
        
    }

    @Test
    public void getProductListReturnsExpectedResults () {
        when(productDb.getProductData()).thenReturn(FiveStandardProductsStub());
        List<Product> expectedProducts = FiveStandardProductsStub();
        List<Product> actualProducts = manager.getProductList();
        // todo: use library or manually implement equals() for list-to-list .equals() comarison
        Assert.assertTrue(expectedProducts.get(1).getProductName().getProductName().equals(
            actualProducts.get(1).getProductName().getProductName()));
    }

    @Test
    public void getProductListPercolatesExceptionWhenThrownAtLowerLevel () {
        when(productDb.getProductData()).thenThrow(new RuntimeException("Test"));
        expectedException.expect(RuntimeException.class);
        manager.getProductList();
    }

    private List<Product> FiveStandardProductsStub () {
        List<Product> result = new ArrayList<Product>();
        Product p1 = new Product("1", new ProductName("1"), new Description("a"), new Image("b"), new Price(0.0), new Stock(5));
        Product p2 = new Product("2", new ProductName("2"), new Description("c"), new Image("d"), new Price(0.0), new Stock(5));
        Product p3 = new Product("3", new ProductName("3"), new Description("e"), new Image("f"), new Price(0.0), new Stock(5));
        Product p4 = new Product("4", new ProductName("4"), new Description("g"), new Image("h"), new Price(0.0), new Stock(5));
        Product p5 = new Product("5", new ProductName("5"), new Description("i"), new Image("j"), new Price(0.0), new Stock(5));
        result = Arrays.asList(p1,p2,p3,p4,p5);
        return result;
    }

    /* What to test Answers from class:
    //Right: manager returns expected results
    //Boundry Test - Error: exception bubbles up
    //boundry test - Error: database connection failure
    
    //boundry test - type or mapping error... misc database error.
    //boundry test - Existance: what happens if there are no products
    //boundry test - Range: too many products?
    //boundry test - range: no more than 10 products are returned at once.
    //performance test: obtain list of 1000 in less than 2 seconds
    //Boundry - conformance: what if product has no ProductName, Price, etc
    */
}