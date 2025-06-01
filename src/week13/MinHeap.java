package week13;

public class MinHeap {
	
	char [] data;
	int size;  // == number of node inserted! == last index inserted!

	MinHeap(int max){
		data=new char[max];
		data[0]=' ';
		size=0;
	}
	public void insert(char c) {
		data[++size]=c; 
		heapifyUpward(size);
	}

	private void heapifyUpward(int k) {
		int parent =k/2;
		if (parent>0) {
			if (data[k]<data[parent]) {
				swap(parent, k);
				heapifyUpward(parent);
			}
		}
	}
	private void swap(int i, int j) {
		char temp=data[i];
		data[i]= data[j];
		data[j]=temp;
	}
	
	public Character delete() {
		Character ret=null;
		if (size>=1) {
			ret=data[1];
			data[1]=data[size];
			data[size]=' ';
			size--;
			heapifyDownward(1);
		}
		return ret;
	}
	private void heapifyDownward(int k) {
		int left=2*k;
		int right = 2*k+1;
		int smaller = left;
		
		if (right<=size) {
			if (data[left]>data[right])
				smaller=right;
		}
		else if (left>size)
			return;
		if (data[smaller]<data[k]) {
			swap(smaller, k);
			heapifyDownward(smaller);
		}
	}
	//////////////////////////////////////////////////////////////
		public void showHeap() {
			for (int i=1;i<=size;i++)
				System.out.print(data[i]);
			System.out.println();
		}

		public void showLevel() {
			int h=height();
			for (int level=0;level<=h;level++) {
				System.out.print("\n Level "+level+" : ");
				int levelStart=(int) Math.pow(2,level);
				int levelEnd=(int) Math.min(Math.pow(2,level+1)-1, size);
				for (int i=levelStart;i<=levelEnd;i++ )
					System.out.print(data[i]);
			}
			System.out.println();
		}
		private int height() {
			return (int)(Math.log(data.length)+1);
		}
		
		public static void main(String[] args) {
			char[] data = {'q','a','z','w','s','x','e','d','c','r','f','v','t','g','b','y','h','n','u','j','m','i','k','o','l','p'};
			
			MinHeap heap = new MinHeap(data.length+1);
			
			for (int i=0;i<data.length;i++) {
				heap.insert(data[i]);
				heap.showHeap();
			}

			System.out.println("\n<< Tree Created >>");
			heap.showHeap();

			heap.showLevel();
			
			System.out.println("\n\n<< Sorted List >>\n");

			for (int i=0;i<26;i++) {
				System.out.print(heap.delete());
				heap.showHeap();
			}
			System.out.println();
		}
	}
