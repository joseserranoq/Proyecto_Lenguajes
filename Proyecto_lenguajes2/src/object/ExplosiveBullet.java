package object;

import entity.Projectile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ExplosiveBullet extends Projectile {
    public ExplosiveBullet(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "ExplosiveBullet";
        SPEED = 5;
        alive = false;
        damage = 2;
        getImage();
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
