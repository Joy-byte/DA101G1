<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="com.guide.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<jsp:useBean id="guideTourSvc" scope="page" class="com.guidetour.model.GuideTourService" />
<jsp:useBean id="guideSvc" scope="page" class="com.guide.model.GuideService" />
<jsp:useBean id="gtListSvc" scope="page" class="com.gtlist.model.GTListService" />
<jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" />
<jsp:useBean id="gtcListSvc" scope="page" class="com.gtclist.model.GTCListService" />

<%
MemberVO memVO = (MemberVO)session.getAttribute("memberVO");

//生日算年齡
Date birth = memVO.getMem_birthday();
LocalDate birthday = birth.toLocalDate();
LocalDate today = LocalDate.now();
long age = ChronoUnit.YEARS.between(birthday,today);

//生日算星座
int[] dayArray = new int[] {20,19,21,20,21,22,23,23,23,24,23,22};
String[] constellations = new String[] {"摩羯座", "水瓶座", "雙魚座", "牡羊座", "金牛座", "雙子座", "巨蟹座", "獅子座", "處女座", "天秤座", "天蠍座", "射手座", "摩羯座" };
int day = birthday.getDayOfMonth();
int month = birthday.getMonthValue();
	String user=null;
    if(day < dayArray[month - 1]) {
    	user = constellations[month - 1];
    } else{
    	user = constellations[month];
    }
%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	 <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <title>伴遊行程</title>
    <style type="text/css">
    	body{
    		font-family: Microsoft JhengHei;

    	}

		.top{
			padding-top: 10%;
			color:#fff;
		}
		.info{
 			padding-top: 20px;
			letter-spacing: 4px;
		}
    	.content{
    		margin-top: 4%;
    		padding:80px;
			background-color: #fff;
    	}
		.right{
			margin-left:50px;
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
			margin: 0px 0px 0 230px;
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
			letter-spacing:2px;
		}
		
    </style>
  </head>
  <body>
  <%@ include file="/front-end/header.file" %>
  
<div class="outer">
	<img src="ReadPicture2?mem_no=${memberVO.mem_no}&type=guide_cover" id="cover">
	<div class="container">
		<div class="row justify-content-center top">
			<div class="col-6">
				<img id=guide_pic src="ReadPicture2?mem_no=${memberVO.mem_no}&type=guide_pic">
			</div>
			<div class="col-5 info">
				<p><font style="font-size: 22px;font-weight:bold;">${memberVO.mem_name}</font></p>
				<font style="font-size: 18px;">
				<p>${guideVO.guide_area} &nbsp<%=age%>歲 &nbsp<%=user%></p><br>
				<p>已發布 ${guideTourSvc.getUploadNum(guideVO.guide_no)}</p>
				<p>伴遊數 ${guideTourSvc.getFinishNum(guideVO.guide_no)}</p>
				</font>
				<i class="fa fa-comments" style="color:#fff;font-size:40px" title="誰聯絡我" id="openChat" ></i>
			</div>
		</div>
	</div>
	
		<div class="row justify-content-center content">
			<div class="col-2" style="margin-left:185px;">
	
				<div class="list-group list-group-flush">
					<div class="list-group-item" style="font-family:monospace;font-size: 18px;"><b>LOCALS LIST</b></div>
					<a href="GuideUpload.jsp" class="list-group-item">發布伴遊行程</a>
					<a href="GuideTour.jsp" class="list-group-item">我的伴遊行程</a>
					<a href="GuideMainPage.jsp" class="list-group-item">我的伴遊資訊</a>
					<a href="GuideUpdate.jsp" class="list-group-item">修改伴遊資訊</a>
					<a href="GuideResOrder.jsp" class="list-group-item">伴遊預約訂單</a>
					<a href="GuideCalendar.jsp" class="list-group-item">伴遊預約狀況</a>
					<a class="list-group-item"></a>
				</div>
			</div>
			<div class="col-8 right">
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
								<div class="btn-update" style="margin:0 0 10px 20px;display:flex">
									<form action="GuideTourUpdate.jsp">
										<input type="hidden" name="guideTour_no" value="${guideTourVO.guideTour_no}">
										<button type="submit" class="btn btn-dark btn-sm" style="letter-spacing:2px;">編輯</button>
									</form>
									<form action="GuideTour.do" method="post">
										<input type="hidden" name="guideTour_no" value="${guideTourVO.guideTour_no}">
										<input type="hidden" name="action" value="delete">
										<button type="submit" class="btn btn-dark btn-sm" style="letter-spacing:2px;margin-left:15px">刪除</button>
									</form>
								</div>
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
									<c:forEach var="gtcListVO" items="${gtcListSvc.getCategoryByGTno(guideTourVO.guideTour_no)}">
										<span
											style="margin: 0 2px; padding: 4px; background: #70c7be; border-radius: 3px 3px; color: #fff; font-size: 13px;">
											#${gtcListVO.category}
										</span>
									</c:forEach>
								</div>
								<form METHOD="get" ACTION="../guidetour/GuideTour.do" name="more">
									<input type="hidden" name="action" value="readMore">
									<input type="hidden" name="guideTour_no" value="${guideTourVO.guideTour_no}"> 
									<input type='hidden' name='guide_no' value="${guideTourVO.guide_no}">
									<input class="more" type="submit" value="MORE">
								</form>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
		
	</div>
</div>




    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>