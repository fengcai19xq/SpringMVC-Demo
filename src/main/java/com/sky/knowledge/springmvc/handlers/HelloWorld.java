package com.sky.knowledge.springmvc.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sky.knowledge.springmvc.service.DummyService;

@Controller
public class HelloWorld {

	@Autowired
	 DummyService dummyService ;
	
	
	
	/**
	 * 1、使用@RequestMapping 注解来映射请求的URL
	 * 2、返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver 视图解析器，会做如下解析：	
	 * prefix + returnVal + 后缀 这样的方式得到实际的物理视图，然后会做转发操作
	 * /WEB-INF/jsp/success.jsp
	 * @description
	 * @create xq
	 * @date 2015-1-15
	 */
	@RequestMapping("/helloworld.do")
	public String hello(){
		dummyService.getDummyList();
		System.out.println("hello world");
		return "success";
	}
}
