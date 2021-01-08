package Model;

import java.sql.Timestamp;

public class BestOF100Playes{
	protected long gameID;
	protected Timestamp gameStartDateTime;
	protected Timestamp gameEndDateTime;
	public BestOF100Playes(long gameID, Timestamp gameStartDateTime, Timestamp gameEndDateTime) {
		this.gameID = gameID;
		this.gameStartDateTime = gameStartDateTime;
		this.gameEndDateTime = gameEndDateTime;
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
		result = prime * result + (int) (gameID ^ (gameID >>> 32));
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
		BestOF100Playes other = (BestOF100Playes) obj;
		if (gameID != other.gameID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BestOF100Playes [gameID=" + gameID + ", gameStartDateTime=" + gameStartDateTime + ", gameEndDateTime="
				+ gameEndDateTime + "]";
	}
	

}
