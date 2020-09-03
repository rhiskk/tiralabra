
package pathfinding.datastructures;

import java.util.Arrays;
import java.util.Collections;
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
        minH = new MinHeap(1024);
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
    
    @Test
    public void nodesArePolledInTheCorrectOrder() {
        Double[] arr = new Double[1024];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.sqrt(i);
        }
        Collections.shuffle(Arrays.asList(arr));
        
        for (int i = 0; i < arr.length; i++) {
            minH.add(new Node(i, i, arr[i], arr[++i]));
        }
        
        while (!minH.isEmpty()) {
            Node p1 = minH.poll();
            Node p2 = minH.poll();
            assertEquals(-1, p1.compareTo(p2));             
        }
    }
}
