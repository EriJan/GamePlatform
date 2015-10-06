import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class CardDeal {
  protected List<PlayingCard> hand;

  CardDeal() {

    hand = new ArrayList<PlayingCard>();
  }

  public void drawFromDeck(DeckHandler deck, int noOfCards) {
    for (int i = 0; i < noOfCards; i++) {
      hand.add(deck.drawTop());
    }
  }

    public List<PlayingCard> getDeal(){
        return hand;
    }

  public void recieveCard(PlayingCard newCard) {
    hand.add(newCard);
  }

  public PlayingCard discardCard(int cardNo) {
    return hand.remove(cardNo);
  }

  public void revealHand() {
    for (PlayingCard card : hand) {
      card.revealCard();
    }
  }

  public void hideHand() {
    for (PlayingCard card : hand) {
      card.hideCard();
    }
  }

  public void sortHand() {
    Collections.sort(hand);
  }

  List<PlayingCard> getHand() {
    return hand;
  }

  public int getHandSize() {
    return hand.size();
  }

  int getHandValue() {
    int handValue = 0;
    for (PlayingCard card : hand) {
      handValue += card.getValue();
    }
    return handValue;
  }

  @Override
  public String toString() {
    String handStr = "";
    for (PlayingCard card : hand) {
      handStr = handStr + card.toString() + " ";
    }
    return handStr;
  }
}
