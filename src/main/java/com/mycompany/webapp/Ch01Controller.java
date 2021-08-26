package com.mycompany.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ch01") // 공통 경로는 따로 빼서 컨트롤러 위에 정의
public class Ch01Controller {	// 컨트롤러의 이름과 애노테이션의 경로는 관련이 없다
	private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);

	@RequestMapping("/content")
	public String home() {	// 메서드 이름과 애노테이션의 경로는 관련이 없다
		logger.info("실행");
		return "ch01/content";	// views에서 파일을 찾음
	}

}
