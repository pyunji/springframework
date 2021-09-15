<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
    <div class="card-header">AOP(관점 지향 프로그래밍)</div>
    <div class="card-body">
        <div class="card">
            <div class="card-header">Advice Test</div>
            <div class="card-body">
                <a href="before" class="btn btn-info btn-sm">@Before 테스트</a>
                <a href="after" class="btn btn-info btn-sm">@After 테스트</a>
                <a href="afterReturning" class="btn btn-info btn-sm">@AfterReturning 테스트</a>
                <a href="afterThrowing" class="btn btn-info btn-sm">@AfterThrowing 테스트</a>
                <a href="around" class="btn btn-info btn-sm">@Around 테스트</a>
            </div>
        </div>

        <div class="card">
            <div class="card-header">AOP 예제</div>
            <div class="card-body">
                <a href="runtimeCheck" class="btn btn-info btn-sm">요청 처리 시간 측정</a>
                <a href="javascript:boardList()" class="btn btn-info btn-sm">인증 여부 확인</a>
                <hr />
                <div>${methodName}실행시간: ${howLong}</div>
                <hr/>
                <div id="boardList"></div>
            </div>
            <script>
            function boardList() {
            	$.ajax({
            		url: "boardList"
            	}).done(data => {
            			$("#boardList").html(data);
            	}
            }
            </script>
        </div>

        <div class="card">
            <div class="card-header">form을 통한 login 처리</div>
            <div class="card-body">
                <c:if test="${sessionMid == null}">
                    <a href="login" class="btn btn-info btn-sm">로그인 폼 요청</a>
                    <!-- 로그인이 되지 않았을 때만 보여야 함 -->
                </c:if>
                <!-- else문은 없다. -->
                <c:if test="${sessionMid != null}">
                    <a href="logout" class="btn btn-info btn-sm">로그아웃</a>
                </c:if>
                <!-- else문은 없다. -->
            </div>
        </div>

    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>