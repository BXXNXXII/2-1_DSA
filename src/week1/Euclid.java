package week1;

public class Euclid {
    public static void main(String[] args) {
        int a = 424;
        int b = 36;

        System.out.println("GCD in Recursion =  " + GCDRec(a,b));

        while(true) {
            if(a<b) {
                int temp = a;
                a=b;
                b=temp;
            }
            int r = a%b;
            if(r==0) {
                System.out.println("GCD = " + b);
                break;
            }
            else {
                a = b;
                b = r;
            }
        }
    }

    private static int GCDRec(int a, int b) {
        if(a<b) {
            return GCDRec(b,a);
        }
        else {
            int r = a%b;
            if(r==0) {
                return b;
            }
            else {
                return GCDRec(b,r);
            }
        }
    }
}