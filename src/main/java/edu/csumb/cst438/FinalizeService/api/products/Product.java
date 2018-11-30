package edu.csumb.cst438.finalizeservice.api.products;

public class Product {
    private String id;
    private ProductName name;
    private Description desc;
    private Image url;
    private Price price;
    private Stock stock;

    public Product() {
        super();
    }

    public Product (String id, ProductName name, Description desc, Image url, Price price, Stock stock) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.price = price;
        this.stock = stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setProductName(ProductName name) {
        this.name = name;
    }

    public ProductName getProductName() {
        return this.name;
    }

    public void setDescription(Description desc) {
        this.desc = desc;
    }

    public Description getDescription() {
        return this.desc;
    }

    public void setImageUrl(Image url) {
        this.url = url;
    }

    public Image getImageUrl() {
        return this.url;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Price getPrice() {
        return this.price;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Stock getStock() {
        return this.stock;
    }

}