import java.util.Random;

public class NodeFactory {
   
    public static ThreeDNode getThreeDNode() throws Exception {
        Random r = new Random();
        int z = INode.LOWER_LIMIT + r.nextInt(INode.UPPER_LIMIT - INode.LOWER_LIMIT + 1);
        Node node = getNode();
        return new ThreeDNode(node.getX(), node.getY(), z);
    }

    public static Node getNode() throws Exception {
        Random r = new Random();
        int x = INode.LOWER_LIMIT + r.nextInt(INode.UPPER_LIMIT - INode.LOWER_LIMIT + 1);
        int y = INode.LOWER_LIMIT + r.nextInt(INode.UPPER_LIMIT - INode.LOWER_LIMIT + 1);
        return new Node(x, y);
    }
}