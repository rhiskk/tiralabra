package pathfinding.datastructures;

/**
 *
 * Class represents a node
 */
public class Node implements Comparable<Node> {

    private int y; //y-coordinate
    private int x; //x-coordinate
    private double g; //distance from start
    private double h; //heuristic value
    private double f; //distance from start + heuristic
    private Node parent;

    public Node(int y, int x, double g, double h) {
        this.y = y;
        this.x = x;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }

    @Override
    public int compareTo(Node other) {
        if (this.f == other.f) {
            return this.g >= other.g ? 1 : -1;
        }
        return this.f >= other.f ? 1 : -1;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public Node getParent() {
        return parent;
    }
    
    public double getG() {
        return g;
    }
}
