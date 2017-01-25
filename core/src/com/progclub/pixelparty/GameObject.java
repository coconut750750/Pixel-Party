package com.progclub.pixelparty;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Brandon on 1/25/17.
 */

public class GameObject {
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Rectangle bounds = new Rectangle();

    protected GameObject(int width, int height){
        bounds.setWidth(width);
        bounds.setHeight(height);
    }

    public Rectangle getBounds() {
        bounds.setX(getX());
        bounds.setY(getY());
        return bounds;
    }

    public void setBounds(Rectangle bounds){
        this.bounds = bounds;
    }

    public void updateBounds(){
        bounds.set(position.x, position.y, bounds.width, bounds.height);
    }

    public float getHeight(){
        return bounds.height;
    }

    public float getWidth(){
        return bounds.width;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
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

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    public void move(float x, float y){
        position.set(x,y);
    }

    public void translate(float x, float y){
        position.add(x, y);
    }

    public void integrate(float dt){
        position.add(velocity.x*dt, velocity.y*dt);
    }

    public float bottom(){
        return bounds.y;
    }

    public float left(){
        return bounds.x;
    }

    public float right(){
        return bounds.x+bounds.width;
    }

    public float top(){
        return bounds.y+bounds.height;
    }
}
