package week11;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleTree {

	class Node {
		char data;
		Node left;
		Node right;

		Node(char d) {
			data = d;
			left = null;
			right = null;
		}

		Node(Node lc, char d, Node rc) {
			data = d;
			left = lc;
			right = rc;
		}

		public String toString() {
			return "" + data;
		}
	}

	Node root;
	char[] array;


	SimpleTree() {
		root = null;
		array = new char[20];
	}

	public Node makeTree(char ch) {
		root = new Node(ch);
		return root;
	}

	public Node makeTree(SimpleTree leftSubtree, char ch, SimpleTree rightSubtree) {
//			root = new Node(ch);
//			root.left = leftSubtree.root;
//			root.right = rightSubtree.root;

		root = new Node(leftSubtree.root, ch, rightSubtree.root);
		return root;
	}

	public void showTree() {
		System.out.println("\n<Tree in Nodes>");
//		showTree(root); // recursion call

		inorder(root);
		System.out.println();
		preorder(root);
		System.out.println();
		postorder(root);
		System.out.println();
		levelorder(root);
		System.out.println();
	}

	private void showTree(Node p) {
		if (p != null) {
			System.out.print(p.data);
			showTree(p.left);
			showTree(p.right);
		}
	}

	public void inorder(Node p) {
		if (p != null) {
			System.out.print(p.data);
			showTree(p.left);
			showTree(p.right);
		}
	}

	public void preorder(Node p) {
		if (p != null) {
			System.out.print(p.data);
			showTree(p.left);
			showTree(p.right);
		}
	}

	public void postorder(Node p) {
		if (p != null) {
			System.out.print(p.data);
			showTree(p.left);
			showTree(p.right);
		}
	}

	public void levelorder(Node p) {
		Deque<Node> queue = new ArrayDeque<>();

		queue.addLast(root);

		while (!queue.isEmpty()) {
			Node node = queue.removeFirst();
			if(node != null) {
				System.out.print(node.data);
				if(node.left != null) {
					queue.addLast(node.left);
				}
				if(node.right != null) {
					queue.addLast(node.right);
				}
			}
		}
	}

	public void toArray() {
		toArray(root, 1);
		System.out.println("\n<Tree in Array>");
		for (int i = 1; i <= 7; i++) {
			System.out.print(array[i] + "   ");
		}
		System.out.println();
	}

	private void toArray(Node p, int i) {
		if (p != null) {
			array[i] = p.data;
			toArray(p.left, i * 2);
			toArray(p.right, i * 2 + 1);
		}
	}

	public int getNodeCount() {

		return getNodeCount(root);

//		if (root == null) {
//			return 0;
//		}
//
//		java.util.Stack<Node> stack = new java.util.Stack<>();
//		stack.push(root);
//		int count = 0;
//
//		while (!stack.isEmpty()) {
//			Node n = stack.pop();
//			count++;
//			if (n.left != null) {
//				stack.push(n.left);
//			}
//			if (n.right != null) {
//				stack.push(n.right);
//			}
//		}
//
//		return count;
	}

	private int getNodeCount(Node node) {
		if (node == null) {
			return 0;
		}
		else {
			return 1+getNodeCount(root.left) + getNodeCount(root.right);
		}
	}

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node p) {
		if (p == null) {
			return 0;
		} else {
			return 1 + Math.max(getHeight(p.left), getHeight(p.right));
		}
	}

	public boolean contains(char d) {

		return contains(root, d);

//		if (root == null) {
//			return false;
//		}
//
//		java.util.Stack<Node> stack = new java.util.Stack<>();
//		stack.push(root);
//
//		while (!stack.isEmpty()) {
//			Node n = stack.pop();
//			if (n.data == d) {
//				return true;
//			}
//			if (n.left != null) {
//				stack.push(n.left);
//			}
//			if (n.right != null) {
//				stack.push(n.right);
//			}
//		}
//
//		return false;
	}

	private boolean contains(Node node, char d) {
		if (root == null) {
			return false;
		}
		else {
			if (root.data == d) {
				return true;
			}
			else {
				return contains(node.left, d) || contains(node.right, d);
			}
		}
	}

	public static void main(String[] args) {
		// a*b+c%d

		SimpleTree t1 = new SimpleTree();
		t1.makeTree('a');
		SimpleTree t2 = new SimpleTree();
		t2.makeTree('b');
		SimpleTree t3 = new SimpleTree();
		t3.makeTree('c');
		SimpleTree t4 = new SimpleTree();
		t4.makeTree('d');

		SimpleTree t5 = new SimpleTree();
		t5.makeTree(t1, '*', t2);
		SimpleTree t6 = new SimpleTree();
		t6.makeTree(t3, '%', t4);

		SimpleTree t7 = new SimpleTree();
		t7.makeTree(t5, '+', t6);

		t7.showTree();
//		t7.toArray();
//
//		System.out.println(t7.getNodeCount());
//		System.out.println(t7.getHeight());
//
//		System.out.println(t7.contains('a'));
//		System.out.println(t7.contains('k'));

	}
}
