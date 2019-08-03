<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.picture.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="com.itinerary_track.model.*"%>
<%@ page import="com.friends.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />

<%
	MemberService memSvc2 = new MemberService();
	String paramMem_no = request.getParameter("mem_no");
	MemberVO profileVO = new MemberVO();
	profileVO = memSvc2.getOneMember(paramMem_no);
	
	PictureService ps = new PictureService();
	LandscapeService ls = new LandscapeService();
	List<LandscapeVO> lanlist = new ArrayList();

	List<PictureVO> piclist = ps.getMemberPic("MB00006");

	pageContext.setAttribute("piclist", piclist);

	for (PictureVO piclist2 : piclist) {
		LandscapeVO landscapeVO = ls.getOneLas(piclist2.getLandscape_no());
		lanlist.add(landscapeVO);
	}
	pageContext.setAttribute("Landlist", lanlist);
%>


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

<title>Profile</title>
<style type="text/css">
body {
	font-family: Microsoft JhengHei;
}

.top {
	padding-top: 8%;
	color: #fff
}

.info {
	padding-top: 20px;
	letter-spacing: 4px;
}

.content {
	justify-content: colum;
	height: 900px;
	padding: 50px;
	background-color: #c4e1e1;
	margin-top: 1%;
}

.innercontent {
	display: flex;
}

.left {
	position: absolute;
	border-radius: 2%;
	text-align: center;
	top: -135%;
	left: 13%;
	z-index: 1;
	justfy-content: colum;
	margin: 3px;
	padding-left: 0px;
}

.right {
	position: absolute;
	border-radius: 2%;
	text-align: center;
	top: -135%;
	left: 38%;
	z-index: 1;
	justfy-content: colum;
	margin: 3px;
	padding-right: 15px;

}

#guide_pic {
	position: relative;
	height: 190px;
	width: 190px;
	border-radius: 50%;
	border-color: #fff;
	border: 3px solid;
	z-index: 100;
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

.outer {
	z-index: 10;
	position: relative;
	background-size: cover;
	background-color: #fff;
}

#cover {
	position: fixed;
	width: 100%;
	height: auto;
	filter: brightness(0.8);
	z-index: -10;
}

#friendimg {
	opacity: 1;
	margin-top: 10px;
	width: 80px;
	height: 80px;
}

#brightness:hover {
	opacity: 1;
}

.btngroup {
	display: flex;
	height: 80px;
	justify-content: space-around;
	top: -15%;
	z-index: 1;
}

#empty {
	opacity: 0.5;
	background-color: #fff;
	z-index: 1;
}

#brightness {
	opacity: 0.6;
	background-color: #fff;
	transition: opacity 1s;
	text-align: center;
	line-height: 80px;
}

.photowall {
	margin-top: 10px;
	background-color: rgba(255,255,255,0.5);
	height: 700px;
}

.profile {
	margin-top: 10px;
	margin-right: -20px;
	background-color: rgba(255,255,255,0.5);
	height: 700px;
}

.btnimg {
	height: 35px;
	width: 35px;
}

#map {
	height: 400px; /* The height is 400 pixels */
	width: 100%; /* The width is the width of the web page */
}

.map {
	font-family: Microsoft JhengHei;
	font-size: 16px;
	display: flex;
	flex-direction: column;
}

#pic {
	margin-top: 10px;
	width: 100px;
	height: 100px;
}


a {
	text-decoration: none !important;
}

</style>
</head>
<body>

	<div class="outer">
		<img src="images/sumiyoshi.jpg" id="cover">
		<div class="container">
			<div class="row justify-content-center top">
				<div class="">
					<img id=guide_pic src="MemReader.do?mem_no=${param.mem_no}">					
				</div>
			</div>
		</div>

		<div class="row justify-content-center content">

			<div class="btngroup col-7 container">

				<div class="col-2" id="brightness">
					<img src="images/car.png" class="btnimg">
				</div>
				<div class="col-2" id="brightness">
					<img src="images/heart.png" class="btnimg">
				</div>


				<div class="col-3" id="empty"></div>
				<div class="col-2" id="brightness">
					<img src="images/add.png" class="btnimg">
				</div>
				<div class="col-2" id="brightness">
					<img src="images/chat.png" class="btnimg">
				</div>

			</div>

			<div class="innercontent container justify-content-center col-9">
				<div class="col-3 left"
					style="font-family: monospace; font-size: 14px;">

					<div class="profile">
						<br> <span style="font-family: monospace; font-size: 16px; color: #808080">
							Profile </span>
						<hr style="width: 80%">
						<div class="profiledetail"
							style="text-align: left; margin-left: 30px; color: #808080;">
<%-- 							${memSvc.getOneMember('${param.mem_no}').mem_name} --%>
							<%=profileVO.getMem_name() %><br> <%=profileVO.getMem_birthday() %><br> ふたご座 <br> 新潟県 <br> <br>
						</div>
						<span class="fontcolor" style="font-family: monospace; font-size: 16px; color: #808080">
							Friend </span>
						<hr style="width: 80%">
						<div class="friendphoto">
							
						</div>
					</div>
				</div>

				<div class="col-6 right">

					<div class="col-12 photowall">
						<br> <span style="font-family: monospace; font-size: 16px; color: #808080">
							Photo</span>
						<hr style="width: 80%">
						<div id="map"></div>

						<div>
							<br> <span style="font-family: monospace; font-size: 16px; color: #808080">
								trip</span>
							<hr style="width: 80%">
						</div>
						<div>
							<br> <span style="font-family: monospace; font-size: 16px; color: #808080;">
								favorite</span>
							<hr style="width: 80%">
						</div>
					</div>

				</div>

			</div>
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
	
	
// 		function initMap() {
			
// 			var marker = [];
// 			var infoWindow = [];
// 			var markerData = [
// 				<c:forEach var="landscapeVO" items="${Landlist}">
// 			    {
// 			    　　name: '${landscapeVO.landscape_name}',
// 			     lat: ${landscapeVO.landscape_lat},
// 			     lng: ${landscapeVO.landscape_lng} 
// 			 	},
// 			</c:forEach>	     	       	
// 			];	
			
// 			var picData = [
// 				<c:forEach var="pictureVO" items="${piclist}">	
// 			    '${pictureVO.pic_no}',
// 			</c:forEach>	
// 			];	
			
			
			
// 			var mapLatLng = new google.maps.LatLng({lat: markerData[1]['lat'], lng: markerData[1]['lng']}); // 緯度経度のデータ作成
// 			   map = new google.maps.Map(document.getElementById('map'), { // #sampleに地図を埋め込む
// 			     center: mapLatLng, // 地図の中心を指定
// 			      zoom: 15 // 地図のズームを指定
// 			   });
			
// 			 for (var i = 0; i < markerData.length; i++) {
// 				    markerLatLng = new google.maps.LatLng({lat: markerData[i]['lat'], lng: markerData[i]['lng']}); // 緯度経度のデータ作成
// 			        marker[i] = new google.maps.Marker({ 
// 			         position: markerLatLng,
// 			            map: map 
// 			       });	
				    
// 			  	   infoWindow[i] = new google.maps.InfoWindow({
// 			            content: '<div class="map">'+ markerData[i]['name']+'<img src="PhotoReader4.do?pic_no='+picData[i]+'" id="pic">'+'</div>'
// 			 	   });   
				    
// 				   markerEvent(i);
// 			 }		



// 				map.addListener('center_changed', function() {
// 					// 3 seconds after the center of the map has changed, pan back to the
// 					// marker.
// 					window.setTimeout(function() {
// 						map.panTo(marker[i].getPosition());
// 					}, 3000);
// 				});
				
// 				function markerEvent(i) {
// 				marker[i].addListener('click', function() {			
// 					infoWindow[i].open(map, marker[i]);
// 				});
// 			   }
// 			}
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpwWDqBnEqZEDW59nB-_DiyYEeLRveps0&callback"
		type="text/javascript"></script>
</body>
</html>