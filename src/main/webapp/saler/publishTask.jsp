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

<script type="text/javascript">
$(document).ready(function(){
  loadType();
  //地域信息初始化
  loadProvince();
  loadCity("江苏省");

  $("#add-subtask").click(function(){
    $("#subtask").after($("#subtask").clone(true));
  });
  $(".remove-subtask").click(function(){
    $(this).parent().remove();
  });

  $("#publish-button").click(function(){
    var task = {};
    task.title = $("#title").val();
    task.brief = $("#brief-task").val();
    task.type = $("#type-select option:selected").val();
    task.commission = $("#commission-input").val();
    task.advanced = $("#advanced-input").val();
    task.verify = ($("#verifyRadio1").attr("checked") == "checked")?1:0
    task.buyerFreeze = $("#buyer-freeze-input").val();
    task.salerFreeze = $("#saler-freeze-input").val();
    task.reward = $("#reward-input").val();
    task.personNeed = $("#person-number").val();
    task.detail = $("#detail").val();

    task.subtask = [];
    $(".subtask-item").each(function(index, item){
      alert(($(item).children("input:eq(0)")).val());
      var row = {}
      row.startTime = $(item).children("input:eq(0)").val();
      row.finishTime = $(item).children("input:eq(1)").val();
      row.province = $(item).children("select:eq(2)").val();
      row.city = $(item).children("select:eq(3)").val();
      row.subNum = $(item).children("input:eq(2)").val();
      task.subtask.push(row);
    });
    alert(JSON.stringify(task));

    $.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "publish.do",
      data : JSON.stringify(task), 
      dataType: "json",
      success : function(data) {
        alert("good!!!!!");
      },
      error : function(){}
    });

  });
});

  //监听 省 选择器的变化
  $("#province-select").change(function(){
    loadCity($("#province-select option:selected").val());
  }); 

  //加载省信息
  function loadProvince(){
    $("#province-select").empty();
    $.post(
      "loadProvince.do",
      {},
      function(data){
        $.each(eval(data),function(i,item){
          $("#province-select").append('<option value="'+ item +'">'+ item +'</option>');
        });
      }
      );
  };

  //加载市信息
  function loadCity(province){
    $("#city-select").empty();
    var city = "province=" + province;
    $.post(
      "loadCity.do",
      city,
      function(data){
        $.each(eval(data),function(i,item){
          $("#city-select").append('<option value="'+ item +'">'+ item +'</option>');
        });
      }
      );
  };

function loadType() {
  $.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "loadType.do",
      data : {}, 
      dataType: "json",
      success : function(data) {
        $.each(data,function(i,item){
          $("#type-select").append('<option value="'+ item +'">'+ item +'</option>');
        });
      },
      error : function(){}
    });
};

</script>

</head>
<body>
<div id="wrapper">  
  <!-- 导航栏 -->
  <%@ include file="../nav/navigation.jsp" %>

<form class="form-horizontal" role="form">

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">任务标题</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="title" placeholder="请输入任务标题...">
    </div>
  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">任务概述</label>
    <div class="col-sm-10">
      <textarea class="form-control" rows=5 id="brief-task" name="abstracts" placeholder="请输入您将要发布的任务概述..."></textarea>
    </div>
  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">任务类别</label>
    <div class="col-sm-2">
      <select id="type-select" class="form-control"></select>
    </div>
  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">任务佣金</label>
    <div class="col-sm-2">
      <input id="commission-input" class="form-control" type="number">
    </div>
    <label for="inputPassword3" class="control-label">元</label>
  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">是否要求买家垫付</label>
    <div class="col-sm-2">
      <label class="radio-inline">
        <input type="radio" name="inlineRadioOptions1" id="verifyRadio1" value="option1" checked="true"> 是
      </label>
      <label class="radio-inline">
        <input type="radio" name="inlineRadioOptions1" id="inlineRadio2" value="option2"> 否
      </label>
    </div>
    <div class="col-sm-2">
      <label for="inputPassword3" class="control-label">垫付金额</label>
    </div>
    <div class="col-sm-2">
      <input id="advanced-input" class="form-control" type="number">
    </div>
    <label for="inputPassword3" class="control-label">元</label>
  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">是否需要你的审核</label>
    <div class="col-sm-2">
      <label class="radio-inline">
        <input type="radio" name="inlineRadioOptions2" id="verifyRadio1" value="option1" checked="true"> 是
      </label>
      <label class="radio-inline">
        <input type="radio" name="inlineRadioOptions2" id="inlineRadio2" value="option2"> 否
      </label>
    </div>
    <label class="control-label">（选则“是”后，您未在10分钟内审核该任务将默认允许买家领取）</label> 
  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">是否要求买家冻结任务宝积分</label>
    <div class="col-sm-2">
      <label class="radio-inline">
        <input type="radio" name="inlineRadioOptions3" id="tmo" value="option1" checked="true"> 是
      </label>
      <label class="radio-inline">
        <input type="radio" name="inlineRadioOptions3" id="inlineRadio2" value="option2"> 否
      </label>
    </div>
    <div class="col-sm-2">
      <label for="advanced-input" class="control-label">买家冻结</label>
    </div>
    <div class="col-sm-2">
      <input id="advanced-input" class="form-control" type="number">
    </div>
    <label for="inputPassword3" class="control-label">积分（冻结金额不得高于您的任务金额）</label>
  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">任务奖励</label>
    <div class="col-sm-2">
      <input id="reward-input" class="form-control" type="number">
    </div>
    <label for="inputPassword3" class="control-label">积分</label>
  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">完成任务总人数</label>
    <div class="col-sm-2">
      <input type="number" class="form-control" id="person-number">
    </div>
  </div>

  <div class="form-group">
    
<!-- 子任务 -->
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">任务预制（子任务）</h3>
  </div>
  <div class="panel-body">
    <div id="subtask">
    <div class="subtask-item">
      完成时间:
      <input type="date" class="start-time" name="user_date" />
      到
      <input type="date" class="finish-time" name="user_date" />
      完成地区要求:
      <select id="province-select"></select>
      <select id="city-select"></select>
      人数:
      <input type="number" id="person-number">
      人
      <button type="button" class="remove-subtask">移除</button>
    </div>
    </div>
    <button id="add-subtask" type="button">增加一条子任务</button>
  </div>
</div>

  </div>

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">任务详述</label>
    <div class="col-sm-10">
      <textarea id="detail" class="form-control" rows=7 id="inputAbstract" name="abstracts" placeholder="请输入您将要发布的详细任务..."></textarea>
    </div>
  </div>

 <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> 保存为模版
        </label>
      </div>
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="publish-button" type="submit" class="btn btn-default">确认发布</button>
    </div>
  </div>
</form>


</div>
  

</body>
</html>