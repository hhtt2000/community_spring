<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
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

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <!-- page content -->
        <div class="col-md-12">
          <div class="col-middle">
            <div class="text-center">
              <h1 class="error-number"></h1>
              <h2>Error</h2>
              <p>${exception}</p>
              <p>We track these errors automatically, but if the problem persists feel free to contact us. In the meantime, try refreshing. <a href="#">Report this?</a>
              </p>
            </div>
          </div>
        </div>
        <!-- /page content -->
      </div>
    </div>

    <!-- jQuery -->
	<script src="/resources/plugins/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
	<script src="/resources/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
	
	<!-- Custom Theme script -->
	<script src="/resources/js/custom.min.js"></script>
  </body>
</html>