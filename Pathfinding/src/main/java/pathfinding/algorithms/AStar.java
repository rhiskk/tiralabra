package pathfinding.algorithms;

import pathfinding.datastructures.MinHeap;
import pathfinding.datastructures.Node;

/**
 *
 * Class contains A*-algorithm
 */
public class AStar {

    private char[][] grid; //map as a grid
    private double[][] gGrid; //grid containing distances from start
    private boolean[][] visited; //keeps track of which nodes have been visited
    private int gridLength;
    private int gridWidth;
    private int[] endPoint;
    private MinHeap heap;
    private double pathLength;
    private Node endNode;

    //possible directions
    private final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
                                       {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    /**
     * Heuristic function.
     *
     * @param x yhe x-coordinate of the node
     * @param y the y-coordinate of the node
     * @return the diagonal distance from given coordinates to the end point.
     */
    private double heuristic(int y, int x) {
        return Math.max(Math.abs(y - endPoint[0]), Math.abs(x - endPoint[1]));
    }

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
        pathLength = 0;
        grid = map;
        gGrid = new double[gridLength][gridWidth]; //grid containing g-values
        visited = new boolean[gridLength][gridWidth];
        endPoint = end;
        heap = new MinHeap(gridLength * gridWidth);

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridWidth; j++) {
                gGrid[i][j] = Integer.MAX_VALUE;
            }
        }
        if (search(start[0], start[1])) {
            pathLength = gGrid[end[0]][end[1]]; 
            return pathLength;
        }

        return -1;
    }

    /**
     * Adds neighboring nodes to the heap.
     *
     * Takes a node as a parameter and adds all of its
     * valid neighboring nodes to the heap.
     *
     * @param node the node whose neighbors are being processed
     */
    private void neighbors(Node n) {
        for (int i = 0; i < 8; i++) {
            double weight = 1;
            if (i > 3) {
                weight = Math.sqrt(2);
            }
            int y = n.getY();
            int x = n.getX();
            int newY = y + direction[i][0];
            int newX = x + direction[i][1];
            //checks invalid coordinates
            if (newY < 0 || newX < 0 || newY >= gridLength
                    || newX >= gridWidth || visited[newY][newX]
                    || grid[newY][newX] == '@') {

                continue;
            }
            if (gGrid[newY][newX] > gGrid[y][x] + weight) {
                gGrid[newY][newX] = gGrid[y][x] + weight;
                Node newNode = new Node(newY, newX, gGrid[newY][newX],
                        heuristic(newY, newX));
                newNode.setParent(n);
                heap.add(newNode);
            }
        }
    }

    /**
     * Handles the search.
     *
     * @param y the y-coordinate of the starting point
     * @param x the x-coordinate of the starting point
     * @return true if the end point was reached, else false
     */
    private boolean search(int y, int x) {
        gGrid[y][x] = 0;
        heap.add(new Node(y, x, 0, heuristic(y, x)));

        while (!heap.isEmpty()) {
            Node n = heap.poll();
            int newY = n.getY();
            int newX = n.getX();
            if (!visited[newY][newX]) {
                visited[newY][newX] = true;

                if (newY == endPoint[0] && newX == endPoint[1]) {
                    endNode = n;                   
                    return true;
                }
                neighbors(n);
            }
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
            path[node.getY()][node.getX()] = 'a';
            node = node.getParent();
        }
        return path;
    }
}
