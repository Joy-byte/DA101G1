<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.guidetour.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="com.mem.model.*"%>
<%@ page import="org.json.*"%>
<%@ page import="com.gtlist.model.*"%>
<%@ page import="com.gtclist.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />    
<jsp:useBean id="guideSvc" scope="page" class="com.guide.model.GuideService" />
<jsp:useBean id="gtListSvc" scope="page" class="com.gtlist.model.GTListService" />
<jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" /> 
<jsp:useBean id="gtcListSvc" scope="page" class="com.gtclist.model.GTCListService" />   
<jsp:useBean id="guideTourSvc" scope="page" class="com.guidetour.model.GuideTourService" />    

<%
MemberVO  memVO = (MemberVO)session.getAttribute("memberVO");

GuideTourService guideTourSvc2 = new GuideTourService();
List<GuideTourVO> list = guideTourSvc2.getAll();
JSONArray array = new JSONArray();
for (GuideTourVO vo : list) {
	JSONObject obj = new JSONObject();
	JSONArray landArray = new JSONArray();
	JSONArray catArray = new JSONArray();
	String guideTour_no = vo.getGuideTour_no();
	String guide_no = vo.getGuide_no();
	String guide_memno = guideSvc.getOneGuide(guide_no).getMem_no();
	obj.put("guideTourNo", guideTour_no);
	obj.put("guideTourRate", guideTourSvc.getGuideTourRate(guideTour_no));
	obj.put("guideMemName",memSvc.getOneMember(guide_memno).getMem_name());
	obj.put("guideMemNo", guide_memno);
	obj.put("guideTourName", vo.getGuideTour_name());
	obj.put("guideNo",guide_no);
	List<GTListVO> landList = gtListSvc.getGTListByGTNo(guideTour_no);
	for(GTListVO gtListVO : landList) {
		String landscapeName = landscapeSvc.getOneLas(gtListVO.getLandscape_no()).getLandscape_name();
		landArray.put(landscapeName);
	}
	obj.put("landscape", landArray);
	List<GTCListVO> catList = gtcListSvc.getCategoryByGTno(guideTour_no);
	for(GTCListVO gtcListVO : catList) {
		String categoryName = (gtcListVO.getCategory());
		catArray.put(categoryName);
	}
	obj.put("category", catArray);
	array.put(obj);
}
pageContext.setAttribute("jsonArray",array.toString());
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
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <title>GuideTour</title>
   
    <style type="text/css">
    	body{
    		/*min-height: 1000px;*/
    		font-family: Microsoft JhengHei;
    		letter-spacing: 2px;
    	}
		.first{
			height:370px;	
		}

		.top{
			padding-top: 6%;
			color:#fff;
		}
    	.content{
    		padding:80px;
    		min-height:900px;
			background-color: #eee;
    	}
		.right{
			margin-left:30px;
		}
		#cover{
			position: fixed;
			width: 100%;
			height:auto;
			z-index: -10;
			filter:brightness(0.6);
		}
		.city{
			display: block;
			line-height: 25px;
		}
		.city li{
			display: block;
		    margin: 0 10px 10px 0;
		    float: left;
		    border: 1px solid #ddd;
		    border-radius: 5%;
		    font-size: 16px;
		    padding: 0 20px;
		    list-style: none;
		    transition: all .3s; 
		}
		.city li:hover{
/* 			background-color: #E8E8E8; */
/* 			color:#363636; */
			transition: all .3s; 
			cursor:pointer;	
		}
		.search-title{
			margin:10px 0 10px 0;
			font-size: 16px;
			letter-spacing: 4px;
		}
		.list-group-item{
    		color:#666;
    		padding-left: 20px;
    		letter-spacing: 2px;
/*     		width:240px; */
    	}
    	#columns {
			column-width: 320px;
			column-gap: 15px;
		 	width: 90%;
			max-width: 1100px;
			margin: 0px auto;
		}
		#columns .guidetour {
			position:relative;
			background: #fefefe;
			border: 2px solid #fcfcfc;
/* 			box-shadow: 0 1px 2px rgba(34, 25, 25, 0.4); */
			margin: 0 2px 30px 0;
			padding-bottom: 15px;
/* 			transition: opacity .2s ease-in-out; */
		 	display: inline-block;
		 	width: 330px;
		}
		#columns .guidetour .guidetour-pic{
			position: relative;
			min-height:200px;
			background-color:gray;
		}
		#columns .guidetour .guidetour-pic img{
			width: 100% ;height: auto;
		}
		.tour-pic {
			filter:brightness(0.8);
		}
		.name{
			position: absolute;
			bottom:90px;
			margin-left: auto;
			margin-right: auto;
			left: 0;
			right: 0;
			text-align: center;
			color:#fff;
		}
		#columns .guidetour .guidetour-pic .guide-pic-box{
			position:absolute;
			bottom:-55px; /*圖片高度的一半*/
			width: 140px;
			height:140px;
			background-color: gray;
			border-radius: 50%;
			overflow: hidden;
			left:50%;
			margin-left:-70px;
			border:3px solid #fff;
		}
		#columns .guidetour .info {
			color: #444;
		  	line-height: 1.5;
		  	margin:70px 15px 0px 15px; /*上右下左*/
		}

		#columns:hover .guidetour:not(:hover) {
/* 			opacity: 0.4; */
		}
		.landscape{
			font-size: 16px;
			margin-bottom: 10px;
		}
		.info .more {
			padding: 6px 10px; 
			border:2px solid #919191;
			color:#919191;
			border-radius: 2.5em;
			font-size:10px;
			margin: 0px 0px 0 230px;
			display:flex;
			justify-content: center;
			font-weight:bold;
			background:#fff;
			letter-spacing:2px;
		}
		.info .more:hover{
			background-color: #919191;
			color:#fff;
			transition: all 0.3s; 
		}
		.gc{
			position:absolute;
			left:8px;
			bottom:8px;
			margin:10px;
		}
		.rate{
			position:absolute;
			top:10px;
			right:5px;
			color:#fff;
			margin:10px;
			background-color: #f0ad4e;
			border-radius: 2.5em;
			padding: 6px 10px; 
			font-size: 13px;
		}
		a {
   			text-decoration: none !important;
		}
		
		#pageLink{
			margin-top:30px;
		}
		#loading{
			position:fixed;
			z-index:500;
			width:80px;
			height:80px;
			border:#9ecce0 4px solid;	/*dashed:虛線*/
			border-bottom: 0px;
			border-right: 0px;
			border-radius:85px;
			animation:loopAni 1s;
			animation-iteration-count:infinite;
			animation-timing-function: linear; 
			top:60%;
			left:55%;
			visibility:hidden;
			}
		@keyframes loopAni{
			from{transform:rotate(0deg);}
			to{transform: rotate(360deg)}
		}
		
    </style>
  </head>
  <body>
  <%@ include file="/front-end/header.file" %>
  
	<div class="outer">
		<img src="image/banner.jpg" id="cover">
		<div class="container-fluid first">
			<div class="row justify-content-center top">
				<div class="col-4">
						<ul class="city">
							<div class="search-title">SEARCH BY CITY</div>
							<li id="cityAll" class="chooseCity" style="background:#E8E8E8;color:#363636;">全部</li>
							<li id="city1" class="chooseCity">台北</li>
							<li id="city2" class="chooseCity">桃園</li>
							<li id="city3" class="chooseCity">新竹</li>
							<li id="city4" class="chooseCity">苗栗</li>
							<li id="city5" class="chooseCity">台中</li>
							<li id="city6" class="chooseCity">南投</li>
							<li id="city7" class="chooseCity">彰化</li>
							<li id="city8" class="chooseCity">雲林</li>
							<li id="city9" class="chooseCity">嘉義</li>
							<li id="city10" class="chooseCity">台南</li>
							<li id="city11" class="chooseCity">高雄</li>
							<li id="city12" class="chooseCity">屏東</li>
							<li id="city13" class="chooseCity">台東</li>
							<li id="city14" class="chooseCity">花蓮</li>
							<li id="city15" class="chooseCity">宜蘭</li>
							<li id="city16" class="chooseCity">基隆</li>
						</ul>
				</div>

				<div class="col-4">
						<ul class="city">
							<div class="search-title">SEARCH BY CATEGORY</div>
							<li id="catAll"  class="chooseCat" style="background:#E8E8E8;color:#363636;">全部</li>
							<li id="cat1"  class="chooseCat" >風景</li>
							<li id="cat2"  class="chooseCat" >人文</li>
							<li id="cat3"  class="chooseCat" >展覽</li>
							<li id="cat4"  class="chooseCat" >大自然</li>
							<li id="cat5"  class="chooseCat" >登山</li>
							<li id="cat6"  class="chooseCat" >美食</li>
							<li id="cat7"  class="chooseCat" >夜生活</li>
							<li id="cat8"  class="chooseCat" >運動</li>
							<li id="cat9"  class="chooseCat" >娛樂</li>
							<li id="cat10"  class="chooseCat" >購物</li>
							<li id="cat11"  class="chooseCat" >都市</li>
							<li id="cat12"  class="chooseCat" >鄉村</li>
							<li id="cat13"  class="chooseCat" >古蹟</li>
							<li id="cat14"  class="chooseCat" >休閒</li>
						</ul>
				</div>
			</div>
		</div>
		<div class="container-fluid second">
			<div class="row justify-content-center content">

				<div class="col-2 left" >
					<div class="btn-group">
						<input type="text" class="form-control" id="searchString" placeholder="search">
						<!-- 查地區跟類別用 -->
						<input type="hidden" id="conditionCity" value="全部"><!-- 地區 -->
						<input type="hidden" id="conditionCat" value="全部"><!-- 類別 -->
						<button class="btn btn-secondary" id="search"><i class="fa fa-search"></i></button>		
					</div>
					<br>
					<div class="list-group list-group-flush">
						<div class="list-group-item" style="font-size: 18px;"><b>條列方式</b>
						</div>
						<a href="#" class="list-group-item order" id="order_new">最新發布</a>
						<a href="#" class="list-group-item order" id="order_rate">依評價排列</a>
					</div>
				</div>
				
				<div class="col-9 right">
					<div id="loading"></div>
					<div id="columns">
<%--  						<c:forEach var="guideTourVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<!-- 						<div class="guidetour"> -->
<!-- 							<div class="guidetour-pic"> -->
<%-- 								<img class="tour-pic" src="ReadTourPic?guideTour_no=${guideTourVO.guideTour_no}"> --%>
	
<!-- 								<div class="rate"> -->
<!--                                     行程評價 -->
<%--                                     	<span>評價  ${guideTourSvc.getGuideTourRate(guideTourVO.guideTour_no)}</span> --%>
<!-- 								</div> -->
<!-- 								<div class="name"> -->
<!-- 								會員名 -->
<%-- 									<span style="font-size: 20px;"><b>${memSvc.getOneMember(guideSvc.getOneGuide(guideTourVO.guide_no).mem_no).mem_name}</b></span> --%>
<!-- 								</div> -->
<!-- 								<div class="guide-pic-box"> -->
<%-- 									<img src="../guide/ReadPicture2?mem_no=${guideSvc.getOneGuide(guideTourVO.guide_no).mem_no}&type=guide_pic" class="guidepic"> --%>
<!-- 								</div> -->
<!-- 							</div> -->
								
<!-- 							<div class="info"> -->

<!-- 								<div class="landscape"> -->
<!-- 								行程名 -->
<%-- 								<span style="font-size: 18px;"><b>【${guideTourVO.guideTour_name}】</b></span><br> --%>
<!-- 								景點名 -->
<%-- 								<c:forEach var="gtListVO" varStatus="status" items="${gtListSvc.getGTListByGTNo(guideTourVO.guideTour_no)}"> --%>
<%-- 									${landscapeSvc.getOneLas(gtListVO.landscape_no).landscape_name} --%>
<%-- 									<c:if test="${not status.last}">  --%>
<!-- 										<i class="fa fa-arrow-circle-right"></i> -->
<%-- 									</c:if> --%>
<%-- 								</c:forEach> --%>
<!-- 								</div> -->
<!-- 								<div class="gc"> -->
<!-- 								分類名 -->
<%-- 								<c:forEach var="gtcListVO" items="${gtcListSvc.getCategoryByGTno(guideTourVO.guideTour_no)}"> --%>
<!-- 									<span style="margin:0 4px;	padding:4px; background:#70c7be;border-radius:3px 3px;color:#fff;font-size:13px;"> -->
<!-- 									#${gtcListVO.category} -->
<!-- 									</span> -->
<%-- 								</c:forEach> --%>
<!-- 								</div> -->
<!-- 								<form METHOD="get" ACTION="GuideTourDetail.jsp" name="more"> -->
<!-- 									<input type="hidden" name="action" value="readMore"> -->
<%-- 									<input type="hidden" name="guideTour_no" value="${guideTourVO.guideTour_no}"> --%>
<!-- 									<input class="more" type="submit" value="MORE"> -->
<!-- 								</form> -->
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 						</c:forEach> --%>
				</div>
			</div>
	</div>
</div>

	<script type="text/javascript">
	var array = JSON.parse('${jsonArray}');
	$(document).ready(function() {
		
	    //一開始的行程頁
		$.each(array, function(i, item){
   			jqueryAppend(item);
		});
	});
	
	</script>
	
	<script type="text/javascript">
	//標籤搜尋
	var preCityId='cityAll';
	var preCatId='catAll';
	
	$(document).ready(function() {
		$('.chooseCity').click(function(){
			$('#columns').fadeOut('2000');
			$('#loading').css('visibility','visible');
			document.getElementById(preCityId).style.backgroundColor="transparent";
			document.getElementById(preCityId).style.color="#fff";
			$(this).css("background-color","#E8E8E8");
			$(this).css("color","#363636");
			$('#conditionCity').val($(this).text());
			preCityId = $(this).attr("id");
			$.ajax({
				 type: "GET",
				 url: "GuideTour.do",
				 data: creatQueryString($('#conditionCity').val(), $('#conditionCat').val()),
				 dataType: "json",
				 success: function (data){
					document.getElementById('columns').innerText="";
					$.each(data, function(i, item){
						jqueryAppend(item);
					});
					$('#loading').css('visibility','hidden');
					$('#columns').fadeIn('2000');
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
		});
		
		$('.chooseCat').click(function(){
			$('#columns').fadeOut('2000');
			$('#loading').css('visibility','visible');
			document.getElementById(preCatId).style.backgroundColor="transparent";
			document.getElementById(preCatId).style.color="#fff";
			$(this).css("background-color","#E8E8E8");
			$(this).css("color","#363636");
			$('#conditionCat').val($(this).text());
			preCatId = $(this).attr("id");
			$.ajax({
				 type: "GET",
				 url: "GuideTour.do",
				 data: creatQueryString($('#conditionCity').val(), $('#conditionCat').val()),
				 dataType: "json",
				 success: function (data){
					document.getElementById('columns').innerText="";
					$.each(data, function(i, item){
						jqueryAppend(item);
					});
					$('#loading').css('visibility','hidden');
					$('#columns').fadeIn('2000');
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
		});
		
		$('.order').click(function(){
			var id = $(this).attr("id");
			$('#columns').fadeOut('2000');
			$('#loading').css('visibility','visible');
			$.ajax({
				 type: "GET",
				 url: "GuideTour.do",
				 data: queryString($('#conditionCity').val(), $('#conditionCat').val(),id,$('#searchString').val()),
				 dataType: "json",
				 success: function (data){
					document.getElementById('columns').innerText="";
					$.each(data, function(i, item){
						jqueryAppend(item);
					});
					$('#loading').css('visibility','hidden');
					$('#columns').fadeIn('2000');
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
		});
		
		$('#search').click(function(){
			var id = $(this).attr("id");
			$('#columns').fadeOut('2000');
			$('#loading').css('visibility','visible');
			$.ajax({
				 type: "GET",
				 url: "GuideTour.do",
				 data: searchString($('#conditionCity').val(), $('#conditionCat').val(),$('#searchString').val()),
				 dataType: "json",
				 success: function (data){
					document.getElementById('columns').innerText="";
					$.each(data, function(i, item){
						jqueryAppend(item);
					});
					$('#loading').css('visibility','hidden');
					$('#columns').fadeIn('2000');
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
		});
	});
	
	function creatQueryString(city, category){
		console.log("city:"+city+"; category:"+category);
		var queryString= {"action":"getGuideTour", "city":city, "category":category};
		return queryString;
	};
	
	function queryString(city, category, order, search){
		console.log("city:"+city+"; category:"+category);
		var queryString= {"action":"getGuideTour", "city":city, "category":category,"orderby":order,"searchString":search};
		return queryString;
	};
	
	function searchString(city, category, search){
		var queryString= {"action":"getGuideTour", "city":city, "category":category,"searchString":search};
		return queryString;
	}
	function jqueryAppend(item){
		$('#columns').append("<div class='guidetour'>"
				+"<div class='guidetour-pic'>"
					+"<img class='tour-pic' src='ReadTourPic?guideTour_no="+item.guideTourNo+"'>"
					+"<div class='rate'>"
                        +"<span style='font-size:16px'>評價  "+item.guideTourRate+"</span>"
					+"</div>"
					+"<div class='name'>"
						+"<span style='font-size: 22px;'><b>"+item.guideMemName+"</b></span>"
					+"</div>"
					+"<div class='guide-pic-box'>"
						+"<img src='../guide/ReadPicture2?mem_no="+item.guideMemNo+"&type=guide_pic' class='guidepic'>"
					+"</div>"
				+"</div>"
				+"<div class='info'>"
					+"<div class='landscape' style='font-size:18px'>"
					+"<span style='font-size: 21px;'><b>【"+item.guideTourName+"】</b></span><br>"
					+item.landscape.map(function(landscape,i){
						if(i == item.landscape.length-1){
							return landscape+"</div><div class='gc'>";
						}else{
							return landscape+"<i class='fa fa-arrow-circle-right'></i>";
						}
					}).join('')
					+item.category.map(function(category,i){
						return "<span style='margin:0 4px;padding:4px; background:#70c7be;border-radius:3px 3px;color:#fff;font-size:15px;'>#"+category+"</span>"
					}).join('')
					+"</div>"
					+"<form METHOD='get' ACTION='GuideTourDetail.jsp' name='more'>"
					+"<input type='hidden' name='action' value='readMore'>"
					+"<input type='hidden' name='guideTour_no' value="+item.guideTourNo+">"
					+"<input type='hidden' name='guide_no' value="+item.guideNo+">"
					+"<input class='more' type='submit' value='MORE'>"
					+"</form>"
					+"</div>"
					+"</div>"	
		);
	}
    </script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>