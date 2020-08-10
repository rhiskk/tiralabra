
package pathfinding.domain;

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