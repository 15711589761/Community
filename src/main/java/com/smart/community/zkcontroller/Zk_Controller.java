package com.smart.community.zkcontroller;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.TableBean;
import com.smart.community.zkbean.Zk_FacilityBean;
import com.smart.community.zkservice.Zk_Service;
import com.smart.community.tool.Access_token;
import com.smart.community.tool.BaseImg64;
import com.smart.community.tool.HttpUtil;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class Zk_Controller
{
	@Resource
	private Zk_Service zk_service;

	@RequestMapping("/facilityJsp")
	public String facilityJsp()
	{
		return "zk_back_facility";
	}

	@RequestMapping("/findFacility")
	@ResponseBody
	public TableBean findFacility(String name, String beginDate, String endDate, String page, String limit){
		ParameterBean parameterBean=new ParameterBean();
		parameterBean.setName(name);
		parameterBean.setBeginDate(beginDate);
		parameterBean.setEndDate(endDate);
		parameterBean.setPage(((Integer.valueOf(page) - 1) * Integer.valueOf(limit)));
		parameterBean.setLimit(Integer.valueOf(limit));
		TableBean tableBean=new TableBean();
		List<Zk_FacilityBean> facilityBeans=zk_service.findFacility(parameterBean);
		tableBean.setCode(0);
		tableBean.setData(facilityBeans);
		tableBean.setMsg("");
		tableBean.setCount(zk_service.countFacility(parameterBean));
		return tableBean;
	}
	@RequestMapping("/setFacilityNum")
	@ResponseBody
	public int setFacilityNum(Zk_FacilityBean zk_facilityBean){
		return zk_service.setFacilityNum(zk_facilityBean);
	}
	@RequestMapping("/delFacility")
	@ResponseBody
	public int delFacility(Zk_FacilityBean zk_facilityBean){
		return zk_service.delFacility(zk_facilityBean.getFacilityID());
	}
	@RequestMapping("/addFacility")
	@ResponseBody
	public int addFacility(Zk_FacilityBean zk_facilityBean){
		return zk_service.addFacility(zk_facilityBean);
	}

	@RequestMapping("/License plate recognition")
	@ResponseBody
	public Map getCarNumber(MultipartFile file){
		FileOutputStream out = null;
		String fileName = file.getOriginalFilename();
		String path="../Community/src/main/resources/static/"+fileName;
		File newFile=null;
		Map map=null;

		// 通用识别url
		String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
		try {
			//复制到临时文件中
			newFile=new File(path);
			out=new FileOutputStream(newFile);
			IOUtils.copy(file.getInputStream(),out);

			String imgStr = BaseImg64.getImageStrFromPath(path);
			String params = URLEncoder.encode("image", "UTF-8") + "=" + imgStr;
			/**
			 * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			 */
			String accessToken = Access_token.getAuth();
			String result = HttpUtil.post(otherHost, accessToken, params);
			System.out.println(result);

			//解析JSON串
			JSONObject data = new JSONObject(result);
			map=(Map) data.toMap().get("words_result");
			map.put("code",200);
			map.put("msg",map.get("number"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code",500);
		} finally {
			// 完毕，关闭所有链接
			try {
				out.close();
				if (newFile.exists())
				{
					newFile.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@RequestMapping("/test")
	public String test()
	{
		return "test";
	}



	public static void main(String[] args)
	{
		// 通用识别url
		String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
		// 本地图片路径
		String filePath = "C:\\Users\\ZKa1\\Desktop\\car1.jpg";

		try {
			String imgStr = BaseImg64.getImageStrFromPath(filePath);
			String params = URLEncoder.encode("image", "UTF-8") + "=" + imgStr;
			/**
			 * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			 */
			String accessToken = Access_token.getAuth();
			String result = HttpUtil.post(otherHost, accessToken, params);
			System.out.println(result);

			//解析JSON串
			JSONObject data = new JSONObject(result);
			Map map=(Map) data.toMap().get("words_result");
			System.out.println(map.get("number"));



		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
