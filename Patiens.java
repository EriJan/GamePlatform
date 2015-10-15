import javax.swing.*;
import java.util.List;
import java.util.ArrayList;


public class Patiens extends CardGame {


    Patiens() {
        super();
    }

/*
* från prucuctBackLog till release BackLog
* I sprinten beräknas tid i 1, 2, 4 8 h eller i 2, 3 5 10 dagar.
* burnDown chart
* en sprint är timeboxad
* gör om funktionerna för isRed
* för g
* product backlog
* release backlog
* sprint backlog (med trello)
* BurnDow Chart
* Minst ett spelbart spel
* */
    PatiensDeckHandler patiensCardDeck = new PatiensDeckHandler();
    PatiensCardDeal[] cardDealList = new PatiensCardDeal[7];
    PatiensCardDeal[] sortedCardDeal = new PatiensCardDeal[4];
    PatiensCardDeal[] whatsLeftCardDeal = new PatiensCardDeal[2];
    PatiensPlayingCard[] arrayOfEmptyCards = new PatiensPlayingCard[4];
    ArrayList<PatiensPlayingCard> listOfOpenCards = new ArrayList<>();
    ArrayList<Boolean> boolList = new ArrayList<>();
    ArrayList<PatiensPlayingCard> localFromCheck = new ArrayList<>();
    ArrayList<PatiensPlayingCard> localToCheck = new ArrayList<>();
    boolean ischangesVar;


    @Override
    public void runGame() {


        boolean gameIsRunning = true;
        System.out.println("Hej och välkommen till Patiens!"); //TODO Översätt till engelska

        setNewPatiensGameDeck();
        setDeckOnField();//UnderDeBugging
        while (gameIsRunning) {

            int longestList = lengthOfList();
            paddingSet(longestList);
            printDeal();
            dePaddingSet();

            printMenu(); //TODO ta bort lägg till radbryt efter utskrift.
            int input = HelperMethods.inPutFromNextInt();
            switch (input) {
                case 1:
                    exekuteValidMoveAndRemoves();

                    break;
                case 2:
                    moveCards();

                    break;
                case 3:
                    putOpenCardInList();
                    break;
                case 4:
                    ifKing((PatiensPlayingCard) getFaceUpCardFromDeck());

                    break;
                case 5:
            }
            if (ischangesVar) {
                //turnUpCard(); TODO dubbla metoder, ta bort en
                openClosedCards();
            }

        }


       /* int longestList = lengthOfList();
        paddingSet(longestList);

        getFaceUpCardFromDeck(); //Byt namn på denna metod, ska heta get and turn up

        longestList = lengthOfList();

        paddingSet(longestList);

        printDeal();*/ //Tror detta kan raderas


    }

    public void openClosedCards() {
        for (PatiensCardDeal deal : cardDealList) {
            if (!deal.getLastCard().isFaceUp()) {
                deal.getLastCard().revealCard();
            }
        }
    }

    public void printMenu() {
        System.out.print("\n");
        System.out.println("1. Vill du lägga upp alla de möjliga korten på respektive hög? Skriv 1");
        System.out.println("2. Vill du att de högar som kan läggas på varandra ska göra just det? Skriv 2");
        System.out.println("3. Vill du lägga det upplagda kortet på en av högarna? Skriv 4");
        System.out.println("4. Vill du lägga en kung på en ledig plats? Skriv 5");
        System.out.println("5. Vad vill du göra? flytta ett eller flera kort från en hög till en annan? Skriv 6");
    }

    public void initSortedList() {
        arrayOfEmptyCards[0] = new PatiensPlayingCard(Suit.Clubs, 0);
        arrayOfEmptyCards[1] = new PatiensPlayingCard(Suit.Diamonds, 0);
        arrayOfEmptyCards[2] = new PatiensPlayingCard(Suit.Hearts, 0);
        arrayOfEmptyCards[3] = new PatiensPlayingCard(Suit.Spades, 0);


    }

    public boolean isEmptyIndex() {
        boolean isEmptyIndex = false;
        for (PatiensCardDeal cardDeal : cardDealList) {
            if (cardDeal.isHandEmpty()) {
                isEmptyIndex = true;
                break;
            }
        }
        return isEmptyIndex;
    }

    public void ifKing(PatiensPlayingCard p) {
        if (isEmptyIndex() && p.getValue() == 13) {
            for (PatiensCardDeal cardDeal : cardDealList) {
                if (cardDeal.isHandEmpty()) {
                    cardDeal.getDeal().add(p);
                    break;
                }
            }
        }
    }


    public void dePaddingSet() {
        for (PatiensCardDeal deal : cardDealList) {
            for (int i = deal.getDeal().size() - 1; i >= 0; i--) {
                if (deal.getDeal().get(i).getValue() == 0) {
                    deal.getDeal().remove(i);
                } else if (deal.getDeal().get(i).getValue() != 0) {
                    break;
                }
            }
        }
    }

    public void putOpenCardInList() {

        List<PatiensPlayingCard> localSortedList = new ArrayList<>();
        int localInt = getFaceUpCardFromDeck().getValue();

        for (PatiensCardDeal deal : sortedCardDeal) {
            if (getFaceUpCardFromDeck().getSuit() == deal.getDeal().get(0).getSuit() //TODO lägg denna jämförelse i en compair to metod
                    && localInt - 1 == deal.getDeal().get(0).getValue()) {

                deal.getDeal().add(patiensCardDeck.drawThisCard((PatiensPlayingCard) getFaceUpCardFromDeck()));//TODO kolla casting, varför?
            }
        }//comp to


        for (PatiensCardDeal deal : cardDealList) {
            for (int i = 0; i < deal.getDeal().size() - 1; i++) {
                if (getFaceUpCardFromDeck().getSuit() == Suit.Hearts
                        || getFaceUpCardFromDeck().getSuit() == Suit.Diamonds
                        && deal.getDeal().get(i).getSuit() == Suit.Spades
                        || deal.getDeal().get(i).getSuit() == Suit.Clubs
                        && deal.getDeal().get(i).getValue() == localInt + 1) {
                    deal.getDeal().add(patiensCardDeck.drawThisCard((PatiensPlayingCard) getFaceUpCardFromDeck())); //TODO kolla casting

                }
            }
        }
    }


    public PlayingCard getFaceUpCardFromDeck() { //TODO synna gill för långa anrop
        whatsLeftCardDeal[1].getDeal().add(whatsLeftCardDeal[0].getDeal().remove(0));

        whatsLeftCardDeal[1].getDeal().get(0).revealCard();

        return whatsLeftCardDeal[1].getDeal().get(0);

    }

    public void findCardsAndAddToNewList(PatiensPlayingCard p, PatiensPlayingCard q) {
        int from = 0;
        int to;
        List<PatiensPlayingCard> localHost1 = new ArrayList<>();
        List<PatiensPlayingCard> localHost2;
        for (int i = 0; i < cardDealList.length; i++) {
            if (cardDealList[i].getDeal().contains(p)) {
                int localIndexFrom = firstCardFaceUpPosition(i);
                from = i;
                localHost1 = cardDealList[from].getDeal().subList(localIndexFrom, cardDealList[from].getDeal().size() - 1);

            }

            if (cardDealList[i].getDeal().contains(q)) {
                to = i;
                for (int j = cardDealList[i].getHandSize() - 1; j >= 0; j--) {
                    if (cardDealList[i].getDeal().get(j).isFaceUp()) {
                        int localIndexTo = j;
                        localHost2 = cardDealList[i].getDeal().subList(j + 1, cardDealList[i].getDeal().size() - 1);
                        for (PlayingCard card : localHost2) {
                            cardDealList[i].getDeal().remove(card);
                        }
                        int local = cardDealList[i].getDeal().size() - 1;
                        for (int k = 0; k < localHost1.size(); k++) {
                            cardDealList[i].getDeal().add(local, localHost1.get(0));
                        }
                    }
                }
            }
        }
    }

    public ArrayList<PatiensPlayingCard> ifCardsValueMach(PatiensPlayingCard p, PatiensPlayingCard q) {
        ArrayList<PatiensPlayingCard> localList = new ArrayList<>();
        for (PatiensPlayingCard cardFrom : localFromCheck) {
            for (PatiensPlayingCard cardTo : localFromCheck) {
                if (cardFrom.getValue() + 1 == cardTo.getValue()) {
                    localList.add(cardFrom);
                    localList.add(cardTo);
                    return localList;
                }
            }
        }
        return localList;
    }

    public ArrayList<PatiensPlayingCard> ifCardsSuitMach(ArrayList<PatiensPlayingCard> listToCheck) {

        if (listToCheck.get(0).isRed(listToCheck.get(0)) && listToCheck.get(1).isBlack(listToCheck.get(1))) {
            return listToCheck;
        } else if (listToCheck.get(0).isBlack(listToCheck.get(0)) && listToCheck.get(1).isRed(listToCheck.get(1))) {
            return listToCheck;
        } else {
            listToCheck.remove(1);
            listToCheck.remove(0);
        }


        return listToCheck;
    }

    public Boolean addOneToAnother(ArrayList<PatiensPlayingCard> validList) {
        ArrayList<PatiensPlayingCard> localSubList = new ArrayList<>();
        boolean validMoveExe = false;
        int local;
        for (int i = 0; i < cardDealList.length; i++) {
            if (cardDealList[i].getDeal().contains(validList.get(0))) {
                local = cardDealList[i].getDeal().size();
                for (int j = 0; j < local; j++) {

                    localSubList.addAll(cardDealList[i].getDeal().subList(j, local - 1));
                    i = cardDealList.length + 2;
                    break;
                }
            }

        }

        for (int i = 0; i < cardDealList.length; i++) {
            if (cardDealList[i].getDeal().contains(validList.get(1))) {
                cardDealList[i].getDeal().addAll(localSubList);

                validMoveExe = true;

            }
        }
        return validMoveExe;
    }


    public boolean moveCards() {
        boolean test = false;
        localFromCheck = listOfCardsToMove(); //JÄmför två listor, passar några ihop?
        localToCheck = whtasUpLIst();
        ArrayList<PatiensPlayingCard> awnserFromCheckValue = new ArrayList<>();
        ArrayList<PatiensPlayingCard> awnserFromCheckSuit = new ArrayList<>();


        for (int i = 0; i < localFromCheck.size(); i++) {
            for (int j = 0; j < localToCheck.size(); j++) {
                awnserFromCheckValue.addAll(ifCardsValueMach(localFromCheck.get(i), localToCheck.get(j)));
                if (!awnserFromCheckValue.isEmpty()) {
                    awnserFromCheckSuit.addAll(ifCardsSuitMach(awnserFromCheckValue));
                    if (!awnserFromCheckSuit.isEmpty()) {
                        addOneToAnother(awnserFromCheckSuit);
                        test = true;
                        return test;
                    }
                }
            }
        }
        return test;

    }

    //Skapar en lista av kort som är möjliga att flytta
    public ArrayList<PatiensPlayingCard> listOfCardsToMove() {
        ArrayList<PatiensPlayingCard> localList = new ArrayList<PatiensPlayingCard>();
        for (int i = 0; i < cardDealList.length; i++) {
            int position = firstCardFaceUpPosition(i);
            localList.add((PatiensPlayingCard) cardDealList[i].getDeal().get(position));
        }

        return localList;
    }

    //metod som kollar det översta kortet som är uppvänt i en lista
    public int firstCardFaceUpPosition(int i) {
        int position = 0;
        for (int j = cardDealList[i].getHandSize() - 1; j >= 0; j--) {
            // if (cardDealList[i].getDeal().get(j).getValue() != 0) {
            if (!cardDealList[i].getDeal().get(j).isFaceUp()) {

                position = j + 1;

                return position;
            }
        }
        return position;

    }


    public void paddingSet(int longestList) {
        for (int i = 0; i < cardDealList.length; i++) {
            int x = longestList - cardDealList[i].getDeal().size();
            for (int j = 0; j <= x; j++) {//TODO kolla depadding, underligt värde på x
                //På vilken plats vill jag lägga kortet?
                cardDealList[i].getDeal().add(cardDealList[i].getHandSize(), new PatiensPlayingCard(Suit.Clubs, 0));//TODO förkorta detta anrop genom att anropa ny metod

            }
        }
    }

    //Kollar vilken av kortlistorna som är längst, returvariabeln används för att lägga till tomma kort för att fylla ut spelplanen inför utskrift
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
                // if (cardDealList[i].getDeal().get(j).getValue() != 0) {
                if (!cardDealList[i].getDeal().get(j).isFaceUp()) {
                    cardDealList[i].getDeal().get(j).revealCard();
                    break;
                }
            }
        }
    }

    public void removeObeject(PatiensPlayingCard p) {
        for (int i = 0; i < cardDealList.length; i++) {
            if (cardDealList[i].getDeal().contains(p)) {
                cardDealList[i].getDeal().remove(p);
            }
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

    //Skapar en lista av alla uppvända kort från listorna
    public ArrayList<PatiensPlayingCard> whtasUpLIst() {
        ArrayList<PatiensPlayingCard> localList = new ArrayList<>();
        for (int i = 0; i < cardDealList.length; i++) {
            for (int j = cardDealList[i].getHandSize() - 1; j >= 0; j--) {
                localList.add(cardDealList[i].getDeal().get(j));

                break;

            }
        }
        // for (int i = 0; i < localList.size(); i++) {
        //System.out.println(localList.get(i));
        //  }
        return localList;
    }

    public boolean putInSortedList(PatiensPlayingCard p) {
        boolean returnBol = false;
        int local = sortedCardDeal[0].getDeal().get(0).getValue() + 1;


        if (p.getSuit() == Suit.Clubs) {
            local = sortedCardDeal[0].getDeal().get(0).getValue() + 1;

            if (p.getValue() == local) {
                sortedCardDeal[0].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Diamonds) {
            local = sortedCardDeal[1].getDeal().get(0).getValue() + 1;


            if (p.getValue() == local) {
                sortedCardDeal[1].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Hearts) {
            local = sortedCardDeal[2].getDeal().get(0).getValue() + 1;

            if ((p.getValue() == local)) {
                sortedCardDeal[2].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Spades) {
            local = sortedCardDeal[3].getDeal().get(0).getValue() + 1;
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

    public PatiensPlayingCard getSortedTopCard(int i) {
        return (PatiensPlayingCard) sortedCardDeal[i].getDeal().get(sortedCardDeal[i].getDeal().size() - 1);


    }

    public void printDeal() {
        String[][] cardHolder1 = {{""}, {" __ "}, {"|  |"}, {"|  |"}, {" == "}};
        String[][] cardHolder2 = {{""}, {"   "}, {"" + getFaceUpCardFromDeck() + " "}, {"   "}, {"   "}};
        String[][] cardHolder3 = {{""}, {" __ "}, {"|" + getSortedTopCard(0) + "|"}, {"|  |"}, {" == "}};
        String[][] cardHolder4 = {{""}, {" __ "}, {"|" + getSortedTopCard(1) + "|"}, {"|  |"}, {" == "}};
        String[][] cardHolder5 = {{""}, {" __ "}, {"|" + getSortedTopCard(2) + "|"}, {"|  |"}, {" == "}};
        String[][] cardHolder6 = {{""}, {" __ "}, {"|" + getSortedTopCard(3) + "|"}, {"|  |"}, {" == "}};

        String[] frontNumber = {"  1  ", "  2  ", "  \u2666  ", "   \u2663  ", "   \u2665  ", "  \u2660  "};

//TODO ändra överskriften på det utskrivna spelet (1 2 osv.) LÄgg till radbryt där det behövs
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


        for (int i = 0; i < cardDealList.length; i++) { //TODO Detta moment kan vara onödigt eftersedan jag lagt till metod som öppnar kort
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
            cardDealList[i] = new PatiensCardDeal();

            cardDealList[i].drawFromDeck(patiensCardDeck, i + 1);
            //int x = 7 - cardDealList[i].getHandSize();
            //for (int j = 0; j <= x; j++) {

            //cardDealList[i].getDeal().add(new PatiensPlayingCard(Suit.Clubs, 0)); //Ta bort padding i denna funktion

        }

        initSortedList();

        for (int j = 0; j < 4; j++) {
            sortedCardDeal[j] = new PatiensCardDeal();
            sortedCardDeal[j].drawFromDeck(patiensCardDeck, 0);
            sortedCardDeal[j].getDeal().add(arrayOfEmptyCards[j]);
        }

        whatsLeftCardDeal[0] = new PatiensCardDeal();
        whatsLeftCardDeal[1] = new PatiensCardDeal();
        int localSize = patiensCardDeck.getCurrentDeck().size();
        whatsLeftCardDeal[0].drawFromDeck(patiensCardDeck, localSize);


    }

    public void setNewPatiensGameDeck() {
        patiensCardDeck.newDeck();
        patiensCardDeck.shuffleDeck();
    }
}



