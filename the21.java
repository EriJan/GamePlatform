//package CardGame;


import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Niklas on 19/10/15.
 */

public class the21  extends CardGame implements Runnable{
  DeckHandler deck = new DeckHandler();
  Player player1,player2;
  the21Hands[] playersHands  ;
  private String sel,m;
  private GameUserInterface ui;
  public static final GameId GAME_ID = GameId.the21;

  int [] DrowCard = new int[] {0,0,0};
  int [] DrowCard2 = new int[] {0,0,0};
  ImageIcon[] DrowCardImg = new ImageIcon[] {new ImageIcon("null"),new ImageIcon("null"),new ImageIcon("null")};
  ImageIcon [] DrowCard2Img = new ImageIcon[] {new ImageIcon("null"),new ImageIcon("null"),new ImageIcon("null")};

  public ArrayList<Integer> getDrowCard() {
    ArrayList<Integer> UserInt = new ArrayList<>();
    for (int pos : DrowCard){
      if(pos != 0 ){
        UserInt.add(pos);
      }
    }
    return UserInt;
  }



  public ArrayList<Integer> getDrowCard2() {
    ArrayList<Integer> pCInt = new ArrayList<>();
    for (int pos : DrowCard2){
      if(pos != 0 ){
        pCInt.add(pos);
      }
    }
    return pCInt;
  }

  public ArrayList<ImageIcon> getDrowCardImg() {
    ArrayList<ImageIcon> userImg = new ArrayList<>();

    for (int i = 0 ;i<DrowCard.length; i++){
      if(DrowCard[i] != 0 ){
        userImg.add(DrowCardImg[i]);
      }
      else{
        userImg.add( new ImageIcon(this.getClass().getResource("cardicons/b2fv.png")) ); //turn downcard img
      }

    }

    return userImg;
  }

  public ArrayList<ImageIcon> getDrowCard2Img() {
    ArrayList<ImageIcon> pCImg = new ArrayList<>();

    for (int i = 0 ;i<DrowCard2.length; i++){
      if(DrowCard2[i] != 0 ){
        pCImg.add(DrowCard2Img[i]);
      }
      else{
        pCImg.add( new ImageIcon(this.getClass().getResource("cardicons/b2fv.png")) ); //turn downcard img
      }

    }


    return pCImg;
  }

  the21() {
    ui = new the21GraphicUi();
    deck.newDeck();
    deck.shuffleDeck();
    player2 = new Player("PC", GAME_ID);
  }

  @Override
  public void runGame() {
    //Gui21 g = new Gui21();
    PrintWelcome();
//int noPos = ui.userInputInt("Playername?");

    player1 = getUserInputForName(); //MVC
    //player1 = new Player(HelperMethods.inPutFromNextLine());


    playersHands = new the21Hands[2];
    for (int i = 0; i< playersHands.length ; i++ ){
      playersHands[i] = new the21Hands();
    }
    playersHands[0].drawFromDeck(deck, 3);
    playersHands[0].revealHand();
    playersHands[1].drawFromDeck(deck, 3);
    playersHands[1].revealHand();


    boolean endLoopForP1 = false;
    int i = 0;
    while(!endLoopForP1){
      sel ="";
      DrowCard[i] = getValueDrownCard(i);
      DrowCardImg[i] = getValueDrownCardImg(i);

      m = String.format("%s, Your cards :\n%s" +
          "%n totally: %s%n" +
          " > Add one more?", player1.getName(), playersHands[0].hand.get(i).toString(),playersHands[0].getHandValue(DrowCard));
      // printPlayersNameAndCard(i);
      // printTotallyOnPlayersHandDrown("totally: " + playersHands[0].getHandValue(DrowCard));


      if(playersHands[0].isCardToTake(DrowCard) && !playersHands[0].isTjock(DrowCard)){
        //DoYouWantANewCardReveald("vill du ha ett till ? (J/N): ");

        endLoopForP1 = !getUserInputForNewCard(m,DrowCardImg[i]); //MVC

        //sel = newCardAnswer();//onskades flera kort?
        if(endLoopForP1){
          sel="N";
        }
      }
      if(!playersHands[0].isCardToTake(DrowCard) || playersHands[0].isTjock(DrowCard) || sel.equalsIgnoreCase("N")){
        endLoopForP1 = true;

        m = String.format("%s, Your cards :\n%s" +
            "%n totally: %s%n" +
            "", player1.getName(), playersHands[0].hand.get(i).toString(),playersHands[0].getHandValue(DrowCard));

        informUserInfoFromPCsTurn(m); //MVC

      }
      i++;
    }


    if(!playersHands[0].isTjock(DrowCard)){

      m = String.format("Now it is %s: s turn %n", player2.getName());
      //ui.gameMessage(m);
      informUserInfoFromPCsTurn(m); //MVC
      sel = "J";
      i = 0;
      while(playersHands[1].isCardToTake(DrowCard2) && !playersHands[1].isTjock(DrowCard2) && !sel.equalsIgnoreCase("N") && !playersHands[1].isTjock(DrowCard2)){

        DrowCard2[i] = playersHands[1].hand.get(i).getValue();
        DrowCard2Img[i] = playersHands[1].hand.get(i).getImage();
        //HelperMethods.printSlowly(String.format("%s, (Dealers cards) :%s\n", player2.getName(), playersHands[1].hand.get(i).toString()));
        // ui.gameMessage(
        informUserInfoFromPCsTurn(String.format("%s, (Dealers cards) :%s%n" +
            " Totally: %s ", player2.getName(), playersHands[1].hand.get(i).toString(),playersHands[1].getHandValue(DrowCard2)), DrowCard2Img[i]);


        //printTotallyOnPlayersHandDrown(" totally: " + playersHands[1].getHandValue(DrowCard2));

        if(playersHands[1].getHandValue(DrowCard2)<=14 || (playersHands[1].getHandValue(DrowCard2) < playersHands[0].getHandValue(DrowCard2)) ){
          sel = "J";
        }else{
          sel = "N";
        }
        MVCWaitStart();
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        MVCWaitEnd();
        i++;
      }

      HelperMethods.printSlowly(String.format("Result is: %n"));
      printPlayerXresult(player1.getName() + " : " + playersHands[0].getHandValue(DrowCard));
      printPlayerXresult(player2.getName() + " : " + playersHands[1].getHandValue(DrowCard2));
    }
    StringBuilder r = new StringBuilder(String.format("%s : %s %n %s : %s %n",player1.getName(),playersHands[0].getHandValue(DrowCard),player2.getName(), playersHands[1].getHandValue(DrowCard2)) );

    if ( (playersHands[0].isTjock(DrowCard))
        || ((playersHands[0].getHandValue(DrowCard) < playersHands[1].getHandValue(DrowCard2))  && !playersHands[1].isTjock(DrowCard2)) ) {

      r.append(String.format("%s vann!! ", player2.getName()));
      //printPlayerName("%s vann!! ", player2.getName());

    }else if ( (playersHands[1].getHandValue(DrowCard2) < playersHands[0].getHandValue(DrowCard) ) || playersHands[1].isTjock(DrowCard2) ) {
      //printPlayerName("%s vann!! ", player1.getName());
      r.append(String.format("%s vann!! ", player1.getName()));
      HelperMethods.Beep();
      addToScoreFile(player1.getName(),playersHands[0].getHandValue(DrowCard));


    }
    else if ( (playersHands[1].getHandValue(DrowCard2) == playersHands[0].getHandValue(DrowCard) ) && ! playersHands[0].isTjock(DrowCard) ) {
      //System.out.format("OAVGJORT!! ");
      //printPlayerName("%s vann!! \n", player2.getName());
      r.append(String.format("%s vann!! ", player2.getName()));
    }
    informUserInfoFromPCsTurn(r.toString());

    //printScoreboard21();
    printScoreboard21file(toString());
  }

  private void MVCWaitStart() {
    ui.waitStart();
  }
  private void MVCWaitEnd() {
    ui.waitEnd();
  }

  private void informUserInfoFromPCsTurn(String m) {
    ui.displayGameState(this);
    ui.gameMessage(m);


  }

  private void informUserInfoFromPCsTurn(String m, ImageIcon imageIcon) {
    ui.displayGameState(this);
    ui.gameMessage(m);

  }

  private boolean getUserInputForNewCard(String m, ImageIcon imageIcon) {

    ui.displayGameState(this);
    return ui.userInputBool(m,"Y/N");
  }

  public String getM() {
    return m;
  }


  private Player getUserInputForName() {
    return getNewPlayer(ui.userInput("Type your name"));
  }

  @Override
  public void setUi(GameGraphicUi ui) {

  }

  private void printPlayerXresult(String x) {
    System.out.println(x);
  }

  private void printScoreboard21file(String x) {
    System.out.println(x);
  }

  private void printPlayerName(String format, String name) {
    System.out.format(format, name);
  }

  private String newCardAnswer() {
    return HelperMethods.inPutFromNextLine();
  }

  private void DoYouWantANewCardReveald(String x) {
    System.out.println(x);
  }

  private void printTotallyOnPlayersHandDrown(String x) {
    System.out.println(x);
  }

  private void printPlayersNameAndCard(int i) {
    // ui.userInputBool("");
    //String.format("%s, Your cards :\n%s", player1.getName(), playersHands[0].hand.get(i).toString()));
    //System.out.format("%s, Your cards :\n%s", player1.getName(), playersHands[0].hand.get(i).toString());
  }

  private int getValueDrownCard(int i) {
    return playersHands[0].hand.get(i).getValue();
  }

  private ImageIcon getValueDrownCardImg(int i) {
    return playersHands[0].hand.get(i).getImage();
  }

  private int getValueDrownCard2(int i) {
    return playersHands[1].hand.get(i).getValue();
  }

  private ImageIcon getValueDrownCard2Img(int i) {
    return playersHands[1].hand.get(i).getImage();
  }

  private Player getNewPlayer(String name) {
    return new Player(name, GAME_ID);
  }

  private void PrintWelcome() {
    HelperMethods.printSlowly("\t\t* The  Game 21 *\n");
    HelperMethods.printSlowly("* * * * * * * * * * * * * * * * * * *\n");
    //HelperMethods.printSlowly(" Player enter your name : ");
  }

  private void addToScoreFile(String name, int score) {

    String oldData = HelperMethods.readFile("21.txt");
    StringBuilder sb = new StringBuilder(oldData);
    Date date = new Date();
    SimpleDateFormat sdf;
    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String stringDate = sdf.format(date);
    sb.append(score + "@" + name + "@" + stringDate + ";"); // "\t"
    String scoreArray[] = sb.toString().split(";");
    List<String> scoreList = Arrays.asList(scoreArray);

    //Collections.sort(scoreList);
    //Collections.reverse(scoreList);
    Collections.sort(scoreList, new Comparator<String>() {

      @Override
      public int compare(String arg0, String arg1) {
        return  arg1.compareTo(arg0);
      }
    });
    sb = new StringBuilder("");
    for(String user: scoreList){
      sb.append(user + ";");
    }
    String newData = sb.toString();
    try {
      HelperMethods.writeFile("21.txt",newData);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void  printScoreboard21() {
    String data = HelperMethods.readFile("21.txt");

    String scoreArray[] = data.toString().split(";");
    List<String> scoreList = Arrays.asList(scoreArray);
    //Collections.reverse(scoreList);
    Collections.sort(scoreList, new Comparator<String>() {

      @Override
      public int compare(String arg0, String arg1) {
        return  arg1.compareTo(arg0);
      }
    });

    String[] scArr = new String[scoreList.size()];
    scArr = scoreList.toArray(scArr);
    GuiHighScore gHS = new GuiHighScore(scArr);
    HelperMethods.printSlowly("" +
        "\n" +
        "********************* -HighScore:s - *******************\n");
    if(scoreArray.length < 1){
      System.out.println("\tEmty, it's still a chance to be no1..\n" +
          "\t\"There should be only one..\"");
    }else{
      for (int i = 0 ; i< scArr.length; i++){
        HelperMethods.printSlowly("\tno" + (i+1) + ": " + scArr[i]+"\n");
      }
    }
    HelperMethods.printSlowly("" +
        "********************************************************\n");

  }

  public String  toString() {
    String data = HelperMethods.readFile("21.txt");

    String scoreArray[] = data.toString().split(";");

    List<String> scoreList = Arrays.asList(scoreArray);
    //Collections.reverse(scoreList);
    Collections.sort(scoreList, new Comparator<String>() {

      @Override
      public int compare(String arg0, String arg1) {
        return arg1.compareTo(arg0);
      }
    });

    String[] scArr = new String[scoreList.size()];
    scArr = scoreList.toArray(scArr);//now it has " in it and is not a 2D array
    String[][] scArr2D = new String[scoreList.size()][scoreList.size() * 3];

    for (int i = 0; i < scoreList.size(); i++)
      scArr2D[i] = scArr[i].toString().split("@");

    //for (int i = 0; i < scoreList.size(); i++)
    //for (int j = 0; j < (scoreList.size()*3); j++)
    //	scArr2D[i][j] = );


    GuiHighScore gHS = new GuiHighScore(scArr2D);


    StringBuilder str = new StringBuilder("");
    str.append("" +
        "\n" +
        "********************* -HighScore:s - *******************\n");
    if(scoreArray.length < 1){
      str.append("\tEmty, it's still a chance to be no1..\n" +
          "\t\"There should be only one..\"");
    }else{
      for (int i = 0 ; i< scArr.length; i++){
        str.append("\tno" + (i + 1) + ": " + scArr[i] + "\n");
      }
    }
    str.append("" +
        "********************************************************\n");

    return str.toString();

  }

  @Override
  public void run() {
    runGame();
  }
}
/*
 class Gui21 extends JFrame {
	 JPanel jp = new JPanel();
	 JButton jbY = new JButton();
	 JButton jbN = new JButton();
	 JLabel label;
	 JLabel[] jLPlayerDrown1;
	 JLabel[] jLPlayerDrown2;
	 JLabel jLPlayer1Result;
	 JLabel jLPlayer2Result;

	 public Gui21() {
		 setTitle("TheGame21");
		 setVisible(true);
		 setSize(310, 290);
		 //setDefaultCloseOperation(EXIT_ON_CLOSE);
		 jp.setBackground(Color.GREEN);
		 Box boxP2 = Box.createVerticalBox();
		 Box boxP1 = Box.createVerticalBox();
		 Box boxP1Button = Box.createVerticalBox();
		 add(boxP2);
		 add(boxP1);
		 add(boxP1Button);

		 label = new JLabel("Get new card: ");
		 Font f = label.getFont();
		 label.setFont(f.deriveFont(f.getStyle() | Font.ITALIC));
		 boxP1Button.add(label);
		 jbY.addActionListener(new ActionListener() { //en inner class för detta..
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 System.out.println("klick Y");

			 }
		 });
		 jbN.addActionListener(new ActionListener() { //en inner class för detta..
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 System.out.println("klick N");

			 }
		 });
		 boxP1Button.add(jbN);
		 boxP1Button.add(jbY);

		 jLPlayerDrown2 = new JLabel[3];
		 for (int i = 0 ; i<jLPlayerDrown2.length ; i++){
			 jLPlayerDrown2[i] = new JLabel("kort"+i);
			 final int ClickPointer = i; //MYCKET VIKTIG!

			 jLPlayerDrown2[i].addMouseListener(new MouseAdapter() {
				 private boolean isMarked;

				 @Override
				 public void mouseClicked(MouseEvent diceClickID) {
					 //onMouseClicked(ClickID);

					 //isMarked = optionsMaker(ClickPointer, isMarked);
					 System.out.println("klick kort" + ClickPointer);

				 }

			 });
			 //bselects[i].setBounds(20, 55+(i*(48+10)), 48, 48);

			 //jp.add(bselects[i], BorderLayout.SOUTH);
			 boxP2.add(jLPlayerDrown2[i]);
		 }

		 jLPlayerDrown1 = new JLabel[3];
		 for (int i = 0 ; i<jLPlayerDrown1.length ; i++){
			 jLPlayerDrown1[i] = new JLabel("kort"+i);
			 final int ClickPointer = i; //MYCKET VIKTIG!

			 jLPlayerDrown1[i].addMouseListener(new MouseAdapter() {
				 private boolean isMarked;

				 @Override
				 public void mouseClicked(MouseEvent diceClickID) {
					 //onMouseClicked(ClickID);

					 //isMarked = optionsMaker(ClickPointer, isMarked);
					 System.out.println("klick kort" + ClickPointer);

				 }

			 });
			 //bselects[i].setBounds(20, 55+(i*(48+10)), 48, 48);

			 //jp.add(bselects[i], BorderLayout.SOUTH);
			 boxP1.add(jLPlayerDrown1[i]);
		 }

		 add(jp);
		 validate();

	 }
}
*/