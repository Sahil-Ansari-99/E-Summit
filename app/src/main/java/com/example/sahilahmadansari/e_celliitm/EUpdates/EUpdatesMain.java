package com.example.sahilahmadansari.e_celliitm.EUpdates;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.Toolbar;

import com.example.sahilahmadansari.e_celliitm.Adapters.EUpdatesAdapter;
import com.example.sahilahmadansari.e_celliitm.Events.EventsMain;
import com.example.sahilahmadansari.e_celliitm.MainActivity;
import com.example.sahilahmadansari.e_celliitm.Model.EUpdatesModel;
import com.example.sahilahmadansari.e_celliitm.R;

import java.util.List;

public class EUpdatesMain extends AppCompatActivity {

    DrawerLayout eupdatesDrawerLayout;
    Toolbar toolbar;
    RecyclerView eupdatesRecyclerView;
    List<EUpdatesModel> contentList;
    EUpdatesAdapter adapter;
    private Parcelable recyclerViewState;
    public ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eupdates_main);

        eupdatesDrawerLayout=(DrawerLayout)findViewById(R.id.eupdates_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("E-Updates");
        setSupportActionBar(toolbar);

        eupdatesRecyclerView=(RecyclerView)findViewById(R.id.eupdatesRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,true);
        linearLayoutManager.setStackFromEnd(true);
        eupdatesRecyclerView.setLayoutManager(linearLayoutManager);
        eupdatesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eupdatesRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseContext(),LinearLayoutManager.VERTICAL));
        eupdatesRecyclerView.setAdapter(adapter);

        progressBar=(ProgressBar)findViewById(R.id.eupdatesProgress);
        progressBar.setVisibility(View.VISIBLE);

        NavigationView eupdatesNavView=(NavigationView)findViewById(R.id.nav_view);
        eupdatesNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_aboutus:{
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        return true;
                    }
                    case R.id.nav_events:{
                        startActivity(new Intent(getApplicationContext(),EventsMain.class));
                        return true;
                    }
                    case R.id.nav_speakers:{

                    }
                    case R.id.nav_esummit:{

                    }
                    case R.id.nav_eupdates:{
                        eupdatesDrawerLayout.closeDrawers();
                        return true;
                    }
                    case R.id.nav_faq:{

                    }
                    case R.id.nav_teams:{

                    }
                }
                menuItem.setChecked(true);
                eupdatesDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

}
