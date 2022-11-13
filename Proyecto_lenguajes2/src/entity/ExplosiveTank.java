package entity;

import main.GamePanel;
import main.KeyHandler;

import main.Pair;
import object.Bullet;
import object.ExplosiveBullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jpl7.*;

public class ExplosiveTank extends Entity {
    public ExplosiveTank(GamePanel gp) {
        super(gp);
        this.type = 2;
        this.maxLife = 2;
        DIRECTION ="DOWN";

        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        collisionOn = true;
        getPlayerImage();
        projectile = new ExplosiveBullet(gp);
        name = "ExplosiveTank";
        belongs = "ExplosiveTank";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/tank/MADTANK32.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/tank/MADTANK32D.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/tank/MADTANK32L.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/tank/MADTANK32R.png"));
            current = up1;

        }
        catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public void setAction () {

        actionLockCounter++;

        if ( actionLockCounter == 180 ){
            try {
                ArrayList<Pair> ruta = new ArrayList();
                String t2 = String.format("path((%s,%s),(%s,%s),X,_).", X / gp.tileSize,Y / gp.tileSize, gp.tank.X/ gp.tileSize, gp.tank.Y/ gp.tileSize);
                Query q3 = new Query(t2);
                Map<String,Term> see = q3.oneSolution();
                Pattern pattern = Pattern.compile("([1-9],[1-9])*\\w+", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(see.values().toArray()[0].toString());

                int contador = 0;
                int x = 0;
                int y = 0;
                //esto es lo que no esta funcionando porque no vuelve a entrar
                while(matcher.find()){
                    String s = matcher.group(0);
                    switch (contador){
                        case 0:
                            x = Integer.parseInt(s);
                            contador++;
                            break;
                        case 1:
                            y = Integer.parseInt(s);
                            ruta.add(new Pair(x,y));
                            contador = 0;
                            break;
                    }
                }
                ruta.remove(0);
                // x,y-1  ,
                if(ruta.get(0).Y > Y/gp.tileSize){
                    System.out.println("IZQUIERDA");
                    DIRECTION = "DOWN";
                    current = down;
                }
                if(ruta.get(0).X < X/gp.tileSize){
                    System.out.println("ABAJO");
                    DIRECTION = "LEFT";
                    current = left;
                }
                if(ruta.get(0).Y < Y/gp.tileSize){
                    System.out.println("DERECHA");
                    DIRECTION = "UP";
                    current = up1;
                }

                if(ruta.get(0).X > X/gp.tileSize){
                    System.out.println("ARRIBA");
                    DIRECTION = "RIGHT";
                    current = right;
                }
                for (int i = 0; i < ruta.size(); i++) {
                    System.out.println("Pos "+i+ ": "+ruta.get(i).X+ " - "+ruta.get(i).Y);
                    System.out.println("Entity Pos "+i+ ": "+X/gp.tileSize+ " - "+Y/gp.tileSize);
                }
            } catch (Exception e){
                System.out.println("ENTRE AL CATCH");
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
            }
            actionLockCounter = 0;
            if(projectile.alive == false){
                projectile.set(X, Y, DIRECTION, true, this, 1);
                gp.projectileList.add(projectile);
            }
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(current, X, Y,gp.tileSize,gp.tileSize, null);
    }
}
