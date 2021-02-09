package com.example.korisnik.mpipproject;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class GraphCoverageTest {
    ProfileActivity profileActivity = new ProfileActivity();

    @Test
    public void positiveScenario(){
        profileActivity.saveUserInfo("Viktorija","Marksova 26");
    }

    @Test(expected = NullPointerException.class)
    public void nameIsEmpty(){
        profileActivity.saveUserInfo("", "Marksova 26");
    }
    @Test(expected = NullPointerException.class)
    public void addressIsEmpty(){
        profileActivity.saveUserInfo("Viktorija", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addressNotMatchNumber(){
       profileActivity.saveUserInfo("Viktorija", "Marksova");
    }
}
