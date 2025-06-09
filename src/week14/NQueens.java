package week14;

import java.util.Arrays;

public class NQueens {

    int N;
    int [] cols;

    public NQueens(int n) {
        N = n;
        cols = new int[N];
        Arrays.fill(cols, -1);
    }

    public boolean solve() {
        return probe(0);
    }

    private boolean probe(int i) {
        if (i == N) {
            for (int k = 0; k < N; k++) {
                System.out.print(cols[k]);
            }
            System.out.println();
            return true;
        }
        else {
            for (int c = 0; c < N; c++) {
                cols[i] = c;
                if (feasible(i)) {
                    if (probe(i+1)) {
//                        return true;
                        System.out.println(cols[i]);
                    }
                }
            }
            return false;
        }
    }

    private boolean feasible(int row) {
        //check with cols[0] ~ cols[i-1]
        for (int i = 0; i < row; i++) {
            if (cols[i] == cols[row]) {
                return false;
            }
            else if ((row-i) == Math.abs(cols[row] - cols[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n;
        n = 2;
        NQueens q = new NQueens(n);
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());
        n = 3;
        q = new NQueens(n);
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());
        n = 4;
        q = new NQueens(n);
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());
        n = 5;
        q = new NQueens(n);
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());
        n = 6;
        q = new NQueens(n);
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());
    }
}
