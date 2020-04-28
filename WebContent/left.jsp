<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
			<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
			<script type="text/javascript" src="js/jquery.easing.js"></script>
			<script type="text/javascript" src="js/jquery.accordion.js"></script>
			
			
		
<script type="text/javascript">
	$(function(){
		$('#navigation').accordion({
			active:1,   /* 第三个被激活 */
			header: '.head',
			/*navigation1: false,  */
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'   /*slide,bounceslide ,bounceslide,easeslide'*/
		});
	});
</script>
			<link rel="stylesheet" type="text/css" href="css/menu.css">

		</head>
		<body>
		

			 <ul id="navigation">
			 
		
		   <c:forEach items="${menu }" var="mmenu">
		   <li>
				
					<a class="head">${mmenu.menuName }</a>
					<ul>
						<li>
							<c:forEach items="${mmenu.menuList}" var="mmmenu">
							<a href="${mmmenu.url }" target="${mmmenu.target }"><img src="images/${mmmenu.icon }" /><label>${mmmenu.menuName }</label></a>
							</c:forEach>
						</li>
					</ul>
				</li>
		   </c:forEach>
		    </ul>

		</body>
</html>