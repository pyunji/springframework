package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ch02")
public class Ch02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class /*모든 로그는 메모리에 올라오는 해당하는 클래
	스의 소스코드에 접근해야 하기에 써준다*/);
	// value 하나만 파라미터로 주어질 경우 'value=' 생략 가능
	@RequestMapping("/content")	// 요청 방식과 상관 없는 매핑
	public String content() {
		logger.info("실행");
		return "ch02/content"; // Deployed Resources/webapp/WEB-INF/views/ch02/content.jsp
	}
	
//	@GetMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.GET)
	public String method1() {
		logger.info("실행");
		return "redirect:/ch02/content";	// ch02/content로 재요청하라 -> content 메서드 재실행
	}
	
//	@PostMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.POST)
	public String method2() {
		logger.info("실행");
		return "redirect:/ch02/content";
	}
	
//	@PutMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.PUT)
	public String method3() {
		logger.info("실행");
		return "redirect:/ch02/content";
	}
	
//	@DeleteMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.DELETE)
	public String method4() {
		logger.info("실행");
		return "redirect:/ch02/content";
	}
	
	@GetMapping("/modelandview")
	public ModelAndView method5() { // 옛날 방식
		logger.info("실행");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ch02/modelandview");
		return modelAndView;
	}
	
	@PostMapping("/login1") 
	public String login1() {
		logger.info("실행");
		return "ch02/loginResult";
	}
	
	@PostMapping("/login2") 
	public String login2() {
		logger.info("실행");
		return "redirect:/ch01/content";
	}
	
	@GetMapping("/boardlist")
	public String boardList() {
		return "ch02/boardList"; // 대소문자 구분
	}
	
	@GetMapping("/boardwriteform")
	public String boardWriteForm() {
		return "ch02/boardWriteForm";
	}
	
	@PostMapping("/boardwrite")
	public String boardWrite() {
		return "redirect:/ch02/boardlist";
	}
	
}
