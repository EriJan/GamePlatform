import java.util.*;

/**
 * Created by Ulla on 2015-10-08.
 */
public class PatiensDeckHandler  {
    static private Set<PatiensPlayingCard> fullDeck;
    private List<PatiensPlayingCard> currentDeck;
    private List<PatiensPlayingCard> dealtCards;
    private List<PatiensPlayingCard> discardedCards;


    PatiensDeckHandler() {
        fullDeck = new HashSet<PatiensPlayingCard>();
        for (Suit suit : Suit.values()) {
            for (int i = 1; i <=13; i++) {
                fullDeck.add(new PatiensPlayingCard(suit,i));
            }
        }
        // currentDeck = new ArrayList<PatiensPlayingCard>(fullDeck);
    }

    public PatiensPlayingCard drawTop() {
        return currentDeck.remove(0);
    }

    public PatiensPlayingCard drawThisCard(PatiensPlayingCard card) {
        currentDeck.remove(card);
        return card;
    }

    public List<PatiensPlayingCard> getCurrentDeck(){
        return currentDeck;
    }

    public int cardsLeft() {
        return currentDeck.size();
    }

    public void newDeck() {
        currentDeck = new ArrayList<PatiensPlayingCard>(fullDeck);
    }

    public void newDeck(int noOfDecks) {
        currentDeck = new ArrayList<PatiensPlayingCard>();
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
        for (PatiensPlayingCard card : currentDeck) {
            cardListStr = cardListStr + card.toString() + "\n";
        }
        return cardListStr;
    }
}

