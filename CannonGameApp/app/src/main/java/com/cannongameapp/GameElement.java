package com.cannongameapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameElement {
    protected CannonView view;
    protected Paint paint = new Paint();
    protected Rect shape;
    private float velocityY;
    private int soundId;

    // public constructor
    public GameElement(CannonView view, int color, int soundId, int x,
                       int y, int width, int length, float velocityY) {
        this.view = view;
        paint.setColor(color);
        shape = new Rect(x, y, x + width, y + length); // set bounds
        this.soundId = soundId;
        this.velocityY = velocityY;
    }

    // update GameElement position and check for wall collisions
    public void update(double interval) {
        // update vertical position
        shape.offset(0, (int) (velocityY * interval));

        // if this GameElement collides with the wall, reverse direction
        if (shape.top < 0 && velocityY < 0 ||
                shape.bottom > view.getScreenHeight() && velocityY > 0)
            velocityY *= -1; // reverse this GameElement's velocity
    }

    // draws this GameElement on the given Canvas
    public void draw(Canvas canvas) {
        canvas.drawRect(shape, paint);
    }

    // plays the sound that corresponds to this type of GameElement
    public void playSound() {
        view.playSound(soundId);
    }
}

