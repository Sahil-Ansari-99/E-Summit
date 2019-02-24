package com.example.sahilahmadansari.e_celliitm;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sahilahmadansari.e_celliitm.Adapters.AnnouncementsAdapter;
import com.example.sahilahmadansari.e_celliitm.Adapters.CustomItemDecorator;
import com.example.sahilahmadansari.e_celliitm.Agenda.AgendaMain;
import com.example.sahilahmadansari.e_celliitm.LogIn.LogIn;
import com.example.sahilahmadansari.e_celliitm.Model.AnnounceModel;
import com.example.sahilahmadansari.e_celliitm.People.PeopleMain;
import com.example.sahilahmadansari.e_celliitm.Sponsors.SponsorsMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private List<AnnounceModel> itemList;
    private AnnouncementsAdapter adapter;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public ProgressBar progressBar;
    Button exploreButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.home_drawer_layout);

        toolbar=(Toolbar)findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar=(ProgressBar)findViewById(R.id.home_progress_bar);

//        actionBarDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
//
//        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
//
//        actionBarDrawerToggle.syncState();

        itemList=new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.home_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
//        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CustomItemDecorator(this, DividerItemDecoration.VERTICAL, 36));

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
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
                progressBar.setVisibility(View.GONE);
                Log.e("Test", itemList.get(0).getTitle());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        NavigationView mNavView=(NavigationView)findViewById(R.id.home_nav_view);

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_aboutus){
                    menuItem.setChecked(true);
                }

                switch (menuItem.getItemId()){
                    case R.id.nav_aboutus:{
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                    case R.id.nav_events:{
                        startActivity(new Intent(getApplicationContext(), AgendaMain.class));
                        return true;
                    }
                    case R.id.nav_speakers:{
                        return true;
                    }
                    case R.id.nav_sponsors:{
                        startActivity(new Intent(getApplicationContext(), SponsorsMain.class));
                        return true;
                    }
                    case R.id.nav_faq:{
                        return true;
                    }
                    case R.id.nav_teams:{
                        return true;
                    }
                    case R.id.nav_logout:{
                        firebaseAuth.signOut();
                        Toast.makeText(getApplicationContext(), "Please Log In!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), LogIn.class));
                        return true;
                    }
                    case R.id.nav_fb:{
                        boolean res=isFbInstalled(getApplicationContext().getPackageManager());
                        String fbPageUrl="https://www.facebook.com/ECELLIITM";
                        if(res){
                            Uri uri=Uri.parse("fb://facewebmodal/f?href="+fbPageUrl);
                            Intent fbIntent=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(fbIntent);
                        }else{
                            Intent intent=new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.facebook.com/ECELLIITM"));
                            startActivity(intent);
                        }
                        return true;
                    }
                    case R.id.nav_twitter:{
                        boolean res=isTwitterInstalled(getApplicationContext().getPackageManager());
                        if(res){
                            Uri uri=Uri.parse("twitter://user?user_id=880364554240184320");
                            Intent twitterIntent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(twitterIntent);
                        }else{
                            Intent intent=new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://twitter.com/ecelliitm"));
                            startActivity(intent);
                        }
                        return true;
                    }
                    case R.id.nav_insta:{
                        boolean res=isInstaInstalled(getApplicationContext().getPackageManager());
                        String instaUrl="https://www.instagram.com/ecell_iitm/";
                        if(res){
                            instaUrl = instaUrl.substring(0, instaUrl.length()-1);
                            String instaUsername = instaUrl.substring(instaUrl.lastIndexOf("/")+1);
                            Intent instaIntent=new Intent(Intent.ACTION_VIEW);
                            instaIntent.setPackage("com.instagram.android");
                            instaIntent.setData(Uri.parse("http://instagram.com/_u/" + instaUsername));
                            startActivity(instaIntent);
                        }else{
                            Intent intent=new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(instaUrl));
                            startActivity(intent);
                        }
                        return true;
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

        homeBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_home :
                        break;

                    case R.id.bottom_nav_agenda :
                        startActivity(new Intent(getApplicationContext(), AgendaMain.class));
                        break;

                    case R.id.bottom_nav_people :
                        startActivity(new Intent(getApplicationContext(), PeopleMain.class));
                        break;
                }
                return true;
            }
        });
    }

    public boolean isFbInstalled(PackageManager pm){
        try{
            ApplicationInfo fbInfo=pm.getApplicationInfo("com.facebook.katana", 0);
            return fbInfo.enabled;
        }catch (PackageManager.NameNotFoundException e){
            return false;
        }
    }

    public boolean isTwitterInstalled(PackageManager pm){
        try{
            ApplicationInfo twitterInfo=pm.getApplicationInfo("com.twitter.android", 0);
            return twitterInfo.enabled;
        }catch (PackageManager.NameNotFoundException e){
            return false;
        }
    }

    public boolean isInstaInstalled(PackageManager pm){
        try{
            ApplicationInfo instaInfo=pm.getApplicationInfo("com.instagram.android", 0);
            return instaInfo.enabled;
        }catch (PackageManager.NameNotFoundException e){
            return  false;
        }
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
