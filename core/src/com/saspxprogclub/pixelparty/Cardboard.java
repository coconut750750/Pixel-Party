package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 2/4/17.
 */

public class Cardboard extends GameObject {

    private static final Color color = Color.GREEN;
    private Mana mana;

    /**
     * constructor
     * @param width  of the object (minions, maybe towers later)
     * @param height of the object
     */
    Cardboard(int width, int height, Mana mana) {
        super(width, height);
        super.setVelocity(0,0);
        this.mana = mana;
    }

    void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.rect(getX(), 0, getWidth(), getHeight());
        mana.draw(shapeRenderer);
    }

    @Override
    float getX() {
        return (field.width-getWidth())/2;
    }
}
