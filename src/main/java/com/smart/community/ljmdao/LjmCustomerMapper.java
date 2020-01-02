package com.smart.community.ljmdao;

import com.smart.community.ljmbean.DispatchPersonBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LjmCustomerMapper
{
	/**
	 * 投诉和建议反馈
	 * @param result 反馈信息
	 * @param suggestId 投诉和建议id
	 * @return int
	 */
	public int updateForSuggestFeedback( @Param("result") String result , @Param("suggestId") String suggestId);

	/**
	 * 派工人员下拉框显示
	 * @return 派工人员信息
	 */
	public List<DispatchPersonBean> selectForGetDispatchPersons();

	/**
	 * 选中的派工人员查询
	 * @param workId 人员id
	 * @return 派工人有信息
	 */
	public DispatchPersonBean selectForGetDispatchPerson(String workId);
}
