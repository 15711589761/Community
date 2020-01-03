package com.smart.community.sxcontroller;

import com.smart.community.sxbean.FullcalendarDate;
import com.smart.community.sxbean.LayuiTableBean;
import com.smart.community.sxbean.SaferScheduling;
import com.smart.community.sxbean.WorkTime;
import com.smart.community.sxservice.Sxservice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class Sxcontroller
{
	@Resource
	private Sxservice sxservice;

	@RequestMapping("/safeArrange.view")
	public ModelAndView safeArrange()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("safeArrange");
		return modelAndView;
	}
	@RequestMapping("/safeArrange.action")
	@ResponseBody
	public LayuiTableBean saferArrangeData()
	{

		List<SaferScheduling> saferScheduling = sxservice.saferCanlendar();
		LayuiTableBean tableBean = new LayuiTableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setCount(saferScheduling.size());
		tableBean.setData(saferScheduling);
		return tableBean;
	}
	@RequestMapping("/safeFullcalendar.view")
	public ModelAndView safeFullcalendar(@Param(value = "getId")String getId,@Param(value = "getDate")String getDate)
	{

		ModelAndView modelAndView= null;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date todayDate;
			if (getDate!=null){
					todayDate = dateFormat.parse(getDate);
			}else {
				todayDate = new Date();
			}
			modelAndView = new ModelAndView();
			FullcalendarDate date = new FullcalendarDate();
			List<Date> dates= date.dateToWeek(todayDate);
			List<String> dateList = new ArrayList<>();
			for (int i = 0; i < dates.size(); i++)
			{
				String getDates=dateFormat.format(dates.get(i));
				dateList.add(getDates);
			}
			Map map = sxservice.SaferFullenlendarDate(getId);

			modelAndView.addObject("Date",dateList);
			modelAndView.addObject("fullcalendar",map);
			modelAndView.addObject("actionId",getId);
			modelAndView.setViewName("safeFullcalendar");
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return modelAndView;

	}


	@RequestMapping("/insertSaferFull.action")
	@ResponseBody
	public Boolean insertSaferFullenlendar(WorkTime workTime)
	{
		List check = sxservice.checkInsert(workTime);
		if (check.size()<1){
			int x =sxservice.insertSaferFullenlendar(workTime);
			if (x>0)
			{
				return true;
			}
		}else {
			return false;
		}

		return false;
	}

	@RequestMapping("/deleteSaferFull.action")
	@ResponseBody
	public Boolean deleteSaferFullenlendar(WorkTime workTime)
	{
		try
		{
			Date today = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date getDate = dateFormat.parse(workTime.getWorkDate());
			if (today.getTime()<=getDate.getTime()){
				int x =sxservice.deleteSaferFullenlendar(workTime);
				if (x>0)
				{
					return true;
				}else {
					return false;
				}
			}
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return false;
	}






}
