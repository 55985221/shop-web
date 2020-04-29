<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <% String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/"; %>
<!DOCTYPE html>
<html>
  <head>
    <title></title>

	<script type="text/javascript" src="js/jquery-1.8.0.js"></script> 
	<script type="text/javascript">
		$(function(){
			$("#ch_checkall,#top_ch_checkall").click(function(){
				if(this.checked){
					$("input[name=ck_id]").attr("checked","checked");
				}else{
					$("input[name=ck_id]").removeAttr("checked");
				}		
			});
					
			$("table tr").mouseover(function(){
				$(this).css("background","#D3EAEF");
				$(this).siblings().css("background","white");
			});
		});
	</script>

	<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>
    <style type="text/css">
    .a{
    max-width: 100px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    display:block;
    float:left;
    }
    a{text-decoration:none;}
	</style>
	<script>
      function delet(a,b){
           if(confirm("确认删除")){
            
                     return true;
			  
		   }else{
			   return false;
		   }

		   
	  }
	  $(function(){
		  $("tr").hover(function(){
             $(this).css("background-color","#60B9F4");
		  },function(){
			$(this).css("background-color","node");
		  });
	  });
      
	  function zhuandao(){
         window.location.href="<%=path%>roleServle?msg=manage&pageindex="+$("#pageIndex").val();
	  }

$(function(){
	$("#delet").click(function(){
		var list=$("input[type=checkbox]:checked");
		if(list.length!=0){
        $("#deletform").submit();
		}else{
			alert("请先勾选");
		}
	});
});
<c:if test="${!(empty delet)}" >
alert("${delet}");
</c:if>
     

	
	</script>
  </head>
 
  <base href="<%=path%>">
  <body>

				<div class ="div_title">
						<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>角色信息列表</span></div>
						
				 </div>
				 
				 
					<form action="adminServle" id="deletform" >
					 <input type="hidden" name="msg" value="delets" >
					 <input type="hidden" name="pageindex" value="${page.pageIndex }" >
				 <table class="main_table" >
				       <tr>
				 					<th>角色名称</th> 	<th>角色描述</th> 	<th>操作</th>
				 		</tr>
				 	<c:forEach items="${role}" var="roles" >
				 	
				 	<tr>
				 	<td>${roles.roleName }</td>
				 	<td>${roles.des }</td>
				 	<td>删除|修改|角色权限分配</td>
				 	</tr>
				 	</c:forEach>
				   		
				 	
				   		 
				 	
				</table>
				</form>
				
				
				<div class="div_page" >
					  <div class="div_page_left">    共有 <label>${page.rowCount }</label> 条记录，当前第 <label>${page.pageIndex }</label> 页，共 <label>${page.pageCount }</label> 页	</div>		
					  <div class="div_page_right" > 	 
					  			 <a href="roleServle?msg=manage&pageindex=1">首页</a>
				  	 			 <c:if test="${ page.pageIndex!=1}">
				  	 			 <a href="roleServle?msg=manage&pageindex=${page.pageIndex-1 }">上一页</a>
				  	 			 </c:if>
					  			 <c:if test="${page.pageIndex<pageinfo.pageCount }">
				  	 		     <a href="roleServle?msg=manage&pageindex=${page.pageIndex+1 }">下一页</a>
				  	 		     </c:if>

					  	  <button onclick="zhuandao()">转到</button>
					  	 <input type="text" name="pageIndex" id="pageIndex" value="1" /> 页
					  	
					   </div>
						
				</div>

  </body>
</html>