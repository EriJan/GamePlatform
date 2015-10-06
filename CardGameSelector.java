import java.util.Scanner;

public class CardGameSelector {
  public static void main(String[] args) {
    CardGame theGame;

    Scanner userInput = new Scanner(System.in);

    System.out.println("Lets play cards!");
    boolean pgmEnd = false;
    while (!pgmEnd) {
      System.out.println("What game do you want to play?");
      String inputStr = userInput.nextLine();
      if ( inputStr.isEmpty() ) {
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
        System.out.println("the game 21 object soon running");
        theGame.runGame();
      }
    }
  }
}
