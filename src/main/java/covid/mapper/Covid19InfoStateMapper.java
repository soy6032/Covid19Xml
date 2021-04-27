package covid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import covid.dto.Covid19ItemDto;

@Mapper
public interface Covid19InfoStateMapper {
	List<Covid19ItemDto> getDbItemList() throws Exception;
	List<Covid19ItemDto> getDbItemListRange(@Param("startDt") String startDt, @Param("endDt") String endDt);
	void setDbItem(Covid19ItemDto item) throws Exception;
	void setDbItemList(List<Covid19ItemDto> itemList) throws Exception;
}
