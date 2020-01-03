
/*Created by IntelliJ IDEA.
		User: 魏书源
		Date: 2019/12/18
		Time: 15:37
To change this template use File | Settings | File Templates.*/
package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_parameter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface WsyParameterMapper
{

	public List<Tbl_parameter> findByParameterId(TableBean tableBean);
	public int findPageParameterId(TableBean tableBean);
	//删除参数
	public int parmeterId(int parameter_id);
	//增加参数
	public int insertParmeter(Tbl_parameter tbl_parameter);
	//修改参数
	public int updateParmeter(Tbl_parameter tbl_parameter);

}
