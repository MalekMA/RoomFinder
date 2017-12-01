package com.example.a100541476.roomfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 100541476 on 11/30/2017.
 */

public class RoomScheduleActivity extends AppCompatActivity implements RetrieveRoomScheduleListener {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_schedule);
    }

    public void search(View view){
        EditText txtIn = (EditText) findViewById(R.id.inDay2);
        String day = txtIn.getText().toString();

        txtIn = (EditText) findViewById(R.id.inRoomName);
        String room = txtIn.getText().toString();

        new RetrieveRoomSchedule(RoomScheduleActivity.this).execute(day, room);
    }

    public void roomScheduleRetrieved(String sched){
        String[] tokens = sched.split("<br>");

        for(int i = 0; i < tokens.length; i++){
            String[] row = tokens[i].split(",");
            if(Double.parseDouble(row[1]) < 13){
                row[1] = row[1] + " AM";
            }else {
                row[1] = Double.toString(Double.parseDouble(row[1])-12) + " PM";
            }
            if(Double.parseDouble(row[2]) < 13){
                row[2] = row[2] + " AM";
            }else {
                row[2] = Double.toString(Double.parseDouble(row[2])-12) + " PM";
            }
            tokens[i] = row[1] + " - " + row[2];
        }
        String toDisplay = "";
        for(String s : tokens){
            toDisplay = toDisplay + "\n\n" + s;
        }

        TextView textView = (TextView) findViewById(R.id.schedule);
        textView.setText(toDisplay);

    }
}
