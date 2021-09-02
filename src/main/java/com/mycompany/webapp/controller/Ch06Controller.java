package com.mycompany.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch06Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@RequestMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}
	
	@RequestMapping("/redirect")
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		logger.info("실행");
		return "ch06/fragmentHtml";
	}
	
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws IOException {
		logger.info("실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo5.jpg");
		String json = jsonObject.toString();
		// 응답 HTTP의 Body부분에 json을 포함
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
	@GetMapping(value="/getJson2", produces="application/json; charset=UTF-8")
	@ResponseBody // 이 방식을 잘 알고있어야 함
	public String getJson2() {
		logger.info("실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		
		return json;
	}
	
	@GetMapping("/getJson3") // 이 방식이 된다 하더라도 이런식으로 코딩하면 안됨. AJAX는 리다이렉트로 응답을 받아서는 안된다.
	public String getJson3() {
		logger.info("실행");
		
//		return "redirect:/ch06/getJson2"; // 나오긴 나옴
		return "redirect:/";
	}
}


