package week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyQueue {
	// 선입선출

	int [] queue;
	int front, rear, queueSize;
	// 출구 : front, 입구 : rear

	public MyQueue(int n) {
		queueSize = n;
		queue = new int [queueSize];
		front = 0; // 출구
		rear = 1; // 입구
		// 원형 큐 구조를 위해 한 칸 차이 유지
	}

	public boolean isEmpty() {
		return (front+1) % queueSize == rear;
		// front 다음 칸이 rear면 비어 있음
		// → 왜냐하면 enqueue 할 땐 rear부터 시작하니까
	}

	public boolean isFull() {
		return front == rear;
		// rear가 front를 다시 만나면 꽉 찬 상태
	}
	
	public void enqueue(int value) {
		// enqueue는 단순히 넣기만 하는 작업
		// 실패하면 아무 것도 안 하면 됨
		
		if (isFull()) {
			System.out.println(">>> Queue Full...");
			return; // 그냥 종료
		}
		else {
			queue[rear] = value;
			rear = (rear + 1) % queueSize;
			// % queueSize : 맨 끝까지 가면 다시 처음(0번 index)부터 사용하기 위해
			// 					index를 강제로 0으로 돌려야 함
		}
	}
	
	public int dequeue() {
		//dequeue는 꺼낸 값을 리턴해야 함
		// 꺼낼 게 없으면? -> "실패"했다는 신호로 -999를 리턴

		if (isEmpty()) {
			System.out.println(">>> Queue Empty...");
			return -999; // 실패했음을 나타내는 값
			// -999인 이유 : 자바는 int 타입에서 null을 리턴 못함
		}
		else {
			front = (front + 1) % queueSize;
			return queue[front];
		}
	}
	
	public void showQueue() {
		System.out.println(Arrays.toString(queue));
	}

	public static void main(String[] args) {
		int [] data= {3,5,4,1,7,2,9,8,6,0};

		List<Integer> list = new LinkedList<>();
		LinkedList<Integer> list2 = new LinkedList<>();
		
		MyQueue queue = new MyQueue(10);
		
		for (int i=0; i<data.length;i++) {
			queue.enqueue(data[i]);
			queue.showQueue();
		}
		
		for (int i=0; i<data.length;i++) {
			System.out.println(queue.dequeue());
//			queue.showQueue();
		}
	}	
}
