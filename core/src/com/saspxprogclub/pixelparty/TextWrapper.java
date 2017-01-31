package com.saspxprogclub.pixelparty;

/***
 * Created by Brandon on 1/30/17.
 */

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TextWrapper{
    private String text;
    private Vector2 position;
    private int width;
    private int height;

    private static final GlyphLayout layout = new GlyphLayout();

    public TextWrapper(String text, Vector2 position){
        this.text = text;
        this.position = position;
    }

    public void draw(SpriteBatch sp,BitmapFont fnt){
        layout.setText(fnt, text);
        float width = layout.width;
        float height = layout.height;
        fnt.draw(sp,text,position.x-width/2, position.y+height/2);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
