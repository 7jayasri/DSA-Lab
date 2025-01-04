class AVLNode {
    int key;
    AVLNode left, right;
    int height;

    AVLNode(int key) {
        this.key = key;
        this.height = 0;
        this.left = this.right = null;
    }
}

class Main {

    public static int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    // Custom method to get maximum height, using conditional comparison instead of Math.max
    public static int maxHeight(int a, int b) {
        return (a > b) ? a : b;  // Manually returning the larger value
    }

    public static AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = maxHeight(height(k2.left), height(k2.right)) + 1;
        k1.height = maxHeight(height(k1.left), k2.height) + 1;
        return k1;
    }

    public static AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = maxHeight(height(k1.left), height(k1.right)) + 1;
        k2.height = maxHeight(height(k2.right), k1.height) + 1;
        return k2;
    }

    public static AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    public static AVLNode doubleWithRightChild(AVLNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public static AVLNode insert(int key, AVLNode node) {
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insert(key, node.left);
            if (height(node.left) - height(node.right) == 2) {
                if (key < node.left.key) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = doubleWithLeftChild(node);
                }
            }
        } else if (key > node.key) {
            node.right = insert(key, node.right);
            if (height(node.right) - height(node.left) == 2) {
                if (key > node.right.key) {
                    node = rotateWithRightChild(node);
                } else {
                    node = doubleWithRightChild(node);
                }
            }
        }

        node.height = maxHeight(height(node.left), height(node.right)) + 1;
        return node;
    }

    public static AVLNode findMin(AVLNode node) {
        if (node == null || node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    public static AVLNode delete(int key, AVLNode node) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(key, node.left);
        } else if (key > node.key) {
            node.right = delete(key, node.right);
        } else {
            if (node.left != null && node.right != null) {
                AVLNode minNode = findMin(node.right);
                node.key = minNode.key;
                node.right = delete(minNode.key, node.right);
            } else {
                node = (node.left != null) ? node.left : node.right;
            }
        }

        if (node != null) {
            node.height = maxHeight(height(node.left), height(node.right)) + 1;

            if (height(node.left) - height(node.right) == 2) {
                if (height(node.left.left) >= height(node.left.right)) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = doubleWithLeftChild(node);
                }
            } else if (height(node.right) - height(node.left) == 2) {
                if (height(node.right.right) >= height(node.right.left)) {
                    node = rotateWithRightChild(node);
                } else {
                    node = doubleWithRightChild(node);
                }
            }
        }
        return node;
    }

    public static void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLNode root = null;
        int[] array = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};

        for (int x : array) {
            root = insert(x, root);
            System.out.println("Height after inserting " + x + " is " + height(root));
        }

        System.out.println("\nIn-order traversal of the AVL tree: ");
        inOrder(root);

        root = delete(13, root);
        System.out.println("\nIn-order traversal after deleting 13: ");
        inOrder(root);
    }
}
