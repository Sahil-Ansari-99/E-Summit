package com.example.sahilahmadansari.e_celliitm.Agenda;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sahilahmadansari.e_celliitm.Adapters.AgendaAdapter;
import com.example.sahilahmadansari.e_celliitm.FAQ.FAQMain;
import com.example.sahilahmadansari.e_celliitm.LogIn.LogIn;
import com.example.sahilahmadansari.e_celliitm.MainActivity;
import com.example.sahilahmadansari.e_celliitm.People.PeopleMain;
import com.example.sahilahmadansari.e_celliitm.R;
import com.example.sahilahmadansari.e_celliitm.Speakers.SpeakersMain;
import com.example.sahilahmadansari.e_celliitm.Sponsors.SponsorsMain;
import com.example.sahilahmadansari.e_celliitm.StartupShowcase.StartupShowcase;
import com.google.firebase.auth.FirebaseAuth;

public class AgendaMain extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        drawerLayout=(DrawerLayout)findViewById(R.id.agenda_drawer_layout);

        firebaseAuth=FirebaseAuth.getInstance();

        toolbar=(Toolbar)findViewById(R.id.agenda_toolbar);
        toolbar.setTitle("Agenda");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//
//        actionBarDrawerToggle.syncState();

        tabLayout=(TabLayout)findViewById(R.id.agenda_tab_layout);
        viewPager=(ViewPager)findViewById(R.id.agenda_viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("Day 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Day 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Day 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        AgendaAdapter adapter=new AgendaAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.agenda_bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_home :
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        break;

                    case R.id.bottom_nav_agenda :
                        break;

                    case R.id.bottom_nav_people :
                        startActivity(new Intent(getApplicationContext(), PeopleMain.class));
                        break;
                }
                return true;
            }
        });

        NavigationView navigationView=(NavigationView)findViewById(R.id.agenda_nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_aboutus){
                    menuItem.setChecked(true);
                }

                switch (menuItem.getItemId()){
                    case R.id.nav_aboutus:{
                        drawerLayout.closeDrawers();
                        return true;
                    }
                    case R.id.nav_events:{
                        startActivity(new Intent(getApplicationContext(), AgendaMain.class));
                        return true;
                    }
                    case R.id.nav_speakers:{
                        startActivity(new Intent(getApplicationContext(), SpeakersMain.class));
                        return true;
                    }
                    case R.id.nav_sponsors:{
                        startActivity(new Intent(getApplicationContext(), SponsorsMain.class));
                        return true;
                    }
                    case R.id.nav_startup:{
                        startActivity(new Intent(getApplicationContext(), StartupShowcase.class));
                        return true;
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
