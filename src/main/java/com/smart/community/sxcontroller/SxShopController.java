package com.smart.community.sxcontroller;

import com.smart.community.sxbean.ShopUserLogin;
import com.smart.community.sxbean.ShopsInfo;
import com.smart.community.sxdao.SxShopsBuyMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class SxShopController
{

	@Resource
	SxShopsBuyMapper shopsBuyMapper;

	@RequestMapping("/toShop.view")
	public ModelAndView toShop(HttpServletRequest request, HttpServletResponse response)
	{
		ShopsInfo hotItemsOne = shopsBuyMapper.HotShowShops(2);
		ShopsInfo hotItemsTwo = shopsBuyMapper.HotShowShops(3);
		ShopsInfo hotItemsThree = shopsBuyMapper.HotShowShops(4);
		ShopsInfo hotItemsFour = shopsBuyMapper.HotShowShops(5);
		ShopsInfo offItem = shopsBuyMapper.HotShowShops(9);
		List<ShopsInfo> viewShowItems= shopsBuyMapper.ViewShowShops();
		ShopUserLogin loginInfo=null;
		ModelAndView modelAndView=new ModelAndView();
		String user = request.getParameter("user");
		String pass = request.getParameter("password");
		if (user!=null && pass!=null)
		{
			 loginInfo = shopsBuyMapper.UserLoginToShop(user,pass);
			 if (loginInfo != null){
				 HttpSession session = request.getSession();
				 session.setAttribute("user", loginInfo.getRoomNumber());//设置需要的参数（key-value）
//				 session.invalidate(); //删除所有session中保存的键
				 modelAndView.setViewName("sx_store_index");
				 modelAndView.addObject("hotOne",hotItemsOne);
				 modelAndView.addObject("hotTwo",hotItemsTwo);
				 modelAndView.addObject("hotThree",hotItemsThree);
				 modelAndView.addObject("hotFour",hotItemsFour);
				 modelAndView.addObject("offShops",offItem);
				 modelAndView.addObject("viewShops",viewShowItems);
				 return modelAndView;

			 }else
			 	{
				    modelAndView.setViewName("sx_store_login");
				    return modelAndView;
			    }

		}



		modelAndView.addObject("hotOne",hotItemsOne);
		modelAndView.addObject("hotTwo",hotItemsTwo);
		modelAndView.addObject("hotThree",hotItemsThree);
		modelAndView.addObject("hotFour",hotItemsFour);
		modelAndView.addObject("offShops",offItem);
		modelAndView.addObject("viewShops",viewShowItems);
		modelAndView.setViewName("sx_store_index");
		return modelAndView;
	}
	@RequestMapping("/toShopLogin.view")
	public ModelAndView toShopLogin()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("sx_store_login");
		return modelAndView;
	}



	@RequestMapping("/toShopItems.view")
	public ModelAndView toShopItems()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("sx_store_items");
		return modelAndView;
	}





}
