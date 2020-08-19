package pathfinding.domain;

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
        
        assertTrue(4 + 2 * Math.sqrt(2) == bfs.shortestPath(map, start, end));
    }

    @Test
    public void shortestPathReturnsMinusOneWhenPathIsNotFound() {
        int[] start = {0, 0};
        int[] end = {4, 0};
        assertTrue(-1 == bfs.shortestPath(map, start, end));
    }
    
    @Test
    public void getPathReturnsTheCorrectPath() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        bfs.shortestPath(map, start, end);
        char[][] path = bfs.getPath();
        assertEquals('b', path[1][0]);
        assertEquals('b', path[0][1]);
        assertEquals('b', path[1][2]);
        assertEquals('b', path[1][3]);
        assertEquals('b', path[2][4]);
        assertEquals('b', path[3][4]);
        assertEquals('b', path[4][4]);
    }

}
