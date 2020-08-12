
package pathfinding.domain;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author hiski
 */
public class MinHeapTest {
    
    MinHeap minH;
    Node[] testNodes;
    
    @Before
    public void setUp() {
        minH = new MinHeap(100);
        testNodes = new Node[100];
    }
    
    @Test
    public void aNewMinHeapIsEmpty() {
        assertTrue(minH.isEmpty());
    }
    
    @Test
    public void minHeapIsNotEmptyAfterAdding() {
        minH.add(new Node(0, 0, 1, 1));
        assertFalse(minH.isEmpty());
    }
    
    @Test(timeout=999999999)
    public void nodesArePolledInTheCorrectOrder() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            minH.add(new Node(1, 1, r.nextInt(100), r.nextInt(100)));
        }
        boolean t = true;
        for (int i = 0; i < 100; i++) {
            Node p1 = minH.poll();
            Node p2 = minH.poll();
            if (p1.compareTo(p2) == 1) {
                t = false;
            }
            i++;
        }
        assertTrue(t);
    }   
}
