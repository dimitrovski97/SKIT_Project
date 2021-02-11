package com.example.korisnik.mpipproject;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;

import org.junit.Before;
import org.junit.Test;

import java.io.InvalidObjectException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class SimpleJUnitTests {
    HomelessRepository homelessRepository = new HomelessRepository("Testing");
    Homeless homeless1 = new Homeless("Dimitar","Dimitrovski", 23);
    Homeless homeless2 = new Homeless("Martin","Dimitrovski", 19);
    Homeless homeless3 = new Homeless("Dimitar","Martinovski", 43);
    Homeless homeless4 = new Homeless("Viktorija","Lapevska", 38);
    Homeless homeless5 = new Homeless("BezIme","BezPrezime",12);
    Homeless homeless6 = new Homeless();
    Homeless homeless7 = null;
    List<Homeless> list;
    public Collection<Object[]> testCases(){
        return Arrays.asList(new Object[][]{
                {homeless1, true},
                {homeless2, true},
                {homeless5, false},
                {homeless6, false}
        });
    }
    @Before
    public void init(){
        list = new ArrayList<>(Arrays.asList(homeless1,homeless2,homeless3,homeless4));
    }

    @Test
    public void findFirstHomeless(){
        assertTrue(homelessRepository.findHomelessInTheList(list, homeless1));
    }

    @Test
    public void findSecondHomeless(){
        assertEquals(true,homelessRepository.findHomelessInTheList(list, homeless2));
    }

    @Test
    public void homelessDoesntExist(){
        assertEquals(false,homelessRepository.findHomelessInTheList(list, homeless6));
    }

    @Test
    public void homelessNull(){
        assertThrows(NullPointerException.class, () -> homelessRepository.findHomelessInTheList(list, homeless7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidDistanceInput() {
        HelpFragment.distance(-3, 2,3,5);
    }

    @Test(expected = InvalidParameterException.class)
    public void samePointEntered() {
        HelpFragment.distance(3, 3,3,3);
    }
}
