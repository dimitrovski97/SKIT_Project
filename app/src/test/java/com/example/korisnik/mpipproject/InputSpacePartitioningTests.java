package com.example.korisnik.mpipproject;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InputSpacePartitioningTests {
    HomelessRepository homelessRepository = new HomelessRepository("Testing");
    Homeless homeless1 = new Homeless("Dimitar","Dimitrovski", 23);
    Homeless homeless2 = new Homeless("Martin","Dimitrovski", 19);
    Homeless homeless3 = new Homeless("Dimitar","Martinovski", 43);
    Homeless homeless4 = new Homeless("Viktorija","Lapevska", 38);
    Homeless homeless6 = new Homeless();
    List<Homeless> list;

    @Before
    public void init(){
        list = new ArrayList<>(Arrays.asList(homeless1,homeless2,homeless3,homeless4));
    }

    @Test
    public void InterfaceBasedTest1(){ //list is not null, list is not empty and object is not null
        assertEquals(true,homelessRepository.findHomelessInTheList(list, homeless1));
    }

    @Test
    public void InterfaceBasedTest2(){ //list is not null, list is not empty and object is null
        assertEquals(false, homelessRepository.findHomelessInTheList(list, null));
    }

    @Test
    public void InterfaceBasedTest3(){ //list is empty and object is not null
        List<Homeless> list1 = new ArrayList<Homeless>();
        assertEquals(false, homelessRepository.findHomelessInTheList(list1, homeless1));
    }

    @Test
    public void InterfaceBasedTest4(){ //list is not empty and object is null
        assertEquals(false, homelessRepository.findHomelessInTheList(list, null));
    }

    @Test
    public void FunctionalityBasedTest1(){ //object not exists in the list
        assertEquals(false, homelessRepository.findHomelessInTheList(list, homeless3));
    }
    @Test
    public void FunctionalityBasedTest2(){ //object exists in the list and it's not first
        assertEquals(true, homelessRepository.findHomelessInTheList(list, homeless2));
    }
    @Test
    public void FunctionalityBasedTest3(){ //object exists in the list and it's first
        assertEquals(true, homelessRepository.findHomelessInTheList(list, homeless1));
    }
    @Test
    public void FunctionalityBasedTest4(){ //object exists in the list and it's not first
        list.add(homeless2);
        assertEquals(true, homelessRepository.findHomelessInTheList(list, homeless2));
    }
    @Test
    public void FunctionalityBasedTest5() { //object exists in the list and it's not first
        list.add(homeless1);
        assertEquals(true, homelessRepository.findHomelessInTheList(list, homeless1));
    }
}
