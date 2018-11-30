package edu.csumb.cst438.finalizeservice.api.products;

public class Description {
    private String desc;
    public Description () {
        super();
    }
    public Description (String desc) {
        super();
        this.desc = desc;
    }

    public void setDescription (String desc) {
        this.desc = desc;
    }

    public String getDescription () {
        return this.desc;
    }
}