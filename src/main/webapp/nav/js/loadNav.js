$(document).ready(function(){
  $.ajax({  
      type: "post",
      dataType: "text",
      url: "../navigation.html",
      success: function(data){
        $("#nav").html(data);
      },
      error: function(data){}
    }); 

});
