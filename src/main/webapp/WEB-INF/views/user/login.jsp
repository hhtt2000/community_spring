<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>로그인</title>

    <!-- Bootstrap -->
    <link href="/resources/plugins/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- Font Awesome -->
	<link href="/resources/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="/resources/plugins/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="/resources/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="login">
     <div class="login_wrapper">
       <div class="animate form login_form">
         <section class="login_content">
           <form action="/user/login" method="post">
             <h1>Login Form</h1>
             <div>
               <input type="text" name="userid" class="form-control" placeholder="사용자 아이디" />
             </div>
             <div>
               <input type="password" name="userpw" class="form-control" placeholder="비밀번호" />
             </div>
             <div>
               <button type="submit" class="btn btn-primary btn-block btn-flat">로그인</button>
               <label>
			   	<input type="checkbox" name="useCookie"> 자동 로그인
			   </label>
             </div>

             <div class="clearfix"></div>

             <div class="separator">
               <p class="change_link">New to site?
                 <a href="/user/register"> 회원가입 </a>
               </p>

               <div class="clearfix"></div>
               <br />

               <div>
                 <h1><i class="fa fa-paper-plane"></i> JCOM</h1>
                 <p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
               </div>
             </div>
           </form>
         </section>
       </div>
     </div>
  </body>
</html>