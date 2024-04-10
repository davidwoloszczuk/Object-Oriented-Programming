 /** David Woloszczuk-Mrugala
 *   CPSC 24500
 *   03/21/23
 *   This is my attempt to make Node
 */


package a5;

public class Node {
    private int x;
    private int y;

    private static final int MIN_VALUE = -100;
    private static final int MAX_VALUE = 100;

    public Node() {
        this(0, 0);
    }

    public Node(Node node) {
        this(node.x, node.y);
    }

    public Node(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (isValid(x)) {
            this.x = x;
        } else {
            throw new IllegalArgumentException("Invalid x value: " + x);
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (isValid(y)) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Invalid y value: " + y);
        }
    }

    public void add(Node node) {
        int newX = this.x + node.x;
        int newY = this.y + node.y;
        if (isValid(newX) && isValid(newY)) {
            this.x = newX;
            this.y = newY;
        } else {
            throw new IllegalArgumentException("Invalid result after adding nodes");
        }
    }

    private boolean isValid(int value) {
        return value >= MIN_VALUE && value <= MAX_VALUE;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        return this.x == other.x && this.y == other.y;
    }
}