package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Model.AnnounceModel;
import com.example.sahilahmadansari.e_celliitm.R;

import java.util.List;

class AnnouncementsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView textTitle, textDesc;

    public AnnouncementsViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle=(TextView)itemView.findViewById(R.id.announce_title);
        textDesc=(TextView)itemView.findViewById(R.id.announce_desc);
    }

    @Override
    public void onClick(View view) {

    }
}

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<AnnounceModel> itemList;

    public AnnouncementsAdapter(Context context, List<AnnounceModel> itemList) {
        this.context = context;
        this.itemList=itemList;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AnnouncementsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=layoutInflater.inflate(R.layout.announcements_card, viewGroup, false);
        return new AnnouncementsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementsViewHolder announcementsViewHolder, int i) {
        announcementsViewHolder.textTitle.setText(itemList.get(i).getTitle());
        announcementsViewHolder.textDesc.setText(itemList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
