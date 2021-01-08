package Model;

import java.sql.Timestamp;

public class CreateYourOwnMatchWithFriends {
	private  long gameID;
	private Timestamp gameStartDateTime;
	private Timestamp gameEndDateTime;
	private int playerID;
	private int points;
	private int timesDied;
	public CreateYourOwnMatchWithFriends(long gameID, Timestamp gameStartDateTime, Timestamp gameEndDateTime, int playerID,
			int points, int timesDied) {
		this.gameID = gameID;
		this.gameStartDateTime = gameStartDateTime;
		this.gameEndDateTime = gameEndDateTime;	
		this.playerID = playerID;
		this.points = points;
		this.timesDied = timesDied;
	}

	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getTimesDied() {
		return timesDied;
	}
	public void setTimesDied(int timesDied) {
		this.timesDied = timesDied;
	}

	public long getGameID() {
		return gameID;
	}
	public void setGameID(long gameID) {
		this.gameID = gameID;
	}
	public Timestamp getGameStartDateTime() {
		return gameStartDateTime;
	}
	public void setGameStartDateTime(Timestamp gameStartDateTime) {
		this.gameStartDateTime = gameStartDateTime;
	}
	public Timestamp getGameEndDateTime() {
		return gameEndDateTime;
	}
	public void setGameEndDateTime(Timestamp gameEndDateTime) {
		this.gameEndDateTime = gameEndDateTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + gameID);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateYourOwnMatchWithFriends other = (CreateYourOwnMatchWithFriends) obj;
		if (gameID != other.gameID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CreateYourOwnMatchWithFriends [gameID=" + gameID + ", gameStartDateTime=" + gameStartDateTime
				+ ", gameEndDateTime=" + gameEndDateTime + ", playerID=" + playerID + ", points=" + points
				+ ", timesDied=" + timesDied + "]";
	}
	
	
}
