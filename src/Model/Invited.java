package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class Invited {
	private long gameID;
	private int playerID;
	private Timestamp sentDate;
	private Timestamp recieveDate;
	private int points;
	private int timesDied;
	public Invited(long gameID, int playerID, Timestamp sentDate, Timestamp recieveDate, int points, int timesDied) {
		super();
		this.gameID = gameID;
		this.playerID = playerID;
		this.sentDate = sentDate;
		this.recieveDate = recieveDate;
		this.points = points;
		this.timesDied = timesDied;
	}
	public long getGameID() {
		return gameID;
	}
	public void setGameID(long gameID) {
		this.gameID = gameID;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public Timestamp getSentDate() {
		return sentDate;
	}
	public void setSentDate(Timestamp sentDate) {
		this.sentDate = sentDate;
	}
	public Timestamp getRecieveDate() {
		return recieveDate;
	}
	public void setRecieveDate(Timestamp recieveDate) {
		this.recieveDate = recieveDate;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + gameID);
		result = prime * result + playerID;
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
		Invited other = (Invited) obj;
		if (gameID != other.gameID)
			return false;
		if (playerID != other.playerID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Invited [gameID=" + gameID + ", playerID=" + playerID + ", sentDate=" + sentDate + ", recieveDate="
				+ recieveDate + ", points=" + points + ", timesDied=" + timesDied + "]";
	}
	
	
}
