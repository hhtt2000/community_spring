<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<style>
.file-drop {
width: 90%;
height: 100px;
border: 1px dotted gray;
margin: auto;
}
</style>

<!-- page content -->
<div class="popup back" style="display:none;"></div>
<div id="popup_front" class="popup front" style="display:none;">
	<img id="popup_img">
</div>

<div class="right_col" role="main">
	<div><h3><Strong>${boardCategory.boardName}</Strong></h3></div>
	<br>
	<form id="registerForm" role="form" action="/board/register" method="post">
		<div class="box-body">
			<input type="hidden" name="boardType" class="form-control" value="${boardCategory.boardType}">
		<c:if test="${login.role eq 'admin'}">
			<input type="hidden" name="notice" class="form-control" value="t">
		</c:if>
			<div class="form-group">
				<label>제목</label>
				<input type="text" name="title" class="form-control" placeholder="Title">
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" name="content" rows="3" placeholder="Type in any words.."></textarea>
			</div>
			<div class="form-group">
				<label>작성자</label>
				<input type="text" name="writer" class="form-control" value="${login.userid}" readonly>
			</div>
			<div class="form-group">
				<label>파일 끌어 넣기</label>
				<div class="file-drop"></div>
			</div>
		</div>
		<!-- /.box-body -->
		
		<div class="box-footer">
			<div>
				<hr>
			</div>
			
			<div class="row upload-list"></div>
			
			<button type="submit" class="btn btn-primary">확인</button>
		</div>
	</form>
</div>
<!-- /page content -->

<script type="text/javascript" src="/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script id="template" type="text/x-handlebars-template">
	<div class="col-md-3 col-md-3 col-xs-6" data-src='{{fullName}}'>
		<div class="x_panel">
			<div class="x_title mailbox-attachment-info">
				<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
				<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
			</div>
			<div class="x_content">
				<img src="{{imgsrc}}" alt="Attachment">
			</div>
		</div>
	</div>
</script>

<script>
	var template = Handlebars.compile($("#template").html());
	
	$(".file-drop").on("dragenter dragover", function(event) {
		event.preventDefault();
	});
	
	$(".file-drop").on("drop", function(event) {
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0];
		
		var formData = new FormData();
		
		formData.append("file", file);
		
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false,
			type: "post",
			success: function(data) {
				var fileInfo = getFileInfo(data);
				
				var html = template(fileInfo);
				
				$(".upload-list").append(html);
			}
		});
	});
	
	$(".upload-list").on("click", ".mailbox-attachment-info a", function(event) {
		var fileLink = $(this).attr("href");
		
		if(checkImageType(fileLink)) {
			event.preventDefault();
			
			var imgTag = $("#popup_img");
			imgTag.attr("src", fileLink);
			
			$(".popup").show("slow");
			imgTag.addClass("show");
		}
	});
	
	$(".upload-list").on("click", ".delbtn", function(event) {
		event.preventDefault();
		
		var that = $(this);
		
		$.ajax({
			url: "/deleteFile",
			type: "post",
			data: {fileName: that.attr("href")},
			dataType: "text",
			success: function(result) {
				if(result == "deleted") {
					that.closest("li").remove();
				}
			}
		});
	});
	
	$("#registerForm").submit(function(event) {
		event.preventDefault();
		
		var that = $(this);
		
		var str = "";
		
		$(".upload-list .delbtn").each(function(index) {
			str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'>";
		});
		
		that.append(str);
		
		that.get(0).submit();
	});
</script>

<%@ include file="../include/footer.jsp" %>