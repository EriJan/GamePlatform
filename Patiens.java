import java.util.List;
import java.util.ArrayList;


public class Patiens extends CardGame {


    Patiens() {
        super();
    }


    DeckHandler patiensCardDeck = new DeckHandler();
    CardDeal patiensCardDeal;
    CardDeal[] cardDealList = new CardDeal[7];
    CardDeal[] sortedCardDeal = new CardDeal[4];
    List<PlayingCard> positionDeal1 = new ArrayList<PlayingCard>();
    PatiensPlayingCard pPlayingCard;
    PatiensPlayingCard[] arrayOfEmptyCards = new PatiensPlayingCard[4];
    ArrayList<PlayingCard> listOfOpenCards = new ArrayList<>();
    ArrayList<Boolean> boolList = new ArrayList<>();
    boolean ischangesVar;


    String[][] cardHolder = {{""}, {" __ "}, {"|  |"}, {"|  |"}, {" == "}};
    String[][] cardHolder2 = {{""}, {"   "}, {"   "}, {"   "}, {"   "}};
    String[] frontNumber = {"  1  ", "  2  ", "  \u2666  ", "   \u2663  ", "   \u2665  ", "  \u2660  "};


    @Override
    public void runGame() {

        //If något värde är ess, lägg upp det

        arrayOfEmptyCards[0] = new PatiensPlayingCard(Suit.Clubs, 0);
        arrayOfEmptyCards[1] = new PatiensPlayingCard(Suit.Diamonds, 0);
        arrayOfEmptyCards[2] = new PatiensPlayingCard(Suit.Hearts, 0);
        arrayOfEmptyCards[3] = new PatiensPlayingCard(Suit.Spades, 0);

        System.out.println("Hej och välkommen till Patiens!");
        setNewPatiensGameDeck();

        setDeckOnField();

        printDeal();
        exekuteValidMoveAndRemoves();
        printDeal();

//Gör en metod som vänder upp det understa kortet om något har förändrats
        //Gör en metod som tar den längsta listans längd och gör alla listor lika långa

        //Möjlighet att lägga kung på tom plats
        //


    }
    public void removeObeject(PlayingCard p){
        for (int i = 0; i < cardDealList.length; i++){
            if (cardDealList[i].getDeal().contains(p)){
                cardDealList[i].getDeal().remove(p);
            }

        }
    }
    public void exekuteValidMoveAndRemoves(){
        boolean isCangesVar = false;
        listOfOpenCards = whtasUpLIst();
        for (int i = 0; i < listOfOpenCards.size(); i++) {
            boolList.add(i, putInSortedList(listOfOpenCards.get(i)));
        }
        ischangesVar = isChanges();
    }

    public boolean isChanges() {
        boolean localBool = false;
        for (int i = 0; i < boolList.size(); i++) {
            if (boolList.get(i)) {
                localBool = true;
                break;
            }
        }
        return localBool;
    }

    public ArrayList<PlayingCard> whtasUpLIst() {
        ArrayList<PlayingCard> localList = new ArrayList<PlayingCard>();
        for (int i = 0; i < cardDealList.length; i++) {
            for (int j = cardDealList[i].getHandSize() - 1; j >= 0; j--) {
                if (cardDealList[i].getDeal().get(j).getValue() != 0) {
                    localList.add(cardDealList[i].getDeal().get(j));
                    if (putInSortedList(cardDealList[i].getDeal().get(j))){

                    }
                    break;
                }
            }
        }
        return localList;
    }

    public boolean putInSortedList(PlayingCard p) {
        boolean returnBol = false;
        for (int i = 0; i < sortedCardDeal.length; i++) {

            if (sortedCardDeal[i].getDeal().get(0).getSuit().equals(Suit.Clubs)) {
                if (p.getValue() == sortedCardDeal[i].getDeal().get(0).getValue() + 1) {
                    sortedCardDeal[i].getDeal().add(p);
                    removeObeject(p);
                    returnBol = true;
                }
            } else if (sortedCardDeal[i].getDeal().get(0).getSuit().equals(Suit.Diamonds)) {
                if (p.getValue() == sortedCardDeal[i].getDeal().get(0).getValue() + 1) {
                    sortedCardDeal[i].getDeal().add(p);
                    removeObeject(p);
                    returnBol = true;
                }
            } else if (sortedCardDeal[i].getDeal().get(0).getSuit().equals(Suit.Hearts)) {
                if ((p.getValue() == sortedCardDeal[i].getDeal().get(0).getValue() + 1)) {
                    sortedCardDeal[i].getDeal().add(p);
                    removeObeject(p);
                    returnBol = true;
                }
            } else if (sortedCardDeal[i].getDeal().get(0).getSuit().equals(Suit.Spades)) {
                if ((p.getValue() == sortedCardDeal[i].getDeal().get(0).getValue() + 1)) {
                    sortedCardDeal[i].getDeal().add(p);
                    removeObeject(p);
                    returnBol = true;
                }
            } else {
                returnBol = false;
            }
        }
        return returnBol;
    }

    public void printDeal() {


        for (int i = 0; i < cardHolder.length; i++) {
            for (int j = 0; j < 1; j++) {
                if (i == 0) {
                    for (int k = 0; k < frontNumber.length; k++) {
                        System.out.print(frontNumber[k]);
                    }
                    System.out.print("\n");
                } else {
                    System.out.print(cardHolder[i][j] + "  ");
                    System.out.print(cardHolder2[i][j] + "  ");
                    System.out.print(cardHolder[i][j] + "  ");
                    System.out.print(cardHolder[i][j] + "  ");
                    System.out.print(cardHolder[i][j] + "  ");
                    System.out.print(cardHolder[i][j] + "  ");
                    System.out.print("\n");
                }
            }
        }
        System.out.print("\n");


        for (int i = 0; i < cardDealList.length; i++) {
            for (int j = cardDealList[i].getDeal().size() - 1; j >= 0; j--) {
                if (cardDealList[i].getDeal().get(j).getValue() != 0) {
                    cardDealList[i].getDeal().get(j).turnUp();
                    break;
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            System.out.print(cardDealList[0].getDeal().get(i) + "   ");
            System.out.print(cardDealList[1].getDeal().get(i) + "   ");
            System.out.print(cardDealList[2].getDeal().get(i) + "   ");
            System.out.print(cardDealList[3].getDeal().get(i) + "   ");
            System.out.print(cardDealList[4].getDeal().get(i) + "   ");
            System.out.print(cardDealList[5].getDeal().get(i) + "   ");
            System.out.print(cardDealList[6].getDeal().get(i) + "   ");
            System.out.print("\n");
        }


    }


    public void setDeckOnField() {

        for (int i = 0; i < cardDealList.length; i++) {
            cardDealList[i] = new CardDeal();
            cardDealList[i].drawFromDeck(patiensCardDeck, i + 1);
            int x = 7 - cardDealList[i].getHandSize();
            for (int j = 0; j <= x; j++) {

                cardDealList[i].getDeal().add(new PatiensPlayingCard(Suit.Clubs, 0));
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sortedCardDeal[j] = new CardDeal();
                sortedCardDeal[j].drawFromDeck(patiensCardDeck, 0);
                sortedCardDeal[j].getDeal().add(arrayOfEmptyCards[j]);
            }
        }
    }

    public void setNewPatiensGameDeck() {
        patiensCardDeck.newDeck();
        patiensCardDeck.shuffleDeck();
    }
}



