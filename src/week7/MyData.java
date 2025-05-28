package week7;

public class MyData {
    int num;
    String name;

    public MyData(int n, String s) {
        num = n;
        name = s;
    }

    // override
    public String toString() {
        return name + "(" + num + ")";
    }

    // override
    public boolean equals(Object that) {
        return (this.num == ((MyData)that).num); // name은 무시하고 num만 비교
    }
}
