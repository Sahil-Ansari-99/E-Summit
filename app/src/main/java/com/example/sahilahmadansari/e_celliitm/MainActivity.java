package com.example.sahilahmadansari.e_celliitm;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.sahilahmadansari.e_celliitm.EUpdates.EUpdatesMain;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    Button exploreButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//

//        exploreButton=(Button)findViewById(R.id.explore_button);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView mNavView=(NavigationView)findViewById(R.id.nav_view);

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
                        startActivity(new Intent(getApplicationContext(),EUpdatesMain.class));
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
