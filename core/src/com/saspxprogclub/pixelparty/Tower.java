package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
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

    private float damageTimeBuffer = 1f;

    /**
     * constructor
     * @param pos of the tower
     * @param color of the tower
     */
    Tower(Vector2 pos, Color color) {
        bounds = new Rectangle();
        ((Rectangle)bounds).setWidth(width);
        ((Rectangle)bounds).setHeight(height);
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
    void subtractHealth(int damage, float dt){
        damageTimeBuffer -= dt;
        if(damageTimeBuffer <= 0){
            damageTimeBuffer = 1f;
            this.health.subtract(damage);
            this.alive = this.health.getHealth() >= 0;
        }

    }

    HealthBar getHealth(){
        return this.health;
    }

    boolean isAlive(){
        return alive;
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
        return ((Rectangle)bounds).getHeight();
    }

    @Override
    float getWidth() {
        return ((Rectangle)bounds).width;
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
