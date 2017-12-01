package com.example.a100541476.roomfinder;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by 100541476 on 11/30/2017.
 */

public class RetrieveRoomSchedule extends AsyncTask<String, Void, String>{
    private final String TAG = "RetrieveRoomSchedule";
    private Exception exception = null;
    private RetrieveRoomScheduleListener listener;

    public RetrieveRoomSchedule(RetrieveRoomScheduleListener listener) {this.listener = listener;}

    protected String doInBackground(String... params){
        String schedule = "";

        String setURL = "https://roomfinder-187219.appspot.com/schedule?key=dsproj&day="+params[0]+"&room="+params[1];
        Log.d(TAG, setURL);
        try{
            schedule = getSchedule(setURL);
        } catch (MalformedURLException e){
            Log.d(TAG, e.getMessage());
        } catch (IOException e){
            Log.d(TAG, e.getMessage());
        }

        return schedule;
    }

    private String getSchedule(String url) throws IOException{
        String scheduleIn = "";
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = "";
            while((line = br.readLine()) != null){
                scheduleIn = line;
                Log.d(TAG, line + "\n");
            }
        } finally {
            is.close();
        }
        return scheduleIn;
    }

    protected void onPostExecute(String s){
        if(this.exception != null){
            Log.d(TAG, this.exception.getMessage());
        } else {
            this.listener.roomScheduleRetrieved(s);
        }
    }
}
