//package CardGame;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class the21  extends CardGame {
	  DeckHandler deck = new DeckHandler();
	  Player player1,player2;
	  the21Hands[] playersHands  ;
	private String val;

	  the21() {
		  deck.newDeck();
		  deck.shuffleDeck();
		  player2 = new Player("PC");
	  }

	  @Override
	  public void runGame() {
		  HelperMethods.printSlowly("\t\t* The  Game 21 *\n");
		  HelperMethods.printSlowly("* * * * * * * * * * * * * * * * * * *\n");
		  HelperMethods.printSlowly(" Player enter your name : ");
		  player1 = new Player(HelperMethods.inPutFromNextLine());
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
			  val="";
			  DrowCard[i] = playersHands[0].hand.get(i).getValue();
				System.out.format("%s, Your cards :\n%s", player1.getName(), playersHands[0].hand.get(i).toString());
				System.out.println("totally: " + playersHands[0].getHandValue(DrowCard));
				
				if(playersHands[0].isCardToTake(DrowCard) && !playersHands[0].isTjock(DrowCard)){
					System.out.println("vill du ha ett till ? (J/N): ");
					val = HelperMethods.inPutFromNextLine();//onskades flera kort?
				}
			  	if(!playersHands[0].isCardToTake(DrowCard) || playersHands[0].isTjock(DrowCard) || val.equalsIgnoreCase("N")){
				  endLoopForP1 = true;
			  }
			  i++;
			}
		  
		  if(!playersHands[0].isTjock(DrowCard)){
				
				System.out.format("Now it is %s: s turn %n", player2.getName());
				val = "J";
			   i = 0;
				while(playersHands[1].isCardToTake(DrowCard2) && !playersHands[1].isTjock(DrowCard2) && !val.equalsIgnoreCase("N") && !playersHands[1].isTjock(DrowCard2)){

					DrowCard2[i] = playersHands[1].hand.get(i).getValue();
					HelperMethods.printSlowly(String.format("%s, (Dealers cards) :%s\n", player2.getName(), playersHands[1].hand.get(i).toString()));
					
					System.out.println(" totally: " + playersHands[1].getHandValue(DrowCard2) );
					
					if(playersHands[1].getHandValue(DrowCard2)<=14 || (playersHands[1].getHandValue(DrowCard2) < playersHands[0].getHandValue(DrowCard2)) ){
						val = "J";								
					}else{
						val = "N";
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}
				
				HelperMethods.printSlowly(String.format("Result is: %n"));
				System.out.println(player1.getName() + " : " + playersHands[0].getHandValue(DrowCard));
				System.out.println(player2.getName() + " : " + playersHands[1].getHandValue(DrowCard2));
				}  
		  if ( (playersHands[0].isTjock(DrowCard))
					|| ((playersHands[0].getHandValue(DrowCard) < playersHands[1].getHandValue(DrowCard2))  && !playersHands[1].isTjock(DrowCard2)) ) {
				System.out.format("%s vann!! ", player2.getName());
				
			}else if ( (playersHands[1].getHandValue(DrowCard2) < playersHands[0].getHandValue(DrowCard) ) || playersHands[1].isTjock(DrowCard2) ) {
				System.out.format("%s vann!! ", player1.getName());
			  addToScoreFile(player1.getName(),playersHands[0].getHandValue(DrowCard));
			  addToScoreFile("Niklas 2tste2", 16);

			}
			else if ( (playersHands[1].getHandValue(DrowCard2) == playersHands[0].getHandValue(DrowCard) ) && ! playersHands[0].isTjock(DrowCard) ) {
				//System.out.format("OAVGJORT!! ");
				System.out.format("%s vann!! \n", player2.getName());
			}

		  printScoreboard21();
	  }

	public void addToScoreFile(String name, int score) {

		String oldData = HelperMethods.readFile("21.txt");
		StringBuilder sb = new StringBuilder(oldData);
		sb.append(score + "\t" + name + ";");
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

	public void  printScoreboard21() {
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

		HelperMethods.printSlowly("" +
				"\n**************************************\n");
		for (int i = 0 ; i< scArr.length; i++){
			if(!scArr[i].contentEquals(" ")) {
				HelperMethods.printSlowly("\tno" + (i+1) + ": " + scArr[i]+"\n");
			}
		}
		HelperMethods.printSlowly("" +
				"**************************************\n");

	}


	}
