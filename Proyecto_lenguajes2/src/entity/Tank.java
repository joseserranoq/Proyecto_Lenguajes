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
		this.type = 0;
		solidArea = new Rectangle(8,16,32,32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		this.belongs = "Tank";


		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		X = 100;
		Y = 100;
		life = maxLife;
		SPEED = 2;
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
			if (kh.upPressed && freezed == false) {
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
			int objectIndex = gp.cChecker.checkObject(this, true);

			pickupObject(objectIndex);
			int entityIndex = gp.cChecker.checkEntity(this, gp.monsterList);
			int bulletIndex = gp.cChecker.checkEntity(this, gp.projectileList);
			interactNPCIndex(entityIndex);
			entityIndex = gp.cChecker.checkEntity(this, gp.entityList);
			interactNPCIndex(entityIndex);
			contactBullet(bulletIndex);
			//contactMonster(entityIndex);
			// IF COLLISIONON IS FALSE TANK MOVE
			if (collisionOn == false && freezed == false) {
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
			projectile.set(X, Y, DIRECTION, true, this, 1);
			gp.projectileList.add(projectile);
		}

		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter > 60){
				invincible = false;
				invincibleCounter = 0;
			}
		}

		if(freezed == true){
			freezeCounter++;
			if(freezeCounter > 120){
				freezed = false;
				freezeCounter = 0;
			}
		}

	}

	private  void contactMonster(int index) {
		if(index != 999){
			if(invincible == false){
				life -= 1;
				invincible = true;
			}
		}
	}

	private void contactBullet(int bulletIndex) {
		if(bulletIndex != 999) {
			if(gp.projectileList.get(bulletIndex).name == "FreezeBullet" && freezed == false){
				freezed = true;
				gp.projectileList.get(bulletIndex).alive = false;
				return;
			}
			if(invincible == false){
				life -= gp.projectileList.get(bulletIndex).damage;
				invincible = true;
				gp.projectileList.get(bulletIndex).alive = false;
				return;
			}

		}
	}

	private void interactNPCIndex(int index ) {
		if(index != 999){


		}
	}

	public void pickupObject(int index){
		if(index != 999){
			String objName = gp.entityList.get(index).name;
			switch (objName){
				case "BronzeCoin":
					gp.entityList.remove(index);
					gp.tank.SPEED += 1;
					break;
				case "C4":
					gp.entityList.remove(index);
					gp.tank.invincible = true;
					break;
			}

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
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
		}
		g2.drawImage(image, X, Y, gp.tileSize, gp.tileSize, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		
		
	}
}
