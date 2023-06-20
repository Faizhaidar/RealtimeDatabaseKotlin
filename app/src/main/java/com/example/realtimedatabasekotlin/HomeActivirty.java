package com.example.realtimedatabasekotlin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    private Button mSubmitButton,mReadData;
    private EditText mInpurtText, mInpurtNum;
    private TextView mOutputText;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activirty);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();



        mSubmitButton = findViewById(R.id.mSubmitButton);
        mOutputText = findViewById(R.id.mOutputText);
        mInpurtText = findViewById(R.id.mInpurtText);
        mSubmitButton.setOnClickListener(this::runcode);

        //this.mReadData.setOnClickListener(this::redData);

    }
    // insert data on firebase
    private void runcode(View view) {
        String data = mInpurtText.getText().toString();
        mRef.setValue(data);
        Toast.makeText(this,"Data is added",Toast.LENGTH_SHORT).show();
    }

}