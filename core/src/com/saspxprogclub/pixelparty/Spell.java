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
    private int cost;
    private float duration;
    private String name;
    private Color color;
    private boolean owned;
    private int level;

    public Spell(float radius, int cost, int duration, String name, Vector2 pos, Color color, boolean owned, int level) {
        bounds = new Circle();
        ((Circle)bounds).setRadius(radius);
        this.radius = radius;
        this.cost = cost;
        this.duration = (float)duration;
        this.name = name;
        setPosition(pos);
        this.color = color;
        this.owned = owned;
        this.level = level;
        setBounds(pos.x, pos.y);

    }

    @Override
    void setBounds(float x, float y) {
        ((Circle)bounds).set(x, y, radius);
    }

    @Override
    void updateBounds() {
        setBounds(getX(), getY());
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

    private void subtractDuration(float dt){
        this.duration -= dt;
    }

    public abstract void effect(Minion minion);

    public abstract void end(Minion minion);

    boolean update(float dt){
        if(duration > 0){
            subtractDuration(dt);
            return true;
        }
        else{
            return false;
        }
    }
}
