<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>年鸿电商交易平台</title>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="../css/main.css">

<script type="text/javascript" src="../jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/date-helper.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript">

$(document).ready(function(){
  /*解析url，加载任务详情*/
  var taskGetID = getUrlParam("taskGetID");

  /*将子任务id作为参数，暂时保存在提交表单中*/
  //$("#subTaskID").val(taskID);

  loadBuyerSubmitInf(taskGetID);


  /*雇主确认买家完成任务*/
  $("#sure-btn").click(function(){
    $.post("sureDeal.do", "taskGetID="+taskGetID ,function(data){
        location.href = "./sureEnd.jsp";
    });

  });
});

/*加载用户提交完成任务确认的信息，参数：taskGetid*/
function loadBuyerSubmitInf(taskGetID) {
	var param = {};
	param.taskGetID = taskGetID;
	$.post("loadBuyerSubmitInf.do", 
			param,
			function(data){
        //填写任务具体信息
        $("#title").text(data.taskModel.title);
        $("#brief").text(data.taskModel.brief);
        $("#type").text(data.taskModel.type);
        $("#commission").text(data.taskModel.commission);
        $("#advanced").text(data.taskModel.advanced);
        $("#reward").text(data.taskModel.reward);
        $("#buyer_freeze").text(data.taskModel.buyer_freeze);
        var time = getFormatDateByLong(data.subTaskModel.start_time, "yy-MM-dd hh:mm");
        time += " ~ "+getFormatDateByLong(data.subTaskModel.finish_time, "yy-MM-dd hh:mm");
        $("#finishTime").text(time);
        var area = data.subTaskModel.province+" "+data.subTaskModel.city;
        $("#area").text(area);
        $("#person_need").text(data.subTaskModel.person_need);
        $("#detail").text(data.taskModel.detail);


        //买家提交的任务完成信息
        $("#buyer-description").text(data.taskGetModel.description);

        //图片信息
        var picContent = "";
        //定义每行展示的图片数量
        var mloop = 4;
        $.each(data.picPaths, function(i, item){
           if(i%mloop == 0) {
              picContent += '<div class="row clearfix mt15">';
            }
            picContent += '<div class="col-md-4 column"><img class="img-thumbnail img-responsive" src="getPic.do?fileName='+item+'" /></div>';
          if(i%mloop == (mloop-1)) {
            typeContent += "</div>";
          }
        });
       $("#picInf").html(picContent);


       //买家提交的个人信息
       var person_inf = "";
       $.each(data.personInf, function(key, value){
    	   person_inf += '<dd>'+key+':'+value+'</dd>';
       });
       $("#person-inf").after(person_inf);
	});
	
}

/*加载子任务的具体信息，参数：子任务id*/
function loadSubTaskInf(subTaskID) {
  var param = {};
  param.subtaskid = subTaskID;
  $.post(
    "loadSubTaskInf.do",
    param,
    function(data){
      $("#title").text(data.taskModel.title);
      $("#brief").text(data.taskModel.brief);
      $("#type").text(data.taskModel.type);
      $("#commission").text(data.taskModel.commission);
      $("#advanced").text(data.taskModel.advanced);
      $("#reward").text(data.taskModel.reward);
      $("#buyer_freeze").text(data.taskModel.buyer_freeze);
      var time = getFormatDateByLong(data.subTaskModel.start_time, "yy-MM-dd hh:mm");
      time += " ~ "+getFormatDateByLong(data.subTaskModel.finish_time, "yy-MM-dd hh:mm");
      $("#finishTime").text(time);
      var area = data.subTaskModel.province+" "+data.subTaskModel.city;
      $("#area").text(area);
      $("#person_need").text(data.subTaskModel.person_need);
      $("#detail").text(data.taskModel.detail);
    });
}


</script>

</head>
<body>

<div id="wrapper" class="container">

  <div class="row clearfix">
    <div class="col-md-12 column">
      <!-- 导航栏 -->
  	  <%@ include file="../nav/navigation.jsp" %>
    </div>
  </div>

  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="panel panel-success">

        <!-- 任务详情信息 -->
        <div class="panel-heading">
          <h3 class="panel-title">
            任务信息详情
          </h3>
        </div>
        <div class="panel-body">

          <div class="row clearfix">
            <div class="col-md-6 column">

          <dl class="dl-horizontal">
            <dt>任务标题</dt>
            <dd id="title"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>任务概述</dt>
            <dd id="brief"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>任务种类</dt>
            <dd id="type"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>任务佣金</dt>
            <dd id="commission"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>要求买家垫付</dt>
            <dd id="advanced"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>任务奖励</dt>
            <dd id="reward"></dd>
          </dl>

            </div>
            <div class="col-md-6 column">

              
          <dl class="dl-horizontal">
            <dt>需暂时冻结您“任务宝”</dt>
            <dd id="buyer_freeze"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>完成时间</dt>
            <dd id="finishTime"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>完成者地区要求</dt>
            <dd id="area"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>完成人数</dt>
            <dd id="person_need"></dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>详细要求</dt>
            <dd id="detail"></dd>
          </dl>

            </div>
          </div>

        </div>
      </div>
      

      <div class="panel panel-success">
        <div class="panel-heading">
          <h3 class="panel-title">
            买家已提交任务完成信息：
          </h3>
        </div>
        <div class="panel-body">
          <div class="col-md-10 col-md-offset-1">
          <dl>
            <dt>文字描述：</dt>
            <dd id="buyer-description"></dd>
          </dl>
          <dl>
            <dt>图片详情：</dt>
            <dd id="picInf"></dd>
          </dl>
          <dl>
            <dt id="person-inf" class="mt15">买家提交的个人信息：</dt>
          </dl>

        </div>
        
        	<div class="col-md-4 col-md-offset-8">
        		<button id="sure-btn" type="button" class="btn btn-block btn-info">确认买家已完成您的任务</button>
        	</div>
        
        </div>
        
        <div class="panel-footer">
        </div>
      </div>

    </div>
  </div>
</div>


</body>
</html>