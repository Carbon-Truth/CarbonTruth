package com.example.newcarbontruth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* This class is the main class for the app. It manages all the interaction between classes. It also
     * manages the changes between different layouts through the various methods linked to the onClick values
     * present in the xml of the layouts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* checks if a day has passed since the last time the footprint was recorded.
         * this portion of the code is commented out for now, as the time-reliant portion
         * of the app isn't part of the MVP. However, it could be included in future
         * iterations of the app.
         */
//        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
//        TimeManager timeManager = new TimeManager(fileManager);
//
//        if (timeManager.dayHasPassed(getApplicationContext()))
//        {
//            String footprintForYesterday = calculateCurrentFootprint();
//            fileManager.writeToFile(footprintForYesterday, "tempFootprint.txt");
//        }

        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());

        //sets the ui textview footprintValue to the footprint value stored in "tempFootprint.txt"
        TextView answerText=findViewById(R.id.footprintValue);
        answerText.setText(fileManager.getFootprintValue());
    }

    //all of the following methods change the layout when a button is clicked
    public void inputCarbon(View v)
    {
        setContentView(R.layout.add_carbon);
    }

    public void onFoodPopUpClick(View v)
    {
        setContentView(R.layout.food_popup);
    }

    public void onVehiclesPopUpClick(View v)
    {
        setContentView(R.layout.vehicle_popup);
    }

    /* when clicked, these items also store what type of item the user clicked in the "temp.txt" file
     * so that the values that they input will be identifiable later on.
     */
    public void onFoodItem1Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("f"+" "+"dairy"+" ", "temp.txt");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onFoodItem2Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("f"+" "+"fat"+" ","temp.txt");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onFoodItem3Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("f"+" "+"grain"+" ","temp.txt");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onFoodItem4Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("f"+" "+"meat"+" ","temp.txt");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onFoodItem5Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("f"+" "+"vegetables"+" ","temp.txt");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onVehicleItem1Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("v"+" "+"Bicycle"+" ","temp.txt");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void onVehicleItem2Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("v"+" "+"Car"+" ","temp.txt");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void onVehicleItem3Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("v"+" "+"Motorcycle"+" ","temp.txt");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void onVehicleItem4Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("v"+" "+"Transit bus"+" ","temp.txt");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void onVehicleItem5Click(View v)
    {
        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile("v"+" "+"Van/Light Truck"+" ","temp.txt");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void submit(View v)
    {
        //puts the quantity that was just inputted by the user into the "temp.txt" file
        EditText givenQuantity=findViewById(R.id.quantity_input);
        String quantity=givenQuantity.getText().toString();

        //if the user fails to input data, the quantity is written as 0, and won't end up affecting the overall footprint.
        if (quantity.isEmpty())
        {
            quantity="0";
        }

        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        fileManager.writeToFile(quantity+"|", "temp.txt");

        setContentView(R.layout.activity_main);
    }

    //calculates current footprint, from a button (hence the need for a (View V) input)
    public void calculateCurrentFootprint(View v)
    {
        calculateCurrentFootprint();
    }

    public String calculateCurrentFootprint()
    {
        ArrayList<String> foodList = new ArrayList<String>();
        ArrayList<String> vehicleList = new ArrayList<String>();

        FileManager fileManager = new FileManager(getFilesDir(), getApplicationContext());
        String temp = fileManager.readTempFile();

        /* takes all the formatting done earlier and parses through it, separating the information
         * gathered into two ArrayLists, foodList and vehicleList. An example of the structure for
         * information in the file is "v Bicycle 10.0|f vegetables 2.0|"
         */

        try{
            while (!temp.equals("")&&!temp.equals(" ")&&!temp.equals("N/A")&&!temp.equals("Found"))
            {
                String type=temp.substring(0, temp.indexOf(' '));
                temp=temp.substring(temp.indexOf(' ')+1);
                String rest=temp.substring(0, temp.indexOf('|'));
                temp=temp.substring(temp.indexOf('|')+1);

                if (type.equals("f"))
                {
                    foodList.add(rest);
                }
                else
                {
                    vehicleList.add(rest);
                }
            }
        }
        catch (Exception e)
        {
        }

        if (!temp.equals("N/A")&&!temp.equals("Found"))
        {
            //actually calculates the footprint, using the Calculations class.
            Calculations calculate = new Calculations(foodList, vehicleList);
            String footprint = ""+calculate.getFootprint();
            //keeps the footprint string at a manageable size (otherwise it could have too many decimal places)
            if (footprint.length()>8)
            {
                footprint=footprint.substring(0, 8);
            }

            TextView answerText=findViewById(R.id.footprintValue);
            answerText.setText(footprint);
            return footprint;
        }
        TextView answerText=findViewById(R.id.footprintValue);
        answerText.setText("N/A");
        return "N/A";
    }

}
