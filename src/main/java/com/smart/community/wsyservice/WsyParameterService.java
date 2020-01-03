package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyParameterMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_parameter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyParameterService
{
	@Resource
	private WsyParameterMapper wsyParameterMapper;

	public TableBean findByParameter(int page, String parameter_name){

		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);
		tableBean.setParameter_name(parameter_name);

		List<Tbl_parameter> list = wsyParameterMapper.findByParameterId(tableBean);

		tableBean.setCount(0);
		tableBean.setCount(wsyParameterMapper.findPageParameterId(tableBean));
		tableBean.setData(list);

        return tableBean;
	}

	//删除参数
	public int parameter(int parameter_id){
		int paramter = wsyParameterMapper.parmeterId(parameter_id);
		return paramter;
	}
	//增加参数
	public int addParameter(Tbl_parameter tbl_parameter){
		int addpar = wsyParameterMapper.insertParmeter(tbl_parameter);
		return addpar;
	}

	//修改参数
	public int updateParmeter(Tbl_parameter tbl_parameter){
		int updatePar = wsyParameterMapper.updateParmeter(tbl_parameter);
		return updatePar;
	}

}
