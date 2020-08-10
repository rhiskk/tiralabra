package pathfinding.domain;

/**
 *
 * Queue implementation
 */
public class Queue {

    int[] array;
    int head;
    int tail;
    int size;

    /**
     *
     * @param capacity capacity of the queue.
     */
    public Queue(int capacity) {
        this.array = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    /**
     *
     * Adds an int value at the back of the queue.
     *
     * @param i int value that is added to the queue.
     */
    public void add(int i) {
        array[tail] = i;
        tail++;
        size++;
    }

    /**
     *
     * Removes an int value from the front of the queue.
     *
     * @return int value that is removed from the queue.
     */
    public int poll() {
        size--;
        int polled = head;
        head++;
        return array[polled];
    }

    /**
     *
     * Checks if the queue is empty.
     *
     * @return true if queue is empty, else false.
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
