package com.example.realtimedatabasekotlin;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;

public class HomeActivirty extends AppCompatActivity {

    private Button btSubmitButton,btReadData;
    private EditText mInpurtAge, mInpurtName;
    private TextView mOutputText;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ChildEventListener mChildeListner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activirty);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");

        btSubmitButton = findViewById(R.id.btSubmitButton);
        btReadData = findViewById(R.id.btReadData);

        mOutputText = findViewById(R.id.mOutputText);
        mInpurtName = findViewById(R.id.mInpurtName);
        mInpurtAge = findViewById(R.id.mInpurtAge);


        btSubmitButton.setOnClickListener(this::runcode);
        btReadData.setOnClickListener(this::readCode);

        mChildeListner = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //Person person = snapshot.getValue(Person.class);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mRef.addChildEventListener(mChildeListner);
    }


    private void readCode(View view){
        }

    // insert  multiple data on firebase
    private void runcode(View view) {
        String name = mInpurtName.getText().toString();
        int age = Integer.parseInt(mInpurtAge.getText().toString());

        // Create new object class of Person and add a value on model class
        Person person = new Person(name,age);
        String key = mRef.push().getKey();
        mRef.child(key).setValue(person);
        Toast.makeText(this,"Data is added",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRef.removeEventListener(mChildeListner);
    }

}