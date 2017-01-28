package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Brandon on 1/25/17.
 */

public class Minion extends GameObject {

    private Color color;

    Minion(int x, int y, int width, int height, Color color) {
        super(width, height);
        move(x, y);
        this.color = color;
    }

    public Color getColor(){
        return color;
    }
}
