package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Tank;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize = 16; //16x16 tiles
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16 ;
	final int maxScreenRow = 12;
	final int SCREEN_WIDTH = tileSize * maxScreenCol;
	final int SCREEN_HEIGHT = tileSize * maxScreenRow;
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	int FPS = 60;
	Thread gameThread;
	KeyHandler kh = new KeyHandler();
	Tank tank = new Tank(this, kh);
	
	
	
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
			System.out.println("Game thread is running");
			
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
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		tank.draw(g2);
		g2.dispose();
	}
	
}
