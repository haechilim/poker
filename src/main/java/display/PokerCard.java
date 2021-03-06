package display;

import core.Card;

import java.awt.*;

public class PokerCard extends Panel {
    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final String classpath = PokerCard.class.getResource("/").getPath();
    private static final String[] shapes = { "spade", "diamond", "heart", "club" };
    private static final String[] numbers = { "", "", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace" };

    private Card card;
    private Image image;

    public PokerCard(PokerTable table, Card card) {
        this.card = card;

        setLayout(null);
        setSize(table.getCardDim().width, table.getCardDim().height);
        image = getCard(toShapeString(card), toNumberString(card));
    }

    public void highlight() {
        setBackground(Color.BLUE);
        revalidate();
        repaint();
    }

    public int getNumber() {
        return card.getNumber();
    }

    private Image getCard(String shape, String number) {
        String path = String.format("%s%s_of_%ss.png", classpath, number, shape);
        return toolkit.getImage(path);
    }

    private String toShapeString(Card card) {
        int shape = card.getShape();
        return shape>=0 && shape<shapes.length ? shapes[shape] : "";
    }

    private String toNumberString(Card card) {
        int number = card.getNumber();
        return number>=0 && number<numbers.length ? numbers[number] : "";
    }

    @Override
    public void paint(Graphics graphics) {
        //graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        graphics.drawImage(image, 2, 2, getWidth() - 4, getHeight() - 4, this);
    }
}