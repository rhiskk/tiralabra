package pathfinding.algorithms;

import pathfinding.datastructures.Node;
import pathfinding.datastructures.Queue;

/**
 *
 * Class contains bfs-algorithm
 */
public class BFS {

    private char[][] grid; //map as a grid
    private boolean[][] visited; //keeps track of which nodes have been visited
    private int gridLength;
    private int gridWidth;
    private double pathLength;
    private int[] endPoint;
    private Queue queue;
    private Node endNode;
    //keeps count of the most time consuming operations performed
    private int operations;

    //possible directions
    private final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
                                       {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    /**
     * Returns the shortest path from the start point to the end point.
     *
     * @param map map as a grid
     * @param start starting point
     * @param end ending point
     * @return length of the shortest path if a path is found. else -1
     */
    public double shortestPath(char[][] map, int[] start, int[] end) {
        gridLength = map.length;
        gridWidth = map[0].length;
        int capacity = gridLength * gridWidth;
        queue = new Queue(capacity);
        pathLength = 0;
        grid = map;
        visited = new boolean[gridLength][gridWidth];
        endPoint = end;
        operations = 0;

        if (map[start[0]][start[1]] == '@' || map[end[0]][end[1]] == '@') {
            return -1;
        }

        if (search(start[0], start[1])) {
            return pathLength;
        }
        return -1;
    }

    /**
     * Processes the neighboring nodes.
     *
     * Takes a node as a parameter, adds all of its valid adjecent nodes to the
     * queue and marks them as visited.
     *
     * @param y the y-coordinate of the node whose neighbors are being processed
     * @param x the x-coordinate of the node whose neighbors are being processed
     */
    private void neighbors(Node node) {
        for (int i = 0; i < 8; i++) {
            double weight = 1;
            if (i > 3) {
                weight = Math.sqrt(2);
            }
            int newY = node.getY() + direction[i][0];
            int newX = node.getX() + direction[i][1];

            //checks invalid coordinates
            if (newY < 0 || newX < 0 || newY >= gridLength
                    || newX >= gridWidth || visited[newY][newX]
                    || grid[newY][newX] == '@') {

                continue;
            }

            Node newNode = new Node(newY, newX, weight + node.getG(), 0);
            newNode.setParent(node);
            queue.add(newNode);
            operations++;
            visited[newY][newX] = true;
        }
    }

    /**
     * Handles the search.
     *
     * Takes the x and y coordinates of the starting point, creates a new node
     * and adds it to the queue. Marks the node as visited and calls the
     * neighbors function.
     *
     * @param y the y-coordinate of the starting point
     * @param x the x-coordinate of the starting point
     * @return true if the end point was reached, else false
     */
    private boolean search(int y, int x) {
        queue.add(new Node(y, x, 0, 0));
        operations++;
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            operations++;
            if (node.getY() == endPoint[0] && node.getX() == endPoint[1]) {
                endNode = node;
                pathLength = node.getG();
                return true;
            }
            neighbors(node);
        }
        return false;
    }

    /**
     * Returns the found path as an ASCII-grid.
     *
     * @return the found path as an ASCII-grid.
     */
    public char[][] getPath() {
        char[][] path = grid;
        Node node = endNode;
        while (node != null) {
            path[node.getY()][node.getX()] = 'b';
            node = node.getParent();
        }
        return path;
    }
    
    /**
     * Returns the number of the most time consuming operations performed.
     * The most time consuming operation is adding to the queue 
     * and pollling from it.
     *
     * @return the number of times nodes are added to the quque or polled from it.
     */
    public int getOperations() {
        return operations;
    }

}
