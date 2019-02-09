package core;

public class Deck {
    public static final int PAIR = 0;
    public static final int TRIPLE = 1;
    public static final int FOUR_CARDS = 2;

    private Card[] cards = new Card[52];

    public void init() {
        int index = 0;
        for(int i = 0; i < cards.length; i++) cards[i] = new Card();

        for(int shape = 0; shape < 4; shape++) {
            for(int number = 2; number <= 14; number++) {
                cards[index].number = number;
                cards[index].shape = shape;

                index++;
            }
        }
    }

    //  카드를 나눠줌
    public Card[] drawCards(int count) {
        Card[] cards = new Card[count];

        for(int i = 0; i < cards.length; i++) {
            while(true) {
                cards[i] = randomCard();

                if(!cards[i].selected) {
                    cards[i].selected = true;
                    break;
                }
            }
        }

        return cards;
    }

    private Card randomCard() {
        return cards[(int)(Math.random() * 52)];
    }

    public Result getResult(Card[] cards) {
        Result result = new Result();

        checkStraightFlush(cards, result);
        checkFourCards(cards, result);
        checkFullHouse(cards, result);
        checkFlush(cards, result);
        checkStraight(cards, result);
        checkTriple(cards, result);
        checkPair(cards, result);
        checkTop(cards, result);

        System.out.println(result.toString());

        return result;
    }

    private void checkStraightFlush(Card[] cards, Result result) {
        if(result.isDone()) return;

        if(isStraight(cards) && isFlush(cards)) {
            result.what = Result.STRAIGHT_FLUSH;
            findTops(cards, result);
        }
    }

    private void checkFourCards(Card[] cards, Result result) {
        if(result.isDone()) return;

        HaechiArray tops = sameNumbers(cards, FOUR_CARDS);

        if(!tops.isEmpty()) {
            result.what = Result.FOUR_CARDS;
            result.top = tops.first();
        }
    }

    private void checkFullHouse(Card[] cards, Result result) {
        if(result.isDone()) return;

        HaechiArray tripleTops = sameNumbers(cards , TRIPLE);
        HaechiArray pairTops = sameNumbers(cards , PAIR);

        if(!tripleTops.isEmpty() && !pairTops.isEmpty()) {
            result.what = Result.FULL_HOUSE;
            result.top = tripleTops.first();
        }
    }

    private void checkFlush(Card[] cards, Result result) {
        if(result.isDone()) return;

        if(isFlush(cards)) {
            result.what = Result.FLUSH;
            findTops(cards, result);
        }
    }

    private void checkStraight(Card[] cards, Result result) {
        if(result.isDone()) return;

        if(isStraight(cards)) {
            result.what = Result.STRAIGHT;
            findTops(cards, result);
        }
    }

    private void checkTriple(Card[] cards, Result result) {
        if(result.isDone()) return;

        HaechiArray tops = sameNumbers(cards, TRIPLE);

        if(!tops.isEmpty()) {
            result.what = Result.TRIPLE;
            result.top = tops.first();
        }
    }

    private void checkPair(Card[] cards, Result result) {
        if(result.isDone()) return;

        HaechiArray tops = sameNumbers(cards, PAIR);
        tops.sort(false);

        if(!tops.isEmpty()) {
            result.what = tops.size() == 1 ? Result.ONE_PAIR : Result.TWO_PAIR;
            result.top = tops.first();
            result.tops = tops;

            for(int i = 0; i < cards.length; i++) {
                if(!cards[i].checked) result.others.add(cards[i].number);
            }
        }
    }

    private void checkTop(Card[] cards, Result result) {
        if(result.isDone()) return;
        if(findTops(cards, result)) result.what = Result.TOP;
    }


    private boolean isFlush(Card[] cards) {
        for(int i = 0; i < (cards.length - 1); i++) {
            int count = 1;
            clearChecked(cards);

            for(int j = (i + 1); j < cards.length; j++) {
                if (cards[i].shape == cards[j].shape) {
                    cards[i].checked = true;
                    cards[j].checked = true;
                    count++;
                }
            }

            if(count >= 5) return true;
        }

        return false;
    }

    private boolean isStraight(Card[] cards) {
        int count = 1;

        clearChecked(cards);
        sort(cards, false);

        if(isBackStraight(cards)) return true;

        for (int i = 0; i < (cards.length - 1); i++) {
            if (cards[i].number == cards[i + 1].number) continue;
            else if ((cards[i].number - 1) == cards[i + 1].number) {
                count++;
                if (count >= 5) return true;
            }
            else count = 1;
        }

        return false;
    }

    private boolean isBackStraight(Card[] cards) {
        int[] backStraight = { 2, 3, 4, 5, 14 };

        for(int i = 0; i < backStraight.length; i++) {
            if(!existsNumber(cards, backStraight[i])) return false;
        }

        return true;
    }

    private boolean findTops(Card[] cards, Result result) {
        if(cards.length <= 0) return false;

        sort(cards, false);

        result.top = cards[0].number;

        for(int i = 0; i < cards.length; i++) {
            result.tops.add(cards[i].number);
        }

        return true;
    }

    private boolean existsNumber(Card[] cards, int number) {
        for(int i = 0; i < cards.length; i++) {
            if(cards[i].number == number) return true;
        }

        return false;
    }

    private HaechiArray sameNumbers(Card[] cards, int type) {
        HaechiArray array = new HaechiArray();

        clearChecked(cards);

        for(int i = 0; i < cards.length - 1; i++) {
            if(cards[i].checked) continue;

            int count = 1;

            for(int j = (i + 1); j < cards.length; j++) {
                if(cards[j].checked) continue;

                if(cards[i].number == cards[j].number) {
                    cards[i].checked = true;
                    cards[j].checked = true;
                    count++;
                }
            }

            if(count == 2 && type == PAIR) array.add(cards[i].number);
            else if(count == 3 && type == TRIPLE) array.add(cards[i].number);
            else if(count == 4 && type == FOUR_CARDS) array.add(cards[i].number);
        }

        return array;
    }

    public static void sort(Card[] cards, boolean ascending) {
        Card temp;

        for(int i = 0; i < cards.length - 1; i++) {
            for(int j = (i + 1); j < cards.length; j++) {
                if((cards[i].number > cards[j].number && ascending) || (cards[i].number < cards[j].number && !ascending)) {
                    temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
            }
        }
    }

    private void clearChecked(Card[] cards) {
        for(int i = 0; i < cards.length; i++) {
            cards[i].checked = false;
        }
    }
}