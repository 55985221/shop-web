<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <% String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/"; %>
<!DOCTYPE html>
<html>
  <head>
    <title></title>

	<script type="text/javascript" src="js/jquery-1.8.0.js"></script> 

	<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>
	<script>
	 <c:if test="${!empty tishi}">
	alert("${tishi}");
    </c:if>
	</script>
	
  </head>
<script>
  var str="${rolemenuid}";
 Array.prototype.cn=function(e){
	 for(i=0;i<this.length;i++){
	
		 if(this[i]==e){
			 
        return true;
		 }
		 return false;
	 }
 }

 var menuids=str.split(",");
 
$(function(){

	$("input[type=checkbox]").each(function(){
		
	 if(menuids.cn(this.value)){
		  
		 this.checked=true;
	 }
  
   });
})
</script>
<script>
function inputclic (e){
	
        if(e.id!=0){
        	
			if($("input[value="+e.id+"]").attr("checked")){
				return true;
			}else{
				 alert("不能不选一级菜单");
				 return false ;
			}
		}
}

$(function(){
	$("input[id=0]").click(function(){
	
	if($(this).attr("checked")){
		
		$("input[id="+this.value+"]").each(
			function(){
			this.checked=true;}
		)
	}else{
		
		$("input[id="+this.value+"]").each(
			function(){
				
			this.checked=false;
			}
		)
	}
	})
})

</script>
 
  <base href="<%=path%>">
  <body>

				<div class ="div_title">
						<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>角色权限分配</span></div>
						
				 </div>
				 
				 <form action="roleServle">
				 <table class="main_table" >
                 <input type="hidden" name="msg" value="updata">
                 <input type="hidden" name="id" value="${roled.id }">
				   		
		        <c:forEach items="${menu }" var="men">
		        <tr>
		        <td>
		        <input type="checkbox" name="rolemenu" id="${men.parentId }" value="${men.id }">${men.menuName }
		        </td>
		        <td>
		        <c:forEach items="${men.menuList }" var="mmenu">
		        <input type="checkbox" name="rolemenu" id="${mmenu.parentId }" onclick="return inputclic(this)" value="${mmenu.id }">${mmenu.menuName}<br>
		        
		        </c:forEach>
		        </td>
		        
		        </tr>
		        
		        </c:forEach>
				   		 
				 	
				</table>
				<input type="submit" value="提交修改">
				</form>
  </body>
</html>