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
    private int buffer;
    private Color color;
    private Color borderColor;
    private int borderWidth;
    private boolean selected;
    private Rectangle bounds = new Rectangle();
    private String minionName;

    /**
     * must call this before creating any cards
     * @param width width of all cards (determined by width of screen)
     * @param height height of all cards (determined by height of screen)
     * @param cardStart x value of where card starts
     * @param margin pixels between each card
     * @param borderWidth pixels of each card border
     */
    static void initCards(int width, int height, int cardStart, int margin, int borderWidth){
        Card.width = width;
        Card.height = height;
        Card.cardStart = cardStart;
        Card.margin = margin;
        Card.borderWidth1 = borderWidth;
    }

    /**
     * constructor
     * @param pos integer value n, nth card
     * @param color color of card (will change to sprite/image)
     */

    Card(int pos, int y, Color color, String minionName){
        this.pos = pos;
        this.x = cardStart+width*pos+margin*(pos+1);
        this.start = cardStart+width*pos+margin*(pos+1);
        this.buffer = y;
        this.y = buffer+margin;
        this.minionName = minionName;

        this.color = color;
        this.borderColor = Color.BLACK;
        this.borderWidth = Card.borderWidth1;
        this.selected = false;
        bounds.setWidth(width);
        bounds.setHeight(height);
    }

    /**
     * @return rectangle of the card to determine if user touched it or not
     */

    Rectangle getBounds() {
        bounds.setX(getX());
        bounds.setY(getY());
        return bounds;
    }

    /**
     * @return the integer position of card, nth card
     */
    int getPos(){
        return pos;
    }

    /**
     * @return x position
     */
    int getX(){
        return x;
    }

    /**
     * @return y position
     */
    int getY(){
        return y;
    }

    /**
     * moves the card for dragging
     * @param x x position
     * @param y y position
     */
    void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return color of card (will change later to image)
     */
    public Color getColor(){
        return color;
    }

    /**
     * sets border color to determine if card was clicked or not
     * @param color border color
     */
    private void setBorderColor(Color color){
        this.borderColor = color;
    }

    /**
     * @return border color
     */
    Color getBorderColor(){
        return this.borderColor;
    }

    /**
     * @return border width
     */
    int getBorderWidth(){
        return borderWidth;
    }

    /**
     * @return if card is selected
     */
    boolean isSelected(){
        return selected;
    }

    String getMinionName(){
        return minionName;
    }

    /**
     * changes border width, border color, and position depending on if card is selected or not
     * @param selected boolean true if card is selected
     */
    void setSelected(boolean selected){
        this.selected = selected;
        if (selected){
            this.borderWidth = Card.borderWidth1 * 2;
            this.y = margin + buffer + height/10;
            this.setBorderColor(Color.WHITE);
        } else {
            this.borderWidth = Card.borderWidth1;
            this.y = margin + buffer;
            this.setBorderColor(Color.BLACK);
            this.x = start;
        }
    }
}
