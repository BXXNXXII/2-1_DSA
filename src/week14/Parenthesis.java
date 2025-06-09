package week14;

import java.util.ArrayList;
import java.util.List;

class Parenthesis {
    public void genCases(int n) {
        String curStr = "";
        List<String> cases = new ArrayList<>();
        int open = 0;
        int close = 0;
        genCases(cases, curStr, open, close, n);

        for (String str:cases) {
            System.out.println(str);
        }
    }

    private void genCases(List<String> cases, String curStr, int open, int close, int n) {
        if (curStr.length() == 2*n) {
            cases.add(curStr);
            return;
        }
        if (open < n) {
            genCases(cases, curStr + "(", open+1, close, n);
        }
        if (close < n) {
            genCases(cases, curStr + ")", open, close+1, n);
        }
    }

    public static void main(String[] args) {
        Parenthesis p = new Parenthesis();
        for (int n = 1; n < 5; n++) {
            System.out.println("\n>>> N = " + n);
            p.genCases(n);
        }
    }
}
