package com.smart.community.sxbean;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class CheckResolving
{
	@Autowired
	public List getBankListByExcel(String url)
	{

		List<List<Object>> list = null;
		try
		{
			String filepath = url;
			File file = new File(filepath);
			FileInputStream inputStream = new FileInputStream(new File(filepath));
			list = ImportExcelUtil.getBankListByExcel(inputStream, filepath);
			for (int i = 0; i < list.size(); i++)
			{
				for (int j = 0; j < list.get(i).size(); j++)
				{
					System.out.println(j+"------"+list.get(i).get(j) );
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;


	}
}
