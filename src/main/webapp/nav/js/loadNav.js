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

function loadNav() {
    $.ajax({  
      type: "post",
      dataType: "text",
      url: "../navigation.html",
      success: function(data){
        $("#wrapper").html(data);
      },
      error: function(data){}
    }); 
}