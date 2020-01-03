//package com.smart.community.interceptor;
//
//import com.smart.community.ljmbean.LoginBean;
//import com.smart.community.ljmbean.OwnerBean;
//import com.smart.community.ljmbean.StaffBean;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
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
//public class BackstageInterceptor implements HandlerInterceptor
//{
//	@Override
//	public boolean preHandle( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o ) throws Exception
//	{
//		String url = httpServletRequest.getRequestURI();
//		StaffBean staffBean = (StaffBean) httpServletRequest.getSession().getAttribute("staffBean");
//		List<OwnerBean> owners = (List) httpServletRequest.getSession().getAttribute("owners");
//		if (url.endsWith("/toDeskHome.view") || url.endsWith("/toDeskLogin.view") || url.endsWith("/toDeskLogin.action") || owners != null || url.endsWith("/toBackstageLogin.view") || url.endsWith("/backstageLogin.action") || null != staffBean)
//		{
//			return true;
//		}
//
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
