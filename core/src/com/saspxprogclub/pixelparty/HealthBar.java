package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 1/30/17.
 */

class HealthBar{

    public final static int width = 30;
    public final static int height = 150;

    private int total;
    private int current;
    private Color color;
    private Vector2 pos;

    HealthBar(int total, Color color, Vector2 pos){
        this.total = total;
        this.color = color;
        this.current = total;
        this.pos = pos;
    }


    float getSplit(){
        return(float)current/(float)total;
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

    void draw(int height, float fieldHeight, Color c, ShapeRenderer shapeRenderer){
        int y = (int)(pos.y+height/2+(fieldHeight/Minion.nameBuffer));
        int x = (int)(pos.x-fieldHeight/HealthBar.width/2);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(x, y, fieldHeight/HealthBar.width, fieldHeight/HealthBar.height);

        shapeRenderer.setColor(c);
        shapeRenderer.rect(x, y, fieldHeight/HealthBar.width*getSplit(), fieldHeight/HealthBar.height);
    }

}
