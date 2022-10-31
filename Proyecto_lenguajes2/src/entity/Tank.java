package entity;

import java.awt.*;
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

		solidArea = new Rectangle(8,16,32,32);

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
		//if the keys a,w,s,d are pressed it will move
		if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
			if (kh.upPressed) {
				DIRECTION = "UP";
				//Y -= SPEED;
			} else if (kh.downPressed) {
				DIRECTION = "DOWN";
				//Y += SPEED;
			} else if (kh.leftPressed) {
				DIRECTION = "LEFT";
				//X -= SPEED;
			} else if (kh.rightPressed) {
				DIRECTION = "RIGHT";
				//X += SPEED;
			}

			// COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			// IF COLLISIONON IS FALSE TANK MOVE
			if (collisionOn == false) {
				switch (DIRECTION) {
					case "UP":
						Y -= SPEED;
						break;
					case "DOWN":
						Y += SPEED;
						break;
					case "LEFT":
						X -= SPEED;
						break;
					case "RIGHT":
						X += SPEED;
						break;
				}
			}
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
