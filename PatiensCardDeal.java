import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ulla on 2015-10-08.
 */
public class PatiensCardDeal extends CardDeal {


    public class CardDeal {
        protected List<PatiensPlayingCard> hand;

        CardDeal() {
            hand = new ArrayList<PatiensPlayingCard>();
        }

        public void drawFromDeck(DeckHandler deck, int noOfCards) {
            for (int i = 0; i < noOfCards; i++) {
                hand.add((PatiensPlayingCard) deck.drawTop());
            }
        }

        public List<PatiensPlayingCard> getDeal(){
            return hand;
        }

        public void recieveCard(PatiensPlayingCard newCard) {
            hand.add(newCard);
        }

        public PatiensPlayingCard discardCard(int cardNo) {
            return hand.remove(cardNo);
        }

        public PatiensPlayingCard getCard(int index) {
            PatiensPlayingCard retCard;
            if (index < hand.size()) {
                retCard = hand.get(index);
            } else {
                retCard = null;
            }
            return retCard;
        }

        public PatiensPlayingCard getLastCard(){
            return hand.get(hand.size()-1);
        }

        public void revealHand() {
            for (PatiensPlayingCard card : hand) {
                card.revealCard();
            }
        }

        public void hideHand() {
            for (PatiensPlayingCard card : hand) {
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
            for (PatiensPlayingCard card : hand) {
                handValue += card.getValue();
            }
            return handValue;
        }

        @Override
        public String toString() {
            String handStr = "";
            for (PatiensPlayingCard card : hand) {
                handStr = handStr + card.toString() + " ";
            }
            return handStr;
        }
    }

}
