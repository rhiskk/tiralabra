package pathfinding.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pathfinding.io.MapScanner;

/**
 *
 * @author hiski
 */
public class AStarTest {

    MapScanner mapScanner;
    AStar astr;
    char[][] map;

    @Before
    public void setUp() throws FileNotFoundException {
        astr = new AStar();
        mapScanner = new MapScanner();
        map = mapScanner.scan(new File("./testmaps/test.map"));
    }

    @Test
    public void shorestPathReturnsMinusOneIfGivenBlockedCoordinates() {
        int[] start = {2, 14};
        int[] end = {2, 15};
        assertEquals(-1, astr.shortestPath(map, start, end), 2);
    }
    
    @Test
    public void shortestPathReturnsTheCorrectLengthWhenPathIsFound() {
        int[] start = {1, 15};
        int[] end = {22, 8};
        assertEquals(26.38, astr.shortestPath(map, start, end), 4);
    }
    
    @Test
    public void getOperationsReturnsTheCorrectAmount() {
        int[] start = {6, 20};
        int[] end = {9, 23};
        astr.shortestPath(map, start, end);
        assertEquals(17, astr.getOperations());
    }
    
    @Test
    public void shortestPathReturnsMinusOneWhenPathIsNotFound() {
        int[] start = {0, 0};
        int[] end = {15, 15};
        assertEquals(-1, astr.shortestPath(map, start, end), 2);
    }
    
    @Test
    public void getPathReturnsTheCorrectPath() throws FileNotFoundException {
        //test path1
        int[] start = {1, 14};
        int[] end = {27, 14};
        map = mapScanner.scan(new File("./testmaps/test.map"));       
        astr.shortestPath(map, start, end);
        char[][] expected = mapScanner.scan(new File("./testmaps/astartest1.map"));
        assertArrayEquals(expected, astr.getPath());
        
        //test path2
        start = new int[]{13, 29};
        end = new int[]{15, 1};  
        map = mapScanner.scan(new File("./testmaps/test.map"));;
        astr.shortestPath(map, start, end);
        expected = mapScanner.scan(new File("./testmaps/astartest2.map"));
        assertArrayEquals(expected, astr.getPath());
        
        //test path3
        start = new int[]{29, 0};
        end = new int[]{7, 29};
        map = mapScanner.scan(new File("./testmaps/test.map"));
        astr.shortestPath(map, start, end);
        expected = mapScanner.scan(new File("./testmaps/astartest3.map"));
        assertArrayEquals(expected, astr.getPath());
    }
}
