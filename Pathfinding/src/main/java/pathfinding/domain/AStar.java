package pathfinding.domain;

/**
 *
 * Class contains A*-algorithm
 */
public class AStar {

    private char[][] grid; //map as a grid
    private int[][] gGrid; //grid containing distances from start
    private boolean[][] visited; //keeps track of which nodes have been visited
    private int gridLength;
    private int gridWidth;
    private int[] endPoint;
    private MinHeap heap;

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
    private int heuristic(int y, int x) {
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
    public int shortestPath(char[][] map, int[] start, int[] end) {
        gridLength = map.length;
        gridWidth = map[0].length;
        grid = map;
        gGrid = new int[gridLength][gridWidth]; //grid containing g-values
        visited = new boolean[gridLength][gridWidth];
        endPoint = end;
        heap = new MinHeap(999999);
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridWidth; j++) {
                gGrid[i][j] = Integer.MAX_VALUE;
            }
        }
        if (search(start[0], start[1])) {
            return gGrid[end[0]][end[1]];
        }

        return -1;
    }

    /**
     * Adds neighboring nodes to the heap.
     *
     * Takes the x and y coordinates of a node as parameters and adds all of its
     * valid neighboring nodes to the heap.
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
            if (gGrid[newY][newX] > gGrid[y][x] + 1) {
                gGrid[newY][newX] = gGrid[y][x] + 1;
                heap.add(new Node(newY, newX, gGrid[newY][newX],
                        heuristic(newY, newX)));
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
            visited[newY][newX] = true;

            if (newY == endPoint[0] && newX == endPoint[1]) {
                return true;
            }

            neighbors(newY, newX);

        }
        return false;
    }

}
