package com.example.sahilahmadansari.e_celliitm.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Interface.ItemClickListener;
import com.example.sahilahmadansari.e_celliitm.Model.EUpdatesModel;
import com.example.sahilahmadansari.e_celliitm.R;

import java.util.List;

class EUpdatesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    public TextView txtTitle, txtPubDate, txtContent, txtUpvotes;
    public ImageButton upvoteButton;
    public ItemClickListener itemClickListener;

    public EUpdatesViewHolder(View itemView) {
        super(itemView);

        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtPubDate = (TextView) itemView.findViewById(R.id.txtPubDate);
        txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        txtUpvotes = (TextView) itemView.findViewById(R.id.upvoteNumber);

        upvoteButton = (ImageButton) itemView.findViewById(R.id.upvote);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), true);
        return true;
    }
}

public class EUpdatesAdapter extends RecyclerView.Adapter<EUpdatesViewHolder>{
    List<EUpdatesModel> contentList;
    Context mContext;
    LayoutInflater inflater;

    public EUpdatesAdapter(List<EUpdatesModel> contentList, Context mContext) {
        this.contentList = contentList;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public EUpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=inflater.inflate(R.layout.eupdates_card,viewGroup,false);
        return new EUpdatesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EUpdatesViewHolder eUpdatesViewHolder, int i) {
        eUpdatesViewHolder.txtTitle.setText(contentList.get(i).getTitle());
        eUpdatesViewHolder.txtPubDate.setText(contentList.get(i).getPubDate());
        eUpdatesViewHolder.txtContent.setText(contentList.get(i).getContent());
        eUpdatesViewHolder.txtUpvotes.setText(contentList.get(i).getUpvotes().toString());

        eUpdatesViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    String extLink=contentList.get(position).getLink();
                    if(!extLink.equals("")){
                        Intent browserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(extLink));
                        mContext.startActivity(browserIntent);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
