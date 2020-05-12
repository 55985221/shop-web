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
<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>
<body>
<base href="<%=path%>">
 <table class="main_table">
 <tr>
 <th>商品名称</th><th>商品价格</th><th>商品数量</th><th>商品总价格</th>
 </tr>
 <c:forEach items="${ordergoods }" var="mp">
 <tr>
 <td>${mp.value.goodsName }</td><td>${mp.value.goodsNumber }</td><td>${mp.value.goodsNumber }</td><td>${mp.value.goodsAmount }</td>
 </tr>
 
 </c:forEach>
 <td>总价格:${ab }</td><td><a href="shoppingServle?msg=shoppingmange">继续购物</a></td><td><a>确认购买</a></td>
 </table>
</body>
</html>