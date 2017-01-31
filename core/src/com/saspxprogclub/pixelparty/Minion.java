package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 1/25/17.
 */

class Minion extends GameObject {

    public static final int nameBuffer = 100;

    private Color color;
    private float delay;
    private boolean owned;
    private TextWrapper name;
    private HealthBar health;
    private int level;

    Minion(Vector2 pos, int width, int height, Color color, boolean owned, String name, int level) {
        super(width, height);
        setPosition(pos);
        this.color = color;
        this.delay = 1.0f;
        this.owned = owned;
        this.name = new TextWrapper(name, pos);
        this.health = new HealthBar(100, color);
        this.level = level;
    }

    public Color getColor(){
        return color;
    }

    void subtractDelay(float dt){
        this.delay -= dt;
    }

    float getDelay(){
        return this.delay;
    }

    boolean isOwned(){
        return owned;
    }

    void subtractHealth(int damage){
        this.health.subtract(damage);
    }

    HealthBar getHealth(){
        return this.health;
    }

    TextWrapper getName(){
        return this.name;
    }

    int getLevel(){
        return this.level;
    }
}
