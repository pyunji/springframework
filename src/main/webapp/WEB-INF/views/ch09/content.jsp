<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		FileUpload & FileDownload
	</div>
	<div class="card-body">
		<div class="card">
          <div class="card-header">
            Form태그를 이용한 FileUpload
          </div>
          <div class="card-body">
            <form method="post" enctype="multipart/form-data" action="fileupload">
                <div class="form-group">
                  <label for="title">Title</label> 
                  <input type="text" class="form-control" id="title" name="title" placeholder="파일 제목">
               </div>
               <div class="form-group">
                  <label for="desc">File Description</label> 
                  <input type="text" class="form-control" id="desc" name="desc" placeholder="파일 설명">
               </div>
               <div class="form-group">
                  <label for="attach">Example file input</label>
                  <!-- multiple로 여러개의 파일 선택 가능 -->
                  <input type="file" class="form-control-file" id="attach" name="attach" multiple> <!-- file을 서버에 보낼때는 반드시 input type-"file"을 추가해야한다 -->
               </div>
               <button class="btn btn-info btn-sm">Form 파일 업로드</button>
               <a href="javascript:fileupload()" class="btn btn-info btn-sm">AJAX 파일 업로드</a>
               
            </form>
          </div>
          <script>
          	function fileupload() {
          		// 1. 입력된 정보를 얻기
          		const title = $("#title").val();
          		const desc = $("#desc").val();
          		// 지금 jquery 객체가 아니라 element 객체가 필요하다
          		const attach = document.querySelector("#attach").files[0]; // 여러개의 파일을 업로드했을 경우 
          		console.log(attach);
          		
          		// 2. Multipart/form-data
          		const formData = new FormData(); // Multipart 데이터를 만들어내는 객체
          		formData.append("title", title);
          		formData.append("desc", desc);
          		formData.append("attach", attach);
          		
          		// 3. Ajax로 서버로 전송
          		$.ajax({
          			url:"fileuploadAjax",
          			method: "post", // 반드시 post 방식으로 전송해야함
          			data: formData,
          			cache: false, // 용량이 다소 큰 이미지 파일을 브라우저에 캐시로 저장하면 자원 낭비니까
          			processData: false, // 파일 데이터를 가공하지 않고 있는 그대로 서버에 전송하게 함
          			contentType: false // 파일을 여러개 보내기 때문에 여러 파일의 타입이 다를 수 있어서 전체 contentType을 명시할 필요가 없다.
          		}).done((data => {
          			console.log(data);
          			window.alert("파일 전송이 성공됨");
          		}));
          	}
          </script>
          
        </div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>