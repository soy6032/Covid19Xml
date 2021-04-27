package covid.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import covid.dto.Covid19ItemDto;
import covid.service.Covid19Service;

@Controller
public class Covid19Controller {

	@Autowired
	private Covid19Service covid19Service;
	
	@RequestMapping(value = "/covid/covidData", method = RequestMethod.GET)
	public Object getCovid() throws Exception{
		ModelAndView mv = new ModelAndView("covid/covidData");
		List<Covid19ItemDto> list = covid19Service.getItem();
		mv.addObject("data", list);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/covid/infoState", method = RequestMethod.POST)
	public Object getinfoState(@RequestParam Map<String, String> param) throws Exception{

		String endPoint = "http://openapi.data.go.kr/openapi/service/rest/Covid19/";
		String serviceFunc = "getCovid19InfStateJson?";
		String keyFunc = "serviceKey=";
		String pageNo = "&pageNo=1";
		String rows = "&numOfRows=10";
		String startCreateDt = "&startCreateDt=";
		String endCreateDt = "&endCreateDt=";
		
		String serviceKey = "aCWI3Wj4ztkyvBJ5dpkTCAQ2xlckU0pcZv0ioov2bMyK2OT%2F08bP3Z1SnSY0%2FtDXyDUnWAhUQI4Oqy%2BvjDswLw%3D%3D";
		String strUrl = endPoint + serviceFunc + keyFunc + serviceKey + pageNo + rows + startCreateDt + param.get("startCreateDt") + endCreateDt + param.get("endCreateDt");
		List<Covid19ItemDto> itemList = covid19Service.getItemListRange(strUrl);
		
		return itemList;
	}
	
	@ResponseBody
	@RequestMapping(value = "covid/infoState3", method = RequestMethod.POST)
	public Object getinfoState3(@RequestParam Map<String, String> param) throws Exception{
//		ajax 통신을 통해서 매개변수로 시작 날짜와 종료 날짜를 받아서 공공데이터 포털에서 데이터를 가져오기 위한 준비
		
		String endPoint = "http://openapi.data.go.kr/openapi/service/rest/Covid19/";
		String serviceFunc = "getCovid19InfStateJson?";
		String keyFunc = "serviceKey=";
		String pageNo = "&pageNo=1";
		String rows = "&numOfRows=10";
		String startCreateDt = "&startCreateDt=";
		String endCreateDt = "&endCreateDt=";
		
		String serviceKey = "aCWI3Wj4ztkyvBJ5dpkTCAQ2xlckU0pcZv0ioov2bMyK2OT%2F08bP3Z1SnSY0%2FtDXyDUnWAhUQI4Oqy%2BvjDswLw%3D%3D";
//		공공데이터 포털에서 데이터를 가져오기 위한 url 설정
		String strUrl = endPoint + serviceFunc + keyFunc + serviceKey + pageNo + rows + startCreateDt + param.get("startCreateDt") + endCreateDt + param.get("endCreateDt");
//		공공데이터 포털 통해서 신규 xml데이터를 가져오기
		List<Covid19ItemDto> itemList = covid19Service.getItemListRange(strUrl);
//		Db에 저장되어있는 기존 데이터 가져오기
		List<Covid19ItemDto> dbItemList = covid19Service.getDbItemListRange(param.get("startCreateDt"), param.get("endCreateDt"));


//		가져온 xml 데이터와 db에 저장되어 있는 기존의 데이터를 비교
		List<Covid19ItemDto> newItemList = new ArrayList<Covid19ItemDto>();
		
		for(Covid19ItemDto item : itemList) {	
			boolean isEquals = false;
			for(Covid19ItemDto dbItem : dbItemList) {
				if(item.getSeq().equals(dbItem.getSeq())) {
					isEquals = true;
				}
			}
				if(!isEquals) {
					newItemList.add(item);
				}
			}
		
//		새로운 데이터만 db에 저장
		covid19Service.setDbItemList(newItemList);
		
//		지정한 기간에 맞는 데이터를 sql 문으로 통해서 db에서 가져옴
		List<Covid19ItemDto> newDbItemList = covid19Service.getDbItemListRange(param.get("startCreateDt"), param.get("endCreateDt"));;
		
		return newDbItemList;
	}
	
}
