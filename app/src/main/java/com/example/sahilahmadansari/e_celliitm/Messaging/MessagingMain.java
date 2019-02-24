package com.example.sahilahmadansari.e_celliitm.Messaging;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sahilahmadansari.e_celliitm.Adapters.MessageAdapter;
import com.example.sahilahmadansari.e_celliitm.Model.Message;
import com.example.sahilahmadansari.e_celliitm.People.AttendeeActivity;
import com.example.sahilahmadansari.e_celliitm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MessagingMain extends AppCompatActivity {
    Toolbar toolbar;
    String userName, currId, userId;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference currSentRef, userReceivedRef, messageRef;
    List<Message> messageList;
    MessageAdapter adapter;
    RecyclerView recyclerView;
    EditText messageBox;
    Button sendButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Intent intent=getIntent();
        userName=intent.getStringExtra("user");
        userId=intent.getStringExtra("uid");

        toolbar=(Toolbar)findViewById(R.id.messaging_toolbar);
        toolbar.setTitle(userName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        messageBox=(EditText)findViewById(R.id.messaging_textBox);
        sendButton=(Button)findViewById(R.id.messaging_button);

        recyclerView=(RecyclerView)findViewById(R.id.messaging_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        currId=firebaseUser.getUid();

        messageList=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();

        messageRef=firebaseDatabase.getReference().child("Users").child(currId).child("messages").child("all").child(userId);
        messageRef.keepSynced(true);

        currSentRef=firebaseDatabase.getReference().child("Users").child(currId).child("messages").child("sent").child(userId);
        currSentRef.keepSynced(true);

        userReceivedRef=firebaseDatabase.getReference().child("Users").child(userId).child("messages").child("received").child(currId);
        userReceivedRef.keepSynced(true);

        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageList.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    HashMap<String, String> hashMap=new HashMap<>();

                    for(DataSnapshot ds : postSnapshot.getChildren()){
                            hashMap.put(ds.getKey(), ds.getValue(String.class));
                    }

                    messageList.add(new Message(hashMap.get("message"), hashMap.get("sender"), hashMap.get("time")));
                }

                adapter=new MessageAdapter(getApplicationContext(), messageList, currId);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(messageBox.getText().toString())) {
                    sendMessage();
                }
            }
        });
    }

    public void sendMessage(){
        String messageToSend=messageBox.getText().toString();

        Date currDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat tFormat = new SimpleDateFormat("H:m");
        String messageTime = tFormat.format(currDate);

        DatabaseReference newMessage=messageRef.push();

        HashMap<String, String> messageMap=new HashMap<>();

        messageMap.put("message", messageToSend);
        messageMap.put("sender", currId);
        messageMap.put("time", messageTime);

        newMessage.setValue(messageMap);
        messageBox.setText("");

        DatabaseReference sentMessage=currSentRef.push();
        sentMessage.setValue(messageMap);

        DatabaseReference receivedMessage=userReceivedRef.push();
        receivedMessage.setValue(messageMap);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), AttendeeActivity.class);
        intent.putExtra("id", userId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
