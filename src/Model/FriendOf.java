package Model;

public class FriendOf {
	private int playerID_1;
	private int playerID_2;
	public FriendOf(int playerID_1, int playerID_2) {
		super();
		this.playerID_1 = playerID_1;
		this.playerID_2 = playerID_2;
	}
	public int getPlayerID_1() {
		return playerID_1;
	}
	public void setPlayerID_1(int playerID_1) {
		this.playerID_1 = playerID_1;
	}
	public int getPlayerID_2() {
		return playerID_2;
	}
	public void setPlayerID_2(int playerID_2) {
		this.playerID_2 = playerID_2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerID_1;
		result = prime * result + playerID_2;
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
		FriendOf other = (FriendOf) obj;
		if (playerID_1 != other.playerID_1)
			return false;
		if (playerID_2 != other.playerID_2)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FriendOf [playerID_1=" + playerID_1 + ", playerID_2=" + playerID_2 + "]";
	}
	
}
