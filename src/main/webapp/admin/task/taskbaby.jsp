<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../nav/resource.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../res/plug/media/css/jquery.dataTables.css">

<script src="../../res/plug/media/js/jquery.dataTables.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	changeTab(0);
} );

function changeTab(index) {
  var page_map = new Array(
                 "applyDepositIn.jsp",
                 "applyDepositOut.jsp"); 

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

/*扣减用户保证金*/
function reduceDeposit() {
  //获取输入值
  var pay = $("#reduce-deposit-val").val();
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

</script>
</head>

<body>

<ul id="saler-task-tab" class="nav nav-tabs" role="tablist">
  <li role="presentation"><a href="javascript:changeTab(0)">任务宝准入管理</a></li>
  <li role="presentation"><a href="javascript:changeTab(1)">任务宝冻结管理</a></li>
</ul>

<div id="saler-task-container"></div>

</body>
</html>