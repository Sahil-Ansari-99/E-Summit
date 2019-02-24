package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Model.User;
import com.example.sahilahmadansari.e_celliitm.People.AttendeeActivity;
import com.example.sahilahmadansari.e_celliitm.R;

import java.util.List;

class AttendeesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView username,designation,company;

    public AttendeesViewHolder(@NonNull View itemView) {
        super(itemView);

        username=(TextView)itemView.findViewById(R.id.attendees_username);
        designation=(TextView)itemView.findViewById(R.id.attendees_desgination);
        company=(TextView)itemView.findViewById(R.id.attendees_company);
    }

    @Override
    public void onClick(View view) {

    }
}

public class AttendeesAdapter extends RecyclerView.Adapter<AttendeesViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<User> userList;

    public AttendeesAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AttendeesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=inflater.inflate(R.layout.attendees_card, viewGroup, false);
        return new AttendeesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AttendeesViewHolder attendeesViewHolder, int i) {
        attendeesViewHolder.username.setText(userList.get(i).getUsername());
        attendeesViewHolder.designation.setText(userList.get(i).getDesignation());
        attendeesViewHolder.company.setText(userList.get(i).getCompany());

        attendeesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, AttendeeActivity.class);
                intent.putExtra("id", userList.get(attendeesViewHolder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
