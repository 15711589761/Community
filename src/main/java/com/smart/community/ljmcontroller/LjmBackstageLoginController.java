package com.smart.community.ljmcontroller;

import com.smart.community.ljmbean.*;
import com.smart.community.ljmservice.LjmBackstageLoginService;
import com.smart.community.tool.RandomValidateCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 后台登入
 * @author LJM
 */
@Controller
public class LjmBackstageLoginController
{
	@Resource
	private LjmBackstageLoginService backstageLoginService;

	/**
	 * 登入界面跳转
	 *
	 * @param request request
	 * @param response response
	 * @return 页面
	 */
	@RequestMapping("/toBackstageLogin.view")
	public ModelAndView toBackstageLogin( HttpServletRequest request, HttpServletResponse response )
	{
		request.getSession().removeAttribute("staffBean");
		ModelAndView modelAndView = new ModelAndView("ljm_backstage_login");
		return modelAndView;
	}

	/**
	 * 生成验证码
	 */
	@GetMapping("/getVerify.image")
	public void getVerify( HttpServletRequest request, HttpServletResponse response )
	{
		backstageLoginService.getVerify(request, response);
	}

	/**
	 * 登入验证
	 */
	@RequestMapping("/backstageLogin.action")
	public ModelAndView backstageLoginAction( @Validated LoginBean loginBean, BindingResult bindingResult, HttpServletRequest request )
	{
		StaffBean isHad = (StaffBean) request.getSession().getAttribute("staffBean");
		ModelAndView modelAndView = new ModelAndView();
		if (null == isHad)
		{
			StringBuilder stringBuilder = new StringBuilder();
			String verification = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
			if (null != bindingResult && bindingResult.hasErrors())
			{
				List<ObjectError> errorMsg = bindingResult.getAllErrors();
				for (ObjectError objectError : errorMsg)
				{
					stringBuilder.append(objectError.getDefaultMessage()).append(";");
				}
				System.out.println(stringBuilder.toString());
				modelAndView.addObject("isEmpty", stringBuilder.toString());
				modelAndView.setViewName("ljm_backstage_login");
			} else
			{
				if (verification.equals(loginBean.getImageCode().toUpperCase()))
				{
					StaffBean staffBean = backstageLoginService.backstageLoginService(loginBean);
					if (null != staffBean)
					{
						if ("在职".equals(staffBean.getStaffStatus()))
						{
							Map<String, List<BackstageMenuBean>> menuMap = backstageLoginService.backstageMenuTake(staffBean.getStaffId());
							modelAndView.setViewName("ljm_backstage_main_view");
							modelAndView.addObject("staff", staffBean);
							modelAndView.addObject("menu", menuMap);
							request.getSession().setAttribute("staffBean", staffBean);
						} else
						{
							modelAndView.setViewName("ljm_backstage_login");
							modelAndView.addObject("authority", "您暂时没有这个权限");
						}
					} else
					{
						modelAndView.setViewName("ljm_backstage_login");
						modelAndView.addObject("error", "账号或密码错误");
					}

				} else
				{
					System.out.println("验证码错误");
					modelAndView.setViewName("ljm_backstage_login");
					modelAndView.addObject("verification", "验证码错误");
				}
			}
		} else {
			Map<String, List<BackstageMenuBean>> menuMap = backstageLoginService.backstageMenuTake(isHad.getStaffId());
			modelAndView.setViewName("ljm_backstage_main_view");
			modelAndView.addObject("staff", isHad);
			modelAndView.addObject("menu", menuMap);
		}
		return modelAndView;
	}

	/**
	 * 跳转到修改密码的界面
	 * @return 界面
	 */
	@RequestMapping("/toUpdatePassWord.view")
	public ModelAndView toUpdatePassWordView()
	{
		return new ModelAndView("ljm_update_password");
	}

	/**
	 * 跳转到基本资料的界面
	 * @return 界面
	 */
	@RequestMapping("/toLookPersonMessage.view")
	public ModelAndView toLookPersonMessage(HttpServletRequest request)
	{
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		ModelAndView modelAndView = new ModelAndView("ljm_backstage_staff_message");
		modelAndView.addObject("staff",staffBean);
		return modelAndView;
	}

	@RequestMapping("/updateBackstagePassWord.action")
	@ResponseBody
	public LayuiTableBean updateBackstagePassWord(String oldPass,String newPass,HttpServletRequest request)
	{
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int isExist = backstageLoginService.selectForExistStaff(staffBean.getStaffJobNum(),oldPass);
		if (isExist>0)
		{
			int result = backstageLoginService.updateForUpPassword(newPass,staffBean.getStaffId());
			if (result>0)
			{
				layuiTableBean.setMsg("修改成功");
			} else {
				layuiTableBean.setMsg("系统忙，请重试");
			}
		} else {
			layuiTableBean.setMsg("原密码错误");
		}
		return layuiTableBean;
	}

}
