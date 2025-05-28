package week12;

import java.util.Arrays;

public class BSTA {
    int [] array;
    int nNode;

    public BSTA(){
        array = new int[100];
        Arrays.fill(array, -1);
        nNode = 0;
    }

    public void insert(int d){
        if (nNode == 0) {
            array[1] = d;
            nNode++;
        }
        else {
            int i = 1;
            while (array[i] != -1) {
                if (d < array[i]) {
                    i = i * 2;
                }
                else {
                    i = i * 2 + 1;
                }
            }
            array[i] = d;
            nNode++;
        }
    }

    public void showTree() {
        for (int i = 1; i <= 30; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//      int[] keys = {4, 7, 5, 1, 0, 3, 9, 2, 6, 8};
        int[] keys = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        BSTA t = new BSTA();

        for (int i = 0; i < keys.length; i++) {
            t.insert(keys[i]);
            t.showTree();
        }
    }
}
