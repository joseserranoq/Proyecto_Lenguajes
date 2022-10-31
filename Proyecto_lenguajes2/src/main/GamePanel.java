package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import entity.Entity;
import entity.Projectile;
import entity.Tank;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize = 16; //16x16 tiles
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16 ;
	public final int maxScreenRow = 12;
	public final int SCREEN_WIDTH = tileSize * maxScreenCol;
	public int SCREEN_HEIGHT = tileSize * maxScreenRow;
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	int FPS = 60;
	TileManager tileM = new TileManager(this);
	Thread gameThread;
	KeyHandler kh = new KeyHandler();
	public CollisionChecker cChecker = new CollisionChecker(this);
	Tank tank = new Tank(this, kh);
	public ArrayList<Entity> entityList = new ArrayList<>();
	
	public ArrayList<Entity> projectileList = new ArrayList<>(); 
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(kh);
		this.setFocusable(true);
		
	}
	
	public void startGameThread () {
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
		tank.update();
		
		for (int i = 0; i < projectileList.size(); i++) {
			System.out.println(projectileList.get(i));
			if(  projectileList.get(i) != null) {
				if(projectileList.get(i).alive) {
					projectileList.get(i).update();
				}
				if(!projectileList.get(i).alive) {
					projectileList.remove(i);
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//entityList.add(tank);
		tileM.draw(g2); //draw the tiles
		tank.draw(g2);
		for(int i = 0; i < projectileList.size(); i++) {
			System.out.println(projectileList);
			projectileList.get(i).draw(g2);
		}

		g2.dispose();
	}
	
}
