<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ page import="com.order_detail.model.*"%>
<%@ page import="java.util.*"%>
<%
	Order_detailVO order_detailVO = (Order_detailVO) request.getAttribute("orderVO");
%>
<%
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
%>

<%
	String amount = (String) request.getAttribute("amount");
	String pointafter = (String) request.getAttribute("pointafter");
%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	String memname = memberVO.getMem_name();
%>


<!-- 測試已經登入的狀態 -->


<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>CheckOut</title>
<!-- Bootstrap CSS -->

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">


<style>
body {
	font-family: Microsoft JhengHei;
	letter-spacing: 2px;
}

.ui-autocomplete {
	position: absolute;
	z-index: 1000;
	cursor: default;
	padding: 0;
	margin-top: 2px;
	list-style: none;
	background-color: #ffffff;
	border: 1px solid #ccc;
}

.ui-autocomplete>li {
	padding: 3px 20px;
}

.ui-autocomplete>li.ui-state-focus {
	background-color: #DDD;
}

.ui-helper-hidden-accessible {
	display: none;
}

.item1 {
	background-color: #ffffff;
	width: 620px;
	height: 530px;
	padding: 20px;
	margin: 20px;
	margin-top: 20px;
	font-size: 20px;
	flex-grow: 1;
	flex-direction: cloum;
}

.item2 {
	background-color: #ffffff;
	width: 320px;
	height: 530px;
	padding: 20px;
	margin-right: 20px;
	margin-top: 20px;
	font-size: 20px;
	flex-grow: 1;
	flex-direction: cloum;
}

.item button {
	margin-left: 50px;
}

.productimg img {
	width: 80px;
	height: 80px;
	margin: 5px;
	flex-grow: 1;
}

.qty {
	width: 35px;
	height: 35px;
	text-align: center;
	border: 0;
}

.plus {
	width: 35px;
	height: 35px;
	border: 1px solid #aaa;
	background: none;
}

.minus {
	width: 35px;
	height: 35px;
	border: 1px solid #aaa;
	background: none;
}

.delinfo {
	width: 1100px;
	height: 570px;
	font-size: 20px;
	flex-grow: 1;
	display: flex;
	flex-direction: row;
	background-color: #f0f0f0;
}

.address {
	display: flex;
	margin: 10px;
}

.price {
	display: flex;
	margin: 5px;
}

.my-selector-c {
	display: flex;
	flex-direction: row;
}

.my-selector-c select {
	margin-left: 10px;
	width: 300px;
}

#map {
	height: 400px; /* The height is 400 pixels */
	width: 100%; /* The width is the width of the web page */
}

.map {
	font-family: Microsoft JhengHei;
	font-size: 22px;
}

#mapbtn {
	position: relative;
	margin-top: 10px;
	text-align: center;
}

.mapadjust {
	display: flex;
	flex-direction: row;
}

#findhoteladr {
	display: none;
}

.navbar {
	z-index: 10;
	position: fixed;
	width: 100%;
	top: 0px;
}

#alert {
	position: fixed;
	z-index: 20;
	width: 1100px;
	height: 50px;
	background-color: #ffeedd;
	color: #ea7500;
	top: 60px;
	left: 21%;
	display: flex;
	text-align: center;
	line-height: 50px;
	font-size: 16pt;
	justify-content: center;
	border: 1px solid #f0f0f0;
	visibility: hidden;
}
</style>

</head>
<body onload="todeposit()">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo01"
			aria-controls="navbarTogglerDemo01" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
			<a class="navbar-brand" href="#">Logo</a>
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/home/TeamAce.jsp">首頁
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/itinerary/itineraryUpload.jsp">發佈</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/game/gameroom.jsp">誰是臥底</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/product/Shop.jsp">商城</a>
				</li>

				<div class="btn-group">
					<button type="button" class="btn  dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
						style="background: #fff !important;">在地旅伴 yo</button>
					<div class="dropdown-menu" id="dropdown-menu1">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/front-end/guide/GuideAdd.jsp">成為在地旅伴</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/front-end/guidetour/GuideTourList.jsp">伴遊行程</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/front-end/resorder/MemResOrder.jsp">我的預約訂單</a>
						<!-- 				    <div class="dropdown-divider"></div> -->
						<c:if test="${guideSvc.getOneGuideByMemNo(memberVO.mem_no)!=null}">
							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/front-end/guide/GuideMainPage.jsp">在地旅伴管理</a>
						</c:if>
					</div>
				</div>

				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${memberVO.mem_no}">個人頁面</a>
				</li>
			</ul>
			<div class="form-inline my-2 my-lg-0" id="headright">
				<c:if test="${memberVO.mem_name !=null}">
					<a style="text-decoration: none; color: black"
						href="<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${memberVO.mem_no}">
						${memberVO.mem_name} </a>&nbsp&nbsp你好 ! 點數:${memberVO.mem_amo}
        	</c:if>
				<button style="margin-left: 40px;"
					class="btn btn btn-outline-info my-2 my-sm-0" onclick="loginbtns()">
					<b id="isLogin" name="${ memberVO.mem_name}">登入</b>
				</button>
			</div>
		</div>
	</nav>

	<!-- 餘額不足提示 -->

	<div id="alert">
		<div>oops!您的餘額不足!請修改商品數量或前往儲值!</div>
		<div>
			<a
				href="<%=request.getContextPath()%>/front-end/deposit/addDeposit2.jsp">
				<button type="button" class="btn btn-outline"
					style="width: 200px; margin-left: 20px; margin-bottom: 8px; border: 1px solid #ea7500; color: #ea7500;">前往儲值</button>
			</a>
		</div>
	</div>

	<div class="container">
		<table class="table" style="margin-top: 115px">
			<thead>
				<tr>
					<th scope="col">商品資訊</th>
					<th scope="col">單價</th>
					<th scope="col">數量</th>
					<th scope="col">小計</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<c:forEach var="productVO" items="${shoppingcart}">
				<tbody>
					<tr>
						<th scope="row">
							<div class="productimg">
								<img src="PhotoReader3.do?product_no=${productVO.product_no}">
								${productVO.product_name}
							</div>

						</th>
						<td><span>NT$</span> <span id="price${productVO.product_no}">
								${productVO.product_pr}</span></td>
						<td><input type='button' id="minus${productVO.product_no}"
							onclick="runMinus('${productVO.product_no}')"
							name='${productVO.product_no}' value='-' class='minus'
							field='quantity' /> <input type='hidden'
							value='${productVO.product_no}' /> <input type='text'
							id="qty${productVO.product_no}" name='quantity'
							value='${productVO.product_quan}' class='qty'
							style='width: 30px;' /> <input type='button'
							onclick="runPlus('${productVO.product_no}')"
							name='${productVO.product_no}' value='+' class='plus'
							field='quantity' /></td>
						<td style="width: 200px"><span>NT$</span> <span
							id="total${productVO.product_no}"> ${productVO.product_pr * productVO.product_quan}</span></td>
						<td>
							<div>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/ShoppingServlet">

									<button type="button" class="close" aria-label="Close"
										name='${productVO.product_no}'
										onclick="deleteproduct('${productVO.product_no}')">
										<span aria-hidden="true">&times;</span>
									</button>
									<input type="hidden" name="product_no"
										value="${productVO.product_no}"> <input type="hidden"
										name="action" value="deleteFromCheckOut">
								</FORM>
							</div>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>

		<form method="post"
			action="<%=request.getContextPath()%>/ShoppingServlet">
			<div class="delinfo">

				<div class="item1">

					<div class="address">顧客姓名</div>
					<div class="address">
						<input type="text" class="form-control" name="order_cusname"
							value="<%=(order_detailVO == null) ? memname : order_detailVO.getOrder_cusname()%>">
					</div>

					<div class="address">收件人電話</div>
					<div class="address">
						<input type="phoneno" class="form-control" name="order_cusphone"
							value="<%=(order_detailVO == null) ? memberVO.getMem_tel() : order_detailVO.getOrder_cusphone()%>">
					</div>

					<div class="address">配送方式</div>
					<div class="address">
						<select class="form-control" style="width: 650px"
							onchange="showbtn(this)">
							<option value="bypost">郵寄</option>
							<option value="tohotel">旅店取貨</option>
							<option value="webservice">不須出貨</option>
						</select>
					</div>
					<div class="address">配送地址(旅店地址)</div>

					<!-- 點選郵寄顯示此區 -->
					<div class="my-selector-c" id="selector">
						<div>
							<select class="county form-control"></select>
						</div>
						<div>
							<select class="district form-control"></select>
						</div>
					</div>

					<!-- 點選飯店取貨顯示此區 -->
					<div class="address" id="findhoteladr" Style="margin-top: 10px; flex-direction:column">
						<div style="color:#ea7500; font-size:16px;">1.&nbsp選擇縣市&nbsp2.&nbsp輸入旅店名稱&nbsp3.點擊搜尋</div>
						<div style="margin-top:10px; margin-bottom:5px; display:flex">
							<div id="selector" Style="width: 150px">
								<select class="county form-control" id="cityselected"
									name="county2">
									<option value="">選擇縣市</option>
									<option value="臺北市" data-index="0">臺北市</option>
									<option value="基隆市" data-index="1">基隆市</option>
									<option value="新北市" data-index="2">新北市</option>
									<option value="宜蘭縣" data-index="3">宜蘭縣</option>
									<option value="桃園市" data-index="4">桃園市</option>
									<option value="新竹市" data-index="5">新竹市</option>
									<option value="新竹縣" data-index="6">新竹縣</option>
									<option value="苗栗縣" data-index="7">苗栗縣</option>
									<option value="臺中市" data-index="8">臺中市</option>
									<option value="彰化縣" data-index="9">彰化縣</option>
									<option value="南投縣" data-index="10">南投縣</option>
									<option value="嘉義市" data-index="11">嘉義市</option>
									<option value="嘉義縣" data-index="12">嘉義縣</option>
									<option value="雲林縣" data-index="13">雲林縣</option>
									<option value="臺南市" data-index="14">臺南市</option>
									<option value="高雄市" data-index="15">高雄市</option>
									<option value="澎湖縣" data-index="16">澎湖縣</option>
									<option value="金門縣" data-index="17">金門縣</option>
									<option value="屏東縣" data-index="18">屏東縣</option>
									<option value="臺東縣" data-index="19">臺東縣</option>
									<option value="花蓮縣" data-index="20">花蓮縣</option>
									<option value="連江縣" data-index="21">連江縣</option>
								</select>
							</div>
				
								<input type="text"
									class="form-control autocomplete" id="autocomplete"
									placeholder="請輸入旅店名稱" Style="margin-left: 10px; width: 320px">
								<button disabled onclick="hotellocator()" id="locatehotel"
									type="button" class="tomap btn btn-secondary"
									Style="width: 130px; margin-left: 10px;" data-toggle="modal"
									data-target=".bd-example-modal-lg">搜尋旅店地址</button>
					
						</div>
					</div>

					<div class="address">
						<input id="deladdress" type="text" class="form-control" name="adr"
							value="<%=(order_detailVO == null) ? "" : order_detailVO.getOrder_cusadr()%>">
					</div>

					<!-- google map -->


					<div class="modal fade bd-example-modal-lg" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content mapadjust">
								<div id="map"></div>
							</div>
						</div>
					</div>
				</div>
				<c:set var="point" value="${pointafter}" />
				<div class="item2">

					<div class="address">
						<div class="col-12 col-sm-7">持有點數:</div>
						<div id="pointbefore" class="col-12 col-sm-3"><%=memberVO.getMem_amo()%></div>
						<div class="col-12 col-sm-2">yo</div>
					</div>
					<div class="address">
						<div class="col-12 col-sm-5">本次消費:</div>
						<div class="col-12 col-sm-2">–</div>
						<div id="finaltotal" class="col-12 col-sm-3"><%=amount%></div>
						<input type="hidden" id="amount" name="order_amosum"
							value="<%=amount%>">
						<div class="col-12 col-sm-2">yo</div>
					</div>
					<hr>
					<div class="address">
						<div class="col-12 col-sm-7">點數餘額:</div>
						<c:if test="${point>=0}">
							<div id="finalpoint" class="col-12 col-sm-3"><%=pointafter%></div>
						</c:if>
						<c:if test="${point<0}">
							<div id="finalpoint" class="col-12 col-sm-3"
								style="color: #ff8f59"><%=pointafter%></div>
						</c:if>
						<input type="hidden" id="pointafter" name="pointafter"
							value="<%=pointafter%>">
						<div class="col-12 col-sm-2">yo</div>
					</div>
					<c:forEach var="productVO" items="${shoppingcart}">
						<input type="hidden" id="proquan${productVO.product_no}"
							name="product_quan" value="${productVO.product_quan}">
					</c:forEach>

					<c:choose>
						<c:when test="${point>=0}">
							<div>
								<button type="button" id="checkout" class="btn btn-outline-info"
									data-toggle="modal" data-target="#exampleModalCenter"
									style="text-align: center; width: 330px;">結帳</button>
							</div>
						</c:when>
						<c:when test="${point<0}">
							<div>
								<button disabled type="button" id="checkout"
									class="btn btn-outline-info" data-toggle="modal"
									data-target="#exampleModalCenter"
									style="text-align: center; width: 330px;">結帳</button>
							</div>
						</c:when>
					</c:choose>
					<div>
						<a href="<%=request.getContextPath()%>/front-end/product/Shop.jsp">
							<button type="button" class="btn btn-outline-success"
								style="text-align: center; width: 330px; margin-top: 10px">繼續購物</button>
						</a>
					</div>

					<div>
						<c:if test="${not empty errorMsgs}">
							<div style="text-align: center; margin: 20px;">
								<span style="color: #ff8f59;">-&nbsp請修正以下錯誤&nbsp-</span>
							</div>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: #ff8f59">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</div>

				<div class="modal fade" id="exampleModalCenter" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalCenterTitle"
					aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" style="text-align: center;">是否同意送出訂單，並扣除點數?</div>
							<div class="modal-footer" style="text-align: center;">
								<button type="button" class="btn btn-outline-secondary"
									data-dismiss="modal">不同意</button>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/ShoppingServlet">
									<button type="submit" class="btn btn-outline-info">同意</button>
									<input type="hidden" name="action" value="createOrderDetail">
								</FORM>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.0/dist/tw-city-selector.min.js">	
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script>

		$('#cityselected').change(function() {
			var city = $('option:selected',this).val();
			
     		$.ajax({
					type : "GET",
					url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					data : creatQueryStringCity(city),
					dataType : "json",
					success : function (data) {
						var array = [];    
						
						$(data).each(function(i, item){
							array[i]=item.aa;							
						  });
								    
						  search(array);
			
						},
					error : function() {
						alert("AJAX-grade發生錯誤囉!")
					}
				});
			});
				
				function creatQueryStringCity(city) {
					var queryString = {
						"action" : "findcityhotel",
						"city" : city
					};
					return queryString;
				}

        function search (array) {
            var hotelname  =  array;

            $( "#autocomplete" ).autocomplete({
               source: hotelname,
               select:function(){
            	   $('#locatehotel').prop("disabled", false);  
               }
            });
         };
         
//          function hotelselected(hotelname){
//      	    var iscityselected = $( "select#cityselected" ).val();
//      	    console.log(iscityselected);
//      	   if(iscityselected!=""){
//      		document.getElementById("locatehotel").disabled = false;
//      	   }
//         	 var inputsection = document.getElementById('autocomplete');
//         	 var input = inputsection.innerText;
//         	 console.log(input);
        	 
//         	 for(var i=0; i<hotelname.length; i++){
//         	      if(hotelname[i]==input){
//         	    	 document.getElementById("locatehotel").disabled = false;
//         	      }
//         	 }
//         }
		$(document).ready(function(){
			$('body').on('input','#autocomplete',function(){
				$('#locatehotel').prop("disabled", true);
			})
		})
         </script>
	<script>
    function hotellocator(){  	
    var hotelname = document.getElementById('autocomplete').value;
    console.log(hotelname)
    if(hotelname==""){
    	alert("請輸入飯店名稱");
    	event.stopPropagation()
    }else{
           
    $.ajax({
		type : "GET",
		url : "/TeamAceMVC/front-end/ShoppingServlet.do",
		data : creatQueryStringlocator(hotelname),
		dataType : "json",
		success : function (data) {
     		
			lat = data.lat;
			lng = data.lng;
			add = data.add;
			
// 			$(data).each(function(i, item){
// 				array[i]=item.aa;							
// 			  });
					    
			 initMap(lat,lng,add,hotelname);
			},
		error : function() {
			alert("AJAX-grade發生錯誤囉!")
		}
	});
  };	
 }
	function creatQueryStringlocator(hotelname) {
		var queryString = {
			"action" : "findhotellatlng",
			"hotelname" : hotelname
		};
		return queryString;
	}
	   
    
    </script>
	<script>
	function initMap(lat,lng,add,hotelname) {
				
			var hotel = {lat,lng};

			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 12,
				center : hotel
			});

			var marker = new google.maps.Marker({
				position : hotel,
				map : map
			});
			
			 var contentString =
				'<div class="map">'+hotelname+'</div>'+'<div class="map" id="hoteladd">'+add+'</div>'+
				'<div id="mapbtn">'+'<button class="btn btn-secondary" data-dismiss="modal" type="button" onclick="setaddress()">'+'確認'+'</button></div>'
				
		 	 var infoWindow = new google.maps.InfoWindow({
		   	 content: contentString
			  });
            
			markerEvent();
			
			map.addListener('center_changed', function() {

				window.setTimeout(function() {
					map.panTo(marker.getPosition());
				}, 3000);
			});
			
			function markerEvent() {
			marker.addListener('click', function() {
						
			map.setZoom(20);
			map.setCenter(marker.getPosition());
			           
			infoWindow.open(map, marker);

			});
		}
	}

		function setaddress() {
			var hoteladd = document.getElementById('hoteladd');
			var add = hoteladd.innerText;
			deladdress.value = add;

		}
		
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDokySNG58PtQXzxx8EP-G18OtbpqZAxDI&callback=initMap"
		type="text/javascript"></script>
	<script>
	  new TwCitySelector({
	    el: ".my-selector-c",
	    elCounty: ".county", // 在 el 裡查找 dom
	    elDistrict: ".district", // 在 el 裡查找 dom
	  });
	</script>

	<script>
		function runPlus(id) {
			var qty = document.getElementById('qty' + id);
			var proquan = document.getElementById('proquan' + id);
			var qtyNum = parseInt(qty.value);
			
			qty.value = qtyNum + 1;
			proquan.value = qtyNum + 1;
			qtyNum = parseInt(qty.value);
			computeplus(id, qtyNum);

			if (qtyNum > 1) {
				document.getElementById('minus' + id).disabled = false;
			}

		}
		function runMinus(id) {
			var qty = document.getElementById('qty' + id);
			var proquan = document.getElementById('proquan' + id);
			var qtyNum = parseInt(qty.value);
			
			qty.value = qtyNum - 1;
			proquan.value = qtyNum - 1;
			qtyNum = parseInt(qty.value);
			computeminus(id, qtyNum);

			if (qtyNum <= 1) {
				document.getElementById('minus' + id).disabled = true;
			}

		}
		var amount = document.getElementById('amount');
		var pointafter = document.getElementById('pointafter');

		function computeplus(id, qtyNum) {
			var price = document.getElementById('price' + id);
			var total = document.getElementById('total' + id);
			var finaltotal = document.getElementById('finaltotal');
			var finalpoint = document.getElementById('finalpoint');
			
			var priceNum = parseInt(price.innerText);
			var totalNum = parseInt(total.innerText);
			var finaltotalNum = parseInt(finaltotal.innerText);
			var finalpointNum = parseInt(finalpoint.innerText);

			total.innerText = priceNum * qtyNum;

			finaltotal.innerText = priceNum + finaltotalNum;
			finalpoint.innerText = finalpointNum - priceNum;

			amount.value = finaltotal.innerText;
			pointafter.value = finalpoint.innerText;
					
			if(finalpoint.innerText < 0){	
				var alert = document.getElementById('alert');
				document.getElementById("checkout").disabled = true;
			    alert.style.visibility="visible";
				
			}		
			
		}
		function computeminus(id, qtyNum) {
			var price = document.getElementById('price' + id);
			var total = document.getElementById('total' + id);
			var finaltotal = document.getElementById('finaltotal');
			var finalpoint = document.getElementById('finalpoint');

			var priceNum = parseInt(price.innerText);
			var totalNum = parseInt(total.innerText);
			var finaltotalNum = parseInt(finaltotal.innerText);
			var finalpointNum = parseInt(finalpoint.innerText);

			total.innerText = priceNum * qtyNum;

			finaltotal.innerText = finaltotalNum - priceNum;
			finalpoint.innerText = finalpointNum + priceNum;

			amount.value = finaltotal.innerText;
			pointafter.value = finalpoint.innerText;
					
			if(finalpoint.innerText >= 0){
			document.getElementById("checkout").disabled = false;
			finalpoint.style.color="black";	
			}
		}
		
		//餘額不足
		function todeposit(){		
			if(finalpoint.innerText < 0){
				var alert = document.getElementById('alert');
				alert.style.visibility="visible";	
			}
		}
		
		function deleteproduct(id) {
			var finaltotal = document.getElementById('finaltotal');
			var total = document.getElementById('total' + id);
			var finalpoint = document.getElementById('finalpoint');

			var finaltotalNum = parseInt(finaltotal.innerText);
			var totalNum = parseInt(total.innerText);
			var finalpointNum = parseInt(finalpoint.innerText);

			finaltotal.innerText = finaltotalNum - totalNum;
			finalpoint.innerText = finalpointNum + totalNum;
		}

		$(document).ready(function() {
			
			
			$('.plus').click(function() {
				var plus = this;
							
				$.ajax({
					type : "GET",
					url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					data : creatQueryStringPlus($(this).prev().prev().val()),
					dataType : "text",
					success : function() {
						
					},
					error : function() {
						alert("AJAX-grade發生錯誤囉!")
					}
				});
			});

		});
		function creatQueryStringPlus(product_no) {
			var queryString = {
				"action" : "plusOne",
				"product_no" : product_no
			};
			return queryString;
		}
		
		$(document).ready(function() {
			$('.minus').click(function() {
				var minus = this;
				$.ajax({
					type : "GET",
					url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					data : creatQueryStringMinus($(this).next().val()),
					dataType : "text",
					success : function() {

					},
					error : function() {
						alert("AJAX-grade發生錯誤囉!")
					}
				});
			});
			
		});
		function creatQueryStringMinus(product_no) {
			var queryString = {
				"action" : "minusOne",
				"product_no" : product_no
			};
			return queryString;
		}
		
		
		$(document).ready(function() {
			$('.close').click(function() {
				var close = this;

				$.ajax({
					type : "GET",
					url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					data : creatQueryString($(this).next().val()),
					dataType : "text",
					success : function() {

						$(close).parents("tr").remove();
					},
					error : function() {
						alert("AJAX-grade發生錯誤囉!")
					}
				});
			});

		});
		function creatQueryString(product_no) {
			var queryString = {
				"action" : "deleteFromCheckOut",
				"product_no" : product_no
			};
			return queryString;
		}
		
		
		
// 	    selector處理
	function showbtn(obj){
		var findhoteladr = document.getElementById('findhoteladr');
		var selector = document.getElementById('selector');
		
		if(obj.options[obj.selectedIndex].value == 'tohotel'){	
			findhoteladr.style.display = 'flex';
			selector.style.display = 'none';
		
		}
		
		if(obj.options[obj.selectedIndex].value == 'bypost'){
			findhoteladr.style.display = 'none';
			selector.style.display = 'flex';
		}
	
}

</script>


</body>
</html>

