package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

import static com.saspxprogclub.pixelparty.PixelPartyGame.field;

/***
 * Created by Brandon on 1/25/17.
 */

public class Minion extends GameObject {

    final static String WIZARD = "wizard";
    final static String KNIGHT = "knight";

    static final int nameBuffer = 70;

    private Sprite sprite;
    private float delay = 1.0f;
    private boolean owned;
    private TextWrapper name;
    private HealthBar health;
    private int level;
    private int range;
    private boolean isMoving;
    private int cost;
    private int damage;
    private int velY;

    //MINIONS
    /*sprite info
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
     */

    /**
     * constructor
     * @param pos position of the minion
     * @param color color of health bar
     * @param owned boolean if user owns it, or its from bluetooth transmission
     * @param name name of the minion, final constant in each minion class
     * @param level level of minion, determines damage reduction (armor)
     */
    public Minion(int width, int height, int vely, int range, int cost, int damage, int health, String name, Vector2 pos, Color color, boolean owned, int level) {
        super(width, height+range);
        setPosition(pos);
        this.owned = owned;
        this.name = new TextWrapper(name, pos);
        this.health = new HealthBar(health, color, pos);
        this.level = level;
        this.range = range;
        this.isMoving = false;
        this.cost = cost;
        this.damage = damage;
        this.velY = vely;

        Sprite sprite;
        if(owned){
            sprite = new Sprite(new Texture(Gdx.files.internal(name+"_back.png")));
        } else {
            sprite = new Sprite(new Texture(Gdx.files.internal(name+"_front.png")));
        }
        sprite.scale(field.height/1000f);
        this.sprite = sprite;
    }

    /**
     * @return returns color (will change to return sprite/image)
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     * @param dt subtracts delay until minion can move
     */
    void subtractDelay(float dt){
        this.delay -= dt;
    }

    /**
     * @return gets the current seconds until minion can move after deployed
     */
    float getDelay(){
        return this.delay;
    }

    void setMoving(boolean isMoving){
        this.isMoving = isMoving;
    }

    boolean isMoving(){
        return this.isMoving;
    }

    /**
     * @return true if user owns this minion
     */
    boolean isOwned(){
        return owned;
    }

    /**
     * @param damage int to be subtracted from total health
     *               TODO: make a level damage buffer (armor)
     */
    void subtractHealth(int damage){
        this.health.subtract(damage);
    }

    /**
     * @return returns health bar object of this minion
     */
    HealthBar getHealth(){
        return this.health;
    }

    /**
     * @return returns TextWrapper object of minion name
     */
    TextWrapper getName(){
        return this.name;
    }

    /**
     * @return returns level of minion
     */
    int getLevel(){
        return this.level;
    }

    int getCost() {
        return cost;
    }

    int getDamage(){
        return damage;
    }

    /**
     * sets velocity of minion
     * @param direction of minion
     */
    void setVelocity(int direction) {
        super.setVelocity(0,direction*getVelocityY());
    }

    /**
     * overrides GameObject method
     * @return final velocity of this minion
     */
    @Override
    public float getVelocityY() {
        return velY;
    }

    @Override
    public float getHeight() {
        return super.getHeight()-range;
    }

    @Override
    void updateBounds() {
        if (owned){
            setBounds(getX()-getWidth()/2, getY()+range-getHeight()/2, getWidth(), getHeight()+range);
        } else {
            setBounds(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight()+range);
        }
    }

    boolean update(float dt, float fieldHeight){
        if (!isMoving() && getDelay() <= 0){
            setMoving(true);
            if(isOwned()){
                setVelocity(1);
            } else {
                setVelocity(-1);
            }
        } else {
            subtractDelay(dt);
        }
        integrate(dt);
        updateBounds();
        sprite.setCenter(getX(), getY());

        return (bottom() <= fieldHeight &&
                top() >= PixelPartyGame.verticalBuffer+getHeight() &&
                getHealth().getHealth() >= 0);
    }

    boolean collideWith(GameObject other){
        return getBounds().overlaps(other.getBounds());
    }
}
