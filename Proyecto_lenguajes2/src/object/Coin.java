package object;

import java.awt.*;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class Coin extends Entity {

	public Coin(GamePanel gp) {
		super(gp);
		name = "BronzeCoin";
		solidArea = new Rectangle(0,0,32,32);
		collisionOn = true;
		// TODO Auto-generated constructor stub
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/objects/coin_bronze.png"));
		} catch (Exception e){

		}
	}
	public void update() {
		
	}
	public void draw(Graphics2D g2) {

		g2.drawImage(up1, X, Y,gp.tileSize,gp.tileSize, null);
	}

}
