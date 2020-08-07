package pathfinding.domain;

import java.util.PriorityQueue;

/**
 *
 * Class contains A*-algorithm
 */
public class AStar {

    /**
     *
     * Class represents a node
     */
    public class Node implements Comparable<Node> {

        int y; //y-coordinate
        int x; //x-coordinate
        int g; //distance from start
        int h; //heuristic
        int f; //distance from start + heuristic

        public Node(int y, int x, int g, int h) {
            this.y = y;
            this.x = x;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }

        @Override
        public int compareTo(Node other) {
            if (this.f == other.f) {
                return this.h > other.h ? +1 : -1;
            }
            return this.f > other.f ? +1 : -1;
        }
    }

    char[][] grid; //map as a grid
    int[][] gGrid; //grid containing distances from start
    boolean[][] visited; //keeps track of which nodes have been visited
    int gridLength;
    int gridWidth;
    int[] endPoint;
    PriorityQueue<Node> heap;

    //possible directions
    int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
                         {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    public int heuristic(int y, int x) {
        return Math.max(Math.abs(y - endPoint[0]), Math.abs(x - endPoint[1]));
    }

    public int shortestPath(char[][] map, int[] start, int[] end) {
        gridLength = map.length;
        gridWidth = map[0].length;
        grid = map;
        gGrid = new int[gridLength][gridWidth]; //grid containing g-values
        visited = new boolean[gridLength][gridWidth];
        endPoint = end;
        heap = new PriorityQueue<>();
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
    public void neighbors(int y, int x) {
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
    public boolean search(int y, int x) {
        gGrid[y][x] = 0;
        heap.add(new Node(y, x, 0, heuristic(y, x)));

        while (!heap.isEmpty()) {
            Node n = heap.poll();
            visited[n.y][n.x] = true;

            if (n.y == endPoint[0] && n.x == endPoint[1]) {
                return true;
            }

            neighbors(n.y, n.x);

        }
        return false;
    }

}
