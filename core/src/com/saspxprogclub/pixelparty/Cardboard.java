package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 2/4/17.
 */

public class Cardboard extends GameObject {

    private static final Color color = Color.GREEN;
    private Mana mana;
    private int width;
    private int height;

    /**
     * constructor
     * @param width  of the object (minions, maybe towers later)
     * @param height of the object
     */
    Cardboard(int width, int height, Mana mana) {
        bounds = new Rectangle();
        ((Rectangle)bounds).setWidth(width);
        ((Rectangle)bounds).setHeight(height);
        this.width = width;
        this.height = height;
        super.setVelocity(0,0);
        this.mana = mana;
    }

    void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.rect(getX(), 0, getWidth(), getHeight());
        mana.draw(shapeRenderer);
    }

    @Override
    void setBounds(float x, float y) {
        ((Rectangle)bounds).set(x, y, width, height);
    }

    @Override
    void updateBounds() {
        ((Rectangle)bounds).setX(getX());
        ((Rectangle)bounds).setY(getY());
    }

    @Override
    float getHeight() {
        return height;
    }

    @Override
    float getWidth() {
        return width;
    }

    @Override
    float getX() {
        return (field.width-getWidth())/2;
    }

    @Override
    float bottom(){
        return ((Rectangle)bounds).y;
    }

    @Override
    public float left(){
        return ((Rectangle)bounds).x;
    }

    @Override
    public float right(){
        return ((Rectangle)bounds).x+((Rectangle)bounds).width;
    }

    @Override
    float top(){
        return ((Rectangle)bounds).y+getHeight();
    }
}
