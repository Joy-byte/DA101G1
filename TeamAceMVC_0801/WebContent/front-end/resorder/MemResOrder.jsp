<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="com.mem.model.*"%>
<%@ page import="com.resorder.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.oneononechat.controller.JedisMessageAction"%>

<%
MemberVO memVO = (MemberVO)session.getAttribute("memberVO");

long now = System.currentTimeMillis();
pageContext.setAttribute("now",now);

// ChatRoom
String memNo = memVO.getMem_no();
Set<String> chatMemberSet = JedisMessageAction.getChatMember(memNo);
pageContext.setAttribute("chatMemberSet",chatMemberSet);
%>

<jsp:useBean id="resOrderSvc" scope="page" class="com.resorder.model.ResOrderService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />    
<jsp:useBean id="guideSvc" scope="page" class="com.guide.model.GuideService" />
<jsp:useBean id="guideTourSvc" scope="page" class="com.guidetour.model.GuideTourService" />


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

    <title>Myorder</title>

    <style type="text/css">
    	body{
    		font-family: Microsoft JhengHei;
    		letter-spacing: 2px;
    	}
		.first{
			height:370px;
			
		}

		.top{
			padding-top: 5%;
			color:#fff;
		}
    	.content{
    		padding:80px;
			background-color: #fff;
			min-height:900px;

    	}
		.right{
			margin-left:150px;
			min-width: 800px;
		}

		.outer{
			position: relative;
			z-index: 0;
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
		.search-title{
			margin:10px 0 10px 0;
			font-size: 14px;
			letter-spacing: 4px;
		}
		.order{
			position: relative;
			height:270px;
			width:770px;
			background-color: #fff;
			border-radius: 0.5em;
			overflow:hidden;
			display: flex;
			font-size: 15px;
			border:1px solid #ccc;
			margin-top:20px;
		}
		.guidepic-box{
			position: relative;
			height:50px;
			width: 50px;
			border-radius:50%;
			overflow: hidden;
		}
		.guidepic{
			width: 50px;
			height: 50px;
		}
		.guide-box{
			display: flex;
			align-items: center;
			margin:15px 20px 15px 40px;

		}
		.info-bottom{
			margin: 10px 20px 0px 20px;
			padding-top: 10px;
			display: flex;
			border-top:1px solid #ddd;
		}
		.info-right{
			width: 320px;
			margin-left: 40px;
			line-height: 24px;
			display: flex;
		}
		.info-left{
			padding-left:20px;
			line-height: 24px;
			width: 370px;
			display: flex;
			/*margin-left:20px;*/
		}
		.info-up{
			line-height: 25px;
		}
		.up-right{
			position:absolute;
			margin-left:51%;
		}
		.button{
			position:absolute;
			right:20px;
			bottom:15px;
			display: flex;
		}
		.msg{
			margin:10px 0px 15px 35px;
			width: 350px;
			height:80px;
			border-radius:0.5em;
			padding:6px 10px 6px 10px;
			/*border:1px solid #ccc;*/
			background-color: #F5F5F5;
		}
		.reason{
			margin:10px 0px 15px 35px;
			width: 200px;
			height:80px;
			border-radius:0.5em;
			padding:6px 10px 6px 10px;
			color:crimson;
		}
		.left-nav{
			border-radius:0 !important;
			width:180px;
			color:#333 !important;
			padding:12px;
			text-align: center;
			border-bottom:1px solid #888;
			background-color: #fff !important;
		}
		.left-nav:hover{
			border-color: #888 !important;
		}
		/* not active */
		.nav-pills .nav-link:not(.active) {
    		border-color:#ccc;
    		color:#888 !important;
		}
		.nav-pills{
			margin-top: 50px;
		}
		.nav span{
			font-family:monospace;
			font-size: 18px;
			color:#666;
			border-bottom:2px solid #999;
			width:180px;
			padding-bottom: 10px;
			text-align: center;
		}
		.fa-star{
    		color:#aaa;
    		font-size: 24px;
    		cursor: pointer;
    	}
    	.chatBefore:hover{
    		cursor:pointer;
    	}
    	.closeRoom:hover{
    		cursor:pointer;
    	}
    </style>
  </head>
  <body onload="onload()" onunload="disconnect()">
  <%@ include file="/front-end/header.file" %>
  
  	<!-- Chat room -->
  	<div style="display:none;width:730;position:fixed;right:0;bottom:0;z-index:9999" id="chatRoom">
		<div style="width:230px;height:400px;border:1px solid #ccc;background:#fff;text-align:center" id="chatMemBefore">
				<div style="width:100%;text-align:center;border-bottom:1px solid #ccc;padding:10px 0 10px 0 ">最 近 密 聊</div>
				<c:forEach var="chatMember" items="${chatMemberSet}">
					<input type="hidden" value="${chatMember}" class="chatMemNo">
					<input type="hidden" value="${memSvc.getOneMember(chatMember).mem_name}">
					<div class="chatBefore" style="display:flex;align-items:center;margin:0 10px 0 10px;padding:10px;" onclick="changeRoom('${chatMember}',$(this).prev().val())">
						<img width="50px" height="50px" src="../guide/ReadPicture2?mem_no=${chatMember}&type=guide_pic" id="${chatMember}" style="border-radius: 999em;"> 
						<span style="margin-left:20px;">${memSvc.getOneMember(chatMember).mem_name}</span> &nbsp<span id="online${chatMember}" style="color:lightgreen;font-size:12px"></span>
					</div>	
				</c:forEach>
		</div>
		<div style="width:500px;height:400px;position:relative;background:#fff">
			<span class="closeRoom" onclick="$('#chatRoom').hide()" style="position:absolute;top:5px;right:10px;font-size:22px;color:gray">×</span>
			<div style="padding:10px 0 10px 20px;background:#cedee8;height:70px;display:flex" id="chatRoomHead">
	<!-- 			<img width="50px" height="50px" src="ReadMemPic?mem_no=MB00003" style="border-radius: 999em;"> -->
	<!-- 			<div style="display:flex;align-items:center;justify-content:center;padding:1rem;font-size:16px">Mem3</div> -->
			</div>
			<div id="messagesArea" class="panel message-area" style="
				margin: 0px; 
		 		height: 260px;
				border:0;
				display: flex;
				padding: 1rem;
				border-style:solid;
				border-radius: 8px 8px 8px 8px;
				background:#fff;
				flex-direction: column;
				overflow-y: auto;">
			</div>
			<div class="panel input-area" style="width:100%;margin-top:10px">	
				<input type="hidden" id="userName" class="text-field" type="text" placeholder="User name" value="${memberVO.mem_name}"/> 
				<div class="row" style="margin-left:10px">
					<input id="message" class="text-field" type="text" placeholder="請輸入訊息" onkeydown="if (event.keyCode == 13) sendMessage();" style="
					float: left;
				    height: 40px;
				    border: none;
				    padding: 0.5rem 1rem;
				    border-radius: 40px;
				    margin: 0 0.5rem 0 0;
				    width: 420px;
				    background-color: #dedfed;
				    box-shadow: inset 0 0 0 2px #dedfed;
				    font-size: 14px;
				    font-weight: bold;
				    transition: 0.15s all ease-in-out;
				    box-sizing: border-box;"/>
					<div type="submit" id="sendMessage" onclick="sendMessage();" style="
						margin-right:15px;
						float: right;
					    width: 40px;
					    border-radius: 30px;
					    user-select: none;">
				    	<img width="40px"height="40px" src="image/icon.jpeg">
					</div>
				</div>
		
			</div>
		</div>
	</div>
  	
  	<!-- Modal rate-->
	<div class="modal fade" id="exampleModalRate" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalCenterTitle">評論</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form action="ResOrderServlet" method="post">
		      <div class="modal-body">
		      		<span class="fa fa-star" onmouseover="starmark(this)" onclick="starmark(this)" id="1" style="margin-left:20px;color:orange"></span>
					<span class="fa fa-star" onmouseover="starmark(this)" onclick="starmark(this)" id="2"></span>
					<span class="fa fa-star" onmouseover="starmark(this)" onclick="starmark(this)" id="3"></span>
					<span class="fa fa-star" onmouseover="starmark(this)" onclick="starmark(this)" id="4"></span>
					<span class="fa fa-star" onmouseover="starmark(this)" onclick="starmark(this)" id="5"></span>
					<br>
		        <div class="input-group">
				  <textarea class="form-control" name="resOrder_review" aria-label="With textarea" rows="8" placeholder="上限500字" style="margin:15px;"></textarea>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" style="letter-spacing:2px">關閉</button>
		        	<input type="hidden" name="rate" id="rate" value="1">
		        	<input type="hidden" name="type" value="rate">
		        	<input type="hidden" name="action" value="update">
		        	<input type="hidden" name="from" value="rate">
		        	<input type="hidden" name="resOrder_no" id="rateRateOrderNo">
		        	<button type="submit" class="btn btn-primary btn-sm" style="letter-spacing:2px">送出</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>
	<!-- Modal cancel -->
	<div class="modal fade" id="exampleModalCancel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h6 class="modal-title" id="exampleModalCenterTitle">取消原因</h6>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form action="ResOrderServlet" method="post">
	      <div class="modal-body">
	        <div class="input-group">
	          <span id="error_cancel" style="font-size:10px;color:red;"></span><br>
			  <textarea class="form-control" name="resOrder_cancel" aria-label="With textarea" rows="8" placeholder="上限500字" style="margin:15px;"></textarea>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" style="letter-spacing:2px">關閉</button>
	        <input type="hidden" name="cancel" id="cancel">
	        <button type="submit" class="btn btn-primary btn-sm" style="letter-spacing:2px">送出</button>
	      </div>
	      <input type="hidden" name="action" value="update">
	      <input type="hidden" name="type" value="cancel">
	      <input type="hidden" name="from" value="cancel">
	      <input type="hidden" name="resOrder_no" id="cancelResOrderNo">
	      </form>
	    </div>
	  </div>
	</div>
	<div class="outer">
		<img src="image/banner.jpg" id="cover" style="filter: brightness(0.6);">
		<div class="container first">
			<div class="row justify-content-center top">

			</div>
		</div>
		<div class="container-fluid second">
			<div class="row justify-content-center content">
				<div class="left" >
					<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
					  <span><b>MY RESERVATION</b></span>
				      <a class="nav-link left-nav" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">預約中</a>
				      <a class="nav-link left-nav" id="v-pills-success-tab" data-toggle="pill" href="#v-pills-success" role="tab" aria-controls="v-pills-success" aria-selected="false">已完成</a>
				      <a class="nav-link left-nav" id="v-pills-cancel-tab" data-toggle="pill" href="#v-pills-cancel" role="tab" aria-controls="v-pills-cancel" aria-selected="false">取消</a>
				    </div>
				</div>
				<!-- 預約中 -->
				<div class="right tab-content" id="v-pills-tabContent" style="margin-top:30px;">
					<div class="tab-pane fade" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
						
						<c:forEach var="resOrderVO" items="${resOrderSvc.getIng(memberVO.mem_no)}" varStatus="status">
						<div class="order">
							<div class="info-box">				
								<div class="info-up">
									<div class="guide-box">
										<div class="guidepic-box">
											<!-- 旅伴頭貼 -->
											<img src="../guide/ReadPicture2?mem_no=${guideSvc.getOneGuide(resOrderVO.guide_no).mem_no}&type=guide_pic" class="guidepic">
										</div>
										<!-- 旅伴名 -->
										<span style="margin-left:20px;margin-right:35px;font-size: 16px" id="guideName"><b>${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_name}</b></span>
										<button type="button" id="openChat" onclick="openChat('${guideSvc.getOneGuide(resOrderVO.guide_no).mem_no}',$(this).parent().find('#guideName b').text())" class="btn btn-info btn-sm" style="letter-spacing: 2px;position:absolute;margin-left:35%;">聯絡他</button>
										<div class="up-right">
											<b>電話：</b> ${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_tel}<br>
											<b>E-mail:</b> ${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_email}<br>
										</div>	
									</div>	
								</div>
								
								<div class="info-bottom">
									<div class="info-left">
										<div class="left-left" style="width:90px">
											<b>下訂時間：</b><br>
											<b>伴遊行程：</b><br>
										</div>
										<div class="left-right">
											${resOrderVO.resOrder_time}<br>
											<form id="form-tour${status.index}" action="../guidetour/GuideTourDetail.jsp">
												<!-- 行程名 -->
												<input type="hidden" name="guideTour_no" value="${resOrderVO.guideTour_no}">
												<a href="#" onclick="readMoreTour('${status.index}','${guideSvc.getOneGuide(resOrderVO.guide_no).guide_status}')">【${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}】</a>
											</form>
										</div>
										 
									</div>
									<div class="info-right">
										<div class="right-left" style="width: 90px">
											<b>預約日期：</b><br>
											<b>預約人數：</b><br>
											<b>是否共遊：</b><br>
										</div>
										<div class="right-right">
											${resOrderVO.resOrder_date}<br>
											${resOrderVO.resOrder_peo}<br>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ1'}">否</c:if>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ2'}">是</c:if>
											<br>
										</div>
									</div>
								</div>
								<div class="msg" id="test123">
									留言：${resOrderVO.resOrder_msg}	
								</div>
							</div>
								<div class="button">
								
								<c:if test="${resOrderVO.resOrder_date.getTime()-now > 0}">
									<button class="btn btn-secondary btn-sm" style="letter-spacing: 2px;margin-right:15px;" disabled>預約中</button>
								</c:if>
								<c:if test="${resOrderVO.resOrder_date.getTime()-now <= 0}">
									<form action="ResOrderServlet" method="post">
										<input type="hidden" name="action" value="update">
									    <input type="hidden" name="type" value="complete">
									    <input type="hidden" name="from" value="mem">
									    <input type="hidden" name="resOrder_no" value="${resOrderVO.resOrder_no}">
										<button type="submit" class="btn btn-secondary btn-sm" style="letter-spacing: 2px;margin-right:15px;">完成預約</button>
									</form>
								</c:if>
								
									<button type="button" class="btn btn-secondary btn-sm" style="letter-spacing: 2px;" data-toggle="modal" data-target="#exampleModalCancel" style="letter-spacing: 2px;" onclick="sendCancelValue('${resOrderVO.resOrder_no}')">取消</button>
								</div>
						</div>
						</c:forEach>
					</div>
					
					<!-- 完成 -->
					<div class="tab-pane fade" id="v-pills-success" role="tabpanel" aria-labelledby="v-pills-success-tab">
						<c:forEach var="resOrderVO" items="${resOrderSvc.getFinish(memberVO.mem_no)}" varStatus="status">
						<div class="order">
							<div class="info-box">				
								<div class="info-up">
									<div class="guide-box">
										<div class="guidepic-box">
											<!-- 旅伴頭貼 -->
											<img src="../guide/ReadPicture2?mem_no=${guideSvc.getOneGuide(resOrderVO.guide_no).mem_no}&type=guide_pic" class="guidepic">
										</div>
										<!-- 旅伴名 -->
										<span style="margin-left:20px;margin-right:35px;font-size: 16px" id="guideName"><b>${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_name}</b></span>
										<button type="button" id="openChat" onclick="openChat('${guideSvc.getOneGuide(resOrderVO.guide_no).mem_no}',$(this).parent().find('#guideName b').text())" class="btn btn-info btn-sm" style="letter-spacing: 2px;position:absolute;margin-left:35%;">聯絡他</button>
										<div class="up-right">
											<b>電話：</b> ${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_tel}<br>
											<b>E-mail:</b> ${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_email}<br>
										</div>	
									</div>	
								</div>
	
								<div class="info-bottom">
									<div class="info-left">
										<div class="left-left" style="width:90px">
											<b>下訂時間：</b><br>
											<b>伴遊行程：</b><br>
										</div>
										<div class="left-right">
											${resOrderVO.resOrder_time}<br>
											<form id="form-complete${status.index}" action="../guidetour/GuideTourDetail.jsp">
												<!-- 行程名 -->
												<input type="hidden" name="action" value="readMore">
												<input type="hidden" name="guideTour_no" value="${resOrderVO.guideTour_no}">
												<a href="#" onclick="readMoreComplete('${status.index}','${guideSvc.getOneGuide(resOrderVO.guide_no).guide_status}')">【${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}】</a>
											</form>
										</div>
										 
									</div>
									<div class="info-right">
										<div class="right-left" style="width: 90px">
											<b>預約日期：</b><br>
											<b>預約人數：</b><br>
											<b>是否共遊：</b><br>
										</div>
										<div class="right-right">
											${resOrderVO.resOrder_date}<br>
											${resOrderVO.resOrder_peo}<br>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ1'}">否</c:if>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ2'}">是</c:if>
											<br>
										</div>
									</div>
								</div>
								<div class="msg">
									留言：${resOrderVO.resOrder_msg}	
								</div>
							</div>
							
							<div class="button">
						
								<button class="btn btn-secondary btn-sm" style="letter-spacing: 2px;margin-right:15px;" disabled>已完成</button>
								
								<!-- Button trigger modal -->
								<c:if test="${(not empty resOrderVO.resOrder_review) or resOrderVO.resOrder_rate!=0}">
									<button onclick="sendRateValue('${resOrderVO.resOrder_no}')" type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#exampleModalRate" style="letter-spacing: 2px;">已評價</button>
								</c:if>
								<c:if test="${(empty resOrderVO.resOrder_rate) or resOrderVO.resOrder_rate==0 }">
									<button onclick="sendRateValue('${resOrderVO.resOrder_no}')" type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#exampleModalRate" style="letter-spacing: 2px;">評價</button>
								</c:if>
								
							</div>
						</div>
						</c:forEach>
					</div>
					<!-- 取消 -->
					<div class="tab-pane fade" id="v-pills-cancel" role="tabpanel" aria-labelledby="v-pills-cancel-tab">
						<c:forEach var="resOrderVO" items="${resOrderSvc.getCancel(memberVO.mem_no)}" varStatus="status">
						<div class="order">
							<div class="info-box">				
								<div class="info-up">
									<div class="guide-box">
										<div class="guidepic-box">
											<!-- 旅伴頭貼 -->
											<img src="../guide/ReadPicture2?mem_no=${guideSvc.getOneGuide(resOrderVO.guide_no).mem_no}&type=guide_pic" class="guidepic">
										</div>
										<!-- 旅伴名 -->
										<span style="margin-left:20px;margin-right:35px;font-size: 16px" id="guideName"><b>${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_name}</b></span>
										<button type="button" id="openChat" onclick="openChat('${guideSvc.getOneGuide(resOrderVO.guide_no).mem_no}',$(this).parent().find('#guideName b').text())" class="btn btn-info btn-sm" style="letter-spacing: 2px;position:absolute;margin-left:35%;">聯絡他</button>
										<div class="up-right">
											<b>電話：</b> ${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_tel}<br>
											<b>E-mail:</b> ${memSvc.getOneMember(guideSvc.getOneGuide(resOrderVO.guide_no).mem_no).mem_email}<br>
										</div>	
									</div>	
								</div>
	
								<div class="info-bottom">
									<div class="info-left">
										<div class="left-left" style="width: 90px">
											<b>下訂時間：</b><br>
											<b>伴遊行程：</b><br>
										</div>
										<div class="left-right">
											${resOrderVO.resOrder_time}<br>
											<form id="form-cancel${status.index}" action="../guidetour/GuideTourDetail.jsp">
												<!-- 行程名 -->
												<input type="hidden" name="action" value="readMore">
												<input type="hidden" name="guideTour_no" value="${resOrderVO.guideTour_no}">
												<a href="#" onclick="readMoreCancel('${status.index}','${guideSvc.getOneGuide(resOrderVO.guide_no).guide_status}')">【${guideTourSvc.getOneGuideTour(resOrderVO.guideTour_no).guideTour_name}】</a>
											</form>
										</div>
										 
									</div>
									<div class="info-right">
										<div class="right-left" style="width: 90px">
											<b>預約日期：</b><br>
											<b>預約人數：</b><br>
											<b>是否共遊：</b><br>
										</div>
										<div class="right-right">
											${resOrderVO.resOrder_date}<br>
											${resOrderVO.resOrder_peo}<br>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ1'}">否</c:if>
											<c:if test="${resOrderVO.resOrder_joinpeo=='RJ2'}">是</c:if>
											<br>
										</div>
									</div>
								</div>
								<div class="msg-box" style="display: flex">
									<div class="msg">
										留言：${resOrderVO.resOrder_msg}			
									</div>
									<div class="reason">
										取消原因：${resOrderVO.resOrder_cancel}			
									</div>
								</div>
							</div>
							
							<div class="button">
									<button class="btn btn-secondary btn-sm" style="letter-spacing: 2px;" disabled>已取消</button>
							</div>
						</div>
						</c:forEach>
					</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
	var webSocketChat;
	var receiver;
	function onload(){
		if('${param.rate}'=='success'){
			document.getElementById("v-pills-home-tab").setAttribute("class","nav-link left-nav");
			document.getElementById("v-pills-success-tab").setAttribute("class","nav-link active left-nav");
			document.getElementById("v-pills-home").setAttribute("class","tab-pane fade");
			document.getElementById("v-pills-success").setAttribute("class","tab-pane fade show active");
		}
		if('${param.rate}'==''){
			document.getElementById("v-pills-home-tab").setAttribute("class","nav-link active left-nav");
			document.getElementById("v-pills-success-tab").setAttribute("class","nav-link left-nav");
			document.getElementById("v-pills-home").setAttribute("class","tab-pane fade show active");
			document.getElementById("v-pills-success").setAttribute("class","tab-pane fade");
		}		
		
		//WebSocket
		var MyPoint = "/ChatRoomWS/${memberVO.mem_no}";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		// 建立 websocket 物件
		webSocketChat = new WebSocket(endPointURL);
		
		webSocketChat.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;
			//抓已上線的會員
			var onlineStr = {"action":"getOnline"};
			webSocketChat.send(JSON.stringify(onlineStr));
		};
		
		webSocketChat.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message;
	        //一開始會員上線
	        if(jsonObj.action == 'getOnline'){
	        	var onlineArray = JSON.parse('['+jsonObj.onlineArray+']');
	        	var chatMemArray = document.getElementsByClassName('chatMemNo');
	        	for(var i=0;i<chatMemArray.length;i++){
	        		for(var j=0;j<onlineArray.length;j++){
	        			var onlineObj = onlineArray[j];
	        			if(onlineObj.memNo == chatMemArray[i].value){
		        			$('#online'+onlineObj.memNo).text("●");
		        		}
	        		}
	        	}
	        }
	        //會員上線
	        if(jsonObj.action == 'online'){
	        	var onMemNo = jsonObj.memNo;
	        	var chatMemArray = document.getElementsByClassName('chatMemNo');
	        	for(var i=0;i<chatMemArray.length;i++){
	        		if(onMemNo == chatMemArray[i].value){
	        			$('#online'+onMemNo).text("●");
	        		}
	        	}
	        }
	        //會員下線
			if(jsonObj.action == 'offline'){
				var offMemNo = jsonObj.memNo;
				var chatMemArray = document.getElementsByClassName('chatMemNo');
				for(var i=0;i<chatMemArray.length;i++){
	        		if(offMemNo == chatMemArray[i].value){
	        			$('#online'+offMemNo).text("");
	        		}
	        	}
	        }
	        //傳單一msg
	        if(jsonObj.action == 'sendMsg'){
	        	if(jsonObj.sender != "${memberVO.mem_no}"){
	        		if((jsonObj.sender == receiver) && (jsonObj.receiver == "${memberVO.mem_no}")){
	        			message = 
		    	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
		    	        	"<div style='float: left;text-align: left;margin: 0 0 0 1rem;background-color: #f8e0a5;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
		    	        	jsonObj.message + 
		    	        	"<br></div></div>";
		    	        messagesArea.innerHTML = messagesArea.innerHTML + message;
		    	        messagesArea.scrollTop = messagesArea.scrollHeight;	
	        		}
	    	    }
	        	if(jsonObj.sender == "${memberVO.mem_no}"){
	    	        message = 
	    	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
	    	        	"<div style='float: right;text-align: right;margin: 0 1rem 0 0;background-color: #f8e0a5;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
	    	        	jsonObj.message + 
	    	        	"<br></div></div>";
	    	        messagesArea.innerHTML = messagesArea.innerHTML + message;
	    	        messagesArea.scrollTop = messagesArea.scrollHeight;
	    	    }
	        } 
	        //切換聊天室窗
	        if(jsonObj.action == 'changeRoom'){
	        	var historyArray = JSON.parse('['+jsonObj.historyMsg+']');
	        	messagesArea.innerHTML = "";
	        	var message;
	        	for(var i=0;i<historyArray.length;i++){
	        		var historyObj = historyArray[i];
	        		if(historyObj.sender != "${memberVO.mem_no}"){
		    	        message = 
		    	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
		    	        	"<div style='float: left;text-align: left;margin: 0 0 0 1rem;background-color: #f8e0a5;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
		    	        	historyObj.message + 
		    	        	"<br></div></div>";
		    	    }else{
		    	        message = 
		    	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
		    	        	"<div style='float: right;text-align: right;margin: 0 1rem 0 0;background-color: #f8e0a5;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
		    	        	historyObj.message + 
		    	        	"<br></div></div>";
		    	    }
	        		messagesArea.innerHTML = messagesArea.innerHTML + message;
	    	        messagesArea.scrollTop = messagesArea.scrollHeight;
	        	}
	        }
		}
		webSocketChat.onclose = function(event) {
			var messagesArea = document.getElementById("messagesArea");
			var message = 
	        	"<div style='padding: 1rem 0 0 0;transition: all 0.15s ease-in-out;animation: fadeNewMessage 0.5s;animation-fill-mode: forwards;'>" +
	        	"<div style='float: right;text-align: right;margin: 0 1rem 0 0;background-color: #ccc;padding: 12px;border-radius: 8px 8px 8px 8px;'>" + 
	        	您已離線 + 
	        	"<br></div></div>";
			messagesArea.innerHTML = messagesArea.innerHTML + message;
		    messagesArea.scrollTop = messagesArea.scrollHeight;
		    document.getElementById('sendMessage').disabled = true;
		};
	}
	
	var rating = "";
	function starmark(item){
		var count = item.id;
		rating = count;
		for(i=0;i<5;i++){
			if(i<count){
				document.getElementById((i+1)).style.color="orange";
			}else{
				document.getElementById((i+1)).style.color="#aaa";
			}
		}
// 		document.getElementById("rating").innerHTML=rating; //測試
		document.getElementById("rate").value=rating;
	}
	
	function sendCancelValue(resOrder_no){
		document.getElementById("cancelResOrderNo").value=resOrder_no;
	}
	
	function sendRateValue(resOrder_no){
		document.getElementById("rateRateOrderNo").value=resOrder_no;
	}
	function readMoreCancel(index,guide_status){
		if(guide_status == 'GN1'){
			document.getElementById('form-cancel'+index).submit()
		}else{
			alert('該用戶已被停權！');	
		}
	}
	function readMoreComplete(index,guide_status){
		if(guide_status == 'GN1'){
			document.getElementById('form-complete'+index).submit()
		}else{
			alert('該用戶已被停權！');	
		}
	}
	function readMoreTour(index,guide_status){
		if(guide_status == 'GN1'){
			document.getElementById('form-tour'+index).submit()
		}else{
			alert('該用戶已被停權！');	
		}
	}
	/********************************* 聊天室 *********************************/ 
	
	function openChat(chatMemNo,chatName){
		receiver = chatMemNo;
		$('#chatRoom').css('display','flex');
		$('#chatRoomHead').html('<img width="50px" height="50px" src="../guide/ReadPicture2?mem_no='+chatMemNo+'&type=guide_pic" style="border-radius: 999em;">'
				+'<div style="display:flex;align-items:center;justify-content:center;padding:1rem;font-size:16px">'+chatName+'</div>');
		var chatMemArray = document.getElementsByClassName('chatMemNo');
		if(chatMemArray.length == 0){
			$('#chatMemBefore').append('<input type="hidden" value="'+chatMemNo+'" class="chatMemNo">'
   				+'<input type="hidden" value="'+chatName+'">'
   				+'<div class="chatBefore" style="display:flex;align-items:center;margin:0 10px 0 10px;padding:10px;" onclick="changeRoom(\''+chatMemNo+'\',\''+chatName+'\')">'
   					+'<img width="50px" height="50px" src="../guide/ReadPicture2?mem_no='+chatMemNo+'&type=guide_pic" id="'+chatMemNo+'" style="border-radius: 999em;">' 
   					+'<span style="margin-left:20px;">'+chatName+'</span> &nbsp<span id="online'+chatMemNo+'" style="color:lightgreen;font-size:12px"></span>'
   				+'</div>'			
   			);
		}
		for(var i=0;i<chatMemArray.length;i++){
    		if(chatMemNo == chatMemArray[i].value){
    			break;
    			$('#online'+offMemNo).text("");
    		}
    		if(i==chatMemArray.length-1){
    			$('#chatMemBefore').append('<input type="hidden" value="'+chatMemNo+'" class="chatMemNo">'
   					+'<input type="hidden" value="'+chatName+'">'
   					+'<div class="chatBefore" style="display:flex;align-items:center;margin:0 10px 0 10px;padding:10px;" onclick="changeRoom(\''+chatMemNo+'\',\''+chatName+'\')">'
   						+'<img width="50px" height="50px" src="../guide/ReadPicture2?mem_no='+chatMemNo+'&type=guide_pic" id="'+chatMemNo+'" style="border-radius: 999em;">' 
   						+'<span style="margin-left:20px;">'+chatName+'</span> &nbsp<span id="online'+chatMemNo+'" style="color:lightgreen;font-size:12px"></span>'
   					+'</div>'			
    			);
    		}
    	}
		changeRoom(chatMemNo,chatName);
	}
	
	function sendMessage() {
	    var user = '${memberVO.mem_no}';
	    if (user == ""){
	        alert ("您尚未登入");
			return;
	    }
	    
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message == ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "message" : message,"sender":"${memberVO.mem_no}","receiver":receiver,"action":"sendMsg"};
	        webSocketChat.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}
	
	function changeRoom(chatMemNo,chatName){
    	receiver = chatMemNo;
    	$('#chatRoomHead').html('<img width="50px" height="50px" src="../guide/ReadPicture2?mem_no='+chatMemNo+'&type=guide_pic" style="border-radius: 999em;">'
    							+'<div style="display:flex;align-items:center;justify-content:center;padding:1rem;font-size:16px">'+chatName+'</div>');
    	jsonObj = {"sender":"${memberVO.mem_no}","receiver":chatMemNo,"action":"changeRoom"};
    	webSocketChat.send(JSON.stringify(jsonObj));
    }
	
	function disconnect () {
		webSocketChat.close();
	}
	
	</script>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>