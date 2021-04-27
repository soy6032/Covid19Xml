package covid.service;

import java.util.List;


import covid.dto.Covid19ItemDto;

public interface Covid19Service {

	List<Covid19ItemDto> getItem() throws Exception;
	List<Covid19ItemDto> getItemListRange(String strUrl) throws Exception;
	
	List<Covid19ItemDto> getDbItemList() throws Exception;
	//지정한 날짜 디에터 가져오기
	List<Covid19ItemDto> getDbItemListRange(String StartDt, String endDt)throws Exception;
	
	void setDbItemList(List<Covid19ItemDto> newItemList) throws Exception;
}
	
	
