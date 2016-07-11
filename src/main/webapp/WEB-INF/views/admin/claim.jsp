<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../include/header_admin.jsp" %>
        
<!-- page content -->
<div class="right_col" role="main">
	<h1>신고 목록</h1>
	<br>
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
	</div>
	
	<table class="table">
		<tr>
			<th style="width: 80px">번호</th>
			<th style="width: 80px">처리상태</th>
			<th>제목</th>
			<th>작성자</th>
			<th style="width: 140px">등록일</th>
		</tr>
		
	<c:forEach items="${list}" var="claimVO">
		<tr>
			<td>${claimVO.cno}</td>
			<td style="text-align: center;">
			<c:choose>
				<c:when test="${claimVO.status eq 'registered'}">
					<span class="label label-default">읽지않음</span>
				</c:when>
				<c:when test="${claimVO.status eq 'read'}">
					<span class="label label-warning">읽음</span>
				</c:when>
				<c:when test="${claimVO.status eq 'completed'}">
					<span class="label label-success">처리완료</span>
				</c:when>
			</c:choose>
			</td>
			<td><a href="/admin/readClaim${pageMaker.makeQuery(pageMaker.cri.page)}&cno=${claimVO.cno}">${claimVO.title}</a></td>
			<td>${claimVO.claimer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${claimVO.regdate}"/></td>
		</tr>
	</c:forEach>
	</table>
	
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${pageMaker.prev}">
				<li><a href="claim${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
			</c:if>
			
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}" />>
					<a href="claim${pageMaker.makeQuery(idx)}">${idx}</a>
				</li>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<li><a href="claim${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
</div>
<!-- /page content -->

<script>
	$(document).ready(function() {
		$("#searchBtn").on("click", function(event) {
			self.location = "/admin/claim"
			+ "?page=1&perPageNum=${cri.perPageNum}"
			+ "&searchType=" + $("select option:selected").val()
			+ "&keyword=" + $("#keywordInput").val();
		});
	});
</script>
               
<%@ include file="../include/footer.jsp" %>
