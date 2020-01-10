package com.smart.community.venmapper;

import com.smart.community.venjavabean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ven
 * 工号管理接口
 */

@Mapper
public interface NoticeMapper
{
	/**
	 * 根据条件获取公告数据
	 * @param condition 查询条件对象
	 * @return 返回工号对象结果集
	 */
	public List<NoticeBean> getNoticeData(ConditionBean condition);

	/**
	 * 根据条件查询记录数
	 * @param condition 查询条件对象
	 * @return 返回符合条件的数据记录数
	 */
	public int countNoticeData(ConditionBean condition);

	/**
	 * 删除公告
	 * @param noticeId 目标公告的id
	 * @return 返回执行结果
	 */
	public int delNoticeById(@Param("noticeId") String noticeId);

	/**
	 * 新增公告
	 * @param  ajaxInfoBean 公告对象
	 * @return 执行结果
	 */
	public int addNewNotice(AjaxInfoBean ajaxInfoBean);


	public int updateNotice(AjaxInfoBean ajaxInfoBean);

}
