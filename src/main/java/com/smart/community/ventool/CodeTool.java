package com.smart.community.ventool;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodeTool
{


	public static String toMD5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			// 生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要。
			md.update(plainText.getBytes());
			// 通过执行诸如填充之类的最终操作完成哈希计算。
			byte b[] = md.digest();
			// 生成具体的md5密码到buf数组
			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0){
					i += 256;
				}
				if (i < 16)
				{
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static String toAdd0(int i) {

		return String.format("%04d", i);
	}




	public static String getDate() {

		//获取当前时间
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}


	public static List<String> getJobNumberList(int startNumber, int endNumber){

		List<String> list=new ArrayList<String>();

		for (int i = startNumber; i <= endNumber; i++)
		{
			String jobNumber=String.valueOf(i);
			if (jobNumber.length()<4){
				jobNumber=toAdd0(i);
			}
			list.add(jobNumber);
		}

		return list;
	}

}
