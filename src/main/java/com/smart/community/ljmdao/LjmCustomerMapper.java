package com.smart.community.ljmdao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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


}
