package com.mitron57.sensors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;

import androidx.annotation.NonNull;

public class DrawView extends View {
    private float x, y;
    private boolean entry = true;

    private final float radius = 200;

    public DrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        paint.setColor(Color.RED);
        if (entry) {
            x = getWidth() / 2.0f;
            y = getHeight() / 2.0f;
            entry = false;
        }
        float velocity = 10f;
        double offsetX = Math.sin(MainActivity.orientation[2]) * velocity;
        double offsetY = Math.sin(-MainActivity.orientation[1]) * velocity;
        x += offsetX;
        y += offsetY;
        if (radius > x || x > getWidth() - radius) {
            x -= offsetX;
        }
        if (radius > y || y > getHeight() - radius) {
            y -= offsetY;
        }
        canvas.drawCircle((float)x, (float)y, radius, paint);
        invalidate();
    }
}
