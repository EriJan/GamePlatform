import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


public class BlackJack extends CardGame {
  DeckHandler deck;

  private ArrayList<BlackJackHand> positions;
  private ArrayList<Player> players;
  private BlackJackHand house;
  private GameUserInterface ui;

  BlackJack() {
    //ui = new GameTextUi();
    ui = new GameGraphicUi();
    deck = new DeckHandler();
    deck.newDeck(8);
    positions = new ArrayList<BlackJackHand>();
    players = new ArrayList<Player>();
  }

  // Todo: add possibilty to select players from file
  public void newGame() {
    ui.welcomeMessage("\n***Lets play Black Jack***\n");
    int noPos = ui.userInputInt("How many postions at the table?");
    if (noPos < 1 || noPos > 8) {
      noPos = 8;
    }
    ui.gameMessage("There will be " + noPos + " at the table." );

    Scanner userInput = new Scanner(System.in);
    String usrInputStr;

    ui.gameMessage("Who are the players at the table?");
    int playerCounter = 1;
    boolean morePlayers = true;
    while (morePlayers) {
      usrInputStr = ui.userInput("What are the name of player " + playerCounter + " ?");

      if ( usrInputStr.isEmpty()) {
        if (playerCounter > 1) {
          morePlayers = false;
        } else {
          ui.gameMessage("Please add at least one player.");
        }
      } else {
        players.add(new Player(usrInputStr));
        playerCounter++;
      }
    }
    ui.gameMessage("Available players.");
    playerCounter = 1;
    for (Player pl : players) {
      ui.gameMessage(playerCounter + " : " + pl);
      playerCounter++;
    }

    for (int i = 1; i <= noPos; i++) {
      int posOwnerId = 0;
      do {
        posOwnerId = ui.userInputInt("Who owns position " + i + " ?");
        posOwnerId--;
      } while (posOwnerId < 0 || posOwnerId > noPos);
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

      String inputStr = ui.userInput("Hit return to play again.");

      if (!inputStr.isEmpty()) {
        endGame = true;
        ui.gameMessage("Program end.");
      }
    }
    printPlayersToFile();
  }

  public void printPlayersToFile() {

    FileSerializable fileTest = new FileSerializable();
    try {
      fileTest.serializeObjectToBinaryFile(players);
    } catch (IOException e) {
      ui.gameMessage("Something went wrong.");
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
      card.revealCard();
      house.recieveCard(card);
    }
  }

  public void checkBlackJackAll() {
    int counter = 1;
    for (BlackJackHand pos : positions) {
      if(pos.isBlackJack()) {
        ui.gameMessage("Blackjack på position " + counter);
        pos.setIsDone();
        pos.getOwner().setWin(2);
      }
      counter++;
    }
    house.revealHand();
    if(house.isBlackJack()) {
      // System.out.println("House gets Blackjack " + counter);
      ui.gameMessage("House gets Blackjack " + counter);
      house.setIsDone();
    }
    System.out.println(toString());
  }

  public void resolvePosition(BlackJackHand hand) {
    int posNo = positions.indexOf(hand);
    posNo++;
    boolean moreCards = true;

    while (moreCards) {
      String inputStr = ui.userInput("Position " + posNo + " do you want another card (y)?");


      if (inputStr.equals("y")) {
        PlayingCard card = deck.drawTop();
        card.revealCard();
        hand.recieveCard(card);
        if (hand.isBust()) {
          ui.gameMessage("Sorry, you are bust!");
          moreCards = false;
          hand.setIsDone();
          hand.getOwner().setLoss(1);
        }
      } else {
        hand.setIsDone();
        moreCards = false;
      }
      ui.gameMessage(hand.toString());
    }

  }

  public void resolveHouse() {
    if (!house.isDone() ) {
      while (house.getHandValue() < 17) {
        PlayingCard card = deck.drawTop();
        card.revealCard();
        house.recieveCard(card);
      }
    }
    ui.gameMessage(house.toString());
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
      ui.gameMessage(pl.toString() + " score:");
      ui.gameMessage("Wins: " + pl.getWin());
      ui.gameMessage("Losses: " + pl.getLoss());
    }
  }

  @Override
  public String toString() {
    String tmpStr = "";
    int posCnt = 1;
    for (BlackJackHand pos : positions) {
      tmpStr += "Position " + posCnt + " (" + pos.getOwner() + "): ";
      tmpStr += pos.toString();
      tmpStr += "(" + pos.getHandValue() + ")\n";
      posCnt++;
    }
    tmpStr += "House : ";
    tmpStr += house.toString();
    return tmpStr;
  }
}
