package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 2/4/17.
 */

public class Mana extends GameObject {

    private static final Color color = Color.PURPLE;
    private static final Color colorEmpty = Color.GRAY;
    private static final float DELAY = 2.5f;
    private float delay;

    private int count;
    private static final int max = 10;
    private int margin;
    private int widthEach;

    private int width;
    private int height;

    /**
     * constructor
     * @param width  of the object (minions, maybe towers later)
     * @param height of the object
     */
    Mana(int width, int height, int margin, int initial) {
        bounds = new Rectangle();
        ((Rectangle)bounds).setWidth(width-2*margin);
        ((Rectangle)bounds).setHeight(height-margin);
        this.count = initial;
        this.margin = margin;
        this.widthEach = (int)(Math.floor(getWidth()/10f));
        this.delay = DELAY;
        this.width = width;
        this.height = height;
    }

    /**
     * called in the cardboard class
     * @param shapeRenderer self explanatory
     */
    void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        for (int i = 0; i < count; i++){
            shapeRenderer.rect(getX()+i*widthEach, margin, widthEach, getHeight());
        }
        shapeRenderer.setColor(colorEmpty);
        for(int i = count; i < max; i++){
            shapeRenderer.rect(getX()+i*widthEach, margin, widthEach, getHeight());
        }
    }

    @Override
    void setBounds(float x, float y) {
        ((Rectangle)bounds).set(x, y, width ,height);
    }

    @Override
    void updateBounds() {
        ((Rectangle)bounds).setX(getX());
        ((Rectangle)bounds).setY(getY());
    }

    @Override
    float getHeight() {
        return ((Rectangle)bounds).getHeight();
    }

    @Override
    float getWidth() {
        return ((Rectangle)bounds).getWidth();
    }

    @Override
    float getX() {
        return (field.width-widthEach*10)/2;
    }

    @Override
    float bottom() {
        return ((Rectangle)bounds).y;
    }

    @Override
    public float left() {
        return ((Rectangle)bounds).x;
    }

    @Override
    public float right() {
        return ((Rectangle)bounds).x+((Rectangle)bounds).getWidth();
    }

    @Override
    float top() {
        return ((Rectangle)bounds).y+((Rectangle)bounds).getHeight();
    }

    /**
     * updates mana, if delay = 0, reset it and add a mana
     * @param dt time elapsed
     */
    void update(float dt){
        this.delay -= dt;
        if(this.delay <= 0){
            this.delay = DELAY;
            count = Math.min(max, count+1);
        }
    }

    /**
     * @return how much mana you have
     */
    public int getCount() {
        return count;
    }

    /**
     * called every time minion is deployed
     * @param amount cost of the minion
     */
    void subtractCount(int amount){
        count -= amount;
    }
}
