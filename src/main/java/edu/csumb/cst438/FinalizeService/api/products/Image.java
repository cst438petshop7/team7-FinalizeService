package edu.csumb.cst438.finalizeservice.api.products;

public class Image {
    private String url;
    public Image () {
        super();
    }
    public Image (String url) {
        super();
        this.url = url;
    }

    public void setImageUrl (String url) {
        this.url = url;
    }

    public String getImageUrl () {
        return this.url;
    }
}