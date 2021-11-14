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
    }

    public void setSnakeGame(SnakeGame snakeGame){
        mSnakeGame = snakeGame;
    }

    @Override
    public void onDraw(Canvas canvas){
        drawBoundingRect(canvas);
        drawSnake(canvas);
    }

    private void drawBoundingRect(Canvas canvas){
        Rect r = new Rect(0, 0, mWidth, mHeight);
        canvas.drawRect(r, mWallPaint);
    }

    private void drawSnake(Canvas canvas){
        Point head = mSnakeGame.getHead();
        canvas.drawCircle(head.x, head.y, 15, mSnakePaint);
    }

    // View size.
    private int mWidth;
    private int mHeight;

    // Paints for objects.
    private Paint mWallPaint;
    private Paint mSnakePaint;

    // Reference to the SnakeGame instance, responsible for game logic.
    SnakeGame mSnakeGame;
}
