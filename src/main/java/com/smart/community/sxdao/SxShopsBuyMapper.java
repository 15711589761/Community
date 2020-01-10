package com.smart.community.sxdao;
import com.smart.community.sxbean.ShopUserLogin;
import com.smart.community.sxbean.ShopsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SxShopsBuyMapper
{
	public ShopsInfo HotShowShops(@Param("goods_shops_type")int x);
	public List<ShopsInfo> ViewShowShops();
	public ShopUserLogin UserLoginToShop(@Param("user")String user,@Param("pass")String pass);
}
