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

    jframe.setLayout(new GridLayout(2,5));
    jframe.getContentPane().setBackground(Color.lightGray);

    DeckHandler deck = new DeckHandler();
    deck.newDeck();
    deck.sortDeck();

    // int deckLength = deck.cardsLeft();

    for (int i = 0; i < 5; i++) {
      PlayingCard card = deck.drawTop();
      card.revealCard();
      JLabel label = new JLabel(card.toString(), JLabel.CENTER);
      label.setPreferredSize(new Dimension(100,100));
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
    // JOptionPane.showInternalMessageDialog(null, message);

    // JOptionPane.showMessageDialog(null, "alert", "alert", message);
    // JOptionPane.showMessageDialog("Eggs are not supposed to be green.");

    System.out.println(message);
  }
}
