package edu.csumb.cst438.finalizeservice.api.products;

public class ProductName {
    private String name;
    public ProductName () {
        super();
    }
    public ProductName (String name) {
        super();
        this.name = name;
    }

    public void setProductName (String name) {
        this.name = name;
    }

    public String getProductName () {
        return this.name;
    }
}