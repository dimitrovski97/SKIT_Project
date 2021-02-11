package com.example.korisnik.mpipproject;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.InvalidObjectException;
import java.security.InvalidParameterException;

public class MutationTest {
    AndroidAppFunctions androidAppFunctions = new AndroidAppFunctions();
    @Test
    public void checkCalculatedDistance() throws InvalidObjectException {
        double lat1 = 40.843424;
        double lng1 = 21.871525;
        double lat2 = 42.002764;
        double lng2 = 21.395959;
        assertEquals(androidAppFunctions.distance(lat1, lng1, lat2, lng2), 135, 1);
    }
    @Test
    public void checkCalculatedDistance2() throws InvalidObjectException {
        double lat1 = 40.843424;
        double lng1 = 21.871525;
        double lat2 = 42.002764;
        double lng2 = 21.395959;
        assertNotEquals(androidAppFunctions.distance(lat1, lng1, lat2, lng2), 135, 0);
    }
    @Test
    public void checkOnlyFirstLowerThanZero(){
        double lat1 = -21.434545;
        double lng1 = 21.871525;
        double lat2 = 42.002764;
        double lng2 = 21.395959;
        assertThrows(IllegalArgumentException.class, () -> {
            androidAppFunctions.distance(lat1, lng1, lat2, lng2);
        });
    }
    @Test
    public void checkOnlySecondLowerThanZero(){
        double lat1 = 21.434545;
        double lng1 = -21.871525;
        double lat2 = 42.002764;
        double lng2 = 21.395959;
        assertThrows(IllegalArgumentException.class, () -> {
            androidAppFunctions.distance(lat1, lng1, lat2, lng2);
        });
    }
    @Test
    public void checkOnlyThirdLowerThanZero(){
        double lat1 = 21.434545;
        double lng1 = 21.871525;
        double lat2 = -42.002764;
        double lng2 = 21.395959;
        assertThrows(IllegalArgumentException.class, () -> {
            androidAppFunctions.distance(lat1, lng1, lat2, lng2);
        });
    }
    @Test
    public void checkOnlyForthLowerThanZero(){
        double lat1 = 21.434545;
        double lng1 = 21.871525;
        double lat2 = 42.002764;
        double lng2 = -21.395959;
        assertThrows(IllegalArgumentException.class, () -> {
            androidAppFunctions.distance(lat1, lng1, lat2, lng2);
        });
    }
    @Test
    public void checkAllLowerThanZero(){
        double lat1 = -21.434545;
        double lng1 = -21.871525;
        double lat2 = -42.002764;
        double lng2 = -21.395959;
        assertThrows(IllegalArgumentException.class, () -> {
            androidAppFunctions.distance(lat1, lng1, lat2, lng2);
        });
    }
    @Test
    public void checkDistance(){
        double lat1 = 26.745610;
        double lng1 = 1.121354;
        double lat2 = 41.244772;
        double lng2 = 88.015272;
        assertEquals(7844, androidAppFunctions.distance(lat1, lng1, lat2, lng2), 0.5);
    }

    @Test
    public void checkSamePoint(){
        double lat1 = 0;
        double lng1 = 0;
        double lat2 = 0;
        double lng2 = 0;
        assertThrows(InvalidParameterException.class, () -> {
            androidAppFunctions.distance(lat1, lng1, lat2, lng2);
        });
    }

    @Test
    public void checkSamePoint2(){
        double lat1 = 26.745610;
        double lng1 = 41.244772;
        double lat2 = 26.745610;
        double lng2 = 41.244772;
        assertThrows(InvalidParameterException.class, () -> {
            androidAppFunctions.distance(lat1, lng1, lat2, lng2);
        });
    }

}
