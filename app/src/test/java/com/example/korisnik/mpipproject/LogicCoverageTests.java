package com.example.korisnik.mpipproject;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LogicCoverageTests {
    HomelessRepository homelessRepository = new HomelessRepository("Testing");
    Homeless homeless1 = new Homeless("Dimitar","Dimitrovski", 23, "medicals");
    Homeless homeless2 = new Homeless("Martin","Dimitrovski", 17, "medicals");
    Homeless homeless3 = new Homeless("Dimitar","Martinovski", 17, "food");
    Homeless homeless4 = new Homeless("Viktorija","Lapevska", 16, "medicals");
    Homeless homeless5 = new Homeless("Marko", "Markovski", 16, "clothes");
    Homeless homeless6 = new Homeless("Leo", "Markovski", 18, "medicals");
    List<Homeless> list;

    @Before
    public void init(){
        list = new ArrayList<>(Arrays.asList(homeless1,homeless2,homeless3));
    }

    @Test //TTT
    public void ChildInListWithMedicalsNeed(){
        assertTrue(homelessRepository.findHungryChildInTheList(list, homeless2));
    }

    @Test //TTF
    public void ChildInListWithNoMedicalsNeed(){
        assertTrue(homelessRepository.findHungryChildInTheList(list, homeless3));
    }

    @Test //TFT
    public void AduldInListWithMedicalsNeed(){
        assertTrue(homelessRepository.findHungryChildInTheList(list, homeless1));
    }

    @Test //FTT
    public void ChildWithMedicalNeedsNotInList(){
        assertFalse(homelessRepository.findHungryChildInTheList(list, homeless4));
    }
    @Test //FTF
    public void ChildWithNoMedicalNeedsNotInList(){
        assertFalse(homelessRepository.findHungryChildInTheList(list, homeless5));
    }
    @Test //FFT
    public void AdultWithMedicalNeedsNotInList(){
        assertFalse(homelessRepository.findHungryChildInTheList(list, homeless6));
    }
}
