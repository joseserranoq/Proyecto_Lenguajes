package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;

import entity.*;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize = 16; //16x16 tiles
	final int scale = 3;
	public int actual_level = 0;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16 ;
	public final int maxScreenRow = 12;
	public final int SCREEN_WIDTH = tileSize * maxScreenCol;
	public int GAME_STATE;
	public final int  TITLE_STATE = 0;
	public final int PLAY_STATE = 1;
	public final int PAUSE_STATE = 2;
	public int SCREEN_HEIGHT = tileSize * maxScreenRow;
	public UI ui = new UI(this);
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	int FPS = 60;
	TileManager tileM = new TileManager(this);
	Thread gameThread;
	KeyHandler kh = new KeyHandler(this);

	public CollisionChecker cChecker = new CollisionChecker(this);
	public Tank tank = new Tank(this, kh);
	//public FastTank tank = new FastTank(this, kh);
	//ExplosiveTank tank = new ExplosiveTank(this, kh);
	public AssetSetter aSetter = new AssetSetter(this);
	public ArrayList<Entity> entityList = new ArrayList<>();

	public ArrayList<Entity> monsterList = new ArrayList<>();
	
	public ArrayList<Entity> projectileList = new ArrayList<>(); 
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(kh);
		this.setFocusable(true);
		
	}

	public void setupGame(){
		aSetter.setObject();
		aSetter.setMonster();
	}
	
	public void startGameThread () {
		GAME_STATE = TITLE_STATE;
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		while(gameThread != null) {
		
			update();
			repaint();
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000000;
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);

				nextDrawTime += drawInterval;
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
		
	}
	public void update() {
		if(GAME_STATE == PLAY_STATE) {
			tank.update();
			if(tank.life <= 0){
				GAME_STATE = TITLE_STATE;
				tank.life = tank.maxLife;
				tank.speed = 2;
				projectileList.clear();
				entityList.clear();
				monsterList.clear();
				aSetter.level1 = new ArrayList<Pair>(Arrays.asList( new Pair(4,4), new Pair(5,5), new Pair(6,6) ));
				aSetter.level2 = new ArrayList<Pair>(Arrays.asList( new Pair(7,3), new Pair(7,4), new Pair(7,5) ));
				aSetter.level3 = new ArrayList<Pair>(Arrays.asList( new Pair(2,5), new Pair(3,5), new Pair(4,5) ));
				actual_level = 0;
				setupGame();
			}
			if(actual_level == 3){
				GAME_STATE = TITLE_STATE;
				tank.life = tank.maxLife;
				tank.speed = 2;
				projectileList.clear();
				entityList.clear();
				monsterList.clear();
				actual_level = 0;
				aSetter.level1 = new ArrayList<Pair>(Arrays.asList( new Pair(4,4), new Pair(5,5), new Pair(6,6) ));
				aSetter.level2 = new ArrayList<Pair>(Arrays.asList( new Pair(7,3), new Pair(7,4), new Pair(7,5) ));
				aSetter.level3 = new ArrayList<Pair>(Arrays.asList( new Pair(2,5), new Pair(3,5), new Pair(4,5) ));
				setupGame();
			}

			if(monsterList.isEmpty() && entityList.isEmpty() && actual_level != 3){

				actual_level+=1;
				tank.life = tank.maxLife;
				tileM.loadMap(actual_level);
				setupGame();

			}


			for (int i = 0; i < monsterList.size(); i++) {
				if(monsterList.get(i) != null){

					monsterList.get(i).update();
				}
			}

			for (int i = 0; i < entityList.size(); i++) {
				if(entityList.get(i) != null){
					entityList.get(i).update();
				}
			}

			for (int i = 0; i < projectileList.size(); i++) {

				if (projectileList.get(i) != null) {
					if (projectileList.get(i).alive) {
						projectileList.get(i).update();
					}
					if (!projectileList.get(i).alive) {
						projectileList.remove(i);
					}
				}
			}

		}else if(GAME_STATE == PAUSE_STATE){

		}

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//entityList.add(tank);
		if(GAME_STATE == PAUSE_STATE){
			ui.draw(g2);
		}
		if(GAME_STATE == TITLE_STATE){
			ui.draw(g2);
		}
		if(GAME_STATE == PLAY_STATE){

			tileM.draw(g2); //draw the tiles
			tank.draw(g2);
			ui.draw(g2);

			for(int i = 0; i < projectileList.size(); i++) {
				projectileList.get(i).draw(g2);
			}

			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			for(int i = 0; i < monsterList.size(); i++) {
				monsterList.get(i).draw(g2);
			}

			g2.dispose();
		}


	}
	
}
