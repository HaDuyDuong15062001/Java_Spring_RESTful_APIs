package com.duyduong.jobhunter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//disable security
// @SpringBootApplication(exclude = {
// 		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
// 		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
// })

@SpringBootApplication
public class JobhunterApplication {

	public static void main(String[] args) {

		SpringApplication.run(JobhunterApplication.class, args);
	}

}
//https://200lab.io/blog/oauth-la-gi
//https://viblo.asia/p/huong-dan-spring-security-co-ban-de-hieu-OeVKBdedlkW
//https://techmaster.vn/posts/36295/spring-security-ban-sau-ve-authentication-va-authorization-p1
//https://techmaster.vn/posts/37937/tim-hieu-ve-bean-trong-springboot