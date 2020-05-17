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

            return total;
        }
        catch (Exception e) {
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
                total="N/A";
            }

            return total;
        }
        catch (Exception e) {
            return "footprint not found";
        }
    }

    public void writeToFile(String text, String fileName)
    {
        File file = new File(fileDirectory, "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, fileName);
            FileWriter writer = new FileWriter(newFile, true);
            writer.append(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getLastTime()
    {
        File file = new File(fileDirectory, "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, "tempLastTime.txt");

            FileWriter writer = new FileWriter(newFile, true);
            writer.append("");
            writer.flush();
            writer.close();

            BufferedReader br = new BufferedReader(new FileReader(newFile));

            String total="";
            String st;
            while ((st = br.readLine()) != null)
            {
                total+=st;
            }

            if (total.equals(""))
            {
                total="0";
            }

            return total;
        }
        catch (Exception e) {
            Toast toast=Toast.makeText(applicationContext, "grep: "+e+" was thrown when trying to make toast", Toast.LENGTH_LONG);
            toast.show();
            return "0";
        }
    }

    public void clearFile(String fileName)
    {
        File file = new File(fileDirectory, "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, fileName);
            FileWriter writer = new FileWriter(newFile, false);
            writer.append("");
            writer.flush();
            writer.close();
            Toast toast=Toast.makeText(applicationContext, "grep: cleared file", Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast=Toast.makeText(applicationContext, "grep:"+e+"was thrown when trying to clear the file", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
