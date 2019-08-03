<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="com.picture.model.*"%>
<%@ page import="com.question.model.*"%>
<%@ page import="java.util.*"%>

<%
	JedisQuestionVO jedisQuestionVO = (JedisQuestionVO) request.getAttribute("jedisQuestionVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
	
<title>itineraryUpload</title>
<style>

body {
/* 	background-color: #FFFFBB; */
}

.preview_progressbarTW_img {
	width: 200px;
	height: 200px;
}

.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
} /* height:  151px; */


#map-canvas { height: 900px; margin: 0; padding: 0 ; width:700px}
</style>


</head>
<body>

	<jsp:useBean id="dao" scope="page"
		class="com.landscape.model.LandscapeDAO" />
	<div class="container-fuild">
		<div class="d-flex justify-content-around">
			<div class="p-1">
				<jsp:useBean id="now" scope="page" class="java.util.Date" />
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>	
				<!-- ItineraryVO itineraryVO = (ItineraryVO) session.getAttribute("itineraryVO");	  -->
				<!-- 		  byte[] bytes = itineraryVO.getItinerary_pic();	 -->

				<!-- 		  PictureService picSrc = 	new	PictureService(); -->
				<!-- byte[] bytes =  picSrc.getOnePic("IT00020").getPic();	  -->

				<!-- PictureService picSrc = 	new	PictureService(); -->
				<!-- byte[] bytes =  picSrc.getOnePic("IT00020").getPic();	 -->
				<!--  Base64.Encoder encoder = Base64.getEncoder(); -->
				<!-- //編碼 -->
				<!-- String encodedText = encoder.encodeToString(bytes); -->
				<!--  //pageContext.setAttribute("encodedText",encodedText);				 -->



				<%-- <td><img src="data:image/jpeg;base64,<%=encodedText%>" style="width:100px;height:100px; border:1px dotted red"></td> --%>
				<!-- <div class="spotClass" style="display:none"> -->
				<!-- 	景點 <input type="text" name="spot"><img src="images/trash.png"> -->
				<!-- </div><br> -->

				<br>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<div>
		
	</div>
	<div>
		
	</div>
				
				<form action="ItineraryServlet.do" id="myForm" method="post"
					enctype="multipart/form-data">
<!-- 					<input type="file" name="pic" onchange="readURL(this)" -->
<!-- 						targetID="preview_progressbarTW_img" -->
<!-- 						accept="image/gif, image/jpeg, image/png"><br>  -->
<!-- 					<input type="file" name="pic"><br>  -->
<!-- 					<input type="file"name="pic"><br><br> -->
					<!-- 帳號   <input type="text" name="memId" id="memId"><br> -->
					<img src="images/add_2.png" id="btnAddSpot"  onclick="addSpot()"><br>
					<div class="spotClass" style="display: none">
					<b>城市:</b> <select size="1" name="landscape_city"  id="landscape_city" onchange="getL()" >
						<option value="-1">請選擇</option>
						<c:forEach var="landscape_city" items="${dao.allCity}">
							<option value="${landscape_city}"
							${(param.landscape_city==landscape_city)? 'selected':'' }>${landscape_city}
<%-- 									${(landscape_city==landscape_city)? 'selected':'' }>${landscape_city} --%>
						</c:forEach>
					</select> <br> <b>景點名稱:</b> <select size="1" name="spot"  id="landscape" >
						<option value="-1">請選擇</option>
					</select> <img src="images/trash.png"><br> 
					<input type="text" class="form-control landInput" id="land" name="landscapeL" style="width: 220px;margin-left:5px" 
						>
					<input id="pic"  type="file" name="pic" onchange="readURL(this)"
							targetID="preview_progressbarTW_img2"
							accept="image/gif, image/jpeg, image/png"> <br>
					<input type="file" name="pic"><br>
				 </div>
					<div style="display:flex">
								
					<div>
						
					<!-- <br> 開始日期時間: <input name="itinerary_start" id="start_dateTime" type="text" ><br> -->
					<!-- <br> 結束日期時間: <input name="end_dateTime"   id="end_dateTime"   type="text" > -->
					<br>
					
					 <b>自訂問題</b>&nbsp <input  name="qustion" value="${param.qustion}"><br><br>
					 
					 <b>選項1:</b><input  id="qustionOption1"  name="qustionOption1" value="${param.qustionOption1}"> 
					 		<input type="checkbox" name="checkboxes"  class="checkboxGroup" value="qustionOption1"  
					 		<%= (jedisQuestionVO==null)?"":(jedisQuestionVO.getAnswer().equals("qustionOption1"))? "checked"  : "" %>>
					 		<span><%= (jedisQuestionVO==null)?"":(jedisQuestionVO.getAnswer().equals("qustionOption1"))? "選為正確解答"  : "" %></span><br><br>
					 		
					 <b>選項2:</b><input  id="qustionOption2"  name="qustionOption2" value="${param.qustionOption2}">
					 		<input type="checkbox" name="checkboxes"  class="checkboxGroup" value="qustionOption2" 
					 		<%= (jedisQuestionVO==null)?"":(jedisQuestionVO.getAnswer().equals("qustionOption2"))? "checked"  : "" %>>
					 		<span><%= (jedisQuestionVO==null)?"":(jedisQuestionVO.getAnswer().equals("qustionOption2"))? "選為正確解答"  : "" %></span><br><br>
					 		
					 <b>選項3:</b><input  id="qustionOption3"  name="qustionOption3" value="${param.qustionOption3}">
					 	    <input type="checkbox" name="checkboxes"  class="checkboxGroup" value="qustionOption3"
							<%= (jedisQuestionVO==null)?"":(jedisQuestionVO.getAnswer().equals("qustionOption3"))? "checked"  : "" %>>
							<span><%= (jedisQuestionVO==null)?"":(jedisQuestionVO.getAnswer().equals("qustionOption3"))? "選為正確解答"  : "" %></span><br><br>

					 	    
					 <b>城市:</b> 
						<select size="1" name="landscape_city"  id="landscape_city1"   onchange="getL(1)" >
							<option value="-1">請選擇</option>
							<c:forEach var="landscape_city" items="${dao.getAllCity()}">							
								<option value="${landscape_city}"
									${(param.landscape_city==landscape_city)? 'selected': '' }>${landscape_city}
								</option>
							</c:forEach>
						</select> <br> <b>景點名稱:</b> <select size="1" name="landscape_no"  id="landscape1"  onchange="setL(1)" >
								<option value="-1">請選擇</option>
						</select> <br> <br> 
						<input type="text" class="form-control landInput" id="land1" name="landscapeL" style="width: 220px;margin-left:5px" 
						>
						<br> 
						<input type="file" name="pic" onchange="readURL(this)"
							targetID="preview_progressbarTW_img1"
							accept="image/gif, image/jpeg, image/png">
							<br> 
						<input type="file" name="pic"><br>
						
						<input type="hidden" name="action" value="insert"> <br>
						<input type="submit" id="btnSend" value="送出" class="btn btn-info">
						
						
						
						
						</div>
						
<!-- 						<div id='prev' style="display:flex; flex-direction:column"> -->
<%-- 							<img id="preview_progressbarTW_img1"  class="preview_progressbarTW_img" src="<%=request.getContextPath()%>/NoData/null2.jpg" /> &nbsp --%>
<%-- 							<img id="preview_progressbarTW_img1"  class="preview_progressbarTW_img" src="<%=request.getContextPath()%>/NoData/null2.jpg" /> &nbsp --%>
<%-- 							<img id="preview_progressbarTW_img1"  class="preview_progressbarTW_img" src="<%=request.getContextPath()%>/NoData/null2.jpg" /> &nbsp --%>
<%-- 							<img id="preview_progressbarTW_img1"  class="preview_progressbarTW_img" src="<%=request.getContextPath()%>/NoData/null2.jpg" /> &nbsp --%>
<!-- 						</div> -->
<!-- 						<div style="display:flex; flex-direction:column"> -->
<%-- 							<img id="preview_progressbarTW_img1"  class="preview_progressbarTW_img" src="<%=request.getContextPath()%>/NoData/null2.jpg" /> &nbsp --%>
<%-- 							<img id="preview_progressbarTW_img1"  class="preview_progressbarTW_img" src="<%=request.getContextPath()%>/NoData/null2.jpg" /> &nbsp --%>
<%-- 							<img id="preview_progressbarTW_img1"  class="preview_progressbarTW_img" src="<%=request.getContextPath()%>/NoData/null2.jpg" /> &nbsp --%>
<%-- 							<img id="preview_progressbarTW_img1"  class="preview_progressbarTW_img" src="<%=request.getContextPath()%>/NoData/null2.jpg" /> &nbsp --%>
						</div>					
<!-- 					</div> -->
				</form>
				
				
				
				
				
				
																 
			</div>
			<div class="p-1" ><span></span>
				<div id="map-canvas"></div>
			</div>
			
		</div>
	

		<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
<!-- 		<script src="https://code.jquery.com/jquery-3.3.1.js" ></script> -->
		<script type="text/javascript"
		      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC08hJMCtyO3EvPjS3C598ptqIz5k-wKY4">
	</script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script src="datetimepicker/jquery.js"></script>
		<script src="datetimepicker/jquery.datetimepicker.full.js"></script>
		<script type="text/javascript">
		
			function $id(id) {
				return document.getElementById(id);
			}
			
				
			$('.checkboxGroup').click(function(){
				$('.checkboxGroup').next().html('');
				    if($(this).prop('checked')){
				     $('.checkboxGroup').prop('checked',false);
				     $('.checkboxGroup').next().html('');
				     $(this).prop('checked',true);
				     $(this).next().html('選為正確解答');
				    }
			});
						
			function setL(x) {
				getL(x,"setL");
				$( "#land"+x ).val($("#landscape"+x+" option:selected").text());			
// 				console.log('who1')
			}
			
// 			$('#land1').addEventListener("input", function(event) {
// 			    result.push({
// 			        event  : 'input',
// 			        value  : this.value,
// 			        keyCode : event.keyCode
// 			    });
// 			})

// 			function abc(){
// 				console.log(document.getElementById('landscape1').options)
// 				var landscapeOption = document.getElementById('landscape1').options
// 				for(var opt = 1 ; opt<landscapeOption.length;opt++){
// 					console.log(landscapeOption[opt].text)
// 					console.log($('#land1').val())
// 					if($('#land1').val()== landscapeOption[opt].text){
// 						console.log('1233')
// 					}
// 				}
// 			}

			
			
			var firsttime = 'Y';
				function getL(x,optionSet) {
// 			$(".landscape_city").change(function() {	
// 				document.getElementById("landscape_city").onchange = function(){
					var xhr = new XMLHttpRequest();
					
					var arrLandscape=[];
					var arrName=[];
				    var arrLat = [];
					var arrLng = [];
					var landscapeName = new Array();
					//設定好回呼函數
					xhr.onreadystatechange = function(){
					  	if(xhr.readyState == 4){
					  		if(xhr.status == 200){					  			
					  			firsttime='N';
					  			var data = JSON.parse(xhr.responseText);                     // parse to JSON format	
// 					  				console.log(hasLand)
					  				if(optionSet!="setL"){
					  					clearSelect(x);	
// 					  					console.log(landscapeOption.length)
					  				}															
					  				$.each(data, function(i, item){
// 					  					console.log($('#landscape'+x))
// 											hasLandscape = false;
										if(optionSet!="setL"){																						
											$('#landscape'+x).append("<option value='"+item.landscape_no+"'>"+item.landscape_name+"</option>");
										}
// 											$(this).next().append("<option value='"+item.landscape_no+"'>"+item.landscape_name+"</option>");
										
													arrName.push(item.landscape_name);
													arrLat.push(item.landscape_lat);
													arrLng.push(item.landscape_lng);
	
													landscapeName.push(item.landscape_name);
													console.log(landscapeName)
// 											uploadmap(item.landscape_name,item.landscape_lat,item.landscape_lng);
									});
// 					  				console.log($("#landscape_city"+x+" option:selected").text()+x);
// 										if(optionSet!="setL"){
											$( "#land" +x).autocomplete({
													source: landscapeName,	
													select: function( event, ui ) {
															console.log(ui.item.value)
															var landscapeOption = document.getElementById('landscape'+x).options
															for(var opt = 1 ; opt<landscapeOption.length;opt++){
																if(ui.item.value== landscapeOption[opt].text){
																	landscapeOption[opt].selected=true;
																	setL(x);
																}
															}
													}
											});
// 										}
										
						  				arrLandscape=[arrName,arrLat,arrLng];
// 					  				console.log(arrLandscape);
										mapCity.set($("#landscape_city"+x+" option:selected").text().trim()+x,arrLandscape);

										if(x<mapCity.size){
											for (var [key, value] of mapCity.entries()) {							 					  
													 if(key.includes(x) && !key.includes($("#landscape_city"+x+" option:selected").text().trim()+x)){
														 mapCity.delete(key);
														 console.log(mapCity);
													 }
											}						
										}
										console.log(mapCity);
// 										console.log(mapCity.values().next().value);
// 										console.log(!mapCity.values().next().value[0].includes(arrName[0]));
 										console.log('who2')
					  				    uploadmap(arrLat[arrLat.length - 1],arrLng[arrLng.length - 1],x);
										
					  		} else{
					  			alert(xhr.status);
					  			alert("AJAX發生錯誤囉!");
					  		}
					  	}
					}
				
					//建立好連接與送出請求

					var landscape_cityo = document.getElementById("landscape_city"+x).value;
					var url= "<%=request.getContextPath()%>/front-end/itinerary/ItineraryServlet.do?action=getSelect&landscape_cityo=" + landscape_cityo +"&firsttime="+firsttime;
					xhr.open("get", url, true); //先open a connection
					xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded"); //再設定requestHeader為urlencoded (POST method)
					xhr.send(null); 

// 				}
			};
			
			
			
			function clearSelect(x){
				document.getElementById("landscape"+x).innerHTML = "";
				var node = document.createElement("option");           // Create a <option> node
				node.value = "-1";							      	   // Set the value
				var textnode = document.createTextNode("請選擇");       // Set the text
				node.appendChild(textnode);                            // Append the text to <option>
				document.getElementById("landscape"+x).appendChild(node);
			}
			

		
// 		$(document).ready(function(){
// 			var firsttime = 'Y';
// 			var landscape_city = document.getElementById("landscape_city").value;
// 			 $('#landscape_city').change(function(){
// 				 $.ajax({
// 					 type: "GET",
<%-- 					 url: "<%=request.getContextPath()%>/front-end/itinerary/ItineraryServlet.do", --%>
// 					 data:{action=getSelect}
// 						 creatQueryString($(this).val(), ""),
// 					 dataType: "json",
// 					 success: function (data){
// 						 firsttime='N';
// 						 clearSelect();
// 						$.each(data, function(i, item){
// 							$('#landscape').append("<option value='"+item.landscape_no+"'>"+item.landscape_name+"</option>");
// 						});
//	 					$(data).each(function(i, item){
//	 						$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//	 					});
//	 					jQuery.each(data, function(i, item){
//	 						$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//	 					});
// 				     },
// 		             error: function(){alert("AJAX-grade發生錯誤囉!")}
// 		         })
// 			 })
// 		}	 
// 		function clearSelect(){
// 			$('#landscape').empty();
// 			$('#landscape').append("<option value='-1'>請選擇</option>");
// 		}
			
			
			
			
			
			function readURL(input) { //搭配20 , 28 行 實現預覽

				if (input.files && input.files[0]) {

					var imageTagID = input.getAttribute("targetID");

					var reader = new FileReader();

					reader.onload = function(e) {

						var img = document.getElementById(imageTagID);

						img.setAttribute("src", e.target.result);

					}

					reader.readAsDataURL(input.files[0]);

				}

			}
			var  i=1;
			function addSpot() {				
				var myForm = $id("myForm");
				var btnSend = $id("btnSend");
				
				var newSpot = document.getElementsByClassName("spotClass")[i-1]
						.cloneNode(true);
				newSpot.style.display = "";
				i++;
				$(newSpot).find('#landscape').attr("name","landscape_no");	
				$(newSpot).find('#landscape').attr("class", "landscape"+i);
				$(newSpot).find('#landscape').attr("onchange", "setL("+i+")");
				$(newSpot).find('#landscape').attr("id", "landscape"+i);				
				$(newSpot).find('#landscape_city').attr("onchange","getL("+i+")");
				$(newSpot).find('#landscape_city').attr("id","landscape_city"+i);	
				$(newSpot).find('#land').attr("id","land"+i);
				$(newSpot).find('#pic').attr("targetID","preview_progressbarTW_img"+i);
				$(newSpot).find('#pic').attr("id","pic"+i);
				newSpot.getElementsByTagName("img")[0].onclick = removeSpot;
				myForm.insertBefore(newSpot, btnSend);
				
				
				$('#prev').append('<img class=preview_progressbarTW_img id=preview_progressbarTW_img'+i+'>');
				$('#preview_progressbarTW_img'+i).attr('src','../../NoData/null2.jpg')
			}

			function removeSpot(e) {
				for (var [key, value] of mapCity.entries()) {							 					  
					 if(key.includes(i) && key.includes($("#landscape_city"+i+" option:selected").text().trim()+i)){
						 mapCity.delete(key);
// 						 console.log(mapCity);
					 }
				}		
				uploadmaps('$landscape'+i)
				$('#preview_progressbarTW_img'+i).remove();
				i--
				var myForm = $id("myForm");
				var spotDiv = e.target.parentNode;
				myForm.removeChild(spotDiv);
				
				

			}

			

// 			$.datetimepicker.setLocale('zh'); 
// 			$(function() {
// 				$('#start_dateTime').datetimepicker(
// 						{
// 							format : 'Y-m-d H:i',
// 							onShow : function() {
// 								this.setOptions({
// 									maxDate : $('#end_dateTime').val() ? $(
// 											'#end_dateTime').val() : false
// 								})
// 							},
// 							timepicker : true,
// 							step : 1
// 						});

// 				$('#end_dateTime').datetimepicker(
// 						{
// 							format : 'Y-m-d H:i',
// 							onShow : function() {
// 								this.setOptions({
// 									minDate : $('#start_dateTime').val() ? $(
// 											'#start_dateTime').val() : false
// 								})
// 							},
// 							timepicker : true,
// 							step : 1
// 						});
// 			});
			
			
			
			//地圖
			var mapCity = new Map();
			
		    var mapOptions;
		    var map;
		    
			
			function initialize() {
		         mapOptions = {
		          center: { lat: 22.9698, lng: 120.29295},
		          zoom: 9
		        };
		         map = new google.maps.Map(
		            document.getElementById('map-canvas'),
		            mapOptions);				    	        	
		      }
		      
		      google.maps.event.addDomListener(
		          window, 'load', initialize);
		      
		      //
		      function uploadmap(selectLat,selectLng,x){
     	  	
		    	 	 mapOptions = {
		    	          center: { lat: selectLat, lng: selectLng},
		    	          zoom: 9
		    	     };
		    	  
		    	  	map = new google.maps.Map(
		    	   		  document.getElementById('map-canvas'),
		    	     	  mapOptions);
		    	  			
// 		    	  	var arrCity =[];
		    	  	for (var [key, value] of mapCity.entries()) {						   
// 						   console.log(mapCity);
// 							console.log(arrCity)
// 						   if(!arrCity.includes(key.substring(0,3))){
// 						  	 arrCity.push(key.substring(0,3));
						  	for(y=0;y<value[0].length;y++){
						    	  markerLatLng = new google.maps.LatLng({lat: value[1][y], lng: value[2][y]});
						    	  	console.log(value[1][y])
							        marker = new google.maps.Marker({ 
							        position: markerLatLng,
						          map: map 
						     	 });
							}						  	
// 						   }
					}
		    	  	
		    	  	if($("#landscape"+x+" option:selected").text()!="請選擇"){
		    	  		console.log($("#landscape"+x+" option:selected").text())
		    	  		uploadmaps($("#landscape"+x+" option:selected").text());
		    	  	}
		      }
		      
		      //
		      function uploadmaps(selectName){
	    	  
		    	  	map = new google.maps.Map(
		    	   		  document.getElementById('map-canvas'),
		    	     	  mapOptions);
		    	  	
		    	  	for (var [key, value] of mapCity.entries()) {						   
		    	  		if(value[0].includes(selectName)){
		    	  		   var pos = value[0].indexOf(selectName);
		    	  			var selectNames = [selectName];
			    	  		var selectLat =  [value[1][pos]];
			    	  		var selectLng = [value[2][pos]];
			    	  		var arrLandscape=[selectNames,selectLat,selectLng];	
			    	  		mapCity.set(key,arrLandscape);
			    	  		break;
		    	  		}
					}
		    	  
// 		    	  	console.log(mapCity);

		    	  	var arrCity =[];
		    	  	var arrLat = [];
					var arrLng = [];
		    	  	for (var [key, value] of mapCity.entries()) {						   			
						 for(var u=0;u<value[0].length;u++){
						   if(!arrCity.includes(value[0][u])){
						  	 arrCity.push(value[0][u]);
						  	 arrLat.push(value[1][u]);
						  	 arrLng.push(value[2][u]);
						   }
						 }
					}
// 		    	  	console.log(mapCity);
// 		    	  	console.log(arrCity);
		    	  	
		    	  	for(y=0;y<arrCity.length;y++){
				    	  markerLatLng = new google.maps.LatLng({lat: arrLat[y], lng: arrLng[y]});
					        marker = new google.maps.Marker({ 
					        position: markerLatLng,
				          map: map 
				     	 });
					}
		      }		      
		</script>
		
		<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30=" crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
</body>
</html>