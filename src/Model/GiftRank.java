package Model;

public class GiftRank {
	private int giftID;
	private int rankID;
	public GiftRank(int giftID, int rankID) {
		super();
		this.giftID = giftID;
		this.rankID = rankID;
	}
	public int getGiftID() {
		return giftID;
	}
	public void setGiftID(int giftID) {
		this.giftID = giftID;
	}
	public int getRankID() {
		return rankID;
	}
	public void setRankID(int rankID) {
		this.rankID = rankID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + giftID;
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
		GiftRank other = (GiftRank) obj;
		if (giftID != other.giftID)
			return false;
		if (rankID != other.rankID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GiftRank [giftID=" + giftID + ", rankID=" + rankID + "]";
	}
	
	
}
