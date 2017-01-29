package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Brandon on 1/29/17.
 */

public class Card {

    public static int width;
    public static int height;
    public static int borderWidth;

    private int x;
    private int y;
    private Color color;

    private Rectangle bounds = new Rectangle();

    public Card(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;

        bounds.setWidth(width);
        bounds.setHeight(height);
    }

    public Rectangle getBounds() {
        bounds.setX(getX());
        bounds.setY(getY());
        return bounds;
    }

    private int getX(){
        return x;
    }

    private int getY(){
        return y;
    }
}
