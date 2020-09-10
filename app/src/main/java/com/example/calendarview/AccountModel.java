package com.example.calendarview;

import java.util.Date;

public class AccountModel {


    /*private int id;*/
    private String date;
    private float income;
    private float outcome;
    private String description;
    //constructor
    public AccountModel(String date, float income, float outcome, String description){
        /*this.id = id;*/
        this.date = date;
        this.income = income;
        this.outcome = outcome;
        this.description = description;
    }

    public AccountModel(){

    }

    // toString is necessary for printing the content of a class object
    @Override
    public String toString() {
        /*"com.example.calendarview.accountModel{" +
                "id=" +id +*/
                if(income!=0){
                    return
                            date +
                            "\nIncome: " + income +
                            /*", Outcome: " + outcome +*/
                            "\nDescription: '" + description + '\'';
                }else {
                    return date +
                            /*"\nIncome: " + income +*/
                            "\nOutcome: " + outcome +
                            "\nDescription: '" + description + '\'';
                }
    }

    //Getters & Setters
    /*public int getId() { return id; }

    public void setId(int id) { this.id = id; }*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getOutcome() {
        return outcome;
    }

    public void setOutcome(float outcome) {
        this.outcome = outcome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
