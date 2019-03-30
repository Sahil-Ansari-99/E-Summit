package com.example.sahilahmadansari.e_celliitm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.Model.AnnounceModel;
import com.example.sahilahmadansari.e_celliitm.Model.FAQModel;
import com.example.sahilahmadansari.e_celliitm.R;

import java.util.List;

class FAQViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView textTitle, textAns;

    public FAQViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle=(TextView)itemView.findViewById(R.id.faq_title);
        textAns=(TextView)itemView.findViewById(R.id.faq_answer);
    }

    @Override
    public void onClick(View view) {

    }
}

public class FAQAdapter extends RecyclerView.Adapter<FAQViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<FAQModel> itemList;

    public FAQAdapter(Context context, List<FAQModel> itemList) {
        this.context = context;
        this.itemList=itemList;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=layoutInflater.inflate(R.layout.faq_card, viewGroup, false);
        return new FAQViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder faqViewHolder, int i) {
        FAQModel faqModel=itemList.get(i);

        faqViewHolder.textTitle.setText(faqModel.getQuestion());
        faqViewHolder.textAns.setText(faqModel.getAnswer());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
