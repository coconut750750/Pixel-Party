package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class PixelPartyGame implements ApplicationListener, InputProcessor {

	private int mheight;
	private int mwidth;

	private Rectangle field = new Rectangle();
	private ShapeRenderer shapeRenderer;
	private float fieldTop, fieldBot, fieldLeft, fieldRight;
	private List<Minion> minions;
	private List<Card> cards;
	private List<Integer> cardsNeeded;
	private int laneInterval;
	private int numLanes;
	private int verticalBuffer;
	private int cardBoardWidth;
	private int cardBoardMargin;
	private int cardBorderWidth;
	private int cardSelected;
	private boolean cardDragged;
	final static private int totalNumCards = 4;
	private int cardMargin;
	private BluetoothManager bluetoothManager;
	private Color color;
	private boolean isSingle;
	final static private float cardRegen = 2;
	private float currentRegen;

	public PixelPartyGame(BluetoothManager bluetoothManager, Color color){
		this.isSingle = color == Color.BLACK;
		if (this.isSingle){
			this.bluetoothManager = null;
			this.color = Color.WHITE;
		} else {
			this.bluetoothManager = bluetoothManager;
			this.color = color;
		}
	}
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		field.set(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mheight = (int)(field.height/30.0);
		mwidth = mheight;

		fieldLeft = field.x;
		fieldRight = field.x+field.width;

		fieldBot = field.y;
		fieldTop = field.y+field.height;

		verticalBuffer = (int)((double)field.height/7.0);
		initCards();
		initBluetooth();

		shapeRenderer = new ShapeRenderer();

		minions = new ArrayList<Minion>();

		numLanes = 4;
		laneInterval = (int)(fieldRight/numLanes);

		Gdx.input.setInputProcessor(this);
	}

	private void initCards(){
		cardBoardWidth = (int)(fieldRight/4*3);
		cardBoardMargin =(int)((fieldRight-cardBoardWidth)/2);
		cardMargin = cardBoardWidth/60;
		cardSelected = -1;
		cardDragged = false;

		cards = new ArrayList<Card>();
		cardsNeeded = new ArrayList<Integer>();
		int cardWidth = (cardBoardWidth-cardMargin*(totalNumCards+1))/4;
		cardBorderWidth = cardWidth/60;
		Card.initCards(cardWidth,verticalBuffer-2*cardMargin, cardBoardMargin, cardMargin, cardBorderWidth);

		for (int i = 0; i < 4; i++){
			cards.add(new Card(i, Color.RED));
		}

		currentRegen = cardRegen;
	}

	private void initBluetooth(){
		if (!isSingle){
			Runnable bluetoothRun = bluetoothManager.getListener();
			Thread messageListener = new Thread(bluetoothRun);
			messageListener.start();
		}
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

	private void update(float dt){
		List<Minion> tempMinions = new ArrayList<Minion>();
		for(Minion m : minions){
			if (m.getDelay() <= 0){
				if(m.isOwned()){
					m.setVelocity(0, field.height/10);
				} else {
					m.setVelocity(0, -1*field.height/10);
				}
			} else {
				m.subtractDelay(dt);
			}
			m.integrate(dt);
			m.updateBounds();

			if(m.top() <= field.height && m.bottom() >= verticalBuffer+m.getHeight()){
				tempMinions.add(m);
			}
		}
		minions = tempMinions;

		if (cardsNeeded.size() == 0){
			currentRegen = cardRegen;
		} else {
			currentRegen -= dt;
			if (currentRegen <= 0){
				int i = cardsNeeded.get(0);
				cardsNeeded.remove(0);
				cards.set(i, new Card(i, Color.RED));
				currentRegen = cardRegen;

			}
		}

		if (!isSingle) {
			List<String> messages = bluetoothManager.receive();
			for (String s : messages){
				try{
					String[] sList = s.split(" ");
					int lane = Integer.parseInt(sList[0]);
					int h = Integer.parseInt(sList[2]);
					int y1 = Integer.parseInt(sList[1]);
					int y = h - (y1 - verticalBuffer);
					y = (int)((float)y/h*field.height);


					int midLane = (int)((lane+0.5)*laneInterval);
					Color c;
					if (color == Color.BLUE){
						c = Color.RED;
					} else {
						c = Color.BLUE;
					}
					Minion m = new Minion(midLane, y, mwidth, mheight, c, false);
					minions.add(m);
				} catch (NumberFormatException e){
					Gdx.app.exit();
				}
			}
			//Gdx.app.debug("bluetooth recieved", ""+total);
		}
	}

	private void draw(){
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		drawLanes();
		shapeRenderer.setColor(Color.GREEN);
		drawCardBoard();
		for (Minion m : minions) {
			shapeRenderer.setColor(m.getColor());
			shapeRenderer.rect(m.getX()-m.getWidth()/2,m.getY()-m.getHeight()/2,m.getWidth(), m.getHeight());
		}
		for(Card c : cards){
			if(c == null){
				continue;
			}
			//draw border
			shapeRenderer.setColor(c.getBorderColor());
			shapeRenderer.rect(c.getX(), c.getY(), Card.width, Card.height);
			//draw card
			int borderWidth = c.getBorderWidth();
			shapeRenderer.setColor(c.getColor());
			shapeRenderer.rect(c.getX()+borderWidth, c.getY()+borderWidth, Card.width-2*borderWidth, Card.height-2*borderWidth);
		}
		shapeRenderer.end();
	}

	private void drawLanes(){
		int width = (int)(fieldRight/100);
		for (int i = laneInterval; i <= fieldRight-laneInterval; i+=laneInterval){
			shapeRenderer.rect(i-width,verticalBuffer,width, fieldTop);
		}
		shapeRenderer.rect(0,verticalBuffer,fieldRight, width);
	}

	private void drawCardBoard(){
		shapeRenderer.rect(cardBoardMargin, 0, cardBoardWidth, verticalBuffer);
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
		//(0,0) is top left when drawn

		//Gdx.app.log("touched",""+x);
		y = (int)(field.height - y);

		for (int i = 0; i < cards.size(); i++){
			Card c = cards.get(i);
			if(c == null){
				continue;
			}
			c.setSelected(false);
			if (c.getBounds().contains(x, y)){
				if(c.isSelected()){
					c.setSelected(false);
					cardSelected = -1;
				} else {
					c.setSelected(true);
					cardSelected = c.getPos();
				}
			}
		}

		/*if (cardSelected != -1 && y >= verticalBuffer){
			int lane = x/laneInterval;
			deployMinion(lane, y);
		}*/

		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		y = (int)(field.height-y);

		if (cardSelected != -1){
			cardDragged = false;
			Card c = cards.get(cardSelected);
			if (y >= verticalBuffer){
				int lane = x/laneInterval;
				deployMinion(lane, y);
			} else {
				c.setSelected(false);
				c.setSelected(true);
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int y, int pointer) {
		y = Math.max(y, (int)((field.height-verticalBuffer)/2));
		y = (int)(field.height-y);
		if (cardSelected != -1){
			Card c = cards.get(cardSelected);
			c.move(screenX-Card.width/2, y-Card.height/2);
			cardDragged = true;
		}

		return true;
	}

	private void deployMinion(int lane, int y){
		y = Math.min(y, (int)((field.height-verticalBuffer)/2+verticalBuffer));
		int midLane = (int)((lane+0.5)*laneInterval);
		Minion m = new Minion(midLane, y, mwidth, mheight, color, true);
		minions.add(m);

		cards.remove(cardSelected);
		cards.add(cardSelected, null);
		cardsNeeded.add(cardSelected);
		cardSelected = -1;

		if (!isSingle){
			bluetoothManager.send(""+lane+" "+y+" "+(int)field.height+"~");
		}
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
