package com.example.building;

import android.location.Location;

/**
 * Created by chacha on 2019-07-04.
 */

public class ARPoint {
    Location location;
    String name, opertime, adress;
    double rating;

    public ARPoint(String name, double lat, double lon, double rating, String opertime, String adress) {
        this.name = name;
        this.opertime = opertime;
        this.adress = adress;
        this.rating = rating;
        location = new Location("ARPoint");
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setAltitude(rating);
    }

    public Location getLocation() {
        return location;
    }

    public String getName() { return name; }

    public String getOpertime() { return opertime; }

    public String getAdress() { return adress; }

    public double getRating() { return rating; }
}
