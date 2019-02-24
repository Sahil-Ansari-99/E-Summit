package com.example.sahilahmadansari.e_celliitm.People;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.sahilahmadansari.e_celliitm.Messaging.MessagingMain;
import com.example.sahilahmadansari.e_celliitm.Model.User;
import com.example.sahilahmadansari.e_celliitm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AttendeeActivity extends AppCompatActivity {
    TextView userName, userDesig, userCompany;
    Button sendButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String userId, currId;
    Toolbar toolbar;
    String name, designation, company;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        toolbar=(Toolbar)findViewById(R.id.user_toolbar);
        toolbar.setTitle("E-Summit");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        userName=(TextView)findViewById(R.id.user_username);
        userDesig=(TextView)findViewById(R.id.user_designation);
        userCompany=(TextView)findViewById(R.id.user_company);
        sendButton=(Button)findViewById(R.id.user_send_message);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        currId=firebaseUser.getUid();

        firebaseDatabase=FirebaseDatabase.getInstance();

        Intent intent=getIntent();
        userId=intent.getStringExtra("id");

        displayData();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userId.equals(currId)) {
                    startMessaging();
                }else{
                    Toast.makeText(getApplicationContext(), "Can't Text Yourself", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    public void displayData(){
        databaseReference=firebaseDatabase.getReference("Users").child(userId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    name=dataSnapshot.child("username").getValue(String.class);
                    designation =dataSnapshot.child("designation").getValue(String.class);
                    company=dataSnapshot.child("company").getValue(String.class);

                    userName.setText(name);
                    userDesig.setText(designation);
                    userCompany.setText(company);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void startMessaging(){
        Intent messageIntent=new Intent(getApplicationContext(), MessagingMain.class);
        messageIntent.putExtra("user", name);
        messageIntent.putExtra("uid", userId);
        startActivity(messageIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), PeopleMain.class);
        startActivity(intent);
        finish();
    }
}
