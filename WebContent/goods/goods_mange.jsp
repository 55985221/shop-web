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
		 function zhuandao(){
	         window.location.href="<%=path%>goodsServle?msg=manage&pageindex="+$("#pageIndex").val();
		  }

	</script>
	<style>
     .show{
         display:block;
		 position: fixed;

	 }
	 img{
		 max-width:500px;
		 max-height: 370px;
	 }
	 .shom{
       display: none;
	 }

	</style>
    <script>
$(function(){
 
//选择框的联动第一个选择框数据改变呈现出第二个选择框
	$("#menu1").change(function(){
 var sevalue=$("#menu1 option:selected").val();
	if(sevalue!=0){
          $.ajax({
			  
			  url:"goodsServle",
			  cache:"false",
			 type:"post",
			 dataType:"json",
			 data:{id:sevalue,msg:"goodscatemenu"},
			 success:function(cates){
				  $("#menu2").remove();
				 $("#menu1").after("<select name=\"smallcateid\" id=\"menu2\"><option value=\"0\">请选择</option></select>")
				$.each(cates,function(v,cate){
					   str="<option value=\""+cate.id+"\">"+cate.cateName+"</option>"
                       $("#menu2").prepend(str);
				})
			 }
			 
		  });
	}
})
//图片的呈现利用鼠标的坐标更改图片位置一开始是选择用标签进行定位但是感觉太麻烦还是鼠标坐标获取方便但是不确定别的浏览器是否能用
$(".a_img").hover(function(e){
x=e.pageX-350;
y=e.pageY-150;
$("#img_sh").removeClass();
$("#img_sh").addClass("show");
$("#img_sh").css("left",x+"px");
$("#img_sh").css("top",y+"px");
$("#img_s").attr("src","<%=path%>goodsServle?msg=goodsimg&goodsimg="+this.id);

},function(e){
$("#img_sh").removeClass();
$("#img_sh").addClass("shom");
});
















});


//判断用户数否删除
function delet(th){
if(confirm("确定删除商品吗？"))
{
	return true;
}else{
	return false;
}
}


<c:if test="${!empty deletmsg}">
alert("${deletmsg }")
</c:if>

	
    </script>
	<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>

	
  </head>
 
  <base href="<%=path%>">
  <body>

				<div class ="div_title">
						<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>商品列表</span></div>
						
				 </div>
				 <form action="goodsServle?msg=manage" method="post">
				 <select  name="bigcateid" id="menu1" >
				 <option value="0" >请选择</option>
				 <c:forEach items="${cate }" var="cat">
			     <c:if test="${cat.parentId==0}">
				 <option value="${cat.id}"   <c:if test="${param.bigcateid==cat.id }">selected="selected"</c:if>      >${cat.cateName }</option>
				 </c:if>
				 </c:forEach>
				 </select>
				 <c:if test="${!empty param.smallcateid }">
				 <select  name="smallcateid" id="menu2" >
				
			
				 <option value="${ param.smallcateid }"  ><c:forEach items="${cate}" var="cats"><c:if test="${ param.smallcateid==cats.id}">${cats.cateName }</c:if></c:forEach></option>
				
				 
				 </select>
				 </c:if>
				 商品名称:<input type="text" name="商品名称"><input type="submit" value="搜索">
				 
				 </form>
				 <table class="main_table">
				 <tr>
				<th><input type="checkbox"></th>  <th>名称</th><th>单位</th><th>大分类</th><th>小分类</th><th>操作</th>
				 
				 </tr>
				 <c:forEach items="${goodsinfo }" var="goods">
				 <tr>
				 <td><input type="checkbox"></td> <td>${goods.goodsName }</td><td>${goods.unit}</td><td>${goods.bigCateName }</td><td>${goods.smallCateName }</td><td><a  id="${goods.id}" class="a_img">查看</a>|<a>修改</a>|<a href="goodsServle?msg=delet&id=${goods.id }" id="deletgoods" onclick="return delet(this)">删除</a></td>
				 </tr>
				 </c:forEach>
				 </table>
				 
				 
			
				 	
		<div id="img_sh"  class="shom">
	    <img  id="img_s" >
		</div>
				
				<div class="div_page" >
					  <div class="div_page_left">    共有 <label>${page.rowCount }</label> 条记录，当前第 <label>${page.pageIndex }</label> 页，共 <label>${page.pageCount }</label> 页	</div>		
					  <div class="div_page_right" > 	 
					  			 <a href="goodsServle?msg=manage&pageindex=1">首页</a>
				  	 			 <c:if test="${ page.pageIndex!=1}">
				  	 			 <a href="goodsServle?msg=manage&pageindex=${page.pageIndex-1 }">上一页</a>
				  	 			 </c:if>
					  			 <c:if test="${page.pageIndex<pageinfo.pageCount }">
				  	 		     <a href="goodsServle?msg=manage&pageindex=${page.pageIndex+1 }">下一页</a>
				  	 		     </c:if>

					  	  <button onclick="zhuandao()">转到</button>
					  	 <input type="text" name="pageIndex" id="pageIndex" value="1" /> 页
					  	
					   </div>
						
				</div>

  </body>
</html>