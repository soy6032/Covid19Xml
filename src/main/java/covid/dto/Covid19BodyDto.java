package covid.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "body")
public class Covid19BodyDto {
	
	private Covid19ItemsDto items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
	
	@XmlElement(name = "items")
	public Covid19ItemsDto getItems() {
		return items;
	}
	public void setItems(Covid19ItemsDto items) {
		this.items = items;
	}
	@XmlElement
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
