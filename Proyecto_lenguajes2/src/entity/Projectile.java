package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Projectile extends Entity {

	public Projectile(GamePanel gp) {
		super(gp);




	}
	Entity tank;
	
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity tank) {
		this.X = worldX ;
		this.Y = worldY ;
		this.type = 1;
		this.DIRECTION = direction;
		this.alive = alive;
		this.tank = tank;
		life = 80;
	}
	public void update() {



		if(tank == gp.tank){
			int entityIndex = gp.cChecker.checkEntity(this, gp.monsterList);
			if(entityIndex != 999){
				System.out.println("Le dió");
				gp.monsterList.remove(entityIndex);
				alive = false;
			}
		}



		
		life -=1;
		if(life <= 0) {
			alive = false;
		}



		collisionOn = false;
		gp.cChecker.checkTile(this);

		//gp.cChecker.checkPlayer(this );
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
		} else {
			this.alive = false;

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
		g2.drawImage(image, this.X, this.Y, gp.tileSize, gp.tileSize, null);
		
		
	}

}
