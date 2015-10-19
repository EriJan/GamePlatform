import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by Janne on 15/10/15.
 */
public class BlackJackGraphicUi extends GameGraphicUi {

  // JFrame jframe;

  public BlackJackGraphicUi() {
    jframe = new JFrame("The Game");

    jframe.setLayout(new GridBagLayout());
    jframe.getContentPane().setBackground(Color.lightGray);

//    DeckHandler deck = new DeckHandler();
//    deck.newDeck();
//    deck.sortDeck();
//    int noCards = deck.cardsLeft();
//
//     for (int i = 0; i < noCards; i++) {
//       PlayingCard card = deck.drawTop();
//       card.revealCard();
//       JLabel label = new JLabel(card.toString(), card.getImage(), JLabel.CENTER);
//       //label.setPreferredSize(new Dimension(400,400));
//       label.setVerticalTextPosition(JLabel.BOTTOM);
//       label.setBorder(new EtchedBorder());
//       jframe.getContentPane().add(label);
//     }
//    jframe.pack();
    jframe.setVisible(true);
    jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


  }

  @Override
  public void displayGameState(CardGame game) {

    gameMessage(game.toString());
  }
}
