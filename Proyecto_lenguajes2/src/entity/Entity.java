package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	public GamePanel gp;
	public int X, Y;
	public int SPEED;
	public BufferedImage up1, down, left, right;
	public Integer type; // 0 tanque, 1 bala, 2 enemigo;
	public BufferedImage current;
	public boolean invincible = false;
	public Integer invincibleCounter = 0;
	public String DIRECTION = "DOWN";
	public String name;
	public String belongs = "";
	public Integer speed = 5;
	public Integer damage = 2;
	public Projectile projectile;
	public Boolean alive;
	public Integer life ;
	public Integer maxLife = 6;

	public Rectangle solidArea = new Rectangle(0,0,32,32); ;
	Integer actionLockCounter = 0;
	public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
	public boolean collisionOn = false;
	public Entity(GamePanel gp) {
		this.gp = gp;
		this.SPEED = 1;
	}

	public void setAction(){}
	
	public void update() {
		setAction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this,false);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if(this.type == 1 && contactPlayer == true) {
			if(gp.tank.invincible == false) {
				//give damage
				gp.tank.life -=1;
				gp.tank.invincible = true;
				alive = false;
			}
		}



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

	private void contactBullet(int bulletIndex) {
		if(bulletIndex != 999) {
			gp.projectileList.get(bulletIndex).alive = false;
		}
	}

	public void attack(int X, int Y){
		if(X == this.X && Y == this.Y){

		}
	}
	public void draw(Graphics2D g2) {



	}

}
