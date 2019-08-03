
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
<jsp:useBean id="guideTourSvc" scope="page" class="com.guidetour.model.GuideTourService" />
<jsp:useBean id="resOrderSvc" scope="page" class="com.resorder.model.ResOrderService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />    
<jsp:useBean id="guideSvc" scope="page" class="com.guide.model.GuideService" />

<%
GuideVO guideVO = (GuideVO)session.getAttribute("guideVO");
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
	<link href='../guidetour/fullcalendar/core/main.css' rel='stylesheet' />
	<link href='../guidetour/fullcalendar/daygrid/main.css' rel='stylesheet' />
	<script src='../guidetour/fullcalendar/core/main.js'></script>
	<script src='../guidetour/fullcalendar/interaction/main.js'></script>
	<script src='../guidetour/fullcalendar/daygrid/main.js'></script>
	<script src='../guidetour/fullcalendar/core/locales-all.js'></script>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    
    <title>預約狀況</title>
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
			margin-left:160px;
			letter-spacing: 1.5px;
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

    	.error{
    		height:30px;
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
		.title{
			font-family:monospace;
			font-size:23px;
			letter-spacing: 2px;
			font-weight:bold;
			color:#666;
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
		.tooltip-inner{
			padding:8px 15px;
			font-family: Microsoft JhengHei;
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
  <body  onload="onload()" onunload="disconnect()">
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
				<i class="fa fa-comments" style="color:#fff;font-size:40px" title="誰聯絡我" id="openChat" onclick="openChat()"></i>
			</div>
		</div>
	</div>
	
		<div class="row justify-content-center content">
			<div class="col-2">
	
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
			<div class="col-6 right">
			<p class="title">- 預 約 狀 況 -</p>
	 			 <div id='calendar' style="position:relative">
	 			 	<div style="position:absolute;display:flex;top:10px;left:200px">
		 			 	<div class="textOnCal">
						<font style="color:#c5dec4">●</font>訂單未完成 &nbsp&nbsp&nbsp
						<font style="color:#e1b79d">●</font>訂單已完成
						</div>
					</div>
	 			 </div>	   
				
			</div>
		
	</div>
</div>

 <script type="text/javascript">
 var calendars = {};
 document.addEventListener('DOMContentLoaded', function() {
     var calendarEl = document.getElementById('calendar');
     var minDate = new Date();
     var calendar = new FullCalendar.Calendar(calendarEl, {
       plugins: ['interaction', 'dayGrid'],
       locale:'zh-tw',
       eventLimit: true,
       eventRender: function(info) {
    	   	  $(info.el).tooltip({
    	   		  title: info.event.extendedProps.description,
    	          placement: 'top',
    	          trigger: 'hover',
    	          container: 'body'
    	   	  })
    	    },
	   events:[
		   <c:forEach var="resOrderVO" items="${resOrderSvc.getGuideIng(guideVO.guide_no)}" varStatus="status">
		   	   {
			      title:'${memSvc.getOneMember(resOrderVO.mem_no).mem_name}'+' '+'${resOrderVO.resOrder_peo}'+'人',
			   	  start:'${resOrderVO.resOrder_date}',
			   	  url:'../guidetour/GuideTourDetail.jsp?guideTour_no='+'${resOrderVO.guideTour_no}'+'&guide_no='+'${resOrderVO.guide_no}',
			   	  backgroundColor:'#c5dec4',
			   	  borderColor:'#c5dec4',
			   	  textColor:'#111',
			   	  description:'${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}'
			   }, 
		   </c:forEach>
		   <c:forEach var="resOrderVO" items="${resOrderSvc.getGuideFinish(guideVO.guide_no)}" varStatus="status">
		   <c:if test="${status.last}">
			   {
			      title:'${memSvc.getOneMember(resOrderVO.mem_no).mem_name}'+' '+'${resOrderVO.resOrder_peo}'+'人',
			   	  start:'${resOrderVO.resOrder_date}',
			   	  url:'../guidetour/GuideTourDetail.jsp?guideTour_no='+'${resOrderVO.guideTour_no}'+'&guide_no='+'${resOrderVO.guide_no}',
			   	  backgroundColor:'#e1b79d',
			   	  borderColor:'#e1b79d',
			   	  textColor:'#111',
			   	  description:'${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}'
			   } 
		   </c:if>
		   <c:if test="${not status.last}">
			   {
				  title:'${memSvc.getOneMember(resOrderVO.mem_no).mem_name}'+' '+'${resOrderVO.resOrder_peo}'+'人',
			   	  start:'${resOrderVO.resOrder_date}',
			   	  url:'../guidetour/GuideTourDetail.jsp?guideTour_no='+'${resOrderVO.guideTour_no}'+'&guide_no='+'${resOrderVO.guide_no}',
			   	  backgroundColor:'#e1b79d',
			   	  borderColor:'#e1b79d',
			   	  textColor:'#111',
			   	  description:'${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}'
			   },
		   </c:if>
		   </c:forEach>
	   ]
     });
     calendars.calendar = calendar;
     calendar.render();

   });
   /***************************************************************************/
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

 </script>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
 
  </body>
</html>