package pathfinding.algorithms;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author hiski
 */
public class JpsTest {
    
    public JpsTest() {
    }
    
    JPS jps;
    char[][] map = {{'.', '.', '.', '.', '.'},
                    {'.', '@', '.', '.', '@'},
                    {'.', '@', '@', '@', '.'},
                    {'@', '@', '.', '.', '.'},
                    {'.', '@', '.', '.', '.'}};

    @Before
    public void setUp() {
        jps = new JPS();

    }
    
    @Test
    public void shortestPathReturnsTheCorrectLengthWhenPathIsFound() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        assertEquals(3 + 3 * Math.sqrt(2), jps.shortestPath(map, start, end), 5);
    }

    @Test
    public void shortestPathReturnsMinusOneWhenPathIsNotFound() {
        int[] start = {0, 0};
        int[] end = {4, 0};
        assertEquals(-1, jps.shortestPath(map, start, end), 2);
    }
    
    @Test
    public void getOperationsReturnsTheCorrectAmount() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        jps.shortestPath(map, start, end);
        assertEquals(12, jps.getOperations());
    }
    
    @Test
    public void getPathReturnsTheCorrectPath() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        jps.shortestPath(map, start, end);
        char[][] expected = {{'.', 'p', 'p', '.', '.'},
                             {'p', '@', '.', 'p', '@'},
                             {'.', '@', '@', '@', 'p'},
                             {'@', '@', '.', '.', 'j'},
                             {'.', '@', '.', '.', 'p'}};
        assertArrayEquals(expected, jps.getPath());
    }
    
}
