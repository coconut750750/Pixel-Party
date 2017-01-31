package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 1/30/17.
 */

public class HealthBar{

    public final static int width = 30;
    public final static int height = 150;

    int total;
    int current;
    Color color;

    HealthBar(int total, Color color){
        this.total = total;
        this.color = color;
        this.current = total;
    }


    public int getSplit(){
        return (int)(width*((float)current/(float)total));
    }

    public Color getColor(){
        return color;
    }

    void subtract(int damage){
        current -= damage;
    }

    int getHealth(){
        return current;
    }

}
