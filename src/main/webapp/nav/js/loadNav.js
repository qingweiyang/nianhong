$(document).ready(function(){
  $.ajax({  
      type: "post",
      dataType: "text",
      url: "../navigation.html",
      success: function(data){
        $("#wrapper").html(data);
      },
      error: function(data){}
    }); 
});

function logout() {
  $.post(
      "logout.do",
      {},
      function(data){
    );
}