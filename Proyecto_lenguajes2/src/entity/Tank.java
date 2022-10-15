package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Tank extends Entity {
	GamePanel gp;
	KeyHandler kh;
	public Tank (GamePanel gp, KeyHandler kh) {
		this.gp = gp;
		this.kh  =kh;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		X = 100;
		Y = 100;
		SPEED = 4;
		DIRECTION = "DOWN";
	}
	public void getPlayerImage() {
		try {
				up1 = ImageIO.read(getClass().getResourceAsStream("/tank/TANK16.png"));
				

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
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
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
		g2.drawImage(image, X, Y, gp.tileSize, gp.tileSize, null);
		
		
	}
}
