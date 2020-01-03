package com.smart.community.sxcontroller;

import com.smart.community.sxbean.CheckAdd;
import com.smart.community.sxbean.CheckResolving;
import com.smart.community.sxbean.LayuiTableBean;
import com.smart.community.sxservice.CheckService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class SxCheckController
{
	@Resource
	CheckService checkService;


	@RequestMapping("/toCheckInfo.view")
	public ModelAndView toCheckInfo()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("equipmentCheck");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("/checkQuery.action")
	public LayuiTableBean documentQuery(){

		List list = checkService.CheckInfo();
		//		int startNum=1*(page-1)*limit+1;
		//		int lastNum=page*limit;
		//	int count = userDaoService.count();
		int count = 5;

		LayuiTableBean tableDate=new LayuiTableBean();
		tableDate.setCode(0);
		tableDate.setMsg("");
		tableDate.setCount(count);
		tableDate.setData(list);

		return tableDate;
	}

	@RequestMapping("/download.action")
	public ResponseEntity<byte[]> downLoadFile ()
	{

		try
		{
			File file = new File("D:/demo/model.xlsx");
			HttpHeaders headers = new HttpHeaders();

			// MediaType:互联网媒介类型 contentType：具体请求中的媒体类型信息
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

			headers.setContentDispositionFormData("attachment", file.getName());

			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}



	@RequestMapping("/upload.action")
	@ResponseBody
	public Map<String,Object> uploadfile(MultipartFile file){
		Map map = new HashMap<String,Object>();
		String FNAME = file.getOriginalFilename();
		String temp[] = FNAME.split("\\.");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		CheckAdd checkAdd = new CheckAdd();
		CheckResolving checkResolving = new CheckResolving();
		System.out.println(temp[0]);
		System.out.println(df.format(new Date()));
		System.out.println(temp[temp.length-1]);
		System.out.println("D:/demo/"+file.getOriginalFilename());
		try
		{
			file.transferTo(new File("D:/demo/"+file.getOriginalFilename()));
			String url = "D:/demo/"+file.getOriginalFilename();
			List<List<Object>> list = checkResolving.getBankListByExcel(url);
			for (int i = 0; i < list.size(); i++)
			{

					checkAdd.setCheckPrincipal(list.get(i).get(4).toString());
					checkAdd.setCheckStatus(list.get(i).get(3).toString());
					checkAdd.setExamineId(list.get(i).get(0).toString());
					checkAdd.setCheckDate(df.format(new Date()));


				int x = checkService.insertCheckInfo(checkAdd);
				System.out.println(x);
			}
			map.put("msg","ok");
			map.put("code",200);
		} catch (IOException e)
		{
			map.put("msg","error");
			map.put("code",0);
			e.printStackTrace();
		}
		return map;
	}

}
