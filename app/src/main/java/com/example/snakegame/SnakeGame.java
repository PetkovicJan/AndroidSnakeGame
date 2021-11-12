package com.example.snakegame;

import android.util.Log;

public class SnakeGame {

    SnakeGame(int width, int height){
        mWidth = width;
        mHeight = height;
    }

    public void start(){
        mStopGame = false;

        init();
        mThread.start();
    }

    public void stop(){
        mStopGame = true;
    }

    private void init(){
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mainLoop();
            }
        });
    }
    private void mainLoop(){
        while (!mStopGame) {
            Log.d(TAG, "Game Loop");
            trySleep(500);
        }

    }

    private void trySleep(int milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Game size.
    int mWidth = 0;
    int mHeight = 0;

    // Stop game flag.
    boolean mStopGame = true;

    // Thread running main loop.
    Thread mThread;

    // Debug tag.
    private static final String TAG = "SnakeGame";
}
