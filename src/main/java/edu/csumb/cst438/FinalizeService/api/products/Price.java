package edu.csumb.cst438.finalizeservice.api.products;

public class Price {
    private double price;
    public Price () {
        super();
    }
    public Price (double price) {
        super();
        this.price = price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public Double getPrice () {
        return this.price;
    }
}