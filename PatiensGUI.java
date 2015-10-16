import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Ulla on 2015-10-16.
 */
public class PatiensGUI {
    static Patiens p = new Patiens();



public static int printMenue(){
    String text = p.printMenuPatiens();

    String awnser = JOptionPane.showInputDialog("text");
        char check;
        int returnInt;

        while(true){
        check=awnser.charAt(0);
        if(Character.isDigit(check)){
        returnInt=Integer.parseInt(awnser);
        break;
        }else{
        JOptionPane.showMessageDialog(null, "Något har blivit fel", "Felmeddelande",JOptionPane.PLAIN_MESSAGE);
        }
        }
        return returnInt;
        }
        }

