package edu.csumb.cst438.finalizeservice.api.users;

public class Credit {
    private double credit;

    public Credit(){
        super();
    }

    public Credit(double credit){
        super();
        this.credit=credit;
    }
    public void setCredit(double credit){
        this.credit=credit;
    }
    public double getCredit(){
        return this.credit;
    }
}