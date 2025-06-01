package week13;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTBalanceTest{

	class Node {
		int key;
		Node left, right, parent;
		
		Node(int d){
			key = d;
			left=null;
			right=null;
			parent=null;
		}
		Node(int d, Node p){
			key = d;
			left=null;
			right=null;
			parent=p;
		}
		
		public String toString() {
			return ""+key+" ";
		}
	}
	
	Node root;
	
	public BSTBalanceTest() {
		root=null;
	}
	
	public void insert(int d) {
		if (root==null)
			root=new Node(d);
		else {
//			Node parent=null;
//			Node node = root;
//			while(node!=null) {
//				parent=node;
//				if (d<node.key)
//					node=node.left;
//				else
//					node=node.right;
//			}
//			Node newNode = new Node(d);
//			if (d<parent.key)
//				parent.left = newNode;
//			else
//				parent.right = newNode;
			
			// advanced code...
			Node prt = null;
			insert(root, d, prt);
		}

	}
	
	private Node insert(Node node, int d, Node p) {
		if (node==null)
			return new Node(d, p);
		if (d<node.key) {
			node.left = insert(node.left, d, node);
			return node;
		}
		else {
			node.right = insert(node.right, d, node);
			return node;
		}
	}

	public void rotateLeft() {
		rotateLeft(root);
	}
	private void rotateLeft(Node pp) {
		if (pp==root) {
			root=pp.right;
			pp.right.parent=null; // ie. root!
			pp.right.left=pp;
			pp.parent=pp.right;
			pp.right=null;  // 임시!
		}
		else {
			if (pp==pp.parent.left) {
				pp.parent.left=pp.right;
				pp.right.parent=pp.parent;
				pp.right.left=pp;
				pp.parent=pp.right;
				pp.right=null;
			}
			else { // ie. pp==pp.parent.right
				pp.parent.right=pp.right;
				pp.right.parent=pp.parent;
				pp.right.left=pp;
				pp.parent=pp.right;
				pp.right=null;
			}
		}
	}
	
	public void showTree() {
		System.out.println();
		showTree(root);
		System.out.println();
	}

	private void showTree(Node p) {
		if (p!=null) {
			showTree(p.left);
			System.out.print(p.toString());
			showTree(p.right);
		}
	}
	public void showLevel() {
		if (root!=null)
			showLevel(root);
	}

	private void showLevel(Node node) {
		Deque<Node> q = new ArrayDeque<>();
		q.add(node);
		int curLevel=-1;
		while(!q.isEmpty()) {
			Node p = q.removeFirst();
			if (curLevel<level(p)) {
				curLevel++;
				System.out.print("\nLevel "+curLevel+" : ");
			}
			System.out.print(p.key);
			if (p.left!=null) q.add(p.left);
			if(p.right!=null) q.add(p.right);
		}
	}

	private int level(Node node) {
		if (node==root)
			return 0;
		else 
			return 1+level(node.parent);
	}

	public static void main(String[] args) {

		BSTBalanceTest t = new BSTBalanceTest();
		
		for (int i=0; i<15;i++) {
			t.insert(i);
			t.showTree();
		}
		
		
		System.out.println("\nBefore Rotate");
		t.showLevel();
		
		t.rotateLeft();
		t.rotateLeft();
		t.rotateLeft();
		t.rotateLeft();
		t.rotateLeft();
		System.out.println("\nAfter Rotate");
		t.showLevel();


	}

}
