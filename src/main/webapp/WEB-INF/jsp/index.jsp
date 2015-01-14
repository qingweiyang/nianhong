<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">  
<title>年鸿电商交易平台</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>

<!-- 导航栏 -->
<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="" href="#"><img src="img/logoyj.png" width="120px" height="50px"></a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li id="nav-0" class="active"><a href="javascript:changePage(0)">我的资源</a></li>
      <li id="nav-1"><a href="javascript:changePage(1)">上传资源</a></li>
      <li id="nav-2"><a href="javascript:changePage(2)">搜索资源</a></li>
      <li id="nav-3"><a href="javascript:changePage(3)">用户状态</a></li>
    </ul>

    <ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
        <a href="#" id="user-info" class="dropdown-toggle" data-toggle="dropdown"><%=session.getAttribute("username")%><b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="javascript:void(0)">个人信息</a></li>
          <li><a id="edit" href="javascript:void(0)">信息修改</a></li>
          <li class="divider"></li>
          <li><a href="#">注销</a></li>
        </ul>
      </li>
    </ul>
  </div><!-- /.navbar-collapse -->

</nav>
  
  
</body>
</html>