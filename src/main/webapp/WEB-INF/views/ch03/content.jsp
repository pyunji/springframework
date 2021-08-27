<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		Controller/Request Parameter
	</div>
	<div class="card-body">
		<div class="card m-2">
			<div class="card-header">
				GET 방식으로 요청
			</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" 
				href="method1?param1=문자열&param2=5&param3=3.14&param4=true&param5=2021-08-27">요청</a>
				<hr/>
				<form method="get" action="method1">
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
	                   <input type="text" name="param1" class="form-control" value="문자열">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
	                   <input type="text" name="param2" class="form-control" value="5">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
	                   <input type="text" name="param3" class="form-control" value="3.14">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
	                   <div class="btn-group btn-group-toggle" data-toggle="buttons">
	                      <label class="btn btn-secondary active">
	                        <input type="radio" name="param4" checked value="true"> true
	                      </label>
	                      <label class="btn btn-secondary">
	                        <input type="radio" name="param4" value="false"> false
	                      </label>
	                   </div>
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
	                   <input type="date" name="param5" class="form-control" value="2030-12-05">
	                </div>
	                <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
	             </form>
			</div>
		</div>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		Controller/Request Parameter
	</div>
	<div class="card-body">
		<div class="card m-2">
			<div class="card-header">
				POST 방식으로 요청
			</div>
			<div class="card-body">
				<form method="post" action="method2">
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
	                   <input type="text" name="param1" class="form-control" value="문자열">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
	                   <input type="text" name="param2" class="form-control" value="5">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
	                   <input type="text" name="param3" class="form-control" value="3.14">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
	                   <div class="btn-group btn-group-toggle" data-toggle="buttons">
	                      <label class="btn btn-secondary active">
	                        <input type="radio" name="param4" checked value="true"> true
	                      </label>
	                      <label class="btn btn-secondary">
	                        <input type="radio" name="param4" value="false"> false
	                      </label>
	                   </div>
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
	                   <input type="date" name="param5" class="form-control" value="2030-12-05">
	                </div>
	                <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
	             </form>
			</div>
			<div class="card m-2">
			<div class="card-header">
				AJAX 방식으로 요청
			</div>
			<div class="card-body">
			
			<form id="form1" name="form1">
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
	                   <input type="text" id="param1" name="param1" class="form-control" value="문자열">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
	                   <input type="text" id="param2" name="param2" class="form-control" value="5">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
	                   <input type="text" id="param3" name="param3" class="form-control" value="3.14">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
	                   <div class="btn-group btn-group-toggle" data-toggle="buttons">
	                      <label class="btn btn-secondary active">
	                        <input type="radio" id="radio1" name="param4" checked value="true"> true
	                      </label>
	                      <label class="btn btn-secondary">
	                        <input type="radio" id="radio2" name="param4" value="false"> false
	                      </label>
	                   </div>
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
	                   <input type="date" id="param5" name="param5" class="form-control" value="2030-12-05">
	                </div>
	             </form>
			<div>
				<button class="btn btn-info btn-sm" onclick="requestGet()">GET 방식 요청</button>
				<button class="btn btn-info btn-sm" onclick="requestPost()">POST 방식 요청</button>
			</div>
			</div>
			<script>
				/* function requestGet() {
					$.ajax({
						url: "method1",
						method: "get",
						// data: "param1=문자열&param2=5&param3=3.14&param4=true&param5=2021-08-27"
						data: {
							param1:"문자열", 
							param2: 5, 
							param3:3.14, 
							param4:true, 
							param5:"2021-08-27"
						}
					})
					.done(() => {});
				}*/
				
				function requestGet() {
					// element에 해당하는 value 가져오기
					const param1 = document.form1.param1.value;
					const param2 = document.querySelector("#param2").value;
					const param3 = $("#param3").val();
					const param4 = $("#form1 input[name=param4]:checked").val();
					const param5 = $("#param5").val();
					
					console.log("param1:", param1);
					console.log("param2:", param2);
					console.log("param3:", param3);
					console.log("param4:", param4);
					console.log("param5:", param5);
					$.ajax({
						url: "method1",
						method: "get",
						// jsp(절대경로 할 때 넣은거)와 javascript 둘다 ${} 를 사용하면 충돌이 난다.
						data: {
							
							// param1:param1, // 속성 이름과 파라미터 이름이 같으면 생략 가능
							param1,
							param2,
							param3,
							param4,
							param5,
						}
					})
					.done(() => {});
					
					
				}
				function requestPost() {
					const param1 = document.form1.param1.value;
					const param2 = document.querySelector("#param2").value;
					const param3 = $("#param3").val();
					const param4 = $("#form1 input[name=param4]:checked").val();
					const param5 = $("#param5").val();
					// 유효성 검사: 클라이언트에서 데이터가 올바른지 검증 후 서버에 보내는 것
					$.ajax({
						url: "method2",
						method: "post",
						data: {
							param1:param1,
							param2,
							param3,
							param4,
							param5,
						}
					})
					.done(() => {});
				}
			</script>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>