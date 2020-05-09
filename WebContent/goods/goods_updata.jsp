<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>    
    <title></title>   
		<link rel="stylesheet" type="text/css" href="css/edittable.css"  ></link>  
		<link rel="stylesheet" type="text/css" href="css/validate.css"  ></link>  
		<script type="text/javascript"  src="js/jquery-1.8.0.js"></script>
  </head>
  <style>
	 img{
		 max-width:500px;
		 max-height: 370px;
	 }
  </style>
  <script>
$(function(){
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
				 $("#menu2").change(function(){
			  if($("#menu2 option:selected").val()!=0){
				  
				$("#select_msg").html("" );
			  }else{
				$("#select_msg").html("请选择" ).css("color","red");
			  }
		  });
				$.each(cates,function(v,cate){
					   str="<option value=\""+cate.id+"\">"+cate.cateName+"</option>"
                       $("#menu2").prepend(str);
				})
			 }
			 
		  });
		 
		  $("#select_msg").html("" );
	}else{
    $("#select_msg").html("请选择" ).css("color","red");
	}
})

function getObjectURL(file) {        var url = null ;
        if (window.createObjectURL!=undefined) { 
			            url = window.createObjectURL(file) ; 
				       } else if (window.URL!=undefined) {  
						     url = window.URL.createObjectURL(file) ;   
							  } else if (window.webkitURL!=undefined) { 
								  url = window.webkitURL.createObjectURL(file) ;}
								          return url ;  }




	$("#ims").change(function(){
		url=getObjectURL(this.files[0]);
		$("#tp").attr("src",url);
	})


	var bds=/^.{1,20}$/
	$("#goodsName").blur(
		function(){
			if(bds.test($(this).val())){

				$("#goodsName_msg").html("");
			}else{
                $("#goodsName_msg").html("请输入正确名称").css("color","red");
			}
		}
	)
	var bdss=/^.{1}$/
	$("#unit").blur(
		function(){
			if(bdss.test($(this).val())){

				$("#unit_msg").html("");
			}else{
                $("#unit_msg").html("请输入正确单位").css("color","red");
			}
		}
	)
	var jg=/^\d{1,}(\.\d{1,})?$/
	$("#price").blur(
		function(){
			if(jg.test($(this).val())){

				$("#price_msg").html("");
			}else{
                $("#price_msg").html("请输入正确价格").css("color","red");
			}
		}
	)
	$("#pd").blur(
		function(){
			if(bds.test($(this).val())){

				$("#pd_msg").html("");
			}else{
                $("#pd_msg").html("请输入正确出版商").css("color","red");
			}
		}
	)
















})
  </script>
  <script>
  <c:if test="${!empty upmsg }">
  alert("${upmsg }")
  </c:if>
  </script>
  
  <% String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/"; %>
  <base href="<%=path%>">
  <body>
     <div class ="div_title">
		 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>商品修改</span></div>
	 </div>
		<form action="goodsServle?msg=goodsup" method="post" enctype="multipart/form-data">
		<input type="hidden" name="goodid" value="${goods.id }">
	 	 <table class="edit_table" >
		 		<tr>
		 			 	<td class="td_info">商品名称:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" name="goodsName"  id="goodsName" value="${goods.goodsName}" name="goodsName" /> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="goodsName_msg"></label>
		 			 	</td> 
		 		</tr>
		 		<tr>
		 			 	<td class="td_info">所属分类:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<select name="bigcateid" id="menu1">
		 			 		<option value="0" >请选择</option>
		 			 		<c:forEach items="${cate }" var="cates">
		 			 		<c:if test="${cates.parentId==0}">
		 			 		<option value="${cates.id }"   <c:if test="${cates.id==goods.bigCateId}">selected="selected"</c:if>     >${cates.cateName }</option>
		 			 		</c:if>
		 			 		</c:forEach>
							  </select>
							  <select id="menu2"  name="smallcateid">
							  
							  <option value="${goods.smallCateId }">
							 <c:forEach items="${cate }" var="cat">
							 <c:if test="${ goods.smallCateId==cat.id}">${cat.cateName }</c:if>
							 
							 </c:forEach>
							  </option>
							  
							  </select>
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="select_msg"></label>
		 			 	</td> 
		 		</tr>
		 		<tr>
		 			 	<td class="td_info">计量单位:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" name="unit"  id="unit"  value="${goods.unit }"  /> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="unit_msg"></label>
		 			 	</td> 
		 		</tr>
		 		<tr>
		 			 	<td class="td_info">商品价格:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" name="price" id="price" value="${goods.price }" /> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="price_msg"></label>
		 			 	</td> 
		 		</tr>
		 		<tr>
		 			 	<td class="td_info">生产厂商:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" name="pd" id="pd" value="${goods.producter }"  /> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="pd_msg"></label>
		 			 	</td> 
		 		</tr>
		 		<tr>
		 			 	<td class="td_info">商品图片:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="file"    name="ims" id="ims"/> 
		 			 		<img  src="goodsServle?msg=goodsimg&goodsimg=${goods.id}" id="tp">
		 			 		
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="adminName_msg"></label>
		 			 	</td> 
		 		</tr>
		 		<tr>
		 			<td class="td_info">备注信息:</td>	
		 			<td><textarea rows="4" cols="27" name="des"  id="des"  >${goods.des }</textarea> </td>	
		 			<td><input type="submit" value="提交"></td>	
		 		</tr>
		</table>
		
		
		</form>
		
  </body>
</html>