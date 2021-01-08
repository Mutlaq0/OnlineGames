package Model;

import java.sql.Date;
import java.sql.Timestamp;


public class Player {
	private int playerID;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Date creationDate;
	private String ginder;
	private String nickName;
	private int pointsCalculatedField;
	private int winsCalculatedField;
	private int countryID;
	private String password;
	public Player(int playerID, String firstName, String lastName, Date birthDate, Date creationDate, String ginder,
			String nickName, int pointsCalculatedField, int winsCalculatedField, int countryID,String password) {
		super();
		this.playerID = playerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.creationDate = creationDate;
		this.ginder = ginder;
		this.nickName = nickName;
		this.pointsCalculatedField = pointsCalculatedField;
		this.winsCalculatedField = winsCalculatedField;
		this.countryID = countryID;
		this.password=password;
	}
	public Player( String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getGinder() {
		return ginder;
	}
	public void setGinder(String ginder) {
		this.ginder = ginder;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getPointsCalculatedField() {
		return pointsCalculatedField;
	}
	public void setPointsCalculatedField(int pointsCalculatedField) {
		this.pointsCalculatedField = pointsCalculatedField;
	}
	public int getWinsCalculatedField() {
		return winsCalculatedField;
	}
	public void setWinsCalculatedField(int winsCalculatedField) {
		this.winsCalculatedField = winsCalculatedField;
	}
	public int getCountryID() {
		return countryID;
	}
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Player other = (Player) obj;
		if (playerID != other.playerID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", creationDate=" + creationDate + ", ginder=" + ginder + ", nickName=" + nickName
				+ ", pointsCalculatedField=" + pointsCalculatedField + ", winsCalculatedField=" + winsCalculatedField
				+ ", countryID=" + countryID + "password="+password+"]";
	}
	
	
}
