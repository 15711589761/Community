package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_owner;
import com.smart.community.wsyjavabean.Tbl_systemlog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsyLogMapper
{
	public List<Tbl_systemlog> findByLog( TableBean tableBean );//日志管理分页

	public int findLogPage( TableBean tableBean );//计算日志表总页数及分页查询

	public int saveLog( Tbl_systemlog tbl_systemlog );//增加日志

	public int delLog( int operation_id );//手动删除日志

	public int updateLog( Tbl_systemlog tbl_systemlog );

}
