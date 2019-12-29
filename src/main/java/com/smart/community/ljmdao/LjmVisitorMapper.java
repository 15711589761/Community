package com.smart.community.ljmdao;

import com.smart.community.ljmbean.TableSearchBean;
import com.smart.community.ljmbean.VisitorBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LjmVisitorMapper
{
	/**
	 * 访客表搜索
	 * @param tableSearchBean param1 开始日期 param2结束日期
	 * @return 访客表信息
	 */
	public List<VisitorBean> selectForGetVisitorTable ( TableSearchBean tableSearchBean );

	/**
	 * 访客表搜索
	 * @param tableSearchBean param1 开始日期 param2结束日期
	 * @return 总记录
	 */
	public int selectForGetVisitorCount( TableSearchBean tableSearchBean );

	/**
	 * 访客添加
	 * @param visitorBean 访客实体类
	 * @return int
	 */
	public int insertForVisitor( VisitorBean visitorBean );

	/**
	 * 清理访客记录
	 * @param date 这个日期之前的记录全部清理
	 * @return int
	 */
	public int deleteForClearVisitor( @Param ("date") String date);
}
