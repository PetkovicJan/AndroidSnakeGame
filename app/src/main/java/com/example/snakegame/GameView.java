package com.example.snakegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    }

    @Override
    public void onDraw(Canvas canvas){
        drawBoundingRect(canvas);
    }

    private void drawBoundingRect(Canvas canvas){
        Rect r = new Rect(0, 0, mWidth, mHeight);
        canvas.drawRect(r, mWallPaint);
    }

    // View size.
    private int mWidth = 0;
    private int mHeight = 0;

    // Paints for objects.
    private Paint mWallPaint;
}
