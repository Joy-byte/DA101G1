<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.guidetour.model.*"%>
<%@ page import="com.guide.model.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ page import="java.util.*"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="java.text.SimpleDateFormat"%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />
<jsp:useBean id="gtListSvc" scope="page" class="com.gtlist.model.GTListService" />
<jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" />
<jsp:useBean id="guideSvc" scope="page" class="com.guide.model.GuideService" />
<jsp:useBean id="guideTourSvc" scope="page" class="com.guidetour.model.GuideTourService" />
<jsp:useBean id="gtcListSvc" scope="page" class="com.gtclist.model.GTCListService" />
<jsp:useBean id="resOrderSvc" scope="page" class="com.resorder.model.ResOrderService" />
<% 
String guideTour_no = request.getParameter("guideTour_no");
String guide_no = request.getParameter("guide_no");
GuideTourVO guideTourVO = guideTourSvc.getOneGuideTour(guideTour_no);
GuideVO guideVO = guideSvc.getOneGuide(guideTourVO.getGuide_no());
MemberVO guideMemVO = memSvc.getOneMember(guideVO.getMem_no());
request.setAttribute("guideTourVO", guideTourVO);
request.setAttribute("guideVO", guideVO);
request.setAttribute("guideMemVO", guideMemVO);
%>

<%
	MemberVO memVO = (MemberVO) request.getAttribute("guideMemVO");
	long age = 0;
	String user = null;

	//生日算年齡
	Date birth = memVO.getMem_birthday();
	LocalDate birthday = birth.toLocalDate();
	LocalDate today = LocalDate.now();
	age = ChronoUnit.YEARS.between(birthday, today);

	//生日算星座
	int[] dayArray = new int[]{20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
	String[] constellations = new String[]{"摩羯座", "水瓶座", "雙魚座", "牡羊座", "金牛座", "雙子座", "巨蟹座", "獅子座", "處女座", "天秤座",
			"天蠍座", "射手座", "摩羯座"};
	int day = birthday.getDayOfMonth();
	int month = birthday.getMonthValue();
	if (day < dayArray[month - 1]) {
		user = constellations[month - 1];
	} else {
		user = constellations[month];
	}
	pageContext.setAttribute("age", age);
	pageContext.setAttribute("user", user);
%>

<%
	Map<String,Integer> mapGray = new HashMap<>();
	Map<String,Integer> mapGreen = new HashMap<>();
	mapGray = resOrderSvc.getGrayDay(guideTour_no);
	mapGreen = resOrderSvc.getGreenDay(guideTour_no);
	Set<String> set = new HashSet<>(mapGray.keySet());
	//取得其他行程被預約的日期
	Set<String> otherDay = resOrderSvc.getOtherDay(guideTour_no,guide_no);
	if(otherDay.size()!=0){
		set.addAll(otherDay);
	}
	String jsonStr1 = new JSONArray(set).toString();
	//設定不能點選的日期
	request.setAttribute("dateArray", jsonStr1);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date date = new java.util.Date();
	StringBuilder str= new StringBuilder();
	str.append("{\"start\":\"1970-01-01\",\"end\":\""+Date.valueOf(sdf.format(date))+"\",\"rendering\":\"background\",\"color\":\"#bac4ce\"},");
	for(String dateGray : mapGray.keySet()) {
		str.append("{\"start\":\""+dateGray+"\",\"rendering\": \"background\",\"color\": \"#bac4ce\"},");
		str.append("{\"start\":\""+dateGray+"\",\"title\":\"已預約  "+mapGray.get(dateGray)+"  人\",\"backgroundColor\":\"#bac4ce\",\"borderColor\":\"#bac4ce\"},");
	}
	if(otherDay.size()!=0){
		for(String otherGrayDay : otherDay) {
			str.append("{\"start\":\""+otherGrayDay+"\",\"rendering\": \"background\",\"color\": \"#bac4ce\"},");
			str.append("{\"start\":\""+otherGrayDay+"\",\"title\":\"其他行程預約\",\"backgroundColor\":\"#bac4ce\",\"borderColor\":\"#bac4ce\"},");
		}
	}
	//GreenDay設Id方便之後刪除event (i.g.id="2019-07-15event"/id="2019-07-15bg")
	for(String dateGreen : mapGreen.keySet()){
		str.append("{\"id\":\""+dateGreen+"bg\",\"start\":\""+dateGreen+"\",\"rendering\":\"background\"},");
		str.append("{\"id\":\""+dateGreen+"event\",\"start\":\""+dateGreen+"\",\"title\":\"已預約  "+mapGreen.get(dateGreen)+"  人\",\"backgroundColor\":\"#b6e2db\",\"borderColor\":\"#b6e2db\"},");
	}
	String jsonStr2 = "["+str.substring(0,str.length()-1)+"]";
	//所有預約事件
	request.setAttribute("event", jsonStr2);
	//找當天已預約的人數來設定++按鈕上限值
	JSONObject jsonGreen = new JSONObject(mapGreen);
	request.setAttribute("mapGreen", jsonGreen);
		
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link href='fullcalendar/core/main.css' rel='stylesheet' />
<link href='fullcalendar/daygrid/main.css' rel='stylesheet' />
<link href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css' rel='stylesheet' />
<link href='https://fonts.googleapis.com/css?family=Pacifico|Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

<script src='fullcalendar/core/main.js'></script>
<script src='fullcalendar/interaction/main.js'></script>
<script src='fullcalendar/daygrid/main.js'></script>
<script src='fullcalendar/core/locales-all.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>

<title>GuideTourDetail</title>

<style type="text/css">
body {
	font-family: Microsoft JhengHei;
	letter-spacing: 2px;
}

.top {
	padding-top: 12%;
	color: #fff;
}

.guide-info {
	letter-spacing: 4px;
	min-width: 300px;
}

.guide-info button {
	text-decoration: none;
	font-size: 14px;
	color: #fff;
	border: 1px solid #fff;
	border-radius: 2em;
	padding: 5px 20px;
	margin-right: 20px;
	letter-spacing:2px;
	background:none;
	
}

.guide-info button:hover {
	border-color: #000;
	background-color: #000;
}

.tour-content {
	margin-top: 4%;
	padding: 50px 0 50px 0;
	background-color: #fff;
	min-width:1600px;
}

#guide_pic {
	height: 300px;
	width: 300px;
	border-radius: 50%;
	border-color: #fff;
	border: 3px solid;
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

.intro {
	line-height: 36px;
	width: 100%;
	display: flex;
	justify-content: center;
}

.intro p {
	width: 700px;
	letter-spacing: 2px;
	text-align: center;
}

.tour-info {
	margin-top: 30px;
	display: flex;
	justify-content: center;
}

.tour-left {
	width: 1000px;
	min-width: 800px;
	margin-right:60px;
}

.tour-right {
	width: 300px;
	background:#fff;
	min-width: 300px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.tour-left .title {
	font-family: monospace;
	font-size: 23px;
	font-weight: bold;
	color: #666;
	width: 950px;
	line-height: 40px;
	padding: 10px 20px;
	border-bottom: 1px solid #666;
}

.location-map {
	display: flex;
	justify-content: center;
	margin-bottom: 30px;
}

.location {
	margin-top: 30px;
	margin-right: 30px;
	width: 220px;
}

.location a {
	font-size: 18px;
}

.map {
	margin-top: 30px;
	left: 10px;
	width: 650px;
	height: 600px;
	background-color: gray;
}

.tour-detail {
	width: 900px;
	margin: 30px;
	line-height: 26px;
}

.right-nav {
	width: 200px;
	color: #333 !important;
	margin: 5px;
	padding: 10px;
	text-align: center;
	border: 2px solid #888;
	background-color: #fff !important;
}

.right-nav:hover {
	border-color: #888 !important;
}
/* not active */
.nav-pills .nav-link:not(.active){
	border-color:#ccc;	
	color:#888 !important;
}
.nav-pills {
	margin-top: 50px;
}

.reservation {
	width: 100%;
	border: 1px solid #ccc;
	margin-top: 30px;
	text-align: center;
}

.reservation .title {
	font-family: monospace;
	font-size: 18px;
	font-weight: bold;
	color: #666;
	line-height: 30px;
	padding: 10px 10px;
}

.reservation .inner {
	position: center;
	margin-bottom: 30px;
	margin-top: 10px;
}

.custom-checkbox .custom-control-label::before {
	background-color: #eee;
	border-color: #aaa;
}

.custom-control-input:checked ~.custom-control-label::before {
	background-color: #fa0;
	border-color: #fa0;
}

.pic-box {
	width: 60px;
	height: 60px;
	border-radius: 50%;
	overflow: hidden;
	background-color: gray;
	margin-right: 30px;
}

.pic-box img {
	width: 60px;
	height: 60px;
}

pre {
	white-space: pre-wrap;
	/*word-wrap: break-word;*/
}

#columns {
	column-width: 300px;
	column-gap: 15px;
	width: 1020px;
	min-width: 800px;
	margin: 50px auto;
}

#columns .guidetour {
	position: relative;
	background: #fefefe;
	border: 2px solid #eee;
	margin: 0 2px 20px 0;
	padding-bottom: 15px;
	transition: opacity .2s ease-in-out;
	display: inline-block;
	width: 320px;
}

#columns .guidetour .guidetour-pic {
	position: relative;
	min-height:160px;
	background:gray;
}

#columns .guidetour .guidetour-pic img {
	width: 100%;
	height: auto;
}

.tour-pic {
	filter: brightness(0.8);
}

#columns .guidetour .info {
	color: #444;
	line-height: 1.5;
	margin: 20px 0px 0px 0px; /*上右下左*/
}
.landscape-other {
	font-size: 16px;
	margin: 0px 15px 10px 15px;
}

.info .more {
	padding: 6px 13px;
	border: 2px solid #919191;
	color: #919191;
	border-radius: 2.5em;
	font-size: 10px;
	margin: 0px 0px 0 220px;
	display: flex;
	justify-content: center;
	font-weight: bold;
	background: #fff;
	letter-spacing: 2px;
}

.info .more:hover {
	background-color: #919191;
	color: #fff;
	transition: all 0.3s;
}

.gc {
	position: absolute;
	left: 8px;
	bottom: 8px;
	margin: 10px;
}

.rate {
	position: absolute;
	top: 10px;
	right: 5px;
	color: #fff;
	margin: 10px;
	background-color: #f0ad4e;
	border-radius: 2.5em;
	padding: 6px 10px;
	font-size: 13px;
}

.fa-star {
	color: #aaa;
	font-size: 20px;
	letter-spacing: 0px;
}

.errorMsg {
	color: red;
	font-size: 10px;
}
.form-group{
	margin-bottom:0px;
}
.lang{
	padding-top: 20px;
	padding-bottom: 20px;
	letter-spacing: 2px;
}
#calendar{
	position: fixed;
	width: 850px;height: 750px;
	top:50%;
	left:50%;
	margin-top:-375px;
	margin-left: -425px;
	z-index: 100;
	background-color: #fff;
	padding:50px;
	border-radius: 1em;
	display:none;
}
#black{
	position:fixed;
	width:100vw;
	height:100vh;
	z-index: 90;
	background-color: #000;
	display:none;
	opacity: 0.5;
}
.textOnCal{
	position:fixed;
	z-index:105;
	font-size:13px;
	top:50%;
	left:50%;
	margin-top:-340px;
	margin-left:-180px;
}
		.fc-day-header{
			background:#c4dbde;
		}
		.fc-today-button{
			border-radius:1.5em;
			padding:5px 15px;
		}
		.fc-prev-button{
			border-radius:1.5em;
			background:#89abcd;
			border-color:#89abcd;
		}
		.fc-prev-button:hover{
			background:#5485b6;
			border-color:#5485b6;
		}
		.fc-next-button{
			border-radius:1.5em;
			background:#89abcd;
			border-color:#89abcd;
		}
		.fc-next-button:hover{
			background:#5485b6;
			border-color:#5485b6;
		}
</style>
</head>
<body>
	<%@ include file="/front-end/header.file" %>
	<div id="black"></div>
	<!-- 	landscapeModal -->
	<div class="modal fade" id="landscapeModal" tabindex="-1" role="dialog" aria-labelledby="landscapeModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="landscapeModalLabel">Modal title</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>		        
		      </div>
		      
		      <div class="modal-body .modal-lg" >		
		      		<div id="map-canvas"></div>						     
		       			 <div id="landscapeAllPic" style="text-align:center">
								
					    </div>
		      </div>
		      							       
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>							    
		      </div>
		      
		    </div>
		  </div>
	</div>
	
	<div id='calendar'>
		<div style="display:flex;width:100%;margin-top:20px;justify-content: center;">
		<div class="textOnCal">
			<font style="color:#dce1e4">●</font>灰底：不可選(本行程<span style="color:red">已達人數上限</span>或<span style="color:red">不接受共遊</span><br>
			 &nbsp&nbsp 或<span style="color:red">在地旅伴的其他行程已被預約該日期</span>)<br>
			<font style="color:#cce3c4">●</font>綠底：可選(本行程已有人預約，但可接受共遊)
		</div>
		<button type="button" class="btn btn-warning btn-sm" style="margin-right: 10px;" onclick="checkDate()">確定</button>
		<button type="button" class="btn btn-secondary btn-sm" onclick="dateClose()" style="margin-left: 10px;">關閉</button>
		</div>
	</div>
	<!-- Modal report -->
	<div class="modal fade" id="exampleModalReport" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h6 class="modal-title" id="exampleModalCenterTitle">檢舉原因</h6>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form action="GuideReportServlet" method="post" id="reportForm" onsubmit="return false">
		      <div class="modal-body">
		      <span id="error_report" style="font-size:10px;color:red;"></span>
		        <div class="input-group">
				  <textarea id="reportReason" class="form-control" name="guideRep_reason" aria-label="With textarea" rows="8" placeholder="上限500字" style="margin:15px;"></textarea>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" style="letter-spacing:2px">關閉</button>
		       
		        <input type="hidden" name="cancel" id="cancel">
		        <button type="submit" class="btn btn-primary btn-sm" style="letter-spacing:2px" onclick="reportVerify()">送出</button>
		      </div>
		      <input type="hidden" name="action" value="insert">
		      <input type="hidden" name="guide_no" value="${guideTourVO.guide_no}">
		      <input type="hidden" name="guideTour_no" value="${guideTourVO.guideTour_no}">
		      <input type="hidden" name="mem_no" value="${sessionScope.memberVO.mem_no}">
	      </form>
	    </div>
	  </div>
	</div>

<!-- 分隔線 -->
	<div class="outer">
		<img src="../guide/ReadPicture2?mem_no=${guideVO.mem_no}&type=guide_cover" id="cover">
		<div class="container">
			<div class="row justify-content-center top">
				<div class="col-6">
					<img id=guide_pic
						src="../guide/ReadPicture2?mem_no=${guideVO.mem_no}&type=guide_pic">
				</div>
				<div class="col-5 guide-info">
					<p>
						<font style="font-size: 22px; font-weight: bold;">${guideMemVO.mem_name}</font>
					</p>
					<div style="font-size: 18px;">
						<p>${guideVO.guide_area} &nbsp${age}歲  &nbsp${user}</p>
						
						<p>興趣：${guideVO.guide_hobby}</p>
						<p>已發布 ${guideTourSvc.getUploadNum(guideVO.guide_no)} &nbsp&nbsp伴遊數 ${guideTourSvc.getFinishNum(guideVO.guide_no)}</p>
						<p class="lang">外語：
						<c:if test="${not empty guideVO.guide_stLang}">
							<span style="background-color:#FF8C00;padding:6px; border-radius:4px 4px 4px 4px;color:#fff;font-size:16px">
							${guideVO.guide_stLang}</span>&nbsp&nbsp
						</c:if>
						<c:if test="${not empty guideVO.guide_ndLang}">
							<span style="background-color:#FF8C00;padding:6px; border-radius:4px 4px 4px 4px;color:#fff;font-size:16px">
							${guideVO.guide_ndLang}</span>
						</c:if>
						<c:if test="${empty guideVO.guide_stLang}">
							無
						</c:if>
						</p>
						<form action="GuideTour.do" method="post">
							<button type="submit" >聯絡他</button>
							<input type="hidden" name="chatMemNo" value="${guideVO.mem_no}">
							<input type="hidden" name="action" value="chat">
						<button type="button" data-toggle="modal" data-target="#exampleModalReport">檢舉</button>
						</form>
					</div>

				</div>
			</div>
		</div>
		<div class="tour-content">
			<div class="intro">
				<p>${guideVO.guide_intro}</p>
			</div>

			<div class="tour-info">
				<div class="tab-content" id="v-pills-tabContent">
					<!-- 行程詳情頁 -->
					<div class="tab-pane fade show active tour-left" id="v-pills-home"
						role="tabpanel" aria-labelledby="v-pills-home-tab">
						<div class="title">LOCATION</div>
						<div class="location-map">
							<div class="location">
								<c:forEach var="gtListVO" varStatus="status" items="${gtListSvc.getGTListByGTNo(guideTourVO.guideTour_no)}">
									<i class="fa fa-map-marker" style="font-size: 25px;color:#dd725a"></i>
									<!-- 景點名 -->
									<span data-toggle="modal" data-target="#landscapeModal" class="landName" id="${gtListVO.landscape_no}">${landscapeSvc.getOneLas(gtListVO.landscape_no).landscape_name}</span>
									<br>
									<c:if test="${not status.last}">
										&nbsp&nbsp&nbsp&nbsp<i class="fa fa-angle-double-down"></i>
										<br>
										&nbsp&nbsp&nbsp&nbsp<i class="fa fa-angle-double-down"></i>
										<c:if test="${gtListVO.landscape_trans=='火車'}">
											<i class="fa fa-train"></i>
										</c:if>
										<c:if test="${gtListVO.landscape_trans=='腳踏車'}">
											<i class="fa fa-bicycle"></i>
										</c:if>
										<c:if test="${gtListVO.landscape_trans=='步行'}">
											<i class="fa fa-male"></i>
										</c:if>
										<c:if test="${gtListVO.landscape_trans=='機車'}">
											<i class="fa fa-motorcycle"></i>
										</c:if>
										<c:if test="${gtListVO.landscape_trans=='捷運'}">
											<i class="fa fa-subway"></i>
										</c:if>
										<c:if test="${gtListVO.landscape_trans=='開車'}">
											<i class="fa fa-car"></i>
										</c:if>
										<c:if test="${gtListVO.landscape_trans=='公車'}">
											<i class="fa fa-bus"></i>
										</c:if>
										<c:if test="${gtListVO.landscape_trans=='其他'}">
											其他
										</c:if>
										<c:if test="${(gtListVO.landscape_hr+gtListVO.landscape_min)!=0}">
											${gtListVO.landscape_hr}時${gtListVO.landscape_min}分
										</c:if>
										 <br>
										&nbsp&nbsp&nbsp&nbsp<i class="fa fa-angle-double-down"></i>
										<br>
									</c:if>
								</c:forEach>

							</div>
							<div class="map" id="map"></div>
						</div>
						<div class="title">INTRODUCTION</div>
						<!-- 行程詳情 -->
						<div class="tour-detail">
							<pre>
${guideTourVO.guideTour_detail}
							</pre>
						</div>
						<div class="title">REVIEWS</div>
						<div class="review">
							<c:forEach var="resOrderVO" varStatus="status" items="${resOrderSvc.getTourResOrders(guideTourVO.guideTour_no)}">
								<c:if test="${resOrderVO.resOrder_rate != 0}">
									<div class="row" style="margin: 25px; display: flex;">
										<div class="pic-box">
											<img src="ReadMemPic?mem_no=${resOrderVO.mem_no}">
										</div>
										<div class="review-text" style="width: 800px">
											<!-- 旅客姓名 -->
											${memSvc.getOneMember(resOrderVO.mem_no).mem_name}<br> 

											<span class="fa fa-star" id="1s${status.index}"></span> 
											<span class="fa fa-star" id="2s${status.index}"></span> 
											<span class="fa fa-star" id="3s${status.index}"></span> 
											<span class="fa fa-star" id="4s${status.index}"></span> 
											<span class="fa fa-star" id="5s${status.index}"></span>
											<pre>
${resOrderVO.resOrder_review}
											</pre>
											
										</div>
										<script type="text/javascript">
										var rate = '${resOrderVO.resOrder_rate}';
										var index ='${status.index}';
										   	for(i=0;i<5;i++){
										   		if(i<rate){
										   			document.getElementById((i+1)+'s'+index).style.color="orange";
										   		}else{
										   			document.getElementById((i+1)+'s'+index).style.color="#aaa";
										   		}
										   	}
										</script>
									</div>
								</c:if>
							</c:forEach>

						</div>

					</div>
					<!-- 其他行程頁 -->
					<div class="tab-pane fade tour-left" id="v-pills-profile"
						role="tabpanel" aria-labelledby="v-pills-profile-tab">
						<div id="columns">
							<c:forEach var="guideTourVO" items="${guideTourSvc.getGuideTour(guideVO.guide_no)}">
								<div class="guidetour">
									<div class="guidetour-pic">
										<img class="tour-pic"
											src="ReadTourPic?guideTour_no=${guideTourVO.guideTour_no}">
										<div class="rate">
											<span>評價 ${guideTourSvc.getGuideTourRate(guideTourVO.guideTour_no)}</span>
										</div>
									</div>

									<div class="info">
										<div class="landscape-other">
											<span style="font-size: 18px;"><b>【${guideTourVO.guideTour_name}】</b></span><br>
											<c:forEach var="gtListVO" varStatus="status"
												items="${gtListSvc.getGTListByGTNo(guideTourVO.guideTour_no)}">
											${landscapeSvc.getOneLas(gtListVO.landscape_no).landscape_name}
											<c:if test="${not status.last}">
													<i class="fa fa-arrow-circle-right"></i>
												</c:if>
											</c:forEach>
										</div>

										<div class="gc">
											<c:forEach var="gtcListVO"
												items="${gtcListSvc.getCategoryByGTno(guideTourVO.guideTour_no)}">
												<span
													style="margin: 0 2px; padding: 4px; background: #70c7be; border-radius: 3px 3px; color: #fff; font-size: 13px;">
													#${gtcListVO.category}
												</span>
											</c:forEach>
										</div>
										<form METHOD="get" ACTION="GuideTourDetail.jsp" name="more">
											<input type="hidden" name="action" value="readMore">
											<input type='hidden' name='guide_no' value="${guideTourVO.guide_no}">
											<input type="hidden" name="guideTour_no" value="${guideTourVO.guideTour_no}"> 
											<input class="more" type="submit" value="MORE">
										</form>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>

				<!-- 右側邊欄區域 -->
				<div class="tour-right">
					<div class="nav flex-column nav-pills" id="v-pills-tab"
						role="tablist" aria-orientation="vertical">
						<a class="nav-link active right-nav" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab"
							aria-controls="v-pills-home" aria-selected="true">行程詳情</a> 
						<a class="nav-link right-nav" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab"
							aria-controls="v-pills-profile" aria-selected="false">他的伴遊行程</a>
						<a class="nav-link right-nav" id="v-pills-profile-tab" data-toggle="pill" href="#" role="tab" aria-controls="v-pills-profile"
							aria-selected="false"><i class="fa fa-heart"></i>加入收藏</a>
					</div>
					<div class="reservation">
						<div class="inner">
							<form action="ResOrderServlet" method="post" onsubmit="return false" id="resForm">
								<div class="title" id="titletest">MAKE A RESERVATION</div>
								<div
									style="font-size: 17px; margin: 0px 20px; font-weight: bold">【${guideTourVO.guideTour_name}】</div>
								<p></p>
								<font>當日上限${guideTourVO.guideTour_peo}人</font><br>
								<div style="height:23px;">
									<span class="errorMsg" id="errorDate">${errorMsgs.resOrder_date}</span>
								</div>
								<div class="form-group row justify-content-center">
									<div class="input-group mb-3 row justify-content-center">日期：
									<input type="text" class="form-control form-control-sm col-4" id="date" aria-label="Example text with button addon" aria-describedby="button-addon1" disabled>
									  <div class="input-group-prepend">
									    <button class="btn btn-outline-secondary btn-sm" type="button" id="btn-choice" onclick="choice()">選擇</button>
									  </div>
									</div>
								</div>
								<div style="height:0px;">
									<span class="errorMsg" id="errorPeo">${errorMsgs.resOrder_peo}</span>
								</div>
								<div class="input-group row" style="margin-left:45px">
									人數：
									<div class="input-group-prepend">
										<button type="button" class="btn btn-sm btn-secondary" id="moins" onclick="minus()">-</button>
									</div>
									<input class="form-control form-control-sm col-2" type="text" name="resOrder_peo" value="1" id="count" readonly>
									<div class="input-group-append">
										<button class="btn btn-sm btn-secondary" type="button" onclick="plus()">+</button>
									</div>
								</div>
								<p></p>
								<div class="custom-control custom-checkbox"
									style="margin-bottom: 20px;">
									<input type="checkbox" class="custom-control-input" name="resOrder_joinpeo" id="customCheck1" checked="checked" value="ok">
									<label class="custom-control-label" for="customCheck1">接受與人共遊</label>
								</div>
								<div class="form-group row justify-content-center">
									<textarea class="form-control form-control-sm col-8" rows="5" placeholder="留言" name="resOrder_msg"></textarea>
								</div>
								<input type="hidden" name="action" value="reservation">
								<input type="hidden" name="resOrder_date" id="resOrder_date">
								<input type="hidden" name="guideTour_no" value="${guideTourVO.guideTour_no}"> 
								<input type="hidden" name="guide_no" value="${guideTourVO.guide_no}"> 
								<input type="hidden" name="mem_no" value="${sessionScope.memberVO.mem_no}"> 
								<input type="submit" class="btn btn-outline-info btn-sm"
									id="check-res" value="預 定" onclick="verify()" style="margin-top:20px;">
							</form>

						</div>

					</div>
				</div>
			</div>
		</div>

	</div>
	
	<!-- Modal1 -->
	<div class="modal fade" id="modal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h6 class="modal-title" id="exampleModalLabel">提醒</h6>
	          <span aria-hidden="true">&times;</span>
	      </div>
	      <div class="modal-body">
	      	  請勿預定自己的伴遊行程！
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
	      </div>
	    </div>
	  </div>
	</div>

	<script type="text/javascript">
	/*************************** New Calendar ****************************/
    //不能選擇的日期
	var jsonArray = JSON.parse('${dateArray}');
    var dateArray = [];
    for(var i=0;i<jsonArray.length;i++){
  	  dateArray.push(new Date(jsonArray[i]+'T00:00:00'))
    }
    var dayEvent = JSON.parse('${event}')
    var dayEventSrc = [{events:dayEvent,id:'event'}]
    var choiceDate;
    var calendars = {};
    document.addEventListener('DOMContentLoaded', function() {
      var calendarEl = document.getElementById('calendar');
      var minDate = new Date();
      var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['interaction', 'dayGrid'],
        locale:'zh-tw',
        selectable: true,
        selectConstraint:{
      	  startTime: '00:00',
      	  endTime: '24:00'
        },
        eventSources:dayEventSrc 
      });
      calendars.calendar = calendar;
      calendar.render();
      calendars.calendar.setOption('height',550);
      calendars.calendar.on('dateClick', function(info) {
		  var today = new Date();
        var date = new Date(info.dateStr+"T00:00:00");
        
        if(date-today>0){
          for(var i=0;i<dateArray.length;i++){
            if((date-dateArray[i])==0){
              return;
            }
          }
          choiceDate = info.dateStr;
  	  	} 
	  }); 
		calendars.calendar.setOption('selectAllow', function(info){
	          info.end = info.start;
	          if(info.start<Date.now()){
	            return false;
	          }
	          for(var i=0;i<dateArray.length;i++){
	            if((info.start-dateArray[i])==0){
	              return false;
	            }
	          }
	            return true;
	    });
    });
    
    
    /**************************** Web Socket *****************************/
	var MyPoint = "/Websocket/reservation";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint; //**

	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	//改變event用
	var jsonObj;
	var events;
	var dateArray;
	var mapGreen = JSON.parse('${mapGreen}'); //+-btn use

	//connect
	window.onload = function() { 	
		// create a websocket
		webSocket = new WebSocket(endPointURL); 
		webSocket.onopen = function(event) { };
		
		//server傳回json物件，對Calendar進行修改
		webSocket.onmessage = function(event) { // WebSocket Server:session.getAsyncRemote().sendText(message)
			jsonObj = JSON.parse(event.data);
			//if同行程
			if(jsonObj.guide_no=='${param.guide_no}' && jsonObj.guideTour_no=='${param.guideTour_no}'){
				alert('預約日曆已更動！');
				document.getElementById("date").value = '';
				eventsArray = jsonObj.events;
				dateArray = jsonObj.dateArray;
				
				var newDateArray = []; //新增的不可選日期Array
				for(var i=0;i<dateArray.length;i++){
					newDateArray.push(new Date(dateArray[i]+'T00:00:00'))
			    }
				//綠色日改為灰色日
				if(jsonObj.greenToGray=='true'){
					var eventBg = calendars.calendar.getEventById(jsonObj.resOrder_date+'bg');
					var eventEvent = calendars.calendar.getEventById(jsonObj.resOrder_date+'event');
					if(eventBg !=null){
						eventBg.remove();
						eventEvent.remove();	
					}
					if(jsonObj.mapGreen!='null'){
						mapGreen = jsonObj.mapGreen;
					}
				}
				var source = {events:eventsArray}
				calendars.calendar.addEventSource(source);
				calendars.calendar.setOption('height',550);
				//更改Calendar selectAllow 
				calendars.calendar.setOption('selectAllow', function(info){
			          info.end = info.start;
			          if(info.start<Date.now()){
			            return false;
			          }
			          for(var i=0;i<newDateArray.length;i++){
			            if((info.start-newDateArray[i])==0){
			              return false;
			            }
			          }
			            return true;
			    });
				//刪掉原本的dateClick callback
				calendars.calendar.off('dateClick');
				//新增一個dateClick callback
				calendars.calendar.on('dateClick', function(info) {
					  var today = new Date();
			          var date = new Date(info.dateStr+"T00:00:00");
			          
			          if(date-today>0){
			            for(var i=0;i<newDateArray.length;i++){
			              if((date-newDateArray[i])==0){
			                return;
			              }
			            }
			            choiceDate = info.dateStr;
			    	  } 
				});
			}
			//同個Guide發布的行程頁，會員會看到"其他行程預定""
			if(jsonObj.guide_no=='${param.guide_no}' && jsonObj.guideTour_no!='${param.guideTour_no}'){
				alert('預約日曆已更動！');
				document.getElementById("date").value = '';
				eventsArray = jsonObj.otherEvents;
				dateArray = jsonObj.otherDateArray;
				
				var newDateArray = []; //新增的不可選日期Array
				for(var i=0;i<dateArray.length;i++){
					newDateArray.push(new Date(dateArray[i]+'T00:00:00'))
			    }
				//綠色日改為灰色日
				if(jsonObj.greenToGray=='true'){
					var eventBg = calendars.calendar.getEventById(jsonObj.resOrder_date+'bg');
					var eventEvent = calendars.calendar.getEventById(jsonObj.resOrder_date+'event');
					if(eventBg !=null){
						eventBg.remove();
						eventEvent.remove();	
					}
					if(jsonObj.mapGreen!='null'){
						mapGreen = jsonObj.mapGreen;
					}
				}
				var source = {events:eventsArray}
				calendars.calendar.addEventSource(source);
				calendars.calendar.setOption('height',550);
				//更改Calendar selectAllow 
				calendars.calendar.setOption('selectAllow', function(info){
			          info.end = info.start;
			          if(info.start<Date.now()){
			            return false;
			          }
			          for(var i=0;i<newDateArray.length;i++){
			            if((info.start-newDateArray[i])==0){
			              return false;
			            }
			          }
			            return true;
			    });
				//刪掉原本的dateClick callback
				calendars.calendar.off('dateClick');
				//新增一個dateClick callback
				calendars.calendar.on('dateClick', function(info) {
					  var today = new Date();
			          var date = new Date(info.dateStr+"T00:00:00");
			          
			          if(date-today>0){
			            for(var i=0;i<newDateArray.length;i++){
			              if((date-newDateArray[i])==0){
			                return;
			              }
			            }
			            choiceDate = info.dateStr;
			    	  } 
				});
			}
		};
		webSocket.onclose = function(event) { };
	}
	
	function disconnect() {
		webSocket.close();
	}
	window.onbeforeunload = function() {
		webSocket.close();
	};
	/******************************加減按鈕********************************/
	  //Calendar的確定button
      var max = parseInt('${guideTourVO.guideTour_peo}', 10);
      function checkDate(){
        $("#calendar").fadeOut(200);
        $("#black").fadeOut(200);
    	for(var i=0;i<dateArray.length;i++){
    		if(dateArray[i]==choiceDate){
    			alert("您選取的日期已無法預約，請重新選取！")
    			return;
    		}
    	}
        if(choiceDate!=undefined){
        	 document.getElementById('date').value=choiceDate;
             document.getElementById('resOrder_date').value=choiceDate;
        }
        document.getElementById("count").value=1;
      }
	  var chooseDate = document.getElementById("date");
      var count = document.getElementById("count");
      var tourMaxPeo = parseInt('${guideTourVO.guideTour_peo}', 10);
      var num = 1;
      //++button
      function plus(){
    	num = count.value;  
    	if(chooseDate.value!=null){
    		if(mapGreen.hasOwnProperty(chooseDate.value)){
    			max = tourMaxPeo - parseInt(mapGreen[chooseDate.value],10); 
    		}else{
    			max = parseInt('${guideTourVO.guideTour_peo}', 10);
    		}
    	}
      	if(num < max){
      		num++;
          	count.value = num;
      	}
      }
      //--button
      function minus(){
    	num = count.value;  
        if (num > 1) {
          num--;
          count.value = num;
        }  
      }
	/**************************** Reservation *****************************/	
   	 //表單驗證
    function verify(){
    	var resForm = document.getElementById("resForm");
    	var date = document.getElementById("date");
    	var count = document.getElementById("count");
    	var join='';
    	if (document.getElementById('customCheck1').checked){
    		join = document.getElementById("customCheck1").value
    	}
    	var errorDate = document.getElementById("errorDate");
    	var errorPeo = document.getElementById("errorPeo");
    	if('${sessionScope.memberVO.mem_no}'=='${guideVO.mem_no}'){
    		$('#modal1').modal('show');
    		return;
    	}
    	if(date.value == "" && count.value == ""){
    		errorPeo.innerText="請選擇人數";
    		errorDate.innerText="請選擇日期";
    	}else if(count.value == ""){
    		errorPeo.innerText="請選擇人數";
    		errorDate.innerText="";
    	}else if(date.value == ""){
    		errorPeo.innerText="";
    		errorDate.innerText="請選擇日期";
    	}else{
    		//驗證過後將預定的Data傳到webSocket Server
    		var jsonObj = {
    				"guideTour_no" : '${param.guideTour_no}',
    				"guide_no" : '${param.guide_no}',
    				"resOrder_date" : date.value,
    				"resOrder_peo" : count.value,
    				"resOrder_joinpeo" : join,
    				"dateArray" : jsonArray,
    				"mapGreen": JSON.stringify(mapGreen)
    			};
    		webSocket.send(JSON.stringify(jsonObj));
    		resForm.onsubmit=true;
    	}
    }

    //檢舉驗證
    function reportVerify(){
    	if(document.getElementById("reportReason").value == ''){
    		document.getElementById("error_report").innerText='請輸入檢舉原因';
    	}else{
    		reportForm.onsubmit=true;
    	}
    }
	//預定成功
   	if('${param.success}'=='success'){
   		
   		swal("", "預約成功！", "success")
  	} 
   	if('${param.report}'=='report'){
   		alert('檢舉已送出！');
   	}
   	
    function dateClose(){
    	$("#calendar").fadeOut(200);
      $("#black").fadeOut(200);
    }
   	function choice(){
        $("#calendar").fadeIn(300);
        $("#black").fadeIn(300);
    }
   	
	/**************************** Google Map ****************************/
	var landscape = new Object(); //景點名:經緯度、編號
	<c:forEach items="${gtListSvc.getGTListByGTNo(guideTourVO.guideTour_no)}" var="gtListVO" varStatus="status"> 
		var lat = '${landscapeSvc.getOneLas(gtListVO.landscape_no).landscape_lat}';
		var lng = '${landscapeSvc.getOneLas(gtListVO.landscape_no).landscape_lng}';
	    var name = '${landscapeSvc.getOneLas(gtListVO.landscape_no).landscape_name}';
		landscape[name] = {'lat':lat,'lng':lng};
	</c:forEach> 
	
   	var map;
	var mapOptions;
	var markers = new Object();
	function initMap() {
		var bounds = new google.maps.LatLngBounds();
		mapOptions = {
          center: {lat: 23.6, lng: 121},
          zoom: 8,
          maxZoom: 16
        };
	    map = new google.maps.Map(document.getElementById('map'), mapOptions);
	    var keyArray = Object.keys(landscape);
		for(var i=0;i<keyArray.length;i++){
			var object = landscape[keyArray[i]];
			var myLatLng = new google.maps.LatLng(object.lat,object.lng);
			marker = new google.maps.Marker({ 
		       position: myLatLng,
		       title:keyArray[i],
		       map:map
			})
			markers[keyArray[i]]=marker;
			//extend the bounds to include each marker's position
			bounds.extend(marker.position);
			//now fit the map to the newly inclusive bounds
			map.fitBounds(bounds);
			marker.setMap(map);
		}
	}
   	/**************************** jQuery ********************************/
   	$(document).ready(function(){
   		var infowindow
   		$(".landName").mouseenter(function() {
   			$(this).css('color','orange');
   			$(this).css('cursor','pointer');
   			infowindow = new google.maps.InfoWindow({
   			    content: $(this).text()
   			});
   			var marker = markers[$(this).text()];
   			infowindow.open(map, marker);
     		marker.setAnimation(google.maps.Animation.BOUNCE);
   		}).mouseleave(function() {
   			$(this).css('color','#000');
   			$(this).css('cursor','');
   			var marker = markers[$(this).text()];
   			marker.setAnimation(null);
   			infowindow.close();
   		});
   		var landscapeLat;
   		var landscapeLng;
   		
   		$(".landName").click(function(){
   			var landscapeNo = $(this).attr('id');
   			var landscapeName = $(this).text();
   			$.ajax({
				 type: "GET",
				 url: "ItineraryServlet.do",
 				 data: creatQueryString(landscapeNo),
				 dataType: "json",
				 success: function (data){
					 clearSelect();
					 if(data!=''){
						 $(data).each(function(i, item){
							$('#landscapeAllPic').append('<img src=PictureReader.do?pic_no='+item.pic_no+" style='width:720px;height:auto'><br>");
							landscapeLat = item.landscape_lat;
							landscapeLng = item.landscape_lng;
							$('#landscapeModalLabel').html('<i class="fa fa-map-marker" style="font-size: 24px;color:#dd725a"></i> '+item.landscape_name);
							console.log(item.pic_no);				
							console.log(landscapeLat);
							console.log(landscapeLng);
	 					}); 
					 }else{
						   $('#landscapeModalLabel').html('<i class="fa fa-map-marker" style="font-size: 24px;color:#dd725a"></i> '+landscapeName);
						   $('#landscapeAllPic').append("尚未有人上傳景點照片");
					 }
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
   		});
   		
   		function clearSelect(){
			$('#landscapeAllPic').empty();
		};
		
		function creatQueryString(landscapeNo){
			var queryString= {"action":"landscapeAll","landscapeNo":landscapeNo};
			return queryString;
		};
   	});

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANKHSWauEfGG_DUy-MEfvdocMH1e1KrMI&callback=initMap"
    async defer></script>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 			crossorigin="anonymous"></script> -->
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