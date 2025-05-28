package week10;

public class MyLinkedList1 {
	private class Node{
		String data;
		Node next;

		Node(String d){
			data=d;
			next=null;
		}

		public String toString() {
			return ""+data;
		}

	}

	Node head;
	int size;

	public MyLinkedList1() {
		head=null;
		size=0;
	}

	public boolean isEmpty() {
		return (head==null);
	}

	public void add(String value) {
		addFirst(value);
//		addLast(value);
	}


	private void addFirst(String value) {
		Node newNode=new Node(value);
		newNode.next=head;
		head = newNode;
		size++;
	}

	private void addLast(String value) {
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

	public int indexOf(String value) {
		int index =0;
		Node  p=head;
		while(p!=null) {
			if (p.data==value)
				return index;
			else {
				index++;
				p=p.next;
			}
		}
		return -1;
	}

	public void add(int index, String value) {  // 0<= index  <= size()
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

	private int size() {
//		int n=0;
//		Node p=head;
//		while(p!=null) {
//			n++;
//			p=p.next;
//		}
//		return n;
		return size;
	}

	public void clear() {
		head=null;
	}

	public boolean contains(String value) {
		return (indexOf(value)!=-1);
	}

	public String get(int index) {
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

	public void set(int index, String value) {
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

	public String remove(int index) {  // return the value removed
		String ret = null;
		if (checkIndexRange(index)) {
			if (index==0) {
				ret =removeFirst();
			}
			else {
				int i=1;
				Node p=head;
				while(p.next!=null) {
					if (i==index) {
						ret=p.next.data;
						p.next=p.next.next;
						size--;
						break;
					}
					i++;
					p=p.next;
				}
			}
		}
		return ret;
	}
	private String removeLast() {
		if (!isEmpty()) {
			if (head.next==null) {
				return removeFirst();
			}
			else {
				Node p=head;
				Node q = p.next;
				while(q!=null) {
					if (q.next==null) {
						p.next=null;
						size--;
						return q.data;
					}
					else {
						p=q;
						q=q.next;
					}
				}
			}
		}
		return null;
	}

	public String remove(String value) {
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

	private String removeFirst() {
		String ret = null;
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
			str=str+p.data+"  ";
			p=p.next;
		}
		return str;
	}
	public void  showList() {
		System.out.println(toString());
	}

	public static void main(String[] args) {

		String [] data = {"kim","lee","park","choi","jung","kang","cho","yoon","jang"};

		MyLinkedList1 list = new MyLinkedList1();

		for (int i=0;i<data.length; i++) {
			list.add(data[i]);
			list.showList();
		}

		System.out.println(list.get(0));
		System.out.println(list.get(5));
		System.out.println(list.get(11));

		list.add(0, "Lim");
		list.add(5, "han");
		list.add(list.size(), "oh");
		list.showList();

		System.out.println(">>> "+list.indexOf("lee"));

		System.out.println(list.remove(0));
		list.showList();
		System.out.println(list.remove(5));
		list.showList();
		System.out.println(list.remove(list.size()-1));
		list.showList();

		System.out.println(list.remove("han"));
		list.showList();

	}


}
