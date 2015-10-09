import java.util.List;
import java.util.ArrayList;


public class Patiens extends CardGame {


    Patiens() {
        super();
    }


    DeckHandler patiensCardDeck = new DeckHandler();
    CardDeal[] cardDealList = new CardDeal[7];
    CardDeal[] sortedCardDeal = new CardDeal[4];
    List<PlayingCard> localCurrentDeck;
    PatiensPlayingCard[] arrayOfEmptyCards = new PatiensPlayingCard[4];
    ArrayList<PlayingCard> listOfOpenCards = new ArrayList<>();
    ArrayList<Boolean> boolList = new ArrayList<>();
    boolean ischangesVar;
    HelperMethods help = new HelperMethods();




    @Override
    public void runGame() {



        System.out.println("Hej och välkommen till Patiens!");
        setNewPatiensGameDeck();
        setDeckOnField();
        printDeal();
        dePaddingSet();

       printMenu(); //TODO ta bort lägg till radbryt efter utskrift. Ta bort att det blir null i utskriften.
        int input = help.inPutFromNextInt();
        switch (input){
            case 1 : exekuteValidMoveAndRemoves();
                openClosedCards();
                break;
            case 2 : moveCards();
                openClosedCards();
                break;
            case 3 : moveCards();
                openClosedCards();
                break;
            case 4 : putOpenCardInList();
                break;
            case 5 : ifKing(getFaceUpCardFromDeck()); //Hitta ett sätt
                openClosedCards();
                break;
            case 6 :
        }
        int longestList = lengthOfList();
        paddingSet(longestList);
        
        getFaceUpCardFromDeck(); //Byt namn på denna metod, ska heta get and turn up
        if (ischangesVar) {
            turnUpCard();
        }
        longestList = lengthOfList();

        paddingSet(longestList);

        printDeal();




    }
    public void openClosedCards(){
        for (CardDeal deal : cardDealList){
            if (!deal.getLastCard().isFaceUp()){
                deal.getLastCard().revealCard();
            }
        }
    }
    public void printMenu(){
        System.out.println("1. Vill du lägga upp alla de möjliga korten på respektive hög? Skriv 1");
        System.out.println("2. Vill du att de högar som kan läggas på varandra ska göra just det? Skriv 2");
        System.out.println("3. Vill du lägga det upplagda kortet på en av högarna fortfarande? Skriv 3");
        System.out.println("4. Vill du lägga det upplagda kortet på en av högarna? Skriv 4");
        System.out.println("5. Vill du lägga en kung på en ledig plats? Skriv 5");
        System.out.println("6. Vad vill du göra? flytta ett eller flera kort från en hög till en annan? Skriv 6");
    }
    public void initSortedList(){
        arrayOfEmptyCards[0] = new PatiensPlayingCard(Suit.Clubs, 0);
        arrayOfEmptyCards[1] = new PatiensPlayingCard(Suit.Diamonds, 0);
        arrayOfEmptyCards[2] = new PatiensPlayingCard(Suit.Hearts, 0);
        arrayOfEmptyCards[3] = new PatiensPlayingCard(Suit.Spades, 0);

    }
    public boolean isEmptyIndex(){
        boolean isEmptyIndex = false;
       for (CardDeal cardDeal : cardDealList){
           if(cardDeal.isHandEmpty() ){
               isEmptyIndex = true;
           }
       }return isEmptyIndex;
    }

    public void ifKing(PlayingCard p){
        if (isEmptyIndex() && p.getValue()== 13){
            for (CardDeal cardDeal : cardDealList){
                if(cardDeal.isHandEmpty()){
                    cardDeal.getDeal().add(p);
                    break;
                }
            }
        }
    }



    public void dePaddingSet() {
        for (CardDeal deal : cardDealList) {
            for (int i = 0; i < deal.getDeal().size(); i++) {
                if (deal.getDeal().get(i).getValue() == 0) {
                    deal.getDeal().remove(i);
                }
            }
        }
    }

    public void putOpenCardInList() {

        List<PlayingCard> localSortedList = new ArrayList<>();
        int localInt = getFaceUpCardFromDeck().getValue();

        for (CardDeal deal : sortedCardDeal) {
            if (getFaceUpCardFromDeck().getSuit() == deal.getDeal().get(deal.getDeal().size() - 1).getSuit()
                    && localInt - 1 == deal.getDeal().get(deal.getDeal().size() - 1).getValue()) {

                deal.getDeal().add(patiensCardDeck.drawThisCard(getFaceUpCardFromDeck()));
            }
        }//comp to


        for (CardDeal deal : cardDealList) {
            for (int i = 0; i < deal.getDeal().size() - 1; i++) {
                if (getFaceUpCardFromDeck().getSuit() == Suit.Hearts
                        || getFaceUpCardFromDeck().getSuit() == Suit.Diamonds
                        && deal.getDeal().get(i).getSuit() == Suit.Spades
                        || deal.getDeal().get(i).getSuit() == Suit.Clubs
                        && deal.getDeal().get(i).getValue() == localInt + 1) {
                    deal.getDeal().add(patiensCardDeck.drawThisCard(getFaceUpCardFromDeck()));

                }

            }
        }


    }


    public PlayingCard getFaceUpCardFromDeck() {
        if (!patiensCardDeck.getCurrentDeck().get(patiensCardDeck.getCurrentDeck().size()-1).isFaceUp()) {
            patiensCardDeck.getCurrentDeck().get(patiensCardDeck.getCurrentDeck().size() - 1).revealCard();
        }

        return patiensCardDeck.getCurrentDeck().get(patiensCardDeck.getCurrentDeck().size()-1);

    }

    public void findCardsAndAddToNewList(PlayingCard p, PlayingCard q) {
        int from = 0;
        int to;
        ArrayList<PlayingCard> localHost1 = new ArrayList<>();
        ArrayList<PlayingCard> localHost2;
        for (int i = 0; i < cardDealList.length; i++) {
            if (cardDealList[i].getDeal().contains(p)) {
                int localIndexFrom = firstCardFaceUpPosition(i);
                from = i;
                localHost1 = (ArrayList<PlayingCard>) cardDealList[from].getDeal().subList(localIndexFrom, cardDealList[from].getDeal().size() - 1);
            }

            if (cardDealList[i].getDeal().contains(q)) {
                to = i;
                for (int j = cardDealList[i].getHandSize() - 1; j >= 0; j--) {
                    if (cardDealList[i].getDeal().get(j).getValue() != 0) {
                        int localIndexTo = j;
                        localHost2 = (ArrayList<PlayingCard>) cardDealList[i].getDeal().subList(j + 1, cardDealList[i].getDeal().size() - 1);
                        for (PlayingCard card : localHost2) {
                            cardDealList[i].getDeal().remove(card);
                        }
                        cardDealList[i].getDeal().addAll(localHost1);
                    }
                }
            }
        }
    }

    public void moveCards() {
        ArrayList<PlayingCard> localListForMoveFromCheck = new ArrayList<>();
        ArrayList<PlayingCard> localListForMoveToCheck = new ArrayList<>();

        localListForMoveFromCheck = listOfCardsToMove();
        localListForMoveToCheck = whtasUpLIst();
        for (int i = 0; i < localListForMoveFromCheck.size(); i++) {
            int checkFrom = localListForMoveFromCheck.get(i).getValue();
            for (int j = 0; j < localListForMoveToCheck.size(); j++) {
                if (checkFrom - 1 == localListForMoveToCheck.get(j).getValue()) {
                    if (localListForMoveToCheck.get(j).getSuit() == Suit.Clubs
                            || localListForMoveToCheck.get(j).getSuit() == Suit.Spades) {
                        if (localListForMoveFromCheck.get(i).getSuit() == Suit.Diamonds
                                || localListForMoveFromCheck.get(i).getSuit() == Suit.Hearts) {
                            //En metod som letar rätt på två kort, det en a sla läggas på det andra.Den jag ska flytta från är först
                            System.out.println("" + localListForMoveFromCheck.get(i) + localListForMoveToCheck.get(j));
                            findCardsAndAddToNewList(localListForMoveFromCheck.get(i), localListForMoveToCheck.get(j));
                        }
                    }
                    if (localListForMoveToCheck.get(j).getSuit() == Suit.Hearts
                            || localListForMoveToCheck.get(j).getSuit() == Suit.Diamonds) {

                        if (localListForMoveFromCheck.get(j).getSuit() == Suit.Clubs
                                || localListForMoveFromCheck.get(j).getSuit() == Suit.Spades) {
                            System.out.println("" + localListForMoveFromCheck.get(i) + localListForMoveToCheck.get(j));
                            findCardsAndAddToNewList(localListForMoveFromCheck.get(i), localListForMoveToCheck.get(j));
                        }

                    }
                }
            }
        }
    }

    public ArrayList<PlayingCard> listOfCardsToMove() {
        ArrayList<PlayingCard> localList = new ArrayList<PlayingCard>();
        for (int i = 0; i < cardDealList.length; i++) {
            int position = firstCardFaceUpPosition(i);
            localList.add(cardDealList[i].getDeal().get(position));
        }

        return localList;
    }

    public int firstCardFaceUpPosition(int i) {
        int position = 0;
        for (int j = cardDealList[i].getHandSize() - 1; j >= 0; j--) {
            if (cardDealList[i].getDeal().get(j).getValue() != 0) {
                if (!cardDealList[i].getDeal().get(j).isFaceUp()) {

                    position = j - 1;

                    return position;
                }
            }
        }
        return position;

    }


    public void paddingSet(int longestList) {
        for (int i = 0; i < cardDealList.length; i++) {
            int x = longestList - cardDealList[i].getDeal().size();
            for (int j = 0; j <= x; j++) {
                cardDealList[i].getDeal().add(cardDealList.length - 1, new PatiensPlayingCard(Suit.Clubs, 0));
            }
        }
    }

    public int lengthOfList() {
        int check = 0;
        int checkHolder = 0;
        for (int i = 0; i < cardDealList.length; i++) {
            int test = cardDealList[i].getDeal().size();
            if (check < test) {
                check = test;
            }
        }
        return check;
    }

    public void turnUpCard() {
        for (int i = 0; i < cardDealList.length; i++) {
            for (int j = cardDealList[i].getHandSize() - 1; j >= 0; j--) {
                if (cardDealList[i].getDeal().get(j).getValue() != 0) {
                    if (!cardDealList[i].getDeal().get(j).isFaceUp()) {
                        cardDealList[i].getDeal().get(j).revealCard();
                    }
                }
            }
        }
    }

    public void removeObeject(PlayingCard p) {
        for (int i = 0; i < cardDealList.length; i++) {
            cardDealList[i].getDeal().remove(p);
        }
    }

    public void exekuteValidMoveAndRemoves() {
        boolean isCangesVar = false;
        listOfOpenCards = whtasUpLIst(); //TODO ta bort spårutskrift i WhatsUpList
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
                    localList.add(cardDealList[i].getDeal().get(j)); //TODO Ta bort padding

                    break;
                }
            }
        }
        for (int i = 0; i < localList.size(); i++) {
            System.out.println(localList.get(i));
        }
        return localList;
    }

    public boolean putInSortedList(PlayingCard p) {
        boolean returnBol = false;
        int local;

        if (p.getSuit() == Suit.Clubs) {
            local = sortedCardDeal[0].getDeal().get(0).getValue()+1;
            if (p.getValue() == local) {
                sortedCardDeal[0].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Diamonds) {
            local = sortedCardDeal[1].getDeal().get(0).getValue()+1;
            if (p.getValue() == local) {
                sortedCardDeal[1].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Hearts) {
            local = sortedCardDeal[2].getDeal().get(0).getValue()+1;
            System.out.println(local);
            if ((p.getValue() ==  local)) {
                sortedCardDeal[2].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Spades) {
            local = sortedCardDeal[3].getDeal().get(0).getValue()+1;
            if ((p.getValue() == local)) {
                sortedCardDeal[3].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else {
            returnBol = false;
        }

        return returnBol;
    }

    public PlayingCard getSortedTopCard(int i) {
        return sortedCardDeal[i].getDeal().get(sortedCardDeal[i].getDeal().size() - 1);


    }

    public void printDeal() {
        String[][] cardHolder1 = {{""}, {" __ "}, {"|  |"}, {"|  |"}, {" == "}};
        String[][] cardHolder2 = {{""}, {"   "}, {" " + getFaceUpCardFromDeck() + " "}, {"   "}, {"   "}};
        String[][] cardHolder3 = {{""}, {" __ "}, {" " + getSortedTopCard(0) + " "}, {"|  |"}, {" == "}};
        String[][] cardHolder4 = {{""}, {" __ "}, {" " + getSortedTopCard(1) + " "}, {"|  |"}, {" == "}};
        String[][] cardHolder5 = {{""}, {" __ "}, {" " + getSortedTopCard(2) + " "}, {"|  |"}, {" == "}};
        String[][] cardHolder6 = {{""}, {" __ "}, {" " + getSortedTopCard(3) + " "}, {"|  |"}, {" == "}};

        String[] frontNumber = {"  1  ", "  2  ", "  \u2666  ", "   \u2663  ", "   \u2665  ", "  \u2660  "};


        for (int i = 0; i < cardHolder1.length; i++) {
            for (int j = 0; j < 1; j++) {
                if (i == 0) {
                    for (int k = 0; k < frontNumber.length; k++) {
                        System.out.print(frontNumber[k]);
                    }
                    System.out.print("\n");
                } else {
                    System.out.print(cardHolder1[i][j] + "  ");
                    System.out.print(cardHolder2[i][j] + "  ");
                    System.out.print(cardHolder3[i][j] + "  ");
                    System.out.print(cardHolder4[i][j] + "  ");
                    System.out.print(cardHolder5[i][j] + "  ");
                    System.out.print(cardHolder6[i][j] + "  ");
                    System.out.print("\n");
                }
            }
        }
        System.out.print("\n");


        for (int i = 0; i < cardDealList.length; i++) {
            for (int j = cardDealList[i].getDeal().size() - 1; j >= 0; j--) {
                if (cardDealList[i].getDeal().get(j).getValue() != 0) {
                    cardDealList[i].getDeal().get(j).revealCard();
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

                cardDealList[i].getDeal().add(new PatiensPlayingCard(Suit.Clubs, 0)); //Ta bort padding i denna funktion
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



