<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="./css/main.css">

<script type="text/javascript" src="./jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="./bootstrap/js/bootstrap.js"></script>

<script type="text/javascript" src="./js/date-helper.js"></script>
<script type="text/javascript" src="./js/util.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		  /*解析url，加载任务详情*/
		  // var publisher = getUrlParam("publisher");
		  // var accepter = getUrlParam("accepter");
		  // getDealRecord(publisher, accepter);

		  //监听 缴纳保证金 输入
		  $('#pay-deposit-val').bind('input propertychange', function() {  
		    $("#pay-deposit-form").removeClass("has-error");
		    $("#pay-result-message").hide();
		  });  
		  
		  $("#pay-sure").click(function(){
				$("#modal-container-688942").modal("hide");
			});
		  
		  $("#edit-submit").click(function(){
			  $("#edit-modal").modal("hide");
			});
		});
</script>
</head>
<body>
<div id="wrapper" class="container">
	
  <%@ include file="../nav/navigation.jsp" %>
  
  <div class="row clearfix">
    <div class="col-md-12 column">
    	<img class="img-rounded" src="../res/background.jpg" height="200" width="100%"/>
    </div>
   </div>

  <div class="row clearfix mt15">
    <div class="col-md-12 column">
      <div class="row">
        <div class="col-md-4">
          <div class="thumbnail">
            <img alt="300x200" src="../res/background.jpg" />
            <div class="caption">
              <h3>
                缴纳保证金简介
              </h3>
              <p>
                保证金缴纳成功后展示在您的个人资料与发布／接受任务个人信息区，让双方看到彼此的保障实力，更诚信的交易，从保障实力开始！
              </p>
              <p>
                 <!-- Button trigger modal -->
                 <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#modal-container-688942">
                  缴纳保证金
                 </button>
              </p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="thumbnail">
            <img alt="300x200" src="../res/background.jpg" />
            <div class="caption">
              <h3>
                查询保证金简介
              </h3>
              <p>
                支持保证金的缴纳、解冻、及扣款等记录查询；保证金明细一目了然！<br/>&nbsp;<br/>&nbsp;
              </p>
              <p>
                 <!-- Button trigger modal -->
                 <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#myModal">
                  查询保证金
                 </button>
              </p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="thumbnail">
            <img alt="300x200" src="../res/background.jpg" />
            <div class="caption">
              <h3>
                解冻保证金简介
              </h3>
              <p>
                当您五进行中和保障期内的交易、赔付、维权等，即可以操作申请解冻保证金<br/>&nbsp;<br/>&nbsp;
              </p>
              <p>
                 <!-- Button trigger modal -->
                 <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#edit-modal">
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


<!-- Modal -->
<div class="modal fade" id="modal-container-688942" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">缴纳保证金</h4>
      </div>
      <div class="modal-body">

<form class="form-horizontal">
  <div id="pay-deposit-form" class="form-group">
    <label for="inputEmail3" class="col-md-offset-2  col-sm-2 control-label">缴纳金额</label>
    <div class="col-sm-4">
      <input id="pay-deposit-val" type="text" class="form-control" placeholder="积分数额...">
    </div>
    <label class="col-sm-2 control-label">积分</label>
  </div>
  <div id="pay-result-message" class="alert alert-dismissable alert-danger" style="display:none">
  </div>
</form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="pay-sure" type="button" class="btn btn-primary">确认</button>
      </div>
    </div>
  </div>
</div>
    
</body>
</html>
