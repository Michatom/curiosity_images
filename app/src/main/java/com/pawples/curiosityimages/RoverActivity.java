package com.pawples.curiosityimages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;

public class RoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover);
        CardView cardCuriosity = findViewById(R.id.card);
        CardView cardOpportunity = findViewById(R.id.card2);

        ImageView imgCuriosity = findViewById(R.id.squareImg);
        ImageView imgOpportunity = findViewById(R.id.squareImg2);

        Glide.with(this)
                .load(R.drawable.curiosity_rover)
                .transition(GenericTransitionOptions.with(R.anim.img_rover))
                .into(imgCuriosity);
        Glide.with(this)
                .load(R.drawable.opportunity_rover)
                .transition(GenericTransitionOptions.with(R.anim.img_rover))
                .into(imgOpportunity);

        cardCuriosity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent curiosity = new Intent(RoverActivity.this,LaunchActivity.class);
                startActivity(curiosity);
            }
        });

        cardOpportunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent opportunity = new Intent(RoverActivity.this,LaunchActivity2.class);
                startActivity(opportunity);
            }
        });
    }
    @Override
    public void onBackPressed(){
        finishAffinity();
        super.onBackPressed();
    }
}