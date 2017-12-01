package com.example.a100541476.roomfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by 100541476 on 11/30/2017.
 */

public class QuickSearch extends AppCompatActivity implements QuickSearchListener{

    private static String TAG = "QuickSearch";
    private static ArrayList<Room> rooms = new ArrayList<Room>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_search);

        new RetrieveQuickSearch(QuickSearch.this).execute(MainActivity.day, Double.toString(MainActivity.time));
    }

    public void emptyRoomsRetrieved(String[] r){
        Log.d(TAG, "retrieved " + r.length);
        for(int i = 0; i < r.length; i++){
            String[] tokens = r[i].split(",");
            Room room = new Room(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]), Double.parseDouble(tokens[7]));
            rooms.add(room);
        }

        ListView listView = (ListView) findViewById(R.id.roomsListView);
        RoomsAdapter adapter = new RoomsAdapter(this, R.layout.listview, rooms);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(QuickSearch.this, RoomActivity.class);
               intent.putExtra("ROOM_NAME", rooms.get(i).getRoomName());
               intent.putExtra("ROOM_LAT", rooms.get(i).getLatitude());
               intent.putExtra("ROOM_LON", rooms.get(i).getLongitude());
               intent.putExtra("ROOM_WIFI", rooms.get(i).getWifi());
               intent.putExtra("ROOM_SOUND", rooms.get(i).getSound());
               intent.putExtra("ROOM_SEAT", rooms.get(i).getSeat());
               intent.putExtra("ROOM_OVERALL", rooms.get(i).getOverall());
               intent.putExtra("ROOM_TOTAL", rooms.get(i).getTotalRated());
               startActivity(intent);
            }
        });
    }
}
