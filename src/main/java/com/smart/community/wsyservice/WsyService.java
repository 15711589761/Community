package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_owner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyService
{
	@Resource
	private WsyMapper wsyMapper;

	//业主表格数据回显和查询
		public TableBean findByOwner(int page, String owner_name, String owner_tel, String owner_status, String owner_identity)
		{
			System.out.println("当前页条数.." + page);
			int i = (page - 1) * 5;
			TableBean tableBean = new TableBean();
			tableBean.setPage(i);//获取当前页数
			tableBean.setOwner_name(owner_name);
			tableBean.setOwner_tel(owner_tel);
			tableBean.setOwner_status(owner_status);
			tableBean.setOwner_identity(owner_identity);
			List<Tbl_owner> list = wsyMapper.findByOwner(tableBean);

		tableBean.setCount(0);
		tableBean.setCount(wsyMapper.findByOwnerPage(tableBean));//计算页数

		tableBean.setData(list);//将list（表格数据）传到界面

		return tableBean;

	}

	//删除业主
	public int delOwner(int owner_id)
	{
		int ownerId = wsyMapper.delOwner(owner_id);

		return ownerId;
	}

	//增加业主
	public int addOwner(Tbl_owner tbl_owner)
	{

		int ownerName = wsyMapper.addOwner(tbl_owner);


		return ownerName;
	}

	//修改业主信息
	public int updateOwner(Tbl_owner tbl_owner)
	{

		return wsyMapper.updateOwner(tbl_owner);
	}


}
