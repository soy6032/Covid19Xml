package covid.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class Covid19DataDto {

	private Covid19HeaderDto header;
	private Covid19BodyDto body;
	
	@XmlElement(name="header")
	public Covid19HeaderDto getHeader() {
		return header;
	}
	public void setHeader(Covid19HeaderDto header) {
		this.header = header;
	}
	
	@XmlElement(name="body")
	public Covid19BodyDto getBody() {
		return body;
	}
	public void setBody(Covid19BodyDto body) {
		this.body = body;
	}
	
	
	
}
