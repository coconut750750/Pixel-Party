package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 2/4/17.
 */

public class Mana extends GameObject {

    private static final Color color = Color.PURPLE;
    private int count;
    private int margin;

    /**
     * constructor
     * @param width  of the object (minions, maybe towers later)
     * @param height of the object
     */
    Mana(int width, int height, int margin, int initial) {
        super(width-2*margin, height-margin);
        this.count = initial;
        this.margin = margin;
    }

    /**
     * called in the cardboard class
     * @param shapeRenderer self explanatory
     */
    void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.rect(getX(), margin, getWidth(), getHeight());
    }

    @Override
    float getX() {
        return (field.width-getWidth())/2;
    }
}
