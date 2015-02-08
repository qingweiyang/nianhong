<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(document).ready(function(){
 	loadWaitedTable();
});

/*雇主中止交易
*    将该条子任务状态改为 交易失败；从表格中删除该行
*/
function suspendDeal(item, taskGetID) {
  var param = {};
  //子任务ID
  param.taskGetID = taskGetID;
  
  $.post(
      "suspendDeal.do",
      param,
      function(data){
        if(data == true) {
          $(item).parent().parent().parent().remove();          
        } else {
          alert("拒绝失败");
        }
      }  
    );
}

/*雇主确认交易完成
*    将该条子任务状态改为 交易完成；从表格中删除该行
*/
function sureDeal(item, taskGetID) {
  var param = {};
  //子任务ID
  param.taskGetID = taskGetID;
  
  $.post(
      "sureDeal.do",
      param,
      function(data){
        if(data == true) {
          $(item).parent().parent().parent().remove();          
        } else {
          alert("拒绝失败");
        }
      }  
    );
}

/*雇主查看买家提交信息*/
function showMessage(taskGetID) {
  location.href = "./submitTaskInf.jsp?&taskGetID="+taskGetID;
}

function loadWaitedTable() {
  $.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "loadDoingTask.do",
      data : {}, 
      dataType: "json",
      success : function(data) {
        var text = "";
        $.each(data,function(i,item){
          var time = getFormatDateByLong(item.publish_time, "yyyy-MM-dd hh:mm:ss");
          var acc_time = getFormatDateByLong(item.accept_time, "yyyy-MM-dd hh:mm:ss");
          var status_show;
          var option;
          if(item.status == 2) {
            status_show = "等待买家完成提交";
            option = "<div><a href='javascript:void(0);' onclick='suspendDeal(this, "+item.taskGetID+");'>中止交易</a></div>";
            option += "<div><a href='javascript:void(0);' onclick='sureDeal(this, "+item.taskGetID+");'> 确认完成</a></div>";
          } else if(item.status == 3) {
            status_show = "买家已确认提交";
            option = "<div><a href='javascript:void(0);' onclick='showMessage("+item.taskGetID+");'>查看信息</a></div>";
            option += "<div><a href='javascript:void(0);' onclick='sureDeal(this, "+item.taskGetID+");'> 确认完成</a></div>";
          }

          text += "<tr><td>"+item.id+"</td>"+
                      "<td>"+time+"</td>"+
                      "<td>"+item.brief+"</td>"+
                      "<td>"+getVerifyBuyerButton(item.publisher, item.accepter)+"</td>"+
                      "<td>"+acc_time+"</td>"+
                      "<td>"+status_show+"</td>"+
                      "<td>"+option+"</td>"+
                      "</tr>";
        });
        $("#saler-waited-task-table tbody").html(text);
      },
      error : function(){}
    });

};

function getVerifyBuyerButton(publisher, accepter) {
  $(".username-span").text(accepter);
  var hf = "../user/deal.jsp?publisher="+publisher+"&accepter="+accepter;
  $(".deal-href").attr("href", encodeURI(hf));
  return $("#test").html();
}

</script>

<!-- 领取人按钮 -->
<div id="test" style="display:none">
<div class="dropdown">
  <button class="btn btn-default dropdown-toggle" id="testbutton" type="button" data-toggle="dropdown" aria-expanded="true">
    <span class="username-span"></span>
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="presentation"><a class="deal-href" role="menuitem" tabindex="-1" href="#" target="_blank">查看交易记录</a></li>
    <li role="presentation" class="divider"></li>
  </ul>
</div>
</div>

<table id="saler-waited-task-table" class="table table-bordered table-hover table-condensed mt15">
    <thead>
      <tr class="active">
        <th>任务编号</th>
        <th>发布时间</th>
        <th>任务概述</th>
        <th>接受者</th>
        <th>接受时间</th>
        <th>完成状态</th>
        <th>操作</th>
      </tr>
    </thead>
      <tbody>
       
      </tbody>
</table>