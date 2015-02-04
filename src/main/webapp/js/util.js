/*获取当前登入的用户名*/
function loadUsername() {
  var res ="";
  $.post(
    "loadUsername.do",
    {},
    function(data){
    	alert(data.username);
      res = data.username;
    });

  alert(res+" =res");
  return res;
}

/*解析url，获取url中的参数值*/
function getUrlParam(name) {
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
  var r = window.location.search.substr(1).match(reg);  //匹配目标参数
  if (r!=null) return decodeURI(r[2]); return null; //返回参数值
} 

function loadNav() {
    $.ajax({  
      type: "post",
      dataType: "text",
      url: "../nav/navigation.html",
      success: function(data){
        $("#nav").html(data);
      },
      error: function(data){}
    }); 
}