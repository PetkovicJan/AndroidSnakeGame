package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gameButton = (Button)findViewById(R.id.game_button);
        gameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent gameIntent = GameActivity.makeIntent(MainActivity.this);
                startActivity(gameIntent);
            }
        });

        Button scoresButton = (Button)findViewById(R.id.score_button);
        scoresButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent scoresIntent = ScoresActivity.makeIntent(MainActivity.this);
                startActivity(scoresIntent);
            }
        });
    }
}