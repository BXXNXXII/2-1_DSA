package week6;

public class MyStack<T> {
	// Stack : 후입선출(나중에 넣은 데이터가 가장 먼저 나옴)
	// ex) 접시 쌓기, 책 쌓기

	// <T> : 타입(Type)의 약자로, 어떤 자료형이든 들어올 수 있다는 뜻
	// 타입을 나중에 지정할 수 있게 해주는 자리 표시자
	// 스택을 모든 타입에 대해 재사용 가능

	Object [] Stack; // 데이터를 담을 배열
	int StackSize; // 최대 크기
	int top; // 현재 넣을 위치 or 스택 크기

	public MyStack(int n) {
		StackSize = n;
		Stack = new Object [StackSize];
		top = 0; // 아직 아무 것도 안 들어감
	}

	public boolean isEmpty() { // 아무 것도 안 들어왔으면
		return(top == 0); // top은 0 : 스택은 비어있음
	}

	public boolean isFull() {
		return(top == StackSize); // top이 배열 끝(StackSize)까지 가면 꽉 찼다는 뜻
	}

	/**public void push(String value) {
	 if(isFull()) {
	 System.out.println("Stack is full");
	 return;
	 } else {
	 Stack[top++] = value;
	 }
	 }*/

	public void push(T value) {
		// push : 스택에 넣는다, 쌓는다
		if(isFull()) {
			System.out.println("Stack is full");
			return;
		} else { // 스택이 꽉 차있지 않다면
			Stack[top++] = value; // Stack[top]에 값(value)을 넣고, top을 1 증가
			// 후입 방식으로 차곡차곡 쌓임
		}
	}

	/**public int pop() {
	 if(isEmpty()) {
	 System.out.println("Stack is empty");
	 return -999;
	 } else {
	 return (int)Stack[--top];
	 }
	 }*/

	public T pop() {
		// pop : 스택에서 꺼낸다, 제거하고 반환
		if(isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		} else {
			return (T)Stack[--top]; // top을 먼저 줄이고 → 해당 위치의 값을 반환
			// 가장 마지막에 넣은 값이 가장 먼저 나옴
		}
	}

	public void showStack() {
		for (int i = 0; i < top; i++) {
			System.out.print(Stack[i] + " ");
		}
		System.out.println();
	}


	public static void main(String[] args) {
		int [] data= {3,5,4,1,7,2,9,8,6,0};


		MyStack stack = new MyStack(10);

		for (int i=0; i<data.length;i++) {
			stack.push(data[i]);
			stack.showStack();
		}

		for (int i=0; i<data.length;i++) {
			stack.pop();
			stack.showStack();
		}

	}

}
