package com.mycompany.webapp.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Ch03Dto;

@Controller
@RequestMapping("/ch03")
public class Ch03Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch03Controller.class);

	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch03/content";
	}

	/*@GetMapping("/method1")
	public String method1(
		String param1,
		String param2, 
		String param3, 
		String param4,
		String param5
		) {
		logger.info("실행");
		logger.info("param1: " + param1);
		logger.info("param2: " + param2);
		logger.info("param3: " + param3);
		logger.info("param4: " + param4);
		logger.info("param5: " + param5);
		return "redirect:/ch03/content";
	}*/

	/*@GetMapping("/method1")
	public String method1(
		String param1,
		// null값은 primitive 타입으로 변환할 수 없기에 primitive 타입을 쓰는 것은 위험하다. -> default값을 설정해주면 된다.
		@RequestParam(defaultValue="0"데이터가 넘어오지 않으면 디폴트로 0이라는 문자열이 넘어온 것으로 간주하겠다.) 
		int param2, // 문자열 데이터를 스프링이 int로 바꿔서 변환
		@RequestParam(defaultValue="0.0")
		double param3, // double 타입을 지정해주면 스프링이 double로 변환해줌
		@RequestParam(defaultValue="false")
		boolean param4,
		@DateTimeFormat(pattern="yyyy-MM-dd") // 넘어오는 데이터를 패턴에 맞는 형식의 Date 객체로 받는다 
		Date param5
		) {
		logger.info("실행");
		logger.info("param1: " + param1);
		logger.info("param2: " + param2);
		logger.info("param3: " + param3);
		logger.info("param4: " + param4);
		logger.info("param5: " + param5);
		return "redirect:/ch03/content";
	}*/
	
		@GetMapping("/method1")
		public String method1(Ch03Dto dto){
			logger.info("실행");
			logger.info("param1: " + dto.getParam1());
			logger.info("param2: " + dto.getParam2());
			logger.info("param3: " + dto.getParam3());
			logger.info("param4: " + dto.isParam4());
			logger.info("param5: " + dto.getParam5());
			return "redirect:/ch03/content";
		}
	
	/*@GetMapping("/method1")
	public String method1(
		@RequestParam("param1") String p1, // 넘어올때의 파라미터 param1의 값을 p1으로 받겠다
		String p2, 
		String p3, 
		String p4,
		String p5
		) {
		logger.info("실행");
		logger.info("param1: " + param1);
		logger.info("param2: " + param2);
		logger.info("param3: " + param3);
		logger.info("param4: " + param4);
		logger.info("param5: " + param5);
		return "redirect:/ch03/content";
	}*/

	/*	@PostMapping("/method2")
		public String method2(String param1, String param2, String param3, String param4, String param5) {
			logger.info("실행");
			logger.info("param1: " + param1);
			logger.info("param2: " + param2);
			logger.info("param3: " + param3);
			logger.info("param4: " + param4);
			logger.info("param5: " + param5);
			return "redirect:/ch03/content";
		}*/

	/*@PostMapping("/method2")
	public String method2(String param1, @RequestParam(defaultValue = "0") int param2,
			@RequestParam(defaultValue = "0.0") double param3, @RequestParam(defaultValue = "false") boolean param4,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date param5) {
		logger.info("실행");
		logger.info("param1: " + param1);
		logger.info("param2: " + param2);
		logger.info("param3: " + param3);
		logger.info("param4: " + param4);
		logger.info("param5: " + param5);
		return "redirect:/ch03/content";
	}*/
	
	@PostMapping("/method2")
	public String method2(Ch03Dto dto) {
		logger.info("실행");
		logger.info("param1: " + dto.getParam1());
		logger.info("param2: " + dto.getParam2());
		logger.info("param3: " + dto.getParam3());
		logger.info("param4: " + dto.isParam4());
		logger.info("param5: " + dto.getParam5());
		return "redirect:/ch03/content";
	}
}
