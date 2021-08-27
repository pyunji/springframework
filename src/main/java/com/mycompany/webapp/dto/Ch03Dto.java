package com.mycompany.webapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Ch03Dto {
	// 필드는 초기화를 하지 않으면 기본으로 디폴트 값이 할당되어서 @RequestParam(default~)를 붙일 필요가 없다.
	private String param1; /*파라미터명과 똑같이 줘야함*/
	private int param2;
	private double param3;
	private boolean param4;
	@DateTimeFormat(pattern="yyyy-MM-dd") // 얘는 붙여주어야 함 
	private Date param5;
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public int getParam2() {
		return param2;
	}
	public void setParam2(int param2) {
		this.param2 = param2;
	}
	public double getParam3() {
		return param3;
	}
	public void setParam3(double param3) {
		this.param3 = param3;
	}
	// boolean type의 getter는 is~로 시작함
	public boolean isParam4() {
		return param4;
	}
	public void setParam4(boolean param4) {
		this.param4 = param4;
	}
	public Date getParam5() {
		return param5;
	}
	public void setParam5(Date param5) {
		this.param5 = param5;
	}
	
	
}
