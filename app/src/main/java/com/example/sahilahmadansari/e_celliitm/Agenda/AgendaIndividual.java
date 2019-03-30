package com.example.sahilahmadansari.e_celliitm.Agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sahilahmadansari.e_celliitm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AgendaIndividual extends AppCompatActivity {
    Toolbar toolbar;
    String agendaTitle, agendaVenue, agendaTime, agendaContent, agendaSpeakers="";
    ArrayList<String> speakersList;
    TextView textTitle, textVenue, textTime, textContent, textSpeakers;
    ImageView agendaImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_individual);

        Intent intent=getIntent();
        agendaTitle=intent.getStringExtra("title");
        agendaVenue=intent.getStringExtra("venue");
        agendaTime=intent.getStringExtra("time");
        agendaContent=intent.getStringExtra("content");
        speakersList=intent.getStringArrayListExtra("speakers");

        for(int j=0; j<speakersList.size(); j++){
            agendaSpeakers=agendaSpeakers.concat(String.valueOf(j+1)).concat(". ").concat(speakersList.get(j)).concat("\n");
        }

        toolbar=(Toolbar)findViewById(R.id.agenda_individual_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        textTitle=(TextView)findViewById(R.id.agenda_individual_title);
        textTime=(TextView)findViewById(R.id.agenda_individual_time);
        textVenue=(TextView)findViewById(R.id.agenda_individual_venue);
        textContent=(TextView)findViewById(R.id.agenda_individual_content);
        textSpeakers=(TextView)findViewById(R.id.agenda_individual_speakers);

        agendaImage=(ImageView)findViewById(R.id.agenda_individual_img);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(agendaTitle.equals("Elevate")||agendaTitle.equals("elevate")){
            agendaImage.setImageResource(R.drawable.elevate);
        }else if(agendaTitle.toLowerCase().equals("inspirit")){
            agendaImage.setImageResource(R.drawable.inspirit);
        }else if(agendaTitle.toLowerCase().equals("bizquiz")){
            agendaImage.setImageResource(R.drawable.biz_quiz);
        }else if(agendaTitle.toLowerCase().equals("bootcamp")){
            agendaImage.setImageResource(R.drawable.bootcamp);
        }else if(agendaTitle.toLowerCase().equals("unconference")){
            agendaImage.setImageResource(R.drawable.unconference);
        }else if(agendaTitle.toLowerCase().equals("workshop")){
            agendaImage.setImageResource(R.drawable.workshops);
        }else{
            agendaImage.setImageResource(R.drawable.esummit_logo_black);
        }
        textTitle.setText(agendaTitle);
        textTime.setText(agendaTime);
        textVenue.setText(agendaVenue);
        textContent.setText(agendaContent);
        textSpeakers.setText(agendaSpeakers);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
