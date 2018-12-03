package edu.csumb.cst438.finalizeservice.api.users;

public class User {
    private String id;
    private Username username;
    private Password password;
    private Cart cart;
    private Credit credit;

    public User(){
        super();
    }

    public User (String id, Username username, Password password, Credit credit, Cart cart) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cart = cart;
        this.credit = credit;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(Username username) {
        this.username = username;
    }
    
    public void setPassword(Password password) {
    	this.password = password;
    }

    public void setCartItems(Cart cart) {
        this.cart = cart;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public String getId() {
        return this.id;
    }

    public Username getUsername() {
        return this.username;
    }
    
    public Password getPassword() {
    	return this.password;
    }

    public Cart getCartItems() {
        return this.cart;
    }

    public Credit getCredit() {
        return this.credit;
    }

}