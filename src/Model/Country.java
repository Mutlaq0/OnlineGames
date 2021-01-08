package Model;

public class Country {
	private int countryID;
	private String name;
	public Country(int countryID, String name) {
		super();
		this.countryID = countryID;
		this.name = name;
	}
	public int getCountryID() {
		return countryID;
	}
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countryID;
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
		Country other = (Country) obj;
		if (countryID != other.countryID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Country [countryID=" + countryID + ", name=" + name + "]";
	}
	
}
