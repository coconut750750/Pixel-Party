package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
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

    public Spell(int width, int height, int radius, int cost, int duration, String name, Vector2 pos, Color color, boolean owned, int level) {
        super(width, height);
    }
}
