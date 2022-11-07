package main;
import entity.Entity;

import java.util.ArrayList;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entLeftMapX = entity.X + entity.solidArea.x;
        int entRightMapX = entity.X + entity.solidArea.x + entity.solidArea.width;
        int entTopMapY = entity.Y + entity.solidArea.y;
        int entBottomMapY = entity.Y + entity.solidArea.y + entity.solidArea.height;

        int entLeftCol = entLeftMapX/gp.tileSize;
        int entRightCol = entRightMapX/gp.tileSize;
        int entTopRow = entTopMapY/gp.tileSize;
        int entBottomRow = entBottomMapY/gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.DIRECTION){
            case "UP":
                entTopRow = (entTopMapY - entity.SPEED)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
                tileNum2 = gp.tileM.mapTileNum[entRightCol][entTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "DOWN":
                entBottomRow = (entBottomMapY + entity.SPEED)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entLeftCol][entBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entRightCol][entBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "LEFT":
                entLeftCol = (entLeftMapX - entity.SPEED)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
                tileNum2 = gp.tileM.mapTileNum[entLeftCol][entBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "RIGHT":
                entRightCol = (entRightMapX + entity.SPEED)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entRightCol][entTopRow];
                tileNum2 = gp.tileM.mapTileNum[entRightCol][entBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gp.entityList.size(); i++){

            if(gp.entityList.get(i)!= null){
                entity.solidArea.x = entity.X + entity.solidArea.x;
                entity.solidArea.y = entity.Y +entity.solidArea.y;

                gp.entityList.get(i).solidArea.x = gp.entityList.get(i).X + gp.entityList.get(i).solidArea.x;
                gp.entityList.get(i).solidArea.y = gp.entityList.get(i).Y + gp.entityList.get(i).solidArea.y;

                switch(entity.DIRECTION){
                    case "UP" -> {

                        entity.solidArea.y -= entity.SPEED;
                        if(entity.solidArea.intersects(gp.entityList.get(i).solidArea)){
                            if(gp.entityList.get(i).collisionOn == true){

                                entity.collisionOn = true;

                            }
                            if(player == true){
                                index = i;
                            }

                        }
                        break;
                    }
                    case "DOWN" -> {

                        entity.solidArea.y += entity.SPEED;
                        if(entity.solidArea.intersects(gp.entityList.get(i).solidArea)){
                            if(gp.entityList.get(i).collisionOn == true){
                                entity.collisionOn = true;


                            }
                            if(player == true){
                                index = i;
                            }

                        }
                        break;
                    }
                    case "LEFT" -> {

                        entity.solidArea.x -= entity.SPEED;
                        if(entity.solidArea.intersects(gp.entityList.get(i).solidArea)){
                            if(gp.entityList.get(i).collisionOn == true){
                                entity.collisionOn = true;


                            }
                            if(player == true){
                                index = i;
                            }

                        }
                        break;
                    }
                    case "RIGHT" -> {

                        entity.solidArea.x += entity.SPEED;
                        if(entity.solidArea.intersects(gp.entityList.get(i).solidArea)){
                            if(gp.entityList.get(i).collisionOn == true){
                                entity.collisionOn = true;


                            }
                            if(player == true){
                                index = i;
                            }

                        }
                        break;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.entityList.get(i).solidArea.x = gp.entityList.get(i).solidAreaDefaultX;
                gp.entityList.get(i).solidArea.y = gp.entityList.get(i).solidAreaDefaultY;

            }
        }

        return index;
    }

    //NPC OR MONSTER COLLISION
    public int checkEntity( Entity entity, ArrayList<Entity> target){
        int index = 999;
        for(int i = 0; i < target.size(); i++){



            if(target.get(i)!= null){
                entity.solidArea.x = entity.X + entity.solidArea.x;
                entity.solidArea.y = entity.Y +entity.solidArea.y;

                target.get(i).solidArea.x = target.get(i).X + target.get(i).solidArea.x;
                target.get(i).solidArea.y = target.get(i).Y + target.get(i).solidArea.y;

                switch(entity.DIRECTION){
                    case "UP" -> {

                        entity.solidArea.y -= entity.SPEED;
                        if(entity.solidArea.intersects(target.get(i).solidArea)){
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    }
                    case "DOWN" -> {
                        entity.solidArea.y += entity.SPEED;
                        if(entity.solidArea.intersects(target.get(i).solidArea)){
                                entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    }
                    case "LEFT" -> {

                        entity.solidArea.x -= entity.SPEED;
                        if(entity.solidArea.intersects(target.get(i).solidArea)){
                                entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    }
                    case "RIGHT" -> {

                        entity.solidArea.x += entity.SPEED;
                        if(entity.solidArea.intersects(target.get(i).solidArea)){
                                entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target.get(i).solidArea.x = target.get(i).solidAreaDefaultX;
                target.get(i).solidArea.y = target.get(i).solidAreaDefaultY;

            }
        }

        return index;

    }

    public boolean checkPlayer(Entity entity){

        boolean contactPlayer = false;
        entity.solidArea.x = entity.X + entity.solidArea.x;
        entity.solidArea.y = entity.Y +entity.solidArea.y;

        gp.tank.solidArea.x = gp.tank.X + gp.tank.solidArea.x;
        gp.tank.solidArea.y = gp.tank.Y + gp.tank.solidArea.y;

        switch(entity.DIRECTION){
            case "UP" -> {

                entity.solidArea.y -= entity.SPEED;

                break;
            }
            case "DOWN" -> {
                entity.solidArea.y += entity.SPEED;

                break;
            }
            case "LEFT" -> {

                entity.solidArea.x -= entity.SPEED;

                break;
            }
            case "RIGHT" -> {

                entity.solidArea.x += entity.SPEED;

                break;
            }
        }

        if(entity.solidArea.intersects(gp.tank.solidArea)){
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.tank.solidArea.x =  gp.tank.solidAreaDefaultX;
        gp.tank.solidArea.y = gp.tank.solidAreaDefaultY;

        return contactPlayer;

    }


}
