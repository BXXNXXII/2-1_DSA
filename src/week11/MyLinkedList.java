package week11;

public class MyLinkedList<T> {
    class Node {
        T data;
        Node prev;
        Node next;

        Node(T d) {
            data = d;
            prev = null;
            next = null;
        }
    }

    Node head, tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    public void add(T value) {
//        addFirst(value);
        addLast(value);
    }

    private void addFirst(T value) {
        Node newNode= new Node(value);
        if (isEmpty()) {
            head= newNode;
            tail=head;
        }
        else {
            newNode.next=head;
            head.prev = newNode;
            head=newNode;
        }
        size++;
    }

    private boolean isEmpty() {
        return head == null;
    }

    private void addLast(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            tail = newNode;
            head = tail;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, T value) {
        if (checkIndexRange(index) || index == size()) { // 0<= index < size()
            if (index == 0) {
                addFirst(value);
            } else if (index == size()) {
                addLast(value);
            }
            else {
                Node newNode = new Node(value);
                Node p = head;
                for (int i = 0; i < index; i++) {
                    p = p.next;
                }
                newNode.next = p;
                newNode.prev = p.prev;
                p.prev.next = newNode;
                p.prev = newNode;
                size++;
            }
        }
    }

    public int indexOf(T value) {
        int index = 0;
        Node p = head;
        while (p != null) {
            if (p.data.equals(value)) {
                return index;
            }
            else {
                index++;
                p = p.next;
            }
        }
        return -1;
    }

    private int size() {
        return size;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public boolean contains(T value) {
        return (indexOf(value) != -1);
    }

    public T get(int index) {
        int i = 0;
        Node p = head;
        while (p != null) {
            if (i == index) {
                return p.data;
            }
            i++;
            p = p.next;
        }
        return null; // null
    }

    public void set(int index, T value) {
        int i = 0;
        Node p = head;
        while (p != null) {
            if (i == index) {
                p.data = value;
            }
            i++;
            p = p.next;
        }
        // error message!
    }

    public T remove(int index) {  // return the value removed
        T ret = null;
        if (checkIndexRange(index)) {
            if (index==0) {
                ret =removeFirst();
            }
            else if (index==(size-1)) {
                ret = removeLast();
            }
            else {
                Node p = head;
                for (int i = 0; i < index; i++) {
                    p = p.next;
                }
                ret = p.data;

                p.next.prev = p.prev;
                p.prev.next = p.next;
                size--;
            }

        }
        return ret;
    }

    T removeFirst() {
        T ret = null;
        if (head!=null) {
            ret = head.data;
            head = head.next;
            if (head!=null)
                head.prev=null;
            else
                tail=null;
            size--;
        }
        return ret;
    }

    T removeLast() {
        T ret = null;
        if (tail!=null) {
            ret = tail.data;
            tail = tail.prev;
            if (tail!=null)
                tail.next=null;
            else
                head=null;
            size--;
        }
        return ret;
    }

    public T remove(T value) {
        if (head!=null) {
            if (head.data==value) {
                return removeFirst();
            }
            else if(tail.data==null) {
                return removeLast();
            }
            else {
                Node p=head.next;
                while(p!=null) {
                    if (p.data.equals(value)) { //  == -> equals, <> -> compareTo(..)
                        T ret = p.data;
                        p.next.prev = p.prev;
                        p.prev.next = p.next;
                        size--;
                        return ret;
                    }
                    p = p.next;
                }
            }
        }
        return null;
    }

    private boolean checkIndexRange(int index) {
        if (index >= 0 && index < size) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        String str = "";
        Node p = head;
        while (p != null) {
            str = str + p.data.toString() + "   ";
            p = p.next;
        }
        return str;
    }

    public void showList() {
        System.out.println(toString());
    }

    public static void main(String[] args) {

        Object [][] data = {{1,"kim"},{2,"lee"},{3,"park"},{4,"choi"},{5,"jung"},
                {6,"kang"},{7,"cho"},{8,"yoon"},{9,"jang"}};

        MyLinkedList<MyData> list = new MyLinkedList<>();

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
