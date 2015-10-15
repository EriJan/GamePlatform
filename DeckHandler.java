import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class DeckHandler {
  static private Set<PlayingCard> fullDeck;
  private List<PlayingCard> currentDeck;
  private List<PlayingCard> dealtCards;
  private List<PlayingCard> discardedCards;

  DeckHandler() {
    fullDeck = new HashSet<PlayingCard>();
    for (Suit suit : Suit.values()) {
      for (int i = 1; i <=13; i++) {
        fullDeck.add(new PlayingCard(suit,i));
      }
    }
  }

  public PlayingCard drawTop() {
    return currentDeck.remove(0);
  }

  public PlayingCard drawThisCard(PlayingCard card) {
    currentDeck.remove(card);
    return card;
  }

  public PatiensPlayingCard getCurrentDeck(){
    return currentDeck;
  }

  public int cardsLeft() {
    return currentDeck.size();
  }

  public void newDeck() {
    currentDeck = new ArrayList<PlayingCard>(fullDeck);
  }

  public void newDeck(int noOfDecks) {
    currentDeck = new ArrayList<PlayingCard>();
    for (int i = 0; i < noOfDecks; i++) {
      currentDeck.addAll(fullDeck);
    }
  }

  public void shuffleDeck() {
    Collections.shuffle(currentDeck);
  }

  public void sortDeck() {
    Collections.sort(currentDeck);
  }

  @Override
  public String toString() {
    String cardListStr = "";
    for (PlayingCard card : currentDeck) {
      cardListStr = cardListStr + card.toString() + "\n";
    }
    return cardListStr;
  }
}
