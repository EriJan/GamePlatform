import javax.swing.*;
import java.util.List;
import java.util.ArrayList;


public class Patiens extends CardGame {
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
    GameUserInterface ui;
    PlayingCard moreThanOneKing;

    Patiens() {
        super();
        ui = new PatiensGraphicUi();
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


    @Override
    public void setUi(GameGraphicUi ui) {

    }

    @Override
    public void runGame() {


        boolean gameIsRunning = false;
        System.out.println("Hej och välkommen till Patiens!"); //TODO Översätt till engelska

        setNewPatiensGameDeck();
        setDeckOnField();
        while (!gameIsRunning) {

            longestList = lengthOfList();
            openClosedCards();
            paddingSet(longestList);
            printDeal();
            dePaddingSet();
            printMenu();
            int input = HelperMethods.inPutFromNextInt();


            switch (input) {
                case 1:
                    exekuteValidMoveAndRemoves();
                    putOpenCardFromWhatsLeft();
                    openNewCardFromWhatsLeft();

                    break;
                case 2:
                    if (whatsLeftCardDeal[0].getHandSize() == 0) {
                        System.out.println("Sårutskrift, korleken vänder sig");
                        turnWhatsLeftDeck();
                    } else {
                        dissOpenCard();
                    }

                    openNewCardFromWhatsLeft();
                    break;
                case 3:
                    ischangesVar = moveCards();

                    break;
                case 4:

                    //putOpenCardFromWhatsLeft();
                    break;
                case 5:
                    putKingInOpenIndex();

                    break;
                case 6:
                    turnWhatsLeftDeck();
                    openNewCardFromWhatsLeft();
                    break;

            }
            gameIsRunning = checkWin();
            //openClosedCards();

        }
    }
    public boolean checkWin(){
        boolean won = false;
        int i = 0;
        for (CardDeal deal : sortedCardDeal){
            if (deal.getLastCard().getValue()== 13){
                i++;
            }
            if (i == 4){
                won = true;
            }
        }return won;
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
        PlayingCard localp = getFaceUpCardFromDeck();
        boolean isChange = putInSortedList(localp);
        if (isChange) {
            whatsLeftCardDeal[1].getDeal().remove(localp);
        }
        return isChange;
    }

    public void dissOpenCard() {
        whatsLeftCardDeal[1].getDeal().add(whatsLeftCardDeal[0].getDeal().remove(0));
    }

    //
    public void openNewCardFromWhatsLeft() {

        whatsLeftCardDeal[1].getLastCard().revealCard();
    }

    //Öppnar alla de kort som ska vara öppna inför varje utskrift
    public void openClosedCards() {
        for (CardDeal deal : cardDealList) {
            if (deal.isHandEmpty()) {
                continue;
            } else if (!deal.getLastCard().isFaceUp()) {
                deal.getLastCard().revealCard();
            }
        }
        getFaceUpCardFromDeck().revealCard();
    }

    //Initierar de sorterade listorna genom att lägga kort med värdet 0
    public void initSortedList() {
        arrayOfEmptyCards[0] = new PlayingCard(Suit.Clubs, 0);
        arrayOfEmptyCards[1] = new PlayingCard(Suit.Diamonds, 0);
        arrayOfEmptyCards[2] = new PlayingCard(Suit.Hearts, 0);
        arrayOfEmptyCards[3] = new PlayingCard(Suit.Spades, 0);


    }

    //Metod som kontrollerar att det finns en ledig plats
    public boolean isEmptyIndex() {
        boolean isEmptyIndex = false;
        for (CardDeal cardDeal : cardDealList) {
            if (cardDeal.isHandEmpty()) {
                isEmptyIndex = true;
                return isEmptyIndex;
            }
        }
        return isEmptyIndex;
    }

    public ArrayList<PlayingCard> ifKingAndOpen(ArrayList<PlayingCard> p) {
        ArrayList<PlayingCard> q = new ArrayList<>();
        if (isEmptyIndex()) {
            for (PlayingCard card : p) {

                if (card.getValue() == 13) {
                    q.add(card);
                    return q;
                }
            }
        }
        return q;
    }

    public void addToIndex(List<PlayingCard> p) {
        for (CardDeal cardDeal : cardDealList) {
            if (cardDeal.isHandEmpty()) {
                cardDeal.getDeal().addAll(p);

                break;
            }
        }
    }

    public int numberOfKings(ArrayList<PlayingCard> p) {
        int i = 0;
        for (PlayingCard card : p) {
            if (card.getValue() == 13) {
                i++;
            }
        }
        return i;
    }

    public PlayingCard kingsToMove(ArrayList<PlayingCard> p) {
        PlayingCard q = null;
        for (PlayingCard card : p) {
            if (card.getValue() == 13) {
                for (CardDeal deal : cardDealList) {
                    if (deal.getCard(0).getValue() == 13) {
                        continue;
                    } else {
                        q = card;
                        return q;
                    }

                }
            }
        }return q;
    }

    //Metod som lägger en kung på en ledig plats
    public boolean putKingInOpenIndex() {
        boolean isChange = false;//TODO titta på en lösning som fungerar om det finns fler än en kung
        List<PlayingCard> localList1 = new ArrayList<>();
        ArrayList<PlayingCard> localList2 = listOfCardsToMove();

        if (!ifKingAndOpen(localList2).isEmpty()) {
            PlayingCard p = ifKingAndOpen(localList2).remove(0);
            if (getFaceUpCardFromDeck() == p) {
                localList1.add(p);
                addToIndex(localList1);
                whatsLeftCardDeal[1].getDeal().remove(p);
                return isChange;
            } else {
                if (numberOfKings(localList2) > 1) {
                p = kingsToMove(localList2);
            }
                for (CardDeal cardDeal : cardDealList) {
                    if (cardDeal.getDeal().contains(p)) {
                        if (cardDeal.getCard(0).getValue() == 13) {
                            continue;
                        }

                        for (int i = 0; i < cardDeal.getHandSize(); i++) {
                            if (cardDeal.getDeal().get(i) == p) {
                                localList1 = cardDeal.getDeal().subList(i, cardDeal.getHandSize());
                                addToIndex(localList1);
                                for (PlayingCard cardRemove : localList1) {
                                    cardDeal.getDeal().remove(cardRemove);
                                    isChange = true;
                                    return isChange;
                                }
                            }
                        }
                    }
                }
            }

        }
        return isChange;
    }

    //Metod som lägger till tomma kort för att kunna skriva ut spelet
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

    //Metod som lägger upp de kort som är möjliga att lägga upp från de osorterade högarna till de sorterade högarna
    public void putOpenCardInList() {
        PlayingCard p = getFaceUpCardFromDeck();

        int localInt = p.getValue();

        for (CardDeal deal : sortedCardDeal)
            if (p.getSuit() == deal.getDeal().get(0).getSuit() //TODO lägg denna jämförelse i en compair to metod
                    && localInt - 1 == deal.getDeal().get(0).getValue()) {

                deal.getDeal().add(p);
                whatsLeftCardDeal[1].getDeal().remove(p);
            }
    }

    //Metod som retrunerar det kort som är synligt på den hög som ligger ouppvänd
    public PlayingCard getFaceUpCardFromDeck() {
        int local = whatsLeftCardDeal[1].getHandSize() - 1;
        return whatsLeftCardDeal[1].getDeal().get(local);

    }

    /*public void findCardsAndAddToNewList(PlayingCard p, PlayingCard q) {
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
*/
    //Metod som kontrollerar om två kort
    public ArrayList<PlayingCard> ifCardsValueMach(PlayingCard from, PlayingCard to) {
        ArrayList<PlayingCard> localList = new ArrayList<>();

        if (from.getValue() + 1 == to.getValue()) {
            localList.add(from);
            localList.add(to);
            System.out.println(localList.get(0) + " " + localList.get(1));
            return localList;
        }
        return localList;
    }

    //Metod som k
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

    //Metod som lägger en hög av kort på en annan hög av kort
    public Boolean addOneToAnother(ArrayList<PlayingCard> validList) { //EN lista med två kort som är möjliga att lägga på varandra
        ArrayList<PlayingCard> localSubList = new ArrayList<>(); //TODO dela upp denna i flera metoder
        boolean validMoveExe = false;
        int local; //Längden på en osorterad lista
        PlayingCard local1 = validList.get(1);
        PlayingCard local2 = validList.get(0);
        if (local1.getValue() - 1 == local2.getValue()) {
            if (getFaceUpCardFromDeck() == local2) {
                localSubList.add(local2);
                whatsLeftCardDeal[1].getDeal().remove(local2);
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
        for (PlayingCard fromCard : localFromCheck) {
            for (PlayingCard toCard : localToCheck) {
                awnserFromCheckValue.addAll(ifCardsValueMach(fromCard, toCard));

                if (!awnserFromCheckValue.isEmpty()) {
                    awnserFromCheckSuit.addAll(ifCardsSuitMach(awnserFromCheckValue));
                    if (!awnserFromCheckSuit.isEmpty()) {
                        addOneToAnother(awnserFromCheckSuit);
                        test = true;
                        return test;
                    }
                } else if (awnserFromCheckValue.isEmpty()) {
                    continue;
                }
            }
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
                localList.add(cardDealList[i].getDeal().get(position));
            }
        }
        localList.add(getFaceUpCardFromDeck());
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

    //Metod som lägger upp kort på de sorterade högarna
    public boolean putInSortedList(PlayingCard p) {
        boolean returnBol = false;
        int local1;
        int local;


        if (p.getSuit() == Suit.Clubs) {
            local = sortedCardDeal[0].getLastCard().getValue() + 1;

            if (p.getValue() == local) {
                sortedCardDeal[0].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Diamonds) {

            local = sortedCardDeal[1].getLastCard().getValue() + 1;


            if (p.getValue() == local) {
                sortedCardDeal[1].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Hearts) {

            local = sortedCardDeal[2].getLastCard().getValue() + 1;

            if ((p.getValue() == local)) {
                sortedCardDeal[2].getDeal().add(p);
                removeObeject(p);
                returnBol = true;
            }
        } else if (p.getSuit() == Suit.Spades) {

            local = sortedCardDeal[3].getLastCard().getValue() + 1;

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
        PlayingCard p;
        p = sortedCardDeal[i].getLastCard();
        return p;


    }

    //Metod som ritar ut spelplanen
    public void printDeal() {
        getSortedTopCard(0).revealCard();
        getSortedTopCard(1).revealCard();
        getSortedTopCard(2).revealCard();
        getSortedTopCard(3).revealCard();
        PlayingCard localP;

        String[][] cardHolder1 = {{""}, {" __ "}, {"|  |"}, {"|  |"}, {" == "}};//TODO ta bort
        String[][] cardHolder2 = {{""}, {"   "}, {"" + getFaceUpCardFromDeck() + " "}, {"   "}, {"   "}};
        String[][] cardHolder3 = {{""}, {" __ "}, {"|" + getSortedTopCard(0) + "|"}, {"|  |"}, {" == "}};
        String[][] cardHolder4 = {{""}, {" __ "}, {"|" + getSortedTopCard(1) + "|"}, {"|  |"}, {" == "}};
        String[][] cardHolder5 = {{""}, {" __ "}, {"|" + getSortedTopCard(2) + "|"}, {"|  |"}, {" == "}};
        String[][] cardHolder6 = {{""}, {" __ "}, {"|" + getSortedTopCard(3) + "|"}, {"|  |"}, {" == "}};

        ///String[] frontNumber = {"  1  ", "  2  ", "  \u2666  ", "   \u2663  ", "   \u2665  ", "  \u2660  "};


        for (int i = 0; i < cardHolder1.length; i++) {
            for (int j = 0; j < 1; j++) {
                // if (i == 0) {
                //for (int k = 0; k < frontNumber.length; k++) {
                //System.out.print(frontNumber[k]);
                // }
                System.out.print(cardHolder1[i][j] + "\t");
                System.out.print(cardHolder2[i][j] + "\t\t");
                System.out.print(cardHolder3[i][j] + "\t");
                System.out.print(cardHolder4[i][j] + "\t");
                System.out.print(cardHolder5[i][j] + "\t");
                System.out.print(cardHolder6[i][j] + "\t");
                System.out.print("\n");
            }
        }

        System.out.print("\n");

        for (int i = 0; i < longestList; i++) {
            System.out.print(cardDealList[0].getDeal().get(i) + "\t\t");
            System.out.print(cardDealList[1].getDeal().get(i) + "\t\t");
            System.out.print(cardDealList[2].getDeal().get(i) + "\t\t");
            System.out.print(cardDealList[3].getDeal().get(i) + "\t\t");
            System.out.print(cardDealList[4].getDeal().get(i) + "\t\t");
            System.out.print(cardDealList[5].getDeal().get(i) + "\t\t");
            System.out.print(cardDealList[6].getDeal().get(i) + "");

            System.out.print("\n");
        }


    }

    //Metod som lägger upp korten på bordet
    public void setDeckOnField() {

        for (int i = 0; i < cardDealList.length; i++) {
            cardDealList[i] = new CardDeal();

            cardDealList[i].drawFromDeck(patiensCardDeck, i + 1);
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
        whatsLeftCardDeal[1].getDeal().add(whatsLeftCardDeal[0].getDeal().remove(0));
        whatsLeftCardDeal[1].getDeal().add(0, new PlayingCard(Suit.Clubs, 0));
        whatsLeftCardDeal[1].getLastCard().revealCard();


    }

    //Metod som skapar en ny kortlek och blandar den
    public void setNewPatiensGameDeck() {
        patiensCardDeck.newDeck();
        patiensCardDeck.shuffleDeck();
    }
}



