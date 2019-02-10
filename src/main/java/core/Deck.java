package core;

public class Deck {
    private Card[] cards = new Card[52];

    public void init() {
        int index = 0;
        for(int i = 0; i < cards.length; i++) cards[i] = new Card();

        for(int shape = 0; shape < 4; shape++) {
            for(int number = 2; number <= 14; number++) {
                cards[index].setNumber(number);
                cards[index].setShape(shape);

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

                if(!cards[i].isSelected()) {
                    cards[i].setSelected(true);
                    break;
                }
            }
        }

        return cards;
    }

    private Card randomCard() {
        return cards[(int)(Math.random() * 52)];
    }
}