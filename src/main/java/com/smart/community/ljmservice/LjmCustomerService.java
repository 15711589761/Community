package com.smart.community.ljmservice;

import com.smart.community.ljmdao.LjmCustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LJM
 */
@Service
public class LjmCustomerService
{
	@Resource
	private LjmCustomerMapper customerMapper;

	public int feedbackForSuggestService(String result , String suggestId)
	{
		return customerMapper.updateForSuggestFeedback(result,suggestId);
	}
}
