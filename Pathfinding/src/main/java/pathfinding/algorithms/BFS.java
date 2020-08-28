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
    private Queue Queue;
    private Node endNode;
    private char[][] path;

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
    public double shortestPath(char[][] map, int[] start, int[] end) {
        gridLength = map.length;
        gridWidth = map[0].length;
        int capacity = gridLength * gridWidth;
        Queue = new Queue(capacity);
        pathLength = 0;
        grid = map;
        visited = new boolean[gridLength][gridWidth];
        endPoint = end;
        if (search(start[0], start[1])) {
            constructPath();
            return pathLength;
        }
        return -1;
    }
    
    
     /**
     * Constructs the found path and calculates its length.
     *
     */
    private void constructPath() {
        path = grid;
            Node node = endNode;
            while (node != null) {
                path[node.getY()][node.getX()] = 'b';
                pathLength += node.getG();
                node = node.getParent();
            }
    }
    
    /**
     * Processes the neighboring nodes.
     *
     * Takes the x and y coordinates of a node as parameters, adds all of its
     * valid adjecent nodes to the queue, marks them as visited.
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

            Node newNode = new Node(newY, newX, weight, 0);
            newNode.setParent(node);
            Queue.add(newNode);
            visited[newY][newX] = true;
        }
    }

    /**
     * Handles the search.
     *
     * Takes the x and y coordinates of the starting point, creates a new node 
     * and adds it to the queue. Marks the node as visited and calls the neighbor
     * function.
     *
     * @param y the y-coordinate of the starting point
     * @param x the x-coordinate of the starting point
     * @return true if the end point was reached, else false
     */
    private boolean search(int y, int x) {
        Queue.add(new Node(y, x, 0, 0));
        visited[y][x] = true;

        while (!Queue.isEmpty()) {
            Node node = Queue.poll();
            if (node.getY() == endPoint[0] && node.getX() == endPoint[1]) {
                endNode = node;
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
        return path;
    }

}
