package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Projectile;
import main.GamePanel;


public class Bullet extends Projectile {
	public Bullet(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Bullet";
		speed = 5;
		alive = false;
		getImage();
		// TODO Auto-generated constructor stub
	}



	GamePanel gp;
	

	
	public void getImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/objects/BULLET.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
