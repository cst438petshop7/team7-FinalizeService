package edu.csumb.cst438.finalizeservice.api.users;

public class Username {
    private String username;

    public Username(){
        super();
    }
    public Username(String username){
        super();
        this.username=username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return this.username;
    }
}