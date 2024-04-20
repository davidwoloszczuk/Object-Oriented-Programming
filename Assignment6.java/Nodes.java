import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Nodes {
    private ArrayList<INode> nodes;

    public Nodes() {
        nodes = new ArrayList<>();
    }

    public void fill(int size) throws Exception {
        nodes.clear();
        for (int i = 0; i < size; i++) {
            if (Math.random() < 0.5) {
                nodes.add(NodeFactory.getNode());
            } else {
                nodes.add(NodeFactory.getThreeDNode());
            }
        }
    }

    public void clear() {
        nodes.clear();
    }

    public int countNodes() {
        int count = 0;
        for (INode node : nodes) {
            if (node instanceof Node && !(node instanceof ThreeDNode)) {
                count++;
            }
        }
        return count;
    }

    public int countThreeDNodes() {
        int count = 0;
        for (INode node : nodes) {
            if (node instanceof ThreeDNode) {
                count++;
            }
        }
        return count;
    }

    public void sort() {
        Collections.sort(nodes, new Sorter());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (INode node : nodes) {
            sb.append(node.toString()).append("\n");
        }
        return sb.toString();
    }

    private static class Sorter implements Comparator<INode> {
        @Override
        public int compare(INode node1, INode node2) {
            int sum1 = node1.getX() + node1.getY();
            int sum2 = node2.getX() + node2.getY();
            if (node1 instanceof ThreeDNode) {
                sum1 += ((ThreeDNode) node1).getZ();
            }
            if (node2 instanceof ThreeDNode) {
                sum2 += ((ThreeDNode) node2).getZ();
            }
            return Integer.compare(sum1, sum2);
        }
    }
}