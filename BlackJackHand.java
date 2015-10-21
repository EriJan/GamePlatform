import java.util.Scanner;

public class BlackJackHand extends CardDeal {

  private boolean isDone;
  private Player owner;

  BlackJackHand() {
    super();
    isDone = false;
    owner = new Player("House");
  }

  BlackJackHand(Player owner) {
    super();
    isDone = false;
    this.owner = owner;
  }

  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public boolean isBust() {
    boolean isBust = false;
    if (getHandValue() > 21) {
      isBust = true;
      hideHand();
    }
    return isBust;
  }

  boolean isBlackJack() {
    boolean blackJack = false;
    if (getHandSize() == 2) {
      PlayingCard[] handArr = new PlayingCard[2];
      handArr[0] = hand.get(0);
      handArr[1] = hand.get(1);
      if ((handArr[0].getValue() == 1 && handArr[1].getValue() >= 10) ||
           handArr[1].getValue() == 1 && handArr[0].getValue() >= 10) {
          blackJack = true;
       }
     }
     return blackJack;
  }

  @Override
  int getHandValue() {
    int handValue = 0;
    int aceCount = 0;
    for (PlayingCard card : hand) {
      int cardValue = card.getValue();
      if (cardValue == 1) {
        handValue += 1;
        aceCount++;
         //handValue += youHaveAnAce();       }
      } else if (cardValue < 10) {
        handValue += cardValue;
      } else {
        handValue += 10;
      }
    }
    for (int i = 0; i < aceCount; i++) {
      if (handValue + 13 <= 21) {
        handValue += 13;
      }
    }
    return handValue;
  }

  public void setIsDone() {
    isDone = true;
  }

  public boolean isDone() {
    return isDone;
  }

}
