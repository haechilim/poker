package core;

public class Player {
    private String name;
    public Card[] cards; //<-테스트 때문에 임시로 public
    private Result result;

    public Card[] getCards() {
        return cards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public String  getCardsText() {
        String result = "";

        for(int i = 0; i < cards.length; i++) {
            result += cards[i] + " ";
        }

        return result;
    }

    public Result getResult() {
        Deck deck = new Deck();
        result = deck.getResult(cards);

        return result;
    }

    public String checkWinner(Player player) {
        if(result.what < player.result.what) return name;
        else if(result.what > player.result.what) return player.name;
        else return checkMoreHighGenealogy(player);
    }

    private String checkMoreHighGenealogy(Player player) {
        if (result.what == result.STRAIGHT_FLUSH
                || result.what == result.STRAIGHT
                || result.what == result.FLUSH) {
            Deck.sort(cards, false);
            Deck.sort(player.cards, false);

            for (int i = 0; i < cards.length; i++) {
                if (cards[i].number != player.cards[i].number) {
                    return cards[i].number > player.cards[i].number ? name : player.name;
                }
            }
        }
        else if (result.what == result.FOUR_CARDS
                || result.what == result.FULL_HOUSE
                || result.what == result.TRIPLE) {
            return result.top > player.result.top ? name : player.name;
        }
        else {
            for (int i = 0; i < result.tops.size(); i++) {
                if (result.tops.get(i) != player.result.tops.get(i)) {
                    return result.tops.get(i) > player.result.tops.get(i) ? name : player.name;
                }
            }

            for (int i = 0; i < result.others.size(); i++) {
                if (result.others.get(i) != player.result.others.get(i)) {
                    return result.others.get(i) > player.result.others.get(i) ? name : player.name;
                }
            }
        }

        return "";
    }
}
