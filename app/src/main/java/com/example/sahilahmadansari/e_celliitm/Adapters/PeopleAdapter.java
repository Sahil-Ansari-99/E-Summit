package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sahilahmadansari.e_celliitm.Agenda.Day1Fragment;
import com.example.sahilahmadansari.e_celliitm.Agenda.Day2Fragment;
import com.example.sahilahmadansari.e_celliitm.Agenda.Day3Fragment;
import com.example.sahilahmadansari.e_celliitm.People.AttendeesFragment;
import com.example.sahilahmadansari.e_celliitm.People.MessagesFragment;
import com.example.sahilahmadansari.e_celliitm.People.ReceivedFragment;

public class PeopleAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public PeopleAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.context=context;
        this.totalTabs=totalTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new AttendeesFragment();

            case 1: return new MessagesFragment();

            case 2: return new ReceivedFragment();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
