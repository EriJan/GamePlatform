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
    boolean endGame = false;
    Scanner userInput = new Scanner(System.in);

    while (!endGame) {
      newGame();
      initialDeal();
      checkBlackJackAll();
      if (!house.isDone()) {
        for (BlackJackHand pos : positions) {
          if (!pos.isDone()) {
            resolvePosition(pos);
          }
        }
        resolveHouse();
      }

      System.out.print("Hit return to play again.");
      String inputStr = userInput.nextLine();
      if (!inputStr.isEmpty()) {
        endGame = true;
        System.out.println("Program end.");
      }
    }
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
    System.out.println(toString());
  }

  public void checkBlackJackAll() {
    int counter = 1;
    for (BlackJackHand pos : positions) {
      if(pos.isBlackJack()) {
        System.out.println("Blackjack på position " + counter);
        pos.setIsDone();
      }
      counter++;
    }
    house.revealHand();
    if(house.isBlackJack()) {
      System.out.println("House gets Blackjack " + counter);
      house.setIsDone();
    }
    System.out.println(toString());
  }

  public boolean isBust(BlackJackHand hand) {
    boolean isBust = false;
    if (hand.getHandValue() > 21) {
      isBust = true;
    }
    return isBust;
  }

  public void resolvePosition(BlackJackHand hand) {
    int posNo = positions.indexOf(hand);
    posNo++;
    Scanner userInput = new Scanner(System.in);
    boolean moreCards = true;

    while (moreCards) {
      System.out.println("Position " + posNo + " do you want another card (y)?");
      System.out.println(hand.toString());
      String inputStr = userInput.nextLine();
      if (inputStr.equals("y")) {
        PlayingCard card = deck.drawTop();
        card.revealCard();
        hand.recieveCard(card);
        if (isBust(hand)) {
          System.out.println("Sorry, you are bust!");
          moreCards = false;
          hand.setIsDone();
        }
      } else {
        hand.setIsDone();
        moreCards = false;
      }
    }
    System.out.println(hand.toString());
  }

  public void resolveHouse() {
    if (!house.isDone() ) {
      while (house.getHandValue() < 17) {
        PlayingCard card = deck.drawTop();
        card.revealCard();
        house.recieveCard(card);
      }
    }
    System.out.println(house.toString());
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

}
