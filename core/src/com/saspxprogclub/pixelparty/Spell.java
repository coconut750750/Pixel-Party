package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 3/2/17.
 */

public abstract class Spell extends GameObject {
    /**
     * constructor
     * @param width  of the object
     * @param height of the object
     */
    private float radius;

    public Spell(int width, int height, float radius, int cost, int duration, String name, Vector2 pos, Color color, boolean owned, int level) {
        bounds = new Circle();
        ((Circle)bounds).setRadius(radius);
        this.radius = radius;
    }

    @Override
    void setBounds(float x, float y) {
        ((Circle)bounds).set(x, y, radius);
    }

    @Override
    void updateBounds() {

    }

    @Override
    float getHeight() {
        return this.radius;
    }

    @Override
    float getWidth() {
        return this.radius;
    }

    @Override
    float bottom() {
        return this.getY()-this.radius;
    }

    @Override
    public float left() {
        return this.getX()-this.radius;
    }

    @Override
    public float right() {
        return this.getX()+this.radius;
    }

    @Override
    float top() {
        return this.getY()+this.radius;
    }
}
