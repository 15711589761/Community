package com.smart.community.vencontroller;


import com.smart.community.venjavabean.*;
import com.smart.community.venservices.ResumeService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


@Controller

public class ResumeController
{

	@Resource
	private ResumeService resumeService;

	/**
	 * 跳转到简历管理界面
	 */

	@RequestMapping(value = "/toResumeManagement")
	public ModelAndView toManagingPeople(HttpSession session)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("v_resumeManagement");
		return mv;
	}
	/** 获取简历信息*/

	@RequestMapping("/getResumeData.action")
	@ResponseBody
	public TableBean querySeparatingEmploy(String startDate, String endDate, String userName, String page, String limit)
	{
		ConditionBean bean = new ConditionBean();

		bean.setStartDate(startDate);

		bean.setEndDate(endDate);

		bean.setUserName(userName);

		bean.setPage((Integer.valueOf(page) - 1) * Integer.valueOf(limit));

		bean.setLimit(Integer.valueOf(limit));

		return resumeService.queryResumeDate(bean);
	}

	/** 检查简历是否存在*/

	@RequestMapping("checkFileExists.action")
	@ResponseBody
	public AjaxInfoBean checkFileExists(AjaxInfoBean ajaxInfoBean) {
		String msg;

		AjaxInfoBean ajaxInfoBean1=new AjaxInfoBean();

		int id=Integer.parseInt(ajaxInfoBean.getTargetId());

		System.out.println("后台接收到的id为："+id);

		File file=new File(resumeService.getResumeById(id).getResumeUrl());

		if (file.exists()){
			msg="存在";
			ajaxInfoBean1.setResultMsg(msg);
			return ajaxInfoBean1;
		}
		return ajaxInfoBean1;

	}
	/** 下载简历*/
	@RequestMapping("/resumeDownload.action")
	public ResponseEntity<byte[]> downLoadFile (HttpServletRequest request, HttpServletResponse response,String resumeId) throws IOException
	{
		System.out.println("resumeId = " + resumeId);

		int id = Integer.parseInt(resumeId);

		ResumeBean resumeBean = resumeService.getResumeById(id);

		File file = new File(resumeBean.getResumeUrl());

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		headers.setContentDispositionFormData("attachment", file.getName());

		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	/** 删除简历*/

	@RequestMapping("/delResume.action")
	@ResponseBody
	public AjaxInfoBean delResume(AjaxInfoBean ajaxInfoBean)
	{
		AjaxInfoBean msg = new AjaxInfoBean();

		int resumeId=Integer.parseInt(ajaxInfoBean.getTargetId());

		int num = resumeService.delResume(resumeId);

		if (num > 0)
		{
			msg.setResultMsg("done");
		}
		return msg;

	}

}