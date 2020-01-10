package com.smart.community.ljmservice;

import com.smart.community.ljmbean.AskForLeaveBean;
import com.smart.community.ljmdao.LjmLeaveMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeaveService
{
	@Resource
	private LjmLeaveMapper leaveMapper;

	/**
	 * 本人请假申请记录显示
	 * @param applyName 请假人
	 * @return 请假记录集合
	 */
	public List<AskForLeaveBean> selectForShowLeaveTable( String applyName)
	{
		return leaveMapper.selectForShowLeaveTable(applyName);
	}

	/**
	 * 请假反馈
	 * @param feedback 反馈信息
	 * @param leaveId 请假id
	 * @return int
	 */
	public int updateForUpLeaveFeedback(String feedback,int leaveId)
	{
		return leaveMapper.updateForUpLeaveFeedback(feedback,leaveId);
	}

	/**
	 * 获取这个人最新的请假id
	 * @param applyName 请假人
	 * @return 最新的请假id
	 */
	public int selectForGetLastRecord(String applyName)
	{
		List<AskForLeaveBean> list = leaveMapper.selectForGetLastRecord(applyName);
		return list.get(list.size()-1).getLeaveId();
	}

	/**
	 * 删除记录
	 * @param leaveId 记录id
	 * @return int
	 */
	public int deleteForDelLeaveRecord(int leaveId)
	{
		return leaveMapper.deleteForDelLeaveRecord(leaveId);
	}

	/**
	 * 添加请假申请
	 * @param applyName 申请人
	 * @param startDate 开始日期
	 * @param leaveDay 天数
	 * @param content 事由
	 * @return int
	 */
	public int insertForAddLeaveRecord(String applyName,String startDate,String leaveDay,String content)
	{
		AskForLeaveBean askForLeaveBean = new AskForLeaveBean();
		askForLeaveBean.setApplyName(applyName);
		askForLeaveBean.setContent(content);
		askForLeaveBean.setStartDate(startDate);
		askForLeaveBean.setLeaveDay(leaveDay);
		return leaveMapper.insertForAddLeaveRecord(askForLeaveBean);
	}
}
