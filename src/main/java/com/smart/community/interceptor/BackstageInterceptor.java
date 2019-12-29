//package com.smart.community.interceptor;
//
//import com.smart.community.ljmbean.LoginBean;
//import com.smart.community.ljmbean.StaffBean;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 路径筛选
// * @author LJM
// */
//public class BackstageInterceptor implements HandlerInterceptor
//{
//	@Override
//	public boolean preHandle( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o ) throws Exception
//	{
//		String url = httpServletRequest.getRequestURI();
//		StaffBean staffBean = (StaffBean) httpServletRequest.getSession().getAttribute("staffBean");
//		if (url.endsWith("/toDeskHome.view"))
//		{
//			return true;
//		}
//		if (url.endsWith("/toBackstageLogin.view") || url.endsWith("/backstageLogin.action") || null != staffBean)
//		{
//			return true;
//		}
//		httpServletRequest.getRequestDispatcher("jsp/ljm_backStage_login.jsp").forward(httpServletRequest,httpServletResponse);
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
