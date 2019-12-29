package com.smart.community.ljmcontroller;

import com.smart.community.activiti.Activiti;
import com.smart.community.ljmbean.*;
import com.smart.community.ljmservice.LjmDeskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端控制层
 * @author LJM
 */
@Controller
public class LjmDeskController
{

	@Resource
	private LjmDeskService deskService;


	/**
	 * 前端主界面跳转
	 * @return 界面
	 */
	@RequestMapping("/toDeskHome.view")
	public ModelAndView toDeskHomeView()
	{
		ModelAndView modelAndView = new ModelAndView("ljm_desk_home");
		modelAndView.addObject("loginSuccess",null);
		modelAndView.addObject("menu",null);
		return modelAndView;
	}

	/**
	 * 跳转到登录界面
	 * @return 返回登入界面
	 */
		@RequestMapping("/toDeskLogin.view")
	public ModelAndView toDeskLoginView()
	{
		return new ModelAndView("ljm_desk_login");
	}

	/**
	 * 前端登入验证
	 * @param loginBean 登录信息
	 * @param bindingResult 登录信息后台验证
	 * @param request httpRequest
	 * @return 相应信息和界面
	 */
	@RequestMapping("/deskLogin.action")
	public ModelAndView loginAndToDeskHome( @Validated LoginBean loginBean, BindingResult bindingResult, HttpServletRequest request )
	{
		ModelAndView modelAndView = new ModelAndView();
		List isHad = (List) request.getSession().getAttribute("owners");
		if (null != isHad)
		{
			//返回一级菜单
			List<DeskMenuBean> deskMenuBeans = deskService.forGetParentDeskMenu();
			modelAndView.addObject("loginSuccess",isHad);
			modelAndView.addObject("menu",deskMenuBeans);
			modelAndView.setViewName("ljm_desk_home");
		} else {
			StringBuilder stringBuilder = new StringBuilder();
			if (null != bindingResult && bindingResult.hasErrors())
			{
				List<ObjectError> errorMsg = bindingResult.getAllErrors();
				for (ObjectError objectError : errorMsg)
				{
					stringBuilder.append(objectError.getDefaultMessage()).append(";");
				}
				modelAndView.addObject("isEmpty", stringBuilder.toString());
				modelAndView.setViewName("ljm_desk_login.jsp");
			} else {
				//service验证业主登入信息
				loginBean.setLoginName(loginBean.getLoginName().toUpperCase());
				List<OwnerBean> ownerBeans = deskService.deskLoginService(loginBean);
				if (null != ownerBeans && ownerBeans.size() > 0)
				{
					//返回一级菜单
					List<DeskMenuBean> deskMenuBeans = deskService.forGetParentDeskMenu();
					modelAndView.addObject("menu",deskMenuBeans);
					modelAndView.setViewName("ljm_desk_home");
					modelAndView.addObject("loginSuccess",ownerBeans);
					request.getSession().setAttribute("owners",ownerBeans);
					request.getSession().setAttribute("menus",deskMenuBeans);
				} else {
					modelAndView.addObject("loginFail","账号密码错误或是您还没有到物业处登记使用");
					modelAndView.setViewName("ljm_desk_login");
				}
			}
		}
		return modelAndView;
	}

	/**
	 * 主界面二级菜单界面
	 * @param parentMenuId 父级菜单
	 * @param request request
	 * @return 二级菜单界面
	 */
	@RequestMapping("/toChildren.view")
	public ModelAndView toChildrenMenuView( int parentMenuId ,HttpServletRequest request )
	{
		List owners = (List) request.getSession().getAttribute("owners");
		List parentMenu = (List) request.getSession().getAttribute("menus");
		ModelAndView modelAndView = new ModelAndView("ljm_desk_login_home");
		modelAndView.addObject("loginSuccess",owners);
		modelAndView.addObject("menu",parentMenu);
		//查询选中的父级菜单
		DeskMenuBean deskMenuBean = deskService.forGetSelectedParentMenu(parentMenuId);
		modelAndView.addObject("selectedMenu",deskMenuBean.getMenuName());
		//查询子级菜单集合
		List<DeskMenuBean> childrenDeskMenu = deskService.forGetChildrenDeskMenu(parentMenuId);
		modelAndView.addObject("childMenu",childrenDeskMenu);
		return modelAndView;
	}

	/**
	 * 跳转到用户建议界面
	 * @return 业主投诉和建议界面
	 */
	@RequestMapping("/toSuggestOwner.view")
	public ModelAndView toSuggestView()
	{
		return new ModelAndView("ljm_owner_suggest_table");
	}

	/**
	 * 业主建议表显示查询
	 * @param tableSearchBean 搜索信息
	 * @param request request
	 * @return layui表格接口
	 */
	@RequestMapping("/forGetSuggestTable.action")
	@ResponseBody
	public LayuiTableBean forGetSuggestTable(TableSearchBean tableSearchBean ,HttpServletRequest request)
	{
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		String roomNum = list.get(0).getOwnerRoom();
		tableSearchBean.setRoomNum(roomNum);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		List<SuggestBean> showTable = new ArrayList<>();
		List<SuggestBean> suggestBeans = deskService.forGetSuggestTable(tableSearchBean);
		if (suggestBeans!=null&&suggestBeans.size()>0)
		{
			layuiTableBean.setCode(0);
			for (SuggestBean suggestBean : suggestBeans)
			{
				if ("建议".equals(suggestBean.getSuggestRemark()))
				{
					showTable.add(suggestBean);
				}
			}
			layuiTableBean.setData(showTable);
		} else {
			layuiTableBean.setCode(1);
			layuiTableBean.setMsg("没有过投诉或建议");
		}

		return layuiTableBean;
	}

	/**
	 * 添加建议或投诉
	 * @param suggestName 发起人
	 * @param suggestPhone 联系方式
	 * @param suggestContext 内容
	 * @param request request
	 * @param remark 标识
	 * @return 信息
	 */
	@RequestMapping("/insertSuggestRecord.action")
	@ResponseBody
	public LayuiTableBean addSuggestRecord (String suggestName , String suggestPhone , String suggestContext , String remark , HttpServletRequest request)
	{
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		String roomNum = list.get(0).getOwnerRoom();
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = deskService.insertSuggestRecordService(suggestName,suggestPhone,suggestContext,remark,roomNum);
		if (result>0)
		{
			layuiTableBean.setMsg("提交成功");
		} else {
			layuiTableBean.setMsg("提交失败");
		}
		return layuiTableBean;
	}

	/**
	 * 删除投诉或建议
	 * @param suggestId 投诉或建议id
	 * @return 信息
	 */
	@RequestMapping("/deleteSuggestRecord.action")
	@ResponseBody
	public LayuiTableBean deleteSuggestRecord (String suggestId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = deskService.deleteSuggestRecordService(suggestId);
		if (result>0)
		{
			layuiTableBean.setMsg("删除成功");
		} else {
			layuiTableBean.setMsg("删除失败");
		}
		return layuiTableBean;
	}

	/**
	 * 跳转到用户投诉界面
	 * @return 业主投诉和建议界面
	 */
	@RequestMapping("/toComplaintOwner.view")
	public ModelAndView toComplaintTable(String remark)
	{
		return new ModelAndView("ljm_owner_complaint_table");
	}

	/**
	 * 业主投诉表显示查询
	 * @param tableSearchBean 搜索信息
	 * @param request request
	 * @return layui表格接口
	 */
	@RequestMapping("/fotGetComplaintTable.action")
	@ResponseBody
	public LayuiTableBean fotGetComplaintTable(TableSearchBean tableSearchBean,HttpServletRequest request)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		String roomNum = list.get(0).getOwnerRoom();
		tableSearchBean.setRoomNum(roomNum);
		List<SuggestBean> suggestBeans = deskService.forGetSuggestTable(tableSearchBean);
		List<SuggestBean> showTable = new ArrayList<>();
		if (suggestBeans!=null&&suggestBeans.size()>0)
		{
			layuiTableBean.setCode(0);
			for (SuggestBean suggestBean : suggestBeans)
			{
				if ("投诉".equals(suggestBean.getSuggestRemark()))
				{
					showTable.add(suggestBean);
				}
			}
			layuiTableBean.setData(showTable);
		} else {
			layuiTableBean.setCode(1);
			layuiTableBean.setMsg("没有过投诉或建议");
		}
		return layuiTableBean;
	}
}
