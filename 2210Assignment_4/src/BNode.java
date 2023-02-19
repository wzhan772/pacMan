/**
 * @author William Zhang - 251215208
 * this class represetns the nodes of the binary search tree
 * each node stores an object of the class Pel and must have references to its left and right child, as well as its parent
 */

public class BNode {
	private Pel value;
	private BNode leftNode;
	private BNode rightNode;
	private BNode parent; 
	
	public BNode (Pel value, BNode left, BNode right, BNode parent) {
		this.value = value;
		this.leftNode = left;
		this.rightNode = right;
		this.parent = parent;
	}
	//set to null
	public BNode () {
		this.value = null;
		this.leftNode = null;
		this.rightNode = null;
		this.parent = null;
	}
	//get parent
	public BNode parent() {
		return this.parent;
	}
	//set parent
	public void setParent(BNode newParent) {
		this.parent = newParent;
	}
	//set left 
	public void setLeftChild(BNode p) {
		leftNode = p;
	}
	//set right 
	public void setRightChild (BNode p) {
		rightNode = p;
	}
	//set content
	public void setContent (Pel value) {
		this.value = value;
	}
	//check to see if node is a leaf
	public boolean isLeaf() {
		if (this.leftNode == null && this.rightNode == null) {
			return true;
		} else {
			return false;
		}
	}
	//get data
	public Pel getData() {
		return value;
	}
	//get left child
	public BNode leftChild() {
		return leftNode;
	}
	//get right child
	public BNode rightChild() {
		return rightNode;
	}
	
}