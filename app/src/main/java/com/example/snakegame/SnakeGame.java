package com.example.snakegame;

import android.graphics.Point;
import android.util.Log;

import java.util.Random;

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

    // Thread-safe getters & setters.
    public Point getHead(){
        Point headCopy;
        synchronized (snakeLock){
            headCopy = new Point(mHead);
        }
        return headCopy;
    }

    public Point getFruit(){
        Point fruitCopy;
        synchronized (fruitLock){
            fruitCopy = new Point(mFruit);
        }
        return fruitCopy;
    }

    private void setHead(Point newHead){
        synchronized (snakeLock){
            mHead = new Point(newHead);
        }
    }

    private void setFruit(Point newFruit){
        synchronized (fruitLock){
            mFruit = new Point(newFruit);
        }
    }

    private void init(){
        mHead = new Point(mWidth/2, mHeight / 2);
        mHeadDir = Direction.UP;
        mFruit = generateRandomPoint();
        mScore = 0;
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
        updateHead();
        updateFruit();
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

    private void updateHead(){
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

    private void updateFruit(){
        if(areClose(getHead(), getFruit())){
            setFruit(generateRandomPoint());
            mScore += 10;
            Log.d(TAG, "Score: " + String.valueOf(mScore));
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

    private boolean areClose(Point a, Point b){
        double dx = (double)(a.x - b.x);
        double dy = (double)(a.y - b.y);
        double dist = Math.hypot(dx, dy);

        return dist < 20;
    }

    private Point generateRandomPoint(){
        Point randPoint = new Point();
        randPoint.x = mRand.nextInt(mWidth);
        randPoint.y = mRand.nextInt(mHeight);
        return randPoint;
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

    // Fruit position.
    private Point mFruit;

    // Thread lock for accessing fruit's position.
    private final Object fruitLock = new Object();

    // Random generator.
    private final Random mRand = new Random();

    // Score.
    int mScore;

    // Reference to a GameView instance, responsible for drawing.
    GameView mGameView;

    // Debug tag.
    private static final String TAG = "SnakeGame";
}
