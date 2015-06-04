package structures.trees.avl;
/**
 * 
 * Implementation of AVL tree
 * 
 * @author shaun.viguerie
 *
 */
public class AVLTree {

	private AVLNode root;

	public AVLTree() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int countNodes() {
		return countNodes(root);
	}

	private int countNodes(AVLNode node) {
		if (node == null) {
			return 0;
		} else {
			int nodeCt = 1;
			nodeCt += countNodes(node.left);
			nodeCt += countNodes(node.right);
			return nodeCt;
		}
	}

	public void insert(int data) {
		root = insert(root, data);
	}

	private AVLNode insert(AVLNode node, int data) {
		if (node == null) {
			node = new AVLNode(data);
		} else if (data < node.data) {
			node.left = insert(node.left, data);

			if (height(node.left) - height(node.right) == 2) {
				if (data < node.left.data) {
					node = rotateWithLeftChild(node); // this is where self
														// balancing occurs
				} else {
					node = doubleWithLeftChild(node); // this is where self
														// balancing occurs
				}
			}
		} else if (data > node.data) {
			node.right = insert(node.right, data);
			if (height(node.right) - height(node.left) == 2) {
				if (data > node.right.data) {
					node = rotateWithRightChild(node); // this is where self
														// balancing occurs
				} else {
					node = doubleWithRightChild(node); // this is where self
														// balancing occurs
				}
			}
		}

		node.height = max(height(node.left), height(node.right)) + 1; // + 1 to
																		// include
																		// the
																		// root
																		// level

		return node;
	}

	public boolean search(int value) {
		return search(root, value);
	}

	private boolean search(AVLNode node, int val) {
		boolean found = false;

		while ((node != null) && !found) {
			int nodeVal = node.data;
			if (val < nodeVal) {
				node = node.left;
			} else if (val > nodeVal) {
				node = node.right;
			} else {
				found = true;
				break;
			}

			found = search(node, val);
		}
		return found;
	}

	/* rotate a binary tree node with left child */
	private AVLNode rotateWithLeftChild(AVLNode n) {

		AVLNode n1 = n.left;
		n.left = n1.right;
		n1.right = n;

		n.height = max(height(n.left), height(n.right)) + 1;
		n1.height = max(height(n1.left), n.height) + 1;

		return n1;
	}

	private AVLNode rotateWithRightChild(AVLNode n) {

		// now for the PWNING....
		AVLNode n1 = n.right;
		n.right = n1.left;
		n1.left = n;

		n.height = max(height(n.left), height(n.right)) + 1;
		n1.height = (max(height(n1.right), n.height)) + 1;

		return n1;
	}

	/**
	 * Double rotate binary tree node: first left child with its right child;
	 * then node k3 with new left child
	 */
	private AVLNode doubleWithLeftChild(AVLNode node) {
		node.left = rotateWithRightChild(node.left);
		return rotateWithLeftChild(node);
	}

	private AVLNode doubleWithRightChild(AVLNode node) {
		node.right = rotateWithLeftChild(node.right);
		return rotateWithRightChild(node);
	}

	private int height(AVLNode t) {
		return t == null ? -1 : t.height;
	}

	private int max(int left, int right) {
		return left > right ? left : right;
	}

	public void inorder() {
		inorder(root);
	}

	private void inorder(AVLNode r) {
		if (r != null) {
			inorder(r.left);
			System.out.print(r.data + " ");
			inorder(r.right);
		}
	}

	/* Function for preorder traversal */
	public void preorder() {
		preorder(root);
	}

	private void preorder(AVLNode r) {
		if (r != null) {
			System.out.print(r.data + " ");
			preorder(r.left);
			preorder(r.right);
		}
	}

	/* Function for postorder traversal */
	public void postorder() {
		postorder(root);
	}

	private void postorder(AVLNode r) {
		if (r != null) {
			postorder(r.left);
			postorder(r.right);
			System.out.print(r.data + " ");
		}
	}
	
	public void makeEmpty() {
		root = null;
	}


}
