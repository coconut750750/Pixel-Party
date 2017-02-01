package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 1/30/17.
 * Test minion class
 */

class Titan extends Minion {

    private final static int width = 30;
    private final static int height = 30;
    private final static String name = "Titan";
    private final static int velY = 10;

    Titan(Vector2 pos, Color color, boolean owned, int level) {
        super(pos, width, height, color, owned, name, level);
    }

    /**
     * overrides gameobject method
     * @return final velocity of this minion
     */

    @Override
    public float getVelocityY() {
        return velY;
    }
}
