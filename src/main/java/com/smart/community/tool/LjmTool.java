package com.smart.community.tool;

import java.time.LocalDate;
import java.time.LocalTime;

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
}
