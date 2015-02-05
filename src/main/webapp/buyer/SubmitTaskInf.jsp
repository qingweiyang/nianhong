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
  var taskID = getUrlParam("subTaskID");
  loadSubTaskInf(taskID);

  /*将子任务id作为参数，暂时保存在提交表单中*/
  $("#taskGetID").val(getUrlParam("taskGetID"));
  
  $("#add-file-btn").click(function(){
		$(this).parent().before('<input type="file" onchange="fileChange(this);" class="form-control-static" name="image" size="16">');
	});

});

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


var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 

/*对上传对图片大小与格式进行筛选*/
function fileChange(target,id) { 
	var fileSize = 0; 
	var filetypes =[".jpg",".png"]; 
	var filepath = target.value; 
	var filemaxsize = 1024*2;//2M 
	if(filepath){ 
	var isnext = false; 
	var fileend = filepath.substring(filepath.indexOf(".")); 
	if(filetypes && filetypes.length>0){ 
	for(var i =0; i<filetypes.length;i++){ 
	if(filetypes[i]==fileend){ 
	isnext = true; 
	break; 
	} 
	} 
	} 
	if(!isnext){ 
	alert("不接受此文件类型！"); 
	target.value =""; 
	return false; 
	} 
	}else{ 
	return false; 
	} 
	if (isIE && !target.files) { 
	var filePath = target.value; 
	var fileSystem = new ActiveXObject("Scripting.FileSystemObject"); 
	if(!fileSystem.FileExists(filePath)){ 
	alert("附件不存在，请重新输入！"); 
	return false; 
	} 
	var file = fileSystem.GetFile (filePath); 
	fileSize = file.Size; 
	} else { 
	fileSize = target.files[0].size; 
	} 
	 
	var size = fileSize / 1024; 
	if(size>filemaxsize){ 
	alert("附件大小不能大于"+filemaxsize/1024+"M！"); 
	target.value =""; 
	return false; 
	} 
	if(size<=0){ 
	alert("附件大小不能为0M！"); 
	target.value =""; 
	return false; 
	} 
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
      

      <div class="panel panel-success">
        <div class="panel-heading">
          <h3 class="panel-title">
            提交您的任务完成信息
          </h3>
        </div>
        <div class="panel-body">
          
  <div class="row clearfix">
    <div class="col-md-12 column">
      <form role="form" action="submitTaskInf.do" method="post" enctype="multipart/form-data">
        <div class="form-group">
           <input id="taskGetID" type="hidden" name ="taskGetID">
           <label for="exampleInputEmail1">文字描述：</label>
           <textarea class="form-control" rows=5 id="brief-task" name="description" placeholder="请输入完成任务概述..."></textarea>
        </div>
        <div class="form-group">
           <label for="exampleInputFile">图片详情：</label>
           <div id="picInf">
           	  <input type="file" name="file" onchange="fileChange(this);" />
              <div class="mt10 form-control-static"><a id="add-file-btn" href="javascipt:void()"><span class="glyphicon glyphicon-plus-sign"></span> Add File</a></div>
           </div>
        </div>

      <div class="alert alert-dismissable alert-danger">
        交易成功后，所有的交易图片信息本站3个工作日后默认清除，如有需要请自行保存
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
      </div>

        <div class="form-group">
          <label for="exampleInputEmail1">向雇主提交个人信息：</label>
        </div>
        
        <div class="form-group">
<label class="checkbox-inline">
  <input type="checkbox" name ="inlineCheckbox1" value="option1"> 银行卡
</label>
<label class="checkbox-inline">
  <input type="checkbox" name ="inlineCheckbox2" value="option2"> 财富通
</label>
<label class="checkbox-inline">
  <input type="checkbox" name ="inlineCheckbox3" value="option3"> 支付宝
</label>
<label class="checkbox-inline">
            <input type="checkbox" name="inlineCheckbox4" value="option4"> 其他
          </label>
        </div>

        <div class="form-group">
          
          <textarea class="form-control" rows=3 id="brief-task" name="others" placeholder="请输入其他信息..."></textarea>
        </div>

        <button type="submit" class="btn btn-default">点击提交</button>
      </form>
    </div>
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