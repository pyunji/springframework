package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.service.Ch13Service1;

@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Controller.class);

	private Ch13Service1 ch13Service1;
	
	public Ch13Controller() {
		logger.info("실행"); 
		// controller가 붙어있으면 자동으로 객체가 만들어지는데 무조건 기본 생성자만 사용해서 객체를 생성하니까 
		// 추가적인 생성자를 만들어서 실행하는 생성자 주입을 못한다.
	}

	// 주입을 위한 Setter 선언
	public void setCh13Service1(Ch13Service1 ch13Service1) {
		logger.info("실행");
		this.ch13Service1 = ch13Service1;
	}
	
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");

		return "ch13/content";
	}
	
	@GetMapping("/request1")
	public String request1() {
		logger.info("실행");
		ch13Service1.method1();
		return "redirect:/ch13/content";
	}
}
