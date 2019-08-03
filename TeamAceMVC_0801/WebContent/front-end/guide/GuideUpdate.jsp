
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

<%
GuideVO guideVO = (GuideVO)session.getAttribute("guideVO");
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
    <title>修改伴遊資訊</title>
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
    </style>
  </head>
  <body onload="onload()">
  
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
			<p class="title">EDIT PROFILE</p>
			<%-- 錯誤表列 --%> 
			<div class="error">
			<c:if test="${not empty errorMsgs}">
				<c:forEach var="message" items="${errorMsgs}">
					<font style="color:red;font-size:12px;">${message}&nbsp</font>
				</c:forEach>
			</c:if>
			</div>
	 			<form method="post" action="guide.do" enctype="multipart/form-data">
	 		 		<div class="form-group">
	 			 		<label for="area">伴遊區域</label>
	 			 	    <select class="form-control " id="area" name="guide_area">
	 			 	      <option>台北市</option>
	 			 	      <option>新北市</option>
	 			 	      <option>桃園市</option>
	 			 	      <option>新竹縣</option>
	 			 	      <option>苗栗縣</option>
	 			 	      <option>台中市</option>
	 			 	      <option>彰化縣</option>
	 			 	      <option>雲林縣</option>
	 			 	      <option>南投縣</option>
	 			 	      <option>嘉義縣</option>
	 			 	      <option>台南市</option>
	 			 	      <option>高雄市</option>
	 			 	      <option>屏東縣</option>
	 			 	      <option>台東縣</option>
	 			 	      <option>花蓮縣</option>
	 			 	      <option>宜蘭縣</option>
	 			 	      <option>基隆市</option>
	 			 	      <option>澎湖縣</option>
	 			 	    </select>
					<br>
					
					<label for="hobby">你的興趣</label> <font size="2" color="gray">請用空白鍵隔開</font>
					<input type="text" id="hobby" name="guide_hobby" class="form-control" value="${guideVO.guide_hobby}">
					<br>
					
					<div class="form-group form-row">
						<div class="col">
							<label for="stlang">第一外語</label>
							<select class="form-control " id="stlang" name="guide_stLang">
		 			 	      <option value="">無</option>
 	     					  <option value="英文, English">英文</option>
		 			 	      <option value="日文, Japanese">日文</option>
		 			 	      <option value="韓文, Korean">韓文</option>
		 			 	      <option value="英文, English">粵語</option>
		 			 	      <option value="西班牙文, Spanish">西班牙文</option>
		 			 	      <option value="法文, French">法文</option>
		 			 	      <option value="德文, German">德文</option>
		 			 	    </select>
						</div>
						 <div class="col">
							 <label for="ndlang">第二外語</label>
							 <select class="form-control " id="ndlang" name="guide_ndLang">
		 			 	      <option value="">無</option>
		 			 	      <option value="英文, English">英文</option>
		 			 	      <option value="日文, Japanese">日文</option>
		 			 	      <option value="韓文, Korean">韓文</option>
		 			 	      <option value="英文, English">粵語</option>
		 			 	      <option value="西班牙文, Spanish">西班牙文</option>
		 			 	      <option value="法文, French">法文</option>
		 			 	      <option value="德文, German">德文</option>
		 			 	    </select>
						</div>
				   </div>
				   
	 			 	   <label for="intro">自我介紹</label>
	 			 	   <textarea class="form-control" id="intro" name="guide_intro" rows="4" placeholder="上限500字"></textarea>
				   <br>
				   <div class="form-group form-row">
				   		<div class="col">
				   			<label>上傳旅伴頭貼</label> <font size="2" color="gray">上方預覽</font><br>
<!-- 				   			<input type="file" class="form-control-file" name="guide_pic" targetID="guide_pic" onchange="readURL(this)" accept="image/gif, image/jpeg, image/png"> -->
				   			<div class="input-group mb-3">
							  <div class="custom-file">
							    <input type="file" class="custom-file-input" name="guide_pic" id="guidePic" targetID="guide_pic" onchange="readURL(this)" aria-describedby="guide_pic" accept="image/gif, image/jpeg, image/png">
							    <label class="custom-file-label" for="guidePic">上傳圖片</label>
							  </div>
							</div>
				   		</div>
				   		<div class="col">
				   			<label>上傳封面</label> <font size="2" color="gray">上方預覽</font><br>
<!-- 				   			<input type="file" class="form-control-file" name="guide_cover" targetID="cover" onchange="readURL(this)" accept="image/gif, image/jpeg, image/png"> -->
				   			<div class="input-group mb-3">
							  <div class="custom-file">
							    <input type="file" class="custom-file-input" name="guide_cover" id="guideCover" targetID="cover" onchange="readURL(this)" aria-describedby="guide_cover" accept="image/gif, image/jpeg, image/png">
							    <label class="custom-file-label" for="guideCover">上傳圖片</label>
							  </div>
							</div>
				   		</div>
				   </div> 			 	   
	 			 	   
				 </div>
				
				 <input type="hidden" name="action" value="update">
	 			 	 <div class="submit">
	 			 	  	<button type="submit" class="btn" style="margin-top: 30px;background-color: dimgray;font-size: 13px;color:#fff;">確認修改</button>
	 			 	 </div>
			</form>
			</div>
		
	</div>
</div>

 <script type="text/javascript">
 	function onload(){
 		document.getElementById("intro").value =`'${guideVO.guide_intro}'`;
 		document.getElementById("stlang").value ='${guideVO.guide_stLang}';
 		document.getElementById("ndlang").value ='${guideVO.guide_ndLang}';
 		document.getElementById("area").value ='${guideVO.guide_area}';
 	}
 
 	function readURL(input){

		if(input.files && input.files[0]){
			var imageTagID = input.getAttribute("targetID");
			var reader = new FileReader();

			reader.onload = function (e) {
				var img = document.getElementById(imageTagID);
				img.setAttribute("src", e.target.result)
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

 </script>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>