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