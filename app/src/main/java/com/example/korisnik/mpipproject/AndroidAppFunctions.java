package com.example.korisnik.mpipproject;

import java.security.InvalidParameterException;

public class AndroidAppFunctions {
    public static float distance(double lat1, double lng1, double lat2, double lng2) throws IllegalArgumentException {
        double earthRadius = 6371000; //meters
        if (lat1 < 0 || lng1 < 0 || lat2 < 0 || lng2 < 0){
            throw new IllegalArgumentException("Invalid input exception");
        }
        if (lat1 - lat2 == 0 && lng1 - lng2 == 0){
            throw new InvalidParameterException("You have entered same point");
        }
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return dist/1000;
    }
}
