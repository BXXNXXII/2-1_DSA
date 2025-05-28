package week10;

public class MyLinkedList2<T> {
	private class Node{
		T data;
		Node next;

		Node(T d){
			data=d;
			next=null;
		}

		public String toString() {
			return ""+data.toString();
		}

	}

	Node head;
	int size;

	public MyLinkedList2() {
		head=null;
		size=0;
	}

	public boolean isEmpty() {
		return (head==null);
	}

	public void add(T value) {
		addFirst(value);
//			addLast(value);
	}


	private void addFirst(T value) {
		Node newNode=new Node(value);
		newNode.next=head;
		head = newNode;
		size++;
	}

	private void addLast(T value) {
		if (isEmpty())
			addFirst(value);
		else {
			Node newNode=new Node(value);
			Node p=head;
			while(p.next!=null) {
				p=p.next;
			}
			p.next=newNode;
			size++;
		}
	}

	public int indexOf(T value) {
		int index =0;
		Node  p=head;
		while(p!=null) {
			if (p.data.equals(value))
				return index;
			else {
				index++;
				p=p.next;
			}
		}
		return -1;
	}

	public void add(int index, T value) {  // 0<= index  <= size()
		if (checkIndexRange(index)) {  // 0<= index  < size()
			if (index==0)
				addFirst(value);
			else {
				Node newNode= new Node(value);
				int i=1;
				Node p=head;  //  p!=null !!!
				while(p.next !=null) {
					if (i==index) {
						newNode.next=p.next;
						p.next=newNode;
						size++;
						return;
					}
					else {
						i++;
						p=p.next;
					}
				}
			}
		}
		else if (index==size())
			addLast(value);
	}

	public int size() {
//			int n=0;
//			Node p=head;
//			while(p!=null) {
//				n++;
//				p=p.next;
//			}
//			return n;
		return size;
	}

	public void clear() {
		head=null;
	}

	public boolean contains(T value) {
		return (indexOf(value)!=-1);
	}

	public T get(int index) {
		int i=0;
		Node p=head;
		while( p !=null) {
			if (i==index) {
				return p.data;
			}
			i++;
			p=p.next;
		}
		return null; // null
	}

	public void set(int index, T value) {
		int i=0;
		Node p=head;
		while( p !=null) {
			if (i==index) {
				p.data=value;
			}
			i++;
			p=p.next;
		}
		//  error message!
	}

	public T remove(int index) {  // return the value removed
		T ret = null;
		if (checkIndexRange(index)) {
			if (index==0) {
				ret =removeFirst();
			}
			else {
				int i=1;
				Node p=head;
				Node q=p.next;
				while( q !=null) {
					if (i==index) {
						ret=q.data;
						p.next=q.next;
						size--;
						break;
					}
					i++;
					p=q;
					q=q.next;
				}
			}
		}
		return ret;
	}

	public T remove(T value) {
		if (head!=null) {
			if (head.data==value)
				return removeFirst();
			else {
				Node p=head;
				Node q=p.next;
				while(q!=null) {
					if (q.data.equals(value)) { //  == -> equals, <> -> compareTo(..)
						p.next=q.next;
						size--;
						return q.data;
					}
					p=q;
					q=q.next;
				}
			}
		}
		return null;
	}

	private T removeFirst() {
		T ret = null;
		if (head!=null) {
			ret = head.data;
			head = head.next;
			size--;
		}
		return ret;
	}

	private boolean checkIndexRange(int index) {
		if (index>=0 && index<size())
			return true;
		else return false;
	}

	public String toString() {
		String str="";
		Node p=head;
		while(p!=null) {
			str=str+p.data.toString()+"  ";
			p=p.next;
		}
		return str;
	}
	public void  showList() {
		System.out.println(toString());
	}

	public static void main(String[] args) {

//			String [] data = {"kim","lee","park","choi","jung","kang","cho","yoon","jang"};
		Object [][] data = {{1,"kim"},{2,"lee"},{3,"park"},{4,"choi"},{5,"jung"},
				{6,"kang"},{7,"cho"},{8,"yoon"},{9,"jang"}};

		MyLinkedList2<MyData> list = new MyLinkedList2<>();

		for (int i=0;i<data.length; i++) {
			list.add(new MyData((int)data[i][0], (String)data[i][1]));
			list.showList();
		}

		System.out.println(list.get(0));
		System.out.println(list.get(5));
		System.out.println(list.get(8));

		list.add(0,new MyData(10, "Lim"));
		list.add(5,new MyData(11, "han"));
		list.add(list.size(),new MyData(12, "oh"));
		list.showList();

		System.out.println(">>>  "+ list.indexOf(new MyData(2, "lee")));

		System.out.println(list.remove(0));
		list.showList();
		System.out.println(list.remove(5));
		list.showList();
		System.out.println(list.remove(list.size()-1));
		list.showList();

		System.out.println(list.indexOf(new MyData(11, "han")));
		System.out.println(list.remove(new MyData(11, "han")));
		list.showList();


	}

}
