package com.example.building;

import android.location.Location;

/**
 * Created by chacha on 2019-07-04.
 */

public class ARPoint {
    Location location;
    String name, opertime;

    public ARPoint(String name, double lat, double lon, double altitude, String opertime) {
        this.name = name;
        this.opertime = opertime;
        location = new Location("ARPoint");
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setAltitude(altitude);
    }

    public Location getLocation() {
        return location;
    }

    public String getName() { return name; }

    public String getOpertime() { return opertime; }
}
