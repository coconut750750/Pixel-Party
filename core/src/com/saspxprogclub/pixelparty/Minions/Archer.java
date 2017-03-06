package com.saspxprogclub.pixelparty.Minions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.saspxprogclub.pixelparty.Minion;

import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by James on 3/1/17.
 */

public class Archer extends Minion{

    private static int width = (int)(field.height/30f); //inverse
    private static int height = (int)(field.height/20f); //inverse
    private static int vely = (int)(field.height/12f); //inverse
    private static int range = (int)(field.height/20f); //inverse
    private static int cost = 4;
    private static int damage = 30;
    private static float attackSpeed = 2f;
    private static int health = 400;
    private final static String name = "archer";

    public Archer(Vector2 pos, Color color, boolean owned, int level) {
        super(width, height, vely, range, cost, damage, attackSpeed, health, name, pos, color, owned, level);
    }

    @Override
    public void mUpdate(float dt) {

    }

    @Override
    public void mCollide() {

    }
}

/**sprite info
 field.height/100f = 10px height
 field.height/90f = 11px height
 field.height/80f = 13px height
 field.height/70f = 14px height
 field.height/60f = 17px height
 field.height/50f = 20px height
 field.height/40f = 25px height
 field.height/30f = 33px height
 field.height/20f = 50px height
 field.height/10f = 100px height
 **/