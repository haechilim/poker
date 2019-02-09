package display;

import core.Card;
import core.Player;

import java.awt.*;

public class PokerCardSet extends Panel {
    private Player player;
    private Card[] cards;
    private int index;

    private Label name;
    private Label result;
    private static final Font nameFont = new Font("Monospaced", Font.BOLD, 10);
    private static final Font resultFont = new Font("Monospaced", Font.ITALIC, 10);

    public PokerCardSet(Player player) {
        this.player = player;
        this.cards = player.getCards();

        init();
    }

    public Card nextCard() {
        return index<cards.length ? cards[index++] : null;
    }

    private void init() {
        name = new Label();
        name.setText(player.getName());
        name.setFont(nameFont);
        add(name);
    }
}