<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="com.guide.model.*"%>
<%@ page import="com.mem.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<title>GuideAdd</title>
 <style type="text/css">
    	body{
    		font-family: Microsoft JhengHei;
    		background-image: url(image/img_slide-01.jpg);
    		background-repeat: no-repeat;
    		background-attachment: fixed;
    		background-position: center;
    		background-size: cover;
    		letter-spacing:1.5px;
    	}
    	.container{
    		width: 680px;
    		height: 900px;
    		margin-top: 5%;
    		margin-bottom: 5%;
			background-color: rgba(255,255,255,0.7);
			border-radius: 2%;
    	}
    	h5{
    		margin-top: 25px;
    	}
    	.error{
    		height:30px;
    	}
		.pic{
			width:200px;
			height: 200px;
			background-color:#eee;
		}
		#pic{
			width:auto;
			height:200px;
		}
		.submit{
			 text-align: center;
			 margin-top: 30px;
		}
		.btn{
/* 			 background-color: dimgray; */
			 font-size: 13px;
			 color:#fff;
		}

    </style>
  </head>
  <body onload="onload()">
<%--   <%@ include file="/front-end/header.file" %> --%>
 <div class="container">
 	<div class="row justify-content-center">
 		<div class="col-10">
 		
 			<h5>成為在地旅伴</h5><p>
 			<%-- 錯誤表列 --%>
			<div class="error">
			<c:if test="${not empty errorMsgs}">
				<c:forEach var="message" items="${errorMsgs}">
					<font style="color:red;font-size:12px;">${message}&nbsp</font>
				</c:forEach>
			</c:if>
			</div>
 			<form method="post" action="guide.do" enctype="multipart/form-data" onsubmit="return false" id="addForm">
 		 		<div class="form-group">
 			 		<label for="area">伴遊區域</label> 
 			 		<font style="font-size:10px;color:red;" id="errorArea">&nbsp</font>
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
 			 	    </select>
					<br>
					
					<label for="hobby">你的興趣</label> <font size="2" color="gray">請用空白鍵隔開
					</font>&nbsp<font style="font-size:10px;color:red;" id="errorHobby"></font>
					<input type="text" id="hobby" name="guide_hobby" class="form-control" value="${param.guide_hobby}">
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
				   
 			 	   <label for="intro">向旅客介紹一下自己</label>
 			 	   <font style="font-size:10px;color:red;" id="errorIntro">&nbsp</font>
 			 	   <textarea class="form-control" id="intro" name="guide_intro" rows="4" placeholder="上限500字"></textarea>
				   <br>
				   
 			 	   <label for="pic">上傳旅伴頭貼</label>
 			 	   <font style="font-size:10px;color:red;" id="errorPic">&nbsp</font><br>
<!--  			 	   <input type="file" class="form-control-file" id="guidePic" name="guide_pic" targetID="pic" onchange="readURL(this)" accept="image/gif, image/jpeg, image/png"> -->
				   <div class="input-group mb-3" style="width:520px">
					  <div class="custom-file">
					    <input type="file" class="custom-file-input" name="guide_pic" id="guidePic" targetID="pic" onchange="readURL(this)" aria-describedby="guidePic" accept="image/gif, image/jpeg, image/png">
					    <label class="custom-file-label" for="guidePic">上傳圖片</label>
					  </div>
					  <button type="button" class="btn btn-info" style="margin-left:140px" onclick="magic()">神奇小按鈕</button>
					</div>
				 </div>
				 
 			 	 <div class="pic">
 			 	  	<img id="pic" src="ReadMemPic?mem_no=${memberVO.mem_no}">
 			 	 </div>
				
				 <input type="hidden" name="action" value="insert">
 			 	  <div class="submit">
 			 	  	<button type="submit" class="btn btn-secondary" onclick="verify()">送出</button>
 			 	 </div>
			</form>
		</div>
	</div>
</div>
 <script type="text/javascript">
 
	function onload(){
 		document.getElementById("intro").value ='${param.guide_intro}';
 		document.getElementById("stlang").value ='${param.guide_stLang}';
 		document.getElementById("ndlang").value ='${param.guide_ndLang}';
 		document.getElementById("area").value ='${param.guide_area}';
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
 	

	 //表單驗證
	 function verify(){
	 	var addForm = document.getElementById("addForm");
	 	var area = document.getElementById("area");
	 	var hobby = document.getElementById("hobby");
	 	var intro = document.getElementById("intro");
// 	 	var guidePic = document.getElementById("guidePic");
	 	var errorArea = document.getElementById("errorArea");
	 	var errorHobby = document.getElementById("errorHobby");
	 	var errorIntro = document.getElementById("errorIntro");
	 	var errorPic = document.getElementById("errorPic");
		var hobbyReg = /[(\u4e00-\u9fa5)(a-zA-Z)(\s)]*/;
	 	if(area.value == ""){
	 		errorArea.innerText="請選擇伴遊區域";
	 	}
	 	if(area.value != ""){
	 		errorArea.innerText="";
	 	}
	 	if(hobby.value != ""){
	 		errorHobby.innerText="";
	 	}
	 	if(intro.value == ""){
	 		errorIntro.innerText="請填寫自我介紹";
	 	}
	 	if(intro.value != ""){
	 		errorIntro.innerText="";
	 	}
// 	 	if(guidePic.value == ""){
// 	 		errorPic.innerText="請上傳旅伴頭貼";
// 	 	}
// 	 	if(guidePic.value != ""){
// 	 		errorPic.innerText="";
// 	 	}
	 	if(!(/^[(\u4e00-\u9fa5)(a-zA-Z)(\s)]*$/.test(hobby.value))){
	 		errorHobby.innerText="興趣只能輸入中、英文字母，並請用空白鍵隔開";
	 	}
	 	if(/^[(\u4e00-\u9fa5)(a-zA-Z)(\s)]*$/.test(hobby.value)){
	 		errorHobby.innerText="";
	 	}
	 	if(hobby.value == ""){
	 		errorHobby.innerText="請填寫興趣";
	 	}
	 	if((area.value != "") && (hobby.value != "") && (intro.value != "")){
	 		addForm.onsubmit=true;
	 	}
	 }
	 
	 //小按鈕
	 function magic(){
		 $('#area').val('台中市');
		 $('#hobby').val('狼人殺 逛夜市 JAVA');
		 $('#stlang').val('英文, English');
		 $('#ndlang').val('德文, German');
		 $('#intro').val('大家好，我是土豪狼王，我最喜歡寫Java。');
	 }

 </script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>