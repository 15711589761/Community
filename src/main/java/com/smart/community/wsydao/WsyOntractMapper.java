package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_ontract;
import com.smart.community.wsyjavabean.Tbl_systemlog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsyOntractMapper
{
	//合同管理分页
	public List<Tbl_ontract> findByOntract( TableBean tableBean );
	//计算合同管理表总页数及分页查询
	public int findByOntractPage( TableBean tableBean );
    //删除合同
	public int delOntract( Tbl_ontract tbl_ontract );
	//上传合同
	public int addOntract( Tbl_ontract tbl_ontract );
	//查看合同
	public Tbl_ontract findontractId( int ontract_id );

}
