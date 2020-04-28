<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>    
    <title></title>   
		<link rel="stylesheet" type="text/css" href="../css/edittable.css"  ></link>  
		<link rel="stylesheet" type="text/css" href="../css/validate.css"  ></link>  
		<script type="text/javascript"  src="../js/jquery-1.8.0.js"></script>
     <style>
		
	 </style>
	 <script>		
	 function but (){ 
				 $("#adminaddform").submit();
	 }
	
			$(function(){
				 $("input[type=text],textarea").focus(function(){
					  $(this).addClass("input_focus");
					}).blur(function(){
							$(this).removeClass("input_focus");
					});

				$(".form_btn").hover(function(){
						$(this).css("color","red").css("background","#6FB2DB");
					},
					
					function(){
						$(this).css("color","#295568").css("background","#BAD9E3");
					});	
                $("#adminName").blur(function(){
					zz=/^(\w){4,15}$/;
					if(zz.test($(this).val())==false){
					   $("#adminName_msg").html("账号格式不正确").css("color","red");
					   $("#but").unbind("click");
					}else{
						$.ajax({
		   url:"adminServle",
		   data:{id:$("#adminName").val(),password:$("#password").val(),msg:"adminadd"},
		   type:"post",
           cache:false,
		   success:function(data){
              if(data=='1'){
				$("#adminName_msg").html("账号被占用").css("color","red");
					   $("#but").unbind("click");
			  			}
			 else{
				$("#adminName_msg").html(" ");
                        $("#but").click(but);
		   }		   
	   }
	
	}
	  )
					}
				});
	
						

			
				$("#password").blur(function(){
					zz=/^(\w){6,20}$/;
					if(zz.test($(this).val())==false){
					   $("#password_msg").html("密码格式不正确").css("color","red");
					   $("#but").unbind("click");
					}else{
						$("#password_msg").html(" ");
                        $("#but").click(but);
					}

				});
               $("#pwdconfirm").blur(function(){
                if($(this).val()==$("#password").val()){
					$("#pwdconfirm_msg").html(" ");
					$("#but").click(but);
				}else{
					$("#pwdconfirm_msg").html("密码与之前不符").css("color","red");
					   $("#but").unbind("click");
				}
			   });









			});		
	</script>

	
  </head>
  <% String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/"; %>
  <base href="<%=path%>">
  <body>
     <div class ="div_title">
		 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>管理员添加</span></div>
	 </div>
				 
	 <form action="adminServle" id="adminaddform" >
		 <table class="edit_table" >
		 		<tr>
		 			 	<td class="td_info">用户账号:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" name="id" class="txtbox" id="adminName" value="${adminadd.adminName }" name="adminName" /> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="adminName_msg">4-15位；只限数字(0-9)和英文(a-z),不区分大小写</label>
		 			 	</td> 
		 		</tr>
		 			<tr>
		 				<td class="td_info">用户密码:</td>	
		 				<td>
		 					<input type="text" name="password" class="txtbox" value="${adminadd.password }" name="password"  id="password" />
		 				</td> 
		 				<td><label  class="validate_info" id="password_msg" >数字或英文,6-20位</label></td>	
		 		</tr>
		 			<tr>
		 				<td class="td_info">重复密码:</td>	
		 				<td><input type="text" class="txtbox" id="pwdconfirm" name="pwdconfirm"  /> 
		 				</td>
		 				<td><label  class="validate_info"  id="pwdconfirm_msg">请重复输入密码</label></td>	
		 		</tr>
		 		<tr><input type="hidden" name="msg" value="adminaddd">
		 			<td class="td_info">备注信息:</td>	
		 			<td><textarea rows="4" cols="27" name="note" class="txtarea" id="note"></textarea> </td>	
		 			<td><label>${msg }</label></td>	
		 		</tr>
		 		<tr>
		 			<td class="td_info"> </td>	
		 			<td> 
		 			<input class="form_btn" type="button" id="but" value="提交" /> 
		 			<input type="reset"  class="form_btn" value="重置" /> </td>	
		 			<td>
		 				<label id="result_msg" class="result_msg"></label>
		 			</td>	
		 		</tr>
		</table>
     </form>
  </body>
</html>