package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 2/4/17.
 */

public class Tower extends GameObject {

    private static final int towerHealth = 10000;
    private static final float dimenMultiplier = 1f/2f;

    private static int width;
    private static int height;
    private Color color;

    private HealthBar health;

    /**
     * constructor
     * @param pos lane tower is in
     */
    Tower(Vector2 pos, Color color) {
        super(width, height);
        setPosition(pos);
        this.health = new HealthBar(towerHealth, color, pos);
        this.color = color;
    }

    public static void initTowers(int dimen){
        Tower.width = (int)(dimenMultiplier*dimen);
        Tower.height = (int)(dimenMultiplier*dimen);
    }

    void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.rect(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
        health.draw(height, PixelPartyGame.field.height, color, shapeRenderer);
    }

    /**
     * @param damage int to be subtracted from total health
     */
    void subtractHealth(int damage){
        this.health.subtract(damage);
    }

    HealthBar getHealth(){
        return this.health;
    }
}
