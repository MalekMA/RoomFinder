package com.example.a100541476.roomfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 100541476 on 11/30/2017.
 */

public class RoomsAdapter extends ArrayAdapter<Room> {
    private float[] result = new float[1];
    public RoomsAdapter(Context context, int textViewResourceID){
        super(context, textViewResourceID);
    }

    public RoomsAdapter(Context context, int resource, ArrayList<Room> rooms){
        super(context, resource, rooms);
    }

    public View getView(int position, View convertView, ViewGroup paremt){
        View v = convertView;

        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listview, null);
        }

        Room room = getItem(position);

        if (room != null){
            TextView name = (TextView) v.findViewById(R.id.roomName);
            TextView distance = (TextView) v.findViewById(R.id.distance);
            TextView rating = (TextView) v.findViewById(R.id.rating);

            if(name != null){
                name.setText(room.getRoomName());
            }

            if(distance != null){
                MainActivity.location.distanceBetween(MainActivity.latitude, MainActivity.longitutde, room.getLatitude(), room.getLongitude(), result);
                String toDisplay = "Distance away: " + Float.toString(result[0]/100) + " meters";
                distance.setText(toDisplay);
            }

            if(rating != null){
                String toDisplay = "Rating: " + Double.toString(room.getOverall());
                rating.setText(toDisplay);
            }
        }
        return v;
    }
}
