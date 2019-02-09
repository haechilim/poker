package core;

public class OldDeck {
    /*
    int PAIR = 0;
    int TRIPLE = 1;
    int FOUR_CARDS = 2;
    private core.Card[] cards = new core.Card[52];

    //  1 1 3 5 3
    //  9 7 7 9 7

    public void init() {
        int index = 0;
        for(int i = 0; i < cards.length; i++) cards[i] = new core.Card();

        for(int shape = 0; shape < 4; shape++) {
            for(int number = 0; number < 13; number++) {
                cards[index].number = number + 1;
                cards[index].shape = shape;

                index++;
            }
        }
    }

    public core.Card[] drawCards(int count) {
        core.Card[] cards = new core.Card[count];

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

    private core.Card randomCard() {
        return cards[(int)(Math.random() * 52)];
    }

    public String getResult(core.Card[] getCard) {
        if(isStraightFlush(getCard)) return "스트레이트 플러쉬";
        else if(isFourCards(getCard)) return "포커";
        else if(isFullHouse(getCard)) return "풀 하우스";
        else if(isFlush(getCard)) return "플러쉬";
        else if(isStraight(getCard)) return "스트레이트";
        else if(isTriple(getCard)) return "트리플";
        else if(isTwoPair(getCard)) return "투페어";
        else if(isOnePair(getCard)) return "원페어";
        return  "탑";
    }

    private boolean isStraightFlush(core.Card[] cards) {
        if(isStraight(cards) && isFlush(cards)) return true;
        return false;
    }

    private boolean isFourCards(core.Card[] cards) {
        if(sameCount(cards, FOUR_CARDS) >= 1) return true;
        return false;
    }

    private boolean isFullHouse(core.Card[] cards) {
        if(isTriple(cards) && isOnePair(cards)) return true;
        return false;
    }

    private boolean isFlush(core.Card[] cards) {
        for(int i = 0; i < (cards.length - 1); i++) {
            int count = 1;

            for(int j = (i + 1); j < cards.length; j++) {
                if (cards[i].shape == cards[j].shape) count++;
            }

            if(count >= 5) return true;
        }

        return false;
    }

    private boolean isStraight(core.Card[] cards) {
        int count = 1;

        sort(cards, true);

        for(int i = 0; i < (cards.length - 1); i++) {
            if(cards[i].number == cards[i + 1].number) continue;
            else if((cards[i].number + 1) == cards[i + 1].number) count++;
            else count = 1;
        }

        return count >= 5;
    }

    private boolean isTriple(core.Card[] cards) {
        if(sameCount(cards, TRIPLE) >= 1) return true;
        return false;
    }

    private boolean isTwoPair(core.Card[] cards) {
        if(sameCount(cards, PAIR) >= 2) return true;
        return false;
    }

    private boolean isOnePair(core.Card[] cards) {
        if(sameCount(cards, PAIR) >= 1) return true;
        return false;
    }

    private int sameCount(core.Card[] cards, int type) {
        int pairCount = 0;
        int tripleCount = 0;
        int fourCardsCount = 0;

        clearChecked();

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

            if(count == 2) pairCount++;
            else if(count == 3) tripleCount++;
            else if(count == 4) fourCardsCount++;
        }

        if(type == PAIR) return pairCount;
        else if(type == TRIPLE) return tripleCount;
        else if(type == FOUR_CARDS) return fourCardsCount;
        return 0;
    }

    private void sort(core.Card[] cards, boolean ascending) {
        core.Card temp;

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

    private void clearChecked() {
        for(int i = 0; i < cards.length; i++) {
            cards[i].checked = false;
        }
    }

    private void test() {
        for(int i = 0; i < cards.length; i++) {
            System.out.print(cards[i].number + " ");
            System.out.print(cards[i].shape + " ");
            System.out.println();
        }
    }
    */
}
