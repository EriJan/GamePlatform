public class CardGameSelector implements Runnable {

  public void run() {
    CardGame theGame;

    //Scanner userInput = new Scanner(System.in);
    System.out.println(HelperMethods.introtext());
    System.out.println(HelperMethods.introtext2());
    System.out.println(HelperMethods.introtext3());

//      System.out.println("Stringtext TO ascii!! : ");
/*      try {
          System.out.println(HelperMethods.stringTOACCII("eNjoG"));
      } catch (Exception e) {
          e.printStackTrace();
      }
*/
    HelperMethods.printSlowly(HelperMethods.introtext() + "\nLets play !\n");
    boolean pgmEnd = false;
    while (!pgmEnd) {
      HelperMethods.printSlowly("What game do you want to play?\n");

      String inputStr = HelperMethods.choseFromMenyNew("Blackjack","Patiens","21","Quit");
      if ( inputStr.equals("Quit")) {
        pgmEnd = true;
        System.out.println("Program end.");
      } else if (inputStr.equals("Blackjack")){
        theGame = new BlackJack();
        theGame.runGame();
      } else if (inputStr.equals("Patiens")){
        theGame = new Patiens();
        theGame.runGame();
      }else if (inputStr.equals("21")) {
        System.out.println("the game 21");
        theGame = new the21();
        theGame.runGame();
      }
    }
    //System.exit(0);
  }


  public static void run(String Option) {
    CardGame theGame;

    //Scanner userInput = new Scanner(System.in);

    HelperMethods.printSlowly(HelperMethods.introtext() + "\nLets play !\n");

    String inputStr = Option;
    if ( inputStr.equals("Quit")) {
      System.exit(0);
      System.out.println("Program end.");
    } else if (inputStr.equals("Blackjack")){
      theGame = new BlackJack();
      theGame.runGame();
    } else if (inputStr.equals("Patiens")){
      theGame = new Patiens();
      theGame.runGame();
    }else if (inputStr.equals("21")) {
      System.out.println("the game 21");
      theGame = new the21();
      theGame.runGame();
    }else if (inputStr.equals("FizzAndBuzz")) {
      System.out.println("the game FizzAndBuzz");
      theGame = new FizzAndBuzz();
      theGame.runGame();
    }
  }
  //System.exit(0);

}
