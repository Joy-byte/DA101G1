<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://aishek.github.io/jquery-animateNumber/javascripts/jquery.animateNumber.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<title></title>

<style>

</style>
</head>
<body>
		<div class="logo" style="padding-right:30px; height:130%; letter-spacing: 2px; font-family: Microsoft JhengHei;  border-right:1px solid #f0f0f0;;">
		    <div>
			<img src="<%=request.getContextPath()%>/back-end/portal/icon.jpeg" id="logo" style="width:100px; height:100px; margin-left:70px; margin-top:30px; margin-bottom:30px;">
			</div>
			<div>
				<ul class="list-group list-group-flush" style="margin-left:30px; width: 200px; text-align: center;">
					<li class="list-group-item">
						<a style="text-decoration:none; color:black" href="<%=request.getContextPath()%>/back-end/member/listAllMember.jsp">
							<img style="width:30px; height:30px" src="<%=request.getContextPath()%>/back-end/portal/image/user.png">
								&nbsp&nbsp&nbsp&nbsp會員
					    </a>
					</li>
					<li class="list-group-item">
						<a style="text-decoration:none; color:black" href="<%=request.getContextPath()%>/back-end/landscape/select_page.jsp">
							<img src="<%=request.getContextPath()%>/back-end/portal/image/moutain.png">
							&nbsp&nbsp&nbsp&nbsp景點</a></li>
					<li class="list-group-item">
						<a style="text-decoration:none; color:black" href="<%=request.getContextPath()%>/back-end/guide/GuideList.jsp">
							<img src="<%=request.getContextPath()%>/back-end/portal/image/backpack.png">
							&nbsp&nbsp&nbsp&nbsp旅伴</a></li>
					<li class="list-group-item">
						<a style="text-decoration:none; color:black" href="<%=request.getContextPath()%>/back-end/product/portal.jsp">
							<img src="<%=request.getContextPath()%>/back-end/portal/image/present.png">
						 	&nbsp&nbsp&nbsp&nbsp商品</a></li>
					<li class="list-group-item">
						<a style="text-decoration:none; color:black" href="<%=request.getContextPath()%>/back-end/order_detail/portal.jsp">
							<img src="<%=request.getContextPath()%>/back-end/portal/image/document.png">
							&nbsp&nbsp&nbsp&nbsp訂單</a></li>
					<li class="list-group-item">
						<a style="text-decoration:none; color:black" href="<%=request.getContextPath()%>/back-end/merchant/portal.jsp">
							<img src="<%=request.getContextPath()%>/back-end/portal/image/merchant.png">
						&nbsp&nbsp&nbsp&nbsp廠商</a></li>
					<li class="list-group-item">
						<a style="text-decoration:none; color:black" href="<%=request.getContextPath()%>/back-end/promotion/portal.jsp">
							<img src="<%=request.getContextPath()%>/back-end/portal/image/promo.png">
						&nbsp&nbsp&nbsp&nbsp廣告</a></li>
					<li class="list-group-item">
						<a style="text-decoration:none; color:black" href="<%=request.getContextPath()%>/back-end/guidereport/GuideReport.jsp">
							<img src="<%=request.getContextPath()%>/back-end/portal/image/report.png">
						&nbsp&nbsp&nbsp&nbsp檢舉</a></li>
				
				</ul>
			</div>
		</div>
	
    


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
