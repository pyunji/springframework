package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/ch08")
public class Ch08Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	
	@GetMapping(value="/saveData", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String saveData(String name, HttpSession session) {
		logger.info("실행");
		logger.info("name: " + name);
		
		session.setAttribute("name", name);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString(); // {"result":"success"}
		return json;
	}
	
	// 방법 1
	/*	@GetMapping(value="/readData", produces="application/json; charset=UTF-8")
		@ResponseBody
		public String readData(HttpSession session) {
			logger.info("실행");
			
			String name = (String) session.getAttribute("name");
			logger.info("name: " + name);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", name);
			String json = jsonObject.toString(); // {"name":"홍길동"}
			return json;
		}*/
	
	// 방법 2
	@GetMapping(value="/readData", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String readData(@SessionAttribute String name) {
		logger.info("실행");
		
		logger.info("name: " + name);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString(); // {"name":"홍길동"}
		return json;
	}
}
