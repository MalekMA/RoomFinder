package com.example.a100541476.roomfinder;

import android.Manifest;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSIONS_REQUEST = 1;

    private final String TAG = "MainActivity";
    public static String day = "";

    public static double latitude;
    public static double longitutde;
    public static double time;

    private LocationManager locationManager;
    public static Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtils.requestPermission(this, PERMISSIONS_REQUEST, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET);
    }

    protected void onStart(){
        super.onStart();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setAltitudeRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        String recommended = locationManager.getBestProvider(criteria, true);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // request that the user install the GPS provider
            String locationConfig = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGPS = new Intent(locationConfig);
            startActivity(enableGPS);

        }
        try {
            location = locationManager.getLastKnownLocation(recommended);
            latitude = location.getLatitude();
            longitutde = location.getLongitude();

            String currentTime = Calendar.getInstance().getTime().toString();
            convertTime(currentTime);
            Log.d(TAG, latitude + " " + longitutde + " " + currentTime);
        }catch(SecurityException e){
            Log.d(TAG, e.getMessage());
        }

    }

    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, PERMISSIONS_REQUEST, grantResults)) {
                    Toast.makeText(this, "Permissions granted", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void quickSearch(View view){
        Intent intent = new Intent(this, QuickSearch.class);
        startActivity(intent);
    }

    public void advancedSearch(View view){
        Intent intent = new Intent(this, AdvancedSearch.class);
        startActivity(intent);
    }

    public void searchRoom(View view){
        Intent intent = new Intent(this, RoomScheduleActivity.class);
        startActivity(intent);
    }

    private void convertTime(String time){
        String[] tokens = time.split(" ");
        String[] t = tokens[3].split(":");
        this.time = Double.parseDouble(t[0] + "." + t[1]);

        if(tokens[0].equals("Mon")){
            day = "Monday";
        }
        else if(tokens[0].equals("Tue")){
           day = "Tuesday";
        }
        else if(tokens[0].equals("Wed")){
            day = "Wednesday";
        }
        else if(tokens[0].equals("Thu")){
            day = "Thursday";
        }
        else if(tokens[0].equals("Fri")){
            day = "Friday";
        }

        Log.d(TAG, this.time + " " + day);
    }
}
