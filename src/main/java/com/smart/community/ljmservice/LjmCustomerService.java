package com.smart.community.ljmservice;

import com.smart.community.ljmbean.DispatchPersonBean;
import com.smart.community.ljmdao.LjmCustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LJM
 */
@Service
public class LjmCustomerService
{
	@Resource
	private LjmCustomerMapper customerMapper;

	/**
	 * 客服反馈
	 * @param result 反馈结果
	 * @param suggestId 建议id
	 * @return int
	 */
	public int feedbackForSuggestService(String result , String suggestId)
	{
		return customerMapper.updateForSuggestFeedback(result,suggestId);
	}

	/**
	 * 获取派工人有信息下拉框
	 * @return 派工人有列表
	 */
	public List<DispatchPersonBean> forGetDispatchList()
	{
		return customerMapper.selectForGetDispatchPersons();
	}

	/**
	 * 查询选中人员
	 * @param workId 人员id
	 * @return 人员信息
	 */
	public DispatchPersonBean forGetSelectedDispatchPerson(String workId)
	{
		return customerMapper.selectForGetDispatchPerson(workId);
	}
}
