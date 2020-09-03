package pathfinding.domain;

import pathfinding.io.MapScanner;
import pathfinding.algorithms.*;
import java.io.File;
import java.io.FileNotFoundException;

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
    private File[] files;

    public PathfindingService() throws FileNotFoundException {

        mapScanner = new MapScanner();
        bfs = new BFS();
        aStar = new AStar();
        jps = new JPS();
        map = mapScanner.scan(new File("./maps/Berlin_0_512.map"));
        files = mapScanner.getFiles();
        start = new int[2];
        end = new int[2];
        setStart(0, 0);
        setEnd(0, 0);

    }

    /**
     * Sets the start point.
     *
     * @param y the y-coordinate of the start point.
     * @param x the x-coordinate of the start point.
     */
    public void setStart(int y, int x) {
        this.start[0] = y;
        this.start[1] = x;
    }

    /**
     * Sets the end point.
     *
     * @param y the y-coordinate of the end point.
     * @param x the x-coordinate of the end point.
     */
    public void setEnd(int y, int x) {
        this.end[0] = y;
        this.end[1] = x;
    }

    public double bfsPathLength() {
        return bfs.shortestPath(map, start, end);
    }

    public double aStarPathLength() {
        return aStar.shortestPath(map, start, end);
    }

    public double jpsPathLength() {
        return jps.shortestPath(map, start, end);
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

    public File[] getFiles() {
        return files;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public char[][] getAStarPath() {
        return aStar.getPath();
    }

    public char[][] getJpsPath() {
        return jps.getPath();
    }

    public char[][] getBfsPath() {
        return bfs.getPath();
    }
    
    public int bfsOperations() {
        return bfs.getOperations();
    }
    
    public int aStarOperations() {
        return aStar.getOperations();
    }
    
    public int jpsOperations() {
        return jps.getOperations();
    }
}
