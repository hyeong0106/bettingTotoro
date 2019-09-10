package point.model.vo;

public class Point {
	private String itemType;
	private String itemName;
	private int price;
	private String content;
	private String itemImage;
	
	public Point() {
		
	}
	public Point(String itemType, String itemName, int price, String content, String itemImage) {
		this.itemType = itemType;
		this.itemName = itemName;
		this.price = price;
		this.content = content;
		this.itemImage = itemImage;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	@Override
	public String toString() {
		return "Point [itemType=" + itemType + ", itemName=" + itemName + ", price=" + price
				+ ", content=" + content + ", itemImage=" + itemImage + "]";
	}
	
	
}
