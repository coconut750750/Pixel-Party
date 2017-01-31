package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 1/30/17.
 */

public class Titan extends Minion {

    private final static int width = 30;
    private final static int height = 30;
    private final static String name = "Titan";

    Titan(Vector2 pos, Color color, boolean owned, int level) {
        super(pos, width, height, color, owned, name, level);
    }
}
