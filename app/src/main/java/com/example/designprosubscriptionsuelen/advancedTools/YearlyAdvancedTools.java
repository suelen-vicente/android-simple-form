package com.example.designprosubscriptionsuelen.advancedTools;

import com.example.designprosubscriptionsuelen.R;

//Class that extends the abstract class Advanced Tools
//This will contain all the Yearly Advanced Tools processes
public class YearlyAdvancedTools extends AdvancedTools {

    //Yearly Subscription Advanced Tools Price
    public final static Double MATERIAL_DESIGNS_PRICE = 3.5;
    public final static Double PREMIUM_TOOLS_PRICE = 4.5;

    //Yearly subscription advanced tools
    public final static String MATERIAL_DESIGNS = "Material Designs";
    public final static String PREMIUM_TOOLS = "Premium Tools";

    //Return the id in the string file for the second option
    public int getSecondOptionTextId(){
        return R.string.advanced_tool_yearly_material_designs;
    }

    //Return the id in the string file for the third option
    public int getThirdOptionTextId(){
        return R.string.advanced_tool_yearly_premium;
    }

    //Chooses the second option
    public void chooseSecondOption(){
        this.advancedTools = MATERIAL_DESIGNS;
        this.cost = MATERIAL_DESIGNS_PRICE;
    }

    //Chooses the third option
    public void chooseThirdOption(){
        this.advancedTools = PREMIUM_TOOLS;
        this.cost = PREMIUM_TOOLS_PRICE;
    }


}
