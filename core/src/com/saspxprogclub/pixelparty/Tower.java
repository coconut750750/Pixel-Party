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
    private boolean alive;

    /**
     * constructor
     * @param pos of the tower
     * @param color of the tower
     */
    Tower(Vector2 pos, Color color) {
        super(width, height);
        Vector2 nPos = new Vector2(pos.x - width/2, pos.y - height/2);
        setPosition(nPos);
        this.health = new HealthBar(towerHealth, color, pos);
        this.color = color;
        alive = true;
    }

    public static void initTowers(int dimen){
        Tower.width = (int)(dimenMultiplier*dimen);
        Tower.height = (int)(dimenMultiplier*dimen);
    }

    void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        health.draw(height, PixelPartyGame.field.height, color, shapeRenderer);
    }

    /**
     * @param damage int to be subtracted from total health
     */
    void subtractHealth(int damage){
        this.health.subtract(damage);
        this.alive = this.health.getHealth() >= 0;
    }

    HealthBar getHealth(){
        return this.health;
    }

    boolean isAlive(){
        return alive;
    }
}
