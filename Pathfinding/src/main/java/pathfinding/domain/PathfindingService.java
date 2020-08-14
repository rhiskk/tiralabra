package pathfinding.domain;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author hiski
 */
public class PathfindingService {

    private MapScanner ms;
    private PerformanceTest pt;
    private BFS b;
    private AStar a;
    private char[][] map;
    private int[] start;
    private int[] end;

    public PathfindingService() throws FileNotFoundException {
        ms = new MapScanner();
        b = new BFS();
        a = new AStar();
        map = ms.scan(new File("./Berlin_0_1024.map"));
        start = new int[2];
        end = new int[2];
        setStart(0, 0);
        setEnd(0, 0);
    }

    public void setStart(int y, int x) {
        this.start[0] = y;
        this.start[1] = x;
    }

    public void setEnd(int y, int x) {
        this.end[0] = y;
        this.end[1] = x;
    }

    public String bfsPathLength() {
        int length = b.shortestPath(map, start, end);
        return "BFS length: " + length;
    }

    public String aStarPathLength() {
        int length = a.shortestPath(map, start, end);
        return "A* length: " + length;
    }

    public String bfsPerformance() {
        pt = new PerformanceTest(map, start, end);
        return "BFS time: " + (pt.test(1)) + " ms";
    }

    public String aStarPerformance() {
        pt = new PerformanceTest(map, start, end);
        return "A* time " + (pt.test(2)) + " ms";
    }
}
