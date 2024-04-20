public interface INode extends Comparable<INode> {
    int LOWER_LIMIT = -100;
    int UPPER_LIMIT = 100;
    int DEFAULT_X = 0;
    int DEFAULT_Y = 0;

    void add(Node node) throws Exception;
    int getX();
    int getY();
}