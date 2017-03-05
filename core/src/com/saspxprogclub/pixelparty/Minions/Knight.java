package com.saspxprogclub.pixelparty.Minions;

import com.badlogic.gdx.Gdx;
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
    private static int vely = (int)(field.height/17f); //inverse
    private static int velyFast = (int)(field.height/14f); //inverse
    private static float rDelay = 2f;
    private float runningDelay;
    private static int range = (int)(field.height/60f); //inverse
    private static int cost = 3;
    private static int damage = 200;
    private static int damageFast = 400;
    private static int health = 1000;
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
        runningDelay = rDelay;
    }

    public void mUpdate(float dt) {
        runningDelay -= dt;

        if(runningDelay<=0){
            super.setVelocityY(velyFast);
            super.setDamage(damageFast);
        } else {
            super.setVelocityY(vely);
            super.setDamage(damage);
        }
    }

    public void mCollide(){
        runningDelay = rDelay;
    }

}
