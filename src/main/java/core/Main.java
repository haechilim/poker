package core;

import display.PokerTable;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        ResultChecker resultChecker = new ResultChecker();
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();

        p1.setName("임준형");
        p2.setName("권재관");
        p3.setName("구팔이");
        p4.setName("박짠호");

        deck.init();
        p1.setCards(deck.drawCards(5));
        p2.setCards(deck.drawCards(5));
        p3.setCards(deck.drawCards(5));
        p4.setCards(deck.drawCards(5));

        /*Card[] cards1 = p1.getCards();
        Card[] cards2 = p2.getCards();
        
        cards1[0].setNumber(4);
        cards1[1].setNumber(5);
        cards1[2].setNumber(6);
        cards1[3].setNumber(7);
        cards1[4].setNumber(8);

        cards1[0].setShape(2);
        cards1[1].setShape(1);
        cards1[2].setShape(2);
        cards1[3].setShape(2);
        cards1[4].setShape(2);

        cards2[0].setNumber(9);
        cards2[1].setNumber(9);
        cards2[2].setNumber(5);
        cards2[3].setNumber(5);
        cards2[4].setNumber(2);

        cards2[0].setShape(0);
        cards2[1].setShape(0);
        cards2[2].setShape(0);
        cards2[3].setShape(0);
        cards2[4].setShape(1);*/


        resultChecker.makeResult(p1);
        resultChecker.makeResult(p2);
        resultChecker.makeResult(p3);
        resultChecker.makeResult(p4);

        PokerTable table = new PokerTable("해치 포커", deck, resultChecker, new Player[] { p1, p2, p3 });
        table.init();
        table.setAnimation(false, 100);
        table.run();

        /*System.out.println(p1.getName() +  ": " + p1.getCardsText() + p1.getResult().text());
        System.out.println();
        System.out.println(p2.getName() +  ": " + p2.getCardsText() + p2.getResult().text());
        System.out.println();
        System.out.println("승자: " + resultChecker.determineWinner(p1 ,p2).getName());*/
    }
}