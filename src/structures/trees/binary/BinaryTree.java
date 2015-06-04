package structures.trees.binary;
/**
 * 
 *TODO review n-ary trees and make sure to know the pros/cons
 * of either in the real world. 
 * 
 * @author shaun.viguerie
 *
 */
public class BinaryTree {

	private TreeNode root;

	public BinaryTree() {

	}

	public BinaryTree(int startVal) {
		root = new TreeNode(startVal);
	}
	
	public void run() {
	    // build the simple tree from chapter 11.
	    root = new TreeNode(5);
	    System.out.println("Binary Tree Example");
	    System.out.println("Building tree with root value " + root.item);
	    insert( 1);
	    insert( 8);
	    insert( 6);
	    insert( 3);
	    insert( 9);
	    System.out.println("Traversing tree in order");
	    inOrderTraverse(root);
	    System.out.println("Traversing tree post order");
	    postOrderTraverse(root);
	 }
	
	public boolean isBinarySearchTree(TreeNode node, int min, int max) {
		
		if(node == null) {
			return true;
		}
		
		if(node.item >= min && node.item <= max
				&& isBinarySearchTree(node.left, min, node.item)
				&& isBinarySearchTree(node.right, node.item, max)){
			return true;
		}
		
		return false;
	}
	

	/**
	 * public method should not require user to pass a reference to the tree
	 * node!! (that would be fucking stupid, no?!)
	 */
	public void insert(int num) {
		insert(root, num);
	}

	private void insert(TreeNode node, int num) {

		if (node == null) {
			node = new TreeNode(num);
		} else {

			if (num < node.item) {
				if (node.left != null) {
					insert(node.left, num);
				} else {
					System.out.println("Inserting " + num + " to the left of "
							+ node.item);
					node.left = new TreeNode(num);
				}
			} else if (num > node.item) {
				if (node.right != null) {
					insert(node.right, num);
				} else {
					System.out.println("Inserting " + num + " to the right of "
							+ node.item);
					node.right = new TreeNode(num);
				}
			}
		}
	}
	
	public void preOrderTraverse(TreeNode node) {
		if(node != null	) {
			System.out.println(" Traversed " + node.item);
			preOrderTraverse(node.left);
			preOrderTraverse(node.right);
		}
	}
	
	public void inOrderTraverse(TreeNode node) {
		if (node != null) {
			inOrderTraverse(node.left);
			System.out.println("  Traversed " + node.item);
			inOrderTraverse(node.right);
		}
	}
	
	public void postOrderTraverse(TreeNode node) {
		if(node != null) {
			postOrderTraverse(node.left);
			postOrderTraverse(node.right);
			System.out.println("  Traversed " + node.item);
		}
	}

	public static void main(String[] args) {
		new BinaryTree().run();
	}
	
}
