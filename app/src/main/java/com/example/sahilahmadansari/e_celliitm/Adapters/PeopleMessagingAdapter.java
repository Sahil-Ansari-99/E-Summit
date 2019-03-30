package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Messaging.MessagingMain;
import com.example.sahilahmadansari.e_celliitm.Model.User;
import com.example.sahilahmadansari.e_celliitm.People.AttendeeActivity;
import com.example.sahilahmadansari.e_celliitm.R;

import java.util.List;

class PeopleMessagingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView username;

    public PeopleMessagingViewHolder(@NonNull View itemView) {
        super(itemView);

        username=(TextView)itemView.findViewById(R.id.people_messaging_username);
    }

    @Override
    public void onClick(View view) {

    }
}

public class PeopleMessagingAdapter extends RecyclerView.Adapter<PeopleMessagingViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<String> userList;
    private List<String> userKeyList;

    public PeopleMessagingAdapter(Context context, List<String> userList, List<String> userKeyList) {
        this.context = context;
        this.userList = userList;
        this.userKeyList=userKeyList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PeopleMessagingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=inflater.inflate(R.layout.people_messaging_card, viewGroup, false);
        return new PeopleMessagingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PeopleMessagingViewHolder peopleMessagingViewHolder, final int i) {
        peopleMessagingViewHolder.username.setText(userList.get(i));

        peopleMessagingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MessagingMain.class);
                intent.putExtra("user", userList.get(i));
                intent.putExtra("uid", userKeyList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}

