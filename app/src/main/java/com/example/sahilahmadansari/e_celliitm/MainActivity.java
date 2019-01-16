package com.example.sahilahmadansari.e_celliitm;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.sahilahmadansari.e_celliitm.Adapters.AnnouncementsAdapter;
import com.example.sahilahmadansari.e_celliitm.Adapters.CustomItemDecorator;
import com.example.sahilahmadansari.e_celliitm.Model.AnnounceModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private BottomNavigationView homeBottomNav;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private List<AnnounceModel> itemList;
    private AnnouncementsAdapter adapter;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    Button exploreButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.home_drawer_layout);

        toolbar=(Toolbar)findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        itemList=new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.home_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CustomItemDecorator(this, DividerItemDecoration.VERTICAL, 36));

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Announcements");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    HashMap<String, String> hashMap = new HashMap<>();

                    for (DataSnapshot postSnapShot : ds.getChildren()) {
                        hashMap.put(postSnapShot.getKey(), postSnapShot.getValue(String.class));
                    }

                    itemList.add(new AnnounceModel(hashMap.get("title"), hashMap.get("description")));
                }

                adapter=new AnnouncementsAdapter(getApplicationContext(), itemList);
                Log.e("Test", itemList.get(0).getTitle());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        NavigationView mNavView=(NavigationView)findViewById(R.id.home_nav_view);

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_events:{
//                        startActivity(new Intent(getApplicationContext(),EventsMain.class));
                    }
                    case R.id.nav_speakers:{

                    }
                    case R.id.nav_esummit:{

                    }
                    case R.id.nav_eupdates:{
//                        startActivity(new Intent(getApplicationContext(),EUpdatesMain.class));
                        return true;
                    }
                    case R.id.nav_faq:{

                    }
                    case R.id.nav_teams:{

                    }
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

//        exploreButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDrawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

        homeBottomNav=(BottomNavigationView)findViewById(R.id.home_bottom_nav);
    }


    public void setUpAdapter(){
        adapter=new AnnouncementsAdapter(this, itemList);
        Log.e("Test", itemList.get(0).getTitle());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void loadData(){
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Announcements");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AnnounceModel model=dataSnapshot.getValue(AnnounceModel.class);
                Log.e("Test", model.getTitle());
                itemList.add(model);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
