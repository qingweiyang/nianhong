<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="<%=basePath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/main.css">

<script type="text/javascript" src="<%=basePath%>/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/date-helper.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/util.js"></script>