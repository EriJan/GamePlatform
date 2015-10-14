// This class describes a standard playing card.
// Uses the enum Suit for the suit of each card
// implements the Comparable interface and overrides
// equals, sorted and compareTo for sorting purposes
//
// Any card value outside 1-13 will be considered as empty.

// TODO make PlayingCard immutable.

public class PlayingCard implements Comparable<PlayingCard> {

  protected final Suit suit;
  protected final int value;
  protected boolean faceUp;

  PlayingCard(Suit suit, int val) {
    this.suit = suit;
    this.value = val;
    faceUp = false;
  }

  boolean isEqSuit(PlayingCard card1, PlayingCard card2) {
    boolean isSame = false;
    if (card1.getSuit() == card2.getSuit()) {
      isSame = true;
    }
    return isSame;
  }

  static boolean isRed(PlayingCard card){
    boolean isRed = false;
      if (card.getSuit() == Suit.Clubs || card.getSuit() == Suit.Hearts) {
        isRed = true;
      }
    return isRed;
  }

  boolean isRed() {
    boolean isRed = false;
    if (this.getSuit() == Suit.Diamonds || this.getSuit() == Suit.Hearts) {
      isRed = true;
    }
    return isRed;
  }

  boolean isBlack() {
    return !this.isRed();
  }

  static boolean isBlack(PlayingCard card) {
    return !isRed(card);
  }
  
  // Fixme: add override for hashVal
  @Override
  public boolean equals(Object aCard) {
    PlayingCard card = (PlayingCard) aCard;
    boolean isEq = false;
    if (this == card) {
      isEq = true;
    } else if (card.getValue() == this.value && card.getSuit() == this.suit) {
      isEq = true;
    }
    return isEq;
  }

  // To be able to call Collections.sort(collection)
  // on a collection of this object
  // This method is necessary
  @Override
  public int compareTo(PlayingCard card) {
    int compRes;
    if (this.equals(card)) {
      compRes = 0;
    } else {
      compRes = this.value - card.getValue();
    }
    return compRes;
  }

  @Override
  public String toString() {
    String valString;

    if (faceUp) {
      switch (value) {
        case 1:
          valString = "A";
          break;
        case 11:
          valString = "J";
          break;
        case 12:
          valString = "Q";
          break;
        case 13:
          valString = "K";
          break;
        default:
          valString = Integer.toString(value);
          break;
        }
      valString = suit + valString;
    } else if ((value < 1 || value > 13) && faceUp) {
      valString = "  ";
    } else {
      valString = "––";
    }
    return valString;
  }

  public void revealCard() {
    faceUp = true;
  }

  public void hideCard() {
    faceUp = false;
  }

  public boolean isFaceUp() {
    return faceUp;
  }

  public int getValue() {
    return value;
  }

  public Suit getSuit() {
    return suit;
  }
}
