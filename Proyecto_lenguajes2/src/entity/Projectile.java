package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Projectile extends Entity {

	public Projectile(GamePanel gp) {
		super(gp);

	}
	Entity tank;
	
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity tank) {
		this.X = worldX;
		this.Y = worldY;
		this.DIRECTION = direction;
		this.alive = alive;
		this.tank = tank;
		life = 80;
	}
	public void update() {
		
		life -=1;
		if(life <= 0) {
			alive = false;
		}
		
		switch(DIRECTION) {
			case "UP":
				this.Y -= SPEED;
				break;
			case "DOWN":
				this.Y += SPEED;
				break;
			case "LEFT":
				this.X -= SPEED;
				break;
			case "RIGHT":
				this.X += SPEED;
				break;
		
		}
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		System.out.println(X);
		System.out.println(Y);
		switch(DIRECTION) {
		case "UP":
			image = up1;
			break;
		case "DOWN":
			image = up1;
			break;
		case "LEFT":
			image = up1;
			break;
		case "RIGHT":
			image = up1;
			break;
		}
		g2.drawImage(image, this.X, this.Y, gp.tileSize, gp.tileSize, null);
		
		
	}

}
