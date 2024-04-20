public class ThreeDNode extends Node {
    private int z;

    public ThreeDNode() throws Exception {
        super();
        this.z = 0;
    }

    public ThreeDNode(int x, int y, int z) throws Exception {
        super(x, y);
        setZ(z);
    }

    public ThreeDNode(ThreeDNode node) throws Exception {
        super(node.getX(), node.getY());
        setZ(node.getZ());
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) throws Exception {
        if (z >= INode.LOWER_LIMIT && z <= INode.UPPER_LIMIT) {
            this.z = z;
        } else {
            throw new Exception("Invalid operation: z value should be in the range: [" + INode.LOWER_LIMIT + "," + INode.UPPER_LIMIT + "]");
        }
    }

    @Override
    public void add(Node node) throws Exception {
        super.add(node);
        if (node instanceof ThreeDNode) {
            ThreeDNode threeDNode = (ThreeDNode) node;
            if (this.z + threeDNode.getZ() >= INode.LOWER_LIMIT && this.z + threeDNode.getZ() <= INode.UPPER_LIMIT) {
                this.z += threeDNode.getZ();
            } else {
                throw new Exception("Invalid operation, the result exceeds the z-space boundaries...");
            }
        }
    }

    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + "," + z + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ThreeDNode) {
            ThreeDNode ref = (ThreeDNode) obj;
            return super.equals(ref) && this.z == ref.z;
        }
        return false;
    }
}