package pathfinding.datastructures;

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
     * Adds a node in the back of the queue.
     *
     * @param node int value that is added to the queue.
     */
    public void add(Node node) {
        array[tail] = node;
        tail++;
        size++;
    }

    /**
     *
     * Removes a node from the front of the queue and returns it.
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
