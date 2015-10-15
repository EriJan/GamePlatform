/**
 * Created by Janne on 14/10/15.
 */
public class GameTextUi implements GameUserInterface {
  @Override
  public void welcomeMessage(String message) {
    HelperMethods.printSlowly(message,10);
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
  public String userInputFromMenu(String... menuElements) {
    return HelperMethods.choseFromMenyNew(menuElements);
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
