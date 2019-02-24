package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Model.AgendaModel;
import com.example.sahilahmadansari.e_celliitm.R;

import java.util.List;

class AgendaDayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView agendaTitle, agendaVenue, agendaTime, agendaDuration;

    public AgendaDayViewHolder(@NonNull View itemView) {
        super(itemView);

        agendaTitle=(TextView)itemView.findViewById(R.id.agenda_card_title);
        agendaVenue=(TextView)itemView.findViewById(R.id.agenda_card_venue);
        agendaTime=(TextView)itemView.findViewById(R.id.agenda_card_time);
        agendaDuration=(TextView)itemView.findViewById(R.id.agenda_card_duration);
    }

    @Override
    public void onClick(View view) {

    }
}

public class AgendaDayAdapter extends RecyclerView.Adapter<AgendaDayViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<AgendaModel> itemList;

    public AgendaDayAdapter(Context context, List<AgendaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AgendaDayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=inflater.inflate(R.layout.agenda_card, viewGroup, false);
        return new AgendaDayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaDayViewHolder agendaDayViewHolder, int i) {
        agendaDayViewHolder.agendaTitle.setText(itemList.get(i).getTitle());
        agendaDayViewHolder.agendaTime.setText(itemList.get(i).getTime());
        agendaDayViewHolder.agendaVenue.setText(itemList.get(i).getVenue());
        agendaDayViewHolder.agendaDuration.setText(itemList.get(i).getDuration());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
