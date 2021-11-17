package com.geekym.care4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

public class Self_Assessment_Result extends AppCompatActivity {

    DonutProgress progress;
    private TextView status, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_self_assessment_result);

        progress = findViewById(R.id.progress);
        status = findViewById(R.id.status);
        desc = findViewById(R.id.desc);

        //UserDetails
        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);

        String name=sp.getString("saved_name", "");         //Retrieved from Firebase
        String height=sp.getString("saved_height", "");     //Maybe Null
        String weight=sp.getString("saved_weight", "");     //Maybe Null
        String bpm=sp.getString("saved_bpm", "");           //Maybe Null
            String age=sp.getString("saved_age", "");       //Not Null

        if(bpm.equals("NULL")){
            bpm = "5";
            Integer bpm_int = new Integer(bpm);
        }
        if(weight.equals("NULL")){
            weight = "5";
            Integer wt = new Integer(weight);
        }
        if(height.equals("NULL")){
            height = "5";
            Integer ht = new Integer(height);
        }



        Integer prog = getIntent().getExtras().getInt("newval",0);
        progress.setProgress(prog.floatValue());

        if(prog<=7) {
            status.setText("Safe-Zone");
            desc.setText("You're Safe Now. Follow proper safety Tips");
        }
        else if(prog>7 && prog<=14)
        {
            status.setText("Low Risk");
            desc.setText("You're are at low risk, take care and proper medications");
        }
        else if(prog>=15 && prog<=29)
        {
            status.setText("Medium Risk");
            desc.setText("Your health is still serious, consult a doctor and self quarantine yourself");
        }
        else if(prog>=30 && prog<=60)
        {
            status.setText("High Risk");
            desc.setText("Please don't go outside, take proper care and consult a Doctor immediately");
        }
        else
        {
            status.setText("Critical Situation");
            desc.setText("Consult a Doctor Immediately, you're at really High Risk!");
        }
    }
    public void back(View view) {
        Intent intent = new Intent(Self_Assessment_Result.this,Self_Assessment.class);
        startActivity(intent);
        finish();
    }

    public void home(View view) {
        Intent intent = new Intent(Self_Assessment_Result.this,Homescreen.class);
        startActivity(intent);
        finishAffinity();
    }
    public void safety(View view) {
        Intent intent = new Intent(Self_Assessment_Result.this,Safety_Tips.class);
        startActivity(intent);
        finishAffinity();
    }
}