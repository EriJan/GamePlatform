import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends CardGame {
  DeckHandler deck;

  ArrayList<BlackJackHand> positions;
  ArrayList<Player> players;
  BlackJackHand house;

  BlackJack() {
    deck = new DeckHandler();
    deck.newDeck(8);
    positions = new ArrayList<BlackJackHand>();
    players = new ArrayList<Player>();
  }

  public void newGame() {
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

    System.out.println("Who are the players at the table?");
    int playerCounter = 1;
    boolean morePlayers = true;
    while (morePlayers) {
      System.out.println("What are the name of player " + playerCounter + " ?");
      usrInputStr = userInput.nextLine();

      if ( usrInputStr.isEmpty()) {
        if (playerCounter > 1) {
          morePlayers = false;
        } else {
          System.out.println("Please add at least one player.");
        }
      } else {
        players.add(new Player(usrInputStr));
        playerCounter++;
      }
    }
    System.out.println("Available players.");
    playerCounter = 1;
    for (Player pl : players) {
      System.out.println(playerCounter + " : " + pl);
      playerCounter++;
    }
    System.out.println();
    for (int i = 1; i <= noPos; i++) {
      System.out.println("Who owns position " + i + " ?");
      int posOwnerId = 0;
      boolean isDigit = false;
      while(!isDigit) {
        usrInputStr = userInput.nextLine();

        if (Character.isDigit(usrInputStr.charAt(0))) {
          posOwnerId = Integer.parseInt(usrInputStr.substring(0, 1)) - 1;
          isDigit = true;
        } else {
          System.out.println("Not a valid number");
        }
      }
      positions.add(new BlackJackHand(players.get(posOwnerId)));
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

      endScore();

      printScore();

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
        pos.getOwner().setWin(2);
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
        if (hand.isBust()) {
          System.out.println("Sorry, you are bust!");
          moreCards = false;
          hand.setIsDone();
          hand.getOwner().setLoss(1);
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

  public void endScore() {
    if (house.isBust()) {
      for (BlackJackHand pos : positions) {
        if (!pos.isBust() && !pos.isBlackJack()) {
          pos.getOwner().setWin(1);
        }
      }
    } else {
      for (BlackJackHand pos : positions) {
        if (!pos.isBust() && !pos.isBlackJack()) {
          if (pos.getHandValue() > house.getHandValue()) {
            pos.getOwner().setWin(1);
          } else {
            pos.getOwner().setLoss(1);
          }
        }
      }
    }
  }

  public void printScore() {
    for (Player pl : players) {
      System.out.println(pl.toString() + " score:");
      System.out.println("Wins: " + pl.getWin());
      System.out.println("Losses: " + pl.getLoss());
    }
  }

  @Override
  public String toString() {
    String tmpStr = "";
    int posCnt = 1;
    for (BlackJackHand pos : positions) {
      tmpStr += "Position " + posCnt + " (" + pos.getOwner() + "): ";
      tmpStr += pos.toString();
      posCnt++;
    }
    tmpStr += "House : ";
    tmpStr += house.toString();
    return tmpStr;
  }
}
