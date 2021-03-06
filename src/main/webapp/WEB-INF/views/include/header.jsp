<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<title>JCOM</title>
	
	<!-- Bootstrap -->
	<link href="/resources/plugins/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- Font Awesome -->
	<link href="/resources/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	
	<!-- Custom Theme Style -->
	<link href="/resources/css/custom.min.css" rel="stylesheet">
</head>

<!-- jQuery -->
<script src="/resources/plugins/jquery/dist/jquery.min.js"></script>

<body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="/" class="site_title"><i class="fa fa-paper-plane"></i> <span>JCOM</span></a>
            </div>

            <div class="clearfix"></div>

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-edit"></i> 게시판 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                    <c:forEach items="${boardCategories}" var="category">
                      <li><a href="/board/list?boardType=${category.boardType}">${category.boardName}</a></li>
                    </c:forEach>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
            <!-- /sidebar menu -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav class="" role="navigation">
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
              <c:if test="${not empty login}">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    ${login.username}
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                  <c:if test="${fn:split(login.role, '_')[1] eq null}">
                  	<li><a href="">관리자</a></li>
                  </c:if>
                  <c:if test="${fn:split(login.role, '_')[1] ne null}">
                    <li><a href="">레벨 ${fn:split(login.role, '_')[1]} (${login.upoint}점)</a></li>
                  </c:if>
                    <li><a href="/user/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>
              </c:if>
              <c:if test="${empty login}">
              	<li><a href="/user/login">Log In</a></li>
              </c:if>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->