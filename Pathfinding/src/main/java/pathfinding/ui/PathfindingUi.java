
package pathfinding.ui;

import java.io.FileNotFoundException;
import java.util.Scanner;
import pathfinding.domain.PathfindingService;

/**
 *
 * Väliakainen tekstikäyttöliittymä
 */
public class PathfindingUi {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        PathfindingService ps = new PathfindingService();
        
        while (true) {
            System.out.println("");
            System.out.println("Toiminnot:");
            System.out.println("0: Sulje ohjelma");
            System.out.println("1: Syötä lähtökoordinaatit");
            System.out.println("2: Syötä maalikoordinaatit");
            System.out.println("3: Etsi lyhyin reitti leveyshaulla");
            System.out.println("4: Etsi lyhyin reitti A*-algoritmilla");
            System.out.println("5: Testaa leveyshaun nopeus");
            System.out.println("6: Testaa A*-algoritmin nopeus");
            System.out.println("");
            System.out.print("Valitse toiminto:  ");
            String f = s.nextLine();
            System.out.println("");
            
            if (f.equals("0")) {
                System.out.println("Ohjelma suljetaan.");
                break;
            } else if(f.equals("1")) {
                System.out.print("Lähtö x: ");
                f = s.nextLine();
                int x = Integer.valueOf(f);
                System.out.print("Lähtö y: ");
                f = s.nextLine();
                int y = Integer.valueOf(f);
                ps.setStart(y, x);   
            }  else if(f.equals("2")) {
                System.out.print("Maali x: ");
                f = s.nextLine();
                int x = Integer.valueOf(f);
                System.out.print("Maali y: ");
                f = s.nextLine();
                int y = Integer.valueOf(f);
                ps.setEnd(y, x);
            } else if(f.equals("3")) {
                System.out.println(ps.bfsPathLength());
            } else if(f.equals("4")) {
                System.out.println(ps.aStarPathLength());
            } else if(f.equals("5")) {
                System.out.println(ps.bfsPerformance());
            } else if(f.equals("6")) {
                System.out.println(ps.aStarPerformance());
            } else {
                System.out.println("Virheellinen syöte!");
            }
        }
    }
}
