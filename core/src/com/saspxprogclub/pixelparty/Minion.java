package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/***
 * Created by Brandon on 1/25/17.
 */

class Minion extends GameObject {

    static final int nameBuffer = 100;
    private static final int totalHealth = 1000;

    private Color color;
    private float delay = 1.0f;
    private boolean owned;
    private TextWrapper name;
    private HealthBar health;
    private int level;
    private int range;
    private boolean isMoving;
    private int cost;
    private int damage;

    /**
     * constructor
     * @param pos position of the minion
     * @param width width of the minion
     * @param height height of the minion (width and height are both final constants in each minion class)
     * @param color color of minion (will be changed later to sprite/image)
     * @param owned boolean if user owns it, or its from bluetooth transmission
     * @param name name of the minion, final constant in each minion class
     * @param level level of minion, determines damage reduction (armor)
     * @param range range of the minion
     */
    Minion(Vector2 pos, int width, int height, Color color, boolean owned, String name, int level, int range, int cost, int damage) {
        super(width, height+range);
        setPosition(pos);
        this.color = color;
        this.owned = owned;
        this.name = new TextWrapper(name, pos);
        this.health = new HealthBar(totalHealth, color, pos);
        this.level = level;
        this.range = range;
        this.isMoving = false;
        this.cost = cost;
        this.damage = damage;
    }

    /**
     * @return returns color (will change to return sprite/image)
     */
    public Color getColor(){
        return color;
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

        return (bottom() <= fieldHeight &&
                top() >= PixelPartyGame.verticalBuffer+getHeight() &&
                getHealth().getHealth() >= 0);
    }

    boolean collideWith(GameObject other){
        return getBounds().overlaps(other.getBounds());
    }
}
