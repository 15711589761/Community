package com.smart.community.ljmdao;

import com.smart.community.ljmbean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LjmDeskMapper
{
	/**
	 * 前端登入验证
	 * @param loginBean 登入信息类
	 * @return 业主信息类
	 */
	public List<OwnerBean> selectForDeskLogin(LoginBean loginBean);

	/**
	 * 业主一级菜单获取
	 * @return 前端一级菜单集合
	 */
	public List<DeskMenuBean> selectForGetDeskMenu();

	/**
	 * 获取选中的父级菜单
	 * @param selectedMenuId 选中的父级菜单id
	 * @return 父级菜单信息类
	 */
	public DeskMenuBean selectForGetSelectedMenu(int selectedMenuId );

	/**
	 * 获取对应的子菜单
	 * @param menuParentId 父级菜单id
	 * @return 子菜单集合
	 */
	public List<DeskMenuBean> selectForGetDeskChildrenMenu( @Param ("menuParentId") int menuParentId );

	/**
	 * 投诉和建议
	 * @param tableSearchBean 搜索参数
	 * @return 投诉建议记录集合
	 */
	public List<SuggestBean> selectForGetSuggestTable( TableSearchBean tableSearchBean );

	/**
	 * 添加投诉或者建议
	 * @param suggestBean 添加信息
	 * @return int
	 */
	public int insertToSuggestRecord(SuggestBean suggestBean);

	/**
	 * 投诉或建议不展示
	 * @param suggestId id
	 * @return int
	 */
	public int deleteToSuggestRecord(String suggestId);

	/**
	 * 验证原密码
	 * @param roomNum 室号
	 * @param password 密码
	 * @return int
	 */
	public int selectForGetDeskPassWord(@Param("roomNum")String roomNum,@Param("password")String password);

	/**
	 * 修改密码
	 * @param roomNum 室号
	 * @param password 密码
	 * @return int
	 */
	public int updateForUpDeskPassword(@Param("roomNum")String roomNum,@Param("password")String password);

	/**
	 * 查看申请记录
	 * @param roomNum 房间号
	 * @param page 页数
	 * @param limit 每页条数
	 * @return 申请记录集合
	 */
	public List<ApplyRecordBean> selectForShowApplyTable (@Param("roomNum")String roomNum,@Param("page")int page,@Param("limit")int limit);

	/**
	 * 添加申请记录
	 * @param applyRecordBean 记录信息
	 * @return int
	 */
	public int insertForApplyRecord (ApplyRecordBean applyRecordBean);

	/**
	 * 修改申请状态
	 * @param status 状态
	 * @param applyId 申请id
	 * @return int
	 */
	public int updateForApplyStatus(@Param("status")String status,@Param("applyId")String applyId);

	/**
	 * 删除申请记录
	 * @param applyId 申请id
	 * @return int
	 */
	public int deleteForApplyRecord(@Param("applyId")String applyId);

	/**
	 * 查询此人的所有申请
	 * @param roomNum 房间号
	 * @return 集合
	 */
	public List<ApplyRecordBean> selectForGetLastApply(@Param("roomNum")String roomNum);

	/**
	 * 查询这个业主所有的投诉
	 * @param roomNum 房间号
	 * @return 集合
	 */
	public List<SuggestBean> selectForGetLastComplaint(@Param("roomNum")String roomNum);
}
