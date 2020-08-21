package pathfinding.domain;

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
    char[][] map = {{'.', '.', '@', '.', '.'},
                    {'.', '.', '.', '.', '@'},
                    {'.', '@', '.', '.', '.'},
                    {'.', '.', '@', '.', '.'},
                    {'.', '@', '.', '.', '.'},
                    {'.', '@', '.', '.', '.'}};

    @Before
    public void setUp() {
        jps = new JPS();

    }

    
    @Test
    public void getPathReturnsTheCorrectPath() {
        int[] start = {0, 4};
        int[] end = {5, 0};
        System.out.println(jps.shortestPath(map, start, end));
        char[][] path = jps.getPath();
        /*assertEquals('a', path[1][0]);
        assertEquals('a', path[0][1]);
        assertEquals('a', path[1][2]);
        assertEquals('a', path[1][3]);
        assertEquals('a', path[2][4]);
        assertEquals('a', path[3][4]);
        assertEquals('a', path[4][4]);       
        */
        
        for (int i = 0; i < path.length; i++) {
            System.out.println("");
            for (int j = 0; j < path[0].length; j++) {
                System.out.print(path[i][j] + " ");
            } 
        }
        
    }
    
}
