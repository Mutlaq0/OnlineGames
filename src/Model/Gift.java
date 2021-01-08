package Model;

public class Gift {
	private int giftID;
	private String shortDesc;
	private String textualDesc;
	private int price;
	public Gift(int giftID, String shortDesc, String textualDesc, int price) {
		super();
		this.giftID = giftID;
		this.shortDesc = shortDesc;
		this.textualDesc = textualDesc;
		this.price = price;
	}
	public int getGiftID() {
		return giftID;
	}
	public void setGiftID(int giftID) {
		this.giftID = giftID;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getTextualDesc() {
		return textualDesc;
	}
	public void setTextualDesc(String textualDesc) {
		this.textualDesc = textualDesc;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + giftID;
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
		Gift other = (Gift) obj;
		if (giftID != other.giftID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Gift [giftID=" + giftID + ", shortDesc=" + shortDesc + ", textualDesc=" + textualDesc + ", price="
				+ price + "]";
	}
	
}
