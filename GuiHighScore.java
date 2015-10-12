import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by NIK1114 on 2015-10-07.
 * http://www.designmantic.com/logo-design/samples
 *
 * make a object of GuiHighScore(String ScoreInArray)
 * (and the GUI just print the ScoreInArray..)
 *
 */
public class GuiHighScore extends JFrame {
    JPanel jp = new JPanel();
    JLabel[] highScores;


public GuiHighScore(String... ScoreInArray){
    setTitle("TheGame - HighScore");
    setVisible(true);
    setSize(110,290);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
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
    //box.set(Color.white);
    add(box);

    JLabel label = new JLabel("HighScores: ");
    Font f = label.getFont();
// bold
//    label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    label.setFont(f.deriveFont(f.getStyle() | Font.ITALIC));
// unbold
//    label.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));

            //label.setHorizontalAlignment(JLabel.RIGHT);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    box.add(label);

    //jp.setBackground(Color.GREEN);

   // jp.add(jb, BorderLayout.SOUTH);

    //jp.add(label, BorderLayout.SOUTH);

    //add(label, BorderLayout.NORTH);
    //jp.setLayout(null);

    highScores = new JLabel[ScoreInArray.length];
    for (int i = 0 ; i<ScoreInArray.length ; i++){
        highScores[i] = new JLabel(ScoreInArray[i]);

        box.add(highScores[i], BorderLayout.SOUTH);
        //jp.add(bselects[i]);
    }

    jp.add(box);

    add(jp);

    validate();


}
    public boolean optionsMaker(final int ClickPointer,boolean isMarked) {
        if (isMarked){
               isMarked = false;
            highScores[ClickPointer].setBorder(null);


        }else if (!isMarked){
            //lblMarkeraDeTrningar.setText("på");
            isMarked = true;
            Border marking = new BevelBorder(
                    BevelBorder.LOWERED, Color.DARK_GRAY, Color.BLACK);
            highScores[ClickPointer].setBorder(marking);
            System.out.println(highScores[ClickPointer].getText());

            CardGameSelector.run(highScores[ClickPointer].getText());
        }
        return isMarked;
    }



}