package com.example.designprosubscriptionsuelen.advancedTools;

import com.example.designprosubscriptionsuelen.R;

//Class that extends the abstract class Advanced Tools
//This will contain all the Monthly Advanced Tools processes
public class MonthlyAdvancedTools extends AdvancedTools {

    //Monthly Subscription Advanced Tools Price
    public final static Double CUSTOM_PRINTING_PRICE = 2.25;
    public final static Double PRO_TOOLS_PRICE = 3.75;

    //Monthly subscription advanced tools
    public final static String CUSTOM_PRINTING = "Custom Printing Tools";
    public final static String PRO_TOOLS = "Pro Tools";

    //Return the id in the string file for the second option
    public int getSecondOptionTextId(){
        return R.string.advanced_tool_monthly_custom_printing;
    }

    //Return the id in the string file for the third option
    public int getThirdOptionTextId(){
        return R.string.advanced_tool_monthly_pro;
    }

    //Chooses the second option
    public void chooseSecondOption(){
        this.advancedTools = CUSTOM_PRINTING;
        this.cost = CUSTOM_PRINTING_PRICE;
    }

    //Chooses the third option
    public void chooseThirdOption(){
        this.advancedTools = PRO_TOOLS;
        this.cost = PRO_TOOLS_PRICE;
    }
}
