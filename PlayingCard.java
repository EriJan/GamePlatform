// This class describes a standard playing card.
// Uses the enum Suit for the suit of each card
// implements the Comparable interface and overrides
// equals, sorted and compareTo for sorting purposes
//
// Any card value outside 1-13 will be considered as empty.

// TODO make PlayingCard immutable.

import javax.swing.*;
import java.util.ArrayList;

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
    String localSuit;
    if (suit == Suit.Clubs) {
      localSuit = "c";
    } else if (suit == Suit.Diamonds) {
      localSuit = "d";
    } else if (suit == Suit.Hearts) {
      localSuit = "h";
    } else {
      localSuit = "s";
    }
    // String url = "http://github.com/EriJan/GamePlatform/blob/EriJan-devel/" + localSuit + i + ".png";
    //if (HelperMethods.fileExists(url)) {
    String iconRoot = "/Users/Janne/JavaProj/IntelliJ/GamePlatform/cardicons";
    String iconPath =  iconRoot + localSuit + i + ".png";
    this.icon = new ImageIcon(iconPath, localSuit + i);
      //System.out.println(url + "exists!");
    //}
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

    if ((value < 1 || value > 13)) {
      valString = "  ";
    } else if (faceUp) {
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

  public ImageIcon getImage() { return icon;}
}
