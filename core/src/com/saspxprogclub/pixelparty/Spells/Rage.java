package com.saspxprogclub.pixelparty.Spells;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.saspxprogclub.pixelparty.Spell;
import com.saspxprogclub.pixelparty.Minion;


import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 3/18/17.
 */

public class Rage extends Spell {

    private static float radius = field.height/60f; //inverse
    private static int cost = 4;
    private static int duration = 10;
    private static String name = Spell.RAGE;


    public Rage(Vector2 pos, Color color, boolean owned, int level) {
        super(radius, cost, duration, name, pos, color, owned, level);
    }

    public void effect(Minion minion) {
        minion.setDamage((int)(minion.getPermanentData().get(Minion.ATTACKSPEED)/1.5));
        minion.setVelocityY((int)(minion.getPermanentData().get(Minion.VELY)*10));
    }

    public void end(Minion minion){
        minion.setDamage(minion.getPermanentData().get(Minion.ATTACKSPEED));
        minion.setVelocityY(minion.getPermanentData().get(Minion.VELY));
    }
}
