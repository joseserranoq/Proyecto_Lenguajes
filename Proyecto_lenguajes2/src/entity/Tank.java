package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.Bullet;

public class Tank extends Entity {
	GamePanel gp;
	KeyHandler kh;
	public Tank (GamePanel gp, KeyHandler kh) {
		super(gp);
		this.gp = gp;
		this.kh  =kh;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		X = 100;
		Y = 100;
		SPEED = 3;
		DIRECTION = "DOWN";
		projectile = new Bullet(gp);
	}
	public void getPlayerImage() {
		try {
				up1 = ImageIO.read(getClass().getResourceAsStream("/tank/TANK.png"));
				down = ImageIO.read(getClass().getResourceAsStream("/tank/TANKD.png"));
				left = ImageIO.read(getClass().getResourceAsStream("/tank/TANKL.png"));
				right = ImageIO.read(getClass().getResourceAsStream("/tank/TANKR.png"));
				

		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public void update() {
		if(kh.upPressed) {
			Y -= SPEED;
			DIRECTION = "UP";
		}else if(kh.downPressed) {
			Y += SPEED;
			DIRECTION = "DOWN";
		}else if(kh.leftPressed) {
			X -= SPEED;
			DIRECTION = "LEFT";
		}else if(kh.rightPressed) {
			X += SPEED;
			DIRECTION = "RIGHT";
		}
		
		if(kh.fPressed && projectile.alive == false) {
			projectile.set(X, Y, DIRECTION, true, this);
			gp.projectileList.add(projectile);
		}

		if(kh.spacePressed){
			this.SPEED = 7;
		}else {
			this.SPEED = 3;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(DIRECTION) {
		case "UP":
			image = up1;
			break;
		case "DOWN":
			image = down;
			break;
		case "LEFT":
			image = left;
			break;
		case "RIGHT":
			image = right;
			break;
		}
		g2.drawImage(image, X, Y, gp.tileSize, gp.tileSize, null);
		
		
	}
}
