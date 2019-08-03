<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>


<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>GuideMainPage</title>
    <style type="text/css">
    	body{
    		font-family: Microsoft JhengHei;

    	}

		.top{
			padding-top: 7.5%;
			color:#fff;
		}
		.info{
			padding-top: 20px;
			letter-spacing: 4px;
		}
    	.content{
    		margin-top: 5%;
    		height: 900px;
    		padding:80px;
			background-color: #fff;
    	}
		.right{
			margin-left:160px;
		}
    	#guide_pic{
    		height:300px;
    		width:300px;
    		border-radius: 50%;
    		border-color: #fff;
    		border: 3px solid;
    	}
    	.list-group-item{
    		color:#666;
    		padding-left: 20px;
    		letter-spacing: 2px;
    	}
		.title-2{
			line-height: 45px;
			font-family:monospace;
			font-size:18px;
			letter-spacing: 2px;
			font-weight:bold;
			color:dimgray;
			border-bottom: 1px solid #aaa;
		}
		.title{
			font-family:monospace;
			font-size:23px;
			letter-spacing: 2px;
			font-weight:bold;
			color:#666;
		}
		.lang{
			padding-top: 20px;
			padding-bottom: 20px;
			letter-spacing: 2px;
		}
		.outer{
			position: relative;
			z-index: 10;
			background-size:cover;
			background-color: #333;
		}
		#cover{
			position: fixed;
			width: 100%;
			height:auto;
			z-index: -10;
			filter: brightness(0.5);

		}
		a {
   			text-decoration: none !important;
		}
		
		#box12{
     		position: absolute;
     		left:  80%;      /* 離左邊距離*/
    		top:   -25%;     /* 位置高度 */
     		text-align: center;
     		font-size: 15px;
     		padding: 10px;
     		border: 1px solid 	#000000;  /*邊框顏色 */
     		background-color: white;
    		box-shadow: 1px 1px 5px #333333;
    		width: 400px;      /* 照片大小 */
     		transform: rotate(15deg);  /* 旋轉角度 */
     		border-style:solud;  
	 		padding-bottom:50px;  /* 下內邊距 */
   	    }
   	    
    </style>
</head>
  <body>
<div class="outer">
	<img src="<%=request.getContextPath()%>/front-end/merchant/images/test1.jpg" id="cover">
	<div class="container">
		<div class="row justify-content-center top">
			<div>
			</div>
			<div>
			</div>
		</div>
	</div>
	
		<div class="row justify-content-center content">
			<div class="col-2">
	
				<div class="list-group list-group-flush">
					<div class="list-group-item" style="font-family:monospace;font-size: 18px;"><b>選單</b></div>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantUpdate.jsp" class="list-group-item">廠商資料</a>
					<a href="<%=request.getContextPath()%>/front-end/product/addProduct.jsp" class="list-group-item">新增商品</a>
				</div>
				
			</div>
			<div class="col-5 right"">
				<p class="title">商品資料修改</p>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
				<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
				</ul>
				</c:if>	

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet1" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=productVO.getProduct_no()%></td>
	</tr>
	<tr>
	<td>廠商編號:</td>
		<td><%=productVO.getMerchant_no()%></td>
	</tr>
	<tr>
	<td>商品名稱:</td>
		<td><input type="TEXT" name="product_name" size="45" value="<%=productVO.getProduct_name()%>" /></td>
	</tr>
	<tr>
	<td>商品價格:</td>
		<td><input type="TEXT" name="product_pr" size="45" value="<%=productVO.getProduct_pr()%>" /></td>
	</tr>
	<tr>
	<td>商品種類:</td>
		<td><input type="TEXT" name="product_typ" size="45" value="<%=productVO.getProduct_typ()%>" /></td>
	</tr>
	<tr>
	<td>商品說明:</td>
<%-- 		<td><input type="TEXT" name="product_ps" size="45" value="<%=productVO.getProduct_ps()%>" /></td> --%>
	<td><textarea  name="product_ps" style="width:400px;height:120px;"><%=productVO.getProduct_ps()%></textarea></td>
	</tr>
	<tr>
		<td>商品圖片:</td>
		<td>
		<input type="file" name="product_img" id="product_input" onchange="readURL(this)" targetID="preview_progressbarTW_img" accept="image/gif, image/jpeg, image/png" >
	    </td>
	</tr>

	
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="product_no" value="<%=productVO.getProduct_no()%>">
<input type="hidden" name="merchant_no" value="<%=productVO.getMerchant_no()%>">
<input type="hidden" name="product_status" value="<%=productVO.getProduct_status()%>">
<input type="hidden" name="product_pn" value="<%=productVO.getProduct_pn()%>">
<input type="hidden" name="product_quan" value="1">
<input type="submit" value="送出修改"></FORM>


	<div id="box12">
<%--     <img src="<%=request.getContextPath()%>/MerchantImageShow?merchant_no=${merchantVO.merchant_no}" width="300vm"  height="200vm" style="width:375px; hight=360px;"> --%>
 	<img id="preview_progressbarTW_img" src="<%=request.getContextPath()%>/ProductImageShow?product_no=${productVO.product_no}" width="300vm"  height="200vm" style="width:375px; hight=360px;" />	
	</div>
</div>	

<script>

	function readURL(input){
  		if(input.files && input.files[0]){

    	 var imageTagID = input.getAttribute("targetID");
    	 var reader = new FileReader();
    		reader.onload = function (e) {
       		  var img = document.getElementById(imageTagID);
       		  img.setAttribute("src", e.target.result)
   			}
   			reader.readAsDataURL(input.files[0]);
  		}
    }
</script>

</body>

</html>