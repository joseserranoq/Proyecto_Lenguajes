package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class C4 extends Entity {

    public C4(GamePanel gp) {
        super(gp);
        name = "C4";
        solidArea = new Rectangle(0,0,32,32);
        collisionOn = true;
        // TODO Auto-generated constructor stub
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/objects/c4.png"));
        } catch (Exception e){

        }
    }

    public void update() {

    }
    public void draw(Graphics2D g2) {

        g2.drawImage(up1, X, Y,gp.tileSize,gp.tileSize, null);
    }
}
