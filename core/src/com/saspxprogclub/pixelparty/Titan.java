package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 1/30/17.
 * Test minion class
 */

class Titan extends Minion {

    private final static int width = 30;
    public final static int height = 30;
    private final static String name = "Titan";
    private final static int velY = 10;
    private final static int range = 60;
    private final static int manaCost = 2;

    Titan(Vector2 pos, Color color, boolean owned, int level) {
        super(pos,
                (int)(field.height/width),
                (int)(field.height/height),
                color,
                owned,
                name,
                level,
                (int)(field.height/range),
                manaCost);
    }

    /**
     * overrides GameObject method
     * @return final velocity of this minion
     */
    @Override
    public float getVelocityY() {
        return field.height/velY;
    }
}
