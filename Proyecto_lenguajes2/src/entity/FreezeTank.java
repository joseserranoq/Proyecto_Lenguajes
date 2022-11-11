package entity;

import main.GamePanel;
import object.ExplosiveBullet;
import object.FreezeBullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Random;

public class FreezeTank extends Entity {

    public FreezeTank(GamePanel gp) {
        super(gp);
        this.type = 2;
        this.maxLife = 2;
        DIRECTION ="DOWN";
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collisionOn = true;
        getPlayerImage();
        projectile = new FreezeBullet(gp);
        name = "FreezeTank";
        belongs = "FreezeTank";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/tank/FASTTANK32.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/tank/FASTTANK32D.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/tank/FASTTANK32L.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/tank/FASTTANK32R.png"));
            current = up1;


        }
        catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public void setAction () {
        actionLockCounter++;
        if ( actionLockCounter == 120 ){
            Random random = new Random();
            int i = random.nextInt(100) +1 ;
            if( i <= 25 ){
                DIRECTION = "UP";
                current = up1;
            }
            if( i > 25 && i <= 50 ){
                DIRECTION = "DOWN";
                current = down;
            }
            if( i > 50 && i <= 75 ){
                DIRECTION = "LEFT";
                current = left;
            }
            if( i > 75 &&  i <= 100 ){
                DIRECTION = "RIGHT";
                current = right;
            }
            actionLockCounter = 0;

            if(projectile.alive == false){
                projectile.set(X, Y, DIRECTION, true, this, 4);

                gp.projectileList.add(projectile);
            }
        }
    }

    public void draw(Graphics2D g2) {


        g2.drawImage(current, X, Y,gp.tileSize,gp.tileSize, null);
    }


}
