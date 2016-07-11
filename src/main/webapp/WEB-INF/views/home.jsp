<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="include/header.jsp" %>
        
<!-- page content -->
<div class="right_col" role="main">
	<h1>대문 페이지</h1>
</div>
<!-- /page content -->
        
<script>
	var result = '${msg}';
	
	if(result != '') {
		alert(result);
	}
</script>        
<%@ include file="include/footer.jsp" %>
