<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../include/header.jsp" %>
<!-- for popup movable -->
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.5/handlebars.js"></script>

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
.claim-popup {
width: 400px;
z-index: 11000;
}
</style>

<!-- image popup -->
<div class="popup img-back" style="display:none;"></div>
<div id="popup_front" class="popup img-front" style="display:none;">
	<img id="popup_img">
</div>

<!-- claim popup -->
<div class="popup claim-popup" style="display:none;">
	<div class="x_panel">
		<div class="x_title">
			<span><strong>신고 작성</strong></span>
		</div>
		<div class="x_content">
			<div class="form-group">
				<label>제목</label>
				<input type="text" id="claimTitle" class="form-control">
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" id="claimContent" rows="3" placeholder="신고할 아이디와 댓글일 경우 내용을 붙여넣기 해주시기 바랍니다."></textarea>
			</div>
			<div class="form-group">
				<label>작성자</label>
				<input type="text" id="claimer" class="form-control" value="${login.userid}" readonly="readonly">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-default" id="submitClaimBtn">확인</button>
				<button class="btn btn-default" id="cancelClaimBtn">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- page content -->
<div class="right_col" role="main">
	<div class="x_panel">
		<div class="x_content">
			<form id="mainForm" role="form" method="post">
				<input type="hidden" name="bno" value="${boardVO.bno}">
				<input type="hidden" name="boardType" value="${cri.boardType}">
				<input type="hidden" name="page" value="${cri.page}">
				<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
				<input type="hidden" name="searchType" value="${cri.searchType}">
				<input type="hidden" name="keyword" value="${cri.keyword}">
			</form>
			<div class="box-body">
				<div class="form-group">
					<label>제목</label>
					<input type="text" name="title" class="form-control" value="${boardVO.title}" readonly="readonly">
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자 <a href="#" class="claimBtn" data-toggle="tooltip" data-placement="right" title="신고"><i class="fa fa-ban"></i></a></label>
					<input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
				</div>
				<br>
				<div class="row upload-list"></div>
			</div>
			<!-- /.box-body -->
		</div>
	</div>

	<div class="box-footer">
	<c:if test="${login.userid == boardVO.writer}">
		<button type="submit" class="btn btn-default" id="modifyBtn">수정</button>
		<button type="submit" class="btn btn-default" id="removeBtn">삭제</button>
	</c:if>
		<button type="submit" class="btn btn-primary" id="goListBtn">목록</button>
	</div>
	
	<hr>
	
<c:if test="${not empty login}">
	<div class="row">
		<div class="col-md-12">
			<div>
				<div>
					<h3>새로운 댓글 작성</h3>
				</div>
				<div class="box-body">
					<label>작성자</label>
					<input class="form-control" type="text" id="newReplyWriter" value="${login.userid}" readonly="readonly">
					<label>내용</label>
					<textarea class="form-control" id="newReplyText" placeholder="reply text"></textarea>			
				</div>
				<br>
				<div class="box-footer">
					<button type="submit" class="btn btn-primary" id="replyAddBtn">확인</button>
				</div>
			</div>
		</div>
	</div>
</c:if>
	
	<hr>
	
	<button id="repliesBtn" class="bg-green">댓글 목록 [<small id="replycntSmall">${boardVO.replycnt}</small>]</button>
	<ul class="timeline" id="repliesDiv" style="list-style:none;">
	</ul>
	
	<div class="text-center">
		<ul id="pagination" class="pagination pagination-sm no-margin">
		</ul>
	</div>
	
	<!-- Modal -->
	<div id="modifyModal" class="modal modal-primary fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body" data-rno>
					<p><textarea id="replytext" class="form-control"></textarea></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="replyModBtn">수정</button>
					<button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->

<script type="text/javascript" src="/resources/js/upload.js"></script>

<script id="templateAttach" type="text/x-handlebars-template">
	<div class="col-md-3 col-md-3 col-xs-6 upload-item" data-src='{{fullName}}'>
		<div class="x_panel">
			<div class="x_title mailbox-attachment-info">
				<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
			</div>
			<div class="x_content">
				<img src="{{imgsrc}}" alt="Attachment">
			</div>
		</div>
	</div>
</script>

<script id="template" type="text/x-handlebars-template">
	{{#each .}}
	<li class="reply-list" data-rno={{rno}}>
		<div class="timeline-item">
			<h4 class="timeline-header"><strong><i class="fa fa-comments"></i> {{replyer}}</strong> <small><a href="#" class="claimBtn" data-toggle="tooltip" data-placement="right" title="신고"><i class="fa fa-ban"></i></a></small></h4>
			<span class="time">
				<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}&nbsp;
			</span>
			<span>
				{{#eqReplyer replyer}}
					<a class="btn btn-default btn-xs" data-toggle="modal" data-target="#modifyModal">수정</a>
				{{/eqReplyer}}
			</span>
			<div class="timeline-body">{{replytext}}</div>
		</div>
	</li>
	{{/each}}
</script>

<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		
		return year+"/"+month+"/"+date;
	});	
	
	Handlebars.registerHelper("eqReplyer", function(replyer, block) {
		var accum = "";
		if(replyer == "${login.userid}") {
			accum += block.fn();
		}
		return accum;
	});
	
	var bno = ${boardVO.bno};
	var replyPage = 1;
	
	function getPage(pageInfo) {
		$.getJSON(pageInfo, function(data) {
			printData(data.list, $("#repliesDiv"), $("#template"));
			printPaging(data.pageMaker, $(".pagination"));
			
			$("#modifyModal").modal("hide");
			$("#replycntSmall").html(data.pageMaker.totalCount);
		});
	}
	
	var printData = function(replyArr, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());
		
		var html = template(replyArr);
		$(".reply-list").remove();
		target.append(html);
	}
	
	var printPaging = function(pageMaker, target) {
		var str = "";
		
		if(pageMaker.prev) {
			str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
		}
		
		for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i ? 'class=active' : '';
			str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
		}
		
		if(pageMaker.next) {
			str += "<li><a href='"+(pageMaker.endPage+1)+"'> >> </a></li>";
		}
		
		target.html(str);
	}
	
	$("#replyAddBtn").on("click", function() {
		var replyerObj = $("#newReplyWriter");
		var replytextObj = $("#newReplyText");
		var replyer = replyerObj.val();
		var replytext = replytextObj.val();
		
		$.ajax({
			type: 'post',
			url: '/replies',
			contentType: 'application/json',
			dataType: 'text',
			data: JSON.stringify({bno: bno, replyer: replyer, replytext: replytext}),
			success: function(result) {
				if(result == 'success') {
					alert("등록되었습니다.");
					replyPage = 1;
					getPage("/replies/"+bno+"/"+replyPage);
					replytextObj.val("");
				}
			}
		});
	});
	
	$("#replyModBtn").on("click", function() {
		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();
		
		$.ajax({
			type: 'put',
			url: '/replies/'+rno,
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "PUT"
			},
			data: JSON.stringify({replytext: replytext}),
			dataType: 'text',
			success: function(result) {
				if(result == 'success') {
					alert("수정되었습니다.");
					getPage("/replies/"+bno+"/"+replyPage);
				}
			}
		});
	});
	
	$("#replyDelBtn").on("click", function() {
		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();
		
		$.ajax({
			type: 'delete',
			url: '/replies/'+rno,
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "DELETE"
			},
			dataType: 'text',
			success: function(result) {
				if(result == 'success') {
					alert("삭제되었습니다.");
					getPage("/replies/"+bno+"/"+replyPage);
				}
			}
		});
	});
	
	$("#repliesBtn").on("click", function() {
		if($(".timeline li").size() > 1) {
			return;
		}
		getPage("/replies/"+bno+"/1");
	});
	
	$(".timeline").on("click", ".reply-list", function(event) {
		var reply = $(this);
		
		$("#replytext").val(reply.find(".timeline-body").text());
		$(".modal-title").html(reply.attr("data-rno"));
	});
	
	$(".pagination").on("click", "li a", function(event) {
		event.preventDefault();
		
		replyPage = $(this).attr("href");
		
		getPage("/replies/"+bno+"/"+replyPage);
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
	
	// 글 신고
	$(".claimBtn").on("click", function(event) {
		event.preventDefault();
		
		var str = "<input type='hidden' id='claimBno' name='bno' value='${boardVO.bno}'>";
		$(".claim-popup .x_content").append(str);
		showClaimPopup(this);
	});
	
	// 댓글 신고
	$("#repliesDiv").on("click", ".reply-list .claimBtn", function(event) {
		event.preventDefault();
		
		var str = "<input type='hidden' id='claimBno' name='bno' value='${boardVO.bno}'>"
				 +"<input type='hidden' id='claimRno' name='rno' value='"+$(".reply-list").attr("data-rno")+"'>";
		$(".claim-popup .x_content").append(str);
		showClaimPopup(this);
	});
	
	function showClaimPopup(claimBtnObj) {
		$(claimBtnObj).tooltip("hide");
		$(".claim-popup").draggable();
		
		var claimBoxWidth = $(".claim-popup").width();
		var claimBoxHeight = $(".claim-popup").height();
		var left = (window.innerWidth - claimBoxWidth) / 2;
		var top = (window.innerHeight - claimBoxHeight) / 2;
		var leftRate = (left / window.innerWidth) * 100 + "%";
		var topRate = (top / window.innerHeight) * 100 + "%";
		
		$(".claim-popup").css({"top": topRate, "left": leftRate});
		$(".claim-popup").show();	
	}
	
	$("#submitClaimBtn").on("click", function(event) {
		event.preventDefault();
		
		var title = $("#claimTitle").val();
		var content = $("#claimContent").val();
		var claimer = $("#claimer").val();
		var url = window.location.href;
		var bno = $("#claimBno").val();
		var rno = $("#claimRno").val();
		console.log(url);
		var data = {title: title, content: content, claimer: claimer, url: url, bno: bno};
		
		if(typeof rno != 'undefined') {
			data.rno = rno;
		}
				
		$.ajax({
			type: "post",
			url: "/claim",
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: "text",
			success: function(result) {
				if(result == "success") {
					alert("등록되었습니다.");
					$(".claim-popup").hide();
					$("#claimTitle").val("");
					$("#claimContent").val("");
				}
			}
		});
	});
	
	$("#cancelClaimBtn").on("click", function(event) {
		event.preventDefault();
		
		$(".claim-popup").hide();
		$("#claimTitle").val("");
		$("#claimContent").val("");
	});

	$(document).ready(function() {
		var formObj = $("#mainForm");
		
		$("#modifyBtn").on("click", function() {
			console.log("form:"+formObj);
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$("#removeBtn").on("click", function() {
			var replyCnt =  $("#replycntSmall").html();
			
			if(replyCnt > 0) {
				alert("댓글이 달린 게시물은 삭제할 수 없습니다.");
				return;
			}
			
			var arr = [];
			$(".upload-list .upload-item").each(function(index) {
				arr.push($(this).attr("data-src"));
			});
			
			if(arr.length > 0) {
				$.post("/deleteAllFiles", {files: arr}, function() {
					
				});
			}
			
			formObj.attr("action", "/board/remove");
			formObj.submit();
		});
		
		$("#goListBtn").on("click", function() {
			self.location = "/board/list?boardType=${cri.boardType}&page=${cri.page}&perPageNum=${cri.perPageNum}"
					+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
		});
		
		var template = Handlebars.compile($("#templateAttach").html());
		
		$.getJSON("/board/getAttach/"+bno, function(list) {
			$(list).each(function() {
				var fileInfo = getFileInfo(this);
				
				var html = template(fileInfo);
				
				$(".upload-list").append(html);
			});
		});
	});

</script>

<%@ include file="../include/footer.jsp" %>