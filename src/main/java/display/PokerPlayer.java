package display;

import core.Card;
import core.HaechiArray;
import core.Player;
import core.Result;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PokerPlayer extends Panel {
    private PokerTable table;
    private Player player;
    private Card[] cards;
    private int index;

    private Panel cardsPanel;
    private Label name;
    private Label result;
    private static final Font nameFont = new Font("Monospaced", Font.BOLD, 25);
    private static final Font resultFont = new Font("Monospaced", Font.ITALIC, 20);

    public PokerPlayer(PokerTable table, Player player) {
        this.table = table;
        this.player = player;
        this.cards = player.getCards();
    }

    public void init() {
        setLayout(null);

        cardsPanel = new Panel();
        cardsPanel.setBounds(0, 0, getWidth(), getHeight() - nameFont.getSize() + 10);
        add(cardsPanel);

        name = new Label();
        name.setText(player.getName());
        name.setFont(nameFont);
        name.setAlignment(Label.CENTER);
        name.setBounds(getWidth()/2 - 100, getHeight() - nameFont.getSize() + 10, 200, nameFont.getSize());
        add(name);

        result = new Label();
        result.setText(player.getResult().text());
        result.setFont(resultFont);
        result.setAlignment(Label.RIGHT);
        result.setForeground(Color.GRAY);
        result.setBounds(getWidth() - 310, getHeight() - nameFont.getSize() + 10, 300, nameFont.getSize());
        result.setVisible(false);
        add(result);
    }

    public void addCard(Card card) {
        cardsPanel.add(new PokerCard(table, card));
    }

    public Card nextCard() {
        return index<cards.length ? cards[index++] : null;
    }

    public void showResult() {
        Result playingResult = player.getResult();
        HaechiArray tops = playingResult.getTops();

        switch (playingResult.getWhat()) {
            case Result.FULL_HOUSE:
            case Result.FLUSH:
            case Result.STRAIGHT:
            case Result.STRAIGHT_FLUSH:
                for(Card card : cards) {
                    if(!card.isChecked()) continue;
                    highlightCards(card.getNumber());
                }
                break;

            case Result.TRIPLE:
            case Result.TOP:
                highlightCards(playingResult.getTop());
                break;

            default:
                for(int i=0; i<tops.size(); i++) {
                    highlightCards(tops.get(i));
                }
                break;
        }

        result.setVisible(true);
    }

    private void highlightCards(int number) {
        for(PokerCard card : findChards(number)) {
            card.highlight();
        }
    }

    private List<PokerCard> findChards(int number) {
        List<PokerCard> result = new ArrayList<>();

        for(Component component : cardsPanel.getComponents()) {
            PokerCard card = (PokerCard)component;
            if(card.getNumber() == number) result.add(card);
        }

        return result;
    }
}