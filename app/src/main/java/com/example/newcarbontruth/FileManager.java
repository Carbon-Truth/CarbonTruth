package com.example.newcarbontruth;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private File fileDirectory;
    private Context applicationContext;

    public FileManager(File givenFileDirectory, Context givenApplicationContext)
    {
        fileDirectory=givenFileDirectory;
        applicationContext=givenApplicationContext;
    }


    public String readTempFile()
    {
        File file = new File(fileDirectory, "theTempFiles");
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

            Toast toast=Toast.makeText(applicationContext, "grep: Found "+total, Toast.LENGTH_LONG);
            toast.show();

            return total;
        }
        catch (Exception e) {
            Toast toast=Toast.makeText(applicationContext, "grep: "+e+" was thrown", Toast.LENGTH_LONG);
            toast.show();
            return "Nothing Found";
        }
    }

    public String getFootprintValue() {
        File file = new File(fileDirectory, "theTempFiles");
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
            Toast toast=Toast.makeText(applicationContext, "grep: "+e+" was thrown when trying to make toast", Toast.LENGTH_LONG);
            toast.show();
            return "footprint not found";
        }
    }

    //saves new type of source of carbon in the file temp, to be read at the end of the day.
    public void saveNewType(String carOrFood, String sourceType)
    {
        File file = new File(fileDirectory, "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, "temp.txt");
            FileWriter writer = new FileWriter(newFile, true);
            writer.append(carOrFood+" "+sourceType+" ");
            writer.flush();
            writer.close();
            Toast toast=Toast.makeText(applicationContext, "grep: wrote "+carOrFood+" "+sourceType+" in temp", Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast=Toast.makeText(applicationContext, "grep:"+e+"was thrown when trying to write into the file", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void saveQuantity(String quantity)
    {
        File file = new File(fileDirectory, "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, "temp.txt");
            FileWriter writer = new FileWriter(newFile, true);
            writer.append(quantity+"|");
            writer.flush();
            writer.close();
            Toast toast=Toast.makeText(applicationContext, "grep: wrote "+quantity+" in temp", Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast=Toast.makeText(applicationContext, "grep:"+e+"was thrown when trying to write into the file", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
