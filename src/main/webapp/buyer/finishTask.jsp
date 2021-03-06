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

function loadWaitedTable() {
  $.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "loadFinishTask.do",
      data : {}, 
      dataType: "json",
      success : function(data) {
        var text = "";
        $.each(data,function(i,item){
          var option = "<div><a href='javascript:void(0);' onclick='goodReputation(this, "+item.taskGetID+");'>好评</a></div>";
          option += "<div><a href='javascript:void(0);' onclick='sureDeal(this, "+item.taskGetID+");'>差评</a></div>";
          option += "<div><a href='javascript:void(0);' onclick='sureDeal(this, "+item.taskGetID+");'>投诉</a></div>";

          var time = getFormatDateByLong(item.publish_time, "yyyy-MM-dd hh:mm:ss");
          var finish_time = getFormatDateByLong(item.finish_time, "yyyy-MM-dd hh:mm:ss");
          text += "<tr><td>"+item.id+"</td>"+
                      "<td>"+time+"</td>"+
                      "<td>"+item.brief+"</td>"+
                      "<td>"+getVerifyBuyerButton(item.publisher, item.accepter)+"</td>"+
                      "<td>"+finish_time+"</td>"+
                      "<td>"+option+"</td>"+
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
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">好评</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">差评</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">投诉</a></li>
  </ul>
</div>
</div>

<table id="saler-waited-task-table" class="table table-bordered table-hover table-condensed mt15">
    <thead>
      <tr class="active">
        <th>任务编号</th>
        <th>发布时间</th>
        <th>任务概述</th>
        <th>发布者者</th>
        <th>完成时间</th>
        <th>操作</th>
      </tr>
    </thead>
      <tbody>
       
      </tbody>
</table>