import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class Player implements Serializable {

	//private static int numberOfPlayers;

	private String name;
  private EnumMap<GameId,GameStatistics> gameStatsPerGame;
  private GameId currentGame;
  private GameStatistics currentStats;
  private ImageIcon angryPlayer;
  private ImageIcon happyPlayer;

	public Player(String name, GameId id) {
		this.name = name;
    gameStatsPerGame = new EnumMap<GameId,GameStatistics>(GameId.class);
    for (GameId loopId : GameId.values()) {
      gameStatsPerGame.put(loopId, new GameStatistics());
    }
    setCurrentGame(id);

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

  @Override
	public String toString() {
    String tmpString = "";
    tmpString += name + "\n";
    for (GameId id : GameId.values()) {
      GameStatistics gameStatistics = gameStatsPerGame.get(id);
      tmpString += "\n" + id + "\n";
      if (gameStatistics != null) {
        tmpString += "Wins: " + gameStatistics.noOfWins + "\n";
        tmpString += "Losses: " + gameStatistics.noOfLosses + "\n";
        tmpString += "No of games: " + gameStatistics.noOfGamesPlayed + "\n";
      }
    }
    return tmpString;
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

	public void incrNumberOfGamesPlayed(){
		currentStats.noOfGamesPlayed++;
	}

  public int getNumberOfGamesPlayed() {
    return currentStats.noOfGamesPlayed;
  }

  public LocalDate getLastDate() {
    return currentStats.lastGamePlayed;
  }

  public void setLastDate() {
    currentStats.lastGamePlayed = LocalDate.now();
  }

	//private static void addNumberOfPlayers(){
	//	numberOfPlayers += 1;
	//}

  class GameStatistics implements Serializable {
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
