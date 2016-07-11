<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../include/header_admin.jsp" %>

<div class="right_col" role="main">
	<form role="form" action="/admin/updateStatus" method="post">
		<input type="hidden" name="cno" value="${claimVO.cno}" id="cno">
		<input type="hidden" name="page" value="${cri.page}">
		<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
		<input type="hidden" name="searchType" value="${cri.searchType}">
		<input type="hidden" name="keyword" value="${cri.keyword}">
	</form>
	
	<div class="row">
		<div class="form-group col-md-2">
			<select id="status" class="form-control">
				<option value="registered" <c:out value="${claimVO.status eq 'registered' ? 'selected' : ''}" />>읽지않음</option>
				<option value="read" <c:out value="${claimVO.status eq 'read' ? 'selected' : ''}" />>읽음</option>
				<option value="completed" <c:out value="${claimVO.status eq 'completed' ? 'selected' : ''}" />>처리완료</option>
			</select>
		</div>
		<div class="col-md-2" style="padding-left: 0px;">
			<button type="submit" class="btn btn-warning" id="updateStatusBtn">상태변경 <i class="fa fa-check"></i></button>
		</div>
	</div>
	
	<div class="box-body">
		<div class="form-group">
			<label>제목</label>
			<input type="text" name="title" class="form-control" value="${claimVO.title}" readonly="readonly">
		</div>
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" name="content" rows="3" readonly="readonly">${claimVO.content}</textarea>
		</div>
		<div class="form-group">
			<label>작성자</label>
			<input type="text" name="writer" class="form-control" value="${claimVO.claimer}" readonly="readonly">
		</div>
	</div>
	<!-- /.box-body -->
	
	<div class="box-footer">
		<button type="submit" class="btn btn-primary" id="goTargetBtn"><i class="fa fa-book"></i> 해당글보기</button>
		<button type="submit" class="btn btn-default" id="deleteTargetBtn">해당글삭제</button>
		<button type="submit" class="btn" id="goListBtn">목록</button>
	</div>
</div>
<!-- /page content -->

<script>
	$(document).ready(function() {
		$("#goListBtn").on("click", function() {
			self.location = "/admin/claim?&page=${cri.page}&perPageNum=${cri.perPageNum}"
					+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
		});
		
		$("#updateStatusBtn").on("click", function() {
			var cno = $("#cno").val();
			var status = $("#status").val();
			
			$.ajax({
				type: "put",
				url: "/admin/updateStatus",
				headers: {
					"Content-Type": "application/json",
					"X-HTTP-Method-Override": "PUT"
				},
				data: JSON.stringify({cno: cno, status: status}),
				dataType: "text",
				success: function(result) {
					if(result == "success") {
						alert("상태 변경을 완료했습니다.");
					}
				}
			});
		});
		
		$("#goTargetBtn").on("click", function() {
			self.location = "${claimVO.url}";
		});
		
		$("#deleteTargetBtn").on("click", function() {
			var bno = ${claimVO.bno};
			var strRno = '${claimVO.rno}';
			
			if(strRno != '') {
				// 댓글 삭제
				var rno = parseInt(strRno);
				
				$.ajax({
					type: "delete",
					url: "/admin/reply/"+rno,
					headers: {
						"Content-Type": "application/json",
						"X-HTTP-Method-Override": "DELETE"
					},
					dataType: "text",
					success: function(result) {
						if(result == "success") {
							alert("삭제가 완료되었습니다.");
						}
					}
				});
			} else {
				// 게시글 삭제
				$.ajax({
					type: "delete",
					url: "/admin/board/"+bno,
					headers: {
						"Content-Type": "application/json",
						"X-HTTP-Method-Override": "DELETE"
					},
					dataType: "text",
					success: function(result) {
						if(result == "success") {
							alert("삭제가 완료되었습니다.");
						}
					}
				});
			}
		});
	});

</script>

<%@ include file="../include/footer.jsp" %>