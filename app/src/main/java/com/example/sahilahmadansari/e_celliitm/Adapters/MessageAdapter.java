package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Model.Message;
import com.example.sahilahmadansari.e_celliitm.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {
    private static final int VIEW_MESSAGE_TYPE_SENT=1;
    private static final int VIEW_MESSAGE_TYPE_RECEIVED=2;

    private Context context;
    private List<Message> messageList;
    private String uid;

    public MessageAdapter(Context context, List<Message> messageList, String uid) {
        this.context = context;
        this.messageList = messageList;
        this.uid=uid;
    }

    @Override
    public int getItemViewType(int position) {
        Message message=messageList.get(position);

        if(message.getSender().equals(uid)){
            return VIEW_MESSAGE_TYPE_SENT;
        }else{
            return VIEW_MESSAGE_TYPE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        if(i==VIEW_MESSAGE_TYPE_SENT){
            view=LayoutInflater.from(context).inflate(R.layout.message_sent, viewGroup, false);
            return new SentMessageViewHolder(view);
        }else{
            view=LayoutInflater.from(context).inflate(R.layout.message_received, viewGroup, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Message message=messageList.get(i);

        switch (viewHolder.getItemViewType()){
            case VIEW_MESSAGE_TYPE_RECEIVED :{
                ((ReceivedMessageViewHolder)viewHolder).bind(message);
                break;
            }
            case VIEW_MESSAGE_TYPE_SENT :{
                ((SentMessageViewHolder)viewHolder).bind(message);
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}

class SentMessageViewHolder extends RecyclerView.ViewHolder{
    TextView messageBody, messageTime;

    public SentMessageViewHolder(@NonNull View itemView) {
        super(itemView);

        messageBody=(TextView)itemView.findViewById(R.id.message_sent_body);
        messageTime=(TextView)itemView.findViewById(R.id.message_sent_time);
    }

    void bind(Message message){
        messageBody.setText(message.getMessage());
        messageTime.setText(message.getTime());
    }
}

class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{
    TextView messageBody, messageTime;

    public ReceivedMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        messageBody=(TextView)itemView.findViewById(R.id.message_received_body);
        messageTime=(TextView)itemView.findViewById(R.id.message_received_time);
    }

    void bind(Message message){
        messageBody.setText(message.getMessage());
//        Date date=new Date(message.getTime());
//        SimpleDateFormat sdf=new SimpleDateFormat("h:mm a");
//        sdf.format(date);
        messageTime.setText(message.getTime());
    }
}
