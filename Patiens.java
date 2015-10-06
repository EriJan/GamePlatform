import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Patiens extends CardGame {


    Patiens() {
        super();
    }

    Random rand = new Random();
    int r = rand.nextInt(50);

    DeckHandler myCardDeck = new DeckHandler();
    CardDeal myCardDeal;
    CardDeal[] cardDealList = new CardDeal[7];
    CardDeal[] sortedCardHand = new CardDeal[4];
    List<PlayingCard> positionDeal1 = new ArrayList<PlayingCard>();
    PatiensPlayingCard pPlayingCard;

    @Override
    public void runGame() {

        System.out.println("Hej och välkommen till Patiens!");
        setNewPatiensGameDeck();

        setDeckOnField();

        printDeal();


    }

    public void printDeal() {

        for (int i = 0; i < 7; i++) {

            System.out.print(cardDealList[0].getDeal().get(i) + "  ");
            System.out.print(cardDealList[1].getDeal().get(i)+ "  ");
            System.out.print(cardDealList[2].getDeal().get(i) + "  ");
            System.out.print(cardDealList[3].getDeal().get(i) +"  ");
            System.out.print(cardDealList[4].getDeal().get(i) + "  ");
            System.out.print(cardDealList[5].getDeal().get(i) + "  ");
            System.out.print(cardDealList[6].getDeal().get(i) + "  ");
            System.out.print("\n");
        }


    }


    public void setDeckOnField() {

        for (int i = 0; i < cardDealList.length; i++) {
            cardDealList[i] = new CardDeal();
            cardDealList[i].drawFromDeck(myCardDeck, i + 1);
            int x = 7 - cardDealList[i].getHandSize();
            for (int j = 0; j <= x; j++) {

                cardDealList[i].getDeal().add(new PatiensPlayingCard(Suit.Clubs, 0));
            }
        }

        for (int i = 0; i < 4; i++) {
            sortedCardHand[i] = new CardDeal();
            sortedCardHand[i].drawFromDeck(myCardDeck, 0);
        }
    }

    public void setNewPatiensGameDeck() {
        myCardDeck.newDeck();
        myCardDeck.shuffleDeck();
    }
}



