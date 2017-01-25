package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class PixelPartyGame extends ApplicationAdapter {

	private Rectangle field = new Rectangle();
	private ShapeRenderer shapeRenderer;
	private float fieldTop, fieldBot, fieldLeft, fieldRight;
	private Minion minion;
	private float totalWidth, totalHeight;

	
	@Override
	public void create () {
		totalWidth = Gdx.graphics.getWidth();
		totalHeight = Gdx.graphics.getHeight();
		field.set(0,0,totalWidth, totalHeight);
		fieldLeft = field.x;
		fieldRight = field.x+field.width;
		fieldBot = field.y;
		fieldTop = field.y+field.height;
		shapeRenderer = new ShapeRenderer();

		minion = new Minion(50,50);
		minion.move(field.x + (field.width - minion.getWidth()) / 2, field.y + (field.height - minion.getHeight()) / 2);

	}

	@Override
	public void render () {
		draw();
	}

	public void draw(){
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(minion.getX(),minion.getY(),minion.getWidth(), minion.getHeight());
		drawLanes(4);
		shapeRenderer.end();
	}

	public void drawLanes(int num){
		int width = (int)(totalWidth/100);
		int interval = (int)(totalWidth/num);
		for (int i = interval; i <= totalWidth-interval; i+=interval){
			shapeRenderer.rect(i-width,0,width, totalHeight);
		}

	}

	@Override
	public void dispose () {

	}
}
