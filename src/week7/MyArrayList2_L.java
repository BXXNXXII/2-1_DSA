package week7;

import java.util.Arrays;

public class MyArrayList2_L<T> {
    // T : 제네릭 타입 -> 사용할 때 어떤 타입으로든 지정 가능
    final int INITIAL_CAPACITY = 5; // 배열 초기 크기 설정 : 5
    T [] array; // 제네릭 배열 -> 내부 요소 저장
    int capacity; // 현재 저장 가능한 최대 개수
    int size; // 실제 저장된 요소 수

    public MyArrayList2_L() {
        capacity = INITIAL_CAPACITY; // 배열 초기 크기 설정 : 5
        array = (T[]) new Object[capacity];
        // 제네릭 배열을 Object[]로 생성 후 타입 캐스팅
        size = 0; // 아직 값 안 들어감
    }
    
    // 비었는지 확인
    public boolean isEmpty() {
        return (size == 0);
    }
    
    // equals() 사용해 비교 -> 객체 내용 비교 가능
    public int indexOf(T value) {
        for(int i=0; i<size; i++)
            if (array[i].equals(value))
                return i;
        return -1;
    }

    public void add(T value) {
        if (isFull()) // 꽉 찼으면
            grow(); // 확장
        array[size++] = value; // 그리고 맨 뒤에 삽입 && 값 삽입 후 size++
    }

    private boolean isFull() {
        return (size == capacity);
        // 현재 개수 = 용량 -> 꽉 찬 상태
    }

    public void add(int index, T value) {
        if (checkIndexRange(index)) {
            if (isFull()) { // 가득 찼으면
                grow(); // 확장
            }
            System.arraycopy(array, index, array, index+1, size-index);
            // 삽입 시 뒤쪽 요소를 한 칸씩 밀어내야 함
            // 삽입 위치부터 뒤로 한 칸씩 이동
            array[index] = value; // 지정 위치에 값 삽입
            size++; // 요소 개수 증가
        }
        else if (index == size) {
            add(value); // 맨 뒤에 추가
        }
    }
    
    // 확장
    private void grow() { 
        T [] tempArray = (T[]) new Object [capacity*2]; // 배열 크기 2배로 늘리고,
        for (int i=0; i<capacity; i++)
            tempArray[i] = array[i]; // 기존 데이터 새 배열로 복사
        array = tempArray; // 기존 배열을 새 배열로 교체 
        capacity *= 2; // 용량도 2배로 키우기
    }
    
    // 특정 값 삭제
    public boolean remove(T value) {
        int index = indexOf(value); // 값의 index를 찾고
        
        if (checkIndexRange(index)) {
            System.arraycopy(array, index+1, array, index, size-index-1);
            // 뒤의 요소를 한 칸 앞으로 복사
            size--; // 개수 1 줄이기
            return true; // 삭제 성공
        }
        else
            return false; // 삭제 실패
    }

    public T remove(int index) {
        if (checkIndexRange(index)) {
            T ret = array[index]; // 삭제할 요소 저장
            System.arraycopy(array, index+1, array, index, size-index-1);
            // 뒤 요소 앞으로 복사
            size--; // 개수 1 감소
            return ret; // 삭제된 요소 반환
        }
        else
            return null; // 삭제 실패 시 null 반환
    }

    // 초기화
    public void clear() {
        Arrays.fill(array, null); // 배열 전체 초기화(null로 설정)
        size = 0; // 초기화 돼서 요소 개수 0
    }

    public boolean contains(T value) {
        return !(indexOf(value) == -1);
        // 값이 포함(존재)되어있으면 true, 없으면 false
    }

    private boolean checkIndexRange(int index) {
        return index >= 0 && index < size; 
        // index가 유효한 범위인지 확인
    }

    public int size() {
        return size; // 현재 저장된 요소 개수 반환
    }

    public T get(int index) {
        if(checkIndexRange(index)) // 유효한 index라면
            return array[index]; // 해당 값 반환
        return null;
    }

    public void set(int index, T value) {
        if(checkIndexRange(index)) // 유효한 index라면
            array[index] = value; // 해당 위치에 값 설정
    }

    public String toString() {
        String ret = ""; // 문자열 초기화
        for (int i=0; i<size; i++)
            ret = ret + array[i] + " "; // 요소를 문자열로
        return ret; // 완성된 문자열 반환
    }
    
    public void showList() {
        System.out.print("Current List Status : ");
        System.out.println(toString());
        System.out.println(">>> Current Capacity = " + capacity + " Size = " + size);
    }

    public static void main(String[] args) {

        int [] data = {113, 336, 74, 71, 86, 176, 313, 80, 225, 342,
                170, 292, 275, 266 , 79, 16, 109, 175 , 245, 156,
                50, 61, 277, 167, 81, 24, 76, 186, 78, 101,
                301, 62, 152, 219, 294};

        MyArrayList2_L<Integer> list_L = new MyArrayList2_L<>();

        for (int i=0;i<4; i++)
            list_L.add(data[i]);
        list_L.showList();

        for (int i=4;i<10; i++)
            list_L.add(data[i]);
        list_L.showList();

        list_L.set(5, 999);
        int x = (int) list_L.get(5);
        System.out.println("\nx = " + x);

        list_L.remove((Integer)336);
        list_L.showList();

        list_L.add(3, 111);
        list_L.showList();
        list_L.add(list_L.size(), 222);
        list_L.showList();

        list_L.clear();
        list_L.showList();

// -----------------------------------------------------------------

        MyArrayList2_L<MyData> list2_L = new MyArrayList2_L<>();

        MyData a = new MyData(1, "aaa");
        MyData b = new MyData(2, "bbb");
        MyData c = new MyData(3, "ccc");
        // MyData.toString()이 "이름(번호)" 형식으로 되어 있음

        list2_L.add(a);
        list2_L.add(b);
        list2_L.add(c);

        list2_L.showList();

        // 출력 결과 : aaa(1) bbb(2) ccc(3)
        // -> MyData_toString()에서 return name + "(" + num + ")";로 저장되었기 때문

        System.out.println(list2_L.contains(a)); // a 객체 들어있음 -> true
        System.out.println(list2_L.contains(new MyData(1, "aaa")));
    }
}
