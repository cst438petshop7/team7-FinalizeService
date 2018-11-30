package edu.csumb.cst438.finalizeservice.api.products;

public class Stock {
    private int stock;
    public Stock () {
        super();
    }

    public Stock (int stock) {
        super();
        this.stock = stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }

}