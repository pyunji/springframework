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
		<div class="card m-2">
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
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>