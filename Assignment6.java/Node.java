public class Node implements INode {
    private int x;
    private int y;

    public Node() throws Exception {
        this(DEFAULT_X, DEFAULT_Y);
    }

    public Node(int x, int y) throws Exception {
        setX(x);
        setY(y);
    }

    public Node(Node node) throws Exception {
        setX(node.getX());
        setY(node.getY());
    }

    private boolean isValidX(int x) {
        return x >= LOWER_LIMIT && x <= UPPER_LIMIT;
    }

    private boolean isValidY(int y) {
        return y >= LOWER_LIMIT && y <= UPPER_LIMIT;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) throws Exception {
        if (isValidX(x)) {
            this.x = x;
        } else {
            throw new Exception("Invalid operation: x value should be in the range: [" + LOWER_LIMIT + "," + UPPER_LIMIT + "]");
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws Exception {
        if (isValidY(y)) {
            this.y = y;
        } else {
            throw new Exception("Invalid operation: y value should be in the range: [" + LOWER_LIMIT + "," + UPPER_LIMIT + "]");
        }
    }

    public void add(Node node) throws Exception {
        if (isValidX(this.x + node.getX()) && isValidY(this.y + node.getY())) {
            this.x += node.getX();
            this.y += node.getY();
        } else {
            throw new Exception("Invalid operation, the result exceeds the xy-space boundaries...");
        }
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node ref = (Node) obj;
            return this.x == ref.x && this.y == ref.y;
        }
        return false;
    }

    @Override
    public int compareTo(INode o) {
        return 0;
    }
}