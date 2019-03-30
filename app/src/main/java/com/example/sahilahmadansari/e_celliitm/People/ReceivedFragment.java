package com.example.sahilahmadansari.e_celliitm.People;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sahilahmadansari.e_celliitm.Adapters.CustomItemDecorator;
import com.example.sahilahmadansari.e_celliitm.Adapters.PeopleMessagingAdapter;
import com.example.sahilahmadansari.e_celliitm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReceivedFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    PeopleMessagingAdapter adapter;
    ProgressBar progressBar;
    String currId;
    List<String> userList;
    List<String> userKeyList;
    String returnVal;

    public ReceivedFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.people_fragment, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.people_fragment_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CustomItemDecorator(getActivity(), DividerItemDecoration.VERTICAL, 36));

        progressBar=(ProgressBar)v.findViewById(R.id.people_fragment_progress_bar);

        userList=new ArrayList<>();
        userKeyList=new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        currId=firebaseUser.getUid();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Users").child(currId).child("messages").child("received");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    String usernameKey=postSnapShot.getKey();
                    retrieveUserName(usernameKey);
                }
                retrieveUserName("COMPLETE");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

    public void retrieveUserName(final String key){
        DatabaseReference usernameRef=firebaseDatabase.getReference().child("Users").child(key).child("username");

        usernameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!key.equals("COMPLETE")) {
                    returnVal = dataSnapshot.getValue(String.class);
                    userList.add(returnVal);
                    userKeyList.add(key);
                }else{
                    progressBar.setVisibility(View.GONE);
                    adapter=new PeopleMessagingAdapter(getActivity(), userList, userKeyList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
