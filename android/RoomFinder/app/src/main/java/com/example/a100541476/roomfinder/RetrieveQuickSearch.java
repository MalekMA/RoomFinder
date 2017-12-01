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

public class RetrieveQuickSearch extends AsyncTask<String, Void, String[]> {
    private static final String TAG = "RetrieveQuickSearch";
    private Exception exception = null;
    private QuickSearchListener listener;

    public RetrieveQuickSearch(QuickSearchListener listener) {this.listener = listener;}

    protected String[] doInBackground(String... params){
        String roomsIn = "";

        String setURL = "https://roomfinder-187219.appspot.com/quick?key=dsproj&day=" + params[0] + "&time=" + params[1];
        Log.d(TAG, setURL);
        try{
            roomsIn = getEmptyRooms(setURL);
        } catch (MalformedURLException e){
            Log.d(TAG, e.getMessage());
        } catch (IOException e){
            Log.d(TAG, e.getMessage());
        }
        String[] rooms = roomsIn.split("<br>");
        return rooms;
    }

    public static String getEmptyRooms(String url) throws IOException {
        String roomsIn = "";
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = "";
            while((line = br.readLine()) != null){
                roomsIn = line;
                Log.d(TAG, line + "\n");
            }
        } finally {
            is.close();
        }
        return roomsIn;
    }

    protected void onPostExecute(String[] rooms){
        if(this.exception != null){
            Log.d(TAG, this.exception.getMessage());
        } else {
            this.listener.emptyRoomsRetrieved(rooms);
        }
    }
}
