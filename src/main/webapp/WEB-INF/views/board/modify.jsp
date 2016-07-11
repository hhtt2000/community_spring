<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<style>
.popup {
position: absolute;
}
.img-back {
background-color: gray;
opacity: 0.5;
width: 100%;
height: 300%;
overflow: hidden;
z-index: 11010;
}
.img-front {
z-index: 11100;
opacity: 1;
border:1px;
margin: auto;
}
.show {
position: relative;
max-width: 1200px;
max-height: 800px;
overflow: auto;
}
.file-drop {
width: 90%;
height: 100px;
border: 1px dotted gray;
margin: auto;
}
</style>

<!-- page content -->
<div class="popup img-back" style="display:none;"></div>
<div id="popup_front" class="popup img-front" style="display:none;">
	<img id="popup_img">
</div>

<div class="right_col" role="main">
	<form role="form" action="modify" method="post">
		<input type="hidden" name="bno" value="${boardVO.bno}">
		<input type="hidden" name="boardType" value="${cri.boardType}">
		<input type="hidden" name="page" value="${cri.page}">
		<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
		<input type="hidden" name="searchType" value="${cri.searchType}">
		<input type="hidden" name="keyword" value="${cri.keyword}">
	
		<div class="box-body">
			<div class="form-group">
				<label>제목</label>
				<input type="text" name="title" class="form-control" value="${boardVO.title}">
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
			</div>
			<div class="form-group">
				<label>작성자</label>
				<input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
			</div>
			<div class="form-group">
				<label>파일 끌어 넣기</label>
				<div class="file-drop"></div>
			</div>
		</div>
		<!-- /.box-body -->
	</form>
	<div class="box-footer">
		<div>
			<hr>
		</div>
		
		<div class="row upload-list"></div>
		
		<button type="submit" class="btn btn-primary">확인</button>
		<button type="submit" class="btn btn-warning">취소</button>
	</div>
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

	var bno = ${boardVO.bno};
	var template = Handlebars.compile($("#template").html());
	
	$.getJSON("/board/getAttach/"+bno, function(list) {
		$(list).each(function() {
			var fileInfo = getFileInfo(this);
			
			var html = template(fileInfo);
			
			$(".upload-list").append(html);
		});
	});
	
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
			
			$(".img-back").show("slow");
			$(".img-front").show("slow");
			imgTag.addClass("show");
		}
	});
	
	$("#popup_img").on("click", function() {
		$(".img-front").hide("slow");
		$(".img-back").hide("slow");
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

	$(document).ready(function() {
		var formObj = $("form[role='form']");
		
		$(".btn-primary").on("click", function() {
			var str = "";
			
			$(".upload-list .delbtn").each(function(index) {
				str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'>";
			});
			
			formObj.append(str);
			
			formObj.submit();
		});
		
		$(".btn-warning").on("click", function() {
			self.location = "/board/list?boardType=${cri.boardType}&page=${cri.page}&perPageNum=${cri.perPageNum}"
				+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
		});
	});

</script>

<%@ include file="../include/footer.jsp" %>