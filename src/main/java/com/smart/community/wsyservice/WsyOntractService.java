package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyOntractMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_ontract;
import com.smart.community.wsyjavabean.Tbl_parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyOntractService
{
	@Resource
	private WsyOntractMapper wsyOntractMapper;

	public TableBean findByOntract(int page, String ontract_name,String startDate,String endDate){

		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);
		tableBean.setOntract_name(ontract_name);
		tableBean.setStartDate(startDate);
		tableBean.setEndDate(endDate);

		List<Tbl_ontract> list = wsyOntractMapper.findByOntract(tableBean);
for (Tbl_ontract tbl_ontract:list){
	System.out.println("数据..."+list.toString());
}
		System.out.println("nnn"+wsyOntractMapper.findByOntractPage(tableBean));
		tableBean.setCount(wsyOntractMapper.findByOntractPage(tableBean));

		tableBean.setData(list);

		return tableBean;
	}

	//删除合同
	public int delOntract(Tbl_ontract tbl_ontract){
		int delOnt = wsyOntractMapper.delOntract(tbl_ontract);
		return delOnt;
	}

	//上传合同
	@Transactional

	public int addOntract(Tbl_ontract tbl_ontract){

		return wsyOntractMapper.addOntract(tbl_ontract);
	}

	//查看合同
	public Tbl_ontract findontractId(int ontract_id){
		return wsyOntractMapper.findontractId(ontract_id);
	}
}
