/**
 * Created by Jan Eriksson on 14/10/15.
 */
public interface GameUserInterface {
  public void welcomeMessage(String message);
  public String userInput(String queryString);
  public int userInputInt(String queryString);
  public String userInputFromMenu(String... menuElements);
  public void displayGameState(CardGame game);
  public void gameMessage(String message);
}