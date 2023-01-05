package lab09;

// Chapter 17.2 - page 1035 (modified)
// Simple binary tree class that includes methods to construct a tree of ints, to print the data using an
// inorder traversal. The trees build have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the trees.
import java.lang.*;
public class IntTree {
	private IntNode root;
	
	public IntTree(int[] arr){
		if (arr.length == 0)
			throw new IllegalArgumentException("empty array");
		root = builTreeArray(1, arr);
	}

	private IntNode builTreeArray(int n, int[] arr){
		if (n > arr.length){
			return null;
		}else{
			if (arr[n-1] != -1){
				return new IntNode(arr[n-1], builTreeArray(2*n, arr), builTreeArray(2*n+1, arr));
			}else{
				return null;
			}
		}
	}

	// pre: max >= 0 (throws IllegalArgumentException if not)
	// post: constructs a sequential tree with given number of nodes
	public IntTree(int max){
		if (max < 0){
			throw new IllegalArgumentException("max: " + max);
		}
		root = buildTree(1, max);
	}

	// post: returns a sequential tree with n as its root unless n is greater than max, in which case it
	// returns an empty tree
	private IntNode buildTree(int n, int max){
		if (n > max){
			return null;
		}else{
			return new IntNode(n, buildTree(2*n, max), buildTree(2*n+1, max));
		}
	}

	public String getInorder(){
		return getInorder(root);
	}

	private String getInorder(IntNode root){
		if (root != null){
			return (getInorder(root.left) + " " + root.data + " " + getInorder(root.right));
		}else{
			return "";
		}
	}

	// post: prints the tree contents using an inorder traversal
	public void printInorder(){
		System.out.print("inorder:");
		printInorder(root);
		System.out.println();
	}

	// post: prints in inorder the tree with given root
	private void printInorder(IntNode root){
		if (root != null){
			printInorder(root.left);
			System.out.print(" " + root.data);
			printInorder(root.right);
		}
	}


	// post: prints the tree contents, one per line, following an inorder traversal and using indentation to
	//indicate node depth; prints right to left so that it looks correct when the output is rotated.
	public void printSideways(){
		printSideways(root, 0);
	}

	// post: prints in reversed preorder the tree with given root, indenting each line to the given level
	private void printSideways(IntNode root, int level){
		if (root != null){
			printSideways(root.right, level+1);
			for (int i = 0; i < level; i++){
				System.out.print("       ");
			}
			System.out.println(root.data);
			printSideways(root.left, level+1);
		}
	}

	//==================================================

	// A binary tree is "lucky" if it contains at least 3 occurrences of some value. Write a method
	// called luckyTree that checks to see if the given value occurs at least three times. If the
	// tree is "lucky", return true; otherwise, return false.
	
	private int countNum(IntNode n, int value, int count, int level) {
		if(n == null) {
			return 0;
		}
		if(n.data == value && level > 0) {
			return 1;
		}
		else if(n.data == value) {
			return  1 + countNum(n.left,value,count,level - 1)+countNum(n.right,value,count, level-1);
		}
		return countNum(n.left,value,count,level - 1)+countNum(n.right,value,count, level-1);
		
	}
	private boolean luckyTree(IntNode n, int value, int count) {
		int level = 0;
		int ans = countNum(n,value,count, level);
		if(ans >= 3) {
			return true;
		}else {
			return false;
		}
	}
	public boolean luckyTree(int value) {
		return luckyTree(root, value, 0);
	}

	// Write a method called perfectify that adds nodes until the binary tree is a perfect tree. A perfect
	// binary tree is one where all leaves are at the same level. Another way of thinking of it is that you
	// are adding dummy nodes to the tree until every path from the root to a leaf is the same length. A
	// perfect tree's shape is triangular and every branch node has exactly two children, and all of the
	// leaves are at the same level. Each new node you add to the tree should store the value -1.
	private void perfectify(IntNode n, int depth) {
		while(depth > 1 ) {
			if(n == null) {
				break;
			}else if(n.left != null && n.right == null) {
				n.right = new IntNode(-1);
			}else if(n.left == null && n.right != null) {
				n.left = new IntNode(-1);
			}else if(n.left == null && n.right == null) {
				n.left = new IntNode(-1);
				n.right = new IntNode(-1);
			}
			--depth;
			perfectify(n.left, depth);
			perfectify(n.right, depth);
		}
	}
	public void perfectify(){
		int depth = getDepth();
		perfectify(root, depth);
	}


	// This method returns the depth of the tree as an integer.
	// We will use the same convention as in printLevel where the top root is at depth 1,
	// its children are at depth 2, and so on.
	// If the tree is empty, the method should return 0.
	private int getMax(int num1, int num2) {
		if(num1 > num2) {
			return num1;
		}
		return num2;
	}
	private int getDepth(IntNode n, int depth) {
		if(n == null) {
			return depth;
		}else if(n.left == null && n.right == null) {
			return depth + 1;
		}else if(n.left == null) {
			return getDepth(n.right, depth + 1);
		}else if(n.right == null) {
			return getDepth(n.left, depth + 1);
		}
		return getMax(getDepth(n.left, depth + 1), getDepth(n.right, depth + 1));
	}
	public int getDepth() {
		return getDepth(root, 0);
	}

	// Write a method called printLevel that accepts an integer parameter n and returns the values at level n
	// as a string, from left to right, one per line . We will use the convention that the overall root is at level 1, its
	// children are at level 2, and so on. If there are no values at the level, your method should return an
	// empty string. Your method should return an empty string if it is passed a value for a level that
	// is less than 1. See the instruction for example output.
	private String printLevel(int level, IntNode n) {
		if(n == null || level == 0) {
			return "";
		}
		else if(level == 1) {
			return n.data + "\n";
		}
		else if(n.left == null && level >= 1) {
			return printLevel(level - 1, n.right);
		}else if(n.right == null && level >= 1) {
			return printLevel(level - 1, n.left);
		}
		return printLevel(level - 1, n.left) + printLevel(level - 1, n.right); 
	}
	public String printLevel(int level){
		return (printLevel(level, root));
	}

	// Write a method called numEmpty that returns the number of empty branches in a tree. An empty tree is
	// considered to have one empty branch (the tree itself). For nonempty trees, your methods should count the
	// total number of empty branches among the nodes of the tree. A leaf node has two empty branches, a node
	// with one nonempty child has one empty branch, and a node with two nonempty children has no empty branches.
	// See the instruction for example output.
	//helper method to check left is null right is null return 2
	//if both not empty call method twice return both nodes
	private int numEmpty(IntNode n) {
		if(n == null) {
			return 1;
		}
		else if(n.left == null && n.right == null) {
			return 2;
		}
		else if(n.left == null){
			return 1 + numEmpty(n.right);
		}else if(n.right == null) {
			return 1 + numEmpty(n.left);
		}
		return numEmpty(n.left) + numEmpty(n.right);
	}
	public int numEmpty(){
		return numEmpty(this.root);
	}

	// write a toString method for a binary tree of integers. The method should return "empty" for an empty tree.
	// For a leaf node, it should return the data in the node as a string. For a branch node, it should return a
	// parenthesized String that has three elements separately by commas: the data at the root, a string
	// representation of the left subtree, and then a string representation of the right subtree.
	// See the instruction for example output.
	private String toString(IntNode n) {
		if(n == null) {
			return "";
		}else if(n.left == null && n.right == null) {
			return n.data + "";
		}else if(n.left == null) {
			return "(" + n.data + ", empty, " + toString(n.right) + ")";
		}else if(n.right == null) {
			return "(" + n.data + ", " + toString(n.left) + ", empty)";
		}
		return "(" + n.data + ", " + toString(n.left) + ", " + toString(n.right) + ")";
	}
	public String toString(){
		return toString(root);
	}

}
