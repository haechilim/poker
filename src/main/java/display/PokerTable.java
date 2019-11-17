package display;

import core.Card;
import core.Deck;
import core.Player;
import core.ResultChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PokerTable extends JFrame implements ActionListener {
    private Deck deck;
    private ResultChecker resultChecker;
    private Player[] players;
    private Dimension frameDim;
    private Dimension cardDim;
    private List<PokerPlayer> playerPanels = new ArrayList<>();
    private Label winner;
    private Button nextGame;
    private Button exit;
    private Font font = new Font("Monospaced", Font.BOLD, 30);
    private Timer timer;
    private boolean timerEnabled = true;
    private int timerInterval = 500;
    private int playerPanelIndex;

    public PokerTable(String title, Deck deck, ResultChecker resultChecker, Player[] players) throws HeadlessException {
        super(title);

        this.deck = deck;
        this.resultChecker = resultChecker;
        this.players = players;
    }

    public void init() {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setBounds(0, 0, 1980, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        initDimensions();
        makeComponents();
        setVisible(true);
    }

    public void setAnimation(boolean enabled, int interval) {
        timerEnabled = enabled;
        timerInterval = interval;
    }

    public void run() {
        makePlayerPanels();

        if(timerEnabled) startTimer();
        else {
            while(showNextCard(false)) {}

            showResult();
        }
    }

    private void initDimensions() {
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        frameDim = new Dimension(screenDim.width, screenDim.height - 30);
        cardDim = new Dimension();
        cardDim.height = (int)(frameDim.height / 5.0);
        cardDim.width = (int)(cardDim.height * 0.69);
    }

    private void makeComponents() {
        nextGame = new Button("One More Game!");
        nextGame.setBounds(10, 10, 130, 60);
        nextGame.addActionListener(this);
        nextGame.setVisible(false);
        add(nextGame);

        exit = new Button("Exit");
        exit.setBounds(frameDim.width - 100, 10, 90, 60);
        exit.addActionListener(this);
        add(exit);

        winner = new Label();
        winner.setVisible(false);
        winner.setFont(font);
        winner.setForeground(Color.WHITE);
        //winner.setBackground(new Color(255, 0, 0, 120));
        winner.setBackground(new Color(0, 0, 255, 120));
        winner.setAlignment(Label.CENTER);
        winner.setBounds(0, frameDim.height/2 - 50, frameDim.width, 100);
        add(winner);
    }

    private void makePlayerPanels() {
        Rectangle[] bounds = getBounds(players.length);

        clear();

        for(int i=0; i<players.length; i++) {
            PokerPlayer panel = new PokerPlayer(this, players[i]);
            panel.setBounds(bounds[i]);
            panel.init();
            add(panel);
            playerPanels.add(panel);
        }
    }

    private Rectangle[] getBounds(int count) {
        int frameWidth = frameDim.width;
        int frameHeight = frameDim.height;
        int width = (cardDim.width + 5) * 7 + 10;
        int height = cardDim.height + 15 + 10;
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
        playerPanelIndex = 0;

        for(PokerPlayer panel : playerPanels) {
            remove(panel);
        }

        playerPanels.clear();

        nextGame.setVisible(false);
        winner.setVisible(false);
    }

    private boolean showNextCard(boolean update) {
        PokerPlayer panel = playerPanels.get(playerPanelIndex++);
        Card card = panel.nextCard();

        if(card == null) return playerPanelIndex >= playerPanels.size() ? false : true;
        playerPanelIndex %= playerPanels.size();

        panel.addCard(card);

        if(update) {
            panel.revalidate();
            panel.repaint();
        }

        return true;
    }

    private void showResult() {
        if(players.length >= 2) {
            Player first = resultChecker.determineWinner(players);
            winner.setText("WINNER: " + first.getName());
        }

        for(PokerPlayer panel : playerPanels) {
            panel.showResult();
        }

        nextGame.setVisible(true);
        winner.setVisible(true);
        revalidate();
        repaint();
    }

    private void startTimer() {
        playerPanelIndex = 0;
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
        if(event.getSource() == exit) dispose();
        else if(event.getSource() == nextGame) {
            deck.init();

            for(Player player : players) {
                player.setCards(deck.drawCards(7));
                resultChecker.makeResult(player);
            }

            run();
        }
        else {
            if (!showNextCard(true)) {
                stopTimer();
                showResult();
            }
        }
    }

    public Dimension getCardDim() {
        return cardDim;
    }
}