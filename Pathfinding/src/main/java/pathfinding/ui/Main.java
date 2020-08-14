
package pathfinding.ui;

import java.io.FileNotFoundException;
/*import java.io.File;
import pathfinding.domain.BFS;
import pathfinding.domain.AStar;
import pathfinding.domain.MapScanner;
import pathfinding.domain.PerformanceTest;*/

/**
 *
 * @author hiski
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        PathfindingUi.main(args);
        /*BFS b = new BFS();
        AStar a = new AStar();
        
        MapScanner mapScan = new MapScanner();
        char[][] map = mapScan.scan(new File("./Berlin_0_1024.map"));
        int[] start = {0,0};
        int[] end = {100,100};
        
        PerformanceTest pt = new PerformanceTest(map, start, end);
        
        System.out.println("BFS Time " + (pt.test(1)) + " ms");        
        System.out.println("A* Time " + (pt.test(2)) + " ms");*/
    }
    
}
