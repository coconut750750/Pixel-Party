package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;

/***
 * Created by Brandon on 1/25/17.
 */

class Minion extends GameObject {

    private Color color;
    private float delay;
    private boolean owned;

    Minion(int x, int y, int width, int height, Color color, boolean owned) {
        super(width, height);
        move(x, y);
        this.color = color;
        this.delay = 1.0f;
        this.owned = owned;
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
}
