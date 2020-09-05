package pathfinding.domain;

import pathfinding.algorithms.JPS;
import pathfinding.algorithms.AStar;
import pathfinding.algorithms.BFS;

/**
 *
 * Class handles performance testing
 */
public class PerformanceTest {

    char[][] map;
    int[] start;
    int[] end;
    
    
     /**
     *
     * @param map map as an ASCII grid
     * @param start coordinates of the start point
     * @param end coordinates of the end point
     */
    public PerformanceTest(char[][] map, int[] start, int[] end) {
        this.map = map;
        this.start = start;
        this.end = end;
    }
    
    
    /**
     * Runs algorithms several times and returns the average of the excecution times.
     *
     * @param algo 1 to test BFS, 2 for A* and 3 for JPS
     * @return the average of the test run times.
     */
    public long test(int algo) {
        long sum = 0;
        int runs = 21;
        switch (algo) {
            case 1:
                BFS b = new BFS();
                for (int i = 0; i < runs; i++) {
                    long s = System.nanoTime();
                    b.shortestPath(map, start, end);
                    long e = System.nanoTime();
                    if (i != 0) {
                        long result = e - s;
                        sum += result;
                    }
                }
                
            case 2:
                AStar a = new AStar();
                for (int i = 0; i < runs; i++) {
                    long s = System.nanoTime();
                    a.shortestPath(map, start, end);
                    long e = System.nanoTime();
                    if (i != 0) {
                        long result = e - s;
                        sum += result;
                    }
                }
                
            case 3:
                JPS j = new JPS();
                for (int i = 0; i < runs; i++) {
                    long s = System.nanoTime();
                    j.shortestPath(map, start, end);
                    long e = System.nanoTime();
                    if (i != 0) {
                        long result = e - s;
                        sum += result;
                    }
                }
                
            default:
                break;
        }       
        return Math.round((sum / (runs - 1)) / 1e6);
    }
}
