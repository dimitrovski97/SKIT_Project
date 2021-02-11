package com.example.korisnik.mpipproject.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomelessRepository {
    private DatabaseReference databaseReference;
    private List<Homeless> homelessList;
    private Context mContext;

    public HomelessRepository() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        homelessList = new ArrayList<>();
    }
    public HomelessRepository(String forTesting) {
    }

    public void readData(){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Homeless homeless = postSnapshot.getValue(Homeless.class);
                            Log.e("Get Data", homeless.getName());
                            homelessList.add(homeless);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("The read failed: " ,databaseError.getMessage());
                    }
                };
                databaseReference.child("Homeless").addValueEventListener(valueEventListener);
                return null;
            }
        }.execute();
    }
    public List<Homeless> getHomelessList() {
        readData();
        return homelessList;
    }
    public void setHomelessList(List<Homeless> list){
        this.homelessList = list;
        List<Homeless> newList = getHomelessList();
        System.out.println( newList.toString());
    }
    public boolean findHomelessInTheList(List<Homeless> list, Homeless homeless){
        if(homeless == null){
            throw new NullPointerException();
        }
        for(Homeless item:list){
            if(item == homeless){
                return true;
            }
        }
        return false;
    }

    public boolean findHungryChildInTheList(List<Homeless> list, Homeless homeless){
        for(Homeless item:list){
            if(item == homeless && (item.getName() != null) && (homeless.getAge() < 18 || homeless.getNeeds() == "medicals"))
                return true;
        }
        return false;
    }

    public void insert(final Homeless homeless){
        if(findHomelessInTheList(homelessList,homeless)){
            return;
        }
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                databaseReference.child("Homeless").push().setValue(homeless);
                return null;
            }
        }.execute();
    }
}
