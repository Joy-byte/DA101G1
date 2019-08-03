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
	
<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/common.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/style.css" media="all">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>

body{
	background-image: url("<%=request.getContextPath()%>/front-end/home/images/trip.jpg");
	background-size: cover;
}

.message-area {
	height: 450px;
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

#undercover {
	width: 100%;
	height: 100%;
	background-color: rgba(50, 50, 50, 0.7);
	position: absolute;
	top: 0px;
	left: 0px;
	color: #fff;
	text-align: center;
	font-size: 40px;
	padding-top: 25%;
	box-sizing: border-box;
	z-index: 999;
}

#undercover img{
	width: 50px;
	height: 75px;
}

#b1{
	width: 50px;
	height: 75px;
}

 .category{
        background-color: gray;
        margin-left:10px; 
        width:90px !important;
        height:90px !important;
        overflow: hidden;
  }
    
</style>
</head>

<jsp:useBean id="topicSvc" scope="page" class="com.topic.model.TopicService" />
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
%>
<body onload="connect();" onunload="disconnect();">

	<div class="container">
		<div class="row align-items-start ">
			<div class="col">							
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic1"
					alt="" ><br>																								
				<span id="GameRole1" class="GameRole"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<span id="GameName1"></span>					
			</div>
			<div class="col"></div>
			<div class="col"></div>
			<div class="col">				
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic4"
					alt=""><br>	
<!-- 							<div class="cp-unit cp-unit--l" > -->
<!--                                     <div class="category "> -->
<!--                                         <img src="../home/images/3.png"> -->
<!--                                     </div> -->
<!--                                     <figure class="thumb m-circle"> -->
<%--                                         <span class="bg-morphing js-morphing" data-r="70"><canvas width="100" height="100"></canvas></span> --%>
<!--                                         <span class="photo"> -->
<!--                                             <span class="arrow"></span><img src="../home/images/4.png"  alt=""> -->
<!--                                         </span> -->
<!--                                     </figure> -->
<!--                                     <div class="text m-row m-row--jc--center"> -->
<!--                                     <p> -->
<!--                                         <span class="t-container"> -->
<!--                                             <span>IIIMisJ</span> -->
<!--                                         </span> -->
<!--                                     </p> -->
<!--                                     </div> -->
<!--                                 </a> -->
<!--                             </div> -->
				<span id="GameRole4" class="GameRole"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp				
				<span id="GameName4"></span>
			</div>
		</div>
		<div class="row align-items-center">
			<div class="col">
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic2"alt=""><br>	
				<span id="GameRole2" class="GameRole"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp				
				<span id="GameName2"></span>
			</div>
			<div class="col">
				<h1>GAME Room</h1>
				<div id="countdown"></div>
				<div id="topic"></div>				
				<textarea id="messagesArea" class="panel message-area" readonly></textarea>
				<div id="undercover" style="visibility: hidden">
					<div id="countdown2"></div><br>
					<img src="#" id="GamesPic1"alt="" class="GamesPic1"  >
					<img src="#" id="GamesPic2"alt="" class="GamesPic2">
					<img src="#" id="GamesPic3"alt="" class="GamesPic3">
					<img src="#" id="GamesPic4"alt="" class="GamesPic4">
					<img src="#" id="GamesPic5"alt="" class="GamesPic5">
					<img src="#" id="GamesPic6"alt="" class="GamesPic6">
					<button onclick="underclose()" id="b1">關閉</button>
				</div>
				<input id="userName" class="text-field" type="text"	placeholder="User name" value="${memberVO.mem_name}" />
				<input id="message" class="text-field" type="text" placeholder="Message"
						onkeydown="if (event.keyCode == 13) sendMessage();" /> 
				<input type="submit" id="sendMessage" class="button" value="發送"
					onclick="sendMessage();" /> 
				<input type="button" id="connect" class="button" value="Connect" onclick="connect();" /> 
				<input type="button" id="disconnect" class="button" value="離開"onclick="disconnect();" />
				<input type="button" id="startgame" value="開始"onclick="sendMessage2('${topicSvc.getOneTopic('TN00001').topic_name}');" />
					
			</div>
			<div class="col"></div>
			<div class="col">				
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic5"
					alt=""><br>	
				<span id="GameRole5" class="GameRole"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp				
				<span id="GameName5"></span>
			</div>
		</div>

		<div class="row align-items-end">
			<div class="col">				
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic3"
					alt=""><br>	
				<span id="GameRole3" class="GameRole"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp				
				<span id="GameName3"></span>
			</div>
			<div class="col"></div>
			<div class="col"></div>
			<div class="col">				
				<img src="<%=request.getContextPath()%>/NoData/null2.jpg" id="GamePic6"
					alt=""><br>	
				<span id="GameRole6" class="GameRole"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp				
				<span id="GameName6"></span>
			</div>
		</div>
	</div>



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
	var usersLength=0;
	var mvotes = new Map();
	var role = new Map();
	var lvotes=0;
	var lifemen = new Array();
	var remainlifemen = new Array();
    var endGame=false;
	
	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
			// 			updateStatus("WebSocket Connected");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};
		
		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
			var jsonObj = JSON.parse(event.data);
			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
			
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
						$('#GamesPic'+(i+1)).attr("src","MemReader.do?mem_no="+jsonObj.users[i]);
						$('#GamePic'+(i+1)).attr("class",jsonObj.users[i]);
						$('#GamesPic'+(i+1)).attr("class",'GamePic'+(i+1));
// 						$('#GameName'+(i+1)).text('${memberVO.mem_name}');
					}
					votes = $('#GamesPic1').attr("class");
				}else{
						$('.'+jsonObj.user).attr("src","<%=request.getContextPath()%>/NoData/null2.jpg");
						$('.GamePic'+(i+1)).attr("src","#");
				}
				lifemen = jsonObj.users.slice();
				remainlifemen=jsonObj.users.slice();
			}
// 			console.log(jsonObj.isStart);
			if(jsonObj.isStart==="y"){
// 				console.log(usersLength)
					$('#topic').text(jsonObj.topic);
// 					document.getElementById('topic').innerText=jsonObj.topic;
					countdownnumber=3;
					gameLength=usersLength;
					
					
					role.set('${memberVO.mem_no}',jsonObj.isGood);
// 					console.log(role)
					boderj=0;
					startgame();
// 				console.log(${memberVO.mem_no});					
					for(i=1;i<=6;i++){
						mvotes.set($('#GamesPic'+i).attr("class"),0);
					}
			}
// 			console.log(jsonObj.votes)
			if(jsonObj.votes!=undefined){		
				role.set(jsonObj.mem,jsonObj.roles);
				for (var [key, value] of mvotes.entries()) {
// 					  console.log(key + ' = ' + value);
						 if(jsonObj.votes===key){
							 mvotes.set(key,value+1);
						 }

				}
				
				lvotes++;
// 				console.log(lvotes)
// 				console.log(usersLength)
					console.log(lifemen.length)
					if(lvotes===lifemen.length){
						var maxValue=0;
						var maxKey=null;
							for (var [key, value] of mvotes.entries()) {
	// 							  console.log(key + ' = ' + value);
								  if(value>maxValue){
									  maxValue=value;
									  maxKey=key;
								  }
							}
				
					//顯示角色		
					$('#'+maxKey).next().next().text(role.get($('#'+maxKey).attr('class'))); 			
					lifemen.splice(lifemen.indexOf($('#'+maxKey).attr('class')),1);
					console.log("123"+lifemen);
					role.delete($('#'+maxKey).attr('class'));
					console.log(role);
					if(!Array.from(role.values()).includes('臥底')){
						alert('好人勝利YA');		
						endGame=true;
						console.log('好人勝利YA');
					}
					if(!Array.from(role.values()).includes('平民')){
						alert('臥底勝利YA');
						endGame=true;
						console.log('臥底勝利YA');
					}
					console.log(endGame);
					if(endGame){
						role.clear();
						lifemen=remainlifemen.slice();
						console.log(lifemen);
						lvotes=0;
// 						$('.GameRole').text(" ");
					}else{
						countdownnumber=3;
						gameLength=lifemen.length;
						boderj=0;
						lvotes=0;
						startgame();
					}
				}
// 				console.log(Array.from(role.values()).includes('臥底'));
// 				console.log(Array.from(role.values()).includes('平民'));
			}
		};
		
		
		webSocket.onclose = function(event) {
// 			updateStatus("WebSocket Disconnected");
		};
	}

	//倒數計時
		var gameLength;
		var countdownnumber;
		var countdownid;
		var boderj;
		function startgame(){ countdownfunc(); }
		function countdownfunc(){
// 		$('#GamePic'+boderj).css('border-color','yellow').css('border-style','solid').css('border-width','5px');	
		 $('.'+lifemen[boderj]).css('border-color','yellow').css('border-style','solid').css('border-width','5px');
		 var x=document.getElementById("countdown");
		 x.innerHTML=countdownnumber;
		 if (countdownnumber==0){ 
// 			 $('#GamePic'+boderj).css('border-width','0px');
			 $('.'+lifemen[boderj]).css('border-width','0px');
		 	 clearTimeout(countdownid);
		 	 gameLength--;
		 	 boderj++;
			  if(gameLength===0){	  
				  $('#undercover').attr("style","visibility:");
				  countdownnumber2=5;
				  vote();
			 }else{
				 	countdownnumber=3;
					startgame(gameLength);
			 }
		 }else{
		  countdownnumber--;
		  if(countdownid){
		   clearTimeout(countdownid);
		  }
		  countdownid=setTimeout(countdownfunc,1000);
		 }	 
		}
	
		
		//投票計時
		var countdownnumber2;
		var countdownid2;
		function vote(){	countdownfunc2();}
		function countdownfunc2(){
			 var x=document.getElementById("countdown2");
			 x.innerHTML=countdownnumber2;
		 if (countdownnumber2==0){ 
		 	 clearTimeout(countdownid2);
		 	 $('#undercover').attr("style","visibility:hidden");
		 	 sendMessage3(votes);
		 }else{
		  countdownnumber2--;
		  if(countdownid2){
		   clearTimeout(countdownid2);
		  }
		  countdownid2=setTimeout(countdownfunc2,1000);
		 }	 
		}
		
		
		var votes;
		$('#undercover img').click(function(){
			 $('#undercover img').css('border-width','0px');
	 		 $(this).css('border-color','yellow').css('border-style','solid').css('border-width','5px');
// 	 		 console.log($(this).attr("class"));
			votes = $(this).attr("class");
	 	 });

// 		function underclose(){
// 		 	$('#undercover').attr("style","visibility:hidden");
// 		}
		
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
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	

	function sendMessage2(topicName) {
// 		var n = topicName.indexOf(",");
// 		topicName.substring(0,n)
// 		console.log(topicName.substring(0,n));
// 		console.log(topicName.substring(n+1));
		var jsonObj = {
			"isStart": 'y',
			"topic":topicName,
			"isGood":'平民',
			"mem":lifemen
		};
		webSocket.send(JSON.stringify(jsonObj));
	}
	
	function sendMessage3(vt) {
		votes = $('#GamesPic1').attr("class");
		var jsonObj = {
			"votes":vt,
			"mem":'${memberVO.mem_no}',
			"roles":role.get("${memberVO.mem_no}")
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
