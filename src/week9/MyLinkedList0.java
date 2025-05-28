package week9;

public class MyLinkedList0 {
    private class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }

        public String toString() {
            return "" + data;
        }
    }

    Node head;

    int size;

    public MyLinkedList0() {
        head = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void add(int value) {
        addFirst(value);
        // addLast(value);
    }

    private void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    private void addLast(int value) {
        if (isEmpty()) {
            addFirst(value);
        } else {
            Node newNode = new Node(value);
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = newNode;
            size++;
        }
    }

    public int indexOf(int value) {
        int index = -1; // index에서 -1은 null의 의미를 가짐
        Node p = head;
        while (p != null) {
            if (p.data == value) {
                return ++index;
            } else {
                index++;
                p = p.next;
            }
        }
        return -1;
    }

    public void add(int index, int value) { //0<= index <= size()
        if (checkIndexRange(index)) { // 0<= index < size()
            if (index == 0) {
                addFirst(value);
            }
            else {
                Node newNode = new Node(value);
                int i = 1;
                Node p = head; // p != null (!!!!)
                while (p.next != null) {
                    if (i == index) {
                        newNode.next = p.next;
                        p.next = newNode;
                        size++;
                        return;
                    } else {
                        i++;
                        p = p.next;
                    }
                }
            }
        }
        else if(index == size()) {
            addLast(value);
        }
    }

    private boolean checkIndexRange(int index) {
        return index >= 0 && index < size();
    }

    private int size() {
//        int n = 0;
//        Node p = head;
//        while (p != null) {
//            n++;
//            p = p.next;
//        }
//        return  n;
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public boolean contains(int value) {
        return (indexOf(value) != -1);
    }

    public int get(int index) {
        int i = 0;
        Node p = head;
        while (p != null) {
            if (i == index) {
                return p.data;
            }
            i++;
            p = p.next;
        }
        return -9999;
    }

    public void set(int index, int value) {
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

    public int remove(int index) { // return the value removed
        int ret = -9999;
        if (checkIndexRange(index)) {
            if (index == 0) {
                ret = removeFirst();
            }
            else {
                int i = 1;
                Node p = head;
                Node q = p.next;
                while (q != null) {
                    if (i == index) {
                        ret = q.data;
                        p.next = q.next; // p.next 날림
                        break;
                    }
                    i++;
                    p = q;
                    q = q.next;
                }
            }
        }
        return ret;
    }

    public int remove(Object value) {
        if(head != null) {
            if (head.data == (int)value) {
                return removeFirst();
            }
            else {
                Node p = head;
                Node q = p.next;
                while (q != null) {
                    if (q.data == (int)value) {
                        p.next = q.next;
                        return q.data;
                    }
                    p = q;
                    q = q.next;
                }
            }
        }
        return -9999;
    }

    private int removeFirst() {
        int ret = -9999;
        if(head != null) {
            ret = head.data;
            head = head.next;
        }
        return ret;
    }

    public String toString() {
        String str = "";
        Node p = head;
        while (p != null) {
            str = str + p.data + "   ";
            p = p.next;
        }
        return str;
    }

    public void showList() {
        System.out.println(toString());
    }

    public static void main(String[] args) {
        int [] data = {113,  336,  74,  71,  86,  176,  313,  80,  225,  342,
                    170,  292,  275,  266 , 79,  16,  109,  175 , 245,  156,
                    50,  61,  277,  167,  81,  24,  76,  186,  78,  101,
                    301,  62,  152,  219,  294
        };

        MyLinkedList0 list = new MyLinkedList0();

        for (int i=0; i<10; i++) {
            list.add(data[i]);
        }
        list.showList();

        list.set(5, 999);
        int x =(int) list.get(5);
        System.out.println("\nx = "+x);

        list.remove((Integer)336);
        list.showList();

        list.add(3, 111);
        list.showList();
        list.add(list.size(), 222);
        list.showList();

        list.clear();
        list.showList();
    }
}