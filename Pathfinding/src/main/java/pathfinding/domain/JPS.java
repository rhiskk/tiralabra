package pathfinding.domain;

/**
 *
 * Class contains JPS-algorithm
 */
public class JPS {

    private char[][] grid; //map as a grid
    private int[][] gGrid; //grid containing distances from start
    private boolean[][] visited; //keeps track of which nodes have been visited
    private int gridLength;
    private int gridWidth;
    private int[] endPoint;
   

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

    public int shortestPath(char[][] map, int[] start, int[] end) {
        gridLength = map.length;
        gridWidth = map[0].length;
        grid = map;
        gGrid = new int[gridLength][gridWidth]; //grid containing g-values
        visited = new boolean[gridLength][gridWidth];
        endPoint = end;
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridWidth; j++) {
                gGrid[i][j] = Integer.MAX_VALUE;
            }
        }

        return -1;
    }

}

   