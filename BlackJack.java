import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends CardGame {
  DeckHandler deck;
  // BlackJackPlayer[] players;
  // BlackJackPlayer house;
  ArrayList<BlackJackHand> positions;
  BlackJackHand house;


  // Create deck from 1-8
  // Deal 2 cards to each position
  // Deal one card to house
  // Resolve each position
  // deal to house, deal on 16, stand on 17.
  //

  BlackJack () {
    deck = new DeckHandler();
    deck.newDeck(8);
    positions = new ArrayList<BlackJackHand>();
  }

  void newGame() {
    Scanner userInput = new Scanner(System.in);
    System.out.println("How many postions at the table?");
    String usrInputStr = userInput.nextLine();
    int noPos;
    if (Character.isDigit(usrInputStr.charAt(0))) {
      noPos = Integer.parseInt(usrInputStr.substring(0,1));
    } else {
      noPos = 8;
    }

    System.out.println("There will be " + noPos + " at the table." );
    for (int i = 0; i < noPos; i++) {
      positions.add(new BlackJackHand());
    }
    house = new BlackJackHand();
    deck.shuffleDeck();
  }

  @Override
  public void runGame() {

    newGame();
    initialDeal();
    System.out.println(toString());
    house.revealHand();
    checkBlackJackAll();
    System.out.println(toString());
  }

  public void initialDeal() {
    for (int i = 0; i < 2; i++) {
      for (BlackJackHand pos : positions) {
        PlayingCard card = deck.drawTop();
        card.revealCard();
        pos.recieveCard(card);
      }
      PlayingCard card = deck.drawTop();
      // card.revealCard();
      house.recieveCard(card);
    }
  }

  public void checkBlackJackAll() {
    int counter = 1;
    for (BlackJackHand pos : positions) {
      if(pos.isBlackJack()) {
        System.out.println("Blackjack på position " + counter);
      }
      counter++;
    }
    if(house.isBlackJack()) {
      System.out.println("House gets Blackjack " + counter);
    }

  }

  public void checkBust() {

  }

  public void resolvePosition() {

  }

  @Override
  public String toString() {
    String tmpStr = "";
    int posCnt = 1;
    for (BlackJackHand pos : positions) {
      tmpStr += "Position " + posCnt + " : ";
      tmpStr += pos.toString();
      posCnt++;
    }
    tmpStr += "House : ";
    tmpStr += house.toString();
    return tmpStr;
  }

  // resolvePostition
  // resolveHouse
  // isBust
  // isBlackJack
  // handValue
  //

}
