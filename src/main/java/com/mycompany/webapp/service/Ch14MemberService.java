package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14Member;

@Service // root의 WebApplicationContext가 관리
public class Ch14MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberService.class);
	
	// 열거 타입 선언
	public enum JoinResult {
		SUCCESS, 
		FAIL,
		DUPLICATED
	}
	
	public enum LoginResult {
		SUCCESS, 
		FAIL_MID,
		FAIL_MPASSWORD,
		FAIL
	}
	
	@Resource
	private Ch14MemberDao memberDao;
	
	// 회원가입을 처리하는 비즈니스 로직
	public JoinResult join(Ch14Member member) {
		try {
			// 이미 가입된 아이디인지 확인
			Ch14Member dbMember = memberDao.selectByMid(member.getMid()); // SELECT * FROM member WHERE mid=?
		
			// DB에 회원 정보를 저장
			if(dbMember == null) {
				memberDao.insert(member);
				return JoinResult.SUCCESS;
			} else {
				return JoinResult.DUPLICATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JoinResult.FAIL;
		}
	}
	
	public LoginResult login(Ch14Member member) {
		try {
			// 이미 가입된 아이디인지 확인
			Ch14Member dbMember = memberDao.selectByMid(member.getMid()); // SELECT * FROM member WHERE mid=?
		
			// 확인 작업
			if(dbMember == null) {
				return LoginResult.FAIL_MID;
			} else if(!dbMember.getMpassword().equals(member.getMpassword())){
				return LoginResult.FAIL_MPASSWORD;
			} else {
				return LoginResult.SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return LoginResult.FAIL;
		}
	}	
}
