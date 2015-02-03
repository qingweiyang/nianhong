<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>

<script type="text/javascript" src="./jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="./js/ajaxfileupload.js"></script>
</head>
<body>

    <p>
        <input type="file" id="file1" name="file1" />
    </p>
    <input type="button" value="上传" />
    <p>
        <img id="img1" alt="XX" src="test.do?name='123'" height="200" width="200"/>
        
    </p>
  
<div class="row">
  <div class="col-xs-6 col-md-3">
    <a href="#" class="thumbnail">
      <img data-src="test.do" alt="...">
    </a>
  </div>
  <div class="col-xs-6 col-md-3">
    <a href="#" class="thumbnail">
      <img data-src="test.do" alt="...">
    </a>
  </div>
</div>
    
</body>
</html>
<script type="text/javascript">

$(document).ready(function(){
	$.get(
		    "test.do",
		    {},
		    function(data){
		    	$("#img1").val(data);
		    });
});

    $(function() {
        $(":button").click(function() {
            if ($("#file1").val().length > 0) {
                ajaxFileUpload();
            } else {
                alert("请选择图片");
            }
        });
    });
    function ajaxFileUpload() {
        $.ajaxFileUpload({
            url : 'tempimg.do', //用于文件上传的服务器端请求地址
            secureuri : false, //一般设置为false
            fileElementId : 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
            type : 'post',
            dataType : 'HTML', //返回值类型 一般设置为json
            success : function(data, status) //服务器成功响应处理函数
            {
                $("#img1").attr("src", data);
                if (typeof (data.error) != 'undefined') {
                    if (data.error != '') {
                        alert(data.error);
                    } else {
                        alert(data.msg);
                    }
                }
            },
            error : function(data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        })
        return false;
    }
</script>