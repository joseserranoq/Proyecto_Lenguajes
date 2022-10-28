package main;
import java.util.Map;

import javax.swing.JFrame;
//import org.jpl7.*;
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
        gamePanel.startGameThread();
        /*
        String path = "consult('file.pl')";
        Query q1 = new Query(path);
        System.out.println(q1.hasSolution());
        Variable X = new Variable("X");
        Query q4 = 
          new Query( 
              "descendent_of", 
              new Term[] {X,new Atom("ralf")} 
          );

        java.util.Map<String,Term> solution;

        solution = q4.oneSolution();

        System.out.println( "first solution of descendent_of(X, ralf)"); 
        System.out.println( "X = " + solution.get("X"));        
        String t1 = "tiene(X,gas)";
        Query q2= new Query(t1);
        Map x = q2.oneSolution();
        System.out.println(x.get("X"));
        */
        
    }
}
