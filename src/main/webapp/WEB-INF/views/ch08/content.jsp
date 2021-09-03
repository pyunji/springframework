<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		Session Support
	</div>
	<div class="card-body">
		<div class="card">
          <div class="card-header">
            세션 원리: JSESSIONID 쿠키
          </div>
          <div class="card-body">
              <p>서버: 세션 객체 생성 -> JSESSION 쿠키 발행</p>
               <p>브라우저: JSESSION 쿠키 전송 -> 세션 객체 찾음 -> 세션 객체 이용</p>
              <a href="javascript:saveData()" class="btn btn-info btn-sm"> 세션에 데이터 저장</a>
              <a href="javascript:readData()" class="btn btn-info btn-sm"> 세션의 데이터 읽기</a>
          </div>
          <script>
          	function saveData() {
          		$.ajax({
          			url: "saveData",
          			data:{name:"홍길동"}
          		})
          		.done((data)=>{
          			console.log(data);
          		})
          	}
          	
          	function readData() {
          		$.ajax({
          			url: "readData"
          		})
          		.done((data)=>{
          			console.log("data:", data);
          			console.log("data.name:", data.name)
          		})
          	}
          </script>
        </div>
        
        <div class="card">
          <div class="card-header">
            form을 통한 login 처리
          </div>
          <div class="card-body">
            <c:if test="${sessionMid == null}">
              <a href="login" class="btn btn-info btn-sm">로그인 폼 요청</a><!-- 로그인이 되지 않았을 때만 보여야 함 -->
            </c:if> <!-- else문은 없다. -->
            <c:if test="${sessionMid != null}">
              <a href="logout" class="btn btn-info btn-sm">로그아웃</a>
            </c:if> <!-- else문은 없다. -->
          </div>
        </div>
        
        <div class="card">
          <div class="card-header">
            AJAX를 통한 login 처리
          </div>
          <div class="card-body">
          <c:if test="${sessionMid == null}">
            <form method="post" action="login">
              <div class="input-group">
                 <div class="input-group-prepend"><span class="input-group-text">mid</span></div>
                 <input id="mid" type="text" name="mid" class="form-control"/>
                 <span id="mid-error" class="error"></span>
              </div>
              <div class="input-group">
                 <div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
                 <input id="mpassword" type="password" name="mpassword" class="form-control"/>
                 <span id="mpassword-error" class="error"></span>
              </div>
            </form>
          </c:if>
       
            <div class="mt-2">
              <c:if test="${sessionMid == null}">
                <a href="javascript:login()" class="btn btn-info btn-sm">로그인</a>
              </c:if> <!-- else문은 없다. -->
              <c:if test="${sessionMid != null}">
                <a href="javascript:logout()" class="btn btn-info btn-sm">로그아웃</a>
              </c:if> <!-- else문은 없다. -->
            </div>
            <script>
            	function login() {
            		let mid = $("#mid").val();
            		let mpassword = $("#mpassword").val();
           
            		$.ajax({
            			url: "loginAjax",
            			data: {mid, mpassword},
            			method: "post"
            		}).done((data)=>{
            			// 로그인 성공한 경우
            			// data = {result:"success"}
            			// 실패의 원인 두개
            			// data = {result:"wrongMid"}
            			// data = {result:"wrongMpassword"}
            			
            			const midError = $("#mid-error");
            			const mpasswordError = $("#mpassword-error");
            			midError.html("");
            			mpasswordError.html("");
            			
            			if(data.result==="success") { 
            				// AJAX 요청은 부분만 바꾸기 때문에 form을 통한 login 처리의 버튼도 logout으로 바뀌어야 한다.
            				// 현재 페이지 전체를 다시 서버에서 받아오도록 함. 세션에 sessionMid != null 상태가 적용되도록.
            				window.location.reload(); // AJAX의 원래 취지와는 맞지 않는다.
            			} else if(data.result==="wrongMid"){
            				midError.html("아이디가 잘못됨");
            			} else if(data.result==="wrongMpassword") {
            				mpasswordError.html("패스워드가 잘못됨");
            			}
            		});
            	}
            	
            	function logout() {
            		$.ajax({
            			url: "logoutAjax"
            		}).done((data)=> {
            			// data = {result:"success"}
            			// 현재 페이지 전체를 다시 서버에서 받아오도록 함
            			window.location.reload();
            		});
            	}
            </script>
          </div>
        </div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>