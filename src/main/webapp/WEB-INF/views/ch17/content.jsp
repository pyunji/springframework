<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">Spring Security</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">로그인/로그아웃</div>
			<div class="card-body">
				<sec:authorize access="isAnonymous()">
					<a href="loginForm" class="btn btn-info btn-sm">로그인</a>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
					<%-- 사이트간 요청 위조 방지가 비활성화 되어있을 경우 --%>
					<%-- <a href="${pageContext.request.contextPath}/logout" class="btn btn-info btn-sm">로그아웃</a> --%>
					
					<%-- 사이트간 요청 위조 방지가 활성화 되어있을 경우 --%>
					<form action="${pageContext.request.contextPath}/logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button class="btn btn-info btn-sm">로그아웃</button>
					</form>
				</sec:authorize>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">접근 권한</div>
			<div class="card-body">
				<a href="adminAction" class="btn btn-info btn-sm">Admin Action</a>
				<a href="managerAction" class="btn btn-info btn-sm">Manager Action</a>
				<a href="userAction" class="btn btn-info btn-sm">User Action</a>
				<hr/>
				<ul>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li>Admin Menu</li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_MANAGER')">
						<li>Manager Menu</li>
					</sec:authorize>
					<%-- <sec:authorize access="isAuthenticated()">와 동일 --%>
					<sec:authorize access="hasRole('ROLE_USER')"> 
						<li>User Menu</li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>