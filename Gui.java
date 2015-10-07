import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NIK1114 on 2015-10-07.
 * http://www.designmantic.com/logo-design/samples
 */
public class Gui extends JFrame {
    JPanel jp = new JPanel();
    JButton jb = new JButton();


public Gui(){
    setTitle("TheGame");
    setVisible(true);
    setSize(100,180);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //jb.setIcon(new ImageIcon("/images/logo1.png"));
    //jb.setIcon(new ImageIcon("Q:\\location\\logo1.png"));
    //jb.setIcon(new ImageIcon("/logo1.png"));
    jb.setIcon(new ImageIcon("logo1.png"));
    //logo1.png to logo6.png..
    jp.add(jb);
    add(jp);
    jb.addActionListener(new ActionListener() { //en inner class för detta..
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("klick");
            CardGameSelector.run();
        }
    });
    validate();

}
    public static void main(String[] args) {
        Gui g = new Gui();
    }
}