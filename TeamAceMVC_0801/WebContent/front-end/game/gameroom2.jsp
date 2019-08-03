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
	width: 150px;
	height: 230px;
}
</style>
</head>
<% MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); %>
<body onload="connect();" onunload="disconnect();">

<!-- <h3 id="statusOutput" class="statusOutput"></h3> -->
<!-- 	<div class="container-fuild"> -->
<!-- 		<div class="d-flex justify-content-around"> -->
<!-- 			<div class="p-4 row align-items-start"> -->
<!-- 			 	<div class=".col"> -->
<!-- 				<img -->
<%-- 					src="<%=request.getContextPath()%>/front-end/home/images/2.png" --%>
<!-- 					alt=""> <img -->
<%-- 					src="<%=request.getContextPath()%>/front-end/home/images/3.png" --%>
<!-- 					alt=""> <img -->
<%-- 					src="<%=request.getContextPath()%>/front-end/home/images/4.png" --%>
<!-- 					alt=""> -->
<!-- 					</div> -->
<!-- 			</div> -->
<!-- 			<div class="p-4"> -->
<!-- 				<h1>GAME Room</h1> -->
<!-- 				<textarea id="messagesArea" class="panel message-area" readonly></textarea> -->
<!-- 				<div class="panel input-area"> -->
<!-- 					<input id="userName" class="text-field" type="text" -->
<!-- 						placeholder="User name" /> <input id="message" class="text-field" -->
<!-- 						type="text" placeholder="Message" -->
<!-- 						onkeydown="if (event.keyCode == 13) sendMessage();" /> <input -->
<!-- 						type="submit" id="sendMessage" class="button" value="Send" -->
<!-- 						onclick="sendMessage();" /> <input type="button" id="connect" -->
<!-- 						class="button" value="Connect" onclick="connect();" /> <input -->
<!-- 						type="button" id="disconnect" class="button" value="Disconnect" -->
<!-- 						onclick="disconnect();" /> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="p-4"> -->
<!-- 				<img -->
<%-- 					src="<%=request.getContextPath()%>/front-end/home/images/5.png" --%>
<!-- 					alt=""> <img -->
<%-- 					src="<%=request.getContextPath()%>/front-end/home/images/6.png" --%>
<!-- 					alt=""> <img -->
<%-- 					src="<%=request.getContextPath()%>/front-end/home/images/7.png" --%>
<!-- 					alt=""> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

<div class="container">
  <div class="row align-items-start ">
    <div class="col">
      <img
					src="<%=request.getContextPath()%>/front-end/home/images/2.png"
					alt="">
    </div>
    <div class="col">
   
    </div>
     <div class="col">
   
    </div>
    <div class="col">
      <img
					src="<%=request.getContextPath()%>/front-end/home/images/5.png"
					alt="">
    </div>
  </div>
  <div class="row align-items-center">
    <div class="col">
      <img
					src="<%=request.getContextPath()%>/front-end/home/images/3.png"
					alt="">
    </div>
    <div class="col">
      <h1>GAME Room</h1>
				<textarea id="messagesArea" class="panel message-area" readonly></textarea>
    </div>
     <div class="col">
   
    </div>
    <div class="col">
      <img
					src="<%=request.getContextPath()%>/front-end/home/images/6.png"
					alt="">
    </div>
  </div>
   
  <div class="row align-items-end">
    <div class="col">
      <img
					src="<%=request.getContextPath()%>/front-end/home/images/4.png"
					alt="">

					
			
    </div>
    <div class="col">
      <input id="userName" class="text-field" type="text"
						placeholder="User name"  value="${memberVO.mem_name}"/> <input id="message" class="text-field"
						type="text" placeholder="Message"
						onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
						type="submit" id="sendMessage" class="button" value="Send"
						onclick="sendMessage();" /> <input type="button" id="connect"
						class="button" value="Connect" onclick="connect();" /> <input
						type="button" id="disconnect" class="button" value="Disconnect"
						onclick="disconnect();" />
    </div>
     <div class="col">
   
    </div>
    <div class="col">
       <img
					src="<%=request.getContextPath()%>/front-end/home/images/7.png"
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
		   <% 
			MemberService memSrc = 	new	 MemberService();
			byte[] bytes =  memSrc.getOneMember(memberVO.getMem_no()).getMem_pic();	
			Base64.Encoder encoder = Base64.getEncoder();
			//編碼
			String encodedText = encoder.encodeToString(bytes);
			pageContext.setAttribute("encodedText",encodedText);		
			%>
			<td><img src="data:image/jpeg;base64,<%=encodedText%>" style="width:100px;height:100px; border:1px dotted red"></td>			
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
		alert(6);
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
// 			updateStatus("WebSocket Connected");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
			sendMessage();
		};

		webSocket.onmessage = function(event) {
			alert(1)
			var messagesArea = document.getElementById("messagesArea");
			alert(2)
			var jsonObj = JSON.parse(event.data);
			alert(3)
			var message = jsonObj.userName + ": " + jsonObj.message + ": "+  jsonObj.userNo +"\r\n";
			alert(4)
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
			alert(jsonObj.userNo)
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket Disconnected");
		};
	}

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
		var message = inputMessage.value.trim()+'1';

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else {

			var jsonObj = {
				"userName" : userName,
				"message" : message,
				"userNo": '${memberVO.mem_no}'
			};
			alert(12)
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
</script>
</html>
