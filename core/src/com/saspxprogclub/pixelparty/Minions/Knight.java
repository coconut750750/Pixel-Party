package com.saspxprogclub.pixelparty.Minions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.saspxprogclub.pixelparty.Minion;
import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 2/28/17.
 */

public class Knight extends Minion{

    private static int width = (int)(field.height/30f); //inverse
    private static int height = (int)(field.height/20f); //inverse
    private static int vely = (int)(field.height/7f); //inverse
    private static int range = (int)(field.height/30f); //inverse
    private static int cost = 3;
    private static int damage = 15;
    private static int health = 800;
    private final static String name = Minion.KNIGHT;

    /**
     * constructor
     * @param pos   position of the minion
     * @param color color of health bar
     * @param owned boolean if user owns it, or its from bluetooth transmission
     * @param level level of minion, determines damage reduction (armor)
     */
    public Knight(Vector2 pos, Color color, boolean owned, int level) {
        super(width, height, vely, range, cost, damage, health, name, pos, color, owned, level);
    }
}
