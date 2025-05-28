package week8_midternExam;

import java.util.Arrays;

public class Code1 {

	public int sum(int n) {
		int sum=0;
		for (int i=1;i<=n;i++)
			sum=sum+i;
		return sum;
	}

	//     Q1 : Complete sumRecursion		
	public int sumRecursion(int n) {
		if (n == 1) {
			return 1; // Recursion 종료
		} else {
			return n + sumRecursion(n - 1); // Recursion 호출
		}
	}

	//	     Q2 : Complete merge in recursion				
	public int[] merge(int[] d1, int[] d2) {
		int[] result = new int[d1.length + d2.length]; // 병합된 배열 크기만큼 새 배열 생성
		return merge(result, 0, d1, 0, d2, 0); // 배열 병합
	}

	private int[] merge(int[] result, int k, int[] d1, int i, int[] d2, int j) {
		// 첫번째 배열 끝 : 두 번째 배열 남은 값 -> 결과 배열에 추가
		if (i >= d1.length) {
			while (j < d2.length) {
				result[k++] = d2[j++];
			}
		} 
		
		// 두 번째 배열 끝 : 첫 번째 배열 남은 값 -> 결과 배열에 추가
		else if (j >= d2.length) {
			while (i < d1.length) {
				result[k++] = d1[i++];
			}
		} 
		else {
			// 두 배열 비교 : 작은 값 -> 결과 배열에 추가
			if (d1[i] < d2[j]) {
				result[k++] = d1[i++];
			} else {
				result[k++] = d2[j++];
			}
			// Recursion 호출 -> 계속해서 병합
			merge(result, k, d1, i, d2, j);
		}
		return result; // 결과 배열 반환
	}


	//	     Q3 : Complete the methods of NewQueue class				
	public static class NewQueue {

		MyArrayList2<Integer> queue = new MyArrayList2<>();

		public void enqueue(int data) {
			queue.add(data); // 데이터 추가
		}

		public int dequeue() { // 데이터 제거&반환
			if (!queue.isEmpty()) {
				return queue.remove(0);  // 첫 번째 항목 제거
			}
			throw new IllegalStateException(">>> Queue is empty..."); // 비어있으면 예외 처리
		}

		public int peek() {
			if (!queue.isEmpty()) {
				return queue.get(0);  // 첫 번째 항목 반환(제거하지 X)
			}
			throw new IllegalStateException(">>> Queue is empty..."); // 비어있으면 예외 처리
		}


		public void showQueue() {
			System.out.println("Queue : "+queue.toString());
		}
	}

	public static void main(String[] args) {
		Code1 q1 = new Code1();
		System.out.println("\n<Q1> ");
		System.out.println("Sum Iteration : "+q1.sum(15));
		System.out.println("Sum Recursion : "+q1.sumRecursion(15));


		int [] d1= {10, 20, 55, 60, 80};
		int [] d2= {15, 30, 40, 75, 90};
		System.out.println("\n<Q2> ");
		System.out.println("Merge Result : "+ Arrays.toString(q1.merge(d1, d2)));

		int [] data= {3,5,4,1,7,2,9,8,0,6};
		NewQueue queue = new NewQueue();
		System.out.println("\n<Q3> ");

		for (int i=0; i<data.length;i++) {
			queue.enqueue(data[i]);
			queue.showQueue();
		}
		System.out.println("Peek Result : "+queue.peek());
		for (int i=0; i<data.length;i++) {
			queue.dequeue();
			queue.showQueue();
		}
	}
}
