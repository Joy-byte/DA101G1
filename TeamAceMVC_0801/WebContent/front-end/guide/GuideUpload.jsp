<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="com.guide.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<jsp:useBean id="guideTourSvc" scope="page" class="com.guidetour.model.GuideTourService" />

<%
MemberVO  memVO = (MemberVO)session.getAttribute("memberVO");

GuideService guideSvc = new GuideService();
GuideVO guideVO = guideSvc.getOneGuideByMemNo(memVO.getMem_no());
session.setAttribute("guideVO", guideVO);

LandscapeService landscapeSvc = new LandscapeService();
List<LandscapeVO> landscapeList = landscapeSvc.getAll();
pageContext.setAttribute("landscapeList", landscapeList);

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
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30=" crossorigin="anonymous"></script>
    <title>發布伴遊行程</title>
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
			margin-left:13px;
			padding-left:50px;
			letter-spacing: 2px;
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
		.title-2{
			line-height: 45px;
			font-family:monospace;
			font-size:18px;
			letter-spacing: 2px;
			font-weight:bold;
			color:dimgray;
			border-bottom: 1px solid #aaa;
		}
		.title{
			font-family:monospace;
			font-size:23px;
			letter-spacing: 2px;
			font-weight:bold;
			color:#666;
		}
		.lang{
			padding-top: 20px;
			padding-bottom: 20px;
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
		.custom-checkbox{
			margin: 10px 0;
			padding-right:10px;
		}
		.custom-checkbox .custom-control-label::before {
			background-color: #eee;
			border-color: #aaa;
		}

		.custom-control-input:checked ~.custom-control-label::before {
			background-color: lightblue;
			border-color: lightblue;
		}
		.sm-title{
			width:200px;
			padding:2px 7px;
			background-color: #666;
			color:#fff;
		}
		.fa-plus-circle:hover{
			cursor: pointer;
			color:#76afd5;
		}
		.fa-plus-circle{
			font-size:30px;color:lightblue;margin-top:15px;margin-left:120px;
		}
		.fa-trash-o:hover{
			cursor: pointer;
		}
		#tourPic:hover{
			cursor: pointer;
		}
/* 		autocomplete-box */
		.ui-widget {
		  font-family: Microsoft JhengHei;
		  font-size: 15px;
		  max-height: 300px;
          overflow-y: auto;
          /* prevent horizontal scrollbar */
          overflow-x: hidden;
          /* add padding to account for vertical scrollbar */
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
			<div class="col-2" style="margin-left:148px">
	
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
			<form method="post" action="GuideTour.do" onsubmit="return false" id="uploadForm" enctype="multipart/form-data">
				<p class="title">CREATE A NEW TOUR</p>
				<div class="form-group form-row">
					<div class="col-4">
						<label for="area">伴遊行程區域</label>
						<select class="form-control " id="area" name="guideTour_area">
	 			 	      <option>台北</option>
	 			 	      <option>桃園</option>
	 			 	      <option>新竹</option>
	 			 	      <option>苗栗</option>
	 			 	      <option>台中</option>
	 			 	      <option>彰化</option>
	 			 	      <option>雲林</option>
	 			 	      <option>南投</option>
	 			 	      <option>嘉義</option>
	 			 	      <option>台南</option>
	 			 	      <option>高雄</option>
	 			 	      <option>屏東</option>
	 			 	      <option>台東</option>
	 			 	      <option>花蓮</option>
	 			 	      <option>宜蘭</option>
	 			 	      <option>基隆</option>
	 			 	    </select>
					</div>
					 <div class="col-8">
						<label for="tourName">伴遊行程名稱</label><span id="nameError" style="color:red;font-size:9;margin-left:22px;"></span>
	 			 	    	<input type="text" id="tourName" name="guideTour_name" class="form-control">
					</div>
				</div>
				<div class="form-group form-row">
					<div class="col-4">
						<label for="tourPeo">人數上限</label><span id="peoError" style="color:red;font-size:9;margin-left:22px;"></span>
	 			 	      	<input type="text" id="tourPeo" name="guideTour_peo" class="form-control" placeholder="最多99人">
					</div>
					 <div class="col-4">
						<label for="tourPic">上傳行程封面</label><span id="picError" style="color:red;font-size:9;margin-left:22px;"></span>
 			 	      	<div class="input-group mb-3" style="width:500px">
						  <div class="custom-file">
						    <input type="file" class="custom-file-input" name="guideTour_pic" id="tourPic" aria-describedby="inputGroupFileAddon01" accept="image/gif, image/jpeg, image/png">
						    <label class="custom-file-label" for="tourPic">上傳圖片</label>
						  </div>
						  <button type="button" class="btn btn-warning" style="margin:0 0 0 30px;" onclick="magic()">神奇小按鈕</button>
						</div>
					</div>
				</div>
				<!-- 景點+map區塊 -->
				<div style="display: flex;margin-top:40px">
					<div id="land-div" style="width:320px;height:550px">
						<div>
							<div class="form-group row align-items-center" style="margin:0 0 0 0">
								<i class="fa fa-map-marker" style="font-size: 30px;color:#dd725a"></i>
								<!-- 景點1-input -->
								<input type="text" class="form-control landInput" id="land1" name="landscape" style="width: 220px;margin-left:5px" >
							</div>
						</div>
						<div class="more-land" style="display: none;">
							<i class="fa fa-angle-double-down" style="margin-left: 3px"></i><br>
							<i class="fa fa-angle-double-down" style="margin-left: 3px"></i>
							<select class="custom-select custom-select-sm" name="transport" style="width:95px">
							  <option disabled selected value="">交通方式</option>
							  <option value="公車">公車</option>
							  <option value="火車">火車</option>
							  <option value="腳踏車">腳踏車</option>
							  <option value="步行">步行</option>
							  <option value="機車">機車</option>
							  <option value="捷運">捷運</option>
							  <option value="開車">開車</option>
							  <option value="其他">其他</option>
							</select>
							<select class="custom-select custom-select-sm" id="hour" name="hour" style="width:55px">
							  <option>0</option>
							</select>
							時
							<select class="custom-select custom-select-sm" id="min" name="min" style="width:55px">
							  <option>0</option>
							</select>分<br>
							<i class="fa fa-angle-double-down" style="margin-left: 3px"></i><br>
							<div class="form-group row align-items-center" id="inputDiv" style="margin:0 0 0 0">
								<i class="fa fa-map-marker" style="font-size: 30px;color:#dd725a"></i>
								<!-- 景點n-input -->
								<input type="text" class="form-control landInput"  id="land" style="width: 220px;margin-left:5px" >
								<i class="fa fa-trash-o" name="remove" onclick="removeLand(this)" style="font-size:25px;margin-left:5px;color:gray"></i>
							</div>
						</div>

						<i class="fa fa-plus-circle" id="add"></i><br>
						<span id="landError" style="color:red;font-size:9;margin-left:22px;"></span> 
						<span id="transError" style="color:red;font-size:9;margin-left:12px;"></span>
					</div>
					<div id="map" style="width:700px;height:650px;background-color: gray">
					</div>
				</div>
				<!-- 行程詳情 -->
				<div style="margin:40px 0;">
					<span class="sm-title"><label for="tourDetail">行程詳細內容</label></span><span id="detailError" style="font-size:9;color:red;margin-left: 20px"></span>
	 			 	<textarea class="form-control" id="tourDetail" name="guideTour_detail" rows="8" placeholder="上限1000字"></textarea>
 			 	</div>
 			 	<!-- 分類選項 -->
 			 	<span class="sm-title">行程分類</span><span id="error-cat" style="font-size:9;color:red;margin-left: 20px"></span>
 			 	<div class="category-div" style="margin:10px 0 20px 20px;">
 			 		<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck1" value="風景">
						<label class="custom-control-label" for="customCheck1">風景</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck2" value="人文">
						<label class="custom-control-label" for="customCheck2">人文</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck3" value="展覽">
						<label class="custom-control-label" for="customCheck3">展覽</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck4" value="大自然">
						<label class="custom-control-label" for="customCheck4">大自然</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck5" value="登山">
						<label class="custom-control-label" for="customCheck5">登山</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck6" value="美食">
						<label class="custom-control-label" for="customCheck6">美食</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck7" value="夜生活">
						<label class="custom-control-label" for="customCheck7">夜生活</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck8" value="運動">
						<label class="custom-control-label" for="customCheck8">運動</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck9" value="娛樂">
						<label class="custom-control-label" for="customCheck9">娛樂</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck10" value="購物">
						<label class="custom-control-label" for="customCheck10">購物</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck11" value="都市">
						<label class="custom-control-label" for="customCheck11">都市</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck12" value="鄉村">
						<label class="custom-control-label" for="customCheck12">鄉村</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck13" value="古蹟">
						<label class="custom-control-label" for="customCheck13">古蹟</label>
					</div>
					<div class="custom-control custom-checkbox custom-control-inline">
						<input type="checkbox" class="custom-control-input" name="category" id="customCheck14" value="休閒">
						<label class="custom-control-label" for="customCheck14">休閒</label>
					</div>
 			 	</div>
			 	<div class="submit" style="width:100%;display: flex;justify-content: center">
			 		<input type="hidden" name="action" value="insert">
			 		<input type="hidden" name="jsonStr" id="jsonStr">
			 		<input type="hidden" name="guide_no" value="${guideVO.guide_no}">
			   		<button type="submit" class="btn btn-secondary" onclick="verify()" style="margin-top: 30px;font-size: 13px;color:#fff;">送出</button>
			 	</div>
			 </form>
			</div>
		
	</div>
</div>

<script>
	/**************************** Google Map ****************************/
	var map;
	var mapOptions;
	var markers = new Object();
	var bounds;
	function initMap() {
		mapOptions = {
          center: {lat: 23.6, lng: 121},
          zoom: 8,
          maxZoom: 16
        };
	    map = new google.maps.Map(document.getElementById('map'), mapOptions);
	    bounds = new google.maps.LatLngBounds();
	}
	
	function addMarker(name,lat,lng){
		var myLatLng = new google.maps.LatLng(lat,lng);
		var infowindow = new google.maps.InfoWindow({
		    content: name
		  });
        marker = new google.maps.Marker({ 
	       position: myLatLng,
	       title:name,
	       animation: google.maps.Animation.DROP,
	       map:map
		})
        //extend the bounds to include each marker's position
		bounds.extend(marker.position);
        //now fit the map to the newly inclusive bounds
		map.fitBounds(bounds);
     	markers[name]=marker;
     	// To add the marker to the map, call setMap();
        marker.setMap(map);
        infowindow.open(map, marker);
        marker.addListener('click', function() {
            infowindow.open(map, marker);
        });
 	}
	/***********************************************************************/

	var landscapeName = new Array(); //autoComplete source
	var landscape = new Object(); //(資料庫)景點名:經緯度、編號
	var selectLand = new Object(); //inputId(landId):景點名
	<c:forEach items="${landscapeList}" var="landscapeVO" varStatus="status"> 
		var lat = '${landscapeVO.landscape_lat}';
		var lng = '${landscapeVO.landscape_lng}';
	    var name = '${landscapeVO.landscape_name}';
	    var no = '${landscapeVO.landscape_no}';
		landscapeName.push(name);
		landscape[name] = {'lat':lat,'lng':lng,'no':no};
	</c:forEach> 
	
	$(document).ready(function(){
		//限制標籤上限
		var limit = 3;
		$('.custom-control-input').on('change', function(e) {
		   if($("input[name='category']:checked").length > limit) {
		       this.checked = false;
		       document.getElementById('error-cat').innerText='最多3項';
		   }
		});
		
		//min hour option
		for(var i=1;i<=23;i++){
			$('#hour').append('<option>'+i+'</option>');
		}
		for(var i=1;i<=59;i++){
			$('#min').append('<option>'+i+'</option>');
		}
		
		var landId = 2;
		// ++ btn
		$('#add').click(function(){
			if($(".more-land").length >= 5) {
				$('#add').hide();
			}
			var newLand = document.getElementsByClassName('more-land')[0].cloneNode(true);
			var add = document.getElementById('add');
			var landDiv = document.getElementById('land-div');
			$(newLand).find('#land').attr('name','landscape');
			$(newLand).find('#land').attr('id','land'+landId);
			var landInput = $(newLand).find('#land'+landId);
			newLand.style.display="";
			$(landInput).autocomplete({
				source: landscapeName,
				minLength: 2,
				select: function( event, ui ) {
					var name = ui.item.value;
					var lat = landscape[name].lat;
					var lng = landscape[name].lng;
					selectLand[$(landInput).attr('id')] = name;
					addMarker(name,lat,lng);
				}
			});
			landDiv.insertBefore(newLand,add);
			landId++;
		})
		
		/*autocomplete正規表示驗證篩選*/
		// var tags = [ "c++", "java", "php", "coldfusion", "javascript", "asp", "ruby" ];
		// $( "#landtest" ).autocomplete({
		//   source: function( request, response ) {
		//           var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( request.term ), "i" );
		//           response( $.grep( tags, function( item ){
		//               return matcher.test( item );
		//           }) );
		//       }
		// });
		
		//autocomplete
		$( "#land1" ).autocomplete({
			source: landscapeName,
			minLength: 2,
			select: function( event, ui ) {
				var name = ui.item.value;
				var lat = landscape[name].lat;
				var lng = landscape[name].lng;
				selectLand['land1'] = name;
				addMarker(name,lat,lng);
			}
		});
		
		//user更改景點時把原有的景點替換成新的&刪除舊marker
		$('body').on('input', '.landInput', function() {
			var id = $(this).attr('id');
			console.log(id)
			if(selectLand.hasOwnProperty(id)){
				var oldLand = selectLand[id];
				console.log(oldLand)
				delete selectLand[id];
				//remove old marker
				var marker = markers[oldLand];
				marker.setMap(null);
			}
		});
	})
	
	//垃圾桶btn
	function removeLand(e){
		if($(".more-land").length <= 6) {
				$('#add').show();
			}
		var name = e.previousElementSibling.value;
		var land = e.parentNode.parentNode;
		var landDiv = document.getElementById('land-div');
		landDiv.removeChild(land);
		//remove marker
		var marker = markers[name];
		marker.setMap(null);
	}
	
	/**************************** 表單驗證 ****************************/
	function verify(){
		var array = [];
		var landscapeArray = document.getElementsByName('landscape');
		var hourArray = document.getElementsByName('hour');
		var minArray = document.getElementsByName('min');
		var transArray = document.getElementsByName('transport');
		var tourName = document.getElementById('tourName');
		var tourPeo = document.getElementById('tourPeo');
		var tourPic = document.getElementById('tourPic');
		var tourdetail = document.getElementById('tourDetail').value;
		var peoValue = tourPeo.value;
		if(tourName.value==""){
			document.getElementById('nameError').innerText='請輸入行程名稱';
		}
		if(tourName.value!=""){
			document.getElementById('nameError').innerText='';
		}
		if(tourPeo.value==""){
			document.getElementById('peoError').innerText='請輸入人數';
		}
		if(tourPeo.value!=""){
			document.getElementById('peoError').innerText='';
		}
		if(parseInt(peoValue, 10)>99){
			document.getElementById('peoError').innerText='超過人數上限';
		}
		if(parseInt(peoValue, 10)<=99){
			document.getElementById('peoError').innerText='';
		}
		if(tourPic.value==""){
			document.getElementById('picError').innerText='請上傳圖片';
		}
		if(tourPic.value!=""){
			document.getElementById('picError').innerText='';
		}
		if(tourdetail.length>1000){
			document.getElementById('detailError').innerText='字數超過上限';
		}
		if(tourdetail.length<=1000){
			document.getElementById('detailError').innerText='';
		}
		for(var i=0;i<landscapeArray.length;i++){
			var value = landscapeArray[i].value;
			if(value == ""){
				document.getElementById('landError').innerText='景點請勿空白';
				return
			}
			var hour = ""; var min=""; var trans="";
			if(i!=(landscapeArray.length-1)){
				hour = hourArray[i+1].value;
				min = minArray[i+1].value;
				trans = transArray[i+1].value;
				if(trans == ""){
					document.getElementById('transError').innerText='請輸入交通方式';
					return
				}
			}
			if(!landscape.hasOwnProperty(value)){
				alert('請點選選單內景點');
				return
			}
			if(landscape.hasOwnProperty(value)){
				var object = {'landscape_no':landscape[value].no,'landscape_min':min,'landscape_hr':hour,'landscape_trans':trans};
				array.push(object);	
			}
		}
		document.getElementById('landError').innerText='';
		document.getElementById('transError').innerText='';
		var jsonStr = JSON.stringify(array);
		document.getElementById('jsonStr').value = jsonStr;
		if(tourName.value!="" && tourPeo!="" && tourPic.value!="" && parseInt(peoValue, 10)<=99 && tourdetail.length<=1000){
			uploadForm.onsubmit=true;	
		}
	}
	
	//小按鈕
	function magic(){
		$('#area').val('台中');
		$('#tourName').val('煞氣a狼王帶你玩台中');
		$('#tourPeo').val(5);
		$('#tourDetail').val('狼王免費提供麗寶樂園票券!!!!!!!!!!!!!!!!!!!!早上先去麗寶樂園玩玩碰碰車、跟狼王搭摩天輪、玩水、騎旋轉木馬懷念童年。備註：狼王不敢玩斷軌，狼王會怕。'
				+'晚上去逢甲夜市，必吃乾燒蛤，狼王最愛吃乾燒蛤。最後去宮原眼科吃冰當盤子');
	}
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANKHSWauEfGG_DUy-MEfvdocMH1e1KrMI&callback=initMap"
    async defer></script>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRMs" crossorigin="anonymous"></script>
  </body>
</html>