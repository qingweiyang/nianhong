<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<script type="text/javascript">

$(document).ready(function() {
    loadDepositIn();   
} );

function loadDepositIn() {
  $.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "loadDepositIn.do",
      data : {}, 
      dataType: "json",
      success : function(data) {
        var text = "";
        $.each(data,function(i,item){
          var identity = (item.identity==1)?"买家会员":"雇主会员";
          var show = "<div><a href='javascript:void(0);' onclick='submitTaskInf("+item.id+", this);'>查看</a></div>";
          var option = "<div><a href='javascript:void(0);' onclick='refuseApply("+item.id+", this);'>拒绝申请</a></div>";
          option += "<div><a href='javascript:void(0);' onclick='agreeApply("+item.id+", this);'>允许申请</a></div>";

          text += "<tr><td>"+identity+"</td>"+
                      "<td>"+item.username+"</td>"+
                      "<td>"+show+"</td>"+
                      "<td>"+item.value+"</td>"+
                      "<td>"+option+"</td>"+
                      "</tr>";
        });
        $("#apply-deposit-in-table tbody").html(text);
        $('#apply-deposit-in-table').DataTable();
      },
      error : function(){}
    });

}

/*拒绝申请操作，删除该行并修改数据库*/
function refuseApply(id, item) {
  var param = "id="+id;
  $.post(
      "refusePayDeposit.do",
      param,
      function(data){
        //刷新页面(要重做一下，目的：只刷新表格，而不是整个页面)
        location.reload();
      }  
    );
}

/*允许申请操作，删除该行并修改数据库*/
function agreeApply(id, item) {
  var param = "id="+id;
  $.post(
      "agreePayDeposit.do",
      param,
      function(data){
        //刷新页面
        location.reload();
      }  
    );
}


</script>				


<table id="apply-deposit-in-table" class="display" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th>身份</th>
	    <th>昵称</th>
	    <th>旺旺ID</th>
	    <th>申请额度</th>
	    <th>操作</th>
    </tr>
  </thead>

  <tbody>
  </tbody>
</table>
