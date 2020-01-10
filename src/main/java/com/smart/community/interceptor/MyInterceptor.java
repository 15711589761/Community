//package com.smart.community.interceptor;
//
//import com.smart.community.ljmbean.OwnerBean;
//import com.smart.community.ljmbean.StaffBean;
//import com.smart.community.tool.LjmTool;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 路径筛选
// *
// * @author LJM
// */
//public class MyInterceptor extends HandlerInterceptorAdapter
//{
//	@Override
//	public boolean preHandle( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o ) throws Exception
//	{
//		String url = httpServletRequest.getRequestURI();
//		StaffBean staffBean = (StaffBean) httpServletRequest.getSession().getAttribute("staffBean");
//		List<OwnerBean> owners = (List) httpServletRequest.getSession().getAttribute("owners");
//		if (url.endsWith(LjmTool.DESK_HOME)||url.endsWith(LjmTool.DESK_LOGIN_VIEW)||url.endsWith(LjmTool.DESK_LOGIN_ACTION))
//		{
//			if (null != owners)
//			{
//				return true;
//			} else {
//				httpServletResponse.sendRedirect(LjmTool.PROJECT_PATH+"/jsp/ljm_desk_login.jsp");
//			}
//		} else if (url.endsWith(LjmTool.BACKSTAGE_LOGIN_VIEW)||url.endsWith(LjmTool.BACKSTAGE_LOGIN_ACTION)||url.endsWith(LjmTool.LOGIN_VERIFY))
//		{
//			if (null != staffBean)
//			{
//				return true;
//			} else {
//				httpServletResponse.sendRedirect(LjmTool.PROJECT_PATH+"/jsp/ljm_backstage_login.jsp");
//			}
//		} else {
//			httpServletResponse.sendRedirect(LjmTool.PROJECT_PATH+"/jsp/ljm_not_find.jsp");
//		}
//		return false;
//	}
//
//	@Override
//	public void postHandle( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView ) throws Exception
//	{
//
//	}
//
//	@Override
//	public void afterCompletion( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e ) throws Exception
//	{
//
//	}
//}
