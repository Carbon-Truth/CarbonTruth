package com.example.newcarbontruth;

import android.content.Context;
import android.widget.Toast;

import java.util.Date;

/* this class isn't implemented in this version of the code (being non-essential to the MVP)
 * but it has the ability to tell time and store data, which would be useful in future iterations of the app.
 */

class TimeManager {
    FileManager fileManager;

//    public TimeManager(FileManager givenFileManager)
//    {
//        fileManager = givenFileManager;
//    }
//
//    //check if a day has passed since the last time the footprint was recorded.
//    public boolean dayHasPassed(Context applicationContext)
//    {
//        //current date
////        Date date = new Date();
//        long currentTime = System.currentTimeMillis();
//
//        //check if current time is greater than last time +24 hours. 86400000.0 milliseconds in a day
//        String lastTimeStr = fileManager.getLastTime();
//        long lastTime = Long.valueOf(lastTimeStr);
//
//        Toast toast=Toast.makeText(applicationContext, "grep: "+(currentTime-lastTime), Toast.LENGTH_LONG);
//        toast.show();
//
//        if (currentTime-lastTime>(long)86400000)
//        {
//            fileManager.clearFile("tempLastTime.txt");
//            fileManager.writeToFile(""+System.currentTimeMillis(), "tempLastTime.txt");
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//
//    public void takeAwayHalfADay(Context applicationContext)
//    {
//        //half a day=43200000 milliseconds
//        String lastTimeStr = fileManager.getLastTime();
//        String ancientLastTimeStr=lastTimeStr;
//        while (lastTimeStr.charAt(lastTimeStr.length()-1)==' ')
//        {
//            lastTimeStr=lastTimeStr.substring(0,lastTimeStr.length()-1);
//        }
//        long lastTime = Long.valueOf(lastTimeStr)-(long)432000000;
//        fileManager.clearFile("tempLastTime.txt");
//        fileManager.writeToFile(""+lastTime, "tempLastTime.txt");
//        Toast toast=Toast.makeText(applicationContext, "grep: "+lastTime+" vs initial "+ancientLastTimeStr, Toast.LENGTH_LONG);
//        toast.show();
//    }
}
