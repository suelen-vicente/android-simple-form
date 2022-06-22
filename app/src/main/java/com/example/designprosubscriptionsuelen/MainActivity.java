package com.example.designprosubscriptionsuelen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.designprosubscriptionsuelen.advancedTools.AdvancedTools;
import com.example.designprosubscriptionsuelen.advancedTools.MonthlyAdvancedTools;
import com.example.designprosubscriptionsuelen.advancedTools.YearlyAdvancedTools;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

// June 19, 2022
// Suelen Cristina Blasques Goes Vicente
// Student ID: 8752253
// Assignment 1
// PROG8480 - Mobile Application Development - Android
// Professor Thomas Mathew
// Mobile Solutions Development - Conestoga College

public class MainActivity extends AppCompatActivity {

    //Binding the widgets
    LinearLayout lnDesignPro;
    EditText edtCustomerName, edtDate;
    RadioGroup rgSubscriptionType, rgAdvancedTools;
    RadioButton rdSubscriptionTypeMonthly, rdSubscriptionTypeYearly, rdAdvancedToolNone,
            rdAdvancedToolSecond, rdAdvancedToolThird;
    CheckBox chkAdditionalFeatureUpdates, chkAdditionalFeatureUnlimitedDesigns;
    Spinner spnTypeOfDesign;
    AutoCompleteTextView acProvince;
    Button btnSubmit;

    //Related Classes
    Subscription subscription;
    AdditionalFeatures additionalFeatures;
    Designs designs;
    AdvancedTools advancedTools;

    String province = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiating all widgets
        instantiatesWidgets();

        //Instantiates Related Classes
        instantiatesRelatedClasses();

        //Configure initial states for relevant widgets
        configureInitialState();

        //Configure Province Array Adapter
        configureProvinceArrayAdapter();

        //Configures Type of Design Adapter
        configureTypeOfDesignArrayAdapter();

        //Configuring Date Field
        // Since this is an date field, we can set the input type to NULL
        // This will hide the keyboard and the user will need to use the DatePicker.
        edtDate.setInputType(InputType.TYPE_NULL);

        //Subscription Type Radio Group Listener
        rgSubscriptionType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            //Listener for the radio group buttons Monthly and Yearly
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Find the reference of the radio button that were selected inside of the
                // radio group widget
                RadioButton rb = group.findViewById(checkedId);

                //Handles each of the radio button options separately
                // and assign specific behaviour to each one of them
                if (rb.getId() == R.id.rdSubscriptionTypeMonthly) {
                    //Sets the monthly subscription and defines the advanced tools that will be used
                    subscription.chooseMonthlySubscription();
                    advancedTools = new MonthlyAdvancedTools();
                } else {
                    //Sets the yearly subscription and defines the advanced tools that will be used
                    subscription.chooseYearlySubscription();
                    advancedTools = new YearlyAdvancedTools();
                }

                //Defines the labels in the Advanced Options
                rdAdvancedToolSecond.setText(advancedTools.getSecondOptionTextId());
                rdAdvancedToolThird.setText(advancedTools.getThirdOptionTextId());

                //After rewriting the names of the options in the Advanced Tools section,
                // sets the selected option to None
                rgAdvancedTools.check(rdAdvancedToolNone.getId());
                advancedTools.chooseNoneOption();
            }
        });

        //Listener for spinner that will manipulate selected items inside of spinner
        spnTypeOfDesign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Process chosen option and price for Type of Design section
                designs.processTypeOfDesign(spnTypeOfDesign.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Advanced Tools Radio Group Listener
        rgAdvancedTools.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            //Listener for the radio group buttons Monthly and Yearly
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Find the reference of the radio button that were selected inside of the
                // radio group widget
                RadioButton rb = group.findViewById(checkedId);

                //Handles each of the radio button options separately
                // and assign specific behaviour to each one of them
                if (rb.getId() == R.id.rdAdvancedToolSecond) {
                    advancedTools.chooseSecondOption();
                } else if(rb.getId() == R.id.rdAdvancedToolThird) {
                    advancedTools.chooseThirdOption();
                }else{
                    advancedTools.chooseNoneOption();
                }
            }
        });

        //Listener for auto-complete field
        acProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = parent.getItemAtPosition(position);
                province = obj.toString();
            }
        });

        //Configures the listener on the DateField, so everytime that the user
        // clicks on it, a DatePicker will be invoked
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Configures the Date Picker dialog that will be displayed
                // everytime th user clicks on the field
                DatePickerDialog datePicker = configureDatePicker();

                //Displays the DatePicker on the OnClick event of the date field
                datePicker.show();
            }
        });

        //Configures the listener to the Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAllMandatoryFieldsFilled()) {
                    //Calculate the total price of additional features
                    additionalFeatures.calculateAdditionalFeatures(chkAdditionalFeatureUpdates.isChecked(),
                            chkAdditionalFeatureUnlimitedDesigns.isChecked());

                    //Creates te submit object, which is responsible for calculating the totals and
                    // building the final message
                    Submit submit = new Submit(subscription, additionalFeatures, designs, advancedTools,
                            edtCustomerName.getText().toString(), edtDate.getText().toString(), province);

                    //Get the final message
                    String message = submit.getMessage();

                    //Logs the message in Logcat
                    Log.i("Final Submit Message", message);

                    //Show the final message in a SnackBar
                    Snackbar.make(lnDesignPro, message, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    //Instantiates widgets
    private void instantiatesWidgets() {
        //Instantiating Linear layout for SnackBar
        lnDesignPro = (LinearLayout) findViewById(R.id.lnDesignPro);

        //Instantiating EditText
        edtCustomerName = (EditText) findViewById(R.id.edtCustomerName);
        edtDate = (EditText) findViewById(R.id.edtDate);

        //Instantiating RadioGroup
        rgSubscriptionType = (RadioGroup) findViewById(R.id.rgSubscriptionType);
        rgAdvancedTools = (RadioGroup) findViewById(R.id.rgAdvancedTools);

        //Instantiating RadioButton
        rdSubscriptionTypeMonthly = (RadioButton) findViewById(R.id.rdSubscriptionTypeMonthly);
        rdSubscriptionTypeYearly = (RadioButton) findViewById(R.id.rdSubscriptionTypeYearly);
        rdAdvancedToolNone = (RadioButton) findViewById(R.id.rdAdvancedToolNone);
        rdAdvancedToolSecond = (RadioButton) findViewById(R.id.rdAdvancedToolSecond);
        rdAdvancedToolThird = (RadioButton) findViewById(R.id.rdAdvancedToolThird);

        //Instantiating CheckBox
        chkAdditionalFeatureUpdates = (CheckBox) findViewById(R.id.chkAdditionalFeatureUpdates);
        chkAdditionalFeatureUnlimitedDesigns = (CheckBox) findViewById(R.id.chkAdditionalFeatureUnlimitedDesigns);

        //Instantiating Spinner
        spnTypeOfDesign = (Spinner) findViewById(R.id.spnTypeOfDesign);

        //Instantiating AutoCompleteTextView
        acProvince = (AutoCompleteTextView) findViewById(R.id.acProvince);

        //Instantiating Button
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    //Instantiates all related classes
    private void instantiatesRelatedClasses(){
        subscription = new Subscription();
        additionalFeatures = new AdditionalFeatures();
        designs = new Designs();
        //Instantiates advanced tools with Monthly configuration by default
        advancedTools = new MonthlyAdvancedTools();

    }

    //Configure Initial States of all relevant widgets
    private void configureInitialState() {
        //Check Monthly in Subscription Type as default
        rgSubscriptionType.check(rdSubscriptionTypeMonthly.getId());

        //Uncheck all the additional features by default
        chkAdditionalFeatureUpdates.setChecked(false);
        chkAdditionalFeatureUnlimitedDesigns.setChecked(false);

        //Check None in Advanced Tools as default
        rgAdvancedTools.check(rdAdvancedToolNone.getId());

        //Selects None option by default in Advanced Tools
        advancedTools.chooseNoneOption();
    }

    //Configure Array Adapter for Province used in the Auto Complete field
    private void configureProvinceArrayAdapter() {
        //Defines that the auto-complete will work once the user out 2 characters on the field
        acProvince.setThreshold(2);

        //Defining the array adapter that will serve as data source to auto-complete widget
        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this,
                R.array.province_array, android.R.layout.simple_dropdown_item_1line);
        //Apply the adapter to the auto-complete
        acProvince.setAdapter(adapterProvince);
    }

    //Configure Array Adapter for Type Of Design used in the Auto Complete field
    private void configureTypeOfDesignArrayAdapter() {
        //Defining the array adapter that will serve as data source to spinner widget.
        ArrayAdapter<CharSequence> adapterTypeOfDesign = ArrayAdapter.createFromResource(this,
                R.array.type_of_designs_array, android.R.layout.simple_spinner_item);

        //Specify the layout to be a dropdown when the list of choices appears
        adapterTypeOfDesign.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapter to the spinner
        spnTypeOfDesign.setAdapter(adapterTypeOfDesign);
    }

    //Configure the Date picker Dialog that will be used on the edtDate field
    private DatePickerDialog configureDatePicker() {
        //We will use a calendar object to control the dates
        Calendar cal = Calendar.getInstance();
        int dayOfSubscription = cal.get(Calendar.DAY_OF_MONTH);
        int monthOfSubscription = cal.get(Calendar.MONTH);
        int yearOfSubscription = cal.get(Calendar.YEAR);

        //This will configure a listener to the DatePicker invoked
        return new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        }, yearOfSubscription, monthOfSubscription, dayOfSubscription);
    }

    //Validates if the mandatory fields are filled
    private boolean isAllMandatoryFieldsFilled(){

        if(edtCustomerName.getText().toString().isEmpty()){
            //Checks if Customer Name is filled
            edtCustomerName.setError("Customer name is mandatory!");
            edtCustomerName.requestFocus();
            return false;
        }else if (province.isEmpty()){
            //Checks if Province is filled
            acProvince.setError("Province is mandatory!");
            acProvince.requestFocus();
            return false;
        }else if(edtDate.getText().toString().isEmpty()){
            //Checks if Date is filled
            edtDate.setError("Date is mandatory!");
            edtDate.requestFocus();
            return false;
        }else{
            edtCustomerName.setError(null);
            acProvince.setError(null);
            edtDate.setError(null);
        }

        return true;
    }


}