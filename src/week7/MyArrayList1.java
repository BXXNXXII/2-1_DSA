package week7;

import java.util.Arrays;

public class MyArrayList1 implements MyList{

    final int INITIAL_CAPACITY = 5; // 시작 용량

    int [] array; // 데이터를 저장할 배열
    int capacity; // 배열의 현재 크기
    int size; // 저장된 실제 데이터 개수

    public MyArrayList1 (int n) {
        capacity = INITIAL_CAPACITY; // 고정된 초기값 5 사용
        array = new int[capacity]; // 용량 5만큼 배열 생성
        size = 0; // 아직 저장된 요소는 X
    }

    // 요소 추가 (맨 뒤에)
    @Override
    public void add(Object value) {
        if(isfull()) { // 배열이 꽉 찼으면
            grow(); // grow() 호출해서 배열 크기 두 배로 확장
        }
        array[size++] = (int)value; // 그 다음 위치에 삽입
    }
    
    // 배열 확장 메소드
    private void grow() {
        int[] tempArray = new int[capacity*2]; 
        // 현재 배열의 2배 크기로 새로운 tempArray 배열 생성
        for (int i = 0; i < size; i++) {
            tempArray[i] = array[i]; // tempArray에 기존 값 복사
        }
        array = tempArray; // 기존 tempArray를 새 배열로 교체
        capacity *= 2; // 현재 배열 크기도 2배로 증가
    }
    
    // 지정 인덱스에 값 삽입
    @Override
    public void add(int index, Object value) {
        if(checkIndexRange(index)) { //? 0<= index <=size-1, 인덱스가 유효한 범위이면
            if(isfull()) { // 꽉 찼으면
                grow(); // 확장
            }
            System.arraycopy( array, index, array, index + 1, size - index ); 
            // for문과 같은 의미
            // 뒤로 밀기
            array[index] = (int)value; // 값 삽입
            size++; // 요소 개수 증가
        }
        else if(index==size) { // add to left position(끝에 추가할 경우)
            add(value); // 기본 add 호출
        }
    }

    // 리스트가 비었는지 확인
    @Override
    public boolean isEmpty() {
        return (size == 0); // size가 0이면 true
    }

    // 특정 값의 인덱스를 반환
    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == (int)value) {
                return i; // 값이 존재하면 index 반환
            }
        }
        return -1; // -1 means "not found!"
    }
    
    // 배열이 가득 찼는지 확인
    private boolean isfull() {
        return (size == capacity);
        // size(저장된 요소 개수)와 capacity(배열의 현재 크기)가 같으면 true
        // 저장된 요소의 개수 = 저장 공간의 크기 -> 꽉 찼음을 뜻함
    }
    
    // 인덱스 범위 검사
    private boolean checkIndexRange(int index) {
        if(index >= 0 && index < size) { // 유효한 인덱스 범위인지 확인
            return true;
        } else {
            return false;
        }
    }

    // 전체 삭제(초기화)
    @Override
    public void clear() {
        Arrays.fill(array, 0); // 모든 배열 요소 0으로 초기화
        size = 0; // 저장된 개수도 0으로 설정
    }
    
    // 특정 값 포함 여부
    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == (int)value) {
                return true; // 값이 있으면 true
            }
        }
        return false;
//		return !(indexOf(value)==-1);
    }
    
    // 특정 값 삭제
    @Override
    public boolean remove(Object value) {

        int index = indexOf(value); // 값의 인덱스 찾기

        if (checkIndexRange(index)) {
            // index 이후 요소들을 한 칸씩 앞으로 복사해서 덮어씀
            for (int i = index+1; i < size; i++) {
                array[i-1] = array[i];
            }
            // 또는 System.arraycopy(array, index+1, array, index, size-index-1);
            size--; // 요소 개수 감소
            return true; // 삭제 성공
        }
        else {
            return false; // 삭제 실패(인덱스가 유효하지 않은 경우)
        }
    }
    
    // 특정 인덱스 삭제
    @Override
    public Object remove(int index) {
        
        if (checkIndexRange(index)) {
            // index 이후 요소들을 한 칸씩 앞으로 복사해서 덮어씀
            // arraycopy 또는 for문 : 동일한 효과
            System.arraycopy(array, index+1, array, index, size-index-1);
//			for (int i = index+1; i < size; i++) {
//				array[i-1] = array[i];
//			}
            size--; // 요소 수 감소
            return true; // 삭제 성공
        }
        else {
            return false; // 삭제 실패(인덱스가 유요하지 않은 경우)
        }
    }
    
    // 현재 저장된 개수 반환
    @Override
    public int size() {
        return size;
    }

    // 값 읽기(get)
    @Override
    public Object get(int index) {
        if (checkIndexRange(index)) {
            return array[index]; // 유효한 인덱스면 해당 값 반환
        }
        return null; // 범위 초과 시 null
    }
    
    // 값 설정(set)
    @Override
    public void set(int index, Object value) {
        if(checkIndexRange(index)) {
            array[index]=(int)value; // 인덱스 위치에 값 설정
        }
    }
    
    // 문자열로 리스트 표현
    @Override
    public String toString() {
        String ret = "";
        for (int i=0; i<size; i++)
            ret = ret + array[i] + " ";
        return ret;
    }
    
    // 현재 리스트 상태 출력
    public void showList() {
        System.out.println("Current List Status : ");
        System.out.println(toString());
        System.out.println(">>> Cuttent Capacity = " + capacity + "    Size = " + size);
        // 배열 용량(capacity)과 요소 개수(size) 출력
    }

    public static void main(String[] args) {

        int [] data = {113,  336,  74,  71,  86,  176,  313,  80,  225,  342,
                170,  292,  275,  266 , 79,  16,  109,  175 , 245,  156,
                50,  61,  277,  167,  81,  24,  76,  186,  78,  101,
                301,  62,  152,  219,  294};

        MyArrayList1 list = new MyArrayList1(20);
        // 리스트 용량 20으로 생성
        // 초기 5로 맞췄던 용량은 무시됨

        for (int i = 0; i < 4; i++)  // [0], [1], [2], [3]
            list.add(data[i]);  // cf :  list[i]=data[i]
            // 처음 4개 데이터 추가
        list.showList();
        // 출력 결과 : >>> Cuttent Capacity = 5    Size = 4
        // 초기 용량이 5, data[0], [1], [2], [3] -> 총 4개 삽입 : size = 4
        // 용량 5 > 현재 개수 4이므로 배열이 다 차지 않음 -> grow() 호출 X

        for (int i=4;i<10; i++) // + [4], [5], [6], [7], [8], [9]
            list.add(data[i]);  // cf :  list[i]=data[i]
            // 이후 6개 데이터 추가
        list.showList();
        // data[4] 추가 시점에 size = 4 → 추가 후 size = 5 → 배열 가득 참
        // grow() 호출 → 용량 5 → 10으로 확장됨
        // 10개의 자리 모두 채워짐

        list.set(5, 999);     // index(list)[5](원래 값은 176)의 값을 999로 변경
        int x =(int) list.get(5);  // index(list)[5]의 값 읽기
        System.out.println("\nx = "+x); // get(5) → x = 999 출력

        list.remove((Integer)336); // 값 336 삭제
        list.showList();
//      336은 인덱스 1에 있었음
//      삭제 후, 뒤의 값들이 앞으로 한 칸씩 밀려옴
//      size-- → 10 - 1 = 9
//      하지만 배열은 10칸이라 마지막 값 342가 그대로 남아 있음
//      capacity = 10, size = 9

        list.add(3, 111); // index(list)[3]에 111 삽입
        list.showList();
//      인덱스 3에 111 삽입
//      뒤의 값들이 밀림
//      size++ → 9 + 1 = 10
//      배열 용량은 충분해서 grow()는 실행되지 않음
        
        list.add(list.size(), 222); // 리스트 마지막에 222 추가
        list.showList();
//      size == capacity(10) → 배열 가득 참
//      grow() 실행 → 10 → 10*2=20으로 확장
//      array[10]에 222 추가됨
//      capacity = 20, size = 11

        list.clear(); // 리스트 초기화
        list.showList(); // 상태 출력 (빈 리스트)
//      clear() 호출 → 배열은 0으로 초기화, size = 0
//      출력에서는 아무 요소도 보이지 않음
//      배열 용량은 그대로 유지됨 → capacity = 20

    }
}
