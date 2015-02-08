<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加入任务宝</title>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="../css/main.css">

<script type="text/javascript" src="../jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.js"></script>

<script type="text/javascript" src="../js/date-helper.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript">

$(document).ready(function(){

  //监听 缴纳保证金 输入
  $('#pay-deposit-val').bind('input propertychange', function() {  
    $("#pay-deposit-form").removeClass("has-error");
    $("#pay-result-message").hide();
  });  
  
  //监听 缴纳保证金modal， modal出现前判断是否用户已申请过缴纳，且申请未通过
  $("#pay-deposit-modal").on("show.bs.modal", function (e) {
    var cont = '<div class="alert alert-dismissable alert-danger">';
    $.get(
      "payDepositStatus.do",
      function(data){
        if(data.length <= 0) return ;
        
        $.each(data, function(i, item){
          var time = getFormatDateByLong(item.operate_time, "yyyy-MM-dd hh:mm:ss");
          cont += '<p>您已于 ' + time+' 申请缴纳 '+item.value+',等待审核中...</p>';
        });
        cont += '</div>';
        $("#pay-deposit-modal-body").html(cont);
        $("#pay-deposit-modal").find(".modal-footer > button:eq(1)").remove();
      });
  });

  //监听 查询保证金modal， 
  $("#query-deposit-modal").on("show.bs.modal", function (e) {
    var cont = '<div class="alert alert-dismissable alert-success">';
    $.get(
      "queryDeposit.do",
      function(data){
        cont += "<p>已缴纳金额："+data.deposit+"</p>";
        cont += '</div>';
        $("#query-deposit-modal-body").html(cont);
      });
  });

  //监听 解冻保证金modal， modal出现前判断是否用户已申请过解冻，且申请未通过
  $("#pay-unfreeze-modal").on("show.bs.modal", function (e) {
    var cont = '<div class="alert alert-dismissable alert-danger">';
    $.get(
      "unfreezeDepositStatus.do",
      function(data){
        if(data.length <= 0) return ;
        
        $.each(data, function(i, item){
          var time = getFormatDateByLong(item.operate_time, "yyyy-MM-dd hh:mm:ss");
          cont += '<p>您已于 ' + time+' 申请解冻保证金，理由：'+item.remark+',等待审核中...</p>';
        });
        cont += '</div>';
        $("#pay-unfreeze-modal-body").html(cont);
        $("#pay-unfreeze-modal").find(".modal-footer > button:eq(1)").remove();
      });
  });

});


/*用户缴纳保证金*/
function payDeposit() {
  //获取输入值
  var pay = $("#pay-deposit-val").val();
  if(isNaN(pay) || pay < 0) {
    //输入不合法，要求为大于0的整数
    $("#pay-deposit-form").addClass("has-error");
    return;
  }

  $.post(
    "payDeposit.do",
    "val="+pay,
    function(data){
      if(data.status == false) {
        $("#pay-deposit-form").addClass("has-error");
    	  $("#pay-result-message").show();
    	  $("#pay-result-message").html('<h4>输入错误</h4>');
        $.each(data.content, function(i, item){
          $("#pay-result-message").append("<p>"+item+"</p>");
        });
      } else {
        //缴纳成功
        $("#pay-deposit-modal").modal("hide");
      }
    });	
}

/*用户解冻保证金*/
function unfreezeDeposit() {
  //获取输入值
  var reason = $("#pay-unfreeze-val").val();

  $.post(
    "unfreezeDeposit.do",
    "reason="+reason,
    function(data){
      $("#pay-unfreeze-modal").modal("hide");
    }); 
}

</script>

</head>
<body>

<div id="wrapper" class="container">
	
  <%@ include file="../nav/navigation.jsp" %>
  
  <div class="row clearfix">
    <div class="col-md-12 column">
    	<img class="img-rounded" src="../res/image/example.jpg" height="200" width="100%"/>
    </div>
   </div>

  <div class="row clearfix mt15">
    <div class="col-md-12 column">
      <div class="row">
        <div class="col-md-4">
          <div class="thumbnail">
            <img alt="300x200" src="../res/image/example.jpg" />
            <div class="caption">
              <h3>
                缴纳保证金简介
              </h3>
              <p>
                保证金缴纳成功后展示在您的个人资料与发布／接受任务个人信息区，让双方看到彼此的保障实力，更诚信的交易，从保障实力开始！
              </p>
              <p>
                 <!-- Button trigger modal -->
                 <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#pay-deposit-modal">
                  缴纳保证金
                 </button>
              </p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="thumbnail">
            <img alt="300x200" src="../res/image/example.jpg" />
            <div class="caption">
              <h3>
                查询保证金简介
              </h3>
              <p>
                支持保证金的缴纳、解冻、及扣款等记录查询；保证金明细一目了然！<br/>&nbsp;<br/>&nbsp;
              </p>
              <p>
                 <!-- Button trigger modal -->
                 <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#query-deposit-modal">
                  查询保证金
                 </button>
              </p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="thumbnail">
            <img alt="300x200" src="../res/image/example.jpg" />
            <div class="caption">
              <h3>
                解冻保证金简介
              </h3>
              <p>
                当您五进行中和保障期内的交易、赔付、维权等，即可以操作申请解冻保证金<br/>&nbsp;<br/>&nbsp;
              </p>
              <p>
                 <!-- Button trigger modal -->
                 <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#pay-unfreeze-modal">
                  解冻保证金
                 </button>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</div>

<!-- pay Modal -->
<div class="modal fade" id="pay-deposit-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">缴纳保证金</h4>
      </div>
      <div id="pay-deposit-modal-body" class="modal-body">

<form class="form-horizontal">
  <div id="pay-deposit-form" class="form-group">
    <label for="inputEmail3" class="col-md-offset-2  col-sm-2 control-label">缴纳金额</label>
    <div class="col-sm-4">
      <input id="pay-deposit-val" type="text" class="form-control" placeholder="缴纳金额..."> 
    </div>
  </div>
  <div id="pay-result-message" class="alert alert-dismissable alert-danger" style="display:none">
  </div>
</form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="payDeposit();">确认</button>
      </div>
    </div>
  </div>
</div>


<!-- query Modal -->
<div class="modal fade" id="query-deposit-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"> 查询保证金</h4>
      </div>
      <div id="query-deposit-modal-body" class="modal-body">

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
      </div>
    </div>
  </div>
</div>


<!-- Modal unfreeze -->
<div class="modal fade" id="pay-unfreeze-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">解冻保证金</h4>
      </div>
      <div id="pay-unfreeze-modal-body" class="modal-body">

<form class="form-horizontal">
  <div id="pay-deposit-form" class="form-group">
    <label for="inputEmail3" class="col-md-offset-2  col-sm-2 control-label">理由：</label>
    <div class="col-sm-6">
      <input id="pay-unfreeze-val" type="text" class="form-control" placeholder="理由...">
    </div>
  </div>
  <div id="pay-result-message" class="alert alert-dismissable alert-danger" style="display:none">
  </div>
</form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="unfreezeDeposit();">确认</button>
      </div>
    </div>
  </div>
</div>


</body>
</html>