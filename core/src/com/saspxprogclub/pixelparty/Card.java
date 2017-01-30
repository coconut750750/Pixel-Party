package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/***
 * Created by Brandon on 1/29/17.
 */

class Card {

    static int width;
    public static int height;
    private static int cardStart;
    private static int margin;
    private static int borderWidth1;

    private int pos;
    private int start;
    private int x;
    private int y;
    private Color color;
    private Color borderColor;
    private int borderWidth;
    private boolean selected;
    private Rectangle bounds = new Rectangle();

    static void initCards(int width, int height, int cardStart, int margin, int borderWidth){
        Card.width = width;
        Card.height = height;
        Card.cardStart = cardStart;
        Card.margin = margin;
        Card.borderWidth1 = borderWidth;
    }

    Card(int pos, Color color){
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

    Rectangle getBounds() {
        bounds.setX(getX());
        bounds.setY(getY());
        return bounds;
    }

    int getPos(){
        return pos;
    }

    int getX(){
        return x;
    }

    int getY(){
        return y;
    }

    void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Color getColor(){
        return color;
    }

    private void setBorderColor(Color color){
        this.borderColor = color;
    }

    Color getBorderColor(){
        return this.borderColor;
    }

    int getBorderWidth(){
        return borderWidth;
    }

    boolean isSelected(){
        return selected;
    }

    void setSelected(boolean selected){
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
