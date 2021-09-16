package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch16Account;
import com.mycompany.webapp.service.Ch16AccountService;
import com.mycompany.webapp.service.Ch16AccountService.TransferResult;

@Controller
@RequestMapping("/ch16")
public class Ch16Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch16Controller.class);

	@Resource
	private Ch16AccountService accountService;
	
	@RequestMapping("/content")
	public String content(Model model) {
		logger.info("실행");
		List<Ch16Account> accounts = accountService.getAccounts();
		model.addAttribute("list", accounts);
		return "ch16/content";
	}
	
	@RequestMapping("/transaction1")
	public String transaction1(int fromAno, int toAno, int amount, HttpSession session) {
		logger.info("실행");
		session.removeAttribute("transferError"); // 동일한 클라이언트에서 요청할 경우 초기화가 필요하므로 기존에 저장되어있던 에러를 날려야한다.
		TransferResult result = accountService.transfer1(fromAno, toAno, amount);
		if (result == TransferResult.FAIL_NOT_FOUND_ACCOUNT) {
			session.setAttribute("transferError", "계좌가 존재하지 않습니다.");
		} 
		return "redirect:/ch16/content";
	}
	
	@RequestMapping("/transaction2")
	public String transaction2(int fromAno, int toAno, int amount) {
		logger.info("실행");
		accountService.transfer2(fromAno, toAno, amount);
		return "redirect:/ch16/content";
	}
}
