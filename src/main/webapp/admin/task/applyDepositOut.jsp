<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<script type="text/javascript">

$(document).ready(function() {
    loadDepositOut();   

    //监听 缴纳保证金 输入
  $('#reduce-deposit-val').bind('input propertychange', function() {  
    $("#reduce-deposit-form").removeClass("has-error");
    $("#reduce-result-message").hide();
  });  
} );

function loadDepositOut() {
  $.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "loadDepositOut.do",
      data : {}, 
      dataType: "json",
      success : function(data) {
        var text = "";
        $.each(data,function(i,item){
          var identity = (item.identity==1)?"买家会员":"雇主会员";
          var option = "<div><a href='javascript:void(0);' onclick='reduceModal(&quot;"+item.username+"&quot;);'>扣减积分</a></div>";
          option += "<div><a href='javascript:void(0);' onclick='agreeUnfreeze("+item.id+", this);' >解冻积分</a></div>";

          text += "<tr><td>"+identity+"</td>"+
                      "<td>"+item.username+"</td>"+
                      "<td>"+item.deposit+"</td>"+
                      "<td>"+item.remark+"</td>"+
                      "<td>"+option+"</td>"+
                      "</tr>";
        });
        $("#apply-deposit-in-table tbody").html(text);
        $('#apply-deposit-in-table').DataTable();
      },
      error : function(){}
    });

}

/*show扣减积分modal*/
function reduceModal(username) {
  $("#reduce-username").text(username);
  $("#reduce-deposit-modal").modal("show");
}

/*允许解冻操作，删除该行并修改数据库*/
function agreeUnfreeze(id, item) {
  var param = "id="+id;
  $.post(
      "agreeUnfreezeDeposit.do",
      param,
      function(data){
        //刷新页面
        location.reload();
      }  
    );
}


/*扣减用户保证金*/
function reduceDeposit() {
  var param = {};
  param.username = $("#reduce-username").text();
  //获取输入值
  var reduce = $("#reduce-deposit-val").val();
  if(isNaN(reduce) || reduce < 0) {
    //输入不合法，要求为大于0的整数
    $("#reduce-deposit-form").addClass("has-error");
    return;
  }
  param.value = reduce;

  $.post(
    "reduceDeposit.do",
    param,
    function(data){
      if(data.status == false) {
        $("#reduce-deposit-form").addClass("has-error");
        $("#reduce-result-message").show();
        $("#reduce-result-message").html('<h4>输入错误</h4>');
        $.each(data.content, function(i, item){
          $("#reduce-result-message").append("<p>"+item+"</p>");
        });
      } else {
        //缴纳成功
        $("#reduce-deposit-modal").modal("hide");
      }
    }); 
}

</script>				


<table id="apply-deposit-in-table" class="display" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th>身份</th>
	    <th>昵称</th>
	    <th>已冻结积分</th>
      <th>申请理由</th>
	    <th>操作</th>
    </tr>
  </thead>

  <tbody>
  </tbody>
</table>

<!-- reduce deposit Modal -->
<div class="modal fade" id="reduce-deposit-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">扣减保证金</h4>
      </div>
      <div id="reduce-deposit-modal-body" class="modal-body">

<form class="form-horizontal">
  <div id="reduce-deposit-form" class="form-group">
    <label for="inputEmail3" class="col-md-offset-2  col-sm-2 control-label">被扣减者</label>
    <div class="col-sm-4">
      <p id="reduce-username" class="form-control-static"></p>
    </div>
  </div>
  <div id="reduce-deposit-form" class="form-group">
    <label for="inputEmail3" class="col-md-offset-2  col-sm-2 control-label">扣减金额</label>
    <div class="col-sm-4">
      <input id="reduce-deposit-val" type="text" class="form-control" placeholder="缴纳金额..."> 
    </div>
  </div>
  <div id="reduce-result-message" class="alert alert-dismissable alert-danger" style="display:none">
  </div>
</form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="reduceDeposit();">确认</button>
      </div>
    </div>
  </div>
</div>