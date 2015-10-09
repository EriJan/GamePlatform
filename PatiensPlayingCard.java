import java.util.List;

/**
 * Created by Ulla on 2015-10-05.
 */
public class PatiensPlayingCard extends PlayingCard {

    PatiensPlayingCard(Suit suit, int val) {
        super(suit, val);

    }

    @Override
    public String toString() {
        String valString;

        if (faceUp) {
            switch (value) {
                case 1:
                    valString = "A";
                    break;
                case 11:
                    valString = "J";
                    break;
                case 12:
                    valString = "Q";
                    break;
                case 13:
                    valString = "K";
                    break;
                default:
                    valString = Integer.toString(value);
                    break;
            }
                valString = suit + valString;


        } else if (!faceUp && value != 0){
            valString = "––";
        } else {
            valString = "  ";
        }
        return valString;
    }
}