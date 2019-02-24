package com.example.sahilahmadansari.e_celliitm.Agenda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sahilahmadansari.e_celliitm.Adapters.AgendaDayAdapter;
import com.example.sahilahmadansari.e_celliitm.Adapters.CustomItemDecorator;
import com.example.sahilahmadansari.e_celliitm.Model.AgendaModel;
import com.example.sahilahmadansari.e_celliitm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day3Fragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<AgendaModel> itemList;
    AgendaDayAdapter adapter;
    ProgressBar progressBar;

    public Day3Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.agenda_day1, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.agenda_d1_recyclerview);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CustomItemDecorator(getActivity(), DividerItemDecoration.VERTICAL, 36));

        progressBar=(ProgressBar)v.findViewById(R.id.agenda_d1_progress_bar);

        itemList=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Agenda").child("Day 3");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    HashMap<String, String> hashMap=new HashMap<>();

                    for(DataSnapshot postSnapShot : ds.getChildren()){
                        hashMap.put(postSnapShot.getKey(), postSnapShot.getValue(String.class));
                    }

                    itemList.add(new AgendaModel(hashMap.get("title"), hashMap.get("time"), hashMap.get("duration"), hashMap.get("venue")));
                }

                adapter=new AgendaDayAdapter(getActivity(), itemList);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }
}
