package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity {

    // Intent factory method, that provides users with intent to start this activity.
    public static Intent makeIntent(Context context){
        // Create a named intent.
        return new Intent(context, GameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Game size.
        int width = 800;
        int height = 800;

        // Create game view and add it to its placeholder.
        LinearLayout container = (LinearLayout)findViewById(R.id.game_view);
        mGameView = new GameView(this, width, height);
        container.addView(mGameView);

        // Create snake game.
        mSnakeGame = new SnakeGame(width, height);
    }

    protected void onStart(){
        super.onStart();
        mSnakeGame.start();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mSnakeGame.stop();
    }

    SnakeGame mSnakeGame;
    GameView mGameView;

}