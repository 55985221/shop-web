<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
         <% String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/"; %>
    
<!DOCTYPE html>
<html>
<base href="<%=path%>">
	<head>
		<link rel="stylesheet" type="text/css" href="css/center.css" ></link>
		</head>
		
		<body>
				<table id="table1" >
						<tr>
							<td id="td1"></td>
							<td id="td2"><iframe frameborder="0" id="leftFrame" src="left.jsp"></iframe></td>
							<td id="td3"></td>
							<td id="td4"><iframe frameborder="0" id="centerFrame" name="centerFrame" src="right.jsp"></iframe></td>
							<td id="td5"></td>		
						</tr>
				</table>		
		</body>
</html>