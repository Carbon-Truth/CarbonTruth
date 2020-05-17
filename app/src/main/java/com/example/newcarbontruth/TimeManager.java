package com.example.newcarbontruth;

import android.content.Context;
import android.widget.Toast;

import java.util.Date;

class TimeManager {
    FileManager fileManager;

    public TimeManager(FileManager givenFileManager)
    {
        fileManager = givenFileManager;
    }

    //check if a day has passed since the last time the footprint was recorded.
    public boolean dayHasPassed()
    {
        //current date
//        Date date = new Date();
        long currentTime = System.currentTimeMillis();

        //check if current time is greater than last time +24 hours. 86400000.0 milliseconds in a day
        String lastTimeStr = fileManager.getLastTime();
        long lastTime = Long.valueOf(lastTimeStr);

        if (currentTime>lastTime+86400000)
        {
            fileManager.clearFile("tempLastTime.txt");
            fileManager.writeToFile(""+System.currentTimeMillis(), "tempLastTime.txt");
            return true;
        }
        else
        {
            return false;
        }
    }

    public void takeAwayHalfADay(Context applicationContext)
    {
        //half a day=43200000 milliseconds
        String lastTimeStr = fileManager.getLastTime();
        String ancientLastTimeStr=lastTimeStr;
        while (lastTimeStr.charAt(lastTimeStr.length()-1)==' ')
        {
            lastTimeStr=lastTimeStr.substring(0,lastTimeStr.length()-1);
        }
        long lastTime = Long.valueOf(lastTimeStr)-432000000;
        fileManager.clearFile("tempLastTime.txt");
        fileManager.writeToFile(""+lastTimeStr, "tempLastTime.txt");
        Toast toast=Toast.makeText(applicationContext, "grep: "+lastTime+" vs initial "+ancientLastTimeStr, Toast.LENGTH_LONG);
        toast.show();
    }
}
