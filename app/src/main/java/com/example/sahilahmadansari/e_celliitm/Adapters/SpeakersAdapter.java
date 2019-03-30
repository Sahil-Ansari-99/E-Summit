package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Model.SpeakersModel;
import com.example.sahilahmadansari.e_celliitm.Model.SponsorsModel;
import com.example.sahilahmadansari.e_celliitm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpeakersAdapter extends RecyclerView.Adapter<SpeakersPlaceViewHolder> {

    private Context mContext;
    private List<SpeakersModel> itemList;

    public SpeakersAdapter(Context mContext, List<SpeakersModel> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @Override
    public SpeakersPlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speakers_card,
                parent, false);
        return new SpeakersPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeakersPlaceViewHolder placeViewHolder, int i) {
        final SpeakersModel speakersModel=itemList.get(i);

        String imgUrl=speakersModel.getUrl();

        Picasso.with(mContext).load(imgUrl).into(placeViewHolder.mPlace);

        placeViewHolder.speakerName.setText(speakersModel.getName());
        placeViewHolder.speakerDesignation.setText(speakersModel.getDesignation());

        placeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkedInUrl=speakersModel.getLinkedin();
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(linkedInUrl));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

class SpeakersPlaceViewHolder extends RecyclerView.ViewHolder {

    ImageView mPlace;
    TextView speakerName, speakerDesignation;

    public SpeakersPlaceViewHolder(View itemView) {
        super(itemView);

        mPlace = itemView.findViewById(R.id.speakers_card_image);
        speakerName = itemView.findViewById(R.id.speakers_card_name);
        speakerDesignation = itemView.findViewById(R.id.speakers_card_designation);
    }
}
