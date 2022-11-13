package tiles;

import main.GamePanel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.*;

import org.jpl7.Query;
import java.util.stream.IntStream;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public String[] fileData;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[3]; //the quantity of kind of tiles
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap(0);
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/FLOOR.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/WALL32.png"));
            tile[1].collision = true;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(int mapNumber){
        deleteFactsInProlog(); //when the map changes the facts in prolog should be too
        try{
            //get a map randomly
            Random rand = new Random();
            int numMap = rand.nextInt(5); //it chooses a number between 0-5

            String mapPath= "";
            switch (mapNumber){
                case 0: mapPath ="/maps/map01.txt";
                break;
                case 1: mapPath ="/maps/map02.txt";
                break;
                case 2: mapPath ="/maps/map03.txt";
                break;
                case 3: mapPath ="/maps/map04.txt";
                break;
                case 4: mapPath ="/maps/map05.txt";
                break;
            }

            System.out.println("es el numero "+numMap + "la ruta del mapa "+mapPath);
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row= 0;

            while(col< gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
            writeInProlog();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        //g2.drawImage(tile[0].image, 0 ,0, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[1].image, 48 ,0, gp.tileSize, gp.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x ,y, gp.tileSize, gp.tileSize, null);
            col ++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol){
                col=0;
                x=0;
                row++;
                y+= gp.tileSize;
            }
        }
    }
    public void loadPrologFile(){
        fileData = new String[102];
        try {
            int position = 0;
            FileReader file = new FileReader("dikstra.txt");
            BufferedReader bf = new BufferedReader(file);
            while(bf.ready()){
                fileData[position] = bf.readLine();
                //System.out.println(bf.readLine());
                position++;
            }
            bf.close();
            System.out.println("Cargada la informacion de file.pl");
        }catch(Exception e){
            System.out.println("NO se ha cargado la informacion de file.pl");
            e.printStackTrace();
        }
    }
    public void deleteFactsInProlog(){
        loadPrologFile();   //it is used to fill the variable fileData
        try {
            FileWriter file = new FileWriter("file.pl");
            BufferedWriter bf = new BufferedWriter(file);
            int i = 0;
            while(i <fileData.length){
                String w = fileData[i];
                bf.write(w + "\n");
                i++;
            }
            bf.close();
        }catch(Exception e){
            System.out.println("No se ha borrado la informacion de deleteFactsInProlog");
            e.printStackTrace();
        }
    }
    public String getFacts(int axisX1,int axisY1,String direction){   //
        int axisX2 = 0;
        int axisY2 = 0;
        switch(direction){
            case "up":
                axisX2 = axisX1 - 1;
                axisY2 = axisY1;
                break;
            case "down":
                axisX2 = axisX1 + 1;
                axisY2 = axisY1;
                break;
            case "right":
                axisX2 = axisX1;
                axisY2 = axisY1 + 1;
                break;
            case "left":
                axisX2 = axisX1;
                axisY2 = axisY1 - 1;
                break;
        }
        String fact = String.format("dist((%s,%s),(%s,%s),1).\n", axisX1, axisY1, axisX2, axisY2);
        //System.out.println(fact);
        return fact;
    }
    public int[] checkLTiles(int x,int y, ArrayList<int[]> l){
        for(int i=0;i<l.size();i++){
            if(l.get(i)[0] == x && l.get(i)[1] == y){
                return l.get(i);
            }
        }
        return null;
    }
    public void writeInProlog(){
        // generar los axis para realizar las rutas
        //If the file has content of facts we are going to delete it
        try{

            FileWriter file = new FileWriter("file.pl",true);
            BufferedWriter bf = new BufferedWriter(file);
            ArrayList<int[]> tilesAvailable= new ArrayList<>();
            for(int x = 0; x < mapTileNum.length; x++) {
                for (int y = 0; y < mapTileNum[x].length; y++) {
                    if (mapTileNum[x][y] == 0) { //it is going to write the tiles that has not collision
                        //tilesAvailable.put(x, y);  //it appends the x and y that has not collision
                        int[] arr = new int[2];
                        arr[0] = x;
                        arr[1] = y;
                        tilesAvailable.add(arr);
                    }
                }
            }
            for(int i=0;i < tilesAvailable.size();i++){
                int x = tilesAvailable.get(i)[0];
                int y = tilesAvailable.get(i)[1];
                //up
                int[] up = checkLTiles(x-1,y,tilesAvailable);
                int[] down = checkLTiles(x+1,y,tilesAvailable);
                int[] right = checkLTiles(x,y+1,tilesAvailable);
                int[] left = checkLTiles(x,y-1,tilesAvailable);
                if(x != 0 && up != null){
                    //getFacts(x,y,"up");
                    bf.write(getFacts(up[0],up[1],"up"));
                }
                //down
                if(x != gp.maxScreenRow - 1 && down != null){
                    bf.write(getFacts(x,y,"down"));
                }
                //right
                if(y != gp.maxScreenCol - 1 && right != null)
                    bf.write(getFacts(x,y,"right"));
                //left
                if(y != 0 && left != null)
                    bf.write(getFacts(x,y,"left"));
            }
            bf.close();
            System.out.println("Function writeInProlog has finished");
        }catch(Exception e){
            System.out.println("writeInProlog has an error. " );
            e.printStackTrace();
        }
    }
}
    /*
    public void writeInProlog(){
        //If the file has content of facts we are going to delete it
        try{

            FileWriter file = new FileWriter("file.pl",true);
            BufferedWriter bf = new BufferedWriter(file);
            for(int i = 0; i < mapTileNum.length; i++){
                for(int y = 0; y < mapTileNum[i].length; y++){
                    if(mapTileNum[i][y] == 0) { //it is going to write the tiles that has not collision
                        bf.write("dist(" + Integer.toString(i) + "," + Integer.toString(y) + "," + "1"+").\n");
                    }
                }
            }
            bf.close();
            System.out.println("Function writeInProlog has finished");
        }catch(Exception e){
            System.out.println("writeInProlog has an error. " );
            e.printStackTrace();
        }
    }
}
*/