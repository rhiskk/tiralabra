package pathfinding.domain;

import pathfinding.io.MapScanner;
import pathfinding.algorithms.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * Class contains 
 */
public class PathfindingService {

    private MapScanner mapScanner;
    private PerformanceTest performanceTest;
    private BFS bfs;
    private AStar aStar;
    private JPS jps;
    private char[][] map;
    private int[] start;
    private int[] end;
    private DecimalFormat decimalFormat;

    public PathfindingService() throws FileNotFoundException {
        decimalFormat = new DecimalFormat("#.###");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        mapScanner = new MapScanner();
        bfs = new BFS();
        aStar = new AStar();
        jps = new JPS();
        map = mapScanner.scan(new File("./Berlin_0_512.map"));
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
        double length = bfs.shortestPath(map, start, end);
        return decimalFormat.format(length);
    }

    public String aStarPathLength() {
        double length = aStar.shortestPath(map, start, end);
        return decimalFormat.format(length);
    }

    public String jpsPathLength() {
        double length = jps.shortestPath(map, start, end);
        return decimalFormat.format(length);
    }

    public double bfsPerformance() {
        performanceTest = new PerformanceTest(map, start, end);
        return performanceTest.test(1);
    }

    public double aStarPerformance() {
        performanceTest = new PerformanceTest(map, start, end);
        return performanceTest.test(2);
    }
    
    public double jpsPerformance() {
        performanceTest = new PerformanceTest(map, start, end);
        return performanceTest.test(3);
    }

    public char[][] getMap() {
        return map;
    }

    public char[][] getAPath() {
        return aStar.getPath();
    }

    public char[][] getJPath() {
        return jps.getPath();
    }

    public char[][] getBPath() {
        return bfs.getPath();
    }
}
