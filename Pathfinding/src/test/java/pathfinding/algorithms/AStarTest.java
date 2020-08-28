package pathfinding.algorithms;

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
        assertEquals(3 + 3 * Math.sqrt(2), astr.shortestPath(map, start, end), 5);
    }

    @Test
    public void shortestPathReturnsMinusOneWhenPathIsNotFound() {
        int[] start = {0, 0};
        int[] end = {4, 0};
        assertEquals(-1, astr.shortestPath(map, start, end), 2);
    }
    
    @Test
    public void getPathReturnsTheCorrectPath() {
        int[] start = {4, 4};
        int[] end = {1, 0};
        astr.shortestPath(map, start, end);
        char[][] expected = {{'.', 'a', '.', '.', '.'},
                             {'a', '@', 'a', 'a', '@'},
                             {'.', '@', '@', '@', 'a'},
                             {'@', '@', '.', '.', 'a'},
                             {'.', '@', '.', '.', 'a'}};
        assertArrayEquals(expected, astr.getPath());
    }
}
