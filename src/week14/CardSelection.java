package week14;

public class CardSelection {

    int [] card;

    public CardSelection(int [] d) {
       card = d;
    }

    public int selectNSum() {
        return selectNSum(0, card.length-1);
    }

    private int selectNSum(int i, int j) {
        if (i+1==j) {
            return Math.max(card[i],card[j]);
        }
        else {
            return Math.max( card[i] + Math.min(selectNSum(i+2, j), selectNSum(i+1, j-1) ),
                    card[j] + Math.min(selectNSum(i+1, j-1), selectNSum(i, j-2)));
        }
    }

    public static void main(String[] args) {
        int [] d = {11, 21, 3, 4, 5, 9, 8, 7, 6, 10}; // must be even num
        CardSelection s = new CardSelection(d);
        System.out.println("\nMaxSum = " + s.selectNSum());
    }
}
