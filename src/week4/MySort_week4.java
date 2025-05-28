package week4;

import java.util.Arrays;

public class MySort_week4 {

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

	private int[] swap(int[] d, int i, int j) {
		int temp = d[i];
		d[i] = d[j];
		d[j] = temp;
        return d;
    }

	public int[] insertionSort(int[] data) {
		int n = data.length;

		for (int i = 1; i < n; i++) {
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
		int n = data.length;

		for (int i=n-1; i>=0; i--) { // i : 반복 횟수를 뜻함
			for (int j=0; j<=i-1; j++) { // j<i or j<=i-1
				if(data[j] > data[j+1]) {
					swap(data, j, j+1);
				}
			}
		}
        return data;
    }


	public static void main(String[] args) {
		int [] data = {113,  336,  74,  71,  86,  176,  313,  80,  225,  342,
				  170,  292,  275,  266 , 79,  16,  109,  175 , 245,  156,
				  50,  61,  277,  167,  81,  24,  76,  186,  78,  101,
				  301,  62,  152,  219,  294};

		System.out.println("\n< Initial Data >");
		showData(data);

		MySort_week4 ms = new MySort_week4();
		// deep copy data to another array, dataSorted, for argument...
		// call each sort method
		// showData(dataSorted)

		// shallow copy (앝은 복사)
		int[] sample = new int[35];
		sample = data;
		int[] sample2 = data;

		// deep copy (깊은 복사)
		for (int i = 0; i<20; i++) {
			sample[i] = data[i];
		}

		sample = data.clone();

		sample = Arrays.copyOf(data, data.length); // data.length : 데이터 전체 카피

		System.arraycopy(data, 0, sample, 0, 20);

//		
//		MySort ms = new MySort();
//		int [] dataSorted = new int[data.length];
//		
//		System.out.println("\n< ---- Sort >");
//		dataSorted=data.clone();
//		ms.----Sort(dataSorted);
//		showData(dataSorted);

		int [] toBeSorted = data.clone();
		ms.selectionSort(toBeSorted);
		System.out.println("\n> Selection Sorted >");
		showData(toBeSorted);

//		toBeSorted = data.clone();
//		ms.selectionSort2(toBeSorted, toBeSorted.length-1);
//		System.out.println("\n> Selection Sorted2 >");
//		showData(toBeSorted);

		toBeSorted = data.clone();
		ms.insertionSort(toBeSorted);
		System.out.println("\n> Insertion Sorted >");
		showData(toBeSorted);

		toBeSorted = data.clone();
		ms.bubleSort(toBeSorted);
		System.out.println("\n> Buble Sorted >");
		showData(toBeSorted);
		
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
