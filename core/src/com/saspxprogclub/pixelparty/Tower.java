package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 2/4/17.
 */

public class Tower extends GameObject {

    private static final int towerHealth = 10000;
    private static final float dimenMultiplier = 2f/3f;

    private static int width;
    private static int height;
    private static Color color;

    private HealthBar health;

    /**
     * constructor
     * @param pos lane tower is in
     */
    Tower(Vector2 pos) {
        super(width, height);
        setPosition(pos);
        this.health = new HealthBar(towerHealth, color, pos);
    }

    public static void initTowers(int dimen, Color color){
        Tower.width = (int)(dimenMultiplier*dimen);
        Tower.height = (int)(dimenMultiplier*dimen);
        Tower.color = color;
    }

    void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
    }
}
