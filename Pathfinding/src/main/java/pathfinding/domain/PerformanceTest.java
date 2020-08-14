package pathfinding.domain;

/**
 *
 * Class handles performance testing
 */
public class PerformanceTest {

    char[][] map;
    int[] start;
    int[] end;

    public PerformanceTest(char[][] map, int[] start, int[] end) {
        this.map = map;
        this.start = start;
        this.end = end;
    }

    public long test(int algo) {
        long sum = 0;
        switch (algo) {
            case 1:
                BFS b = new BFS();
                for (int i = 0; i < 5; i++) {
                    long s = System.nanoTime();
                    b.shortestPath(map, start, end);
                    long e = System.nanoTime();
                    if (i != 0) {
                        long result = e - s;
                        sum += result;
                    }
                }
            case 2:
                AStar a = new AStar();
                for (int i = 0; i < 5; i++) {
                    long s = System.nanoTime();
                    a.shortestPath(map, start, end);
                    long e = System.nanoTime();
                    if (i != 0) {
                        long result = e - s;
                        sum += result;
                    }
                }
            default:
                break;
        }       
        return Math.round((sum / 4) / 1e6);
    }

}