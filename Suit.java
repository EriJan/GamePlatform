// Used of standard 52 card playing deck. Suit values
// will set sort order.
public enum Suit {
  Clubs(0), Diamonds(1), Hearts(3), Spades(2);

  private final int value;

  Suit(int val) {
    value = val;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    char suitRepCh;
    if (this == Clubs) {
      suitRepCh = '\u2666';
    } else if (this == Diamonds) {
      suitRepCh = '\u2663';
    } else if (this == Hearts) {
      suitRepCh = '\u2665';
    } else if (this == Spades) {
      suitRepCh = '\u2660';
    } else {
      suitRepCh = ' ';
    }
    return Character.toString(suitRepCh);
  }
}
