
package pathfinding.datastructures;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author hiski
 */
public class QueueTest {
    
    Queue q;
    
    @Before
    public void setUp() {
        q = new Queue(10);
    }
    
    @Test
    public void aNewQueueIsEmpty() {
        assertTrue(q.isEmpty());
    }
    
    @Test
    public void isEmptyReturnsFalseIfQueueIsNotEmpty() {
        Node added = new Node(1, 1, 1, 0);
        q.add(added);
        assertFalse(q.isEmpty());
    }
    
   @Test
   public void queuePollReturnsTheCorrectValue() {
       Node added1 = new Node(1, 1, 1, 0);
       Node added2 = new Node(2, 2, 2, 0);
       q.add(added1);
       q.add(added2);
       assertEquals(1, q.poll().getX());
       assertEquals(2, q.poll().getX());
   }
}
