package com.example.sahilahmadansari.e_celliitm.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.R;

class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
    public TextView eventsText;

    public EventsViewHolder(@NonNull View itemView) {
        super(itemView);
//        eventsText=(TextView)itemView.findViewById(R.id.events_text);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}
public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {

    private Context eventsContext;
    private LayoutInflater eventsLayoutInflater;

    public EventsAdapter(Context eventsContext) {
        this.eventsContext = eventsContext;
        eventsLayoutInflater = LayoutInflater.from(eventsContext);
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=eventsLayoutInflater.inflate(R.layout.events_card,viewGroup,false);
        return new EventsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder eventsViewHolder, int i) {
        eventsViewHolder.eventsText.setText("Events Text");

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
