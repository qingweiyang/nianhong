<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../nav/resource.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>年鸿电商交易平台</title>

<script type="text/javascript">

$(document).ready(function(){

  /*解析url，加载任务详情*/
  var taskID = getUrlParam("id");
  loadTaskInf(taskID, "路人");
  loadSalerInf(taskID);

  /*监听接受任务按钮*/
  $("#accept-button").click(function(){
  	acceptTask();
  });
});

/* 改变面包屑的值 */
function changeBreadcrumb(type) {
  $("#task-room-breadcrumb li:eq(1)").html(type);
}

function acceptTask() {
	if($("#accept-button").text() == "确认领取") {
		var param = {};
		var item = $("input[name='subTaskRadios']:checked");
		param.id = item.parent().parent().children("td:eq(0)").children("label").text();
		param.accepter = "路人";

		//领取任务
		$.post(
			"acceptTask.do",
			param,
			function(data) {
				if(data.success == false) {
					alert("领取失败:"+data.message);
					
				} else {
					location.href = "acceptSuccess.jsp";
				}
			}
			); 
	}
}

/* 加载任务的具体信息，并添加用户能否接受该任务状态 */
function loadTaskInf(taskID, accepter) {
	//改变面包屑导航的值，显示当前任务id
	$("#taskid-breadcrumb").text("任务编号："+taskID);

	var param = {};
	param.taskID = taskID;
	param.accepter = accepter;

	$.post(
		"loadTaskInf.do",
		param,
		function(data){
			$("#title").text(data.taskModel.title);
			$("#brief").text(data.taskModel.brief);
			$("#type").text(data.taskModel.type);
			$("#commission").text(data.taskModel.commission);
			$("#advanced").text(data.taskModel.advanced);
			$("#reward").text(data.taskModel.reward);
			$("#buyer_freeze").text(data.taskModel.buyer_freeze);
			$("#person_need").text(data.taskModel.person_need);
			var subTasks = data.subtask;
			var text = "";
        	$.each(subTasks,function(i,item){
          		var time = getFormatDateByLong(item.subTaskModel.start_time, "yy-MM-dd hh:mm");
          		time += " ~ "+getFormatDateByLong(item.subTaskModel.finish_time, "yy-MM-dd hh:mm");
          		var area = item.subTaskModel.province+" "+item.subTaskModel.city;
          		text += "<tr>"+
          			  "<td><label class='sr-only'>"+item.subTaskModel.id+"</label><input type='radio' name='subTaskRadios' value='option1'></td>"+
                      "<td>"+time+"</td>"+
                      "<td>"+area+"</td>"+
                      "<td>"+item.subTaskModel.person_need+"</td>"+
                      "<td>"+item.actSubNum+"</td>"+
                      "</tr>";
        		});
        	$("#subtask-table tbody").html(text);
        	$("#accept-button").text(data.accStatus);
		});
}

function loadSalerInf(taskID) {

	$.post(
      "loadSalerInf.do",
      "taskID="+taskID,
      function(data){
        $("#salerName").text(data.salerName);
        $("#thisWeek").text(data.thisWeek);
        $("#thisMonth").text(data.thisMonth);
        $("#thisYear").text(data.thisYear);
      }  
    );
}

</script>

</head>
<body>
<div id="wrapper">  
  <!-- 导航栏 -->
  <%@ include file="../nav/navigation.jsp" %>
  
  <!-- 任务种类面包屑 -->
  <ol id="task-room-breadcrumb" class="breadcrumb mt15">
    <li><a href="../taskRoom/mainRoom.jsp">任务大厅</a></li>
    <li id="taskid-breadcrumb" class="active"></li>
  </ol>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-3 column">
			<div class="list-group">
				 <a href="#" class="list-group-item active">卖家信息</a>
				<div class="list-group-item">
					<h5>雇主昵称 ：<small id="salerName"></small></h5>
					<h5>会员等级 ：<small id="salerLever"></small></h5>
					<h5>资  质 ：<small id="taskBaby"></small></h5>
				</div>
				<a href="#" class="list-group-item active">双方交易记录展示</a>
				<div class="list-group-item">
					<h5>本周 ：<small id="thisWeek"></small>笔</h5>
					<h5>本月 ：<small id="thisMonth"></small>笔</h5>
					<h5>本年 ：<small id="thisYear"></small>笔</h5>
				</div>
				<a href="#" class="list-group-item active">卖家已向买家承诺</a>
				<div class="list-group-item">
					<h5>本周<small id="salerName">0</small>笔</h5>
					<h5>本月<small id="salerLever"></small>笔</h5>
					<h5>本年<small id="taskBaby"></small>笔</h5>
				</div>
			</div>
		</div>
		<div class="col-md-9 column">
			<div class="row clearfix">
				<div class="col-md-2 column">
					<p class="text-left">任务标题</p>
				</div>
				<div class="col-md-7 column">
					<p id="title" class="text-left"></p>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-2 column">
					<p class="text-left">任务概述</p>
				</div>
				<div class="col-md-7 column">
					<p id="brief" class="text-left"></p>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-2 column">
					<p class="text-left">任务类别</p>
				</div>
				<div class="col-md-7 column">
					<p id="type" class="text-left"></p>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-2 column">
					<p class="text-left">任务佣金</p>
				</div>
				<div class="col-md-3 column">
					<p id="commission" class="text-left"></p>
				</div>
				<div class="col-md-4 column">
					<p class="text-left">雇主要求买家垫付金额 ：<small id="advanced"></small></p>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-2 column">
					<p class="text-left">任务奖励</p>
				</div>
				<div class="col-md-7 column">
					<p id="reward" class="text-left"></p>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-9 column">
					<p class="text-left">接受此任务需要暂时冻结您“任务宝” :<small id="buyer_freeze"></small></p>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-2 column">
					<p class="text-left">完成总人数</p>
				</div>
				<div class="col-md-7 column">
					<p id="person_need" class="text-left"></p>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-9 column">
					<p class="text-left">任务预制 ：</p>
				</div>
			</div>
			<div class="row clearfix">
				<table id="subtask-table" class="table table-hover table-condensed">
					<thead>
					<tr>
						<th>选择</th>
						<th>完成时间</th>
						<th>完成者地区要求</th>
						<th>人数要求</th>
						<th>已接受人数</th>
					</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div class="row clearfix">
				<div class="col-md-4 col-md-offset-8"><button id="accept-button" type="button" class="btn btn-block btn-info"></button></div>
			</div>
		</div>
	</div>
</div>

</div>
</body>
</html>