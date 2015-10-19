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
    DeckHandler patiensCardDeck = new DeckHandler();
    CardDeal[] cardDealList = new CardDeal[7];
    CardDeal[] sortedCardDeal = new CardDeal[4];
    CardDeal[] whatsLeftCardDeal = new CardDeal[2];
    PlayingCard[] arrayOfEmptyCards = new PlayingCard[4];
    ArrayList<PlayingCard> listOfOpenCards = new ArrayList<>();
    ArrayList<Boolean> boolList = new ArrayList<>();
    ArrayList<PlayingCard> localFromCheck = new ArrayList<>();
    ArrayList<PlayingCard> localToCheck = new ArrayList<>();
    boolean ischangesVar;
    int longestList;


    @Override
    public void runGame() {


        boolean gameIsRunning = true;
        System.out.println("Hej och välkommen till Patiens!"); //TODO Översätt till engelska

        setNewPatiensGameDeck();
        setDeckOnField();
        while (gameIsRunning) {

            longestList = lengthOfList();
            openClosedCards();
            paddingSet(longestList);
            printDeal();
            dePaddingSet();
            printMenu();
            int input = HelperMethods.inPutFromNextInt();

            //int input = PatiensGUI.printMenue();

            switch (input) {
                case 1:
                    exekuteValidMoveAndRemoves();
                    putOpenCardFromWhatsLeft();
                    openNewCardFromWhatsLeft();

                    break;
                case 2:
                    openNewCardFromWhatsLeft();
                    break;
                case 3:
                    moveCards();

                    break;
                case 4:
                    putOpenCardInList();
                    putOpenCardFromWhatsLeft();
                    break;
                case 5:
                    putKingInOpenIndex();

                    break;
                case 6:
                    turnWhatsLeftDeck();
                    openNewCardFromWhatsLeft();
                    break;

            }
            if (ischangesVar) { //TODO denna variable stämmer inte riktigt
                openClosedCards();
            }
        }
    }

    public void printMenu() {
        String one = "1. Vill du lägga upp alla de möjliga korten på respektive hög? Skriv 1 \n";
        String two = "2. Vill du vända upp ett nytt kort? skriv 2 \n";
        String three = "3. Vill du att de högar som kan läggas på varandra ska göra just det? Skriv 3 \n";
        String four = "4. Vill du lägga det upplagda kortet på en av högarna? Skriv 4\n";
        String five = "5. Vill du lägga en kung på en ledig plats? Skriv 5\n";
        String six = "6. Vill du vända på högen? Skriv 6\n";

        System.out.print("\n");
        System.out.print(one);
        System.out.print(two);
        System.out.print(three);
        System.out.print(four);
        System.out.print(five);
        System.out.print(six);
    }

    public String printMenuPatiens() {
        String one = "1. Vill du lägga upp alla de möjliga korten på respektive hög? Skriv 1 \n";
        String two = "2. Vill du vända upp ett nytt kort? skriv 2 \n";
        String three = "3. Vill du att de högar som kan läggas på varandra ska göra just det? Skriv 3 \n";
        String four = "4. Vill du lägga det upplagda kortet på en av högarna? Skriv 4\n";
        String five = "5. Vill du lägga en kung på en ledig plats? Skriv 5\n";
        String six = "6. Vill du vända på högen? Skriv 6\n";

       /* System.out.print("\n");
        System.out.print(one);
        System.out.print(two);
        System.out.print(three);
        System.out.print(four);
        System.out.print(five);
        System.out.print(six);*/
        String newString = one + two + three + four + five + six;
        return two;
    }

    public void turnWhatsLeftDeck() {
        if (whatsLeftCardDeal[0].isHandEmpty()) {
            whatsLeftCardDeal[1].hideHand();
            whatsLeftCardDeal[0] = whatsLeftCardDeal[1];
        }
    }

    public boolean putOpenCardFromWhatsLeft() {

        boolean isChange = putInSortedList(whatsLeftCardDeal[1].getDeal().get(0));
        return isChange;
    }

    public void dissOpenCard() {
        whatsLeftCardDeal[1].getDeal().add(whatsLeftCardDeal[0].getDeal().remove(0));
    }

    public void openNewCardFromWhatsLeft() {

        whatsLeftCardDeal[0].getDeal().get(0).revealCard();
    }

    public void openClosedCards() {
        for (CardDeal deal : cardDealList) {
            if (deal.isHandEmpty()) {
                break;
            } else if (!deal.getLastCard().isFaceUp()) {
                deal.getLastCard().revealCard();
            }
        }
    }


    public void initSortedList() {
        arrayOfEmptyCards[0] = new PlayingCard(Suit.Clubs, 0);
        arrayOfEmptyCards[1] = new PlayingCard(Suit.Diamonds, 0);
        arrayOfEmptyCards[2] = new PlayingCard(Suit.Hearts, 0);
        arrayOfEmptyCards[3] = new PlayingCard(Suit.Spades, 0);


    }

    public boolean isEmptyIndex() {
        boolean isEmptyIndex = false;
        for (CardDeal cardDeal : cardDealList) {
            if (cardDeal.isHandEmpty()) {
                isEmptyIndex = true;
                break;
            }
        }
        return isEmptyIndex;
    }

    public boolean putKingInOpenIndex() {
        boolean isChange = false;
        ArrayList<PlayingCard> localLIst = new ArrayList<>();
        localLIst = whtasUpLIst();

        for (PlayingCard card : localLIst) {
            if (isEmptyIndex() && card.getValue() == 13) {
                for (CardDeal cardDeal : cardDealList) {
                    if (cardDeal.isHandEmpty()) {
                        cardDeal.getDeal().add(card);
                        break;
                    }
                }
            }
        }
        return isChange;
    }


    public void dePaddingSet() {
        for (CardDeal deal : cardDealList) {
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

        int localInt = getFaceUpCardFromDeck().getValue(); //Vd är detta för metod?

        for (CardDeal deal : sortedCardDeal) //Man skulle kunna lösa detta gneom att dessa deals är speciella för detta syfte
            //
            if (getFaceUpCardFromDeck().getSuit() == deal.getDeal().get(0).getSuit() //TODO lägg denna jämförelse i en compair to metod
                    && localInt - 1 == deal.getDeal().get(0).getValue()) {

                deal.getDeal().add(patiensCardDeck.drawThisCard((PlayingCard) getFaceUpCardFromDeck()));//TODO kolla casting, varför?
            }
        //comp to

//TODO gör om jämförelse med isRed
        for (CardDeal deal : cardDealList) {
            for (int i = 0; i < deal.getDeal().size() - 1; i++) {
                if (getFaceUpCardFromDeck().getSuit() == Suit.Hearts
                        || getFaceUpCardFromDeck().getSuit() == Suit.Diamonds
                        && deal.getDeal().get(i).getSuit() == Suit.Spades
                        || deal.getDeal().get(i).getSuit() == Suit.Clubs
                        && deal.getDeal().get(i).getValue() == localInt + 1) {
                    deal.getDeal().add(patiensCardDeck.drawThisCard((PlayingCard) getFaceUpCardFromDeck())); //TODO kolla casting

                }
            }
        }
    }


    public PlayingCard getFaceUpCardFromDeck() { //TODO synna gill för långa anrop
        whatsLeftCardDeal[1].getDeal().add(whatsLeftCardDeal[0].getDeal().remove(0));

        whatsLeftCardDeal[1].getDeal().get(0).revealCard();

        return whatsLeftCardDeal[1].getDeal().get(0);

    }

    public void findCardsAndAddToNewList(PlayingCard p, PlayingCard q) {
        int from = 0;
        int to;
        List<PlayingCard> localHost1 = new ArrayList<>();
        List<PlayingCard> localHost2;
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

    public ArrayList<PlayingCard> ifCardsValueMach() {
        ArrayList<PlayingCard> localList = new ArrayList<>();
        for (PlayingCard cardFrom : localFromCheck) {
            for (PlayingCard cardTo : localToCheck) {
                if (cardFrom.getValue() + 1 == cardTo.getValue()) {
                    localList.add(cardFrom);
                    localList.add(cardTo);
                    System.out.println(localList.get(0) + " " + localList.get(1));
                    return localList;
                }
            }
        }
        return localList;
    }

    public ArrayList<PlayingCard> ifCardsSuitMach(ArrayList<PlayingCard> listToCheck) {
        System.out.println(listToCheck.get(0) + " " + listToCheck.get(1));
        if (listToCheck.get(0).isRed() && listToCheck.get(1).isBlack()) {
            return listToCheck;
        } else if (listToCheck.get(0).isBlack() && listToCheck.get(1).isRed()) {
            return listToCheck;
        } else {
            listToCheck.remove(1);
            listToCheck.remove(0);
        }


        return listToCheck;
    }

    public Boolean addOneToAnother(ArrayList<PlayingCard> validList) { //EN lista med två kort som är möjliga att lägga på varandra
        ArrayList<PlayingCard> localSubList = new ArrayList<>(); //TODO dela upp denna i flera metoder
        boolean validMoveExe = false;
        int local; //Längden på en osorterad lista
        PlayingCard local1 = validList.get(1);
        PlayingCard local2 = validList.get(0);
        if (local1.getValue() - 1 == local2.getValue()) {
            if (whatsLeftCardDeal[1].getDeal().contains((local2))) {
                localSubList.add(local2);
                whatsLeftCardDeal[0].getDeal().remove(local2);
            } else {
                for (int i = 0; i < cardDealList.length; i++) {

                    if (cardDealList[i].getDeal().contains(local2)) {
                        local = cardDealList[i].getDeal().size();
                        for (int j = 0; j < local; j++) {
                            if (cardDealList[i].getDeal().get(j) == local2) {
                                localSubList.addAll(cardDealList[i].getDeal().subList(j, local));
                                for (PlayingCard card : localSubList) {
                                    cardDealList[i].getDeal().remove(card);
                                }
                                for (PlayingCard card : localSubList) {
                                    System.out.println(card); //TODO ta bort spårutskrift
                                }
                                i = cardDealList.length + 2;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (local2.getValue() - 1 == local1.getValue()) {
            if (whatsLeftCardDeal[0].getDeal().contains((local2))) {
                localSubList.add(local2);
                whatsLeftCardDeal[0].getDeal().remove(local2);
            } else {
                for (int i = 0; i < cardDealList.length; i++) {

                    if (cardDealList[i].getDeal().contains(local1)) {
                        local = cardDealList[i].getDeal().size();
                        for (int j = 0; j < local; j++) {
                            if (cardDealList[i].getDeal().get(j) == local1) {
                                localSubList.addAll(cardDealList[i].getDeal().subList(j, local - 1));
                                for (PlayingCard card : localSubList) {
                                    System.out.println(card); //TODO ta bort spårutskrift
                                }
                                i = cardDealList.length + 2;
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < cardDealList.length; i++) {

            if (cardDealList[i].getDeal().contains(local1)) {
                cardDealList[i].getDeal().addAll(localSubList);
                for (PlayingCard card : cardDealList[i].getDeal()) {
                    System.out.println(card);//TODO ta bort spårutskrift
                }
                validMoveExe = true;
                break;

            }
        }
        return validMoveExe;
    }


    public boolean moveCards() {
        boolean test = false;
        localFromCheck = listOfCardsToMove(); //JÄmför två listor, passar några ihop?
        localToCheck = whtasUpLIst();
        ArrayList<PlayingCard> awnserFromCheckValue = new ArrayList<>();
        ArrayList<PlayingCard> awnserFromCheckSuit = new ArrayList<>();

        awnserFromCheckValue.addAll(ifCardsValueMach());
        if (!awnserFromCheckValue.isEmpty()) {
            awnserFromCheckSuit.addAll(ifCardsSuitMach(awnserFromCheckValue));
            if (!awnserFromCheckSuit.isEmpty()) {
                addOneToAnother(awnserFromCheckSuit);
                test = true;
                return test;
            }
        } else if (awnserFromCheckValue.isEmpty()) {
            test = false;
            return test;
        }
        return test;

    }

    //Skapar en lista av kort som är möjliga att flytta
    public ArrayList<PlayingCard> listOfCardsToMove() {
        ArrayList<PlayingCard> localList = new ArrayList<PlayingCard>();
        for (int i = 0; i < cardDealList.length; i++) {
            if (cardDealList[i].isHandEmpty()) {
                continue;
            } else {
                int position = firstCardFaceUpPosition(i);
                localList.add((PlayingCard) cardDealList[i].getDeal().get(position));
            }


        }
        localList.add(whatsLeftCardDeal[1].getCard(0));
        return localList;
    }

    //metod som kollar det översta kortet som är uppvänt i en lista
    public int firstCardFaceUpPosition(int i) {
        int position = 0;
        for (int j = cardDealList[i].getHandSize() - 1; j >= 0; j--) {
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
            for (int j = 0; j <= x; j++) {
                //På vilken plats vill jag lägga kortet?
                cardDealList[i].getDeal().add(cardDealList[i].getHandSize(), new PlayingCard(Suit.Clubs, 0));//TODO förkorta detta anrop genom att anropa ny metod

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
        return check + 1;
    }


    public void removeObeject(PlayingCard p) {
        for (int i = 0; i < cardDealList.length; i++) {
            if (cardDealList[i].getDeal().contains(p)) {
                cardDealList[i].getDeal().remove(p);
                break;

            }
        }
    }

    public void exekuteValidMoveAndRemoves() {
        boolean isCangesVar = false;
        listOfOpenCards = whtasUpLIst();
        for (int i = 0; i < listOfOpenCards.size(); i++) {

            boolList.add(i, putInSortedList(listOfOpenCards.get(i))); //Lägger in kort från listan på de sorterade högarna
            //Santidigt som en arrray med booleaner bildas som används senare

        }
        ischangesVar = isChanges(); //TODO Alla de metoder som returnerar en boolean för förändring, ändra denna variabel direkt ist.
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
    public ArrayList<PlayingCard> whtasUpLIst() {
        ArrayList<PlayingCard> localList = new ArrayList<>();
        for (int i = 0; i < cardDealList.length; i++) {
            if (cardDealList[i].isHandEmpty()) {
                continue;
            } else {
                int local = cardDealList[i].getHandSize();
                localList.add(cardDealList[i].getDeal().get(local - 1));
            }

        }
        return localList;
    }

    public boolean putInSortedList(PlayingCard p) {
        boolean returnBol = false;
        int local;


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

    public PlayingCard getSortedTopCard(int i) {
        return (PlayingCard) sortedCardDeal[i].getDeal().get(sortedCardDeal[i].getDeal().size() - 1);


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
                    System.out.print(cardHolder1[i][j] + "  "); //TODO Ixa tabbar ist
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
        openClosedCards(); //TODO Denna metod borde inte anropas här


        /*for (int i = 0; i < cardDealList.length; i++) { //TODO Detta moment kan vara onödigt eftersedan jag lagt till metod som öppnar kort
            for (int j = cardDealList[i].getDeal().size() - 1; j >= 0; j--) {
                if (cardDealList[i].getDeal().get(j).getValue() != 0) {
                    cardDealList[i].getDeal().get(j).revealCard();
                    break;
                }
            }*/


        for (int i = 0; i < longestList; i++) {
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
            //int x = 7 - cardDealList[i].getHandSize();
            //for (int j = 0; j <= x; j++) {

            //cardDealList[i].getDeal().add(new PlayingCard(Suit.Clubs, 0)); //Ta bort padding i denna funktion

        }

        initSortedList();

        for (int j = 0; j < 4; j++) {
            sortedCardDeal[j] = new CardDeal();
            sortedCardDeal[j].drawFromDeck(patiensCardDeck, 0);
            sortedCardDeal[j].getDeal().add(arrayOfEmptyCards[j]);
        }

        whatsLeftCardDeal[0] = new CardDeal();
        whatsLeftCardDeal[1] = new CardDeal();
        int localSize = patiensCardDeck.cardsLeft();
        whatsLeftCardDeal[0].drawFromDeck(patiensCardDeck, localSize);


    }

    public void setNewPatiensGameDeck() {
        patiensCardDeck.newDeck();
        patiensCardDeck.shuffleDeck();
    }
}



