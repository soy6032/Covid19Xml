package covid.service;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import covid.dto.Covid19DataDto;
import covid.dto.Covid19ItemDto;
import covid.mapper.Covid19InfoStateMapper;

@Service
public class Covid19ServiceImpl implements Covid19Service {

	@Autowired
	private Covid19InfoStateMapper covid19Mapper;
	
	@Override
	public List<Covid19ItemDto> getItem() throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Covid19DataDto.class);
		Unmarshaller um = jc.createUnmarshaller();
		Covid19DataDto data = (Covid19DataDto)um.unmarshal(new File("c://java102//covid19infostate.xml"));
		List<Covid19ItemDto> itemList = data.getBody().getItems().getItemList();
		return itemList;
	}

	@Override
	public List<Covid19ItemDto> getItemListRange(String strUrl) throws Exception {
		
		List<Covid19ItemDto> itemList = null;
		URL url = null;
		HttpURLConnection urlConn = null;
		
		
		try {
			url = new URL(strUrl);
			urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setRequestMethod("GET");
			
			JAXBContext jc = JAXBContext.newInstance(Covid19DataDto.class);
			Unmarshaller um = jc.createUnmarshaller();
			Covid19DataDto infoData = (Covid19DataDto)um.unmarshal(url);
			
			itemList = infoData.getBody().getItems().getItemList(); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			urlConn.disconnect();
		}
		return itemList;
	}

	@Override
	public List<Covid19ItemDto> getDbItemList() throws Exception {
		
		return covid19Mapper.getDbItemList();
	}

	@Override
	public List<Covid19ItemDto> getDbItemListRange(String startDt, String endDt) throws Exception {

		List<Covid19ItemDto> dbItemList = covid19Mapper.getDbItemListRange(dateConvert(startDt), dateConvert(endDt));
		
		return dbItemList;
	}
	
	private String dateConvert(String date) {
//		입력받은 날짜를 db에서 알수 있는 형태로 변환
//		20200314 -> 2020-03-14
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < date.length(); i++) {
			sb.append(date.charAt(i));
			if(i == 3) {
				sb.append('-');
			}
			else if (i == 5) {
				sb.append('-');
			}
		}
		return sb.toString();
	}

	@Override
	public void setDbItemList(List<Covid19ItemDto> newItemList) throws Exception {
//		for(Covid19ItemDto item : newItemList) {
//			covid19Mapper.setDbItem(item);
//		}
		
		covid19Mapper.setDbItemList(newItemList);
		
	}

	
}
