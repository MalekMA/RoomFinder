package com.example.a100541476.roomfinder;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
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

public class RetrieveNextClass extends AsyncTask<String, Void, String[]> {
    private final String TAG = "RetrieveNextClass";
    private Exception exception = null;
    private NextClassListener listener;

    private String[] toReturn = new String[4];

    public RetrieveNextClass(NextClassListener listener) {this.listener = listener;}

    protected String[] doInBackground(String... params){
        String next = "";
        String reviews = "No Reviews";

        String setURL = "https://roomfinder-187219.appspot.com/byname?key=dsproj&day=" + params[0] + "&time=" + params[1] + "&room=" + params[2];
        Log.d(TAG, setURL);

        try {
            next = getNextTime(setURL);
        }catch (MalformedURLException e){
            Log.d(TAG, e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        toReturn[0] = next;

        setURL = "https://roomfinder-187219.appspot.com/getreviews?key=dsproj&room=" + params[2];
        Log.d(TAG, setURL);

        try {
            reviews = getReviews(setURL);
        }catch (MalformedURLException e){
            Log.d(TAG, e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        toReturn[1] = reviews;

        return toReturn;
    }

    private String getReviews(String url) throws IOException{
        String in = "";
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = "";
            while((line = br.readLine()) != null){
                in = line;
                Log.d(TAG, line + "\n");
            }
        } finally {
            is.close();
        }
        return in;
    }

    private String getNextTime(String url) throws IOException{
        String in = "";
        boolean updated = false;
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = "";
            while((line = br.readLine()) != null){
                 in = line;
                 updated = true;
                Log.d(TAG, line + "\n");
            }
        } finally {
            is.close();
        }
        if(updated) {
            toReturn[2] = "t";
            String[] tokens = in.split("<br>");
            return tokens[0].split(",")[1];
        }else {
            toReturn[2] = "f";
            return in;
        }
    }

    protected void onPostExecute(String[] n){
        if(this.exception != null){
            this.exception.printStackTrace();
        } else {
            this.listener.nextClassRetrieved(n);
        }
    }
}
