package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ScoresActivity extends AppCompatActivity {

    // Intent factory method, that provides users with intent to start this activity.
    public static Intent makeIntent(Context context){
        // Create a named intent.
        return new Intent(context, ScoresActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
    }
}