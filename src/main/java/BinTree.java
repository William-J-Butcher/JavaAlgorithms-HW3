public class BinTree {
    Node root;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            root.color = Color.black;
            return true;
        }
        if (addNode(root, value) != null)
            return true;
        return false;

    }
    private Node addNode(Node node, int value) {
        if (node.value == value)
            return null;
        if (node.value > value) {
            if (node.left == null) {
                node.left = new Node(value);
                node.left.parent = node;
                return node.left;
            } else
                return addNode(node.left, value);
        } else  {
            if (node.right == null) {
                node.right = new Node(value);
                node.right.parent = node;
                return node.right;
            } else
                return addNode(node.right, value);
        }
    }
    public boolean contain(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == value)
                return true;
            if (currentNode.value > value)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }

    private void leftRotate(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null)
            rightChild.left.parent = node;
        rightChild.parent = node.parent;
        if (node.parent == null)
            root = rightChild;
        else if (node == node.parent.left)
            node.parent.left = rightChild;
        else
            node.parent.right = rightChild;
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rightRotate(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null)
            leftChild.right.parent = node;
        leftChild.parent = node.parent;
        if (node.parent == null)
            root = leftChild;
        else if (node == node.parent.right)
            node.parent.right = leftChild;
        else
            node.parent.left = leftChild;
        leftChild.right = node;
        node.parent = leftChild;
    }

    private void fixInsertion(Node node) {
        while (node != root && node.parent.color == Color.red) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                if (uncle != null && uncle.color == Color.red) {
                    node.parent.color = Color.black;
                    uncle.color = Color.black;
                    node.parent.parent.color = Color.red;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = Color.black;
                    node.parent.parent.color = Color.red;
                    rightRotate(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;
                if (uncle != null && uncle.color == Color.red) {
                    node.parent.color = Color.black;
                    uncle.color = Color.black;
                    node.parent.parent.color = Color.red;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = Color.black;
                    node.parent.parent.color = Color.red;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = Color.black;
    }

    private class Node {
        int value;
        Node parent;
        Node left;
        Node right;
        Color color;

        Node() {
            this.color = Color.red;
        }

        Node(int _value) {
            this.value = _value;
            this.color = Color.red;
        }
    }

    enum Color {red, black}
}