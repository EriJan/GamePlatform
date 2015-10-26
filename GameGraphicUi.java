import javax.swing.*;
import java.awt.*;

/**
 * Created by Janne on 15/10/15.
 */
public abstract class GameGraphicUi implements GameUserInterface {

  protected JFrame jframe;

  @Override
  public void waitStart() {
    jframe.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
  }

  @Override
  public void waitEnd() {

    jframe.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }

  @Override
  public void welcomeMessage(String message) {
    HelperMethods.printSlowly(message, 10);
  }


  @Override
  public void gameMessage(String message) {
    JOptionPane.showMessageDialog(jframe, message, "Game Message",
            JOptionPane.PLAIN_MESSAGE);

  }


  @Override
  public String userInput(String queryString) {
    String retStr = (String) JOptionPane.showInputDialog(queryString);
    retStr = (retStr == null) ? "" : retStr;
    return retStr;
  }

  @Override
  public int userInputInt(String queryString) {
    boolean isDigit = false;
    int returnInt = 0;

    while (!isDigit) {
      String retStr = (String) JOptionPane.showInputDialog(queryString);
      retStr = (retStr == null) ? "" : retStr;
      if (!retStr.isEmpty()) {
        char check = retStr.charAt(0);
        if (Character.isDigit(check)) {
          returnInt = Integer.parseInt(retStr);
          isDigit = true;
        } else {
          gameMessage("Något har blivit fel. Tänk på att svara genom att skriva en siffra.");
        }
      } else {
        gameMessage("Något har blivit fel. Tänk på att svara genom att skriva en siffra.");
      }
    }
    return returnInt;
  }

  @Override
  public boolean userInputBool(String queryString, String cond) {
    int ans = JOptionPane.showConfirmDialog(jframe, queryString, "Game Query",
            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    boolean retAns = (ans == 0) ? true : false;
    return retAns;
  }

  @Override
  public int userInputFromMenu(String queryString, String... menuElements) {
    String retStr = (String) JOptionPane.showInputDialog(jframe,
            queryString, "Choose from menu", JOptionPane.PLAIN_MESSAGE,
            null, menuElements, menuElements[0]);
    retStr = (retStr == null) ? "" : retStr;
    int menuItemNo = 0;
    int i = 0;
    for (String str : menuElements) {
      if (retStr.equals(str)) {
        menuItemNo = i;
      }
      i++;
    }
    return menuItemNo;
  }



  @Override
  public abstract void displayGameState(CardGame game);



}