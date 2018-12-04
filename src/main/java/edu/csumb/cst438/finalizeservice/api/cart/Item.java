package edu.csumb.cst438.finalizeservice.api.cart;

public class Item {
    private String id;
    private int amount;

    public Item(){
        super();
    }

    public Item (String id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return this.id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

}