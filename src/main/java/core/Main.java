package core;

import display.PokerTable;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        ResultChecker resultChecker = new ResultChecker();
        Player[] players = new Player[2];

        players[0] = new Player("Junny");
        players[1] = new Player("Magician");
        /*players[2] = new Player("Circus Man");
        players[3] = new Player("LA Chan");*/

        deck.init();
        for(int i = 0; i < players.length; i++) {
            players[i].setCards(deck.drawCards(5));
        }

        Card[] cards1 = players[0].getCards();
        Card[] cards2 = players[1].getCards();
        /*Card[] cards3 = players[2].getCards();
        Card[] cards4 = players[3].getCards();*/

        cards1[0].setNumber(2);
        cards1[1].setNumber(3);
        cards1[2].setNumber(3);
        cards1[3].setNumber(5);
        cards1[4].setNumber(5);

        cards1[0].setShape(2);
        cards1[1].setShape(1);
        cards1[2].setShape(1);
        cards1[3].setShape(1);
        cards1[4].setShape(1);

        cards2[0].setNumber(2);
        cards2[1].setNumber(3);
        cards2[2].setNumber(3);
        cards2[3].setNumber(5);
        cards2[4].setNumber(5);

        cards2[0].setShape(1);
        cards2[1].setShape(2);
        cards2[2].setShape(2);
        cards2[3].setShape(2);
        cards2[4].setShape(2);

        for(int i = 0; i < players.length; i++) {
            resultChecker.makeResult(players[i]);
            System.out.println(players[i].getName() + ": " + players[i].getCardsText() + players[i].getResult().text());
            System.out.println();
        }

        System.out.println("승자: " + resultChecker.determineWinner(players).getName());

        PokerTable table = new PokerTable("해치 포커", deck, resultChecker, players);
        table.init();
        table.setAnimation(false, 100);
        table.run();

    }
}