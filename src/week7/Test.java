package week7;

public class Test {

    public static void main(String[] args) {
        MyArrayList2_L<MyArrayList1> list = new MyArrayList2_L<>();
        // MyArrayList2 : "리스트 안에 리스트" 형태 -> MyArrayList1 객체들을 담고 있음
        // MyArrayList1 : 정수형 배열을 다루는 클래스. 중첩된 리스트 다루는 데 적합

        MyArrayList1 a = new MyArrayList1(0);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        MyArrayList1 b = new MyArrayList1(0);
        b.add(11);
        b.add(21);
        b.add(31);

        list.add(a);
        list.add(b);

        list.showList();

        list.get(0).set(2, 30);
        // list.get(0) : 첫 번째 MyArrayList1 리스트 a에 접근
        // set(2, 30) : a 리스트의 index 2번째 값을 30으로 변경(3 -> 30)

        list.showList();
    }
}
