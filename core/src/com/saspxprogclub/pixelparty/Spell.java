package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 3/2/17.
 */

public abstract class Spell extends GameObject {

    protected final static String RAGE = "rage_spell";

    /**
     * constructor
     * @param width  of the object
     * @param height of the object
     */
    private float radius;
    private int cost;
    private float duration;
    private String name;
    private Color color;
    private boolean owned;
    private int level;
    private Sprite sprite;

    public Spell(float radius, int cost, int duration, String name, Vector2 pos, Color color, boolean owned, int level) {
        bounds = new Circle();
        ((Circle)bounds).setRadius(radius);
        this.radius = radius;
        this.cost = cost;
        this.duration = (float)duration;
        this.name = name;
        setPosition(pos);
        this.color = color;
        this.owned = owned;
        this.level = level;
        setBounds(pos.x, pos.y);

        Sprite sprite;
        if(owned){
            sprite = new Sprite(new Texture(Gdx.files.internal(name+"_back.png")));
        } else {
            sprite = new Sprite(new Texture(Gdx.files.internal(name+"_front.png")));
        }
        sprite.scale(field.height/1000f);
        this.sprite = sprite;

    }

    @Override
    void setBounds(float x, float y) {
        ((Circle)bounds).set(x, y, radius);
    }

    @Override
    void updateBounds() {
        setBounds(getX(), getY());
    }

    @Override
    float getHeight() {
        return this.radius;
    }

    @Override
    float getWidth() {
        return this.radius;
    }

    @Override
    float bottom() {
        return this.getY()-this.radius;
    }

    @Override
    public float left() {
        return this.getX()-this.radius;
    }

    @Override
    public float right() {
        return this.getX()+this.radius;
    }

    @Override
    float top() {
        return this.getY()+this.radius;
    }

    private void subtractDuration(float dt){
        this.duration -= dt;
    }

    public abstract void effect(Minion minion);

    public abstract void end(Minion minion);

    public Sprite getSprite(){
        return sprite;
    }

    boolean update(float dt){
        if(duration > 0){
            subtractDuration(dt);
            return true;
        }
        else{
            return false;
        }
    }

    public int getCost(){
        return cost;
    }

    public boolean contains(Minion m){
        Rectangle tempBounds = new Rectangle(getX()-radius, getY()-radius, radius*2, radius*2);
        return tempBounds.contains((Rectangle)(m.getBounds()));
    }
}
