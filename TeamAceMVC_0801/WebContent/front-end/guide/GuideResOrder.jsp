<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="com.guide.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ page import="com.oneononechat.controller.JedisMessageAction"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="resOrderSvc" scope="page" class="com.resorder.model.ResOrderService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />    
<jsp:useBean id="guideSvc" scope="page" class="com.guide.model.GuideService" />
<jsp:useBean id="guideTourSvc" scope="page" class="com.guidetour.model.GuideTourService" />
<%
MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
//ChatRoom
String memNo = memVO.getMem_no();
Set<String> chatMemberSet = JedisMessageAction.getChatMember(memNo);
pageContext.setAttribute("chatMemberSet",chatMemberSet);
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
    <title>伴遊預約訂單</title>
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
			min-height:1000px;
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
		.order{
			position: relative;
			height:270px;
			width:770px;
			background-color: #fff;
			border-radius: 0.5em;
			overflow:hidden;
			display: flex;
			font-size: 15px;
			border:1px solid #ccc;
			margin-top:20px;
		}
		.guidepic-box{
			position: relative;
			height:50px;
			width: 50px;
			border-radius:50%;
			overflow: hidden;
		}
		.guidepic{
			width: 50px;
			height: 50px;
		}
		.guide-box{
			display: flex;
			align-items: center;
			margin:15px 20px 15px 40px;

		}
		.info-bottom{
			margin: 10px 20px 0px 20px;
			padding-top: 10px;
			display: flex;
			border-top:1px solid #ddd;
		}
		.info-right{
			width: 320px;
			margin-left: 40px;
			line-height: 24px;
			display: flex;
		}
		.info-left{
			padding-left:20px;
			line-height: 24px;
			width: 370px;
			display: flex;
			/*margin-left:20px;*/
		}
		.info-up{
			line-height: 25px;
		}
		.up-right{
			position:absolute;
			margin-left:51%;
		}
		.button{
			position:absolute;
			right:20px;
			bottom:15px;
			display: flex;
		}
		.msg{
			margin:10px 0px 15px 35px;
			width: 350px;
			height:80px;
			border-radius:0.5em;
			padding:6px 10px 6px 10px;
			/*border:1px solid #ccc;*/
			background-color: #F5F5F5;
		}
		.reason{
			margin:10px 0px 15px 35px;
			width: 200px;
			height:80px;
			border-radius:0.5em;
			padding:6px 10px 6px 10px;
			color:crimson;
		}
		.fa-comments:hover{
			cursor:pointer;
		}
		.chatBefore:hover{
    		cursor:pointer;
    	}
    	.closeRoom:hover{
    		cursor:pointer;
    	}
    </style>
  </head>
  <body onload="onload()" onunload="disconnect()">
  <%@ include file="/front-end/header.file" %>
  
  <!-- Chat room -->
  	<div style="display:none;width:730;position:fixed;right:0;bottom:0;z-index:9999" id="chatRoom">
		<div style="width:230px;height:400px;border:1px solid #ccc;background:#fff;text-align:center" id="chatMemBefore">
				<div style="width:100%;text-align:center;border-bottom:1px solid #ccc;padding:10px 0 10px 0 ">最 近 密 聊</div>
				<c:forEach var="chatMember" items="${chatMemberSet}">
					<input type="hidden" value="${chatMember}" class="chatMemNo">
					<input type="hidden" value="${memSvc.getOneMember(chatMember).mem_name}">
					<div class="chatBefore" style="display:flex;align-items:center;margin:0 10px 0 10px;padding:10px;" onclick="changeRoom('${chatMember}',$(this).prev().val())">
						<img width="50px" height="50px" src="ReadMemPic?mem_no=${chatMember}" id="${chatMember}" style="border-radius: 999em;"> 
						<span style="margin-left:20px;">${memSvc.getOneMember(chatMember).mem_name}</span> &nbsp<span id="online${chatMember}" style="color:lightgreen;font-size:12px"></span>
					</div>	
				</c:forEach>
		</div>
		<div style="width:500px;height:400px;position:relative;background:#fff">
			<span class="closeRoom" onclick="$('#chatRoom').hide()" style="position:absolute;top:5px;right:10px;font-size:22px;color:gray">×</span>
			<div style="padding:10px 0 10px 20px;background:#cedee8;height:70px;display:flex" id="chatRoomHead">
	<!-- 			<img width="50px" height="50px" src="ReadMemPic?mem_no=MB00003" style="border-radius: 999em;"> -->
	<!-- 			<div style="display:flex;align-items:center;justify-content:center;padding:1rem;font-size:16px">Mem3</div> -->
			</div>
			<div id="messagesArea" class="panel message-area" style="
				margin: 0px; 
		 		height: 260px;
				border:0;
				display: flex;
				padding: 1rem;
				border-style:solid;
				border-radius: 8px 8px 8px 8px;
				background:#fff;
				flex-direction: column;
				overflow-y: auto;">
			</div>
			<div class="panel input-area" style="width:100%;margin-top:10px">	
				<input type="hidden" id="userName" class="text-field" type="text" placeholder="User name" value="${memberVO.mem_name}"/> 
				<div class="row" style="margin-left:10px">
					<input id="message" class="text-field" type="text" placeholder="請輸入訊息" onkeydown="if (event.keyCode == 13) sendMessage();" style="
					float: left;
				    height: 40px;
				    border: none;
				    padding: 0.5rem 1rem;
				    border-radius: 40px;
				    margin: 0 0.5rem 0 0;
				    width: 420px;
				    background-color: #dedfed;
				    box-shadow: inset 0 0 0 2px #dedfed;
				    font-size: 14px;
				    font-weight: bold;
				    transition: 0.15s all ease-in-out;
				    box-sizing: border-box;"/>
					<div type="submit" id="sendMessage" onclick="sendMessage();" style="
						margin-right:15px;
						float: right;
					    width: 40px;
					    border-radius: 30px;
					    user-select: none;">
				    	<img width="40px"height="40px" src="image/icon.jpeg">
					</div>
				</div>
		
			</div>
		</div>
	</div>
  
  
  	<!-- Modal cancel -->
	<div class="modal fade" id="exampleModalCancel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h6 class="modal-title" id="exampleModalCenterTitle">取消原因</h6>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form action="ResOrderServlet" method="post">
	      <div class="modal-body">
	        <div class="input-group">
	          <span id="error_cancel" style="font-size:10px;color:red;"></span><br>
			  <textarea class="form-control" name="resOrder_cancel" aria-label="With textarea" rows="8" placeholder="上限500字" style="margin:15px;"></textarea>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" style="letter-spacing:2px">關閉</button>
	       
	        <input type="hidden" name="cancel" id="cancel">
	        <button type="submit" class="btn btn-primary btn-sm" style="letter-spacing:2px">送出</button>
	      </div>
	      <input type="hidden" name="action" value="update">
	      <input type="hidden" name="type" value="cancel">
	      <input type="hidden" name="from" value="guide">
	      <input type="hidden" name="resOrder_no" id="cancelResOrderNo">
	      </form>
	    </div>
	  </div>
	</div>
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
				<i class="fa fa-comments" style="color:#fff;font-size:40px" onclick="openChat()" title="誰聯絡我" id="openChat" ></i>
			</div>
		</div>
	</div>
	
		<div class="row justify-content-center content" style="letter-spacing:2px;">
			<div class="col-2">
	
				<div class="list-group list-group-flush">
					<div class="list-group-item" style="font-family:monospace;font-size: 18px;"><b>LOCALS LIST</b></div>
					<a href="GuideUpload.jsp" class="list-group-item">發布伴遊行程</a>
					<a href="GuideTour.jsp" class="list-group-item">我的伴遊行程</a>
					<a href="GuideMainPage.jsp" class="list-group-item">我的伴遊資訊</a>
					<a href="GuideUpdate.jsp" class="list-group-item">修改伴遊資訊</a>
					<a href="GuideResOrder.jsp" class="list-group-item">伴遊預約訂單</a>
					<a href="GuideCalendar.jsp" class="list-group-item">伴遊預約狀況</a>
<!-- 					<a class="list-group-item"></a> -->
				</div>
				<div class="btn-group" style="margin-top:20px">
					<input type="date" class="form-control" placeholder="search" id="dateInput">
					<button class="btn btn-secondary" id="dateSearch"><i class="fa fa-search"></i></button>		
				</div>
			</div>
			<div class="col-6 right">
				<nav>
				   <div class="nav nav-tabs" id="nav-tab" role="tablist">
				     <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">預約中</a>
				     <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">已完成</a>
				     <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">取消</a>
				   </div>
				</nav>
				<!-- 預約中 -->
				<div class="tab-content" id="nav-tabContent">
				   <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				  		<c:forEach var="resOrderVO" items="${resOrderSvc.getGuideIng(guideVO.guide_no)}" varStatus="status">
						<div class="order">
							<div class="info-box">				
								<div class="info-up">
									<div class="guide-box">
										<div class="guidepic-box">
											<!-- 旅客會員頭貼 -->
											<img src="ReadMemPic?mem_no=${resOrderVO.mem_no}" class="guidepic">
										</div>
										<!-- 旅客名 -->
										<span style="margin-left:20px;margin-right:35px;font-size: 16px"><b>${memSvc.getOneMember(resOrderVO.mem_no).mem_name}</b></span>
										<button type="submit" class="btn btn-info btn-sm" style="letter-spacing: 2px;position:absolute;margin-left:35%;">聯絡他</button>
										<div class="up-right">
											<b>電話：</b> ${memSvc.getOneMember(resOrderVO.mem_no).mem_tel}<br>
											<b>E-mail:</b> ${memSvc.getOneMember(resOrderVO.mem_no).mem_email}<br>
										</div>	
									</div>	
								</div>
								
								<div class="info-bottom">
									<div class="info-left">
										<div class="left-left" style="width:90px">
											<b>下訂時間：</b><br>
											<b>伴遊行程：</b><br>
										</div>
										<div class="left-right">
											${resOrderVO.resOrder_time}<br>
											<form id="form-tour${status.index}" action="../guidetour/GuideTourDetail.jsp">
												<!-- 行程名 -->
												<input type="hidden" name="action" value="readMore">
												<input type="hidden" name="guideTour_no" value="${resOrderVO.guideTour_no}">
												<a href="#" onclick="document.getElementById('form-tour${status.index}').submit()">【${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}】</a>
											</form>
										</div>
										 
									</div>
									<div class="info-right">
										<div class="right-left" style="width: 90px">
											<b>預約日期：</b><br>
											<b>預約人數：</b><br>
											<b>是否共遊：</b><br>
										</div>
										<div class="right-right">
											${resOrderVO.resOrder_date}<br>
											${resOrderVO.resOrder_peo}<br>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ1'}">否</c:if>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ2'}">是</c:if>
											<br>
										</div>
									</div>
								</div>
								<div class="msg" id="test123">
									留言：${resOrderVO.resOrder_msg}	
								</div>
							</div>
								<div class="button">
									<button class="btn btn-secondary btn-sm" style="letter-spacing: 2px;margin-right:15px;" disabled>未完成</button>
									<button type="button" class="btn btn-secondary btn-sm" style="letter-spacing: 2px;" data-toggle="modal" data-target="#exampleModalCancel" style="letter-spacing: 2px;" onclick="sendCancelValue('${resOrderVO.resOrder_no}')">取消</button>
								</div>
						</div>
						</c:forEach>
				   </div>
				   <!-- 已完成 -->
				   <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
				       	<c:forEach var="resOrderVO" items="${resOrderSvc.getGuideFinish(guideVO.guide_no)}" varStatus="status">
						<div class="order">
							<div class="info-box">				
								<div class="info-up">
									<div class="guide-box">
										<div class="guidepic-box">
											<!-- 旅客會員頭貼 -->
											<img src="ReadMemPic?mem_no=${resOrderVO.mem_no}" class="guidepic">
										</div>
										<!-- 旅客名 -->
										<span style="margin-left:20px;margin-right:35px;font-size: 16px"><b>${memSvc.getOneMember(resOrderVO.mem_no).mem_name}</b></span>
										<button type="submit" class="btn btn-info btn-sm" style="letter-spacing: 2px;position:absolute;margin-left:35%;">聯絡他</button>
										<div class="up-right">
											<b>電話：</b> ${memSvc.getOneMember(resOrderVO.mem_no).mem_tel}<br>
											<b>E-mail:</b> ${memSvc.getOneMember(resOrderVO.mem_no).mem_email}<br>
										</div>	
									</div>	
								</div>
	
								<div class="info-bottom">
									<div class="info-left">
										<div class="left-left" style="width:90px">
											<b>下訂時間：</b><br>
											<b>伴遊行程：</b><br>
										</div>
										<div class="left-right">
											${resOrderVO.resOrder_time}<br>
											<form id="form-complete${status.index}" action="../guidetour/GuideTourDetail.jsp">
												<!-- 行程名 -->
												<input type="hidden" name="action" value="readMore">
												<input type="hidden" name="guideTour_no" value="${resOrderVO.guideTour_no}">
												<a href="#" onclick="document.getElementById('form-complete${status.index}').submit()">【${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}】</a>
											</form>
										</div>
										 
									</div>
									<div class="info-right">
										<div class="right-left" style="width: 90px">
											<b>預約日期：</b><br>
											<b>預約人數：</b><br>
											<b>是否共遊：</b><br>
										</div>
										<div class="right-right">
											${resOrderVO.resOrder_date}<br>
											${resOrderVO.resOrder_peo}<br>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ1'}">否</c:if>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ2'}">是</c:if>
											<br>
										</div>
									</div>
								</div>
								<div class="msg">
									留言：${resOrderVO.resOrder_msg}	
								</div>
							</div>
							
							<div class="button">
						
								<button class="btn btn-secondary btn-sm" style="letter-spacing: 2px;margin-right:15px;" disabled>已完成</button>
								
								<!-- Button trigger modal -->
								<c:if test="${(not empty resOrderVO.resOrder_review) or resOrderVO.resOrder_rate!=0}">
									<button type="button" class="btn btn-secondary btn-sm" style="letter-spacing: 2px;" disabled>已評價</button>
								</c:if>
								<c:if test="${(empty resOrderVO.resOrder_rate) or resOrderVO.resOrder_rate==0 }">
									<button type="button" class="btn btn-secondary btn-sm" style="letter-spacing: 2px;" disabled>旅客未評價</button>
								</c:if>
								
							</div>
						</div>
						</c:forEach>	
				   </div>
				   <!-- 取消 -->
				   <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
				       <c:forEach var="resOrderVO" items="${resOrderSvc.getGuideCancel(guideVO.guide_no)}" varStatus="status">
						<div class="order">
							<div class="info-box">				
								<div class="info-up">
									<div class="guide-box">
										<div class="guidepic-box">
											<!-- 旅客頭貼 -->
											<img src="ReadMemPic?mem_no=${resOrderVO.mem_no}" class="guidepic">
										</div>
										<!-- 旅客名 -->
										<span style="margin-left:20px;margin-right:35px;font-size: 16px"><b>${memSvc.getOneMember(resOrderVO.mem_no).mem_name}</b></span>
										<button type="submit" class="btn btn-info btn-sm" style="letter-spacing: 2px;position:absolute;margin-left:35%;">聯絡他</button>
										<div class="up-right">
											<b>電話：</b> ${memSvc.getOneMember(resOrderVO.mem_no).mem_tel}<br>
											<b>E-mail:</b> ${memSvc.getOneMember(resOrderVO.mem_no).mem_email}<br>
										</div>	
									</div>	
								</div>
	
								<div class="info-bottom">
									<div class="info-left">
										<div class="left-left" style="width: 90px">
											<b>下訂時間：</b><br>
											<b>伴遊行程：</b><br>
										</div>
										<div class="left-right">
											${resOrderVO.resOrder_time}<br>
											<form id="form-cancel${status.index}" action="../guidetour/GuideTourDetail.jsp">
												<!-- 行程名 -->
												<input type="hidden" name="action" value="readMore">
												<input type="hidden" name="guideTour_no" value="${resOrderVO.guideTour_no}">
												<a href="#" onclick="document.getElementById('form-cancel${status.index}').submit()">【${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}】</a>
											</form>
										</div>
										 
									</div>
									<div class="info-right">
										<div class="right-left" style="width: 90px">
											<b>預約日期：</b><br>
											<b>預約人數：</b><br>
											<b>是否共遊：</b><br>
										</div>
										<div class="right-right">
											${resOrderVO.resOrder_date}<br>
											${resOrderVO.resOrder_peo}<br>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ1'}">否</c:if>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ2'}">是</c:if>
											<br>
										</div>
									</div>
								</div>
								<div class="msg-box" style="display: flex">
									<div class="msg">
										留言：${resOrderVO.resOrder_msg}			
									</div>
									<div class="reason">
										取消原因：${resOrderVO.resOrder_cancel}			
									</div>
								</div>
							</div>
							
							<div class="button">
									<button class="btn btn-secondary btn-sm" style="letter-spacing: 2px;" disabled>已取消</button>
							</div>
						</div>
						</c:forEach>
				   </div>
				</div>
		
			</div>
		</div>
	<script>
	var webSocketChat;
	var receiver;
	function onload(){
		
		//WebSocket
		var MyPoint = "/ChatRoomWS/${memberVO.mem_no}";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		// 建立 websocket 物件
		webSocketChat = new WebSocket(endPointURL);
		
		webSocketChat.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;
			//抓已上線的會員
			var onlineStr = {"action":"getOnline"};
			webSocketChat.send(JSON.stringify(onlineStr));
		};
		
		webSocketChat.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message;
	        //一開始抓會員上線
	        if(jsonObj.action == 'getOnline'){
	        	var onlineArray = JSON.parse('['+jsonObj.onlineArray+']');
	        	var chatMemArray = document.getElementsByClassName('chatMemNo');
	        	for(var i=0;i<chatMemArray.length;i++){
	        		for(var j=0;j<onlineArray.length;j++){
	        			var onlineObj = onlineArray[j];
	        			if(onlineObj.memNo == chatMemArray[i].value){
		        			$('#online'+onlineObj.memNo).text("●");
		        		}
	        		}
	        	}
	        }
	        //會員上線
	        if(jsonObj.action == 'online'){
	        	var onMemNo = jsonObj.memNo;
	        	var chatMemArray = document.getElementsByClassName('chatMemNo');
	        	for(var i=0;i<chatMemArray.length;i++){
	        		if(onMemNo == chatMemArray[i].value){
	        			$('#online'+onMemNo).text("●");
	        		}
	        	}
	        }
	        //會員下線
			if(jsonObj.action == 'offline'){
				var offMemNo = jsonObj.memNo;
				var chatMemArray = document.getElementsByClassName('chatMemNo');
				for(var i=0;i<chatMemArray.length;i++){
	        		if(offMemNo == chatMemArray[i].value){
	        			$('#online'+offMemNo).text("");
	        		}
	        	}
	        }
	        //傳單一msg
	        if(jsonObj.action == 'sendMsg'){
	        	if(jsonObj.sender != "${memberVO.mem_no}"){
	        		if((jsonObj.sender == receiver) && (jsonObj.receiver == "${memberVO.mem_no}")){
	        			message = 
		    	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
		    	        	"<div style='float: left;text-align: left;margin: 0 0 0 1rem;background-color: #f8e0a5;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
		    	        	jsonObj.message + 
		    	        	"<br></div></div>";
		    	        messagesArea.innerHTML = messagesArea.innerHTML + message;
		    	        messagesArea.scrollTop = messagesArea.scrollHeight;	
	        		}
	    	    }
	        	if(jsonObj.sender == "${memberVO.mem_no}"){
	    	        message = 
	    	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
	    	        	"<div style='float: right;text-align: right;margin: 0 1rem 0 0;background-color: #f8e0a5;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
	    	        	jsonObj.message + 
	    	        	"<br></div></div>";
	    	        messagesArea.innerHTML = messagesArea.innerHTML + message;
	    	        messagesArea.scrollTop = messagesArea.scrollHeight;
	    	    }
	        } 
	        //切換聊天室窗
	        if(jsonObj.action == 'changeRoom'){
	        	var historyArray = JSON.parse('['+jsonObj.historyMsg+']');
	        	messagesArea.innerHTML = "";
	        	var message;
	        	for(var i=0;i<historyArray.length;i++){
	        		var historyObj = historyArray[i];
	        		if(historyObj.sender != "${memberVO.mem_no}"){
		    	        message = 
		    	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
		    	        	"<div style='float: left;text-align: left;margin: 0 0 0 1rem;background-color: #f8e0a5;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
		    	        	historyObj.message + 
		    	        	"<br></div></div>";
		    	    }else{
		    	        message = 
		    	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
		    	        	"<div style='float: right;text-align: right;margin: 0 1rem 0 0;background-color: #f8e0a5;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
		    	        	historyObj.message + 
		    	        	"<br></div></div>";
		    	    }
	        		messagesArea.innerHTML = messagesArea.innerHTML + message;
	    	        messagesArea.scrollTop = messagesArea.scrollHeight;
	        	}
	        }
		}
		webSocketChat.onclose = function(event) {
			var messagesArea = document.getElementById("messagesArea");
			var message = 
	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
	        	"<div style='float: right;text-align: right;margin: 0 1rem 0 0;background-color: #ccc;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
	        	您已離線 + 
	        	"<br></div></div>";
			messagesArea.innerHTML = messagesArea.innerHTML + message;
		    messagesArea.scrollTop = messagesArea.scrollHeight;
		    document.getElementById('sendMessage').disabled = true;
		};
	}

	/********************************* 聊天室 *********************************/ 

	function openChat(){
		$('#chatRoom').css('display','flex');
	}

	function sendMessage() {
	    var user = '${memberVO.mem_no}';
	    if (user == ""){
	        alert ("您尚未登入");
			return;
	    }
	    
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message == ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "message" : message,"sender":"${memberVO.mem_no}","receiver":receiver,"action":"sendMsg"};
	        webSocketChat.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}

	function changeRoom(chatMemNo,chatName){
		receiver = chatMemNo;
		$('#chatRoomHead').html('<img width="50px" height="50px" src="ReadMemPic?mem_no='+chatMemNo+'" style="border-radius: 999em;">'
								+'<div style="display:flex;align-items:center;justify-content:center;padding:1rem;font-size:16px">'+chatName+'</div>');
		jsonObj = {"sender":"${memberVO.mem_no}","receiver":chatMemNo,"action":"changeRoom"};
		webSocketChat.send(JSON.stringify(jsonObj));
	}

	function disconnect () {
		webSocketChat.close();
	}
	
	/***********************************************************************/
	
	
		function sendCancelValue(resOrder_no){
			document.getElementById("cancelResOrderNo").value=resOrder_no;
		}
		
		$(document).ready(function(){
			$('#dateSearch').click(function(){
				var searchDate = $('#dateInput').val();
				$.ajax({
					 type: "GET",
					 url: "ResOrderServlet",
					 data: {"action":"dateSearch", "guide_no":"${guideVO.guide_no}","searchDate":searchDate},
					 dataType: "json",
					 success: function (data){
						document.getElementById('nav-home').innerText="";
						$.each(data, function(i, item){
							jqueryAppend(i,item);
						});
				     },
		             error: function(){alert("AJAX-grade發生錯誤囉!")}
		         })
			});
		})
		
		function jqueryAppend(i,item){
			var joinPeo;
			var msg = "";
			if(item.resOrder_msg!=undefined){
				msg=item.resOrder_msg;
			}
			if(item.resOrder_joinpeo=="RJ1"){joinPeo ="否"} else {joinPeo ="是"}
			$('#nav-home').append('<div class="order">'
					+'<div class="info-box">'				
						+'<div class="info-up">'
							+'<div class="guide-box">'
								+'<div class="guidepic-box">'
									+'<img src="ReadMemPic?mem_no='+item.mem_no+'" class="guidepic">'
								+'</div>'
								+'<span style="margin-left:20px;margin-right:35px;font-size: 16px"><b>'+item.mem_name+'</b></span>'
								+'<button type="submit" class="btn btn-info btn-sm" style="letter-spacing: 2px;position:absolute;margin-left:35%;">聯絡他</button>'
								+'<div class="up-right">'
									+'<b>電話：</b> '+item.mem_tel+'<br>'
									+'<b>E-mail:</b> '+item.mem_email+'<br>'
								+'</div>'	
							+'</div>'	
						+'</div>'
						+'<div class="info-bottom">'
							+'<div class="info-left">'
								+'<div class="left-left" style="width:90px">'
									+'<b>下訂時間：</b><br>'
									+'<b>伴遊行程：</b><br>'
								+'</div>'
								+'<div class="left-right">'
									+item.resOrder_time+'<br>'
									+'<form id="form-tour'+i+'" action="../guidetour/GuideTourDetail.jsp">'
										+'<input type="hidden" name="action" value="readMore">'
										+'<input type="hidden" name="guideTour_no" value="'+item.guideTour_no+'">'
										+'<a href="#" onclick="document.getElementById("form-tour'+i+'").submit()">【'+item.guideTour_name+'】</a>'
									+'</form>'
								+'</div>'
							+'</div>'
							+'<div class="info-right">'
								+'<div class="right-left" style="width: 90px">'
									+'<b>預約日期：</b><br>'
									+'<b>預約人數：</b><br>'
									+'<b>是否共遊：</b><br>'
								+'</div>'
								+'<div class="right-right">'
									+item.resOrder_date+'<br>'
									+item.resOrder_peo+'<br>'
									+joinPeo
									+'<br>'
								+'</div>'
							+'</div>'
						+'</div>'
						+'<div class="msg" id="test123">'
							+'留言：'+msg
						+'</div>'
					+'</div>'
					+'<div class="button">'
						+'<button class="btn btn-secondary btn-sm" style="letter-spacing: 2px;margin-right:15px;" disabled>未完成</button>'
						+"<button type='button' class='btn btn-secondary btn-sm' style='letter-spacing: 2px;' data-toggle='modal' data-target='#exampleModalCancel' style='letter-spacing: 2px;' onclick='sendCancelValue(\""+item.resOrder_no+"\")'>取消</button>"
					+'</div></div>'
			);
		}
	</script>



    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>