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
             $(this).css("background-color","red");
		  },function(){
			$(this).css("background-color","node");
		  });
	  });
      
	  function zhuandao(){
         window.location.href="<%=path%>adminServle?msg=manage&pageindex="+$("#pageIndex").val();
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
						<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>管理人员基本信息列表</span></div>
						<div class="div_titleoper">
							<input type="checkbox" id="top_ch_checkall"/> 全选 <a href="admin_add.jsp"> <img src="images/add.gif"/>添加 </a> <a href="javascript:void(0)"><img src="images/del.gif" id="delet">删除</a> </div>
				 </div>
				 
				 
					<form action="adminServle" id="deletform" >
					 <input type="hidden" name="msg" value="delets" >
					 <input type="hidden" name="pageindex" value="${pageinfo.pageIndex }" >
				 <table class="main_table" >
				       <tr>
				 				<th><input type="checkbox" id="ch_checkall" /></th>	<th>账号</th> 	<th>状态</th>	<th>备注</th>  <th>最后更新日期</th> 	<th>操作</th>
				 		</tr>
				 	
				   		
				 		<c:forEach items="${admin }" var="ad">
				 		<tr>
				 				<td>
				 					<input type="checkbox" name="ck_id" value="${ ad.id}" /> 
				 				</td>
				 				<td>${ad.adminName}</td>	
				 				<td>
				 			
				 				<c:if test="${ad.state==2}">
				 			            已锁定
				 				</c:if>
				 				</td>	
				 				<td> <a class="a" title="${ad.note}">${ad.note}</a> </td>	
				 				
				 				<td></td>	
				 				<td>
				 				<c:if test="${ad.state==2 }">
				 			      	
					 					<a href="adminServle?msg=js&pageindex=${pageinfo.pageIndex }&id=${ad.id}">解锁</a> |    	
					 			       <a href="adminServle?msg=delet&pageindex=${pageinfo.pageIndex }&id=${ad.id}" 
					 			          onclick="return delet(${ad.id},${pageinfo.pageIndex })">删除</a> 
				 				</c:if>
				 				
					 					<c:if test="${ad.state==1 }">
				 			      	
					 					<a href="adminServle?msg=sd&pageindex=${pageinfo.pageIndex }&id=${ad.id}">锁定</a>  |  
					 					<a href="javascript:void(0)">修改</a> |  	
					 				
					 			        <a href="adminServle?msg=delet&pageindex=${pageinfo.pageIndex }&id=${ad.id}" 
					 			          onclick="return delet(${ad.id},${pageinfo.pageIndex })">删除</a> 
				 				</c:if>

				 				</td>
				 		</tr>
				 		
				 		</c:forEach>
				   		 
				 	
				</table>
				</form>
				
				
				<div class="div_page" >
					  <div class="div_page_left">    共有 <label>${page.rowCount }</label> 条记录，当前第 <label>${page.pageIndex }</label> 页，共 <label>${page.pageCount }</label> 页	</div>		
					  <div class="div_page_right" > 	 
					  			 <a href="adminServle?msg=manage&pageindex=1">首页</a>
				  	 			 <c:if test="${ page.pageIndex!=1}">
				  	 			 <a href="adminServle?msg=manage&pageindex=${page.pageIndex-1 }">上一页</a>
				  	 			 </c:if>
					  			 <c:if test="${page.pageIndex<page.pageCount }">
				  	 		     <a href="adminServle?msg=manage&pageindex=${page.pageIndex+1 }">下一页</a>
				  	 		     </c:if>

					  	  <button onclick="zhuandao()">转到</button>
					  	 <input type="text" name="pageIndex" id="pageIndex" value="1" /> 页
					  	
					   </div>
						
				</div>

  </body>
</html>