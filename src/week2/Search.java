package week2;

import java.util.Arrays;

public class Search {

    public int seqS(int[] d, int key) {
        for(int i = 0; i < d.length; i++) {
            if(d[i] == key) {
                return i;
            }
        }
        return -1; // 검색 결과가 없을 때 -1 리턴

//        int index = 0;
//        while(index < d.length) {
//            if(d[index] == key) {
//                return index;
//            } else {
//                index++;
//            }
//        }
//        return -1;
    }

    public int seqSortedS(int[] d, int key) {
        int index = 0;
//        while(index < d.length) {
//            if(d[index] == key) {
//                return index;
//            } else if(d[index] > key) {
//                return -1;
//            } else {
//                index++;
//            }
//        }
//        return -1;

        while(index < d.length && d[index] <= key) {
            if(d[index] == key) {
                return index;
            } else {
                index++;
            }
        }
        return -1;
    }

    private int binS(int[] d, int key, int start, int end) {
        if(start > end) {
            return -1;
        } int mid = (start + end) / 2;

        if(key==d[mid]) {
            return mid;
        } else if(key<d[mid]) { // start ~ mid-1 까지만 비교
            return binS(d, key, start, mid-1);
        } else { // key>d[mid]
            return binS(d, key, mid+1, end);
        }
    }

    // week 3
    private int seqSRec(int[] d, int n, int key) {
        if (n==0) {
            return -1;
        } else if (d[n-1]==key) {
            return n-1;
        } else {
            return seqSRec(d, n-1, key);
        }
    }

    public static void main(String[] args) {
        int [] data = {10, 45, 40, 20, 15, 50, 25, 5, 30, 35, 55};
        int [] sData = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};

        Search me = new Search();

        System.out.println(me.seqS(data, 25));
        System.out.println(me.seqSortedS(sData, 27));

        System.out.println(me.binS(sData, 25, 0, sData.length-1));

        // week 3
        System.out.println(me.seqSRec(data, data.length-1, 25));
    }




}
