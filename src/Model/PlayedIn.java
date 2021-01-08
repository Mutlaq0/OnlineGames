package Model;

public class PlayedIn {
	private long gameID;
	private int playerID;
	private int exists;
	private int effectiveness;
	private int accidents;
	private int points;
	private int location;
	private int overtakes;
	public PlayedIn(long gameID, int playerID, int exists, int effectiveness, int accidents, int points, int location,
			int overtakes) {
		super();
		this.gameID = gameID;
		this.playerID = playerID;
		this.exists = exists;
		this.effectiveness = effectiveness;
		this.accidents = accidents;
		this.points = points;
		this.location = location;
		this.overtakes = overtakes;
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
	public int getExists() {
		return exists;
	}
	public void setExists(int exists) {
		this.exists = exists;
	}
	public int getEffectiveness() {
		return effectiveness;
	}
	public void setEffectiveness(int effectiveness) {
		this.effectiveness = effectiveness;
	}
	public int getAccidents() {
		return accidents;
	}
	public void setAccidents(int accidents) {
		this.accidents = accidents;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getOvertakes() {
		return overtakes;
	}
	public void setOvertakes(int overtakes) {
		this.overtakes = overtakes;
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
		PlayedIn other = (PlayedIn) obj;
		if (gameID != other.gameID)
			return false;
	
		if (playerID != other.playerID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlayedIn [gameID=" + gameID + ", playerID=" + playerID + ", exists=" + exists + ", effectiveness="
				+ effectiveness + ", accidents=" + accidents + ", points=" + points + ", location=" + location
				+ ", overtakes=" + overtakes + "]";
	}
	
	
}
