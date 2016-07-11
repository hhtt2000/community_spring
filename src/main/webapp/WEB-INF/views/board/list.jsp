<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../include/header.jsp" %>

<style>
.notice-text {
font-weight: bold;
}
</style>

<!-- page content -->
<div class="right_col" role="main">

	<div class="box-body">
		<select name="searchType">
			<option value="n" <c:out value="${cri.searchType == null ? 'selected' : ''}" />>---</option>
			<option value="t" <c:out value="${cri.searchType eq 't' ? 'selected' : ''}" />>제목</option>
			<option value="c" <c:out value="${cri.searchType eq 'c' ? 'selected' : ''}" />>내용</option>
			<option value="w" <c:out value="${cri.searchType eq 'w' ? 'selected' : ''}" />>작성자</option>
			<option value="tc" <c:out value="${cri.searchType eq 'tc' ? 'selected' : ''}" />>제목+내용</option>
			<option value="cw" <c:out value="${cri.searchType eq 'cw' ? 'selected' : ''}" />>내용+작성자</option>
			<option value="tcw" <c:out value="${cri.searchType eq 'tcw' ? 'selected' : ''}" />>제목+내용+작성자</option>
		</select>
		<input type="text" name="keyword" id="keywordInput" value="${cri.keyword}">
		<button id="searchBtn">검색</button>
		<button id="newBtn">글쓰기</button>
	</div>
	
	<table class="table">
		<tr>
			<th style="width: 80px">번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th style="width: 140px">등록일</th>
			<th style="width: 80px">조회</th>
		</tr>
		
	<c:forEach items="${list}" var="boardVO">
		<tr <c:out value="${boardVO.notice eq 't' ? 'class=notice-text' : ''}"/>>
			<td style="text-align: center;">
			<c:choose>
				<c:when test="${boardVO.notice eq 't'}">
					<span class="label label-info">공 지</span>
				</c:when>
				<c:otherwise>
					${boardVO.bno}
				</c:otherwise>
			</c:choose>
			</td>
			<td><a href="/board/read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}">${boardVO.title} <strong>[${boardVO.replycnt}]</strong></a></td>
			<td>${boardVO.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
			<td>${boardVO.viewcnt}</td>
		</tr>
	</c:forEach>
	</table>
	
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${pageMaker.prev}">
				<li><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
			</c:if>
			
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}" />>
					<a href="list${pageMaker.makeQuery(idx)}">${idx}</a>
				</li>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<li><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
</div>
<!-- /page content -->

<script>

	var result = '${msg}';
	
	if(result == "success") {
		alert("처리가 완료되었습니다.");
	}
	
</script>

<script>
	$(document).ready(function() {
		$("#searchBtn").on("click", function(event) {
			self.location = "/board/list"
			+ "?page=1&perPageNum=${cri.perPageNum}"
			+ "&searchType=" + $("select option:selected").val()
			+ "&keyword=" + $("#keywordInput").val()
			+ "&boardType=${cri.boardType}";
		});
		
		$("#newBtn").on("click", function() {
			self.location = "register?boardType=${cri.boardType}";
		});
	});
</script>

<%@ include file="../include/footer.jsp" %>