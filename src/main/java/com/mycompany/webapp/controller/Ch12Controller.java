package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ch12")
public class Ch12Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch12Controller.class);

	@RequestMapping("/content")
	public String content() {
		logger.info("실행");

		return "ch12/content";
	}
	
	@GetMapping("/fileList")
	public void fileList(HttpServletResponse response) throws IOException {
		logger.info("실행");
		
		// 파일의 총 수 및 파일 이름 목록 얻기
		String fileDir = "C:\\hyundai_it&e\\upload_files";
		File file = new File(fileDir);
		String[] fileList = file.list();
		int totalFileNum = fileList.length;
		
		//응답 생성 및 보내기
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("totalFileNum", totalFileNum);
		/*JSONArray jsonArray = new JSONArray();
		for(String fileName : fileList) {
			jsonArray.put(fileName);
		}
		
		jsonObject.put("fileList", jsonArray);*/
		
		// 배열 안에 값만 들어가있어서 가능한 것이지 [{...}, {...}, ...] 이렇게 객체가 들어있을 경우는 바로 put하는 방식을 사용할 수 없다. 
		jsonObject.put("fileList", fileList);
		String json = jsonObject.toString();
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
	@GetMapping("/fileDownload")
	@ResponseBody
	public void fileDownload(
			String fileName, 
			HttpServletRequest request,
			HttpServletResponse response, 
			@RequestHeader("User-Agent") String userAgent) throws IOException {
		// 파일 이름의 확장자를 보고 mime 타입을 자동으로 가져옴
		String contentType = request.getServletContext().getMimeType(fileName);
		
		String originalFilename = fileName;
		String savedName = fileName;
		
		// 응답 바디의 데이터의 형식 설정
		response.setContentType(contentType);
		
		// 브라우저 별 한글 파일명을 변환
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			// IE11일 경우
			originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
		} else {
			// 크롬, 엣지, 사파리
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		// 브라우저에서 보여주는 것이 아니라 파일을 로컬에 다운로드 받도록 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\""); // 어떤 이름으로 다운로드 받을 것인지.
		
		// 파일로부터 데이터를 읽는 입력 스트림 생성
		// 원래 디비로부터 다운받아야되는데 내 컴퓨터가 디비라고 생각하고 경로를 넣은것임
		String filePath = "C:/hyundai_it&e/upload_files/"+savedName;
		InputStream is = new FileInputStream(filePath);
		
		// 응답 바디에 출력하는 출력스트림 얻기
		OutputStream os = response.getOutputStream();
		
		// 입력스트림 -> 출력스트림
		FileCopyUtils.copy(is, os);
		is.close();
		os.flush();
		os.close();
	}
}
