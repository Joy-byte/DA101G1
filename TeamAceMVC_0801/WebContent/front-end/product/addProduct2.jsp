<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("NewProductVO");
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
     		left:  70%;      /* 離左邊距離*/
    		top:   5%;     /* 位置高度 */
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
  <nav class="navbar navbar-expand-lg navbar-light bg-light"  >
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		    <a class="navbar-brand" href="#">Logo</a>
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item active">
		        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/home/TeamAce.jsp">首頁 <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/itinerary/itineraryUpload.jsp">發佈</a>
		      </li>
		      <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/game/gameroom.jsp">誰是臥底</a>
		      </li>
		      <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/product/Shop.jsp">商城</a>
		      </li>
		      <div class="btn-group">
				  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background:#fff !important;">
				   在地旅伴 yo
				  </button>
				  <div class="dropdown-menu" id="dropdown-menu1">
				  <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guide/GuideAdd.jsp">成為在地旅伴</a>
				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guidetour/GuideTourList.jsp">伴遊行程</a>
				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/resorder/MemResOrder.jsp">我的預約訂單</a>
<!-- 				    <div class="dropdown-divider"></div> -->
					<c:if test="${guideSvc.getOneGuideByMemNo(memberVO.mem_no)!=null}">
				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guide/GuideMainPage.jsp">在地旅伴管理</a>
				  </c:if>
				  </div>
				</div>

		      <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${memberVO.mem_no}">個人頁面</a>
		      </li>
		    </ul>
		    <div class="form-inline my-2 my-lg-0" id="headright">
		    <c:if test="${memberVO.mem_name !=null}">
            	<a href="<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${memberVO.mem_no}">
            	 ${memberVO.mem_name} </a> ,你好 ! 點數:${memberVO.mem_amo}
        	</c:if>
		      <button class="btn btn-outline-success my-2 my-sm-0" onclick="loginbtns()"><b id="isLogin" name="${ memberVO.mem_name}" >登入</b></button>
		    </div>
		  </div>
	</nav>
	</div>
	<div class="cover"></div>
  
<div class="outer">
<div class="outer">
	<img src="<%=request.getContextPath()%>/MerchantView/image/test1.jpg" id="cover">
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
	</div>
<div class="col-5 right">
<p class="title">申請商品</p>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet1" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>廠商編號:</td>
		<td><%=session.getAttribute("merchant_no")%></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="product_name" size="45"
			 value="<%= (productVO==null)? "香蕉" : productVO.getProduct_name()%>" /></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td><%="上架"%></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="product_pr" size="45"
			 value="<%= (productVO==null)? "100" : productVO.getProduct_pr()%>" /></td>
	</tr>
	<tr>
		<td>商品種類:</td>
		<td><input type="TEXT" name="product_typ" size="45"
			 value="<%= (productVO==null)? "農產品" : productVO.getProduct_typ()%>" /></td>
	</tr>
	<tr>
		<td>廣告促銷狀態:</td>
		<td><%="沒有廣告促銷狀態"%></td>
	</tr>
	<tr>
		<td>商品說明:</td>
<!-- 		<td><input type="TEXT" name="product_ps" size="45" -->
<%-- 			 value="<%= (productVO==null)? "666666" : productVO.getProduct_ps()%>" /></td> --%>
		<td><textarea  name="product_ps" style="width:400px;height:120px;"></textarea></td>
	</tr>
	<tr>
		<td>商品圖片:</td>
		<td>
		<input type="file" name="product_img" onchange="readURL(this)" targetID="preview_progressbarTW_img" accept="image/gif, image/jpeg, image/png">
	    </td>
	</tr>
	
	
	
</table><br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="merchant_no" value="<%=session.getAttribute("merchant_no")%>">
<input type="hidden" name="product_status" value="C1">
<input type="hidden" name="product_pn" value="D0">
<input type="hidden" name="product_quan" value="1">
<input type="submit" value="送出新增"></FORM>
</div>

	<div id="box12">
<%--<img src="<%=request.getContextPath()%>/MerchantImageShow?merchant_no=${merchantVO.merchant_no}" width="300vm"  height="200vm" style="width:375px; hight=360px;"> --%>
 	<img id="preview_progressbarTW_img"  src="<%=request.getContextPath()%>/ProductImageShow?product_no=${productVO.product_no}" width="300vm"  height="200vm" style="width:375px; hight=360px;" />
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



$(document).ready(function() {
	 if($('#isLogin').attr('name')!=""){
		 $('#isLogin').text('登出');
		 $('#isLogin').prev().attr('onclick','logoutbtns()');
	 }else{
		 $('#isLogin').text('登入');
		 $('#isLogin').prev().attr('onclick','loginbtns()');
	 }
});

function loginbtns(){
	location.href='<%=request.getContextPath() %>/front-end/member/login.jsp';
}
</script>






    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
  
</html>