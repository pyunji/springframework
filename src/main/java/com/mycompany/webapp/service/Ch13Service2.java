package com.mycompany.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13BoardDao2;

@Service
public class Ch13Service2 {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch13Service2.class);

	private Ch13BoardDao2 ch13BoardDao2;
	
	public Ch13Service2() {
		logger.info("실행");
	}
	
	@Autowired // 파라미터가 스프링이 관리하고 있는 객체면 자동으로 주입해준다.
	public void setCh13BoardDao2(Ch13BoardDao2 ch13BoardDao2) {
		logger.info("실행");
		this.ch13BoardDao2 = ch13BoardDao2;
	}
	
	public void method1() {
		logger.info("실행");
		ch13BoardDao2.update();
	}
}
