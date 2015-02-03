<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>年鸿电商交易平台</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="../jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/date-helper.js"></script>
<script type="text/javascript">

$(document).ready(function(){
  changeTab(0);
});

function changeTab(index) {
  var page_map = new Array("waitedAcceptTask.jsp",
                 "waitedVerifyTask.jsp",
                 "doingTask.jsp",
                 "finishTask.jsp",
                 "failTask.jsp"); 

  $.ajax({  
      type: "post",
      dataType: "text",
      url: page_map[index],
      success: function(data){
        $("#saler-task-container").html(data);
        $("#saler-task-tab li").each(function(i, item){
          if(index == i) {
            $(item).attr("class", "active");
          } else {
            $(item).removeClass("active");
          }
          
        });
      },
      error: function(data){}
    }); 

};


</script>

</head>
<body>
<div id="wrapper">  

  <!-- 导航栏 -->
  <%@ include file="../nav/navigation.jsp" %>

<ul id="saler-task-tab" class="nav nav-tabs" role="tablist">
  <li role="presentation" class="active"><a href="javascript:changeTab(0)">待领取</a></li>
  <li role="presentation"><a href="javascript:changeTab(1)">待审核</a></li>
  <li role="presentation"><a href="javascript:changeTab(2)">任务中</a></li>
  <li role="presentation" class="active"><a href="javascript:changeTab(3)">已完成</a></li>
  <li role="presentation"><a href="javascript:changeTab(4)">交易失败</a></li>
</ul>

<div id="saler-task-container"></div>

</div>
  

</body>
</html>