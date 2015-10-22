import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class Player implements java.io.Serializable {

	private static int numberOfPlayers;

	private String name;
  private EnumMap<GameId,GameStatistics> gameStatsPerGame;
  private GameId currentGame;
  private GameStatistics currentStats;

	public Player(String name) {
		this.name = name;
    gameStatsPerGame = new EnumMap<GameId,GameStatistics>(GameId.class);
		addNumberOfPlayers();
	}

  public void setCurrentGame(GameId id) {
    currentGame = id;
    currentStats = gameStatsPerGame.get(currentGame);
  }

  public GameId getCurrentGame() {
    return currentGame;
  }

	public String getName() {
		return name;
	}

	public String toString() {

		return name;
	}

	public void addWin(int add) {
    currentStats.noOfWins += add;
	}

	public int getWin() {
		return currentStats.noOfWins;
	}

	public void addLosses(int add) {
		currentStats.noOfLosses += add;
	}

  public int getLosses() {
		return currentStats.noOfLosses;
	}

	public void setNumberOfGamesPlayed(){
		currentStats.noOfGamesPlayed++;
	}

  public int getNumberOfGamesPlayed() {
    return currentStats.noOfGamesPlayed;
  }

	private static void addNumberOfPlayers(){
		numberOfPlayers += 1;
	}

  class GameStatistics {
    int noOfGamesPlayed;
    int noOfWins;
    int noOfLosses;
    LocalDate lastGamePlayed;

    GameStatistics() {
      noOfGamesPlayed = 0;
      noOfWins = 0;
      noOfLosses = 0;
      lastGamePlayed = LocalDate.now();
    }
  }
}
