package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyCollectionMapper;
import com.smart.community.wsyjavabean.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyCollectionService
{
	@Resource
	private WsyCollectionMapper wsyCollectionMapper;

	//获取收入记录表，and 查询功能
	public TableBean findByCollection(int page, String receivables_type, String receivables_time, String receivables_remarks)
	{
		System.out.println("当前页条数.." + page);
		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);//获取当前页数
		tableBean.setReceivables_type(receivables_type);
		tableBean.setReceivables_time(receivables_time);
		tableBean.setReceivables_remarks(receivables_remarks);

		List<Tbl_receivables> list = wsyCollectionMapper.findByCollection(tableBean);
		for (Tbl_receivables tbl_receivables : list){
			System.out.println("收入记录..."+tbl_receivables.toString());
		}
		tableBean.setCount(0);
		tableBean.setCount(wsyCollectionMapper.findCollectionPage(tableBean));//计算页数

		tableBean.setData(list);//将list（表格数据）传到界面

		return tableBean;

	}

	//删除收款记录
	public int delReceivables(int receivables_Id){
		int delReceivable = wsyCollectionMapper.delCollection(receivables_Id);
		return delReceivable;
	}

	//增加收款记录
	public int addCollection(Tbl_receivables tbl_receivables){

		int add_Collection = wsyCollectionMapper.addCollection(tbl_receivables);
		return add_Collection;
	}
	//修改收款记录
	public int updateCollection(Tbl_receivables tbl_receivables){
		int update_Collection = wsyCollectionMapper.updateCollection(tbl_receivables);
		return update_Collection;
	}

	//统计收款记录
	public List<Wsy_EcharsBean> collectionCountMap(){
		return wsyCollectionMapper.collectionCountMap();
	}
}
