import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

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
  JPanel p1 = new JPanel(new GridBagLayout());
  JPanel p2 = new JPanel(new GridBagLayout());
  JPanel p3 = new JPanel(new GridBagLayout());

  JLabel[] P1Cards;
  JLabel[] P2Cards;

  ArrayList<String> Names= new ArrayList<String>();

  // JFrame jframe;

  public the21GraphicUi() {


    jframe = new JFrame("The 21 Game");
    jframe.setLayout(new GridBagLayout());
    jframe.getContentPane().setBackground(new Color(0, 184, 46));
    //jframe.setSize(300,300);
    //jframe.setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("cardicons/Green.png"))));

    //JLabel background = new JLabel(new ImageIcon(this.getClass().getResource("cardicons/Green.png")));
    //background.setLayout(new BorderLayout());
    //jframe.add(background);

    //jframe.setLocationRelativeTo(null);

    jframe.setVisible(false);
    jframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
    //jframe.add(p1);
    //jframe.add(p2);
    //jframe.add(p3);


    JMenuBar menuBar = new JMenuBar();
    jframe.setJMenuBar(menuBar);



    mnOptions = new JMenu("Options");
    menuBar.add(mnOptions);

    mntmSetNumberOf = new JMenuItem("Set number of players");
    mntmSetNumberOf.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setNoOfPlayer();
      }

    });
    mnOptions.add(mntmSetNumberOf);

//    jframe.pack();

    // jframe.setVisible(true);

    //jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
  public void gameMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Game Message",
        JOptionPane.PLAIN_MESSAGE);

    //super.gameMessage(message);
  }

  @Override
  public String userInput(String queryString) {
    String retStr = (String) JOptionPane.showInputDialog(jframe,"Type your name, please", queryString);
    retStr = (retStr == null) ? "" : retStr;
    return retStr;

    // return super.userInput(queryString);
  }

  @Override
  public boolean userInputBool(String queryString, String cond) {

    int ans = JOptionPane.showConfirmDialog(null, queryString, "Game Query",
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    boolean retAns = (ans == 0) ? true : false;


    return retAns;




    // return super.userInputBool(queryString, cond);
  }

  @Override
  public void displayGameState(CardGame game) {
    jframe.getContentPane().removeAll();
    jframe.setLocation(200,290);

    jframe.setSize(300,300);

    the21 t21 = (the21) game;

    ArrayList<Integer> PCints = t21.getDrowCard2();
    ArrayList<Integer> UserInts = t21.getDrowCard();
    ArrayList<ImageIcon> PCImgs = t21.getDrowCard2Img();
    ArrayList<ImageIcon> UserImgs = t21.getDrowCardImg();

    JPanel housePanel = new JPanel();
    ImageIcon testBack = new ImageIcon(this.getClass().getResource("cardicons/b1fv.png")) ;
    housePanel.setPreferredSize(new Dimension(testBack.getIconWidth()*3, testBack.getIconHeight()));
    SpringLayout houseLayout = new SpringLayout();
    housePanel.setLayout(houseLayout);

    GridBagConstraints c = new GridBagConstraints();
    int halfTableSize = 2/2;
    c.gridx = halfTableSize;
    c.gridy = 0;
    jframe.add(housePanel,c);

    //PC
    JLabel currentLabel = null;
    JLabel lastLabel = null;
    int j = 0;
    for(ImageIcon PCImg : PCImgs) {

      currentLabel = new JLabel(PCImg);
      housePanel.add(currentLabel);
      housePanel.setBackground(new Color(0, 184, 46));

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

    //lastLabel.add(new JLabel(t21.getM()));

    houseLayout.putConstraint(SpringLayout.EAST, housePanel,
        5,SpringLayout.EAST,lastLabel);
    houseLayout.putConstraint(SpringLayout.SOUTH, housePanel,
        5,SpringLayout.SOUTH,lastLabel);
    //PC end

    //userStart
    GridBagConstraints gbCons = new GridBagConstraints();//size = 1



    JPanel localPanel = new JPanel();
    localPanel.setBackground(new Color(0, 184, 46));
    localPanel.setPreferredSize(new Dimension(testBack.getIconWidth()*3, testBack.getIconHeight()));

    SpringLayout localLayout = new SpringLayout();
    localPanel.setLayout(localLayout);

    gbCons = new GridBagConstraints();
    gbCons.gridx = 0;
    gbCons.gridy = 1;
    jframe.add(localPanel,gbCons);

    j = 0;

    for(ImageIcon UserImg : UserImgs) {
      System.out.println(t21.getDrowCard());
      System.out.println(t21.getDrowCard2());

      currentLabel = new JLabel(UserImg);

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


    jframe.validate();
    //jframe.pack();
    jframe.setVisible(true);

    // gameMessage(game.toString());
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
