package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/***
 * Created by Brandon on 1/29/17.
 */

public class Card {

    public static int width;
    public static int height;
    public static int cardStart;
    public static int margin;
    public static int borderWidth1;

    private int pos;
    private int start;
    private int x;
    private int y;
    private Color color;
    private Color borderColor;
    private int borderWidth;
    private boolean selected;
    private Rectangle bounds = new Rectangle();

    public static void initCards(int width, int height, int cardStart, int margin, int borderWidth){
        Card.width = width;
        Card.height = height;
        Card.cardStart = cardStart;
        Card.margin = margin;
        Card.borderWidth1 = borderWidth;
    }

    public Card(int pos, Color color){
        this.pos = pos;
        this.x = cardStart+width*pos+margin*(pos+1);
        this.start = cardStart+width*pos+margin*(pos+1);
        this.y = margin;

        this.color = color;
        this.borderColor = Color.BLACK;
        this.borderWidth = Card.borderWidth1;
        this.selected = false;
        bounds.setWidth(width);
        bounds.setHeight(height);
    }

    public Rectangle getBounds() {
        bounds.setX(getX());
        bounds.setY(getY());
        return bounds;
    }

    public int getPos(){
        return pos;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Color getColor(){
        return color;
    }

    public void setBorderColor(Color color){
        this.borderColor = color;
    }

    public Color getBorderColor(){
        return this.borderColor;
    }

    public int getBorderWidth(){
        return borderWidth;
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
        if (selected){
            this.borderWidth = Card.borderWidth1 * 2;
            this.y = margin + height/10;
            this.setBorderColor(Color.WHITE);
        } else {
            this.borderWidth = Card.borderWidth1;
            this.y = margin;
            this.setBorderColor(Color.BLACK);
            this.x = start;
        }
    }
}
