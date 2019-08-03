<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.picture.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="com.itinerary_track.model.*"%>
<%@ page import="com.friends.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.friends.model.*"%>
<%@ page import="com.itineraryStart.model.*"%>
<%@ page import="com.guide.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />
<jsp:useBean id="pictureSvc" scope="page" class="com.picture.model.PictureService" />
<jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" />
<jsp:useBean id="itinerarySvc" scope="page" class="com.itinerary.model.ItineraryService" />
<jsp:useBean id="itinerary_detailSvc" scope="page" class="com.itinerary_detail.model.Itinerary_detailService" />
<jsp:useBean id="itinerary_trackSvc" scope="page" class="com.itinerary_track.model.Itinerary_trackService" />
<jsp:useBean id="jedisFriendPool" scope="page" class="com.friends.model.JedisFriendPool" />
<jsp:useBean id="itineraryStartSvc" scope="page" class="com.itineraryStart.model.ItineraryStartService" />
<jsp:useBean id="guideSvc" scope="page" class="com.guide.model.GuideService" />

<%
	MemberService memSvc2 = new MemberService();
	String paramMem_no = request.getParameter("mem_no");
	
	MemberVO profileVO = new MemberVO();
	profileVO = memSvc2.getOneMember(paramMem_no);

	
	PictureService ps = new PictureService();
	LandscapeService ls = new LandscapeService();
	List<LandscapeVO> lanlist = new ArrayList();

	List<PictureVO> piclist = ps.getMemberPic(paramMem_no);

	pageContext.setAttribute("piclist", piclist);

	for (PictureVO piclist2 : piclist) {
		LandscapeVO landscapeVO = ls.getOneLas(piclist2.getLandscape_no());
		lanlist.add(landscapeVO);
	}
	pageContext.setAttribute("Landlist", lanlist);
	
	
	
%>

<!-- JedisFriendPool jedisFriendPoolSvc = new JedisFriendPool(); -->
<!-- 	List<FriendsVO> friendlist = jedisFriendPoolSvc.getAll(paramMem_no); -->
<!-- 	pageContext.setAttribute("friendlist", friendlist); -->

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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title>Profile</title>
<style type="text/css">
body {
	font-family: Microsoft JhengHei;
}

#headright button{
		margin-left: 35px;
		margin-top: 5px;
	}

#bobo1{
		margin-left: 500px;
		margin-top: 300px;
	}
	
	#bobo2{
		margin-left: 700px;
		margin-top: 100px;
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
	font-size: 16px;
	letter-spacing: 2px;
	font-weight: bold;
	color: #808080;
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

.friendphoto img {
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
	height: 850px;

}

.profile {
	margin-top: 10px;
	margin-right: -20px;
	background-color: rgba(255,255,255,0.5);
	height: 850px;
	
}
.profiledetail{
    font-size:16px;
}

.btnimg {
	height: 35px;
	width: 35px;
}

#map {
	height: 350px; /* The height is 400 pixels */
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
#chatbox{
position:fixed;
width:200px;
height:300px;
background-color:#fff;
opacity: 0.8;
z-index:100;
bottom:0%;
right:5%;
display:none;
}

#mapbtn{

background-color:#fff;
border:none;
outline:none;
}

.itstatus{
width: 50px;
height: 25px;
border: 1px solid;
border-radius: 15%;
font-size: 13px;
line-height: 25px;
text-align:center;
}

#pic1{
width: 400px;
height:400px;
}

img:hover{
	cursor: pointer;
	
}
	
.itinerarySelect:hover{
	cursor: pointer;
	color:green;
}	

.itinerarySelect{
	font-size:25px;
}

#itineraryQuestion{
	font-size:25px;
	text-align:left; 
}

#tripGametable{
	text-align:left; 
}



</style>
<script>
	
	
		function initMap() {
			
			var marker = [];
			var infoWindow = [];
			var markerData = [
				<c:forEach var="landscapeVO" items="${Landlist}">
			    {
			    name: '${landscapeVO.landscape_name}',
			     lat: ${landscapeVO.landscape_lat},
			     lng: ${landscapeVO.landscape_lng} 
			 	},
			</c:forEach>	     	       	
			];	
			
			var picData = [
				<c:forEach var="pictureVO" items="${piclist}">	
			    '${pictureVO.pic_no}',
			</c:forEach>	
			];	
			
			
			
			var mapLatLng = new google.maps.LatLng({lat: markerData[1]['lat'], lng: markerData[1]['lng']}); // 緯度経度のデータ作成
			   map = new google.maps.Map(document.getElementById('map'), { // #sampleに地図を埋め込む
			     center: mapLatLng, // 地図の中心を指定
			      zoom: 15 // 地図のズームを指定
			   });
			
			 for (var i = 0; i < markerData.length; i++) {
				    markerLatLng = new google.maps.LatLng({lat: markerData[i]['lat'], lng: markerData[i]['lng']}); // 緯度経度のデータ作成
			        marker[i] = new google.maps.Marker({ 
			         position: markerLatLng,
			            map: map 
			       });	
				    
			  	   infoWindow[i] = new google.maps.InfoWindow({
			            content: '<div class="map">'+ markerData[i]['name']+'<button id="mapbtn" type="button">'+'<img src="PhotoReader4.do?pic_no='+picData[i]+'" id="pic">'+'</button>'+'</div>'
			            
			 	   });   
				    
				   markerEvent(i);
			 }		

	
			 
// 			 <div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-hidden="true">
// 			  <div class="modal-dialog modal-xl">
// 			    <div class="modal-content">
// 			    </div>
// 			  </div>
// 			</div>
			 
				map.addListener('center_changed', function() {
					// 3 seconds after the center of the map has changed, pan back to the
					// marker.
					window.setTimeout(function() {
						map.panTo(marker[i].getPosition());
					}, 3000);
				});
				
				function markerEvent(i) {
				marker[i].addListener('click', function() {			
					infoWindow[i].open(map, marker[i]);
				});
			   }
				
				
			}
	</script>
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
					<img src="images/car.png" class="btnimg"  onclick="tripGame()">
				</div>
				<div class="col-2" id="brightness">
					<img src="images/heart.png" class="btnimg">
				</div>


				<div class="col-3" id="empty"></div>
				<div class="col-2" id="brightness">
					<img src="images/add.png" class="btnimg">
				</div>

				<div class="col-2" id="brightness">
					<button type="button" Style="outline:none; width:100%; height:100%; background-color:#fff; border:none"  onclick="openchatroom()">			
						<img src="images/chat.png" class="btnimg">			
					</button>
				</div>
			</div>

			<div class="innercontent container justify-content-center col-9">
				<div class="col-3 left"
					style="font-family: monospace; font-size: 14px;">

					<div class="profile">
						<br> <span class="title">
							Profile </span>
						<hr style="width: 80%">
						<div class="profiledetail" style="text-align: left; margin-left: 30px; color: #808080;">
						<br> 
							<span class="title"><%=profileVO.getMem_name() %></span><br><br>
							<i class="fa fa-birthday-cake"></i>&nbsp <%=profileVO.getMem_birthday() %><br>
<!-- 							<i class="fa fa-star"></i>&nbsp ふたご座 <br> -->
<!-- 							<i class="fa fa-home"></i>&nbsp 新潟県 <br> <br> -->
						</div>
						<span class=title>
							Friend </span>
						<hr style="width: 80%">
						<div class="friendphoto">
<!-- 							<img id="friendimg" src="konoeprofile.jpg"> <img -->
<!-- 								id="friendimg" src="konoeprofile.jpg"> <img id="friendimg" -->
<!-- 								src="konoeprofile.jpg">  -->
<%-- 						<c:forEach var="friendsVO" items="${friendlist}"> --%>
<%-- 							 ${friendsVO.friends_no} --%>
<%-- 							 <img src="MemReader.do?mem_no=${friendsVO.friends_no}" onclick="location.href='<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${friendsVO.friends_no}'"> --%>
<%-- 						</c:forEach> --%>
<%-- 				<c:forEach var="paramMemNo" items="${paramList}"> --%>
						<c:forEach var="friendsVO" items="${jedisFriendPool.getAll(param.mem_no)}">
							 <img src="MemReader.do?mem_no=${friendsVO.friends_no}" onclick="location.href='<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${friendsVO.friends_no}'">
						</c:forEach>
<%-- 				</c:forEach> --%>
						</div>
					</div>
				</div>

				<div class="col-6 right">

					<div class="col-12 photowall" >
							<div id="tripGametable" style="display:none">
<div class="itstatus"><b class="Starting">進行中</b></div>
								<c:forEach var="itineraryStartVO" items="${itineraryStartSvc.getItineraryStartByMemNo(param.mem_no)}">
									<sapn class="itinerarySelect"  onclick="itineraryQuestionShow('${itineraryStartVO.itinerary_no}')">							
										<c:forEach var="itinerary_detailVO" items='${itinerary_detailSvc.getLands(itineraryStartVO.itinerary_no)}' varStatus="s">      

								 			  &nbsp&nbsp 	${ landscapeSvc.getOneLas(itinerary_detailVO.getLandscape_no()).landscape_name}	
												<c:if test="${!s.last}"><i class="fa fa-arrow-right"></i></c:if>
																							            													        								
						     			 </c:forEach>
						     			</sapn> 
								</c:forEach>
								<button id = "find-me"  onclick="getLocation()">Show my location</button><br/>
								<p id = "status"></p>
								<a id = "map-link" target="_blank"></a>
								<br>
								<br>
								<br>
								
								<div id="itineraryQuestion"></div>
							</div><br>
							<div id="profilePhoto">
						<br> <span class="title">Photo</span>
						<hr style="width: 95%">
						<div id="map"></div>

						<div>
							<br> <span class="title">Tour</span>
							<hr style="width: 95%">
<%-- 				<c:forEach var="paramMemNo" items="${paramList}">			  --%>
							<c:forEach var="itineraryVO" items="${itinerarySvc.getItineraryByMemNo(param.mem_no)}">
								<div class="it" Style="text-align:left; display:flex; color: #808080; margin:10px;">
							 		<div class="itstatus">已發佈</div>&nbsp&nbsp
							  			<c:forEach var="itinerary_detailVO" items='${itinerary_detailSvc.getLands(itineraryVO.getItinerary_no())}' varStatus="s">      
											<div class="landscapeName">
								 			  	${ landscapeSvc.getOneLas(itinerary_detailVO.getLandscape_no()).landscape_name}	
												<c:if test="${!s.last}"><i class="fa fa-arrow-right"></i></c:if>
											</div>																										            													        												
						     			 </c:forEach>
						     		 </div>   
						     </c:forEach>
<%-- 				</c:forEach>  --%>
						 </div>	
						 <div>
							<br> <span class="title">
								Favorite</span>
							<hr style="width: 95%">
					
				<c:forEach var="paramMemNo" items="${paramList}">			
								<c:forEach var="itinerary_trackVO" items="${itinerary_trackSvc.getItineraryByMemNo(param.mem_no)}">
								<div class="it" Style="text-align:left; display:flex; color: #808080; margin:10px;"">
							 		<div class="itstatus">已收藏</div>&nbsp&nbsp
							  			<c:forEach var="itinerary_detailVO" items='${itinerary_detailSvc.getLands(itinerary_trackVO.getItinerary_no())}' varStatus="s">      
											<div class="landscapeName">
								 			  	${ landscapeSvc.getOneLas(itinerary_detailVO.getLandscape_no()).landscape_name}	
												<c:if test="${!s.last}"><i class="fa fa-arrow-right"></i></c:if>
											</div>																										            													        												
						     			 </c:forEach>
						     		 </div>   
						     </c:forEach>
				</c:forEach>					
						 </div>			 
					  </div>
					  </div>				
					</div>
				</div>
				<div class="chatbox" id="chatbox">
					<div Style="background-color:#f0f0f0; height:40px">
						<button type="button" class="close" aria-label="Close" Style="margin:5px; outline:none;" onclick="closechatroom()">
								<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 		crossorigin="anonymous"></script> -->
<script src="https://code.jquery.com/jquery-3.3.1.js" ></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	
	<script>
// 	$(document).ready(function(){
// 		 var s = '${param.mem_no}'
// 		 console.log(s)
// 	});
// 	$(document).ready(function(){
// 		$('#tripGametable').hide();
// 	});
	
	
	
	
	function tripGame(){
// 		$('#tripGametable').empty();
// 		$('#tripGametable').append('<div></div>')
		$('#tripGametable').show();
		$('#profilePhoto').hide();
	}
	

	function itineraryQuestionShow(itinerary_no){
			$.ajax({
				 type: "GET",
				 url: "ItineraryServlet.do",
 				 data: creatQueryString(itinerary_no),
				 dataType: "json",
				 success: function (data){
					 console.log(data.length)
					 clearSelect();
					var random =  Math.floor(Math.random()*(data.length));
 					$(data).each(function(i, item){
 						console.log(random);
 						if(item.question!=null){
 							console.log(item.question);		
 							
 							$('#itineraryQuestion').append("<b>1.回答問題:</b>"+item.question+"<br>"+
 							"<input type=radio name=radiobutton id=qustionOption1 class=checkboxGroup>"+item.qusetionOption1+"<br>"+
 							"<input type=radio name=radiobutton id=qustionOption2 class=checkboxGroup>"+item.qusetionOption2+"<br>"+
 							"<input type=radio name=radiobutton id=qustionOption3 class=checkboxGroup>"+item.qusetionOption3 + "<br><br>" +
 							"<button type=button class='btn btn-warning' onclick=isAnswer("+"'"+item.answer+"'"+")>送出</button><br>");
 						}
 						if(random==i){
								$('#itineraryQuestion').append("<b>2.到"+item.landscape_name+"定位</b><br><br>"+
								"<button type=button class='btn btn-warning' onclick=toLocat()>定位檢查</button>");
						}
 						
 					});
 			
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
	         
	         function creatQueryString(itinerary_no){
				var queryString= {"action":"itineraryDetail","itinerary_no":itinerary_no};
				return queryString;
			}
			
			function clearSelect(){
				$('#itineraryQuestion').empty();
			}
			
			
			
			
	}

	
	function openchatroom(){
		var chatroom = document.getElementById('chatbox');
		chatroom.style.display = 'block';
	}
	function closechatroom(){
		var chatroom = document.getElementById('chatbox');
		chatroom.style.display = 'none';
	}
	

	
	function isAnswer(answer){
		console.log($(':checked').attr('id'));
		console.log(answer)
			if(answer===$(':checked').attr('id')){
				alert('恭喜答對了');
			}
	}
	
	
	function loginbtns(){
		location.href='<%=request.getContextPath() %>/front-end/member/login.jsp';
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
	
	
	
	
	
	// 瀏覽器支援 HTML5 定位方法
	
	function getLocation(){
						if (navigator.geolocation) {
						  // HTML5 定位抓取
						  navigator.geolocation.getCurrentPosition(function (position) {
						    mapServiceProvider(position.coords.latitude, position.coords.longitude);
						  },
						  function(error) {
						    switch (error.code) {
						      case error.TIMEOUT:
						        alert('連線逾時');
						        break;
					
						      case error.POSITION_UNAVAILABLE:
						        alert('無法取得定位');
						        break;
					
						      case error.PERMISSION_DENIED: // 拒絕
						        alert('請允許GPS定位功能喔!');
						        break;
					
						      case error.UNKNOWN_ERROR:
						        alert('不明的錯誤，請稍候再試');
						        break;
						    }
						  });
						} else { // 不支援 HTML5 定位
						  // 若支援 Google Gears
						  if (window.google && google.gears) {
						    try {
						      // 嘗試以 Gears 取得定位
						      var geo = google.gears.factory.create('beta.geolocation');
						      geo.getCurrentPosition(successCallback, errorCallback, { enableHighAccuracy: true, gearsRequestAddress: true });
						    } catch (e) { 
						      alert('定位失敗請稍候再試');
						    }
						  } else {
						    alert('請允許手機的GPS定位功能喔!');
						  }
						}
					
						// 取得 Gears 定位發生錯誤
						function errorCallback(err) {
						  var msg = 'Error retrieving your location: ' + err.message;
						  alert(msg);
						}
					
						// 成功取得 Gears 定位
						function successCallback(p) {
						  mapServiceProvider(p.latitude, p.longitude);
						}
					
						// 顯示經緯度
						function mapServiceProvider(latitude, longitude) {
						  alert('經緯度：' + latitude + ', ' + longitude);
						}
	}
	
	
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpwWDqBnEqZEDW59nB-_DiyYEeLRveps0&callback=initMap"
		type="text/javascript"></script>
		
		
</body>
</html>