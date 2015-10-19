//package CardGame;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class the21  extends CardGame {
	  DeckHandler deck = new DeckHandler();
	  Player player1,player2;
	  the21Hands[] playersHands  ;
	private String sel;

	  the21() {
		  deck.newDeck();
		  deck.shuffleDeck();
		  player2 = new Player("PC");
	  }

	  @Override
	  public void runGame() {
		  //Gui21 g = new Gui21();
		  PrintWelcome();

		  player1 = getNewPlayer(HelperMethods.inPutFromNextLine());
		  //player1 = new Player(HelperMethods.inPutFromNextLine());
		   int [] DrowCard = new int[] {0,0,0};
		  int [] DrowCard2 = new int[] {0,0,0};
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
			  printPlayersNameAndCard(i);
			  printTotallyOnPlayersHandDrown("totally: " + playersHands[0].getHandValue(DrowCard));

			  if(playersHands[0].isCardToTake(DrowCard) && !playersHands[0].isTjock(DrowCard)){
				  DoYouWantANewCardReveald("vill du ha ett till ? (J/N): ");
				  sel = newCardAnswer();//onskades flera kort?
				}
			  	if(!playersHands[0].isCardToTake(DrowCard) || playersHands[0].isTjock(DrowCard) || sel.equalsIgnoreCase("N")){
				  endLoopForP1 = true;
			  }
			  i++;
			}
		  
		  if(!playersHands[0].isTjock(DrowCard)){

			  printPlayerName("Now it is %s: s turn %n", player2.getName());
			  sel = "J";
			   i = 0;
				while(playersHands[1].isCardToTake(DrowCard2) && !playersHands[1].isTjock(DrowCard2) && !sel.equalsIgnoreCase("N") && !playersHands[1].isTjock(DrowCard2)){

					DrowCard2[i] = playersHands[1].hand.get(i).getValue();
					HelperMethods.printSlowly(String.format("%s, (Dealers cards) :%s\n", player2.getName(), playersHands[1].hand.get(i).toString()));

					printTotallyOnPlayersHandDrown(" totally: " + playersHands[1].getHandValue(DrowCard2));
					
					if(playersHands[1].getHandValue(DrowCard2)<=14 || (playersHands[1].getHandValue(DrowCard2) < playersHands[0].getHandValue(DrowCard2)) ){
						sel = "J";
					}else{
						sel = "N";
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}
				
				HelperMethods.printSlowly(String.format("Result is: %n"));
			  printPlayerXresult(player1.getName() + " : " + playersHands[0].getHandValue(DrowCard));
			  printPlayerXresult(player2.getName() + " : " + playersHands[1].getHandValue(DrowCard2));
		  }
		  if ( (playersHands[0].isTjock(DrowCard))
					|| ((playersHands[0].getHandValue(DrowCard) < playersHands[1].getHandValue(DrowCard2))  && !playersHands[1].isTjock(DrowCard2)) ) {
			  printPlayerName("%s vann!! ", player2.getName());

		  }else if ( (playersHands[1].getHandValue(DrowCard2) < playersHands[0].getHandValue(DrowCard) ) || playersHands[1].isTjock(DrowCard2) ) {
			  printPlayerName("%s vann!! ", player1.getName());
			  addToScoreFile(player1.getName(),playersHands[0].getHandValue(DrowCard));


			}
			else if ( (playersHands[1].getHandValue(DrowCard2) == playersHands[0].getHandValue(DrowCard) ) && ! playersHands[0].isTjock(DrowCard) ) {
				//System.out.format("OAVGJORT!! ");
			  printPlayerName("%s vann!! \n", player2.getName());
		  }

		  //printScoreboard21();
		  printScoreboard21file(toString());
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
		System.out.format("%s, Your cards :\n%s", player1.getName(), playersHands[0].hand.get(i).toString());
	}

	private int getValueDrownCard(int i) {
		return playersHands[0].hand.get(i).getValue();
	}

	private Player getNewPlayer(String name) {
		return new Player(name);
	}

	private void PrintWelcome() {
		HelperMethods.printSlowly("\t\t* The  Game 21 *\n");
		HelperMethods.printSlowly("* * * * * * * * * * * * * * * * * * *\n");
		HelperMethods.printSlowly(" Player enter your name : ");
	}

	private void addToScoreFile(String name, int score) {

		String oldData = HelperMethods.readFile("21.txt");
		StringBuilder sb = new StringBuilder(oldData);
		Date date = new Date();
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
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