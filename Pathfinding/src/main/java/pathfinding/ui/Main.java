
package pathfinding.ui;

import pathfinding.domain.Bfs;

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
        char[][] map = {{'.','.','.','.','.'},
                        {'.','@','.','.','@'},
                        {'.','@','@','@','.'},
                        {'@','@','.','.','.'},
                        {'.','.','.','.','.'}};
        int[] start = {4,4};
        int[] end = {1,0};
        System.out.println(b.shortestPath(map, start, end));
    }
    
}
