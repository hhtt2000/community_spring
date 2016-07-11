<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../include/header_admin.jsp" %>
       
<style>
.form-input {
width: 300px;
}
</style>
 
<!-- page content -->
<div class="right_col" role="main">
	<h1>게시판 개설</h1>
	<br>
	<div class="x_panel">
		<div class="x_content">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<form action="/admin/boardcategory" method="post">
						<div class="box-body">
							<div class="form-group">
								<label>게시판 이름</label>
								<input type="text" name="boardName" class="form-control form-input">
							</div>
							<div class="form-group">
								<label>게시판 타입</label>
								<input type="text" name="boardType" id="boardType" class="form-control form-input">
								<div id="warningText"></div>
							</div>
							<div class="form-group">
								<label>게시판 권한</label>
								<select name="role" class="form-control form-input">
									<option value="user_1" selected>레벨1</option>
									<option value="user_2">레벨2</option>
									<option value="user_3">레벨3</option>
								</select>
							</div>
						</div>
						<div class="box-footer">
							<button type="submit" class="btn btn-primary">확인</button>
						</div>
					</form>
				</div>
				<div class="col-md-6 col-sm-6">
					<h3>참고사항</h3>
					<hr>
					<ul>
						<li>게시판 이름은 게시판 목록에 표시되는 항목입니다.</li>
						<li>게시판 타입은 영어로 작성하여 다른 게시판과 중복되지 않도록 합니다.</li>
						<li>게시판 권한은 사용자가 접근할 수 있는 권한을 설정합니다.</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
</div>
<!-- /page content -->

<script>

	var result = "${msg}";
	
	if(result == "success") {
		alert("등록이 완료되었습니다.");
	}
</script>

<script>

	$(document).ready(function() {
		
		var boardTypes = [];
		
		var warningTextObj = $("#warningText");
		
		$.getJSON("/board/categories", function(list) {
			$(list).each(function() {
				boardTypes.push(this.boardType);
			});
		});
		
		$("#boardType").on("keyup", function() {
			var value = $(this).val();
			
			var idx;
			
			for(idx in boardTypes) {
				if(value === boardTypes[idx] || value === '') {
					warningTextObj.html("<span style='color:red;'>"+ value +"은(는) 사용할 수 없습니다.</span>");
					break;
				} else {
					warningTextObj.html("<span style='color:green;'>"+ value +"은(는) 사용 가능합니다.</span>");
				}
			}
		});
	});
	
</script>
               
<%@ include file="../include/footer.jsp" %>
