package com.example.designprosubscriptionsuelen;

import android.util.Log;

import com.example.designprosubscriptionsuelen.advancedTools.AdvancedTools;

import java.text.DecimalFormat;

public class Submit {
    //Tax Percentage
    final static Double TAX_PERCENTAGE = 0.13;

    private Subscription subscription;
    private AdditionalFeatures additionalFeatures;
    private Designs designs;
    private AdvancedTools advancedTools;
    private String customerName;
    private String date;
    private String province;

    private String message = "";
    private Double totalCost = 0.0;

    public Submit(Subscription subscription, AdditionalFeatures additionalFeatures, Designs designs,
                  AdvancedTools advancedTools, String customerName, String date, String province) {
        this.subscription = subscription;
        this.additionalFeatures = additionalFeatures;
        this.designs = designs;
        this.advancedTools = advancedTools;
        this.customerName = customerName;
        this.date = date;
        this.province = province;
    }

    //Builds the submit Message
    public String getMessage() {
        //Starts the structure og the message
        message = "On " + date + ", for " +
                customerName + " from " + province + ", a " +
                subscription.getChosenSubscription() + " subscription for " + designs.getChosenTypeOfDesign();

        //Adds the additional features chosen by the user to the message
        String additionalFeaturesMessage = additionalFeatures.getMessage();

        //Adds the advanced tools chosen by the user to the message
        String advancedToolsMessage = advancedTools.getMessage(additionalFeaturesMessage);

        //Finalize the message and calculate the total
        message += additionalFeaturesMessage + advancedToolsMessage +
                ", cost: " + calculateTotalFormatted();

        return message;
    }

    private String calculateTotalFormatted(){

        //Formats the price to show two decimal and $ in the beginning
        DecimalFormat df = new DecimalFormat("$0.00");

        //Initialize the internal variables
        Double subTotal = 0.0;
        Double taxes = 0.0;
        String formattedTotal = "";

        //Sums all the sections to form the total
        subTotal = subscription.getCost() + designs.getCost() +
                additionalFeatures.getTotalCost() + advancedTools.getCost();

        //Calculate the taxes
        taxes = subTotal * TAX_PERCENTAGE;

        //Format the sum of taxes and subTotal
        formattedTotal = df.format(subTotal + taxes);

        //Logs all the values for consult
        Log.i("subscriptionTypeCost", subscription.getCost().toString());
        Log.i("typeOfDesignCost", designs.getCost().toString());
        Log.i("additionalFeaturesCost", additionalFeatures.getTotalCost().toString());
        Log.i("advancedToolsCost", advancedTools.getCost().toString());
        Log.i("subTotal", subTotal.toString());
        Log.i("taxes", taxes.toString());
        Log.i("formattedTotal", formattedTotal);

        return formattedTotal;
    }
}
