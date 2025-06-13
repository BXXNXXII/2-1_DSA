package week15_finalExam;

public class TernarySearchTree {  // ternary search tree
	
	class Node {
		Integer key1, key2;
		Node left, middle, right, parent;
		
		Node(){
			key1=null;
			key2=null;
			left=null;
			middle=null;
			right=null;
			parent=null;
		}
		public String toString() {
			return "["+key1+","+key2+"] ";
		}
	}
	
	Node root;
	
	TernarySearchTree(){
		root=null;
	}

	public void insert(int input) {
		// 트리가 비어 있을 경우 루트에 key1으로 저장
		if (root == null) {
			root = new Node();
			root.key1 = input;
			return;
		}

		Node node = root;
		while (true) {
			// 현재 노드의 key1이 비어 있다면 삽입
			if (node.key1 == null) {
				node.key1 = input;
				return;
			}

			// input이 key1보다 작을 경우 왼쪽 서브트리로 이동
			if (input < node.key1) {
				if (node.left == null) {
					node.left = new Node();
					node.left.parent = node;
					node.left.key1 = input;
					return;
				}
				node = node.left;

				// key2가 아직 비어 있을 경우 key2에 저장
			} else if (node.key2 == null) {
				if (input != node.key1) {
					node.key2 = input;
					return;
				} else return;  // key1과 중복된 값은 무시

				// key1과 key2가 모두 있을 경우, 위치에 따라 left/middle/right 서브트리로 분기
			} else {
				int min = Math.min(node.key1, node.key2);
				int max = Math.max(node.key1, node.key2);

				if (input < min) {
					if (node.left == null) {
						node.left = new Node();
						node.left.parent = node;
						node.left.key1 = input;
						return;
					}
					node = node.left;
				} else if (input > max) {
					if (node.right == null) {
						node.right = new Node();
						node.right.parent = node;
						node.right.key1 = input;
						return;
					}
					node = node.right;
				} else {  // 중간 영역일 경우 middle 서브트리로 이동
					if (node.middle == null) {
						node.middle = new Node();
						node.middle.parent = node;
						node.middle.key1 = input;
						return;
					}
					node = node.middle;
				}
			}
		}
	}


	public void showTree() {
		Node node = root;
		java.util.Stack<Node> stack = new java.util.Stack<>();
		java.util.Stack<Boolean> visitedKey1 = new java.util.Stack<>();
		java.util.Stack<Boolean> visitedKey2 = new java.util.Stack<>();

		while (!stack.isEmpty() || node != null) {
			// 왼쪽 자식으로 계속 탐색
			if (node != null) {
				stack.push(node);
				visitedKey1.push(false);  // 아직 key1 방문 안 함
				visitedKey2.push(false);  // 아직 key2 방문 안 함
				node = node.left;
			} else {
				node = stack.peek();
				boolean visit1 = visitedKey1.pop();
				boolean visit2 = visitedKey2.pop();

				if (!visit1) {
					System.out.print(node.key1 + " ");  // key1 출력
					visitedKey1.push(true);
					visitedKey2.push(visit2);
					node = node.middle;  // middle 서브트리로 이동
				} else if (node.key2 != null && !visit2) {
					System.out.print(node.key2 + " ");  // key2 출력
					visitedKey1.push(visit1);
					visitedKey2.push(true);
					node = node.right;  // right 서브트리로 이동
				} else {
					stack.pop();  // 해당 노드 처리 완료
					node = null;
				}
			}
		}
		System.out.println();
	}

	public void showLevel() {
		System.out.println();
		java.util.LinkedList<Node> q = new java.util.LinkedList<>();
		q.add(root);
		int currentLevel = -1;

		while (!q.isEmpty()) {
			Node node = q.removeFirst();
			int l = level(node);  // 현재 노드의 레벨 계산
			if (l > currentLevel) {
				currentLevel = l;
				System.out.print("\nLevel " + l + " : ");
			}
			System.out.print(node + " ");  // 현재 노드 출력

			// 자식 노드들을 큐에 추가
			if (node.left != null) q.add(node.left);
			if (node.middle != null) q.add(node.middle);
			if (node.right != null) q.add(node.right);
		}
	}

	private int level(Node node) {
		if (node==root)
			return 0;
		else
			return 1+level(node.parent);
	}

	public static void main(String[] args) {
		int n=20;
		int [] data= {45, 21, 78, 97, 91, 26, 11, 57, 15, 49, 36, 38, 86, 20, 56, 17, 99, 82, 71, 98};
		TernarySearchTree t = new TernarySearchTree();
		for (int i=0;i<data.length;i++) {
			t.insert(data[i]); 
			t.showTree();
		}
		System.out.println();
		t.showLevel();
	}
}