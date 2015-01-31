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
  loadType();
});

/*根据数据库type类型加载用户当前能看到的所有任务类型*/
function loadType() {
  $.post(
    "loadType.do",
    {},
    function(data){
      var typeContent = "";
      //定义每行展示的type数量
      var mloop = 4;
      $.each(data, function(i, item) {
        if(i%mloop == 0) {
          typeContent += '<div class="row mt15">';
        }
        typeContent += '<div class="col-md-3"><button type="button" onclick="loadTaskTable(\''+item+'\');" class="btn btn-info btn-lg btn-block">'+item+'</button></div>';
        if(i%mloop == (mloop-1)) {
            typeContent += "</div>";
        }
      });
      //显示任务类别
      $("#task-type").html(typeContent);
    });
}

/*-- 改变面包屑的值 --*/
function changeBreadcrumb(type) {
  $("#task-room-breadcrumb li:eq(1)").html(type);
}

function loadTaskTable(type) {
  changeBreadcrumb(type);

  var param = {};
  param.type = type;

  $.post(
    "loadTaskTable.do",
    param,
    function(data){
      var text = "";
      $.each(data,function(i,item){
      var option = "<div><a href='javascript:void(0);' onclick='showDetail(this, "+item.id+");'>查看详情</a></div>";

      var time = getFormatDateByLong(item.publish_time, "yyyy-MM-dd hh:mm:ss");
      var finish_time = getFormatDateByLong(item.finish_time, "yyyy-MM-dd hh:mm:ss");
      text += "<tr><td>"+item.id+"</td>"+
              "<td>"+item.commission+"</td>"+
              "<td>"+time+"</td>"+
              "<td>"+item.title+"</td>"+
              "<td>"+option+"</td>"+
              "</tr>";
        });
      $("#task-room-table tbody").html(text);
    });
}

/*点击查看详情，跳转至detail.html页面*/
function showDetail(item, taskID) {
  location.href = "./detail.jsp?id="+taskID;
}

</script>

</head>
<body>
<div id="wrapper">  

  <!-- 导航栏 -->
  <%@ include file="../nav/navigation.jsp" %>
  
  <!-- 任务种类面包屑 -->
  <ol id="task-room-breadcrumb" class="breadcrumb mt15">
    <li><a href="#">任务大厅</a></li>
    <li class="active"></li>
  </ol>

  <!-- 任务类别选择 -->
  <div id="task-type"></div>

  <table id="task-room-table" class="table table-bordered table-hover table-condensed mt15">
    <thead>
      <tr class="active">
        <th>任务编号</th>
        <th>任务金额</th>
        <th>发布时间</th>
        <th>任务标题</th>
        <th>操作</th>
      </tr>
    </thead>
      <tbody></tbody>
  </table>

</div>
</body>
</html>