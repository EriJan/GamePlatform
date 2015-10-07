import java.util.Scanner;

public class CardGameSelector {
  public static void main(String[] args) {
    CardGame theGame;

    Scanner userInput = new Scanner(System.in);

    Methods.printSlowly("Lets play !\n");
    boolean pgmEnd = false;
    while (!pgmEnd) {
      Methods.printSlowly("What game do you want to play?\n");

      String inputStr = Methods.choseFromMenyNew("Blackjack","Patiens","21","Quit");
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
  }
}
