package pathfinding.domain;

//import java.util.ArrayDeque;

/**
 *
 * Class contains bfs-algorithm
 */
public class Bfs {

    private char[][] grid; //map as a grid
    private boolean[][] visited; //keeps track of which nodes have been visited
    private int gridLength;
    private int gridWidth;
    private int pathLength;
    private int current; //nodes in the current layer
    private int next; //nodes in the next layer
    private int[] endPoint;
    private Queue xQueue;
    private Queue yQueue;
    //ArrayDeque<Integer> xQueue; //queue for x-coordinates
    //ArrayDeque<Integer> yQueue; //queue for y-coordinates

    //possible directions
    private final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
                                       {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    /**
     * Returns the shortest path from the starting point to the ending point.
     *
     * @param map map as a grid
     * @param start starting point
     * @param end ending point
     * @return length of the shortest path if a path is found. else -1
     */
    public int shortestPath(char[][] map, int[] start, int[] end) {
        //xQueue = new ArrayDeque<>();
        //yQueue = new ArrayDeque<>();
        gridLength = map.length;
        gridWidth = map[0].length;
        int capacity = gridLength * gridWidth;
        xQueue = new Queue(capacity);
        yQueue = new Queue(capacity);
        pathLength = 0;
        next = 0;
        current = 1;
        grid = map;
        visited = new boolean[gridLength][gridWidth];
        endPoint = end;

        if (search(start[0], start[1])) {
            return pathLength;
        }
        return -1;

    }

    /**
     * Processes the neighboring nodes.
     *
     * Takes the x and y coordinates of a node as parameters, adds all of their
     * valid adjecent coordinates to their respective queues, marks the
     * neighboring nodes as visited and increments the number of nodes in the
     * next layer.
     *
     * @param y the y-coordinate of the node whose neighbors are being processed
     * @param x the x-coordinate of the node whose neighbors are being processed
     */
    private void neighbors(int y, int x) {
        for (int i = 0; i < 8; i++) {
            int newY = y + direction[i][0];
            int newX = x + direction[i][1];

            //checks invalid coordinates
            if (newY < 0 || newX < 0 || newY >= gridLength
                    || newX >= gridWidth || visited[newY][newX]
                    || grid[newY][newX] == '@') {

                continue;
            }

            yQueue.add(newY);
            xQueue.add(newX);
            visited[newY][newX] = true;
            next++;
        }
    }

    /**
     * Handles the search.
     *
     * Takes the x and y coordinates of the starting point and adds them to
     * their respective queues. Marks the node as visited and calls the neighbor
     * function. Reduces the number of nodes in the current layer. If the number
     * of nodes in the current layer reaches zero increments the number of steps
     * taken.
     *
     * @param y the y-coordinate of the starting point
     * @param x the x-coordinate of the starting point
     * @return true if the end point was reached, else false
     */
    private boolean search(int y, int x) {
        yQueue.add(y);
        xQueue.add(x);
        visited[y][x] = true;

        while (!yQueue.isEmpty()) {
            y = yQueue.poll();
            x = xQueue.poll();
            if (y == endPoint[0] && x == endPoint[1]) {
                return true;
            }
            neighbors(y, x);
            current--;
            if (current == 0) {
                current = next;
                next = 0;
                pathLength++;
            }
        }
        return false;
    }

}
