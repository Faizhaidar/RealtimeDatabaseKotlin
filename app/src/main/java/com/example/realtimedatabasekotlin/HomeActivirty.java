package com.example.realtimedatabasekotlin;

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

public class HomeActivirty extends AppCompatActivity {

    private Button btSubmitButton,btReadData;
    private EditText mInpurtAge, mInpurtName;
    private TextView mOutputText;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef,mRefnew;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activirty);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");
        mRefnew = mDatabase.getReference("items");

        btSubmitButton = findViewById(R.id.btSubmitButton);
        btReadData = findViewById(R.id.btReadData);

        mOutputText = findViewById(R.id.mOutputText);
        mInpurtName = findViewById(R.id.mInpurtName);
        mInpurtAge = findViewById(R.id.mInpurtAge);


        btSubmitButton.setOnClickListener(this::runcode);
        btReadData.setOnClickListener(this::readCode);
    }
    private void readCode(View view){
        //this refrence is use a user
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String,Object> data  = (Map<String, Object>)  snapshot.getValue();

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
        });

        //this refrence is use a item
        mRefnew.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String,Object> data  = (Map<String, Object>)  snapshot.getValue();
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
        });
        }

    // insert  multiple data on firebase
    private void runcode(View view) {
        String data = mInpurtName.getText().toString();
        int age = Integer.parseInt(mInpurtAge.getText().toString());

        Map<String,Object> insertValue =  new HashMap<>();
        insertValue.put("ItemId",data);
        insertValue.put("ItemsName",age);

        String key = mRef.push().getKey();                      //first ref
        mRef.child(key).setValue(insertValue);
        Toast.makeText(this,"Data is added",Toast.LENGTH_SHORT).show();


        String Newkey = mRefnew.push().getKey();                //second ref
        mRefnew.child(Newkey).setValue(insertValue);
        Toast.makeText(this,"Data is added",Toast.LENGTH_SHORT).show();
    }
}