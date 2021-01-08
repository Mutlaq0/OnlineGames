package Model;

import java.security.Timestamp;
import java.sql.Date;

public class GiftRankPlayer {
	private int rankID;
	private int playerID;
	private int giftID;
	private Date dateRecieve;
	private boolean suspended;
	public GiftRankPlayer(int rankID, int playerID, int giftID, Date dateRecieve, boolean suspended) {
		super();
		this.rankID = rankID;
		this.playerID = playerID;
		this.giftID = giftID;
		this.dateRecieve = dateRecieve;
		this.suspended = suspended;
	}
	public int getRankID() {
		return rankID;
	}
	public void setRankID(int rankID) {
		this.rankID = rankID;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public int getGiftID() {
		return giftID;
	}
	public void setGiftID(int giftID) {
		this.giftID = giftID;
	}
	public Date getDateRecieve() {
		return dateRecieve;
	}
	public void setDateRecieve(Date dateRecieve) {
		this.dateRecieve = dateRecieve;
	}
	public boolean isSuspended() {
		return suspended;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerID;
		result = prime * result + rankID;
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
		GiftRankPlayer other = (GiftRankPlayer) obj;
		if (playerID != other.playerID)
			return false;
		if (rankID != other.rankID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GiftRankPlayer [rankID=" + rankID + ", playerID=" + playerID + ", giftID=" + giftID + ", dateRecieve="
				+ dateRecieve + ", suspended=" + suspended + "]";
	}
	
	
}
