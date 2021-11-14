package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        // Create snake game.
        mSnakeGame = new SnakeGame(width, height);

        // Create game view and add it to its placeholder.
        LinearLayout container = (LinearLayout)findViewById(R.id.game_view);
        mGameView = new GameView(this, width, height);
        container.addView(mGameView);

        // Connect snake game and its view.
        mSnakeGame.setGameView(mGameView);
        mGameView.setSnakeGame(mSnakeGame);

        // Connect buttons to game controls.
        connectNavigationButton(R.id.button_up, SnakeGame.Direction.UP);
        connectNavigationButton(R.id.button_down, SnakeGame.Direction.DOWN);
        connectNavigationButton(R.id.button_right, SnakeGame.Direction.RIGHT);
        connectNavigationButton(R.id.button_left, SnakeGame.Direction.LEFT);
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

    private void connectNavigationButton(int buttonId, SnakeGame.Direction dir){
        Button button = (Button)findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSnakeGame.setDirection(dir);
            }
        });
    }

    private SnakeGame mSnakeGame;
    private GameView mGameView;
}