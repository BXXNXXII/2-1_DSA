package week7;

import java.util.Arrays;

public class MyArrayList0 implements MyList{
	// 배열 기반, 크기 조절은 X -> 한정된 크기에서만 동작

	int [] array; // 데이터를 저장하는 실제 배열
	int capacity; // 배열의 최대 크기
	int size; // 현재 저장된 데이터 개수

	public MyArrayList0 (int n) {
		capacity = n;
		array = new int[capacity];
		size = 0; // 아직 아무 것도 안 들어가서 0
	}

	@Override
	public boolean isEmpty() {
		return (size == 0); // 아무 것도 안 들어갔으면 true
	}

	@Override
	public int indexOf(Object value) {
		for (int i = 0; i < size; i++) {
			if (array[i] == (int)value) {
				return i; // 해당 값이 몇 번째 인덱스에 있는지 반환
			}
		}
		return -1; // 못 찾으면 -1
	}

	@Override
	public void add(Object value) {
		if (!isfull()) { // 배열이 가득 차지 않았으면 == 빈 공간이 있으면
			array[size++] = (int)value;
			// size 위치에 추가
			// size++로 개수 증가
		}

	}

	@Override
	public void add(int index, Object value) {
		// index 위치에 중간에 값을 삽입
		if(checkIndexRange(index)) { //? 0<= index <=size-1
			if (!isfull()) { // 빈 공간이 있으면
				for (int i = size-1; i >= size; i--) {
					array[i+1] = array[i]; // 오른쪽으로 한 칸씩 이동
				}
//			System.arraycopy( array, index, array, index + 1, size - index ); for문과 같은 의미
				array[index] = (int)value; // 지정 위치에 값 삽입
				size++;
			}
		}
		else if(index==size) { // add to left position, 끝에 추가하는 경우
			add(value);
		}
	}

	private boolean isfull() {
		return (size == capacity); // 배열이 가득 찼는지 확인
	}

	private boolean checkIndexRange(int index) {
		if(index >= 0 && index < size) { // 유효한 index 범위인지 확인
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		Arrays.fill(array, 0); // 배열을 0으로 초기화
		size = 0; // size 초기화
	}

	@Override
	public boolean contains(Object value) {
		for (int i = 0; i < size; i++) {
			if (array[i] == (int)value) {
				return true; // 포함되어 있으면 true
			}
		}
		return false;
//		return !(indexOf(value)==-1);
	}

	@Override
	public boolean remove(Object value) {
		int index = indexOf(value);
		if (checkIndexRange(index)) {
			for (int i = index+1; i < size; i++) {
				array[i-1] = array[i];
			}
			System.arraycopy(array, index+1, array, index, size-index-1);
			// index 위치의 값을 삭제하고, 그 뒤에 있는 값들을 한 칸씩 앞으로 당기는 코드

// 			[예제]
//			array = [10, 20, 30, 40, 50]
//			size = 5
//			index = 2 → 우리가 30을 지우고 싶어!
//
//			System.arraycopy(array, 3, array, 2, 2)
//			(원본 배열(자기 자신) : array,
//			복사 시작 위치 = 삭제할 index 다음 요소 : 3,
//			복사 대상 배열(자기 자신) : array,
//			복사할 위치 = 삭제할 인덱스 : 2,
//			복사할 길이(뒤에 남아있는 요소 개수) : 2)
//			→ array[2] = array[3]  (30 → 40) => [10, 20, 40, 40, 50]
//			→ array[3] = array[4]  (40 → 50) => [10, 20, 40, 50, 50]
//
//			array = [10, 20, 40, 50, 50]

			size--;// 뒤의 값들을 한 칸 앞으로 당김
//			[10, 20, 40, 50, 50]이므로 size--를 통해 50 하나는 무시됨 -> [10, 20, 40, 50]

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Object remove(int index) {
		if (checkIndexRange(index)) {
			System.arraycopy(array, index+1, array, index, size-index-1);
			// index 위치의 값을 삭제하고, 그 뒤에 있는 값들을 한 칸씩 앞으로 당기는 코드

			size--; // 뒤의 값들을 한 칸 앞으로 당김
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		return size; // 현재 요소 개수 반환
	}

	@Override
	public Object get(int index) {
		if (checkIndexRange(index)) {
			return array[index]; // index 위치의 값 반환(가져오기)
		}
		return null;
	}

	@Override
	public void set(int index, Object value) {
		if(checkIndexRange(index)) {
			array[index]=(int)value; // index 위치에 값 설정(수정하기)
		}
	}

	@Override
	public String toString() {
		String ret = "";
		for (int i=0; i<size; i++)
			ret = ret + array[i] + " ";
		return ret;
	}

	public void showList() {
		System.out.println("Current List Status");
		System.out.println(toString());
	}

	public static void main(String[] args) {

		int [] data = {113,  336,  74,  71,  86,  176,  313,  80,  225,  342,
				170,  292,  275,  266 , 79,  16,  109,  175 , 245,  156,
				50,  61,  277,  167,  81,  24,  76,  186,  78,  101,
				301,  62,  152,  219,  294};

		MyArrayList0 list = new MyArrayList0(20);
		// 용량이 20인 리스트 생성

		for (int i=0;i<10; i++)
			list.add(data[i]);  // cf :  list[i]=data[i]
			// data[0] ~ data[9]까지 list에 추가
		list.showList();

		list.set(5, 999);     // list[5]=999 : 5번째 요소를 176 -> 999로 설정
		int x =(int) list.get(5);  // cf : x = list[5] : 5번째 요소 가져오기 : x = 999
		System.out.println("\nx = "+x);

		list.remove((Integer)336); // 값으로 삭제
		list.showList();

		list.add(3, 111); // index 3에 값 삽입
		list.showList();
		list.add(list.size(), 222); // 끝에 값 추가
		list.showList();

		list.clear(); // 리스트 초기화
		list.showList();

	}
}
