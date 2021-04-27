package covid.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
public class Covid19ItemsDto {
	
	private List<Covid19ItemDto> itemList;

	@XmlElement(name = "item")
	public List<Covid19ItemDto> getItemList() {
		return itemList;
	}

	public void setItemList(List<Covid19ItemDto> itemList) {
		this.itemList = itemList;
	}
	
	
}
