/**
 * Created by Janne on 14/10/15.
 */
public class GameTextUi implements GameUserInterface {
  @Override
  public void welcomeMessage(String message) {
    HelperMethods.printSlowly(message,10);
  }

  @Override
  public void waitStart() {
    System.out.print("waiting start....");
  }

  @Override
  public void waitEnd() {
    System.out.print("..waiting Ended");
  }

  @Override
  public String userInput(String queryString) {
    System.out.println(queryString);
    return HelperMethods.inPutFromNextLine();
  }

  @Override
  public int userInputInt(String queryString) {
    System.out.println(queryString);
    return HelperMethods.inPutFromNextInt();
  }

  @Override
  public boolean userInputBool(String queryString, String cond) {
    System.out.println(queryString);
    String replyStr = HelperMethods.inPutFromNextLine();
    char condChar = Character.toLowerCase(cond.charAt(0));
    char replyChar = Character.toLowerCase(replyStr.charAt(0));
    boolean condMet;
    if (replyChar == condChar) {
      condMet = true;
    } else {
      condMet = false;
    }
    return condMet;
  }


  @Override
  public int userInputFromMenu(String queryString, String... menuElements) {
    gameMessage(queryString);
    return HelperMethods.choseFromMenyInt(menuElements);
  }

  @Override
  public void displayGameState(CardGame game) {
    System.out.println(game.toString());
  }

  @Override
  public void gameMessage(String message) {
    System.out.println(message);
  }
}
