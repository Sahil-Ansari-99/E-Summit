package com.example.sahilahmadansari.e_celliitm.Agenda;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sahilahmadansari.e_celliitm.Adapters.AgendaAdapter;
import com.example.sahilahmadansari.e_celliitm.LogIn.LogIn;
import com.example.sahilahmadansari.e_celliitm.MainActivity;
import com.example.sahilahmadansari.e_celliitm.People.PeopleMain;
import com.example.sahilahmadansari.e_celliitm.R;
import com.example.sahilahmadansari.e_celliitm.Sponsors.SponsorsMain;
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
    }

}
