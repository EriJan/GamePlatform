import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by Janne on 15/10/15.
 */
public class GameGraphicUi implements GameUserInterface {

  JFrame jframe;

  public GameGraphicUi() {
    jframe = new JFrame("The Game");

    jframe.setLayout(new GridLayout(4,13));
    jframe.getContentPane().setBackground(Color.lightGray);

    DeckHandler deck = new DeckHandler();
    deck.newDeck();
    deck.sortDeck();
    int noCards = deck.cardsLeft();

     for (int i = 0; i < noCards; i++) {
      PlayingCard card = deck.drawTop();
      card.revealCard();
      JLabel label = new JLabel(card.getImage());
      //label.setPreferredSize(new Dimension(400,400));
      label.setBorder(new EtchedBorder());
      jframe.getContentPane().add(label);
    }
    jframe.pack();
    jframe.setVisible(true);
    jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


  }

  @Override
  public void welcomeMessage(String message) {
    HelperMethods.printSlowly(message,10);
  }

  @Override
  public String userInput(String queryString) {
    String retStr = (String) JOptionPane.showInputDialog(queryString);
    return retStr;
  }

  @Override
  public int userInputInt(String queryString) {
    boolean isDigit = false;
    int returnInt = 0;

    while (!isDigit) {
      String retStr = (String) JOptionPane.showInputDialog(queryString);
      char check = retStr.charAt(0);
      if (Character.isDigit(check)) {
        returnInt = Integer.parseInt(retStr);
        isDigit = true;
      } else {
        gameMessage("Något har blivit fel. Tänk på att svara genom att skriva en siffra.");
      }
    }

    return returnInt;
  }

  @Override
  public boolean userInputBool(String queryString, String cond) {
    return false;
  }

  @Override
  public int userInputFromMenu(String... menuElements) {
    return HelperMethods.choseFromMenyInt(menuElements);
  }

  @Override
  public void displayGameState(CardGame game) {


    System.out.println(game.toString());
  }

  @Override
  public void gameMessage(String message) {

    JOptionPane.showMessageDialog(jframe,message);

  }
}
