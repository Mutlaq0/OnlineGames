package Model;

import java.sql.Timestamp;


public class Purchase {
	private int playerID;
	private Timestamp purchaseDateTime;
	public Purchase(int playerID, Timestamp purchaseDateTime) {
		super();
		this.playerID = playerID;
		this.purchaseDateTime = purchaseDateTime;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public Timestamp getPurchaseDateTime() {
		return purchaseDateTime;
	}
	public void setPurchaseDateTime(Timestamp purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerID;
		result = prime * result + ((purchaseDateTime == null) ? 0 : purchaseDateTime.hashCode());
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
		Purchase other = (Purchase) obj;
		if (playerID != other.playerID)
			return false;
		if (purchaseDateTime == null) {
			if (other.purchaseDateTime != null)
				return false;
		} else if (!purchaseDateTime.equals(other.purchaseDateTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Purchase [playerID=" + playerID + ", purchaseDateTime=" + purchaseDateTime + "]";
	}
	
}
