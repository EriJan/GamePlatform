import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class CardDeal {
  protected List<PlayingCard> hand;

  CardDeal() {
    hand = new ArrayList<PlayingCard>();
  }

  public void drawFromDeck(DeckHandler deck, int noOfCards) {
    for (int i = 0; i < noOfCards; i++) {
      hand.add(deck.drawTop());
    }
  }

  //public List<PlayingCard> getDeal(){
   // return hand;
 // }

  public void recieveCard(PlayingCard newCard) {
    hand.add(newCard);
  }

  public PlayingCard discardCard(int cardNo) {
    return hand.remove(cardNo);
  }

  public PlayingCard getCard(int index) {
    PlayingCard retCard;
    if (index < hand.size()) {
      retCard = hand.get(index);
    } else {
      retCard = null;
    }
    return retCard;
  }

  public PlayingCard getLastCard(){
    return hand.get(hand.size()-1);
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

  public boolean isHandEmpty(){
    boolean local = false;
    if (hand.size()== 0){
      local = true;
    }
    return local;
  }

  public void sortHand() {
    Collections.sort(hand);
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
