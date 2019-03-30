package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Agenda.AgendaIndividual;
import com.example.sahilahmadansari.e_celliitm.Model.AgendaModel;
import com.example.sahilahmadansari.e_celliitm.Model.Speakers;
import com.example.sahilahmadansari.e_celliitm.R;

import java.util.ArrayList;
import java.util.List;

class AgendaDayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView agendaTitle, agendaVenue, agendaTime, agendaContent;

    public AgendaDayViewHolder(@NonNull View itemView) {
        super(itemView);

        agendaTitle=(TextView)itemView.findViewById(R.id.agenda_card_title);
        agendaVenue=(TextView)itemView.findViewById(R.id.agenda_card_venue);
        agendaTime=(TextView)itemView.findViewById(R.id.agenda_card_time);
        agendaContent=(TextView)itemView.findViewById(R.id.agenda_card_content);
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
    public void onBindViewHolder(@NonNull AgendaDayViewHolder agendaDayViewHolder, final int i) {
        agendaDayViewHolder.agendaTitle.setText(itemList.get(i).getTitle());
        agendaDayViewHolder.agendaTime.setText(itemList.get(i).getTime());
        agendaDayViewHolder.agendaVenue.setText(itemList.get(i).getVenue());
        agendaDayViewHolder.agendaContent.setText(itemList.get(i).getDescription());

        agendaDayViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> speakerList=new ArrayList<>();
                List<Speakers> obtainedList=itemList.get(i).getSpeakers();
                int j=0;

                do{
                    String speakerString=obtainedList.get(j).getName()+", "+obtainedList.get(j).getDesignation();
                    speakerList.add(speakerString);
                    j++;
                }while (j<obtainedList.size());

                Intent intent=new Intent(context, AgendaIndividual.class);
                intent.putExtra("title", itemList.get(i).getTitle());
                intent.putExtra("time", itemList.get(i).getTime());
                intent.putExtra("venue", itemList.get(i).getVenue());
                intent.putExtra("content", itemList.get(i).getDescription());
                intent.putExtra("speakers", speakerList);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
