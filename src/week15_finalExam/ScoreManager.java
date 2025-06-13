package week15_finalExam;

public class ScoreManager {

	class ScoreCard{
		String name;
		int score;
		
		public ScoreCard(String n, int s) {
			name=n;
			score=s;
		}
		
		public String toString() {
			return name+"("+score+")";
		}
	}
	
	ScoreCard[] heap;
	int size;
	
	public ScoreManager(int max) {
		heap=new ScoreCard[max];
		size=0;
	}

	public void insert(String n, int s) {
		size++;
		heap[size] = new ScoreCard(n, s);  // 새로운 요소 추가
		heapifyUpward(size);               // 위로 정렬 (Max Heap 유지)
	}

	private void heapifyUpward(int k) {
		while (k > 1 && heap[k].score > heap[k/2].score) {
			swap(k, k/2);
			k = k/2;
		}
	}

	private void swap(int i, int j) {
		ScoreCard temp=heap[i];
		heap[i]=heap[j];
		heap[j]=temp;	
	}

	public ScoreCard award() {
		if (size == 0) return null;
		ScoreCard winner = heap[1];                       // 1등 추출
		heap[1] = new ScoreCard(winner.name, 10);         // 점수만 10점으로 초기화
		heapifyDownward(1);                               // 아래로 정렬
		return winner;
	}

	private void heapifyDownward(int k) {
		while (2 * k <= size) {
			int left = 2 * k;
			int right = 2 * k + 1;
			int max = left;

			// 왼쪽과 오른쪽 자식 중 더 큰 값을 찾음
			if (right <= size && heap[right].score > heap[left].score)
				max = right;

			// 자식보다 현재가 크면 종료
			if (heap[k].score >= heap[max].score)
				break;

			swap(k, max);
			k = max;
		}
	}

	public void update(String n, int point) {
		for (int i = 1; i <= size; i++) {
			if (heap[i].name.equals(n)) {
				heap[i].score += point;    // 점수 증가 또는 감소
				heapifyUpward(i);          // 점수 올라갔을 경우 위로
				heapifyDownward(i);        // 점수 내려갔을 경우 아래로
				break;
			}
		}
	}

	public void delete(String n) {
		for (int i = 1; i <= size; i++) {
			if (heap[i].name.equals(n)) {
				swap(i, size);  // 삭제 대상과 마지막 요소 교환
				size--;         // 힙 크기 감소
				heapifyUpward(i);    // 위치에 따라 위 또는
				heapifyDownward(i);  // 아래로 정렬
				break;
			}
		}
	}

	//////////////////////////////////////////////////////////////
	public void showHeap() {
		for (int i=1;i<=size;i++)
			System.out.print(heap[i]);
		System.out.println();
	}

	public void showLevel() {

		int h=height();
		for (int level=0;level<=h;level++) {
			System.out.print("\n Level "+level+" : ");
			int levelStart=(int) Math.pow(2,level);
			int levelEnd=(int) Math.min(Math.pow(2,level+1)-1, size);
			for (int i=levelStart;i<=levelEnd;i++ )
				System.out.print(heap[i]);
		}
		System.out.println();
	}

	private int height() {
		return (int)(Math.log(heap.length)+1);
	}

	public static void main(String[] args) {
		Object[][] data = {{"aaa",10},{"bbb",20},{"ccc",15},{"ddd",10},{"eee",13},
				{"fff",12},{"ggg",25},{"hhh",30},{"iii",22},{"jjj",18}  };
		
		ScoreManager heap = new ScoreManager(30);
		
		for (int i=0;i<data.length;i++) {
			heap.insert((String)data[i][0], (int)data[i][1]);
			heap.showHeap();
		}

		System.out.println("\n<< Tree Created >>");
		heap.showLevel();
		
		System.out.println("\n<< Operation ...  >>");
		heap.update("aaa", 5);
		heap.update("ccc", -5);
		heap.showLevel();
		heap.update("iii", -2);
		heap.update("kkk", 5);
		heap.update("lll", 8);
		heap.showLevel();
		for (int i=1;i<=5;i++) {
			System.out.print("\n>>> "+i+"-th Winner : ");
			System.out.println(heap.award());
			heap.showLevel();
		}
		
		heap.delete("kkk");
		heap.delete("lll");
		heap.showLevel();
	}
}
