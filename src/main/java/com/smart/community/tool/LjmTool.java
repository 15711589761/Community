package com.smart.community.tool;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class LjmTool
{
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
