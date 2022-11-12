package main;
import java.util.Map;

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

        String t1 = "listing(dist).";
        Query q2= new Query(t1);

        Map<String, Term>[] sol = q2.allSolutions();

        t1 = "path(1,10,X,_).";
        Query q3 = new Query(t1);
        Map<String,Term> see = q3.oneSolution();
        System.out.println(see);
    }
}
