package Model;

import java.sql.Timestamp;

public class GiftPurchase {
	private int giftID;
	private int playerID;
	private Timestamp purchaseDateTime;
	private int quantity;
	public GiftPurchase(int giftID, int playerID, Timestamp purchaseDateTime, int quantity) {
		super();
		this.giftID = giftID;
		this.playerID = playerID;
		this.purchaseDateTime = purchaseDateTime;
		this.quantity = quantity;
	}
	public int getGiftID() {
		return giftID;
	}
	public void setGiftID(int giftID) {
		this.giftID = giftID;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + giftID;
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
		GiftPurchase other = (GiftPurchase) obj;
		if (giftID != other.giftID)
			return false;
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
		return "GiftPurchase [giftID=" + giftID + ", playerID=" + playerID + ", purchaseDateTime=" + purchaseDateTime
				+ ", quantity=" + quantity + "]";
	}
	
	
}
