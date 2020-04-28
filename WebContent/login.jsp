<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
 <head>   
    <title></title>
		<link rel="stylesheet" type="text/css" href="css/login.css" ></link>
  </head>
  <script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  <script type="text/javascript" src="js/jquery.easing.js"></script>
  <script type="text/javascript" src="js/jquery.accordion.js"></script>
  <script>
	  $(function(){
      $("#btn_img").click(function(){
       $.ajax({
		   url:"adminServle",
		   data:{id:$("#adminName").val(),password:$("#password").val(),msg:"login"},
		   type:"post",
           cache:false,
		   success:function(data){
              if(data=='0'){
				window.location.href="admin.jsp"
			  			}
			 else{
				 if(data=='1'){
			    $("#ts").html("账号或密码错误");
			   };
		   }		   
	   }
	
	}
	  )
	   

	  })})
  </script>
  <body>
 	     <div id="div_center">
 				<div id="div_inputbox">
					 <form>
 					<input type="text" id="adminName"  />
					 <input type="password" id="password" />
					 </form>
					 <span id="ts"></span>
 				</div>
				 <input id="btn_img" type="image" src="images/bg_login_btn.jpg" />
 	 	 </div>
  </body>
</html>