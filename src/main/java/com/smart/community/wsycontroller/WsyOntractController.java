package com.smart.community.wsycontroller;

import com.smart.community.tool.Docx2Html;
import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_ontract;
import com.smart.community.wsyservice.WsyOntractService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(value = "downUrl")
public class WsyOntractController
{
	@Resource
	private WsyOntractService wsyOntractService;

	//文件保存的路径
	public final static String ONTRACT_PATH = "\\src\\main\\resources\\file_upload\\";

	@RequestMapping("/ontract.action")
	@Log(operationType="ht",operationName="查看合同管理")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_ontract");
	}

	@RequestMapping(value = "/ontractTable.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="合同管理")
	public TableBean ontracTable(int page, String ontract_name,String startDate, String endDate)
	{


		TableBean tableBean = wsyOntractService.findByOntract(page,ontract_name,startDate,endDate);

		return tableBean;
	}

	//删除合同
	@RequestMapping(value = "delOntract.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="删除合同")
	public TableBean delOntract(Tbl_ontract tbl_ontract,@RequestParam(value = "file") MultipartFile multipartFile,HttpServletRequest request){
		int delOnt = wsyOntractService.delOntract(tbl_ontract);

		TableBean tableBean = new TableBean();
		String ontract_name = request.getParameter("ontract_name");
		String orginalFilename = multipartFile.getOriginalFilename();
		String suffix = orginalFilename.substring(orginalFilename.lastIndexOf(".") + 1);
		String newFile = ontract_name + "." + suffix;
		String uploadPath = System.getProperty("user.dir") + ONTRACT_PATH + newFile ;
		//File oldFile = new File();
		if (delOnt > 0){
			tableBean.setMsg("1");
			System.out.println("合同删除成功！");
		}else {
			System.out.println("合同删除失败！");
		}
		return tableBean;
	}

	//上传合同
	@RequestMapping(value = "insertOntract.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="上传合同")
	public Map<String,Object> addOntract(@RequestParam(value = "file") MultipartFile multipartFile,HttpServletRequest request){

		Map<String,Object> map = new HashMap<>();
		String ontract_name = request.getParameter("ontract_name");
		String ontract_uploadPath = request.getParameter("ontract_uploadPath");
		String ontract_time = request.getParameter("ontract_time");
		if (ontract_name.equals("") || ontract_name == null){
			map.put("msg","请输入文件名");
			return map;
		}else {
			//获取上传合同的文件名

			String orginalFilename = multipartFile.getOriginalFilename();
			System.out.println("文件名为..."+orginalFilename);
			//文件后缀
			String suffix = orginalFilename.substring(orginalFilename.lastIndexOf(".") + 1);
			//获取上传的时间
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Tbl_ontract tbl_ontract = new Tbl_ontract();
			tbl_ontract.setOntract_name(ontract_name);
			tbl_ontract.setOntract_uploadPath(ontract_uploadPath);
			tbl_ontract.setOntract_time(simpleDateFormat.format(date));//获取当前时间

			try
			{
				//TableBean tableBean = new TableBean();
				//文件名+后缀（如：pdf）
				String newFile = ontract_name + "." + suffix;
				String uploadPath = System.getProperty("user.dir") + ONTRACT_PATH + newFile ;
				tbl_ontract.setOntract_uploadPath(uploadPath);
				int flag = wsyOntractService.addOntract(tbl_ontract);
				if (flag > 0){
					// >0,下载到指定路径
					multipartFile.transferTo(new File(uploadPath));
					//tableBean.setMsg("1");
					map.put("msg","ok");
					System.out.println("合同上传成功！");
				}else {
					map.put("msg","error");
				}
			} catch (IOException e)
			{
				map.put("msg","error");
				e.printStackTrace();
			}
			}
		return map;
	}
   //下载文件
	@RequestMapping(value = "downFile.action")
	@Log(operationType="ht",operationName="下载合同")
	public ResponseEntity<byte[]> downFile(String fileName) throws IOException{
		System.out.println("文件下载名"+fileName);
		//下载路径
		String downUrl = System.getProperty("user.dir")+ONTRACT_PATH;
		File file = new File(downUrl + fileName);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentDispositionFormData("attachment",fileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);


	}
    //合同在线预览
	@RequestMapping(value = "devDoc.action")
	@ResponseBody
	public void devDoc(int ontract_id, String type,HttpServletResponse response) throws Exception {
       Tbl_ontract tbl_ontract = wsyOntractService.findontractId(ontract_id);

        InputStream input=null;
		OutputStream out=null;
        //获取文件文件路径
		String ontract_uploadPath = tbl_ontract.getOntract_uploadPath();
		Docx2Html.doc2Html(ontract_uploadPath,"E:\\" + "文件名"+".html");
                input=new FileInputStream("E:\\" + "文件名"+".html");
                response.setContentType("text/html;charset=UTF-8");//解决页面显示乱码 
				out = response.getOutputStream();

		byte[] b = new byte[1024];
		if(out!=null) {
			if(input!=null) {
				while ((input.read(b))!=-1) {
					out.write(b);
				}
			}else {
				System.out.println("InputStream为空。。。");
			}
		}else {
			System.out.println("OutputStream为空。。。");
		}
        //关闭
		out.flush();
		input.close();
		out.close();

	}




}
