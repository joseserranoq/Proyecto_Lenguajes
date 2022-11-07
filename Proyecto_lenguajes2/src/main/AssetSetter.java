package main;

import entity.ExplosiveTank;
import entity.FastTank;
import object.Coin;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        switch (this.gp.actual_level){
            case 0:
                Coin coin = new Coin(gp);
                coin.X = 4 * gp.tileSize;
                coin.Y = 4 * gp.tileSize;

                gp.entityList.add(coin);
                break;
            case 1:
                Coin coin2 = new Coin(gp);
                coin2.X = 4 * gp.tileSize;
                coin2.Y = 4 * gp.tileSize;

                gp.entityList.add(coin2);
                break;
            case 2:
                Coin coin3 = new Coin(gp);
                coin3.X = 4 * gp.tileSize;
                coin3.Y = 4 * gp.tileSize;

                gp.entityList.add(coin3);
                break;



        }



    }

    public void setMonster () {
        switch (this.gp.actual_level){
            case 0:
                gp.tank.X = 5 * gp.tileSize;
                gp.tank.Y = 6 * gp.tileSize;

                FastTank tank = new FastTank(gp);
                tank.X = 4 * gp.tileSize;
                tank.Y = 5 * gp.tileSize;
                gp.monsterList.add(tank);
                break;
            case 1:
                gp.tank.X = 3 * gp.tileSize;
                gp.tank.Y = 4 * gp.tileSize;

                ExplosiveTank tank2 = new ExplosiveTank(gp);
                tank2.X = 5 * gp.tileSize;
                tank2.Y = 3 * gp.tileSize;
                gp.monsterList.add(tank2);
                break;
            case 2:
                gp.tank.X = 3 * gp.tileSize;
                gp.tank.Y = 4 * gp.tileSize;
                FastTank tank3 = new FastTank(gp);
                tank3.X = 4 * gp.tileSize;
                tank3.Y = 5 * gp.tileSize;
                gp.monsterList.add(tank3);

                ExplosiveTank tank4 = new ExplosiveTank(gp);
                tank4.X = 5 * gp.tileSize;
                tank4.Y = 3 * gp.tileSize;
                gp.monsterList.add(tank4);
                break;



        }



    }
}
