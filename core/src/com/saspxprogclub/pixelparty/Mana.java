package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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

    /**
     * constructor
     * @param width  of the object (minions, maybe towers later)
     * @param height of the object
     */
    Mana(int width, int height, int margin, int initial) {
        super(width-2*margin, height-margin);
        this.count = initial;
        this.margin = margin;
        this.widthEach = (int)(Math.floor(getWidth()/10f));
        this.delay = DELAY;
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
    float getX() {
        return (field.width-widthEach*10)/2;
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
