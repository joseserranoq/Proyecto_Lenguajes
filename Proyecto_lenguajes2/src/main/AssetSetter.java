package main;

import entity.Entity;
import entity.ExplosiveTank;
import entity.FastTank;
import entity.FreezeTank;
import object.C4;
import object.Coin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    ArrayList level1 = new ArrayList<Pair>(Arrays.asList( new Pair(4,4), new Pair(5,5), new Pair(6,6) ));
    ArrayList level2 = new ArrayList<Pair>(Arrays.asList( new Pair(7,3), new Pair(7,4), new Pair(7,5) ));
    ArrayList level3 = new ArrayList<Pair>(Arrays.asList( new Pair(2,5), new Pair(3,5), new Pair(4,5) ));
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        Random rand = new Random();
        switch (this.gp.actual_level){
            case 0:
                int random = rand.nextInt(level1.size());
                Pair par = (Pair) level1.get(random);

                Coin coin = new Coin(gp);
                coin.X = par.X * gp.tileSize;
                coin.Y = par.Y * gp.tileSize;
                level1.remove(random);

                gp.entityList.add(coin);

                random = rand.nextInt(level1.size());
                par = (Pair) level1.get(random);

                C4 c4 = new C4(gp);
                c4.X = par.X * gp.tileSize;
                c4.Y = par.Y * gp.tileSize;
                level1.remove(random);

                gp.entityList.add(c4);
                break;

            case 1:
                int random2 = rand.nextInt(level2.size());
                Pair par2 = (Pair) level2.get(random2);
                Coin coin2 = new Coin(gp);
                coin2.X = par2.X * gp.tileSize;
                coin2.Y = par2.Y * gp.tileSize;

                gp.entityList.add(coin2);
                level2.remove(random2);
                break;
            case 2:
                random = rand.nextInt(level3.size());
                par = (Pair) level3.get(random);
                Coin coin3 = new Coin(gp);
                coin3.X = par.X * gp.tileSize;
                coin3.Y = par.Y * gp.tileSize;

                gp.entityList.add(coin3);
                level3.remove(random);
                break;



        }



    }

    public void setMonster () {
        switch (this.gp.actual_level){
            case 0:

                gp.tank.X = 2 * gp.tileSize;
                gp.tank.Y = 2 * gp.tileSize;

                FastTank tank = new FastTank(gp);
                tank.X = 4 * gp.tileSize;
                tank.Y = 5 * gp.tileSize;

                FreezeTank freezingTank = new FreezeTank(gp);
                freezingTank.X = 5 * gp.tileSize;
                freezingTank.Y = 6 * gp.tileSize;


                gp.monsterList.add(tank);
                gp.monsterList.add(freezingTank);
                break;
            case 1:
                gp.tank.X = 3 * gp.tileSize;
                gp.tank.Y = 4 * gp.tileSize;

                ExplosiveTank tank2 = new ExplosiveTank(gp);
                tank2.X = 4 * gp.tileSize;
                tank2.Y = 3 * gp.tileSize;
                gp.monsterList.add(tank2);

                ExplosiveTank tank5 = new ExplosiveTank(gp);
                tank5.X = 5 * gp.tileSize;
                tank5.Y = 3 * gp.tileSize;
                gp.monsterList.add(tank5);
                break;
            case 2:
                gp.tank.X = 3 * gp.tileSize;
                gp.tank.Y = 4 * gp.tileSize;

                FastTank tank3 = new FastTank(gp);
                tank3.X = 3 * gp.tileSize;
                tank3.Y = 3 * gp.tileSize;


                ExplosiveTank tank4 = new ExplosiveTank(gp);
                tank4.X = 5 * gp.tileSize;
                tank4.Y = 3 * gp.tileSize;

                ExplosiveTank tank7 = new ExplosiveTank(gp);
                tank7.X = 7 * gp.tileSize;
                tank7.Y = 2 * gp.tileSize;

                ExplosiveTank tank8 = new ExplosiveTank(gp);
                tank8.X = 4 * gp.tileSize;
                tank8.Y = 3 * gp.tileSize;

                gp.monsterList.add(tank3);
                gp.monsterList.add(tank4);
                gp.monsterList.add(tank7);
                gp.monsterList.add(tank8);
                break;



        }



    }
}
