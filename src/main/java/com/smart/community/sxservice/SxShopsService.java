package com.smart.community.sxservice;

import com.smart.community.sxbean.ShopUserLogin;
import com.smart.community.sxbean.ShopsInfo;
import com.smart.community.sxdao.SxShopsBuyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SxShopsService
{
	@Resource
	private SxShopsBuyMapper shopsBuyMapper;
	public ShopsInfo HotShowShops(int x)
	{
		ShopsInfo hotGoods = shopsBuyMapper.HotShowShops(x);
		return hotGoods;
	}
	public List<ShopsInfo> ViewShowShops()
	{
		List<ShopsInfo> viewShops = shopsBuyMapper.ViewShowShops();
		return viewShops;
	}
	public ShopUserLogin UserLoginToShop(String user,String pass)
	{
		ShopUserLogin login = shopsBuyMapper.UserLoginToShop(user,pass);
		return login;
	}

}
