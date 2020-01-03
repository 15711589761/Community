package com.smart.community.wsyaspects;


import com.smart.community.wsyjavabean.Tbl_systemlog;
import com.smart.community.wsyservice.WsyLogService;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

//日志切面
@Aspect//代表当前类是一个切面
@Component//容器
public class WsyLogAspect
{

	@Resource
	private WsyLogService wsyLogService;

	//记录日志
	//Controller层切点
	//匹配TbluserService类中的所有方法
	@Pointcut("execution(* com.smart.community.wsycontroller..*.*(..))")
	public void recordLog(){

	}
	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 *
	 * @param joinPoint 切点
	 */
	@Before("recordLog()")
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("==========执行controller前置通知===============");

		System.out.println("----------joinPoint----------------"+joinPoint);
	}

	@After("recordLog()")
	public void afterLog(JoinPoint joinPoint) throws Throwable
	{
		System.out.println("进入记录日志方法");
		String className = joinPoint.getTarget().getClass().getName();
		String mothodName = joinPoint.getSignature().getName();
		Object[] logParams = joinPoint.getArgs();

		Class<?> clazz = Class.forName(className);
		Method[] methods = clazz.getMethods();
		String operationType = "";
		String operationName = "";
//		 String operation_matter="" ;//日志内容
//		 String operation_person="";//操作人员
//		 String operation_date= "";//日志操作日期
//		 String operation_time= "";//日志操作时间
		try
		{

			Class<?>[] logType = null;
			boolean isSameMethod = true;
			for (Method method : methods)
			{
				if (method.getName().equals(mothodName) && method.getParameterTypes().length == logParams.length)
				{
					logType = method.getParameterTypes();
					for (int i = 0; i < logType.length; i++)
					{
						if (logParams[i] instanceof MultipartFile && logType[i] == MultipartFile.class)
						{
							continue;

						}
						if (logParams[i] instanceof HttpServletRequest && logType[i] == HttpServletRequest.class)
						{
							continue;
						}
						if (logParams[i] instanceof HttpServletResponse && logType[i] == HttpServletResponse.class)
						{
							continue;
						}
						if (logParams[i] != null)
						{
							if (logType[i] == logParams[i].getClass())
							{
								continue;
							}
						} else
						{
							continue;
						}
						isSameMethod = false;
						break;
					}
					if (isSameMethod)
					{

						operationName = method.getAnnotation(Log.class).operationName();
						operationType = method.getAnnotation(Log.class).operationType();
                        Tbl_systemlog tbl_systemlog = new Tbl_systemlog();
						tbl_systemlog.setOperation_matter(operationName);
						tbl_systemlog.setOperation_person(operationType);
						Date date = new Date();
						SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

						tbl_systemlog.setOperation_date(dayFormatter.format(date));
						tbl_systemlog.setOperation_time(timeFormatter.format(date));
						int insertlog = wsyLogService.addLog(tbl_systemlog);
						if (insertlog > 0)
						{
							System.out.println("日志添加成功");
						} else
						{
							System.out.println("日志添加失败");
						}


					}
//					System.out.println("操作日志..." + operation_matter);
//					System.out.println("操作人员..." + operation_person);
//					System.out.println("操作日期..." + operation_date);
//					System.out.println("操作时间..." + operation_time);
				}


			}


		} catch (Exception ex)
		{
			ex.printStackTrace();
		}


	}
}
