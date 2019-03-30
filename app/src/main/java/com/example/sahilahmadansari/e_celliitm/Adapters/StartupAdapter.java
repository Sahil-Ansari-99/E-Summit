package com.example.sahilahmadansari.e_celliitm.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Model.SponsorsModel;
import com.example.sahilahmadansari.e_celliitm.Model.StartupModel;
import com.example.sahilahmadansari.e_celliitm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StartupAdapter extends RecyclerView.Adapter<StartupPlaceViewHolder> {

    private Context mContext;
    private List<StartupModel> itemList;

    public StartupAdapter(Context mContext, List<StartupModel> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @Override
    public StartupPlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.startup_showcase_card,
                parent, false);
        return new StartupPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StartupPlaceViewHolder placeViewHolder, int i) {
        StartupModel sponsorsModel=itemList.get(i);

        String imgUrl=sponsorsModel.getUrl();

        Picasso.with(mContext).load(imgUrl).into(placeViewHolder.mPlace);

        placeViewHolder.sponsorName.setText(sponsorsModel.getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

class StartupPlaceViewHolder extends RecyclerView.ViewHolder {

    ImageView mPlace;
    TextView sponsorName;

    public StartupPlaceViewHolder(View itemView) {
        super(itemView);

        mPlace = itemView.findViewById(R.id.startup_showcase_card_image);
        sponsorName=itemView.findViewById(R.id.startup_showcase_card_name);
    }

}

