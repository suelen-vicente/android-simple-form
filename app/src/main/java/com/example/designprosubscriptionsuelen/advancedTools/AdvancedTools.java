package com.example.designprosubscriptionsuelen.advancedTools;

//Abstract class that handle Advanced Tools
// Two specific classes were created based on this one: Monthly and Yearly
public abstract class AdvancedTools {

    // This is de default option of Advanced Tools
    public final String NONE = "None";

    String advancedTools;
    Double cost;

    //Sets NONE option as chosen
    public void chooseNoneOption(){
        this.advancedTools = NONE;
        this.cost = 0.0;
    }

    //Return the cost of the Advanced Tool chosen
    public Double getCost() {
        return cost;
    }

    //Builds the final message that will be displayed on Submit Button event
    public String getMessage(String additionalFeaturesMessage){
        //Checks if any Advanced Tools (besides None) was chosen
        if(advancedTools != NONE){

            //If any Additional Feature has not been added yet,
            // we should add the ", with " to form the phrase
            if(additionalFeaturesMessage.isEmpty())
                return ", with " + advancedTools;

            //Otherwise, if any Additional Feature has been already added,
            // we should add the ", and " to continue the phrase
            return ", and " + advancedTools;
        }

        //If no Advanced Tools is chosen, we should return empty
        return "";
    }

    public abstract int getSecondOptionTextId();

    public abstract int getThirdOptionTextId();

    public abstract void chooseSecondOption();

    public abstract void chooseThirdOption();
}
