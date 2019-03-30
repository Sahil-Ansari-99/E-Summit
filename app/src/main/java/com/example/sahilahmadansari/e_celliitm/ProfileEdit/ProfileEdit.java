package com.example.sahilahmadansari.e_celliitm.ProfileEdit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.MainActivity;
import com.example.sahilahmadansari.e_celliitm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProfileEdit extends AppCompatActivity {
    TextView userDesignation, userCompany, userName;
    Button profileButton;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        userId=firebaseUser.getUid();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(userId)){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setContentView(R.layout.profile_edit);

        userName=(TextView)findViewById(R.id.profile_edit_username);
        userDesignation=(TextView)findViewById(R.id.profile_edit_designation);
        userCompany=(TextView)findViewById(R.id.profile_edit_company);
        profileButton=(Button)findViewById(R.id.profile_edit_button);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(userName.getText().toString())&&!TextUtils.isEmpty(userDesignation.getText().toString())&&!TextUtils.isEmpty(userCompany.getText().toString()))
                updateProfile();
            }
        });
    }

    public void updateProfile(){
        String name=userName.getText().toString();
        String designation=userName.getText().toString();
        String company=userCompany.getText().toString();

        HashMap<String, String> userMap=new HashMap<>();

        userMap.put("username", name);
        userMap.put("designation", designation);
        userMap.put("company", company);
        userMap.put("id", userId);

        DatabaseReference userRef=databaseReference.child(userId);
        userRef.setValue(userMap);
    }
}
