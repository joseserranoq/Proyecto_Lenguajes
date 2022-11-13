package main;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import org.jpl7.*;
//import org.jpl7.Query;
//import org.jpl7.Term;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tank game");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();

        String path = "consult('file.pl')";
        Query q1 = new Query(path);
        System.out.println(q1.hasSolution());
/*
        String t1 = "listing(dist).";
        Query q2= new Query(t1);

        Map<String, Term>[] sol = q2.allSolutions();

        String t2 = "path((7,10),(11,1),X,_).";
        Query q3 = new Query(t2);
        Map<String,Term> see = q3.oneSolution();
        Pattern pattern = Pattern.compile("(([1-9],[1-9]))*\\w+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(see.values().toArray()[0].toString());
        ArrayList<Pair> ruta = new ArrayList();
        int contador = 0;
        int X = 0;
        int Y = 0;
        while(matcher.find()){
            String s = matcher.group(0);
            switch (contador){
                case 0:
                    X = Integer.parseInt(s);
                    contador++;
                    break;
                case 1:
                    Y = Integer.parseInt(s);
                    ruta.add(new Pair(X,Y));
                    contador = 0;
                    break;
            }
        }
        for (int i = 0; i < ruta.size(); i++) {
            System.out.println(ruta.get(i).X+" "+ruta.get(i).Y);

        }
        */
    }
}
//[','(7, 10), ','(8, 10), ','(8, 9), ','(8, 8), ','(9, 8), ','(10, 8), ','(11, 8), ','(11, 7), ','(11, 6), ','(11, 5), ','(11, 4), ','(11, 3), ','(11, 2), ','(11, 1)]