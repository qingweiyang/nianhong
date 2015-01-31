<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
 	$("#submit").click(function(){
	var param = {};
	param.username = $("#username").val();
	param.password = $("#password").val();

	$.post(
      "login.do",
      param,
      function(data){
      	if("success" == data) {
      		location.href = "../saler/task.jsp";
      	}
      }  
    );

});
});

</script>
</head>
<body>
    
    x姓名:<input id="username" type="text" name="username" size="10">
    password:<input id="password" type="password" name="password" size="10">
    <button id="submit"> sure</button>

</body>
</html>
