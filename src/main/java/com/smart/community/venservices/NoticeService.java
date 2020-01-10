package com.smart.community.venservices;

import com.smart.community.venjavabean.AjaxInfoBean;
import com.smart.community.venjavabean.ConditionBean;
import com.smart.community.venjavabean.StaffBean;
import com.smart.community.venjavabean.TableBean;
import com.smart.community.venmapper.JobNumberMapper;
import com.smart.community.venmapper.NoticeMapper;
import com.smart.community.ventool.CodeTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeService

{
	@Resource
	private NoticeMapper noticeMapper;


	/**
	 * 新增公告
	 * @param ajaxInfoBean 新增公告的ajax对像
	 * @return 执行结果
	 */

	public int addNewNotice(AjaxInfoBean ajaxInfoBean){

		return noticeMapper.addNewNotice(ajaxInfoBean);
	}

	/**
	 * 删除公告
	 * @param noticeId 目标公告的id
	 * @return 执行结果int值
	 */
	public int delNoticeById(String noticeId){

		return noticeMapper.delNoticeById(noticeId);

	}

	/**
	 * 修改公告信息
	 * @param ajaxInfoBean ajax数据对象
	 * @return 执行结果
	 */
	public int updateNotice(AjaxInfoBean ajaxInfoBean){

		return noticeMapper.updateNotice(ajaxInfoBean);
	}

	/**
	 * 查询公告
	 * @param condition 查询条件对象
	 * @return layui需要的结果对象
	 */
	public TableBean queryNotice(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");

		tableBean.setData(noticeMapper.getNoticeData(condition));
		tableBean.setCount(noticeMapper.countNoticeData(condition));
		return tableBean;
	}
}


