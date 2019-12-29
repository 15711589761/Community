package com.smart.community.ljmservice;

import com.smart.community.ljmdao.LjmVisitorMapper;
import com.smart.community.ljmbean.LayuiTableBean;
import com.smart.community.ljmbean.TableSearchBean;
import com.smart.community.ljmbean.VisitorBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 访客操作控制层
 * @author Ljm
 */
@Service
public class LjmVisitorService
{
	@Resource
	private LjmVisitorMapper visitorMapper;

	/**
	 * 获取访客表
	 * @param tableSearchBean param1 开始日期 param2结束日期
	 * @return 访客信息集合
	 */
	public LayuiTableBean getVisitorTable( TableSearchBean tableSearchBean )
	{
		int page = (tableSearchBean.getPage()-1)*tableSearchBean.getLimit();
		tableSearchBean.setPage(page);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		try
		{
			List<VisitorBean> visitorBeans = visitorMapper.selectForGetVisitorTable(tableSearchBean);
			int count = visitorMapper.selectForGetVisitorCount(tableSearchBean);
			layuiTableBean.setCode(0);
			layuiTableBean.setData(visitorBeans);
			layuiTableBean.setCount(count);
		} catch (Exception e)
		{
			System.err.println("-----查询访客表时出现异常-----");
			layuiTableBean.setCode(1);
			layuiTableBean.setMsg("系统繁忙，请重试");
		}
		return layuiTableBean;
	}

	public int insertForVisitorService( VisitorBean visitorBean )
	{
		return visitorMapper.insertForVisitor(visitorBean);
	}
}
