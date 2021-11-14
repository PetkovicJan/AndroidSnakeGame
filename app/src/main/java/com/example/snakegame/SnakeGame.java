package com.example.snakegame;

import android.graphics.Point;
import android.util.Log;

public class SnakeGame {

    public enum Direction { UP, DOWN, LEFT, RIGHT }

    SnakeGame(int width, int height){
        mWidth = width;
        mHeight = height;
    }

    public void start(){
        init();
        mStopGame = false;
        mThread.start();
    }

    public void stop(){
        mStopGame = true;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setGameView(GameView gameView){
        mGameView = gameView;
    }

    public void setDirection(Direction dir){
        mHeadDir = dir;
    }

    // Thread-safe getter & setter.
    public Point getHead(){
        Point headCopy = null;
        synchronized (snakeLock){
            headCopy = new Point(mHead);
        }
        return headCopy;
    }

    private void setHead(Point newHead){
        synchronized (snakeLock){
            mHead = new Point(newHead);
        }
    }

    private void init(){
        mHead = new Point(mWidth/2, mHeight / 2);
        mHeadDir = Direction.UP;
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mainLoop();
            }
        });
    }
    private void mainLoop(){
        while (!mStopGame) {
            update();
            updateView();
            trySleep(50);
        }
    }

    private void update(){
        Point newHead = getHead();

        // Move based on the snake direction.
        if(mHeadDir == Direction.UP){
            newHead.y -= HEAD_VEL;
        } else if(mHeadDir == Direction.DOWN){
            newHead.y += HEAD_VEL;
        } else if(mHeadDir == Direction.RIGHT) {
            newHead.x += HEAD_VEL;
        } else if(mHeadDir == Direction.LEFT) {
            newHead.x -= HEAD_VEL;
        }

        // Wrap the position.
        newHead.x = wrap(newHead.x, mWidth);
        newHead.y = wrap(newHead.y, mHeight);

        setHead(newHead);
    }

    private void updateView(){
        mGameView.postInvalidate();
    }

    private void trySleep(int milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int wrap(int num, int max){
        if(num >= max) {
            return num - max;
        }
        else if(num < 0) {
            return num + max;
        }
        else {
            return num;
        }
    }

    // Game size.
    private int mWidth = 0;
    private int mHeight = 0;

    // Stop game flag.
    private boolean mStopGame = true;

    // Thread running main loop.
    private Thread mThread;

    // Snake's head position.
    private Point mHead = null;

    // Snake's head direction.
    private Direction mHeadDir;

    // Snake's head velocity.
    private final int HEAD_VEL = 6;

    // Thread lock for accessing Snake position.
    private final Object snakeLock = new Object();

    // Reference to a GameView instance, responsible for drawing.
    GameView mGameView;

    // Debug tag.
    private static final String TAG = "SnakeGame";
}
