package core;

public class Player{
    private String name;
    private Card[] cards;
    private Result result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String  getCardsText() {
        String result = "";

        for(int i = 0; i < cards.length; i++) {
            result += cards[i] + " ";
        }

        return result;
    }
}
