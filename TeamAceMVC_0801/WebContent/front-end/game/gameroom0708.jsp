<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Chat Room</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- <link rel="stylesheet" href="css/styles.css" type="text/css" /> -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
.message-area {
	height: 500px;
	/* 	WEIGHT:400PX; */
	weight: 400px;
	resize: none;
	box-sizing: border-box;
	overflow: auto;
}

.panel {
	border: 2px solid #0078ae;
	border-radius: 5px;
	width: 150%;
}

img {
	width: 160px;
	height: 200px;
}

#countdown{
	font-size:50px;
}
</style>
</head>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
%>
<body onload="connect();" onunload="disconnect();">

	<div class="container">
		<div class="row align-items-start ">
			<div class="col">
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic1"
					alt="">		
			</div>
			<div class="col"></div>
			<div class="col"></div>
			<div class="col">
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic2"
					alt="">
			</div>
		</div>
		<div class="row align-items-center">
			<div class="col">
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic3"
					alt="">
			</div>
			<div class="col">
				<h1>GAME Room</h1>
				<div id="countdown"></div>
				<textarea id="messagesArea" class="panel message-area" readonly></textarea>
				<input id="userName" class="text-field" type="text"	placeholder="User name" value="${memberVO.mem_name}" />
				<input id="message" class="text-field" type="text" placeholder="Message"
						onkeydown="if (event.keyCode == 13) sendMessage();" /> 
				<input type="submit" id="sendMessage" class="button" value="發送"
					onclick="sendMessage();" /> 
				<input type="button" id="connect" class="button" value="Connect" onclick="connect();" /> 
				<input type="button" id="disconnect" class="button" value="離開"onclick="disconnect();" />
				<input type="button" id="startgame" value="開始"onclick="sendMessage2();" />
			</div>
			<div class="col"></div>
			<div class="col">
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic4"
					alt="">
			</div>
		</div>

		<div class="row align-items-end">
			<div class="col">
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic5"
					alt="">
			</div>
			<div class="col"></div>
			<div class="col"></div>
			<div class="col">
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic6"
					alt="">
			</div>
		</div>
	</div>



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

	<!-- 			MemberService memSrc = 	new	 MemberService(); -->
	<!-- 			byte[] bytes =  memSrc.getOneMember(memberVO.getMem_no()).getMem_pic();	 -->
	<!-- 			Base64.Encoder encoder = Base64.getEncoder(); -->
	<!-- 			//編碼 -->
	<!-- 			String encodedText = encoder.encodeToString(bytes); -->
	<!-- 			pageContext.setAttribute("encodedText",encodedText);		 -->

	<%-- 			<td><img src="data:image/jpeg;base64,<%=encodedText%>" style="width:100px;height:100px; border:1px dotted red"></td>			 --%>
</body>

<script>
	var MyPoint = "/TogetherWS/${memberVO.mem_no}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

	function connect() {
		// create a websocket
		// 		alert(6);
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
			// 			updateStatus("WebSocket Connected");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
			// 			sendMessage();
		};
		var usersLength=0;
		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
			var jsonObj = JSON.parse(event.data);
			var message = jsonObj.userName + ": " + jsonObj.message + ": "+ "\r\n";
			if(jsonObj.userName != undefined){
				messagesArea.value = messagesArea.value + message;
				messagesArea.scrollTop = messagesArea.scrollHeight;
			}
// 			console.log(jsonObj);
			if(jsonObj.users!=null){
				if(usersLength<=jsonObj.users.length){
					usersLength = jsonObj.users.length;
					for( i=0 ; i<usersLength;i++){
						$('#GamePic'+(i+1)).attr("src","MemReader.do?mem_no="+jsonObj.users[i]);
						$('#GamePic'+(i+1)).attr("class",jsonObj.users[i]);
					}
				}else{
						$('.'+jsonObj.user).attr("src","<%=request.getContextPath()%>/NoData/null2.jpg");
				}
			}
			if(jsonObj.isStart==="y"){
				countdownnumber=30;
				startgame();
				
			}
			
			
		};
		
		
		webSocket.onclose = function(event) {
// 			updateStatus("WebSocket Disconnected");
		};
	}

	//倒數計時

		var countdownnumber;
		var countdownid;
		function startgame(){ countdownfunc(); }
		function countdownfunc(){
		 var x=document.getElementById("countdown");
		 x.innerHTML=countdownnumber;
		 if (countdownnumber==0){ 
		  clearTimeout(countdownid);
		 }else{
		  countdownnumber--;
		  if(countdownid){
		   clearTimeout(countdownid);
		  }
		  countdownid=setTimeout(countdownfunc,1000);
		 }
		}
	
	
	//聲音
//		function qwe(){ 
//			alert(123)
//		window.AudioContext = 
//// 			window.AudioContext
//		window.webkitAudioContext;
//		navigator.getUserMedia =
//// 			navigator.getUserMedia
//		navigator.webkitGetUserMedia;
	
//		navigator.getUserMedia(constraints, successCallback, errorCallback);
//		var s = document.getElementById('s');
//		var p = document.getElementById('p');
//		var timer;
//		var context = new AudioContext();
//		navigator.getUserMedia({audio: true}, function(stream) {

//			var microphone = context.createMediaStreamSource(stream);
//			var analyser = context.createAnalyser();
//			microphone.connect(analyser);
//// 			analyser.connect(context.destination);

//			analyser.fftSize = 2048;
//			var bufferLength = analyser.frequencyBinCount;
//			var dataArray = new Uint8Array(analyser.fftSize);
//			//analyser.getByteFrequencyData(dataArray);

//			s.onclick = function(){
//			  clearTimeout(timer);
//			};

//			p.onclick = function(){
//			  update();
//			};

//			update();

//			function update(){
//			  console.log(dataArray);
//			  analyser.getByteFrequencyData(dataArray);
//			  timer = setTimeout(update,20);
//			}
	
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();

	
	function sendMessage() {

		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("Input a user name");
			inputUserName.focus();
			return;
		}

		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();
		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else {

			var jsonObj = {
				"userName" : userName,
				"message" : message,
// 				"isStart": 'y'
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	
	function sendMessage2() {
		var jsonObj = {
			"isStart": 'y'
		};
		webSocket.send(JSON.stringify(jsonObj));
	}

	function disconnect() {
		webSocket.close();
			history.go(-1);
// 		document.getElementById('sendMessage').disabled = true;
// 		document.getElementById('connect').disabled = false;
// 		document.getElementById('disconnect').disabled = true;
	}

// 	function updateStatus(newStatus) {
// 		statusOutput.innerHTML = newStatus;
// 	}
</script>
</html>
