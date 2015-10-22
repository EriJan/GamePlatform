/**
 * Created by Niklas on 19/10/15.
 */
public class the21Hands extends CardDeal {
  private int summa;

  the21Hands() {
    super();
  }

  public boolean isTjock(int[] cards) {
    //kortIHand [0] till 2...
    if (getHandValue(cards) >= 22) {
      return true;
    }
    return false;
  }

//	public int getResultat() {
//		return summa;
//	}

  public int youHaveAnAce() {
    int aceVal = 0;

    return aceVal;
  }

  @Override
  int getHandValue() {
    int evTempEss1, evTempEss2, evTempEss3;
    PlayingCard card1 = null;
    PlayingCard card2 = null;
    PlayingCard card3 = null;

    for (PlayingCard cardInHand : hand) {
      summa += cardInHand.getValue();
    }

    return summa;


  }

  int getHandValue(int[] cards) {
    int evTempEss1 = cards[0];
    int evTempEss2 = cards[1];
    int evTempEss3 = cards[2];

    //System.out.println("evTempEss: " + evTempEss1 + " "+  evTempEss2 + " "+ evTempEss3 );
    //System.out.println("kortIHand: " + kortIHand[0] + " " + kortIHand[1] + " " + kortIHand[2] );
    summa = cards[0] + cards[1] + cards[2];//summerar allt..
    //System.out.println("inne i get result, summerar fÃ¶rst de lÃ¥ga : " + summa);

    if (cards[0] == 1) {
      evTempEss1 = 14;
    }
    if (cards[1] == 1) {
      evTempEss2 = 14;
    }
    if (cards[2] == 1) {
      evTempEss3 = 14;
    }
    //System.out.println("evTempEss-2: " + evTempEss1 + " "+  evTempEss2 + " "+ evTempEss3 );

    if ((cards[0] == cards[1] || cards[0] == cards[2]) && (cards[0] == 1)) {
      //aj aj vi har ju 2 ess dï¿½ i DENNA VERSION, sï¿½tter vi Ett av dessa till 1
      //ex om vi har kung ess ess = fï¿½rst 10 + 14+ 14, sedan
      //nu 10 + 14+ 1
      //det andra kontrolleras senare..
      evTempEss1 = 1;
    } else if ((cards[1] == cards[2]) && (cards[1] == 1)) {
      evTempEss2 = 1;
    }
    //nu har vi endast EN 14 om vi hade fler tidigare...
    //evTempEss3 om alla var ess..
    int temtexxtestsumm = evTempEss1 + evTempEss2 + evTempEss3;
    //System.out.println("temtexxtestsumm : " + temtexxtestsumm);
    if (temtexxtestsumm <= 21) { //OM SUMMAN Ã„R UNDER 22..

      //System.out.println("evTempEss-21: " + evTempEss1 + " "+  evTempEss2 + " "+ evTempEss3 );
      summa = evTempEss1 + evTempEss2 + evTempEss3;
      // System.out.println("summa2: " + summa);


      //detta ï¿½r den absolut stï¿½rsta vï¿½rdet vi kan ha..
      // vi vet ï¿½ven att det ev bara ï¿½r EN som ï¿½r 14
    } else {
      //nej om man anvï¿½nde Ess son 14 sï¿½ fick man 21 eller ï¿½ver..
      //suck nu blev det jobbigt..
      //nu vill vi fï¿½ ut det stï¿½rsta vï¿½rdet..
      if (evTempEss1 == 14) {
        evTempEss1 = 1;
        summa = evTempEss1 + evTempEss2 + evTempEss3;

        // System.out.println("evTempEss-3: " + evTempEss1 + " "+  evTempEss2 + " "+ evTempEss3 );

        //System.out.println("summa3: " + summa);
      } else if (evTempEss2 == 14) {
        evTempEss2 = 1;
        summa = evTempEss1 + evTempEss2 + evTempEss3;

        // System.out.println("evTempEss-4: " + evTempEss1 + " "+  evTempEss2 + " "+ evTempEss3 );

        // System.out.println("summa4: " + summa);
      } else if (evTempEss3 == 14) {
        evTempEss3 = 1;

        // System.out.println("evTempEss-5: " + evTempEss1 + " "+  evTempEss2 + " "+ evTempEss3 );

        summa = evTempEss1 + evTempEss2 + evTempEss3;
        // System.out.println("summa5: " + summa);
      }//ja nu kan det vara sï¿½ att summan ï¿½r 21 men vi kan inte fï¿½ "lï¿½gre"..


    }
    return summa;

  }

  public boolean isCardToTake(int[] cards) {
    for (int i = 0; i < cards.length; i++) {
      if (cards[i] == 0) {
        return true;
      }
    }
    return false;
  }

}
