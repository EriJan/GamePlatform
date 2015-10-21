import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Niklas on 19/10/15.
 */
public class the21GraphicUi extends GameGraphicUi {
  private JMenu mnOptions;
  private JMenuItem mntmSetNumberOf;
  private JMenuItem mntmAddplayer;
  private JMenuItem mntmStartGame;
  private JMenuItem mntmHighscore;
  JPanel jp = new JPanel();
  JButton jbY = new JButton();
  JButton jbN = new JButton();

  JLabel jL = new JLabel();
  JLabel jLNameP1 = new JLabel();
  JLabel jLNameP2 = new JLabel();
  JLabel jLResult1 = new JLabel();
  JLabel jLResult2 = new JLabel();
  JLabel jLTotal = new JLabel();
  Box upperBox = new Box(1);
  Box lowerBox = new Box(1);
  JPanel p1 = new JPanel(new BorderLayout());
  JPanel p2 = new JPanel(new BorderLayout());
  JPanel p3 = new JPanel(new BorderLayout());

  JLabel[] P1Cards;
  JLabel[] P2Cards;

  ArrayList<String> Names= new ArrayList<String>();

  // JFrame jframe;

  public the21GraphicUi() {
    jframe = new JFrame("The 21 Game");

    jLNameP2.setText("playerPC ");
    jLResult2.setText(" result P2 ");
    upperBox.add(jLNameP2);
    upperBox.add(jLResult2);
    p2.setToolTipText(" playerPC ");
    p2.add(upperBox);

    jLNameP1.setText(" playerPerson ");
    jLResult1.setText(" result P1 ");
    lowerBox.add(jLNameP1);
    lowerBox.add(jLResult1);
    p1.setToolTipText(" player/You ");
    p1.add(lowerBox);

    jLTotal.setText(" Result Total ");
    p3.add(jLTotal);
    jframe.add(p1);
    jframe.add(p2);
    jframe.add(p3);

    jframe.setLayout(new GridBagLayout());
    jframe.getContentPane().setBackground(Color.lightGray);
    JMenuBar menuBar = new JMenuBar();
    jframe.setJMenuBar(menuBar);
    jframe.setSize(300,300);

    mnOptions = new JMenu("Options");
    menuBar.add(mnOptions);

    mntmSetNumberOf = new JMenuItem("Set number of players");
    mntmSetNumberOf.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setNoOfPlayer();
      }

    });
    mnOptions.add(mntmSetNumberOf);

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
/*
  @Override
  public void gameMessage(String message) {
    //ui.gameMessage(String.format("%s, Your cards :\n%s", player1.getName(), playersHands[0].hand.get(i).toString()));
    jL = new JLabel(message);
    jframe.add(jL);
    jframe.repaint();

  }
*/

  @Override
  public void welcomeMessage(String message) {
    super.welcomeMessage(message);
  }

  @Override
  public void waitStart() {
    super.waitStart();
  }

  @Override
  public void waitEnd() {
    super.waitEnd();
  }

  @Override
  public boolean userInputBool(String queryString, String cond) {
    return super.userInputBool(queryString, cond);
  }

  @Override
  public void displayGameState(CardGame game) {
    gameMessage(game.toString());
  }

//___________________
  public void setNoOfPlayer() {
    Object[] options1 = { "OK", "Next",
            "Quit" };
    int result = 0;
    String text;
    JPanel panel = new JPanel();
    panel.add(new JLabel("Players Name: "));
    JTextField textField = new JTextField(10);

    panel.add(textField);
    do{
      result = JOptionPane.showOptionDialog(null, panel, "Setup",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.PLAIN_MESSAGE, null, options1, null);
      if (result == JOptionPane.NO_OPTION) {
        if(!textField.getText().equalsIgnoreCase("")){
          Names.add(textField.getText());
        }
        System.out.println("NO_OPTION");
      }
      if (result == JOptionPane.YES_OPTION) {
        if(!textField.getText().equalsIgnoreCase("")){
          Names.add(textField.getText());
        }
        System.out.println("YES_OPTION");
        break;
      }
      if (result == JOptionPane.CANCEL_OPTION) {
        if(!textField.getText().equalsIgnoreCase("")){
          Names.add(textField.getText());
        }
        System.out.println("CANCEL_OPTION");
      }

      System.out.println(result);
      text = textField.getText();

    }while (result != 0 || result != JOptionPane.CANCEL_OPTION ||result != JOptionPane.YES_OPTION|| result != JOptionPane.CLOSED_OPTION);

    System.out.println("testar..");
    if(!Names.isEmpty()){
      System.out.println("inne n new..");
      mntmSetNumberOf.setVisible(false);
      //Names[0];

    }
  }

  public boolean isNewCard() {

    boolean NewCard=false;

    return NewCard;
  }

}
