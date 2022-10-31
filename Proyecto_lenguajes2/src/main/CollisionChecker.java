package main;
import entity.Entity;

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
}
