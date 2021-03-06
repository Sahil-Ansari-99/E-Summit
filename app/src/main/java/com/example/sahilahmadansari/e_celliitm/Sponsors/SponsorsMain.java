package com.example.sahilahmadansari.e_celliitm.Sponsors;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sahilahmadansari.e_celliitm.Adapters.SponsorsAdapter;
import com.example.sahilahmadansari.e_celliitm.FAQ.FAQMain;
import com.example.sahilahmadansari.e_celliitm.LogIn.LogIn;
import com.example.sahilahmadansari.e_celliitm.MainActivity;
import com.example.sahilahmadansari.e_celliitm.Model.AnnounceModel;
import com.example.sahilahmadansari.e_celliitm.Model.Speakers;
import com.example.sahilahmadansari.e_celliitm.Model.SponsorsModel;
import com.example.sahilahmadansari.e_celliitm.R;
import com.example.sahilahmadansari.e_celliitm.Speakers.SpeakersMain;
import com.example.sahilahmadansari.e_celliitm.StartupShowcase.StartupShowcase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SponsorsMain extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    SponsorsAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    List<SponsorsModel> itemList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        toolbar=(Toolbar)findViewById(R.id.sponsors_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Our Partners");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout=(DrawerLayout)findViewById(R.id.sponsors_drawer_layout);

        recyclerView=(RecyclerView)findViewById(R.id.sponsors_recyclerView);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(SponsorsMain.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);

        itemList=new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Sponsors");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    HashMap<String, String> hashMap = new HashMap<>();

                    for (DataSnapshot postSnapShot : ds.getChildren()) {
                        hashMap.put(postSnapShot.getKey(), postSnapShot.getValue(String.class));
                    }

                    itemList.add(new SponsorsModel(hashMap.get("url"), hashMap.get("name"), hashMap.get("title")));
                }

                adapter=new SponsorsAdapter(getApplicationContext(), itemList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        NavigationView navigationView=(NavigationView)findViewById(R.id.sponsors_nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_sponsors){
                    menuItem.setChecked(true);
                }

                switch (menuItem.getItemId()){
                    case R.id.nav_aboutus:{
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    }
                    case R.id.nav_events:{
//                        startActivity(new Intent(getApplicationContext(),EventsMain.class));
                        return true;
                    }
                    case R.id.nav_speakers:{
                        startActivity(new Intent(getApplicationContext(), SpeakersMain.class));
                        return true;
                    }

                    case R.id.nav_sponsors:{
                        drawerLayout.closeDrawers();
                        return true;
                    }

                    case R.id.nav_startup:{
                        startActivity(new Intent(getApplicationContext(), StartupShowcase.class));
                    }

                    case R.id.nav_faq:{
                        startActivity(new Intent(getApplicationContext(), FAQMain.class));
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
                drawerLayout.closeDrawers();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
