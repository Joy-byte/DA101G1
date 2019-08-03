<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />
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
				<div class="spotClass" style="display: none">

					<b>地點:</b> <select size="1" name="spot" id="landscape_city">
						<c:forEach var="landscape_city" items="${dao.allCity}">
							<option value="${landscape_city}">${landscape_city}
						</c:forEach>
					</select> <br> <b>景點名稱:</b> <select size="1" name="spot">
						<c:forEach var="landscapeVO" items="${dao.all}">
							<option value="${landscapeVO.landscape_no}"
								${(landscapeVO.landscape_no==landscapeVO.landscape_no)? 'selected':'' }>${landscapeVO.landscape_name}
						</c:forEach>
					</select> <img src="images/trash.png">
				</div>
				<br>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<img src="<%=request.getContextPath()%>/front-end/home/images/5.png"
					onclick="tt()"><br>
				<form action="ItineraryServlet.do" id="myForm" method="post"
					enctype="multipart/form-data">
					<input type="file" name="pic" onchange="readURL(this)"
						targetID="preview_progressbarTW_img"
						accept="image/gif, image/jpeg, image/png"><br> <input
						type="file" name="upfile1"><br> <input type="file"
						name="upfile2"><br> <br>
					<!-- 帳號   <input type="text" name="memId" id="memId"><br> -->
					<img src="images/add_2.png" id="btnAddSpot"><br>

					<!-- <br> 開始日期時間: <input name="itinerary_start" id="start_dateTime" type="text" ><br> -->
					<!-- <br> 結束日期時間: <input name="end_dateTime"   id="end_dateTime"   type="text" > -->
					<br> <b>城市:</b> <select size="1" name="landscape_city"
						id="landscape_city">
						<c:forEach var="landscape_city" items="${dao.allCity}">
							<option value="${landscape_city}"
								${(landscape_city==landscape_city)? 'selected':'' }>${landscape_city}
						</c:forEach>
					</select> <br> <b>景點名稱:</b> <select size="1" name="landscape_no"
						id="landscape">
						<option value="-1">請選擇</option>
					</select> <input type="hidden" name="action" value="insert"> <br>
					<input type="submit" id="btnSend" value="送出" class="btn btn-info">
					${landscape_city}
				</form>
			</div>
			<div class="p-1">
				<iframe
					src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d3616.952024735842!2d121.18951131321882!3d24.96774684710203!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1szh-TW!2stw!4v1561560401689!5m2!1szh-TW!2stw"
					width="600" height="450" frameborder="0" style="border: 0"
					allowfullscreen></iframe>
			</div>
		</div>
			<tr style='background-color:#fee;'>
				<td class="rank">1</td>
				<td>資料xxx</td>
				<td><button class="add">Add</button> <button class="remove">Remove</button></td>
			</tr>
			<tr style='background-color:#fee;'>
				<td class="rank">2</td>
				<td>資料yyy</td>
				<td><button class="add">Add</button> <button class="remove">Remove</button></td>
			</tr>

		<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
<!-- 		<script src="https://code.jquery.com/jquery-3.3.1.js"></script> -->
		<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
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
</body>		
		<script type="text/javascript">
		
			function $id(id) {
				return document.getElementById(id);
			}
			
			$(function(){
				$('.table1').on('click', '.add', function(){
					$(this).closest('tr').after('<input type="text"> ');
					refreshItem();
				});
			
			$('.table1').on('click', '.remove', function(){
				$(this).closest('tr').remove();
				refreshItem();
			});
	
	
	// 常用卻不能用的做法
// 	$('.add').on('click', function(){
// 		$(this).closest('tr').after('<tr><td class="rank"></td><td>資料zzz</td><td><button class="add">Add</button> <button class="remove">Remove</button></td></tr>');
// 		refreshItem();
// 	});
	
// 	$('.remove').on('click', function(){
// 		$(this).closest('tr').remove();
// 		refreshItem();
// 	});

    // 另外一種作法
// 	add();
//     remove();
});

// function add(){
//     $('.add').off().on('click', function(){
// 		$(this).closest('tr').after('<tr><td class="rank"></td><td>資料zzz</td><td><button class="add">Add</button> <button class="remove">Remove</button></td></tr>');
// 		refreshItem();
// 		add();
// 		remove();
// 	});
// }

function add(){
    $('.add').off().on('click', function(){
		$(this).closest('tr').after('<tr><td class="rank"></td><td>資料zzz</td><td><button class="add">Add</button> <button class="remove">Remove</button></td></tr>');
		refreshItem();
		add();
		remove();
	});
}

function remove(){
	$('.remove').off().on('click', function(){
		$(this).closest('tr').remove();
		refreshItem();
	});	
}

function refreshItem(){
	$('.table1 tr').each(function(index, item){
		$(this).find('.rank').text(index);
	});	
};
			
			function init(){	
				var firsttime = 'Y';
				document.getElementById("landscape_city").onchange = function(){
					var xhr = new XMLHttpRequest();
					//設定好回呼函數 
					xhr.onreadystatechange = function(){
					  	if(xhr.readyState == 4){
					  		if(xhr.status == 200){
					  			firsttime='N';
					  			var data = JSON.parse(xhr.responseText);                     // parse to JSON format
								clearSelect();
					  			data.forEach(function(item) {
					  				var node = document.createElement("option");             // Create a <option> node
					  				node.value = item.landscape_no;							     // Set the value
					  				var textnode = document.createTextNode(item.landscape_name);       // Set the text
					  				node.appendChild(textnode);                              // Append the text to <option>
					  				document.getElementById("landscape").appendChild(node);
					  			});
					  		} else{
					  			alert(xhr.status);
					  			alert("AJAX發生錯誤囉!");
					  		}
					  	}
					}
				
					//建立好Post連接與送出請求
					
					var landscape_city = document.getElementById("landscape_city").value;
					var url= "<%=request.getContextPath()%>/front-end/itinerary/ItineraryServlet.do?action=getSelect&landscape_city=" + landscape_city +"&firsttime="+firsttime;
					xhr.open("get", url, true); //先open a connection
					xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded"); //再設定requestHeader為urlencoded (POST method)
					xhr.send(null); 

				}
			}
			
			function clearSelect(){
				document.getElementById("landscape").innerHTML = '';
				var node = document.createElement("option");           // Create a <option> node
				node.value = "-1";							      	   // Set the value
				var textnode = document.createTextNode("請選擇");       // Set the text
				node.appendChild(textnode);                            // Append the text to <option>
				document.getElementById("landscape").appendChild(node);
			}
			
		window.onload=init;
		
		
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

						img.setAttribute("src", e.target.result)

					}

					reader.readAsDataURL(input.files[0]);

				}

			}

			function addSpot() {
				var myForm = $id("myForm");
				var btnSend = $id("btnSend");
				var newSpot = document.getElementsByClassName("spotClass")[0]
						.cloneNode(true);
				newSpot.style.display = "";
				newSpot.getElementsByTagName("img")[0].onclick = removeSpot;
				myForm.insertBefore(newSpot, btnSend);
			}

			function removeSpot(e) {
				var myForm = $id("myForm");
				var spotDiv = e.target.parentNode;
				myForm.removeChild(spotDiv);
			}

			window.addEventListener("load", function() {
				$id("btnAddSpot").onclick = addSpot;

			}, false)

			$.datetimepicker.setLocale('zh'); // kr ko ja en
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
		</script>
</body>
</html>