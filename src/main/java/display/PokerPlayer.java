package display;

import core.Card;
import core.Player;

import java.awt.*;

public class PokerPlayer extends Panel {
    private PokerTable table;
    private Player player;
    private Card[] cards;
    private int index;

    private Panel cardsPanel;
    private Label name;
    private Label result;
    private static final Font nameFont = new Font("Monospaced", Font.BOLD, 15);
    private static final Font resultFont = new Font("Monospaced", Font.ITALIC, 13);

    public PokerPlayer(PokerTable table, Player player) {
        this.table = table;
        this.player = player;
        this.cards = player.getCards();
    }

    public void init() {
        setLayout(null);

        cardsPanel = new Panel();
        cardsPanel.setBounds(0, 0, getWidth(), getHeight() - nameFont.getSize());
        add(cardsPanel);

        name = new Label();
        name.setText(player.getName());
        name.setFont(nameFont);
        name.setAlignment(Label.CENTER);
        name.setBounds(getWidth()/2 - 100, getHeight() - nameFont.getSize(), 200, nameFont.getSize());
        add(name);

        result = new Label();
        result.setText(player.getResult().text());
        result.setFont(resultFont);
        result.setAlignment(Label.RIGHT);
        result.setForeground(Color.GRAY);
        result.setBounds(getWidth() - 310, getHeight() - nameFont.getSize(), 300, nameFont.getSize());
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
        result.setVisible(true);
    }
}