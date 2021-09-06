<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
    <div class="card-header">DTO 객체의 필드값을 양식의 드롭다운리스트(select 태그)로 세팅</div>
    <div class="card-body">
        <!-- 방법1 이걸 우선적으로 알아야함-->
        <%-- <form method="post" action="form2">
            <div class="form-group">
                <label for="mtype">Type</label>
                <select class="form-control" id="mtype" name="mtype">
                    <!-- <option>일반회원</option>
                    <option>기업회원</option>
                    <option>헤드헌터회원</option> -->
                    <c:forEach var="type" items="${typeList}">
                        <option value="${type}" 
                        <c:if test="${member.mtype == type}">selected</c:if>
                        >${type}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">제출</button>
        </form> --%>
        <!-- 방법2 -->
        <form:form method="post" action="form2" modelAttribute="member">
            <div class="form-group">
                <label for="mtype">Type</label>
                <form:select path="mtype" items="${typeList}" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-primary">제출</button>
        </form:form>
        
        
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>