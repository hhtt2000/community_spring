<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    

<%@ include file="../include/header.jsp" %>
<style>
.error {
color: #169F85;
}	
</style>

	<div class="right_col" role="main">
  	<div class="x_panel">
	    <div class="x_title">
	      <h2>회원가입</h2>
	      <div class="clearfix"></div>
	    </div>
	    <div class="x_content">
	      <br />
	      <form:form class="form-horizontal form-label-left" action="/user/register" method="post" commandName="userVO">
	        <div class="form-group">
	          <label class="control-label col-md-3 col-sm-3 col-xs-12">아이디 <span class="required">*</span>
	          </label>
	          <div class="col-md-6 col-sm-6 col-xs-12">
	            <form:input type="text" path="userid" required="required" class="form-control col-md-7 col-xs-12"/>
	            <form:errors path="userid" cssClass="error"/>
	          </div>
	        </div>
	        <div class="form-group">
	          <label class="control-label col-md-3 col-sm-3 col-xs-12">비밀번호 <span class="required">*</span>
	          </label>
	          <div class="col-md-6 col-sm-6 col-xs-12">
	            <form:input type="password" path="userpw" required="required" class="form-control col-md-7 col-xs-12"/>
	            <form:errors path="userpw" cssClass="error"/>
	          </div>
	        </div>
	        <div class="form-group">
	          <label class="control-label col-md-3 col-sm-3 col-xs-12">이름 <span class="required">*</span>
	          </label>
	          <div class="col-md-6 col-sm-6 col-xs-12">
	            <form:input type="text" path="username" class="form-control col-md-7 col-xs-12" required="required"/>
	            <form:errors path="username" cssClass="error"/>
	          </div>
	        </div>
	        <div class="form-group">
	          <label class="control-label col-md-3 col-sm-3 col-xs-12">이메일 <span class="required">*</span>
	          </label>
	          <div class="col-md-6 col-sm-6 col-xs-12">
	            <form:input type="email" path="email" class="form-control col-md-7 col-xs-12" required="required"/>
	            <form:errors path="email" cssClass="error"/>
	          </div>
	        </div>
	        <div class="ln_solid"></div>
	        <div class="form-group">
	          <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
	            <button type="submit" class="btn btn-primary">취소</button>
	            <button type="submit" class="btn btn-success">확인</button>
	          </div>
	        </div>
	      </form:form>
	    </div>
	  </div>
	 </div>
  <%@ include file="../include/footer.jsp" %>