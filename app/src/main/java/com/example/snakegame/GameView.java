package com.example.snakegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public class GameView extends View {

    GameView(Context context, int width, int height){
        super(context);

        mWidth = width;
        mHeight = height;
        this.setLayoutParams(new ViewGroup.LayoutParams(width, height));

        mWallPaint = new Paint();
        mWallPaint.setColor(Color.BLACK);
        mWallPaint.setStrokeWidth(20);
        mWallPaint.setStyle(Paint.Style.STROKE);

        mSnakePaint = new Paint();
        mSnakePaint.setColor(Color.GREEN);

        mFruitPaint = new Paint();
        mFruitPaint.setColor(Color.RED);
    }

    public void setSnakeGame(SnakeGame snakeGame){
        mSnakeGame = snakeGame;
    }

    @Override
    public void onDraw(Canvas canvas){
        drawBoundingRect(canvas);
        drawSnake(canvas);
        drawFruit(canvas);
    }

    private void drawBoundingRect(Canvas canvas){
        Rect r = new Rect(0, 0, mWidth, mHeight);
        canvas.drawRect(r, mWallPaint);
    }

    private void drawSnake(Canvas canvas){
        Point head = mSnakeGame.getHead();
        canvas.drawCircle(head.x, head.y, 15, mSnakePaint);
    }

    private void drawFruit(Canvas canvas){
        Point fruit = mSnakeGame.getFruit();
        canvas.drawCircle(fruit.x, fruit.y, 15, mFruitPaint);
    }

    // View size.
    private int mWidth;
    private int mHeight;

    // Paints for objects.
    private Paint mWallPaint;
    private Paint mSnakePaint;
    private Paint mFruitPaint;

    // Reference to the SnakeGame instance, responsible for game logic.
    SnakeGame mSnakeGame;
}
