package Model;

public class Ranks {

	private int rankID;
	private String name;
	private int minPoints;
	public Ranks(int rankID, String name, int minPoints) {
		super();
		this.rankID = rankID;
		this.name = name;
		this.minPoints = minPoints;
	}
	public int getRankID() {
		return rankID;
	}
	public void setRankID(int rankID) {
		this.rankID = rankID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinPoints() {
		return minPoints;
	}
	public void setMinPoints(int minPoints) {
		this.minPoints = minPoints;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Ranks other = (Ranks) obj;
		if (rankID != other.rankID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ranks [rankID=" + rankID + ", name=" + name + ", minPoints=" + minPoints + "]";
	}
	
}
