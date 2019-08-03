<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="com.itinerary_detail.model.*"%>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>Main</title>
<style type="text/css">
body {
	font-family: Microsoft JhengHei;
}

.top {
	padding-top: 7.5%;
	color: #fff;
}

.info {
	padding-top: 20px;
	letter-spacing: 4px;
}

.content {
	margin-top: 5%;
	height: 900px;
	padding: 80px;
	background-color: #fff;
}

.right {
	margin-left: 160px;
}


.list-group-item {
	color: #666;
	padding-left: 20px;
	letter-spacing: 2px;
}

.title-2 {
	line-height: 45px;
	font-family: monospace;
	font-size: 18px;
	letter-spacing: 2px;
	font-weight: bold;
	color: dimgray;
	border-bottom: 1px solid #aaa;
}

.title {
	font-family: monospace;
	font-size: 23px;
	letter-spacing: 2px;
	font-weight: bold;
	color: #666;
}

.lang {
	padding-top: 20px;
	padding-bottom: 20px;
	letter-spacing: 2px;
}

.outer {
	position: relative;
	z-index: 10;
	background-size: cover;
	background-color: #333;
}

#cover {
	position: fixed;
	width: 100%;
	height: auto;
	z-index: -10;
	filter: brightness(0.5);
}

a {
	text-decoration: none !important;
}

.trip{
	margin-left: 280px;

}

.trip img{
	margin-left: 35px;
	margin-top: 70px;
	width:150px;
	height:230px;
}

#headright button{
	margin-left: 35px;
}

#itinerarypic img{
	width:150px;
	height:230px;
}

</style>
</head>
<body>
	<% MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); %>
	<jsp:useBean id="pictureSvc" scope="page" class="com.picture.model.PictureService" />
	<jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" />
	<jsp:useBean id="itinerarySvc" scope="page" class="com.itinerary.model.ItineraryService" />
	<jsp:useBean id="itinerary_detailSvc" scope="page" class="com.itinerary_detail.model.Itinerary_detailService" />
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light"  >
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
	    <a class="navbar-brand" href="#">Logo</a>
	    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
	      <li class="nav-item active">
	        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/member/login.jsp">首頁 <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/itinerary/Upload.jsp">發佈</a>
	      </li>
	      <li class="nav-item">
		    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/game/gameroom.jsp">誰是臥底</a>
	      </li>
	    </ul>
	    <div class="form-inline my-2 my-lg-0" id="headright">
	    	${memberVO.mem_name}
	      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" >購物車</button>
	      <button class="btn btn-outline-success my-2 my-sm-0" onclick="loginbtns()">登入</button>
	    </div>
	  </div>
	</nav>


	<div class="outer">
		<div id="carouselExampleControls" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="https://i.imgur.com/m0LvHO3.jpg" id="cover"class="d-block w-100" alt="...">
					<span class="trip">
						<img src="images/1.png" >
						<img src="images/2.png">
						<img src="images/3.png">
						<img src="images/4.png">
						<img src="images/5.png">
						<img src="images/6.png">
						<img src="images/7.png">
					</span>
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>


		<div class="row justify-content-center content">
			<div class="col-2">

				<div class="list-group list-group-flush">
					<div class="list-group-item"
						style="font-family: monospace; font-size: 18px;">
						<b>LOCALS LIST</b>
					</div>
					<a href="#" class="list-group-item">發布伴遊行程</a> <a href="#"
						class="list-group-item">我的伴遊行程</a> <a href="GuideMainPage.jsp"
						class="list-group-item">我的伴遊資訊</a> <a href="GuideUpdate.jsp"
						class="list-group-item">修改伴遊資訊</a> <a href="#"
						class="list-group-item">伴遊預約訂單</a> <a class="list-group-item"></a>
				</div>
			</div>
			<div class="col-5 right" id="itinerarypic">
				<ul class="flex">
							<c:forEach var="itineraryVO" items="${itinerarySvc.getLastF()}">	
		                        <li>
		                            <div class="cp-unit cp-unit--l" style="top: 60px; left: 2px;">
		                                <a href="http://kumejima-nuchigusuitrip.com/happy-healthy/11.html">
		                                    <div class="category category--happy">
		                                        <h4>		                                                                    															
												<c:forEach var="itinerary_detailVO" items='${itinerary_detailSvc.getLands(itineraryVO.getItinerary_no())}'>
																	${ landscapeSvc.getOneLas(itinerary_detailVO.getLandscape_no()).landscape_name}																	
												</c:forEach>
		                                        </h4>
		                                    </div>
		                                <figure class="thumb m-circle">		                          
		                                    <span class="photo">
		                                        <span class="arrow">
<!-- 		                                         箭頭 -->
		                                        </span>
		                                     <img src="PictureReader.do?pic_no= ${pictureSvc.getOnePic(itineraryVO.getItinerary_no()).pic_no}" alt="">                                 
		                                    </span>		                                    
		                                </figure>
		                                </a>
		                            </div>
		                        </li>
		                      </c:forEach>
           				 </ul>
			</div>
		</div>



		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
			
			<script>
				function loginbtns(){
					location.href='<%=request.getContextPath() %>/front-end/member/login.jsp';
				}
		</script>
</body>
		

</html>