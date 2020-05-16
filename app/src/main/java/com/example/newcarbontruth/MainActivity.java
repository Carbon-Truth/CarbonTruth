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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView answerText=findViewById(R.id.footprintValue);
        answerText.setText(getFootprintValue());

        Toast toast=Toast.makeText(getApplicationContext(), "grep: you toast", Toast.LENGTH_LONG);
        toast.show();
    }

    public void inputCarbon(View v) { setContentView(R.layout.add_carbon); }

    public void onFoodPopUpClick(View v)
    {
        setContentView(R.layout.food_popup);
    }

    public void onVehiclesPopUpClick(View v)
    {
        setContentView(R.layout.vehicle_popup);
    }

    public void onFoodItem1Click(View v)
    {
        saveNewType("f", "dairy");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onFoodItem2Click(View v)
    {
        saveNewType("f","fat");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onFoodItem3Click(View v)
    {
        saveNewType("f","grain");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onFoodItem4Click(View v)
    {
        saveNewType("f","meat");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onFoodItem5Click(View v)
    {
        saveNewType("f","vegetables");
        setContentView(R.layout.food_quantity_popup);
    }

    public void onVehicleItem1Click(View v)
    {
        saveNewType("v","Bicycle");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void onVehicleItem2Click(View v)
    {
        saveNewType("v","Car");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void onVehicleItem3Click(View v)
    {
        saveNewType("v","Motorcycle");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void onVehicleItem4Click(View v)
    {
        saveNewType("v","Transit bus");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void onVehicleItem5Click(View v)
    {
        saveNewType("v","Van/Light Truck");
        setContentView(R.layout.vehicle_quantity_popup);
    }

    public void submit(View v)
    {
        EditText givenQuantity=findViewById(R.id.quantity_input);
        String quantity=givenQuantity.getText().toString();

        if (quantity.isEmpty())
        {
            quantity="0";
        }

        saveQuantity(quantity);

        setContentView(R.layout.activity_main);

        TextView answerText=findViewById(R.id.mostRecentInputStr);
        answerText.setText(formatTemp(readTempFile()));
    }

    public String formatTemp(String temp)
    {
        String string = "";
        while (!temp.equals("")&&!temp.equals(" "))
        {
            String type=temp.substring(0, temp.indexOf(' '));
            temp=temp.substring(temp.indexOf(' ')+1);
            String rest=temp.substring(0, temp.indexOf('|'));
            temp=temp.substring(temp.indexOf('|')+1);

            string = string + rest + " ";
        }

        if (string.equals(""))
        {
            string="N/A";
        }
        return string;
    }




    //the final block that closes readers from https://stackoverflow.com/questions/37037718/is-it-necessary-to-call-close-in-a-finally-when-writing-files-in-java



    public String readTempFile()
    {
        File file = new File(getFilesDir(), "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, "temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(newFile));

            String total="";
            String st;
            while ((st = br.readLine()) != null)
            {
                total=total+st+" ";
            }

            Toast toast=Toast.makeText(getApplicationContext(), "grep: Found "+total, Toast.LENGTH_LONG);
            toast.show();

            return total;
        }
        catch (Exception e) {
            Toast toast=Toast.makeText(getApplicationContext(), "grep: "+e+" was thrown", Toast.LENGTH_LONG);
            toast.show();
            return "Nothing Found";
        }
    }

    //saves new type of source of carbon in the file temp, to be read at the end of the day.
    private void saveNewType(String carOrFood, String sourceType)
    {
//        File directory;
//        if (filename.isEmpty()) {
//        directory = getFilesDir();
//    }
//        else {
//            directory = getDir(filename, MODE_PRIVATE);
//        }
//        File[] files = directory.listFiles();
        File file = new File(getFilesDir(), "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, "temp.txt");
            FileWriter writer = new FileWriter(newFile, true);
            writer.append(carOrFood+" "+sourceType+" ");
            writer.flush();
            writer.close();
            Toast toast=Toast.makeText(getApplicationContext(), "grep: wrote "+carOrFood+" "+sourceType+" in temp", Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast=Toast.makeText(getApplicationContext(), "grep:"+e+"was thrown when trying to write into the file", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void saveQuantity(String quantity)
    {
        File file = new File(getFilesDir(), "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, "temp.txt");
            FileWriter writer = new FileWriter(newFile, true);
            writer.append(quantity+"|");
            writer.flush();
            writer.close();
            Toast toast=Toast.makeText(getApplicationContext(), "grep: wrote "+quantity+" in temp", Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast=Toast.makeText(getApplicationContext(), "grep:"+e+"was thrown when trying to write into the file", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private String getFootprintValue() {
        File file = new File(getFilesDir(), "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, "tempFootprint.txt");

            FileWriter writer = new FileWriter(newFile, true);
            writer.append("");
            writer.flush();
            writer.close();

            BufferedReader br = new BufferedReader(new FileReader(newFile));

            String total="";
            String st;
            while ((st = br.readLine()) != null)
            {
                total=total+st+" ";
            }

            if (total.equals(""))
            {
                total="Not Yet";
            }

            return total;
        }
        catch (Exception e) {
            Toast toast=Toast.makeText(getApplicationContext(), "grep: "+e+" was thrown when trying to make toast", Toast.LENGTH_LONG);
            toast.show();
            return "footprint not found";
        }
    }

    public void calculateCurrentFootprint(View v)
    {
        ArrayList<String> foodList = new ArrayList<String>();
        ArrayList<String> vehicleList = new ArrayList<String>();

        String temp = readTempFile();

        while (!temp.equals("")&&!temp.equals(" "))
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

        Calculations calculate = new Calculations(foodList, vehicleList);
        String footprint = ""+calculate.getFootprint();
        if (footprint.length()>8)
        {
            footprint=footprint.substring(0, 8);
        }

        TextView answerText=findViewById(R.id.footprintValue);
        answerText.setText(footprint);
    }

}
