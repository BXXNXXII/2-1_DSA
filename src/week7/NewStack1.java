package week7;


public class NewStack1<T> {

    MyArrayList2_L<T> stack;

    public NewStack1() {
        stack = new MyArrayList2_L<>(); // MyArrayList2를 사용하여 스택 구현
    }

    public void push(T value) {
        stack.add(value); // 요소를 스택에 추가
    }

    public T pop() {
        return stack.remove (stack.size()-1);
        // 가장 마지막에 추가된 요소를 제거하고 반환
    }

    public T peek() {
        return stack.get(stack.size()-1);
        // 가장 마지막 요소 반환(삭제X)
    }

    public void showStack() {
        stack.showList();
    }

    public static void main(String[] args) {

        int [] data= {3,5,4,1,7,2,9,8,6,0};

        NewStack1<Integer> stack = new NewStack1<>(); // integer 타입 스택 생성

        for (int i=0; i<data.length;i++) {
            stack.push(data[i]); // data를 stack에 push(추가)
            stack.showStack();
        }

        for (int i=0; i<data.length;i++) {
            stack.pop(); // stack에서 data를 pop
            stack.showStack();
        }

        NewStack1<String> stack2 = new NewStack1<>(); // String 타입 스택 생성

        stack2.push("AAA");
        stack2.push("BBB");
        stack2.showStack();

    }
}
