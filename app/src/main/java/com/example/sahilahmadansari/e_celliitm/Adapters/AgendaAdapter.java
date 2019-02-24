package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sahilahmadansari.e_celliitm.Agenda.Day1Fragment;
import com.example.sahilahmadansari.e_celliitm.Agenda.Day2Fragment;
import com.example.sahilahmadansari.e_celliitm.Agenda.Day3Fragment;

public class AgendaAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public AgendaAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.context=context;
        this.totalTabs=totalTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: Day1Fragment day1Fragment=new Day1Fragment();
                    return day1Fragment;

            case 1: return new Day2Fragment();

            case 2: return new Day3Fragment();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
