<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="./css/main.css">

<script type="text/javascript" src="./jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="./bootstrap/js/bootstrap.js"></script>

<script type="text/javascript" src="./js/date-helper.js"></script>
<script type="text/javascript" src="./js/util.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		  /*解析url，加载任务详情*/
		  // var publisher = getUrlParam("publisher");
		  // var accepter = getUrlParam("accepter");
		  // getDealRecord(publisher, accepter);

		  //监听 缴纳保证金 输入
		  $('#pay-deposit-val').bind('input propertychange', function() {  
		    $("#pay-deposit-form").removeClass("has-error");
		    $("#pay-result-message").hide();
		  });  
		  
		  $("#pay-sure").click(function(){
				$("#modal-container-688942").modal("hide");
			});
		  
		  $("#edit-submit").click(function(){
			  $("#edit-modal").modal("hide");
			});
		});
</script>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top navbar-layoutit">
		<div class="navbar-header">
			<button data-target="navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
				<span class="glyphicon-bar"></span>
				<span class="glyphicon-bar"></span>
				<span class="glyphicon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">
				<img src="http://ibootstrap-file.b0.upaiyun.com/www.layoutit.com/img/favicon.png">
				ibootstrap
				<span class="label label-default">BETA</span>
			</a>
		</div>
		<div class="collapse navbar-collapse">

			<ul class="nav pull-right">
				<li>

					<div class="btn-group btn-donate pull-right"></div>

					<div class="btn-group" data-toggle="buttons-radio">
						<button role="button" data-toggle="modal" data-target="#feedbackModal" id="feedback" class="btn btn-xs btn-primary active"> <i class="glyphicon-comment glyphicon"></i>
							联系我们
						</button>
					</div>
				</li>
			</ul>
			<ul class="nav" id="menu-layoutit">
				<li>
					<div class="btn-group" data-toggle="buttons-radio">
						<button type="button" id="edit" class="active btn btn-xs btn-primary"> <i class="glyphicon glyphicon-edit "></i>
							编辑
						</button>
						<button type="button" class="btn btn-xs btn-primary" id="devpreview">
							<i class="glyphicon-eye-close glyphicon"></i>
							开发
						</button>
						<button type="button" class="btn btn-xs btn-primary" id="sourcepreview">
							<i class="glyphicon-eye-open glyphicon"></i>
							预览
						</button>
					</div>
					<div class="btn-group">
						<button type="button" class="btn btn-xs btn-primary" id="button-download-modal" data-target="#downloadModal" role="button" data-toggle="modal"> <i class="glyphicon-chevron-down glyphicon"></i>
							下载
						</button>
						<!--  <button class="btn btn-xs btn-primary" id="button-share-modal" href="/share/indexV3" role="button" data-toggle="modal" data-target="#shareModal"> <i class="glyphicon-share glyphicon"></i>
						分享
					</button>
					-->
					<button class="btn btn-xs btn-primary" href="#clear" id="clear">
						<i class="glyphicon-trash glyphicon"></i>
						清空
					</button>
					</div>
					<div class="btn-group">
						<a href="/v2">
						<button type="button" class="btn btn-xs btn-primary">
							前往2.0版本 
							<i class="glyphicon-chevron-right glyphicon"></i>
						</button>
						</a>
					</div>
			</li>
		</ul>
	</div>
	<!--/.navbar-collapse -->
</div>
    
</body>
</html>
