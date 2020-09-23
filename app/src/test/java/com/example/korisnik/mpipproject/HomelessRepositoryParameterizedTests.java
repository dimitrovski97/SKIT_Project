package com.example.korisnik.mpipproject;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.testng.annotations.AfterTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class HomelessRepositoryParameterizedTests {
    HomelessRepository homelessRepository = new HomelessRepository("Testing");
    Homeless homeless1 = new Homeless("Dimitar","Dimitrovski", 23);
    Homeless homeless2 = new Homeless("Martin","Dimitrovski", 19);
    Homeless homeless3 = new Homeless("Dimitar","Martinovski", 43);
    Homeless homeless4 = new Homeless("Viktorija","Lapevska", 38);
    Homeless homeless5 = new Homeless("BezIme","BezPrezime",12);
    Homeless homeless6 = new Homeless();
    List<Homeless> list;

    @Parameterized.Parameters
    public Collection<Object[]> testCases(){
        return Arrays.asList(new Object[][]{
                {homeless1, true},
                {homeless2, true},
                {homeless3, true},
                {homeless4, true},
                {homeless5, false},
                {homeless6, false}
        });
    }
    @Before
    public void init(){
        list = new ArrayList<>(Arrays.asList(homeless1,homeless2,homeless3,homeless4));
    }

   /* @ParameterizedTest
    public void testPrimeNumberChecker() {
        System.out.println("Parameterized Number is : " + inputNumber);
        assertEquals(expectedResult,
                primeNumberChecker.validate(inputNumber));
    }*/

}
