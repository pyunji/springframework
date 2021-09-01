package com.mycompany.webapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch05Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(HttpServletRequest request) {
		logger.info("실행");
		
		logger.info("method:" +  request.getMethod());
		logger.info("requestURI:" + request.getRequestURI());
		logger.info("client IP:" + request.getRemoteAddr());
		logger.info("context root: " + request.getContextPath());
		
		String userAgent = request.getHeader("User-Agent");
		logger.info("User-Agent: " + userAgent);
		if(userAgent.contains("Windows NT")) {
			logger.info("Client OS: Windows");
		} else if(userAgent.contains("Macintosh")) {
			logger.info("Client OS: macOS");
		}
		
		if(userAgent.contains("Edg")) {
			logger.info("Client Browser: Edge");
		} else if(userAgent.contains("Trident")) {
			logger.info("Client Browser: IE11");
		} else if(userAgent.contains("Chrome")) {
			logger.info("Client Browser: Chrome");
		} else if(userAgent.contains("Safari")) {
			logger.info("Client Browser: Safari");
		}
		
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response) {
		logger.info("실행");
		
		Cookie cookie = new Cookie("useremail", "vusguswl@gmail.com"); // 쿠키의 파라미터는 문자열만 가능하다.
//		cookie.setPath("/ch04"); // ch04로 요청받았을 때만 쿠키를 가져가라
		cookie.setDomain("localhost");	// localhost면 전송
		cookie.setPath("/");	// localhost/... 이면 모두 전송
		cookie.setMaxAge(30*60); // 해당 시간동안만 전송. 쿠키를 메모리에 저장하면 브라우저를 종료하면 없어지니까 파일에 저장하게 된다.
		cookie.setHttpOnly(true); // JavaScript에서 못읽게 함
		cookie.setSecure(true); // https:// 에만 전송
		response.addCookie(cookie);
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie1")
	public String getCookie1(
			@CookieValue String useremail,
			@CookieValue("useremail") String uemail) {
		logger.info("실행");
		logger.info("useremail: " + useremail);
		logger.info("useremail: " + uemail);
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie2")
	public String getCookie2(HttpServletRequest request) {
		logger.info("실행");
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			
			if(cookieName.equals("useremail")) {
				logger.info("useremail: " + cookieValue);
				break;
			}
		}
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/createJsonCookie")
	public String createJsonCookie(HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("실행");
		
//		String json = "{\"userid\":\"fall\", \"useremail\":\"fall@company.com\", \"username\":\"홍길동\"}";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userid", "fall");
		jsonObject.put("useremail", "fall@company.com");
//		jsonObject.put("usertel", "0101231234");
		jsonObject.put("username", "홍길동");
		String json = jsonObject.toString(); //큰따옴표 인코딩 하기위해
		logger.info("json: " + json);
		json = URLEncoder.encode(json, "UTF-8");
		logger.info("json: " + json);
		
		Cookie cookie = new Cookie("user", json);
		response.addCookie(cookie);
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getJsonCookie")
	public String getJsonCookie(@CookieValue String user) { // user: {"userid":"fall","useremail":"fall@company.com","username":"홍길동"}
		logger.info("실행");
		logger.info("user: " + user);
		// 중괄호로 시작하면 JSONObject, 대괄호로 시작하면 JSONArray 써서 파싱
		JSONObject jsonObject = new JSONObject(user);
		String userid = jsonObject.getString("userid");
		String useremail = jsonObject.getString("useremail");
		String username = jsonObject.getString("username");
		
		logger.info("userid: " + userid);
		logger.info("useremail: " + useremail);
		logger.info("username: " + username);
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/createJwtCookie")
	public String createJwtCookie(HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("실행");
		
		String userid = "fall";
		String useremail = "fall@company.com";
		String username = "홍길동";
		
		
		
		JwtBuilder builder = Jwts.builder();
		// Header
		builder.setHeaderParam("alg", "HS256");
		builder.setHeaderParam("typ", "JWT");
		// 1970년 자정부터 천분의 1초로 카운팅한 수가 getTime에 나옴
		builder.setExpiration(new Date(new Date().getTime() + 1000*60*30)); // 지금으로부터 30분동안만 쓸 수 있는 JWT
		// Payload
		builder.claim("userid", userid);
		builder.claim("useremail", useremail);
		builder.claim("username", username);
		//Signature
		String secretKey = "abc12345";
		builder.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"));
		String jwt = builder.compact();
		logger.info("jwt: " + jwt);
		
		Cookie cookie = new Cookie("jwt", jwt);
		response.addCookie(cookie);
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getJwtCookie")
	public String getJwtCookie(@CookieValue String jwt) throws UnsupportedEncodingException {
		logger.info("실행");
		
		logger.info(jwt);
		
		JwtParser parser = Jwts.parser();
		String secretKey = "abc12345";
		parser.setSigningKey(secretKey.getBytes("UTF-8"));
		Jws<Claims> jws = parser.parseClaimsJws(jwt);
		Claims claims = jws.getBody();
		String userid = claims.get("userid", String.class);
		String useremail = claims.get("useremail", String.class);
		String username = claims.get("username", String.class);
		logger.info("userid: " + userid);
		logger.info("useremail: " + useremail);
		logger.info("username: " + username);
		
		return "redirect:/ch05/content";
	}
}
