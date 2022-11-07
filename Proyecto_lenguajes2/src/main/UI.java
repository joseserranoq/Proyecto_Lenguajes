package main;

import entity.Entity;
import object.Heart;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40 ;
    public boolean messageOn = false;
    BufferedImage hearth_full, hearth_half, hearth_blank;
    public String message = "";
    public int command_number = 0;
    int messageCounter  = 0;
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        Entity hearth = new Heart(gp);
        hearth_full = hearth.up1;
        hearth_half = hearth.down;
        hearth_blank = hearth.left;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;

    }
    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if(gp.GAME_STATE == gp.TITLE_STATE){

            drawTitleScreen();
        }
        if(gp.GAME_STATE == gp.PAUSE_STATE){

            drawPauseScreen();
        }
        if(gp.GAME_STATE == gp.PLAY_STATE){
            drawPlayerLife();
        }



    }
    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;


        while (i < gp.tank.maxLife/2){
            g2.drawImage(hearth_blank,x,y,null);
            i++;
            x+=gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        while (i < gp.tank.life){
            g2.drawImage(hearth_half,x,y,null);
            i++;
            if(i < gp.tank.life){
                g2.drawImage(hearth_full, x, y, null);
            }
            i++;
            x+=gp.tileSize;
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Paused";
        int x = getXForCenteredText(text);
        int y = gp.SCREEN_HEIGHT /2;
        g2.drawString(text, x, y);
    }

    private void drawTitleScreen() {
        //TITLE GAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 34F));
        String text = "Las explosivas aventuras de cañín el cañón";
        int x = getXForCenteredText(text);
        int y =  gp.tileSize * 3 ;
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 34F));
        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(command_number == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(command_number == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize ;
        g2.drawString(text, x, y);
        if(command_number == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }






    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.SCREEN_WIDTH / 2 - length/2;
        return x;
    }
}
