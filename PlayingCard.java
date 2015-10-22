// This class describes a standard playing card.
// Uses the enum Suit for the suit of each card
// implements the Comparable interface and overrides
// equals, sorted and compareTo for sorting purposes
//
// Any card value outside 1-13 will be considered as empty.

// TODO make PlayingCard immutable.

import javax.swing.*;

public class PlayingCard implements Comparable<PlayingCard> {

  protected final Suit suit;
  protected final int value;
  protected boolean faceUp;
  protected ImageIcon icon;

  PlayingCard(Suit suit, int val) {
    this.suit = suit;
    this.value = val;
    faceUp = false;
    setIconAndDescription(suit, val);
  }

  boolean isEqSuit(PlayingCard card1, PlayingCard card2) {
    boolean isSame = false;
    if (card1.getSuit() == card2.getSuit()) {
      isSame = true;
    }
    return isSame;
  }

  void setIconAndDescription(Suit suit, int i) {
    String stringSuit;
    if (suit == Suit.Clubs) {
      stringSuit = "c";
    } else if (suit == Suit.Diamonds) {
      stringSuit = "d";
    } else if (suit == Suit.Hearts) {
      stringSuit = "h";
    } else {
      stringSuit = "s";
    }

    this.icon = new ImageIcon(this.getClass().getResource("cardicons/" + stringSuit + i + ".png"), stringSuit + i);
  }

  boolean isRed() {
    boolean isRed = false;
    if (this.getSuit() == Suit.Diamonds || this.getSuit() == Suit.Hearts) {
      isRed = true;
    }
    return isRed;
  }

  boolean isBlack() {
    boolean isBlack = false;
    if (this.getSuit()== Suit.Clubs || this.getSuit()==Suit.Spades){
      isBlack = true;
    }
    return isBlack;
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


    } else if (!faceUp && value != 0){
      valString = "––";
    } else {
      valString = "  ";
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

  public ImageIcon getImage() { return icon;}
}
