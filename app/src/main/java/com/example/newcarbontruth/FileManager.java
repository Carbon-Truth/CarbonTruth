package com.example.newcarbontruth;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* This class manages all the files for the app. Data is stored in files, and if other classes need that data,
 * this class will read and write from files for them.
 *
 * code for reading and writing a file in internal storage based on https://stackoverflow.com/questions/44587187/android-how-to-write-a-file-to-internal-storage
 */
public class FileManager
{
    private File fileDirectory;
    private Context applicationContext;

    public FileManager(File givenFileDirectory, Context givenApplicationContext)
    {
        fileDirectory=givenFileDirectory;
        applicationContext=givenApplicationContext;
    }

    //reads the file "temp.txt"
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
                total=total+st;
            }

            if (total.equals(""))
            {
                total="N/A";
            }

            return total;
        }
        catch (Exception e) {
            return "N/A";
        }
    }

    //gets the current value for "tempFootprint.txt", if nothing is found, returns "N/A"
    public String getFootprintValue() {
        File file = new File(fileDirectory, "theTempFiles");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            File newFile = new File(file, "tempFootprint.txt");

            if (!newFile.exists()) {
                newFile.mkdirs();
            }

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
            return "N/A";
        }
    }

    //write String text to file fileName.
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


    /* this is another method that would be used if the time-based app were to be fully implemented.
     * it gets a stored time value, the last time that the daily footprint was calculated. This value
     * indicates whether or not a new daily footprint can be calculated, i.e. whether or not a day has passed.
     */
//    public String getLastTime()
//    {
//        File file = new File(fileDirectory, "theTempFiles");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//
//        try {
//            File newFile = new File(file, "tempLastTime.txt");
//
//            FileWriter writer = new FileWriter(newFile, true);
//            writer.append("");
//            writer.flush();
//            writer.close();
//
//            BufferedReader br = new BufferedReader(new FileReader(newFile));
//
//            String total="";
//            String st;
//            while ((st = br.readLine()) != null)
//            {
//                total+=st;
//            }
//
//            if (total.equals(""))
//            {
//                total="0";
//            }
//
//            return total;
//        }
//        catch (Exception e) {
//            Toast toast=Toast.makeText(applicationContext, "grep: "+e+" was thrown when trying to make toast", Toast.LENGTH_LONG);
//            toast.show();
//            return "0";
//        }
//    }

    //clears given file
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
