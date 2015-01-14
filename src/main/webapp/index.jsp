<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	alert("come");
$("#submit").click(function(){
	alert("ok");
	var qs = $("#form1").serialize();
	$.ajax({
		type : "POST",
		url : "publish.do",
		data : qs, 
        dataType: "json",
		success : function(data) {
			console.log(data.message);
			if(data.message=="Admin")
				window.location.href = "admin.jsp";
			else 
				window.location.href = "main.jsp";
		},
		error : function(err) {
			$("#login-alert").show();
		}
	});
});
});
</script>
</head>
<body>
<h2>Hello World!</h2>

<form id="form1" action="" method="post">
    
    name:<input type="text" name="username" size="10">
    password:<input type="password" name="password" size="10">
    <input id="submit" type="button" value="login">
</form>

</body>
</html>
