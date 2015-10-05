/**
 * Created by Ulla on 2015-10-05.
 */
public class PatiensPlayingCard extends PlayingCard {

    private final Suit suit;
    private final int value;
    private boolean faceUp;

    PatiensPlayingCard(Suit suit, int val) {
        super(suit, val);
        this.suit = suit;
        this.value = val;

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
                case 0:
                    valString = "0";
                default:
                    valString = Integer.toString(value);
                    break;
            }
            if (valString.equals("0")) {
                valString = "  ";
            } else {
                valString = suit + valString;
            }

        } else {
            valString = "––";
        }
        return valString;
    }
}