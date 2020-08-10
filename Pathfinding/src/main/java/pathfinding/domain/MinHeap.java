package pathfinding.domain;

/**
 *
 * Min heap implementation
 */
public class MinHeap {

    Node[] heap;
    int size;
    int maxSize;

    /**
     *
     * @param max maximum size of the heap.
     */
    public MinHeap(int max) {
        this.heap = new Node[max + 1];
        this.maxSize = max;
        this.size = 0;
        heap[0] = new Node(-1, -1, -1, -1);
    }

    /**
     *
     * @param pos position of a node.
     * @return the location of the parent of the node.
     */
    private int parent(int pos) {
        return pos / 2;
    }

    /**
     *
     * @param pos position of a node.
     * @return the location of the left child of the node.
     */
    private int left(int pos) {
        return (2 * pos);
    }

    /**
     *
     * @param pos position of a node.
     * @return the location of the right child of the node.
     */
    private int right(int pos) {
        return (2 * pos) + 1;
    }

    /**
     * Checks if the given position is a leaf of the heap.
     * 
     * @param pos position of a node.
     * @return true if the node is a leaf, else false.
     */
    private boolean leaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    /**
     * Swaps the positions of two nodes.
     * 
     * @param pos1 position of a node.
     * @param pos2 position of another node.
     */
    private void swap(int pos1, int pos2) {
        Node temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    /**
     * Makes sure that the heap keeps the correct structure.
     * 
     * @param pos position of a node.
     */
    private void heapify(int pos) {
        if (!leaf(pos) && !isEmpty()) {
            if (heap[pos].compareTo(heap[left(pos)]) == 1
                    || heap[pos].compareTo(heap[right(pos)]) == 1) {

                if (heap[left(pos)].compareTo(heap[right(pos)]) == -1) {
                    swap(pos, left(pos));
                    heapify(left(pos));
                } else {
                    swap(pos, right(pos));
                    heapify(right(pos));
                }
            }
        }
    }

    /**
     * Adds a node to the heap.
     * 
     * @param node a node that is added to the heap.
     */
    public void add(Node node) {
        if (size >= maxSize) {
            return;
        }
        size++;
        heap[size] = node;
        int pos = size;
        while (heap[pos].compareTo(heap[parent(pos)]) == -1) {
            swap(pos, parent(pos));
            pos = parent(pos);
        }
    }

    /**
     * Removes a node from the heap and returns it.
     * 
     * @return the node that is removed from the heap.
     */
    public Node poll() {
        Node node = heap[1];
        heap[1] = heap[size];
        size--;
        heapify(1);
        return node;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, else false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

}
