package com.saspxprogclub.pixelparty;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/*ideas
	We can alter
	minions (ranged, melee)
	spells(rage spells)
	towers
	different terrains(water,mountain,swamp) so different creatures interact differently with each terrain.
*/


public class PixelPartyGame implements ApplicationListener, InputProcessor {

	public static int verticalBuffer;
	public static Rectangle field = new Rectangle();
	private ShapeRenderer shapeRenderer;
	private float fieldTop, fieldBot, fieldLeft, fieldRight;
	private List<Minion> minions;
	private List<Minion> enemyMinions;
	private List<Tower> towers;
	private List<Tower> enemyTowers;

	private List<Card> cards;
	private List<Integer> cardsNeeded;
	private int laneInterval;
	private final int numLanes = 5;
	private Cardboard cardboard;
	private Mana mana;
	private final int startMana = 5;
	private int cardBorderWidth;
	private float cardY;
	private int cardSelected;
	final static private int totalNumCards = 4;
	private int cardMargin;
	private BluetoothManager bluetoothManager;
	private Color color;
	private boolean isSingle;
	final static private float cardRegen = 2;
	private float currentRegen;
	private BitmapFont font;
	private SpriteBatch spriteBatch;
	private List<String> availiable = new ArrayList<String>();

	public PixelPartyGame(BluetoothManager bluetoothManager, Color color){
		this.isSingle = color == Color.BLACK;
		if (this.isSingle){
			this.bluetoothManager = null;
			this.color = Color.BLACK;
		} else {
			this.bluetoothManager = bluetoothManager;
			this.color = color;
		}
	}
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		field.set(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		fieldLeft = field.x;
		fieldRight = field.x+field.width;

		fieldBot = field.y;
		fieldTop = field.y+field.height;

		verticalBuffer = (int)(field.height/6f);
		laneInterval = (int)(fieldRight/numLanes);
		initCards();
		initBluetooth();
		initTowers();

		shapeRenderer = new ShapeRenderer();

		minions = new ArrayList<Minion>();
		enemyMinions = new ArrayList<Minion>();

		Gdx.input.setInputProcessor(this);
		font = new BitmapFont();
		spriteBatch = new SpriteBatch();
		font.getData().setScale(1.5f);
	}

	private void initCards(){

		availiable = Arrays.asList(Minion.TITAN, Minion.KNIGHT);

		int cardboardWidth = (int)(fieldRight/4*3);
		cardMargin = cardboardWidth/60;

		mana = new Mana(cardboardWidth, (int)(verticalBuffer/5.0), cardMargin, startMana);
		cardboard = new Cardboard(cardboardWidth, verticalBuffer, mana);

		cardSelected = -1;
		cardY = mana.getHeight()+cardMargin;

		cards = new ArrayList<Card>();
		cardsNeeded = new ArrayList<Integer>();
		int cardWidth = (int)((cardboard.getWidth()-cardMargin*(totalNumCards+1))/totalNumCards);
		cardBorderWidth = cardWidth/60;
		Card.initCards(cardWidth,(int)(verticalBuffer-2*cardMargin-cardY), (int)cardboard.getX(), cardMargin, cardBorderWidth);

		for (int i = 0; i < 4; i++){
			String m = availiable.get(new Random().nextInt(availiable.size()));
			cards.add(new Card(i, (int)cardY, Color.RED, m));
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

	private void initTowers(){
		towers = new ArrayList<Tower>();
		enemyTowers = new ArrayList<Tower>();
		Tower.initTowers(laneInterval);
		Color c;
		if (color == Color.BLUE){
			c = Color.RED;
		} else {
			c = Color.BLUE;
		}
		for(int i = 0; i < numLanes; i++){
			towers.add(new Tower(new Vector2((int)((i+0.5)*laneInterval), verticalBuffer+(int)(laneInterval/2f)), color));
			enemyTowers.add(new Tower(new Vector2((int)((i+0.5)*laneInterval), field.height-(int)(laneInterval/2f)), c));
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
			if(m.update(dt, field.height)){
				tempMinions.add(m);
			}
			for(Minion other : enemyMinions){
				if(m.collideWith(other)){
					m.integrate(-1*dt);
					other.integrate(-1*dt);
					m.subtractHealth(other.getDamage());
					other.subtractHealth(m.getDamage());
				}
			}
			for(Tower tower : enemyTowers){
				if(m.collideWith(tower) && tower.isAlive()){
					m.integrate(-1*dt);
					tower.subtractHealth(m.getDamage());
				}
			}
		}
		minions = tempMinions;
		tempMinions = new ArrayList<Minion>();
		for(Minion m : enemyMinions){
			if(m.update(dt, field.height)){
				tempMinions.add(m);
			}
			for(Tower tower : towers){
				if(m.collideWith(tower) && tower.isAlive()){
					m.integrate(-1*dt);
					tower.subtractHealth(m.getDamage());
				}
			}
		}
		enemyMinions = tempMinions;

		if (cardsNeeded.size() == 0){
			currentRegen = cardRegen;
		} else {
			currentRegen -= dt;
			if (currentRegen <= 0){
				int i = cardsNeeded.get(0);
				cardsNeeded.remove(0);
				String m = availiable.get(new Random().nextInt(availiable.size()));

				cards.set(i, new Card(i, (int)cardY, Color.RED, m));
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
					String name = sList[3];
					int y1 = Integer.parseInt(sList[1]);
					float y = 1f-(float)y1/(float)h;
					y = y*(field.height-verticalBuffer);
					y = y + verticalBuffer;


					int midLane = (int)((lane+0.5)*laneInterval);
					Color c;
					if (color == Color.BLUE){
						c = Color.RED;
					} else {
						c = Color.BLUE;
					}
					HashMap<String, Integer> data = Minion.minions.get(name);
					Minion m = new Minion(data, name, new Vector2(midLane, y), c, false, 1);
					enemyMinions.add(m);
				} catch (NumberFormatException e){
					Gdx.app.exit();
				}
			}
		}

		mana.update(dt);
	}

	private void draw(){
		Gdx.gl.glClearColor(255f,255f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		drawLanes();
		cardboard.draw(shapeRenderer);
		drawMinions();
		drawTowers();
		drawCards();
		shapeRenderer.end();
	}

	private void drawLanes(){
		shapeRenderer.setColor(Color.WHITE);
		int width = (int)(fieldRight/100);
		for (int i = laneInterval; i <= fieldRight-laneInterval; i+=laneInterval){
			shapeRenderer.rect(i-width,verticalBuffer,width, fieldTop);
		}
		shapeRenderer.rect(0,verticalBuffer,fieldRight, width);
	}

	private void drawMinions(){
		spriteBatch.begin();
		List<Minion> temp = new ArrayList<Minion>();
		temp.addAll(minions);
		temp.addAll(enemyMinions);
		for (Minion m : temp) {
			shapeRenderer.setColor(m.getColor());
			int w = (int)(m.getWidth());
			int h = (int)(m.getHeight());
			shapeRenderer.rect(m.getX()-w/2,m.getY()-h/2,w, h);
			//TextWrapper name = m.getName();
			//name.setPosition(new Vector2(m.getX(), m.getY()+h/2+(field.height/Minion.nameBuffer)));
			//name.draw(spriteBatch, font);

			HealthBar health = m.getHealth();
			health.draw(h, field.height, m.getColor(), shapeRenderer);

		}
		spriteBatch.end();
	}

	private void drawTowers(){
		shapeRenderer.setColor(color);
		for(Tower t: towers){
			t.draw(shapeRenderer);
		}
		for(Tower t : enemyTowers){
			t.draw(shapeRenderer);
		}
	}

	private void drawCards(){
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
		//(0,0) is bot left when drawn

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

		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		y = (int)(field.height-y);

		if (cardSelected != -1){
			Card c = cards.get(cardSelected);
			if (y > verticalBuffer){
				int lane = x/laneInterval;
				deployMinion(lane, y, c);
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
		}

		return true;
	}

	private void deployMinion(int lane, int y, Card c){
		y = Math.min(y, (int)((field.height-verticalBuffer)/2+verticalBuffer));
		int midLane = (int)((lane+0.5)*laneInterval);
		HashMap<String, Integer> data = Minion.minions.get(c.getMinionName());
		Minion m = new Minion(data, c.getMinionName(), new Vector2(midLane, y), color, true, 1);

		int cost = m.getCost();
		if (mana.getCount() < cost){
			Card c1 = cards.get(cardSelected);
			c1.setSelected(false);
			c1.setSelected(true);
			return;
		} else {
			mana.subtractCount(cost);
		}

		minions.add(m);

		cards.remove(cardSelected);
		cards.add(cardSelected, null);
		cardsNeeded.add(cardSelected);
		cardSelected = -1;

		if (!isSingle){
			bluetoothManager.send(""+lane+" "+
					(y-verticalBuffer)+" "+
					((int)field.height-verticalBuffer)+" "+
					c.getMinionName()+"~");
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
