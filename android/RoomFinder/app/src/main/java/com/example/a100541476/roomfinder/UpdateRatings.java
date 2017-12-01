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

public class UpdateRatings extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... params){
        String room = params[0];
        String wifi = params[1];
        String sound = params[2];
        String seat = params[3];
        String overall = params[4];
        String total = params[5];
        String review = "";

        String[] tokens = params[6].split(" ");
        int i = 0;
        for(String s : tokens){
            if(i==0){
                review = s;
                i++;
            }else {
                review = review + "%20" + s;
            }
        }

        String ratingURL = "https://roomfinder-187219.appspot.com/updaterating?key=dsproj&room="+room+"&wifi="+wifi+"&sound="+sound+"&seat="+seat+"&overall="+overall+"&total="+total;
        String reviewURL = "https://roomfinder-187219.appspot.com/enterreview?key=dsproj&room="+room+"&review="+review;
        Log.i("---URL", ratingURL);
        Log.i("---URL", reviewURL);

        try{
            updateRatings(ratingURL);
            updateReview(reviewURL);
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return "Review entered";
    }

    private void updateRatings(String url) throws IOException{
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        } finally {
            is.close();
        }
    }

    private void updateReview(String url) throws IOException{
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        } finally {
            is.close();
        }
    }
}
