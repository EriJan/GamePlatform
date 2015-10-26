import java.util.ArrayList;

/**
 * Created by NIK1114 on 2015-10-12.
 */
public class FizzAndBuzz extends CardGame {

    ArrayList<Player>  players = new ArrayList<>();
    private static final GameId GAME_ID = GameId.FizzAndBuzz;
    //Player player1,player2;


    @Override
    public void runGame() {
        String val;
        boolean QuitLoopAddPlayer = true;
        do {
            System.out.println("set playername: [ or set Q to start the game] ");
            val = HelperMethods.inPutFromNextLine();
            if (!val.isEmpty() && !val.equalsIgnoreCase("q")){
                Player playerX = new Player(val, GAME_ID);
                players.add(playerX);
            }
            if (val.equalsIgnoreCase("q")){
                QuitLoopAddPlayer = false;
            }

        } while (QuitLoopAddPlayer);

        int level = 30;
        boolean playAgain = true;
        int turns = players.size()*2;
        do {
            for(Player playerId : players){
                int tal = HelperMethods.getRandomInt(0, 30);
                String RealAnswer = RealAnswer(tal);

                System.out.println("\nNumber: " + tal);
                System.out.format("'N' (normal), 'Fizz' (3) or 'Buzz' (5), %s :", playerId.getName());
                val = HelperMethods.inPutFromNextLine();
                if (val.equals(RealAnswer)){
                    playerId.addWin(1);
                    System.out.format("Correct %s", playerId.getName());
                }else System.out.format("Fault %s", playerId.getName());
            }
            turns--;
            if(turns == 0){
                System.out.println("continue? (Y):");
                val = HelperMethods.inPutFromNextLine();
                if(val.equalsIgnoreCase("Y")){
                    turns = players.size();
                }else playAgain = false;
            }

        } while (playAgain);

        for(Player playerId : players){
            System.out.println(playerId.toString() + " score " + playerId.getWin());
        }

    }

  @Override
  public void setUi(GameGraphicUi ui) {

  }

  public String RealAnswer(int tal){
        if(tal % 3 == 0) {
            return "Fizz";
        } else

        if (tal % 5 == 0) {
            return "Buzz";
        }

        if( (tal % 3 != 0) && (tal % 5 != 0) ) {
            return "N";
        }
      return "N";
    }

}
