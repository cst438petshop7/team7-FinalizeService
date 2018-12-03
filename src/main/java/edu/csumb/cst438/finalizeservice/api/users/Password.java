package edu.csumb.cst438.finalizeservice.api.users;

public class Password {
    private String password;

    public Password(){
        super();
    }
    public Password(String password){
        super();
        this.password = password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
}