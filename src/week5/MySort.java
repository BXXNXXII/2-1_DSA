package week5;

import java.util.Arrays;

public class MySort {

	private int[] swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
        return data;
    }

	public int[] selectionSort(int[] data) {
		int n = data.length;

		for (int i = n-1; i > 0; i--) {
			int maxIndex = i; // 가장 큰 값은 가장 오른쪽으로 놓기 위함
			for(int j=0; j<=i-1; j++)
				if (data[j] > data[maxIndex]) {
					maxIndex =j;
				}
			// data[n-1] <- maxValue 위치로 저장 : 위치를 알아야 함 -> maxIndex로 변경
			swap(data, maxIndex, i);
			// maxIndex : 찾은 최댓값이 현재 있는 위치
			// i : 맨 오른쪽(찾은 값을 옮길 위치)
		}
		//return data;
		return selectionSort2(data, data.length-1);
	}

	public int[] selectionSort2(int[] data, int n) { // n == last index
		if (n==0)
			return data;
		else {
			int maxIndex = n; // 가장 큰 값은 가장 오른쪽으로 놓기 위함
			for (int j=0; j<=n-1; j++)
				if (data[j] > data[maxIndex]) {
					maxIndex = j;
				}
			swap(data, maxIndex, n);
			return selectionSort2(data, n-1);
		}
	}

	public int[] insertionSort(int[] data) {
		// 이미 정렬된 구간에, 새로운 값을 적절한 위치에 삽입하는 방식
		int n = data.length;

		for (int i = 1; i < n; i++) {
			// 두 번째 index부터 시작(i=1)
			int value = data[i];
			int j = i-1;

//			while(j<=0) {
//				if (data[j] > value)  // ==> j++

			while (j >= 0 && (data[j] > value)) {
				j--;
			}

			int myPosition = j+1;
			int k=i-1;

			while(k>=myPosition) {
				data[k+1] = data[k];
				k--;
				//if (data[j] < value) // ==> j 뒤에 삽입 ==> j+1 ~ i-1을 하나씩 뒤로 민다.
			}
			data[myPosition] = value;
		}
		return data;
	}

	public int[] bubleSort(int[] data) {
		// 인접한 두 수를 비교해서 큰 수를 오른쪽으로 보내는 정렬 방식
		int n = data.length;
		// n : data의 개수
		// i=n-1 : 해당 data의 인덱스(4번째 수라면 index는 3)

		for (int i=n-1; i>=0; i--) { // i : 반복 횟수를 뜻함, 가장 큰 값 하나가 맨 뒤로 밀려남
			for (int j=0; j<=i-1; j++) { // j<i or j<=i-1, 인접한 두 값 비교하는 루프
				if(data[j] > data[j+1]) { // 앞쪽이 크면 서로 자리 swap
					swap(data, j, j+1);
				}
			}
		}
		return data;
	}

	public int [] quickSort(int [] data) {
		return quickSort(data, 0, data.length-1);
	}

	private int [] quickSort(int [] data, int p, int r) { // "overloading" cf. overriding
		// 피벗(Pivot) 기준으로 데이터를 작/큰 그룹으로 나눔
		if(p<r) {
			int q = partition(data, p, r);
			quickSort(data, p, q - 1);
			quickSort(data, q + 1, r);
		}
		return data;
	}

	private int partition(int[] data, int p, int r) {

		int x = data[r]; // 마지막 값을 피벗으로 선택
		int k = p-1; // last index of left subset
					// 피벗보다 작은 값을 쌓을 마지막 인덱스

		for (int i=p; i<r; i++) {
			if(data[i] <= x) {
				swap(data, ++k, i);
			}
		}
		swap(data, k+1, r);
		return k+1;

//		예제 흐름
//		배열: [4, 7, 1, 5, 3]
//		피벗: 3 (맨 마지막 값)
//		비교: 4 > 3 → X
//		비교: 7 > 3 → X
//		비교: 1 <= 3 → k = 0, swap(0,2) → [1, 7, 4, 5, 3]
//		비교: 5 > 3 → X
//		마지막 swap: swap(1, 4) → [1, 3, 4, 5, 7]
//		→ 3은 정확한 위치로 고정!

//		int pivot = r; // 기준 값 인덱스(배열의 가장 마지막)
//		int left = p; // 왼쪽 포인터(왼쪽부터 오른쪽으로)
//		int right = r; // 오른쪽 포인터(오른쪽부터 왼쪽으로)
//							-> 점점 좁혀옴
//
//		while (left < right) {
//			while(data[left] < data[pivot]) {
//				left++; // pivot보다 작은 값들은 통과 && 왼쪽에서 점점 늘려옴
//			}
//			while(right>left && data[right] >= data[pivot]) {
//				right--; // pivot보다 큰 값들은 통과 && 오른쪽에서 점점 좁혀옴 && pivot보다 작은 값 만나면 멈춤
//			}
//			if (left < right) { // 겹치지 않도록 방지
//				swap(data, left, right);
//			}
//		}
//		swap(data, pivot, left);
//
//
//		return left; // 이때 left == right
	}

	public int[] mergeSort(int[] data) {
		return mergeSort(data, 0, data.length-1);
		// 배열 전체를 인덱스 범위로 쪼개서 정렬
	}

	private int[] mergeSort(int[] data, int p, int r) {
		if(p<r) {
			int q = (p+r)/2; // 배열을 반으로 나누는 기준 (예: 0~5면 → q = 2)
			mergeSort(data, p, q); // 왼쪽 절반 정렬
			mergeSort(data, q+1, r); // 오른쪽 절반 정렬
			merge(data, p, q, r); // 병합
		}
		return data;
	}

	private int[] merge(int[] data, int p, int q, int r) {
		int[] temp = new int[data.length];

		// merge {data[p], ..., data[q]} and {data[q+1], ..., data[r]}

		int i = p;
		int j = q+1;
		int k = p;

		while(i<=q && j<=r) {
			if(data[i]<data[j]) {
				temp[k++] = data[i++];
			}
			else {
				temp[k++] = data[j++];
			}
		}
		while(i<=q) {
			temp[k++] = data[i++];
		}
		while(j<=r) {
			temp[k++] = data[j++];
		}

		for (int l=p; l<=r; l++) {
			data[l] = temp[l];
		}
		return data;

//		-시각화-
//		[분할]
//		[8, 3, 1, 5]
//		→ [8, 3]  [1, 5]
//		→ [8][3]  [1][5]
//
//		[병합]
//		[8][3] → [3, 8]
//		[1][5] → [1, 5]
//		[3, 8] + [1, 5] → [1, 3, 5, 8]
	}

	public static void main(String[] args) {
		int[] data = {113, 336, 74, 71, 86, 176, 313, 80, 225, 342,
				170, 292, 275, 266, 79, 16, 109, 175, 245, 156,
				50, 61, 277, 167, 81, 24, 76, 186, 78, 101,
				301, 62, 152, 219, 294};

		System.out.println("\n< Initial Data >");
		showData(data);

		// deep copy data to another array, dataSorted, for argument...
		// call each sort method
		// showData(dataSorted)

		MySort ms = new MySort();

		// shallow copy (앝은 복사)
		int[] sample = new int[35];
		sample = data;
		int[] sample2 = data;

		// deep copy (깊은 복사)
		for (int i = 0; i < 20; i++) {
			sample[i] = data[i];
		}

		sample = data.clone();

		sample = Arrays.copyOf(data, data.length); // data.length : 데이터 전체 카피

		System.arraycopy(data, 0, sample, 0, 20);


		int [] dataSorted = new int[data.length];

//		System.out.println("\n< ---- Sort >");
//		dataSorted=data.clone();
//		ms.----Sort(dataSorted);
//		showData(dataSorted);

		int[] toBeSorted = data.clone();
		ms.selectionSort(toBeSorted);
		System.out.println("\n> Selection Sorted >");
		showData(toBeSorted);

		toBeSorted = data.clone();
		ms.selectionSort2(toBeSorted, toBeSorted.length-1);
		System.out.println("\n> Selection Sorted2 >");
		showData(toBeSorted);

		toBeSorted = data.clone();
		ms.insertionSort(toBeSorted);
		System.out.println("\n> Insertion Sorted >");
		showData(toBeSorted);

		toBeSorted = data.clone();
		ms.bubleSort(toBeSorted);
		System.out.println("\n> Buble Sorted >");
		showData(toBeSorted);

		System.out.println("\n< Quick Sort >");
		dataSorted = data.clone();
		ms.quickSort(dataSorted);
		showData(dataSorted);

		System.out.println("\n< Merge Sort >");
		dataSorted = data.clone();
		ms.mergeSort(dataSorted);
		showData(dataSorted);
	}


	private static void showData(int[] data) {
		int nData = data.length;
		int nPrinted=0;
		while(nPrinted<nData) {
			for (int i=0;(nPrinted<nData)&&(i<10); i++) {
				System.out.printf("%6d ",data[nPrinted++]);
			} // 10개씩 끊어서
			System.out.println(); // 출력
		}
	}
}
