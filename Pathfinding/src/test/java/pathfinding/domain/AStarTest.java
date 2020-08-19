package pathfinding.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author hiski
 */
public class AStarTest {

    AStar astr;
    char[][] map = {{'.', '.', '.', '.', '.'},
                    {'.', '@', '.', '.', '@'},
                    {'.', '@', '@', '@', '.'},
                    {'@', '@', '.', '.', '.'},
                    {'.', '@', '.', '.', '.'}};

    @Before
    public void setUp() {
        astr = new AStar();

    }

    @Test
    public void shortestPathReturnsTheCorrectLengthWhenPathIsFound() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        assertTrue(4 + 2 * Math.sqrt(2) == astr.shortestPath(map, start, end));
    }

    @Test
    public void shortestPathReturnsMinusOneWhenPathIsNotFound() {
        int[] start = {0, 0};
        int[] end = {4, 0};
        assertTrue(-1 == astr.shortestPath(map, start, end));
    }
    
    @Test
    public void getPathReturnsTheCorrectPath() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        astr.shortestPath(map, start, end);
        char[][] path = astr.getPath();
        assertEquals('a', path[1][0]);
        assertEquals('a', path[0][1]);
        assertEquals('a', path[1][2]);
        assertEquals('a', path[1][3]);
        assertEquals('a', path[2][4]);
        assertEquals('a', path[3][4]);
        assertEquals('a', path[4][4]);
        
    }
}
