package com.smart.community.tool;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class LjmTool
{
	public final static String ROLE_CUSTOMER = "客服";
	public final static String ROLE_LOGISTICS = "后勤";
	public final static String ROLE_FINANCE = "财务";
	public final static String ROLE_AFFAIRS = "人力资源";
	public final static String DESK_HOME = "/toDeskHome.view";
	public final static String DESK_LOGIN_VIEW = "/toDeskLogin.view";
	public final static String DESK_LOGIN_ACTION = "/deskLogin.action";
	public final static String BACKSTAGE_LOGIN_VIEW = "/toBackstageLogin.view";
	public final static String BACKSTAGE_LOGIN_ACTION = "/backstageLogin.action";
	public final static String LOGIN_VERIFY = "/getVerify.image";
	public final static String PROJECT_PATH = "/Community";

	public static String getTodayDate()
	{
		return LocalDate.now().toString();
	}

	public static String getTime()
	{
		return  LocalTime.now().withNano(0).toString();
	}

	public static String getOneMouthBefore()
	{
		return LocalDate.now().minusMonths(1).toString();
	}

	public static String getPreciseTime()
	{
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddHHmmssS");
		System.out.println();
		return  dateFormat.format(date);
	}
}
