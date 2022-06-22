package com.example.designprosubscriptionsuelen;

public class Designs {
    //Type of Designs Price
    public final static Double LABELS_DESIGN_TYPE_PRICE = 2.5;
    public final static Double CARDS_DESIGN_TYPE_PRICE = 3.5;
    public final static Double BADGES_DESIGN_TYPE_PRICE = 4.25;

    //Type of Designs
    final static String LABELS = "Labels";
    final static String CARDS = "Cards";
    final static String BADGES = "Badges";

    private String chosenTypeOfDesign;
    private Double cost = LABELS_DESIGN_TYPE_PRICE;

    //return the chosen type of design
    public String getChosenTypeOfDesign() {
        return chosenTypeOfDesign;
    }

    //Return the cost
    public Double getCost() {
        return cost;
    }

    //Process cost of Type Of Design based on the chosen option
    public void processTypeOfDesign(String chosenDesign) {
        this.chosenTypeOfDesign = chosenDesign;

        //Defines the price for Type Of Design
        switch (chosenTypeOfDesign){
            case LABELS:
                cost = LABELS_DESIGN_TYPE_PRICE;
                break;
            case CARDS:
                cost = CARDS_DESIGN_TYPE_PRICE;
                break;
            case BADGES:
                cost = BADGES_DESIGN_TYPE_PRICE;
                break;
        }
    }
}
