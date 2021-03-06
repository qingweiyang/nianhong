<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(document).ready(function(){
 	loadWaitedTable();
});

/**
*删除表格的一行，并删除数据库数据
*/
function removeRow(id, item) {
  var param = "id="+id;
  $.post(
      "deleteTask.do",
      param,
      function(data){
        alert("remove");
        $(item).parent().parent().remove();
      }  
    );
};

function modifyRow(item) {
  alert("remove");
  $(item).parent().parent().remove();
  // $("#saler-waited-task-table tr:eq(0)").remove();
};

/**
*同意买家领取任务
*/
function agreeAccept(item) {
  var param = {};
  //获取接受者姓名
  param.accepter = $(item).parent().parent().parent().find(".username-span").html();
  //获取任务id
  param.taskID = $(item).parent().parent().parent().parent().parent().children("td:eq(0)").html();
  $.post(
      "agreeAccepter.do",
      param,
      function(data){
        if(data == true) {
          $(item).parent().parent().parent().parent().parent().remove();          
        } else {
          alert("同意失败");
        }
      }  
    );

};

/*拒绝买家领取任务*/
function refuseAccept(item) {
  var param = {};
  //获取接受者姓名
  param.accepter = $(item).parent().parent().parent().find(".username-span").html();
  //获取任务id
  param.taskID = $(item).parent().parent().parent().parent().parent().children("td:eq(0)").html();
  $.post(
      "refuseAccepter.do",
      param,
      function(data){
        if(data == true) {
          $(item).parent().parent().parent().parent().parent().remove();          
        } else {
          alert("拒绝失败");
        }
      }  
    );

}

function loadWaitedTable() {
  $.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "loadVerifyTask.do",
      data : {}, 
      dataType: "json",
      success : function(data) {
        var text = "";
        $.each(data,function(i,item){
          var time = getFormatDateByLong(item.publish_time, "yyyy-MM-dd hh:mm:ss");
          var acc_time = getFormatDateByLong(item.accept_time, "yyyy-MM-dd hh:mm:ss");
          text += "<tr><td>"+item.id+"</td>"+
                      "<td>"+time+"</td>"+
                      "<td>"+acc_time+"</td>"+
                      "<td>"+item.brief+"</td>"+
                      "<td>"+getVerifyBuyerButton(item.publisher, item.accepter)+"</td>"+
                      "</tr>";
        });
        $("#saler-waited-task-table tbody").html(text);
      },
      error : function(){}
    });

};


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
    <li role="presentation"><a class="deal-href" role="menuitem" tabindex="-1" href="#" target="_blank">交易记录</a></li>
    <li role="presentation" class="divider"></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0);">雇主信誉</a></li>
  </ul>
</div>
</div>

<table id="saler-waited-task-table" class="table table-bordered table-hover table-condensed mt15">
    <thead>
      <tr class="active">
        <th>任务编号</th>
        <th>发布时间</th>
        <th>接收时间</th>
        <th>任务概述</th>
        <th>发布者</th>
      </tr>
    </thead>
      <tbody>
       
      </tbody>
</table>