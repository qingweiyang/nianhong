<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(document).ready(function(){
 	loadWaitedTable();
});

/*买家提交完成任务信息
*    param 子任务id
*/
function submitTaskInf(subTaskID, taskGetID) {
  location.href = "./SubmitTaskInf.jsp?subTaskID="+subTaskID+"&taskGetID="+taskGetID;
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
          var option = "<div><a href='javascript:void(0);' onclick='submitTaskInf("+item.sub_task_id+","+item.taskGetID+");'>提交完成信息</a></div>";

          text += "<tr><td>"+item.id+"</td>"+
                      "<td>"+time+"</td>"+
                      "<td>"+item.brief+"</td>"+
                      "<td>"+getVerifyBuyerButton(item.publisher, item.accepter)+"</td>"+
                      "<td>"+acc_time+"</td>"+
                      "<td>"+option+"</td>"+
                      "</tr>";
        });
        $("#saler-waited-task-table tbody").html(text);
      },
      error : function(){}
    });

}

function getVerifyBuyerButton(publisher, accepter) {
  $(".username-span").text(publisher);
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
  </ul>
</div>
</div>

<table id="saler-waited-task-table" class="table table-bordered table-hover table-condensed mt15">
    <thead>
      <tr class="active">
        <th>任务编号</th>
        <th>发布时间</th>
        <th>任务概述</th>
        <th>发布者</th>
        <th>接受时间</th>
        <th>操作</th>
      </tr>
    </thead>
      <tbody>
       
      </tbody>
</table>