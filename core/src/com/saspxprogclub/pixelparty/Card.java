package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/***
 * Created by Brandon on 1/29/17.
 */

public class Card {

    public static int width;
    public static int height;
    public static int borderWidth;
    public static int cardStart;

    private int pos;
    private int x;
    private int y;
    private Color color;
    private Rectangle bounds = new Rectangle();

    public static void initCards(int width, int height, int borderWidth, int cardStart){
        Card.width = width;
        Card.height = height;
        Card.borderWidth = borderWidth;
        Card.cardStart = cardStart;
    }

    public Card(int pos, Color color){
        this.pos = pos;
        this.x = cardStart+width*pos;
        this.y = 0;

        this.color = color;
        bounds.setWidth(width);
        bounds.setHeight(height);
    }

    public Rectangle getBounds() {
        bounds.setX(getX());
        bounds.setY(getY());
        return bounds;
    }

    private int getPos(){
        return pos;
    }

    private int getX(){
        return x;
    }

    private int getY(){
        return y;
    }
}
