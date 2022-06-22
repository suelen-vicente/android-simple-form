package com.example.designprosubscriptionsuelen;

//Class to handle all Subscription related processes
public class Subscription {
    //Subscription Type Price
    public  final static Double MONTHLY_PRICE = 12.5;
    public final static Double YEARLY_PRICE = 140.0;

    //Subscription Types
    public final static String SUBSCRIPTION_MONTHLY = "Monthly";
    public final static String SUBSCRIPTION_YEARLY = "Yearly";

    private String chosenSubscription;
    private Double cost;

    //Initialize Subscription with Monthly
    public Subscription() {
        this.chosenSubscription = SUBSCRIPTION_MONTHLY;
        this.cost = MONTHLY_PRICE;
    }

    //Return the subscription type chosen by the user
    public String getChosenSubscription() {
        return chosenSubscription;
    }

    //Returns the subscription cost for the type chosen
    public Double getCost() {
        return cost;
    }

    //Chooses Monthly subscription
    public void chooseMonthlySubscription(){
        chosenSubscription = SUBSCRIPTION_MONTHLY;
        cost = MONTHLY_PRICE;
    }

    //Chooses Yearly subscription
    public void chooseYearlySubscription(){
        chosenSubscription = SUBSCRIPTION_YEARLY;
        cost = YEARLY_PRICE;
    }
}
