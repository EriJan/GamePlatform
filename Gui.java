import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by NIK1114 on 2015-10-07.
 * http://www.designmantic.com/logo-design/samples
 */
public class Gui extends JFrame {
    JPanel jp = new JPanel();
    JButton jb = new JButton();
    JLabel[] bselects;
    int numberOfOptions = 4;
            //"Blackjack","Patiens","21","Quit"


public Gui(String... Options){
    setTitle("TheGame");
    setVisible(true);
    setSize(110,270);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

//    getContentPane().setBackground( Color.white );
    //jb.setIcon(new ImageIcon("/images/logo1.png"));
    //jb.setIcon(new ImageIcon("Q:\\location\\logo1.png"));
    //jb.setIcon(new ImageIcon("/logo1.png"));
    //jb.setIcon(new ImageIcon("logo1.png"));
    jb.setBackground(Color.white);
    jb.setIcon(new ImageIcon(this.getClass().getResource("logo1.png")));
    //logo1.png to logo6.png..

    //adderar en capsel!
    Box box = Box.createVerticalBox();
    //box.set(Color.white);
    add(box);

    JLabel label = new JLabel("Click on:");
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

    jp.add(jb, BorderLayout.SOUTH);
    //jp.add(label, BorderLayout.SOUTH);

    //add(label, BorderLayout.NORTH);
    //jp.setLayout(null);

    bselects = new JLabel[Options.length];
    for (int i = 0 ; i<Options.length ; i++){
        bselects[i] = new JLabel(Options[i]);
        final int ClickPointer = i; //MYCKET VIKTIG!

        bselects[i].addMouseListener(new MouseAdapter() {
            private boolean isMarked;

            @Override
            public void mouseClicked(MouseEvent diceClickID) {
                //onMouseClicked(ClickID);
                isMarked = optionsMaker(ClickPointer, isMarked);

            }

        });

        bselects[i].addMouseListener(new RatingMouseListener(i));
        //bselects[i].setBounds(20, 55+(i*(48+10)), 48, 48);

                //jp.add(bselects[i], BorderLayout.SOUTH);
        box.add(bselects[i], BorderLayout.SOUTH);
        //jp.add(bselects[i]);
    }

    jp.add(box);

    add(jp);
    jb.addActionListener(new ActionListener() { //en inner class för detta..
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("klick");
            CardGameSelector CardGameSelectorRunnebleThread = new CardGameSelector();

            new Thread(CardGameSelectorRunnebleThread).start();
        }
    });
    validate();


}
    public boolean optionsMaker(final int ClickPointer,boolean isMarked) {
        if (isMarked){
               isMarked = false;
            bselects[ClickPointer].setBorder(null);


        }else if (!isMarked){
            //lblMarkeraDeTrningar.setText("på");
            isMarked = true;
            Border marking = new BevelBorder(
                    BevelBorder.LOWERED, Color.DARK_GRAY, Color.BLACK);
            bselects[ClickPointer].setBorder(marking);
            System.out.println(bselects[ClickPointer].getText());

            CardGameSelector.run(bselects[ClickPointer].getText());
        }
        return isMarked;
    }

    private class RatingMouseListener extends MouseAdapter {
        private final int index;

        public RatingMouseListener(int index) {
            this.index = index;
        }


        @Override
        public void mouseExited(MouseEvent e) {
            bselects[index].setBackground(Color.lightGray);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            bselects[index].setBackground(Color.ORANGE);
        }

    }

    public static void main(String[] args) {
        Gui g = new Gui("Blackjack","Patiens","21","Quit");
    }
}