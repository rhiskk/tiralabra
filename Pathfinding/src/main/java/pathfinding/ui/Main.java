
package pathfinding.ui;

import pathfinding.domain.Bfs;
import pathfinding.domain.AStar;

/**
 *
 * @author hiski
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bfs b = new Bfs();
        AStar a = new AStar();
        char[][] map = {{'.','.','.','.','.'},
                        {'@','@','.','.','.'},
                        {'.','.','.','.','.'},
                        {'.','.','.','.','.'},
                        {'.','.','.','.','.'}};
        int[] start = {0,0};
        int[] end = {4,4};
        //long alku = System.nanoTime();
        System.out.println(b.shortestPath(map, start, end));
        System.out.println(a.shortestPath(map, start, end));
        //long loppu = System.nanoTime();
        //System.out.println("Aikaa kului "+(loppu-alku)/1e9+" s");
    }
    
}
