package com.saspxprogclub.pixelparty.Minions;

/**
 * Created by Ryanchang on 3/8/17.
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.saspxprogclub.pixelparty.Minion;
import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

public class Spider extends Minion{
    private static int width = (int)(field.height/30f); //inverse
    private static int height = (int)(field.height/20f); //inverse
    private static int vely = (int)(field.height/7f); //inverse
    private static int range = (int)(field.height/40f); //inverse
    private static int cost = 5;
    private static int damage = 20;
    private static float attackSpeed = 5f;
    private static int health = 1000;
    private final static String name = Minion.SPIDER;

    /**
     * constructor
     * @param pos   position of the minion
     * @param color color of health bar
     * @param owned boolean if user owns it, or its from bluetooth transmission
     * @param level level of minion, determines damage reduction (armor)
     */

    public Spider(Vector2 pos, Color color, boolean owned, int level) {
        super(width, height, vely, range, cost, damage, attackSpeed, health, name, pos, color, owned, level);
    }
    public void mUpdate(float dt) {

    }
    public void mCollide(){

    }
}