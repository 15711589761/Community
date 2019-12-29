package com.smart.community;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LJM。
 */
@SpringBootApplication( exclude = SecurityAutoConfiguration.class)
public class CommunityApplication
{
	public static void main( String[] args )
	{
		SpringApplication.run(CommunityApplication.class, args);
	}
}
