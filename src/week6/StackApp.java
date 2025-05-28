package week6;

public class StackApp {

    public static void main(String[] args) {
        int [] data= {3,5,4,1,7,2,9,8,6,0};


        MyStack<Integer> stack = new MyStack<>(10);

        for (int i=0; i<data.length;i++) {
            stack.push(data[i]); // 10개 숫자 차례대로 push
            stack.showStack(); // 한 번 넣을 때마다 출력
        } // top에서부터 아래로 쌓이는 구조

        for (int i=0; i<data.length;i++) {
            stack.pop(); // LIFO(후입선출) 구조대로 마지막에 넣은 값부터 나감
            stack.showStack(); // 꺼낼 때마다 출력
        }

        MyStack<String> stack2 = new MyStack<>(5); // 문자열을 담은 스택

        stack2.push("AAA");
        stack2.push("AAA");
        stack2.showStack();

    }

}

