package object;

import entity.Projectile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class FreezeBullet extends Projectile {
    GamePanel gp;
    public FreezeBullet(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.name = "FreezeBullet";
        this.SPEED = 6;
        this.alive = false;
        this.damage = 0;
        this.type = 4;
        System.out.println(this.type);
        getImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/objects/ice_bullet.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
