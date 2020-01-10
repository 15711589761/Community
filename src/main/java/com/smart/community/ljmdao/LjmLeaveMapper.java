package com.smart.community.ljmdao;

import com.smart.community.ljmbean.AskForLeaveBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface LjmLeaveMapper
{
	/**
	 * 本人请假申请记录显示
	 * @param applyName 请假人
	 * @return 请假记录集合
	 */
	public List<AskForLeaveBean> selectForShowLeaveTable( @Param ("applyName")String applyName);

	/**
	 * 请假反馈
	 * @param feedback 反馈信息
	 * @param leaveId 请假id
	 * @return int
	 */
	public int updateForUpLeaveFeedback(@Param("feedback")String feedback,@Param("leaveId")int leaveId);

	/**
	 * 获取这个人的请假记录
	 * @param applyName 请假人
	 * @return 请假信息集合
	 */
	public List<AskForLeaveBean> selectForGetLastRecord( @Param("applyName")String applyName);

	/**
	 * 删除记录
	 * @param leaveId 记录id
	 * @return int
	 */
	public int deleteForDelLeaveRecord(@Param("leaveId")int leaveId);

	/**
	 * 添加记录
	 * @param askForLeaveBean 添加信息
	 * @return int
	 */
	public int insertForAddLeaveRecord( AskForLeaveBean askForLeaveBean);

}
