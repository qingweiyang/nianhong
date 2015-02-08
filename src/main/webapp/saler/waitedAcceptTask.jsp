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
      url : "loadWaitedTask.do",
      data : {}, 
      dataType: "json",
      success : function(data) {
        var text = "";
        $.each(data,function(i,item){
          var time = getFormatDateByLong(item.publish_time, "yyyy-MM-dd hh:mm:ss");
          text += "<tr><td>"+item.id+"</td>"+
                      "<td>"+time+"</td>"+
                      "<td>"+item.brief+"</td>"+
                      "<td>"+
                      "<a href='javascript:void(0);' onclick='javascript:removeRow("+item.id+",this);'>  删除</a></td></tr>";
        });
        $("#saler-waited-task-table tbody").html(text);
      },
      error : function(){}
    });
};

</script>


<table id="saler-waited-task-table" class="table table-bordered table-hover table-condensed mt15">
    <thead>
      <tr class="active">
        <th>任务编号</th>
        <th>发布时间</th>
        <th>任务概述</th>
        <th>操作</th>
      </tr>
    </thead>
      <tbody>
      </tbody>
</table>