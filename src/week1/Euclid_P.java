package week1;

public class Euclid_P {
    public static void main(String[] args) {
        int a = 424;
        int b = 36;

        while(true) {
            if (a<b) {
                int temp = a;
                a = b;
                b = temp;
            }
            int r = a % b;
            if(r==0) {
                System.out.println("GCD = " + b);
                break;
            }
            else {
                a = b;
                b = r;
            }
        }

        System.out.println("GCD in Recursion = " + GCDRec_P(a,b));

    }

    private static int GCDRec_P(int a, int b) {
        if(a<b) {
            return GCDRec_P(b, a);
        }
        else {
            int r = a % b;
            if(r==0) {
                return b;
            }
            else {
                return GCDRec_P(b, r);
            }
        }
    }
}
