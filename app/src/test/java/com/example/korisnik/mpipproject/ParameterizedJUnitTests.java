package com.example.korisnik.mpipproject;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.InvalidParameterException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class ParameterizedJUnitTests {
    HomelessRepository homelessRepository = new HomelessRepository("Testing");
    public static Homeless homeless1 = new Homeless("Dimitar","Dimitrovski", 23);
    public static Homeless homeless2 = new Homeless("Martin","Dimitrovski", 19);
    public static Homeless homeless3 = new Homeless("Dimitar","Martinovski", 43);
    public static Homeless homeless4 = new Homeless("Viktorija","Lapevska", 38);
    public static Homeless homeless5 = new Homeless("BezIme","BezPrezime",12);
    public static Homeless homeless6 = new Homeless();
    public static Homeless homeless7 = new Homeless("Viktorija","Lapevskaa", 15);
    List<Homeless> list = new ArrayList<>(Arrays.asList(homeless1,homeless2,homeless3,homeless4));
    List<Homeless> list2 = new ArrayList<>(Arrays.asList(homeless1,homeless2,homeless3,homeless4, homeless5, homeless6, homeless7));

    public static Collection<Object[]> testCases(){
        return Arrays.asList(new Object[][]{
                {homeless1, true},
                {homeless2, true},
                {homeless3, true},
                {homeless4, true},
                {homeless5, false},
                {homeless6, false}
        });
    }

    public static Collection<Object[]> testCases2(){
        return Arrays.asList(new Object[][]{
                {homeless1, true},
                {homeless2, true},
                {homeless3, true},
                {homeless7, false},
                {homeless5, false},
                {homeless6, true}
        });
    }

    public static Collection<Object[]> testCases3(){
        return Arrays.asList(new Object[][]{
                {3, -3, 5, 7, IllegalArgumentException.class},
                {-1, 4, 5, 7, IllegalArgumentException.class},
                {3, 4, -3, 7, IllegalArgumentException.class},
                {3, 4, -3, -11, IllegalArgumentException.class},
                {5, 6, 5, 6 , InvalidParameterException.class},
                {0, 1, 0, 1, InvalidParameterException.class},
                {-1, -1, -1, -1, IllegalArgumentException.class}
        });
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void findHomelessInList(Homeless homeless, boolean expectedResult) {
        System.out.println("Test case: " + homeless.getName() + " " + homeless.getSurname());
        assertEquals(expectedResult,
                homelessRepository.findHomelessInTheList(list, homeless));
    }

    @ParameterizedTest
    @MethodSource("testCases2")
    public void findHungryChildHomelessInList(Homeless homeless, boolean expectedResult) {
        System.out.println("Test case - child: " + homeless.getName() + " " + homeless.getSurname());
        assertNotEquals(expectedResult,
                homelessRepository.findHungryChildInTheList(list2, homeless));
    }

    @ParameterizedTest
    @MethodSource("testCases3")
    public void testDistanceExceptions(int lat1, double lng1, double lat2, double lng2, Class expectedResult) {
        System.out.println("Test case:" + lat1 + " " + lng1 + " " + lat2 + " " + lng2);
        assertThrows(expectedResult, () -> {
                HelpFragment.distance(lat1, lng1, lat2, lng2);
        });
    }

}
