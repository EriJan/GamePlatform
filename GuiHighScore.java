import javax.swing.*;
import java.awt.*;

/**
 * Created by NIK1114 on 2015-10-07.
 * http://www.designmantic.com/logo-design/samples
 *
 * make a object of GuiHighScore(String 2D-ScoreInArray)
 * (and the GUI just print the ScoreInArray..)
 * the cells is:
 * "Score", "Name", "Date"
 * in String[]... ScoreInArray
 *
 */
public class GuiHighScore extends JFrame {
    JPanel jp = new JPanel();

    JTable jT;


public GuiHighScore(String[]... ScoreInArray){
    setTitle("TheGame - HighScore");
    setVisible(true);
    setSize(500,250);
    //setDefaultCloseOperation(EXIT_ON_CLOSE);
    setBackground(Color.white);


//    getContentPane().setBackground( Color.white );
    //jb.setIcon(new ImageIcon("/images/logo1.png"));
    //jb.setIcon(new ImageIcon("Q:\\location\\logo1.png"));
    //jb.setIcon(new ImageIcon("/logo1.png"));
    //jb.setIcon(new ImageIcon("logo1.png"));
    //jb.setBackground(Color.white);

    //correct way:
    //jb.setIcon(new ImageIcon(this.getClass().getResource("logo1.png")));
    //wrong way, "Test":
   //jb.setIcon(new ImageIcon("C:\\location\\logo1.png")); //is under " C:\location\"

    //jb.setIcon(new ImageIcon("/Users/Janne/JavaProj/src/logo/location/logo1.png")); //is under " C:\location\"
    //logo1.png to logo6.png..



    //adderar en capsel!
    Box box = Box.createVerticalBox();

    add(box);

    JLabel label = new JLabel("HighScores: ");
    Font f = label.getFont();
// bold
    label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    label.setFont(f.deriveFont(f.getStyle() | Font.ITALIC));
// unbold
//    label.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));

            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    box.add(label);


    String[] name = {"Score", "Name", "Date"};
    Object[][] cells= ScoreInArray;

    jT = new JTable(cells, name);
    //jT.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    c.add(box);
    c.add(new JScrollPane(jT), BorderLayout.CENTER);

    add(jp);

    validate();


}


}