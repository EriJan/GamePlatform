import java.util.ArrayList;
import java.io.IOException;


public class BlackJack extends CardGame {
  public static final GameId GAME_ID = GameId.BlackJack;

  private DeckHandler deck;
  private ArrayList<BlackJackHand> positions;
  private ArrayList<Player> players;
  private BlackJackHand house;
  private GameUserInterface ui;

  BlackJack() {
    ui = new BlackJackGraphicUi();
    deck = new DeckHandler();
    deck.newDeck(8);
    positions = new ArrayList<BlackJackHand>();
    players = new ArrayList<Player>();
  }

  // Todo: add possibilty to select players from file
  public void newGame() {
    ui.welcomeMessage("\n***Lets play Black Jack***\n");
    int noPos = ui.userInputInt("How many postions at the table? (1-8)");
    if (noPos < 1 || noPos > 8) {
      noPos = 8;
    }
    // ui.gameMessage("There will be " + noPos + " positions at the table." );

    // String usrInputStr;
    // ui.gameMessage("Who are the players at the table? (return to end)");
    int playerCounter = 1;
    boolean morePlayers = true;
    while (morePlayers) {
      String usrInputStr = ui.userInput("What is the name of player " + playerCounter + " ?");

      if ( usrInputStr.isEmpty()) {
        if (playerCounter > 1) {
          morePlayers = false;
        } else {
          ui.gameMessage("Please add at least one player.");
        }
      } else {
        players.add(new Player(usrInputStr, GAME_ID));
        playerCounter++;
      }
    }

    String[] playerArr = new String[players.size()];

    for (int i = 0; i < players.size(); i++) {
      playerArr[i] = players.get(i).getName();
    }

    for (int i = 1; i <= noPos; i++) {
      int posOwnerId = 0;
      // ui.gameMessage("Who owns position " + i + " ?");
      posOwnerId = ui.userInputFromMenu("Who owns position " + i + " ?",
              playerArr);
      positions.add(new BlackJackHand(players.get(posOwnerId)));
    }
    house = new BlackJackHand();
    deck.shuffleDeck();
  }

  @Override
  public void runGame() {
    boolean endGame = false;

    while (!endGame) {
      newGame();
      initialDeal();
      checkBlackJackAll();
      ui.displayGameState(this);
      if (!house.isDone()) {
        for (BlackJackHand pos : positions) {
          if (!pos.isDone()) {
            resolvePosition(pos);
            ui.displayGameState(this);
          }
        }
        resolveHouse();
      }
      ui.displayGameState(this);
      endScore();

      printScore();

      if (!ui.userInputBool("Play again? (Yes/no)", "Yes")) {
        endGame = true;
        ui.gameMessage("Program end.");
      }
    }
    writePlayersToFile();
  }

  @Override
  public void setUi(GameGraphicUi ui) {

  }

  public void writePlayersToFile() {

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
        pos.getOwner().addWin(2);
      }
      counter++;
    }
    house.revealHand();
    if(house.isBlackJack()) {
      // System.out.println("House gets Blackjack " + counter);
      ui.gameMessage("House gets Blackjack " + counter);
      house.setIsDone();
    }
  }

  public void resolvePosition(BlackJackHand hand) {
    int posNo = positions.indexOf(hand);
    posNo++;
    boolean moreCards = true;

    while (moreCards) {
      if (ui.userInputBool("Position " + posNo +
              " do you want another card (Yes/No)? " + hand.toString(),"Yes")) {
        PlayingCard card = deck.drawTop();
        card.revealCard();
        hand.recieveCard(card);
        if (hand.isBust()) {
          ui.gameMessage("Sorry, you are bust!");
          moreCards = false;
          hand.setIsDone();
          hand.getOwner().addLosses(1);
        }
      } else {
        hand.setIsDone();
        moreCards = false;
      }
     }
    //ui.gameMessage("Position " + posNo + " final hand: " + hand.toString());
  }

  public void resolveHouse() {
    if (!house.isDone() ) {
      while (house.getHandValue() < 17) {
        PlayingCard card = deck.drawTop();
        card.revealCard();
        house.recieveCard(card);
      }
    }
    ui.gameMessage("House ends with " + house.toString());
  }

  public void endScore() {
    if (house.isBust()) {
      for (BlackJackHand pos : positions) {
        if (!pos.isBust() && !pos.isBlackJack()) {
          pos.getOwner().addWin(1);
        }
      }
    } else {
      for (BlackJackHand pos : positions) {
        if (!pos.isBust() && !pos.isBlackJack()) {
          if (pos.getHandValue() > house.getHandValue()) {
            pos.getOwner().addWin(1);
          } else {
            pos.getOwner().addLosses(1);
          }
        }
      }
    }
  }

  public void printScore() {
    String scoreStr = "";
    for (Player pl : players) {
      scoreStr += pl.toString() + " score:";
      scoreStr += "Wins: " + pl.getWin();
      scoreStr += " Losses: " + pl.getLosses() + "\n";

      // ui.gameMessage(pl.toString() + " score:");
      // ui.gameMessage("Wins: " + pl.getWin());
      // ui.gameMessage("Losses: " + pl.getLoss());
    }
    ui.gameMessage(scoreStr);
  }

  public BlackJackHand getHouse () {
    return house;
  }

  public ArrayList<BlackJackHand> getPositions() {
    return positions;
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
