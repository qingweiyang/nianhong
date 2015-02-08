<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易信息</title>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="../css/main.css">

<script type="text/javascript" src="../jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/date-helper.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript">

$(document).ready(function(){
  /*解析url，加载任务详情*/
  var publisher = getUrlParam("publisher");
  var accepter = getUrlParam("accepter");
  getDealRecord(publisher, accepter);

});

function getDealRecord(publisher, accepter) {
  var param = {};
  param.publisher = publisher;
  param.accepter = accepter;

  $.post(
      "getDealRecord.do",
      param, 
      function(data) {
        var text = "";
        $.each(data,function(i,item){
          $("#deal-username").text("雇主>"+item.publisher+" 买家>"+item.accepter);
          var time = getFormatDateByLong(item.publish_time, "yyyy-MM-dd hh:mm:ss");
          var acc_time = getFormatDateByLong(item.accept_time, "yyyy-MM-dd hh:mm:ss");
          var relation = "交易失败："+item.remark;
          //任务状态：已删除（0）、待同意（1）、任务中（2）、已提交完成信息（3）、交易完成（4）、交易失败（5）；
          if(item.status == 1) {
            relation = "待同意";
          } else if(item.status == 2) {
            relation = "任务中";
          } else if(item.status == 3) {
            relation = "已提交完成信息";
          }else if(item.status == 4) {
            relation = "交易完成";
          }
          text += "<tr><td>"+item.id+"</td>"+
                      "<td>"+time+"</td>"+
                      "<td>"+item.brief+"</td>"+
                      "<td>"+acc_time+"</td>"+
                      "<td>"+relation+"</td>"+
                      "</tr>";
        });
        $("#deal-table tbody").html(text);
      }
    );

}


</script>

</head>
<body>

<div id="wrapper" class="container">
	
  <%@ include file="../nav/navigation.jsp" %>

  <div class="panel panel-success">
  <!-- Default panel contents -->
  <div class="panel-heading">交易双方：<span id="deal-username"></span></div>

  <table id="deal-table" class="table table-bordered table-hover table-condensed">
    <thead>
      <tr class="active">
        <th>任务编号</th>
        <th>发布时间</th>
        <th>任务概述</th>
        <th>接受时间</th>
        <th>关系</th>
      </tr>
    </thead>
      <tbody></tbody>
  </table>

  </div>
  
</div>


</body>
</html>