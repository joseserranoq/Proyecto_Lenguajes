package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Heart extends Entity {
    public Heart(GamePanel gp) {
        super(gp);
        name = "Hearth";
        solidArea = new Rectangle(0,0,32,32);
        collisionOn = true;
        // TODO Auto-generated constructor stub
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));

            down = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            up1 = scale(up1, gp.tileSize, gp.tileSize);
            down = scale(down, gp.tileSize, gp.tileSize);
            left = scale(left, gp.tileSize, gp.tileSize);
        } catch (Exception e){

        }
    }
    public BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }

    public void update() {

    }
    public void draw(Graphics2D g2) {

        g2.drawImage(up1, X, Y,gp.tileSize,gp.tileSize, null);
    }

}
