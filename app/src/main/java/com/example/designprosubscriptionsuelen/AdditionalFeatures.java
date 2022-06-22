package com.example.designprosubscriptionsuelen;

public class AdditionalFeatures {
    //Additional Features Price
    public final static Double UPDATES_PRICE = 2.75;
    public final static Double UNLIMITED_DESIGNS_PRICE = 3.5;

    //Additional Features
    public final static String UPDATES = "Updates";
    public final static String UNLIMITED_DESIGNS = "Unlimited Designs";

    Double totalCost = 0.0;
    String message = "";

    //Return the total cost of Additional Features
    public Double getTotalCost() {
        return totalCost;
    }

    //Return the message used in Submit Button event
    public String getMessage(){
        if(!message.isEmpty())
            message = ", with " + message;

        return message;
    }

    //Calculate the price and message based on which checkbox is checked
    public Double calculateAdditionalFeatures(boolean isUpdatesChecked, boolean isUnlimitedDesignsChecked){
        //Initialize message and total cost to start the calculation
        this.message = "";
        this.totalCost = 0.0;

        //Add Updates Feature if marked on the screen
        if(isUpdatesChecked){
            addUpdatesFeature();
        }

        //Add Unlimited Designs Feature if marked on the screen
        if(isUnlimitedDesignsChecked){
            addUnlimitedDesignsFeature();
        }

        return  totalCost;
    }

    //Adds the cost and the feature in the message
    private void addUpdatesFeature(){
        totalCost += UPDATES_PRICE;
        message += AdditionalFeatures.UPDATES;
    }

    //Adds the cost and the feature in the message
    private void addUnlimitedDesignsFeature(){
        totalCost += UNLIMITED_DESIGNS_PRICE;
        message += (message == "" ? "" : ", and ") + UNLIMITED_DESIGNS;
    }
}
