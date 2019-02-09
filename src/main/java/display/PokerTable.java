package display;

import core.Card;
import core.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PokerTable extends JFrame implements ActionListener {
    public static final int CARD_WIDTH = (int)(167 * 0.75);
    public static final int CARD_HEIGHT = (int)(242 * 0.75);
    private Dimension cardDim = new Dimension(167, 242);
    private Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    private List<PokerCardSet> cardSets = new ArrayList<>();
    private Label winner;
    private Font font = new Font("Monospaced", Font.BOLD, 30);
    private Timer timer;
    private boolean timerEnabled;
    private int timerInterval;
    private int cardSetIndex;

    public PokerTable(String title) throws HeadlessException {
        super(title);

        timerEnabled = true;
        timerInterval = 500;
    }

    public void init() {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        initCardDimension();
        makeWinner();
        setVisible(true);
    }

    public void setAnimation(boolean enabled, int interval) {
        timerEnabled = enabled;
        timerInterval = interval;
    }

    public void run(Player[] players) {
        makeCardSets(players);

        if(timerEnabled) startTimer();
        else {
            while(showNextCard(false)) {}

            winner.setVisible(true);
            revalidate();
            repaint();
        }
    }

    private void initCardDimension() {
        cardDim.height = (int)(screenDim.height / 5.0);
        cardDim.width = (int)(cardDim.height * 0.69);
    }

    private void makeWinner() {
        winner = new Label("WINNER: 임준형");
        winner.setVisible(false);
        winner.setFont(font);
        winner.setForeground(Color.WHITE);
        winner.setBackground(new Color(255, 0, 0, 120));
        winner.setAlignment(Label.CENTER);
        winner.setBounds(0, screenDim.height/2 - 50, screenDim.width, 100);
        add(winner);
    }

    private void makeCardSets(Player[] players) {
        Rectangle[] bounds = getBounds(players.length);

        clear();

        for(int i=0; i<players.length; i++) {
            PokerCardSet cardSet = new PokerCardSet(players[i]);
            cardSet.setBounds(bounds[i]);
            add(cardSet);
            cardSets.add(cardSet);
        }
    }

    private Rectangle[] getBounds(int count) {
        int frameWidth = screenDim.width;
        int frameHeight = screenDim.height - 30;
        int width = (cardDim.width + 5) * 5 + 10;
        int height = cardDim.height + 10;
        int x = frameWidth /2 - width/2;
        int y = frameHeight /2 - height/2;

        Rectangle[] preset = {
                new Rectangle(x, 10, width, height),
                new Rectangle(x, frameHeight - height - 55, width, height),
                new Rectangle(20, y, width, height),
                new Rectangle(frameWidth - width - 20, y, width, height)
        };

        switch (count) {
            case 3:
                return new Rectangle[] { preset[0], preset[3], preset[1] };

            case 4:
                return new Rectangle[] { preset[0], preset[3], preset[1], preset[2] };

            default:
                return new Rectangle[] { preset[0], preset[1] };
        }
    }

    private void clear() {
        for(PokerCardSet cardSet : cardSets) {
            remove(cardSet);
        }

        cardSets.clear();
    }

    private boolean showNextCard(boolean update) {
        PokerCardSet cardSet = cardSets.get(cardSetIndex++);
        Card card = cardSet.nextCard();

        if(card == null) return cardSetIndex>=cardSets.size() ? false : true;
        cardSetIndex %= cardSets.size();

        cardSet.add(new PokerCard(card));

        if(update) {
            cardSet.revalidate();
            cardSet.repaint();
        }

        return true;
    }

    private void startTimer() {
        cardSetIndex = 0;
        stopTimer();
        timer = new Timer(timerInterval, this);
        timer.start();

    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    public void actionPerformed(ActionEvent event) {
        if(!showNextCard(true)) {
            stopTimer();

            winner.setVisible(true);
            revalidate();
            repaint();
        }
    }
}