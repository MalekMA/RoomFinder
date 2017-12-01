package com.example.a100541476.roomfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 100541476 on 11/30/2017.
 */

public class RoomActivity extends AppCompatActivity implements NextClassListener{

    private float[] result = new float[1];

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        new RetrieveNextClass(RoomActivity.this).execute(MainActivity.day, Double.toString(MainActivity.time), getIntent().getStringExtra("ROOM_NAME"));

    }

    public void nextClassRetrieved(String[] next){

        TextView textView = (TextView) findViewById(R.id.rroomName);
        textView.setText(getIntent().getStringExtra("ROOM_NAME"));

        textView = (TextView) findViewById(R.id.overallRating);
        textView.setText(Double.toString(getIntent().getDoubleExtra("ROOM_OVERALL", 0)));

        textView = (TextView) findViewById(R.id.distanceAway);
        MainActivity.location.distanceBetween(MainActivity.latitude, MainActivity.longitutde, getIntent().getDoubleExtra("ROOM_LAT", 0), getIntent().getDoubleExtra("ROOM_LON", 0), result);
        String toDisplay = Float.toString(result[0]/100) + " meters away";
        textView.setText(toDisplay);

        if(!next[0].equals("")) {
            textView = (TextView) findViewById(R.id.nextClass);
            try {
                if(Double.parseDouble(next[0]) < 13) {
                    String time = "Next class is at " + next[0].split(".")[0] + ":" + next[0].split(".")[1] +" AM";
                    textView.setText(time);
                }else{
                    String newTime = Double.toString(Double.parseDouble(next[0]) -12);
                    String time = "Next class is at " + newTime.split(".")[0] + ":" + newTime.split(".")[1] +" PM";
                    textView.setText(time);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                if(Double.parseDouble(next[0]) < 13) {
                    String time = "Next class is at " + next[0] +" AM";
                    textView.setText(time);
                }else{
                    String newTime = Double.toString(Double.parseDouble(next[0]) -12);
                    textView.setText("Next class is at " + newTime + " PM");

                }
            }
        }else{
            textView = (TextView) findViewById(R.id.nextClass);
            textView.setText("No more classes today");
        }

        textView = (TextView) findViewById(R.id.wifiRating);
        toDisplay = "Wifi " + Double.toString(getIntent().getDoubleExtra("ROOM_WIFI", 0));
        textView.setText(toDisplay);

        textView = (TextView) findViewById(R.id.soundRating);
        toDisplay = "Quietness " + Double.toString(getIntent().getDoubleExtra("ROOM_SOUND", 0));
        textView.setText(toDisplay);

        textView = (TextView) findViewById(R.id.seatRating);
        toDisplay = "Seating " + Double.toString(getIntent().getDoubleExtra("ROOM_SEAT", 0));
        textView.setText(toDisplay);

        textView = (TextView) findViewById(R.id.reviews);
        if(next[1].equals("")){
            textView.setText("No reviews");
        }else {
            String[] tokens = next[1].split("<br>");
            String out = "";
            for(String s : tokens){
                out = out + "\n"  + s;
            }
            textView.setText(out);
        }
    }

    public void submit(View view){
        double oldWifi, oldSound, oldSeat, oldOverall, oldTotal;
        oldWifi = getIntent().getDoubleExtra("ROOM_WIFI", 0);
        oldSound = getIntent().getDoubleExtra("ROOM_SOUND", 0);
        oldSeat = getIntent().getDoubleExtra("ROOM_SEAT", 0);
        oldOverall = getIntent().getDoubleExtra("ROOM_OVERALL", 0);
        oldTotal = getIntent().getDoubleExtra("ROOM_TOTAL", 0);

        oldTotal++;

        EditText txtIn = (EditText) findViewById(R.id.inWifiRating);
        String newWifi = Double.toString((Double.parseDouble(txtIn.getText().toString()) + oldWifi) / oldTotal);

        txtIn = (EditText) findViewById(R.id.inSoundRating);
        String newSound = Double.toString((Double.parseDouble(txtIn.getText().toString()) + oldWifi) / oldTotal);

        txtIn = (EditText) findViewById(R.id.inSeatRating);
        String newSeat = Double.toString((Double.parseDouble(txtIn.getText().toString()) + oldWifi) / oldTotal);

        String newOverall = Double.toString((Double.parseDouble(newWifi) + Double.parseDouble(newSound) + Double.parseDouble(newSeat)) / 3);

        txtIn = (EditText) findViewById(R.id.inReview);
        String review = txtIn.getText().toString();

        new UpdateRatings().execute(getIntent().getStringExtra("ROOM_NAME"), newWifi, newSound, newSeat, newOverall, Double.toString(oldTotal), review);
        Toast.makeText(this, "Review entered.", Toast.LENGTH_LONG).show();

        txtIn = (EditText) findViewById(R.id.inWifiRating);
        txtIn.setText("");

        txtIn = (EditText) findViewById(R.id.inSoundRating);
        txtIn.setText("");

        txtIn = (EditText) findViewById(R.id.inSeatRating);
        txtIn.setText("");

        txtIn = (EditText) findViewById(R.id.inReview);
        txtIn.setText("");
    }

    public void home(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
