package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class PixelPartyGame implements ApplicationListener, InputProcessor {

	private Rectangle field = new Rectangle();
	private ShapeRenderer shapeRenderer;
	private float fieldTop, fieldBot, fieldLeft, fieldRight;
	private List<Minion> minions;
	private int laneInterval;
	private int numLanes;
	private int verticalBuffer;
	private BluetoothManager bluetoothManager;

	public PixelPartyGame(BluetoothManager bluetoothManager){
		this.bluetoothManager = bluetoothManager;
	}

	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		field.set(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fieldLeft = field.x;
		fieldRight = field.x+field.width;

		fieldBot = field.y;
		fieldTop = field.y+field.height;

		verticalBuffer = (int)((double)field.height/10.0);
		shapeRenderer = new ShapeRenderer();

		minions = new ArrayList<Minion>();

		numLanes = 4;
		laneInterval = (int)(fieldRight/numLanes);

		Gdx.input.setInputProcessor(this);

		Runnable bluetoothRun = bluetoothManager.getListener();
		Thread messageListener = new Thread(bluetoothRun);
		messageListener.start();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getRawDeltaTime();
		update(dt);
		draw();
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	public void update(float dt){
		List<Minion> tempMinions = new ArrayList<Minion>();
		for(Minion m : minions){
			m.integrate(dt);
			m.updateBounds();

			if(m.bottom() >= fieldBot){
				tempMinions.add(m);
			}
		}
		minions = tempMinions;

		List<String> messages = bluetoothManager.receive();
		String total = "";
		for (String s : messages){
			total += s+" ";
		}
		Gdx.app.debug("bluetooth recieved", ""+total);
	}

	public void draw(){
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for (Minion m : minions) {
			shapeRenderer.rect(m.getX()-m.getWidth()/2,m.getY()-m.getHeight()/2,m.getWidth(), m.getHeight());
		}

		drawLanes(numLanes);
		shapeRenderer.end();
	}

	public void drawLanes(int num){
		int width = (int)(fieldRight/100);
		for (int i = laneInterval; i <= fieldRight-laneInterval; i+=laneInterval){
			shapeRenderer.rect(i-width,verticalBuffer,width, fieldTop);
		}
		shapeRenderer.rect(0,verticalBuffer,fieldRight, width);

	}

	@Override
	public void dispose () {

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		//(0,0) is top left, not bot left
		Gdx.app.debug("adfasdfasdf", ""+x);
		float lane = x/laneInterval;
		int midLane = (int)((lane+0.5)*laneInterval);
		Minion m = new Minion(midLane, verticalBuffer+50, 50, 50);
		m.setVelocity(0, field.height/10);
		minions.add(m);

		bluetoothManager.send(""+x);

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
