package pathfinding.algorithms;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author hiski
 */
public class BfsTest {

    BFS bfs;
    char[][] map = {{'.', '.', '.', '.', '.'},
                    {'.', '@', '.', '.', '@'},
                    {'.', '@', '@', '@', '.'},
                    {'@', '@', '.', '.', '.'},
                    {'.', '@', '.', '.', '.'}};

    @Before
    public void setUp() {
        bfs = new BFS();

    }

    @Test
    public void shortestPathReturnsTheCorrectLengthWhenPathIsFound() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        
        assertEquals(3 + 3 * Math.sqrt(2), bfs.shortestPath(map, start, end), 5);
    }
    
    @Test
    public void getOperationsReturnsTheCorrectAmount() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        bfs.shortestPath(map, start, end);
        assertEquals(30, bfs.getOperations());
    }
    
    @Test
    public void shortestPathReturnsMinusOneWhenPathIsNotFound() {
        int[] start = {0, 0};
        int[] end = {4, 0};
        assertEquals(-1, bfs.shortestPath(map, start, end), 2);
    }
    
    @Test
    public void getPathReturnsTheCorrectPath() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        bfs.shortestPath(map, start, end);
        char[][] expected = {{'.', 'b', '.', '.', '.'},
                             {'b', '@', 'b', 'b', '@'},
                             {'.', '@', '@', '@', 'b'},
                             {'@', '@', '.', '.', 'b'},
                             {'.', '@', '.', '.', 'b'}};
        assertArrayEquals(expected, bfs.getPath());
    }

}
