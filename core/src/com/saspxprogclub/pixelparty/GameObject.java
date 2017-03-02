package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;


/***
 * Created by Brandon on 1/25/17.
 */

abstract class GameObject {
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    Shape2D bounds;

    /**
     * @return object of the bounds
     */
    Shape2D getBounds(){
        updateBounds();
        return bounds;
    }

    /**
     * sets the bounds of the object
     */
    abstract void setBounds(float x, float y);

    /**
     * updates bounds if object was moved
     */
    abstract void updateBounds();

    /**
     * @return height of object
     */
    abstract float getHeight();

    /**
     * @return width of object
     */
    abstract float getWidth();

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    float getX(){
        return position.x;
    }

    float getY(){
        return position.y;
    }

    public float getVelocityX(){
        return velocity.x;
    }

    public float getVelocityY(){
        return velocity.y;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getTotalVelocity(){
        return (float)Math.sqrt(getVelocityX()*getVelocityX()+getVelocityY()*getVelocityY());
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    void move(float x, float y){
        position.set(x,y);
    }

    public void translate(float x, float y){
        position.add(x, y);
    }

    void integrate(float dt){
        position.add(velocity.x*dt, velocity.y*dt);
    }

    abstract float bottom();

    abstract public float left();

    abstract public float right();

    abstract float top();
}
