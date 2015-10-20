import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Janne on 15/10/15.
 */
public class BlackJackGraphicUi extends GameGraphicUi {

  public BlackJackGraphicUi() {
    jframe = new JFrame("Black Jack");
    jframe.setLayout(new GridBagLayout());
    jframe.getContentPane().setBackground(Color.green);
    jframe.setVisible(false);
    jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  /**
   * Basicly a way to display several card deals with the use of
   * SpringLayout. If the same side of the cards are used as reference,
   * west in this case, you can model overlapping cards.
   * display order is last added on top. Seems a bit messy to fix the display
   * order, but anot really necessary anyway.
   * TODO make better names for i and j
   * @param game
   */
  @Override
  public void displayGameState(CardGame game) {
    jframe.getContentPane().removeAll();

    BlackJack bj = (BlackJack) game;
    BlackJackHand house = bj.getHouse();
    ArrayList<BlackJackHand> positions = bj.getPositions();

    JPanel housePanel = new JPanel();
    SpringLayout houseLayout = new SpringLayout();
    housePanel.setLayout(houseLayout);

    GridBagConstraints c = new GridBagConstraints();
    int halfTableSize = positions.size()/2;
    c.gridx = halfTableSize;
    c.gridy = 0;
    jframe.add(housePanel,c);

    ArrayList<PlayingCard> houseCards = (ArrayList<PlayingCard>) house.getDeal();
    JLabel currentLabel = null;
    JLabel lastLabel = null;
    int j = 0;
    for(PlayingCard card : houseCards) {
      currentLabel = new JLabel(card.getImage());
      housePanel.add(currentLabel);

      if (j == 0) {
        houseLayout.putConstraint(SpringLayout.WEST, currentLabel,
                5,SpringLayout.WEST,housePanel);
        houseLayout.putConstraint(SpringLayout.NORTH, currentLabel,
                5,SpringLayout.NORTH,housePanel);
      } else {
        houseLayout.putConstraint(SpringLayout.WEST, currentLabel,
                25,SpringLayout.WEST,lastLabel);
        houseLayout.putConstraint(SpringLayout.NORTH, currentLabel,
                0,SpringLayout.NORTH,lastLabel);
      }

      lastLabel = currentLabel;
      j++;
    }

    houseLayout.putConstraint(SpringLayout.EAST, housePanel,
            5,SpringLayout.EAST,lastLabel);
    houseLayout.putConstraint(SpringLayout.SOUTH, housePanel,
            5,SpringLayout.SOUTH,lastLabel);


    GridBagConstraints[] gbCons = new GridBagConstraints[positions.size()];

    int i = 0;
    for(BlackJackHand pos : positions) {
      ArrayList<PlayingCard> posCards = (ArrayList<PlayingCard>) pos.getDeal();

      JPanel localPanel = new JPanel();

      SpringLayout localLayout = new SpringLayout();
      localPanel.setLayout(localLayout);

      gbCons[i] = new GridBagConstraints();
      gbCons[i].gridx = i;
      gbCons[i].gridy = 1;
      jframe.add(localPanel,gbCons[i]);

      j = 0;

      for(PlayingCard card : posCards) {

        currentLabel = new JLabel(card.getImage());
        localPanel.add(currentLabel);

        if (j == 0) {
          localLayout.putConstraint(SpringLayout.WEST, currentLabel,
                  5,SpringLayout.WEST,localPanel);
          localLayout.putConstraint(SpringLayout.NORTH, currentLabel,
                  5,SpringLayout.NORTH,localPanel);
        } else {
          localLayout.putConstraint(SpringLayout.WEST, currentLabel,
                  25,SpringLayout.WEST,lastLabel);
          localLayout.putConstraint(SpringLayout.NORTH, currentLabel,
                  0,SpringLayout.NORTH,lastLabel);
        }

        lastLabel = currentLabel;
        j++;
      }
      localLayout.putConstraint(SpringLayout.EAST, localPanel,
              5,SpringLayout.EAST,lastLabel);
      localLayout.putConstraint(SpringLayout.SOUTH, localPanel,
              5,SpringLayout.SOUTH,lastLabel);
      i++;
    }

    jframe.pack();
    jframe.setVisible(true);
  }
}
