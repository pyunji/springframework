package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileUpload(String title, String desc, MultipartFile attach) throws IllegalStateException, IOException {
		logger.info("실행");
		
		// 문자 파트 내용 읽기
		logger.info("title: " + title);
		logger.info("desc: " + desc);
		
		// 파일 파트 내용 읽기
		logger.info("file originalname: " + attach.getOriginalFilename());
		logger.info("file contenttype: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());
		
		// 파일 파트 데이터를 서버의 파일로 저장
		String savedname = new Date().getTime() + "-" + attach.getOriginalFilename(); // 파일 이름이 동일하면 덮어쓰기가 되기 때문에 
		File file = new File("C:/hyundai_it&e/upload_files/"+savedname);
		attach.transferTo(file);
		return "redirect:/ch09/content";
	}
	
	@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileUploadAjax(String title, String desc, MultipartFile attach) throws IllegalStateException, IOException {
		logger.info("실행");
		
		// 문자 파트 내용 읽기
		logger.info("title: " + title);
		logger.info("desc: " + desc);
		
		// 파일 파트 내용 읽기
		logger.info("file originalname: " + attach.getOriginalFilename());
		logger.info("file contenttype: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());
		
		// 파일 파트 데이터를 서버의 파일로 저장
		String savedname = new Date().getTime() + "-" + attach.getOriginalFilename(); // 파일 이름이 동일하면 덮어쓰기가 되기 때문에 
		File file = new File("C:/hyundai_it&e/upload_files/"+savedname);
		attach.transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("savedname", savedname);
		String json = jsonObject.toString();
		
		return json;
	}
	
	// 좋은 방법이 아님
	// 1. produces 때문에 응답 바디에 데이터 형식이 고정되어버림
	// 2. toByteArray()에 리턴하는 배열의 길이가 문제가 됨. -> 파일의 크기가 크면 파일의 크기 만큼의 배열이 만들어지므로 자원이 낭비됨
	/*@GetMapping(value="/filedownload", produces="image/jpeg")
	@ResponseBody
	public byte[] filedownload(String savedname) throws IOException {
		
		String filePath = "C:/hyundai_it&e/upload_files/"+savedname;
		InputStream is = new FileInputStream(filePath);
		byte[] data = IOUtils.toByteArray(is);
		
		return data;
	}*/
	
	// 메모리를 적게 차지하는 좋은 방법
	@GetMapping("/filedownload")
	@ResponseBody
	public void filedownload(
			int fileNo, 
			HttpServletResponse response, 
			@RequestHeader("User-Agent") String userAgent) throws IOException {
		// fileNo를 이용해서 DB에서 파일 정보를 가져오기
		String contentType = "image/jpeg"; // -> 브라우저에 그림을 띄움
		String originalFilename = "눈내리는마을.jpg";
		String savedName = "1630656745179-눈내리는마을.jpg";
		
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
