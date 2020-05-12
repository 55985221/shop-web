<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
    *{
        margin-top: 0px;
        margin-left: 0px;
        border: 0px;
        padding: 0px;
    }
.tp{
    max-width: 199px;
    min-height: 230px;
}
.shangpin{
border: darkblue 1px solid;
width: 200px;
height: 300px;
float:left;
margin-top: 10px;
margin-left: 20px;
}
</style>
<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>
<body>
<base href="<%=path%>">
<div class="shangpin">
 <img class="tp" src="./oxdd67.jpg"> 
 <p>商品</p> 
 <a href="#">添加到购物车</a>
</div>
<c:forEach items="${goods }" var="gd">

<div class="shangpin">
 <img class="tp" src="goodsServle?msg=goodsimg&goodsimg=${gd.id}"> 
 <p>${gd.goodsName}</p> 
 <a href="shoppingServle?msg=shopcart&id=${gd.id }">添加到购物车</a>
</div>

</c:forEach>
<div class="div_page" >
					  <div class="div_page_left">    共有 <label>${page.rowCount }</label> 条记录，当前第 <label>${page.pageIndex }</label> 页，共 <label>${page.pageCount }</label> 页	</div>		
					  <div class="div_page_right" > 	 
					  			 <a href="shoppingServle?msg=mange&pageindex=1">首页</a>
				  	 			 <c:if test="${ page.pageIndex!=1}">
				  	 			 <a href="shoppingServle?msg=mange&pageindex=${page.pageIndex-1 }">上一页</a>
				  	 			 </c:if>
					  			 <c:if test="${page.pageIndex<page.pageCount }">
				  	 		     <a href="shoppingServle?msg=mange&pageindex=${page.pageIndex+1 }">下一页</a>
				  	 		     </c:if>

					  	  <button onclick="zhuandao()">转到</button>
					  	 <input type="text" name="pageIndex" id="pageIndex" value="1" /> 页
					  	
					   </div>
						
				</div>
</body>
</html>