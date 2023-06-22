package com.example.realtimedatabasekotlin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeActivirty extends AppCompatActivity {

    private Button btSubmitButton,btReadData;
    private EditText mInpurtAge, mInpurtName;
    private TextView mOutputText;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ChildEventListener mChildeListner;

    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;
    private List<User> mDataList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activirty);

        initView();

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");

        btSubmitButton.setOnClickListener(this::runcode);
        btReadData.setOnClickListener(this::readCode);
        mDataList = new ArrayList<>();

        mChildeListner = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //Person person = snapshot.getValue(Person.class);

                User user = snapshot.getValue(User.class);
                mDataList.add(user);
                mUserAdapter.notifyDataSetChanged();
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

        //Item RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter = new UserAdapter(this,mDataList);
        mRecyclerView.setAdapter(mUserAdapter);
    }

    public void initView(){
        btSubmitButton = findViewById(R.id.btSubmitButton);
        btReadData = findViewById(R.id.btReadData);

        mOutputText = findViewById(R.id.mOutputText);
        mInpurtName = findViewById(R.id.mInpurtName);
        mInpurtAge = findViewById(R.id.mInpurtAge);
        mRecyclerView = findViewById(R.id.rv_items);
    }

    private void readCode(View view){
        }

    // insert  multiple data on firebase
    private void runcode(View view) {
        String name = mInpurtName.getText().toString();
        int age = Integer.parseInt(mInpurtAge.getText().toString());

        // Create new object class of Person and add a value on model class
        User person = new User(name,age);
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