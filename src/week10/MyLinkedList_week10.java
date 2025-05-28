package week10;

public class MyLinkedList_week10<T> { // double linked list
    class Node {
        T data;
        Node prev;
        Node next;

        Node(T d) {
            data = d;
            prev = null;
            next = null;
        }

        public String toString() {
            return "" + data.toString();
        }
    }

    Node head, tail;
    int size;

    public MyLinkedList_week10() {
        head = null;
        tail = null;
    }

    public void add(T value) {
        addFirst(value);
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

    private void addLast(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
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
                for (int i = 0; i < index - 1; i++) {
                    p = p.next;
                }
                newNode.prev = p;
                newNode.next = p.next;
                p.next.prev = newNode;
                p.next = newNode;
                size++;
            }
        }
    }

    public int indexOf(T value) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public T get(int index) {
        if (!checkIndexRange(index)) return null;
        Node p;
        if (index < size / 2) {
            p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = tail;
            for (int i = size - 1; i > index; i--) {
                p = p.prev;
            }
        }
        return p.data;
    }

    public void set(int index, T value) {
        if (!checkIndexRange(index)) return;
        Node p;
        if (index < size / 2) {
            p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = tail;
            for (int i = size - 1; i > index; i--) {
                p = p.prev;
            }
        }
        p.data = value;
    }

    private int size() {
        return size;
    }

    private boolean checkIndexRange(int index) {
        return index >= 0 && index < size;
    }

    private boolean isEmpty() {
        return head == null;

    }

    public void showList() {
        System.out.println(toString());
    }

    public static void main(String[] args) {

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
