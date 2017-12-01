package com.example.a100541476.roomfinder;

/**
 * Created by 100541476 on 11/30/2017.
 */

public class Room {
    private String roomName;
    private double latitude, longitude, wifi, sound, seat, overall, totalRated;

    public Room() {}

    public Room(String name, double lat, double lon, double w, double so, double se, double o, double t){
        this.roomName = name;
        this.latitude = lat;
        this.longitude = lon;
        this.wifi = w;
        this.sound = so;
        this.seat = se;
        this.overall = o;
        this.totalRated = t;
    }

    public String getRoomName() {return this.roomName;}

    public double getLatitude() {return this.latitude;}

    public double getLongitude() {return this.longitude;}

    public double getWifi() {return this.wifi;}

    public double getSound() {return this.sound;}

    public double getSeat() {return this.seat;}

    public double getOverall() {return this.overall;}

    public double getTotalRated() {return this.totalRated;}
}
