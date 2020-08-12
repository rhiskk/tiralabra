
package pathfinding.domain;

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
        q.add(1);
        assertFalse(q.isEmpty());
    }
    
   @Test
   public void queuePollReturnsTheCorrectValue() {
       q.add(1);
       q.add(2);
       assertEquals(1, q.poll());
       assertEquals(2, q.poll());
   }
}
