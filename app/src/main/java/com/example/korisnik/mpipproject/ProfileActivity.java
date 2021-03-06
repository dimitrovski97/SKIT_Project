package com.example.korisnik.mpipproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.korisnik.mpipproject.Models.UserInfo;
import com.example.korisnik.mpipproject.Repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUser;
    private Button buttonLogout;

    private DatabaseReference databaseReference;
    private UserRepository userRepository;
    private EditText editTextName;
    private EditText editTextAddress;
    private Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();


        editTextAddress=(EditText)findViewById(R.id.user_addres);
        editTextName=(EditText)findViewById(R.id.user_name);
        buttonSave=(Button) findViewById(R.id.btn_save);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        userRepository = new UserRepository();

        buttonSave.setOnClickListener(this);

    }
    public void saveUserInfo(String testName, String testAddress){
        String name;
        String address;
        if(testName == "" && testAddress == ""){
            name= editTextName.getText().toString().trim();
            address=editTextAddress.getText().toString().trim();
        }
        else{
            name = testName;
            address = testAddress;
        }
        FirebaseUser user=firebaseAuth.getCurrentUser();
        String userID;
        if(user != null) {
            userID = user.getUid();
            if(TextUtils.isEmpty(userID)){
                Toast.makeText(this, "User without id", Toast.LENGTH_SHORT).show();
                throw new NullPointerException();
            }
        }
        else{
            throw new NullPointerException();
        }

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            throw new NullPointerException();
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show();
            throw new NullPointerException();
        }
        if(!address.matches(".*\\d.*"))
        {
            Toast.makeText(this, "Please specify number in the address", Toast.LENGTH_SHORT).show();
            throw new IllegalArgumentException();
        }
        //Adding new users to repository
        UserInfo userInfo = new UserInfo(userID, name, address);
        userRepository.insert(userInfo);

        Toast.makeText(this, "Information saved...", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {

        if(view==buttonSave)
        {
            saveUserInfo("","");
        }
    }
}