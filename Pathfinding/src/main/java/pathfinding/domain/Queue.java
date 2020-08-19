package pathfinding.domain;

/**
 *
 * Queue implementation
 */
public class Queue {

    private Node[] array;
    private int head;
    private int tail;
    private int size;

    /**
     *
     * @param capacity capacity of the queue.
     */
    public Queue(int capacity) {
        this.array = new Node[capacity];
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
    public void add(Node node) {
        array[tail] = node;
        tail++;
        size++;
    }

    /**
     *
     * Removes an int value from the front of the queue.
     *
     * @return int value that is removed from the queue.
     */
    public Node poll() {
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
