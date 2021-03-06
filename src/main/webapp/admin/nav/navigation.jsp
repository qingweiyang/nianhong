<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(document).ready(function(){
  loadUsername();
});

/*获取当前登入的用户名*/
function loadUsername() {
  $.post(
    "loadUsername.do",
    {},
    function(data){
    	$("#username").html(data.username+'<b class="caret"></b>');
    });
}

function logout() {
$.post(
	      "logout.do",
	      {},
	      function(data){
	        location.href = "../login/login.jsp";
	      }
	    );
}
</script>

<!-- 导航栏 -->
<nav class="navbar navbar-default" role="navigation">
  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li id="nav-1"><a href="javascript:logout()">退出登入</a></li>
      <li class="dropdown">
        <a id="username" href="#" id="username" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="javascript:void(0)">个人资料</a></li>
          <li><a id="edit" href="javascript:void(0)">关系网</a></li>
          <li class="divider"></li>
          <li><a href="#">注销</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" id="user-info1" class="dropdown-toggle" data-toggle="dropdown">我是雇主<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="../saler/publishTask.jsp">发布任务</a></li>
          <li><a id="edit" href="../saler/task.jsp">我的任务</a></li>
          <li><a id="edit" href="../user/taskbaby.jsp">加入任务宝</a></li>
          <li><a id="edit" href="javascript:void(0)">购买积分</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" id="user-info1" class="dropdown-toggle" data-toggle="dropdown">我是买家<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="../buyer/task.jsp">我的任务</a></li>
          <li><a id="edit" href="../user/taskbaby.jsp">加入任务宝</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" id="user-info1" class="dropdown-toggle" data-toggle="dropdown">积分商城<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a id="profile" href="javascript:void(0)">我的积分</a></li>
          <li><a id="edit" href="javascript:void(0)">积分转赠</a></li>
          <li><a id="edit" href="javascript:void(0)">购买积分</a></li>
        </ul>
      </li>
      <li id="nav-1"><a href="javascript:changePage(1)">推荐会员</a></li>
      <li id="nav-2"><a href="../taskRoom/mainRoom.jsp">任务大厅</a></li>
      <li id="nav-3"><a href="javascript:changePage(3)">站内信</a></li>
      <li id="nav-3"><a href="javascript:changePage(3)">联系客服</a></li>
    </ul>

  </div><!-- /.navbar-collapse -->
</nav>