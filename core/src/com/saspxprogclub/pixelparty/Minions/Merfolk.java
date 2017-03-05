package com.saspxprogclub.pixelparty.Minions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.saspxprogclub.pixelparty.Minion;
import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Ryanchang on 3/1/17.
 */

public class Merfolk extends Minion{

    private static int width = (int)(field.height/30f); //inverse
    private static int height = (int)(field.height/20f); //inverse
    private static int vely = (int)(field.height/7f); //inverse
    private static int range = (int)(field.height/30f); //inverse
    private static int cost = 3;
    private static int damage = 500;
    private static int health = 500;
    private final static String name = Minion.MERFOLK;


    public Merfolk(Vector2 pos, Color color, boolean owned, int level) {
        super(width, height, vely, range, cost, damage, health, name, pos, color, owned, level);
    }

    public void mUpdate(float dt) {
    }
    public void mCollide(){

    }

}
