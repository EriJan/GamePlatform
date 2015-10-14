// This class describes a standard playing card.
// Uses the enum Suit for the suit of each card
// implements the Comparable interface and overrides
// equals, sorted and compareTo for sorting purposes

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
    }

    PlayingCard(Suit suit, String val) {
        this.suit = suit;
        this.value = Integer.parseInt(val);
        faceUp = false;
    }

    PlayingCard(Suit suit, char val) {
        this.suit = suit;
        if (val == 'J') {
            this.value = 11;
        } else if (val == 'Q') {
            this.value = 12;
        } else if (val == 'K') {
            this.value = 13;
        } else if (val == 'A') {
            this.value = 1;
        } else if (Character.isDigit(val)) {
            this.value = Character.getNumericValue(val);
        } else {
            this.value = 0;
            System.out.println(val + " does not seem to be a valid chatacter or vaue.");
        }
        faceUp = false;
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
        this.icon = new ImageIcon("C:/Users/Ulla/Documents/GitHub/testCardGame/" + localSuit + i + ".png", localSuit+i);
    }

    boolean isEqSuit(PlayingCard card1, PlayingCard card2) {
        boolean isSame = false;
        if (card1.getSuit() == card2.getSuit()) {
            isSame = true;
        }
        return isSame;
    }

    boolean isRed(PlayingCard card) {
        boolean isRed = false;
        if (card.getSuit() == Suit.Clubs || card.getSuit() == Suit.Hearts) {
            isRed = true;
        }
        return isRed;
    }

    /*boolean isRed = false;
    if (card.getSuit() == Suit.D || card.getSuit() == Suit.Hearts) {
      isRed = true;
    }
    return isRed;
  }*/

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


    boolean isBlack(PlayingCard card) {
        boolean isBlack = false;
        if (card.getSuit() == Suit.Diamonds || card.getSuit() == Suit.Spades) {
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
    // Fixme: correct sort, value first
    // Todo: add sort rules w. setter and getter
    @Override
    public int compareTo(PlayingCard card) {
        int compRes;
        if (this.equals(card)) {
            compRes = 0;
        } else if (this.suit == card.getSuit()) {
            compRes = this.value - card.getValue();
        } else {
            compRes = this.suit.value - card.getSuit().value;
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
