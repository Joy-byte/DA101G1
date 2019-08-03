<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="com.picture.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Upload</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="datetimepicker/jquery.datetimepicker.css" />
	<script type="text/javascript"
		      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC08hJMCtyO3EvPjS3C598ptqIz5k-wKY4">
		</script>
<style>

body {
	background-color: #FFFFBB;
}

#preview_progressbarTW_img {
	width: 200px;
	height: 200px;
}

.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
} /* height:  151px; */

html { height: 100% }
body { height: 100%; margin: 0; padding: 0 }
#map-canvas { height: 100%; margin: 0; padding: 0 ; width:100%}
</style>


</head>
<body>

	<jsp:useBean id="dao" scope="page"
		class="com.landscape.model.LandscapeDAO" />
	<div class="container-fuild">
		<div class="d-flex justify-content-around">
			<div class="p-1">
				<img id="preview_progressbarTW_img" src="images/memberpre.jpg" /> <br>
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

					<!-- <br> 開始日期時間: <input name="itinerary_start" id="start_dateTime" type="text" ><br> -->
					<!-- <br> 結束日期時間: <input name="end_dateTime"   id="end_dateTime"   type="text" > -->
					<br> <b>城市:</b> 
						<select size="1" name="landscape_city"  id="landscape_city1"   onchange="getL(1)" >
							<option value="-1">請選擇</option>
							<c:forEach var="landscape_city" items="${dao.allCity}">							
								<option value="${landscape_city}"
								${(landscape_city==landscape_city)? 'selected':'' }>${landscape_city}
							</c:forEach>
						</select> <br> <b>景點名稱:</b> <select size="1" name="landscape_no"  id="landscape1"  onchange="setL(1)" >
								<option value="-1">請選擇</option>
						</select> <br> 
						<input type="file" name="pic" onchange="readURL(this)"
							targetID="preview_progressbarTW_img"
							accept="image/gif, image/jpeg, image/png"><br> 
						<input type="file" name="pic"><br>
						
					<input type="hidden" name="action" value="insert"> <br>
					<input type="submit" id="btnSend" value="送出" class="btn btn-info">
				</form>
				
				<div class="spotClass" style="display: none">
					<b>地點:</b> <select size="1" name="landscape_city"  id="landscape_city" onchange="getL()" >
						<c:forEach var="landscape_city" items="${dao.allCity}">
							<option value="${landscape_city}"
							${(landscape_city==landscape_city)? 'selected':'' }>${landscape_city}
						</c:forEach>
					</select> <br> <b>景點名稱:</b> <select size="1" name="spot"  id="landscape" >
						<option value="-1">請選擇</option>
					</select> <img src="images/trash.png"><br> 
					<input type="file" name="pic" ><br> 
					<input type="file" name="pic"><br>
				 </div>
																 
			</div>
			<div class="p-1" ><span>-----------------------------------------------------------------------------------------</span>
				<div id="map-canvas"></div>
			</div>
			
		</div>
	

		<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
		<script src="https://code.jquery.com/jquery-3.3.1.js" ></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>

		<script src="datetimepicker/jquery.js"></script>
		<script src="datetimepicker/jquery.datetimepicker.full.js"></script>
		<script type="text/javascript">
		
			function $id(id) {
				return document.getElementById(id);
			}
			
						
			function setL(z) {
				uploadmaps($("#landscape"+z+" option:selected").text());
			}
			
			
			var firsttime = 'Y';
				function getL(x) {
// 			$(".landscape_city").change(function() {	
// 				document.getElementById("landscape_city").onchange = function(){
					var xhr = new XMLHttpRequest();
					//設定好回呼函數
					
					xhr.onreadystatechange = function(){
					  	if(xhr.readyState == 4){
					  		if(xhr.status == 200){
					  			firsttime='N';
					  			var data = JSON.parse(xhr.responseText);                     // parse to JSON format	
									clearSelect(x);
									var arrLandscape=[];
									var arrName=[];
								    var arrLat = [];
								    var arrLng = [];
								    var hasLandscape;
					  				$.each(data, function(i, item){
// 					  					console.log($('#landscape'+x))
											hasLandscape = false;
										$('#landscape'+x).append("<option value='"+item.landscape_no+"'>"+item.landscape_name+"</option>");
// 											$(this).next().append("<option value='"+item.landscape_no+"'>"+item.landscape_name+"</option>");
											if(mapCity.size==0){
// 												arrName.push(item.landscape_name);
// 												arrLat.push(item.landscape_lat);
// 												arrLng.push(item.landscape_lng);
											}else if(mapCity.size==1){																						
// 												 console.log(item.landscape_name)
												     var mapCitys = mapCity.values();
											   		 if(mapCitys.next().value[0].includes(item.landscape_name)){
											   			hasLandscape=true;
											   		 }											    	 
											}else{
													for (var [value] of mapCity.values()) {
														 if(value[0].includes(item.landscape_name)){
															 hasLandscape=true;
														 }	
														 console.log(value)
													}
											}
											
											if(!hasLandscape){
													arrName.push(item.landscape_name);
													arrLat.push(item.landscape_lat);
													arrLng.push(item.landscape_lng);
										    }	
											
// 											uploadmap(item.landscape_name,item.landscape_lat,item.landscape_lng);
									});
// 					  				console.log($("#landscape_city"+x+" option:selected").text()+x);
									
						  				arrLandscape=[arrName,arrLat,arrLng];
	// 					  				console.log(arrLandscape);

										mapCity.set($("#landscape_city"+x+" option:selected").text()+x,arrLandscape);
										console.log(mapCity);
// 										console.log(mapCity.values().next().value);
// 										console.log(!mapCity.values().next().value[0].includes(arrName[0]));
// 					  				uploadmap();
					  		} else{
					  			alert(xhr.status);
					  			alert("AJAX發生錯誤囉!");
					  		}
					  	}
					}
				
					//建立好Post連接與送出請求

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
				
				newSpot.getElementsByTagName("img")[0].onclick = removeSpot;
				myForm.insertBefore(newSpot, btnSend);
				

			}

			function removeSpot(e) {
				i--;
				var myForm = $id("myForm");
				var spotDiv = e.target.parentNode;
				myForm.removeChild(spotDiv);
			}

			

			$.datetimepicker.setLocale('zh'); 
			$(function() {
				$('#start_dateTime').datetimepicker(
						{
							format : 'Y-m-d H:i',
							onShow : function() {
								this.setOptions({
									maxDate : $('#end_dateTime').val() ? $(
											'#end_dateTime').val() : false
								})
							},
							timepicker : true,
							step : 1
						});

				$('#end_dateTime').datetimepicker(
						{
							format : 'Y-m-d H:i',
							onShow : function() {
								this.setOptions({
									minDate : $('#start_dateTime').val() ? $(
											'#start_dateTime').val() : false
								})
							},
							timepicker : true,
							step : 1
						});
			});
			
			
			
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
// 		      function uploadmap(){
// 		    	     mapOptions = {
// 		    	          center: { lat: arrLat[0], lng: arrLng[0]},
// 		    	          zoom: 9
// 		    	     };
		    	  
// 		    	  	map = new google.maps.Map(
// 		    	   		  document.getElementById('map-canvas'),
// 		    	     	  mapOptions);
		    	  			    	  											
// 					for(y=0;y<arrName.length;y++){
// 				    	  markerLatLng = new google.maps.LatLng({lat: arrLat[y], lng: arrLng[y]});
// 					        marker = new google.maps.Marker({ 
// 					        position: markerLatLng,
// 				          map: map 
// 				     	 });
// 					}
// 		      }
		      
		      //
		      function uploadmaps(selectName){
	    	  
		    	  	map = new google.maps.Map(
		    	   		  document.getElementById('map-canvas'),
		    	     	  mapOptions);
		    	  	
// 		    	  	var mapCitys = mapCity.values();
// 					for(var p =0 ; p<mapCity.size;p++){
// 						if(!mapCitys.next().value[0].includes(selectName)){												
// 							var pos = mapCitys.value[0].indexOf(selectName);
// 			    	  		var selectLat =  mapCitys.value[1][pos];
// 			    	  		var selectLng =  mapCitys.value[2][pos];
							
// 						}
// 					}
					
// 		    	  		console.log(mapCity.values().next().value[0].indexOf(selectName))
						var pos = mapCity.values().next().value[0].indexOf(selectName);
		    	  		var selectLat =  mapCity.values().next().value[1][pos];
		    	  		var selectLng =  mapCity.values().next().value[2][pos];
// 		    	  		console.log(selectLat);
						mapCity.values().next().value[1].clear();
						
// 		    	  	arrName.splice(arrName.indexOf(selectName),1);
// 		    	  	arrLat.splice(arrName.indexOf(selectName),1);
// 		    	  	arrLng.splice(arrName.indexOf(selectName),1);

// 					for(y=0;y<arrName.length;y++){
// 				    	  markerLatLng = new google.maps.LatLng({lat: arrLat[y], lng: arrLng[y]});
// 					        marker = new google.maps.Marker({ 
// 					        position: markerLatLng,
// 				          map: map 
// 				     	 });
// 					}
		      }
		      
		</script>
</body>
</html>