package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Entity {
	public GamePanel gp;
	public int X, Y;
	public int SPEED;
	public BufferedImage up1, down, left, right;
	public String DIRECTION;
	public String name;
	public Integer speed;
	public Projectile projectile;
	public Boolean alive;
	public Integer life;
	public Integer maxLife;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
		this.SPEED = 5;
	}
	
	public void update() {
		
	}
	public void draw(Graphics2D g2) {
		
	}

}
