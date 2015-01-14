<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>年鸿电商交易平台</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>

$(document).ready(function(){
	
});

</script>
</head>
<body>
<div id="wrapper">	

<!-- 导航栏 -->
<nav class="navbar navbar-default" role="navigation">
  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li id="nav-1"><a href="javascript:changePage(1)">退出登入</a></li>
      <li class="dropdown">
        <a href="#" id="user-info1" class="dropdown-toggle" data-toggle="dropdown"><%=session.getAttribute("username")%><b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="javascript:void(0)">个人资料</a></li>
          <li><a id="edit" href="javascript:void(0)">关系网</a></li>
          <li class="divider"></li>
          <li><a href="#">注销</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" id="user-info1" class="dropdown-toggle" data-toggle="dropdown">我是雇主<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="javascript:void(0)">发布任务</a></li>
          <li><a id="edit" href="javascript:void(0)">我的任务</a></li>
          <li><a id="edit" href="javascript:void(0)">购买积分</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" id="user-info1" class="dropdown-toggle" data-toggle="dropdown">我是买家<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="javascript:void(0)">我的任务</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" id="user-info1" class="dropdown-toggle" data-toggle="dropdown">积分商城<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="javascript:void(0)">我的积分</a></li>
          <li><a id="edit" href="javascript:void(0)">积分转赠</a></li>
          <li><a id="edit" href="javascript:void(0)">购买积分</a></li>
        </ul>
      </li>
      <li id="nav-1"><a href="javascript:changePage(1)">推荐会员</a></li>
      <li id="nav-2"><a href="javascript:changePage(2)">任务大厅</a></li>
      <li id="nav-3"><a href="javascript:changePage(3)">站内信</a></li>
      <li id="nav-3"><a href="javascript:changePage(3)">联系客服</a></li>
    </ul>

  </div><!-- /.navbar-collapse -->

</nav>

<!-- 面包屑 -->
<ol class="breadcrumb">
  <li><a href="#">Home</a></li>
  <li><a href="#">Library</a></li>
  <li class="active">Data</li>
</ol>

  
</div>
  
</body>
</html>