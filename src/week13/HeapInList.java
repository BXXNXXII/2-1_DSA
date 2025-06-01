package week13;


import java.util.ArrayDeque;
import java.util.Deque;

public class HeapInList {
	
	class Node{
		char priority;
		Node left, right, parent;
		public Node(char val, Node l, Node r, Node p) {
			priority=val;
			left=l;
			right=r;
			parent=p;
		}
		public String toString() {
			return " "+priority;
		}
	}
	
	Node heapRoot;
	Node last;
	
	public HeapInList() {
		heapRoot=null;
		last=null;
	}
	
	public void insert(char c) {
		Node parentOfNext=null;
		
		if (heapRoot==null) {
			heapRoot=new Node(c, null, null, parentOfNext);
			last=heapRoot;
		}
		else if (heapRoot==last) {
			parentOfNext=heapRoot;
			heapRoot.left=new Node(c, null, null, parentOfNext);
			last=heapRoot.left;
		}
		else if (last==last.parent.left) {
			parentOfNext=last.parent;
			parentOfNext.right=new Node(c, null, null, parentOfNext);
			last=parentOfNext.right;
		}
		else {
			parentOfNext=last;
			while (parentOfNext.parent!=null && parentOfNext==parentOfNext.parent.right)
				parentOfNext=parentOfNext.parent;
			
			if (parentOfNext.parent!=null ) // ie. left child
				parentOfNext=parentOfNext.parent.right;
			
			while (parentOfNext.left!=null)
				parentOfNext=parentOfNext.left;
			
			parentOfNext.left=new Node(c, null, null, parentOfNext);
			last=parentOfNext.left;
		}
		fixUpward(last);
	}
	
	private void fixUpward(Node node) {
		if (node ==null || node.parent==null)
			return;
		if (node.priority > node.parent.priority) {
			swap(node, node.parent);
			fixUpward(node.parent);
		}
	}

	private void swap(Node a, Node b) {
		char temp = a.priority;
		a.priority=b.priority;
		b.priority=temp;
	}
	
	public void showHeap() {
		Node node = heapRoot;
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(node);
		while(!q.isEmpty()) {
			Node p = q.removeFirst();
			System.out.print(p.toString());
			if (p.left!=null) q.add(p.left);
			if (p.right!=null) q.add(p.right);
		}
//		showLevel();
	}
	
	public void showLevel() {
		showLevel(heapRoot);
	}
	private void showLevel(Node node) {
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(node);
		int curLevel =-1;
		while(!q.isEmpty()) {
			Node p = q.removeFirst();
			if (curLevel < level(p)) {
				curLevel++;
				System.out.print("\nLevel "+curLevel+" : ");
			}
			System.out.print(p.priority);
			if (p.left!=null) q.add(p.left);
			if (p.right!=null) q.add(p.right);
		}
		
	}
	private int level(Node node) {
		if (node==heapRoot)
			return 0;
		else
			return 1+level(node.parent);
	}
	
	public Character delete() {
		char retVal;
		if (heapRoot==null)
			return null;
		retVal=heapRoot.priority;
		if (last==heapRoot) {
			last=null;
			heapRoot=null;
		}
		else {
			heapRoot.priority=last.priority;
			Node newLast;
			
			if (last==last.parent.right) {
				newLast=last.parent.left;
				last.parent.right=null;
				last = newLast;
			}
			else { // ie. last == left child !
				newLast= last;
				while (newLast.parent != null && newLast == newLast.parent.left) {
					newLast=newLast.parent;
				}
				if (newLast.parent!=null) {
					newLast = newLast.parent.left;
				}
				while (newLast.right!=null) {
					newLast=newLast.right;
				}
				last.parent.left=null;
				last=newLast;
			}
		}
		fixDownward(heapRoot);
		return retVal;
	}

	private void fixDownward(Node node) {
		if (node==null || node.left==null)
			return;
		Node larger =node.left;
		if (node.right!=null && node.right.priority >node.left.priority)
			larger = node.right;
		if (larger.priority > node.priority)
			swap(larger, node);
		fixDownward(larger);
	}

	public static void main(String[] args) {
		HeapInList heap = new HeapInList();
		
		for (int i=0;i<26;i++) {
			heap.insert((char)('A'+i));
			System.out.println();

			heap.showHeap();
		}

		System.out.println("\n<< Tree Created >>");
		heap.showHeap();
		
		heap.showLevel();
		
		System.out.println("\n<< Sorted List >>\n");

		for (int i=0;i<26;i++) {
			System.out.println();

			heap.showHeap();
			heap.delete();
		}
		
	}
}
