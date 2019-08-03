<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.deposit.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	DepositVO depositVO = (DepositVO) request.getAttribute("depositVO");
%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
    String memname = memberVO.getMem_name();
%>

<%
	String str = (String) (session.getAttribute("mem_no"));
%>
<jsp:useBean id="depositSvc" scope="page"
	class="com.deposit.model.DepositService" />
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">


<style type="text/css">
.custom-checkbox .custom-control-label::before {
	background-color: #eee;
	border-color: #aaa;
}

.custom-control-input:checked ~.custom-control-label::before {
	background-color: #fa0;
	border-color: #fa0;
}
/*-------------------------------*/
/*-------------------------------*/
.custom-control-label::after {
	background-image:
		url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 8 8'%3e%3cpath fill='%23fff' d='M6.564.75l-3.59 3.612-1.538-1.55L0 4.26 2.974 7.25 8 2.193z'/%3e%3c/svg%3e");
	transform: scale(0);
	transition: .2s cubic-bezier(.87, 1.82, .87, 1.71);
}

.custom-checkbox .custom-control-input:checked ~.custom-control-label::after
	{
	transform: scale(1.65);
}

.adjust {
	margin-left: 10px;
	padding-left: 0px;
	margin-top: 10px;
}

.sizing {
	width: 200px;
}

.navbar {
	z-index: 10;
	position: fixed;
	width: 100%;
	top: 0px;
}

.container {
	margin-top: 100px;
	display: flex;
	justify-content: center;
}

body {
	font-family: Microsoft JhengHei;
	letter-spacing: 2px;
}

.radioadjust {
	margin-top: 10px;
}
</style>

</head>

<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<%@ include file="/front-end/header.file" %>

	<div class="container">
		<div class="card" style="width: 50rem; height: 40rem; padding: 50px;">
			<div class="card-body">
				<div class="row justify-content-center">
					<div>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/DepositServlet1"
							name="form1">
							<table>
								<tr style="line-height: 60px;">
									<td><span>信用卡類型:</span></td>
									<td><select id="cardtype" class="form-control adjust"
										style="width: 180px;">
											<option>Visa/Master/JCB</option>
											<option value="Visa">Visa</option>
											<option value="Master">Master</option>
											<option value="JCB">JCB</option>
									</select></td>
								</tr>
								<tr style="line-height: 60px;">
									<td class="sizing">持卡人:</td>
									<td><div class="adjust" style="width: 180px;">
											<input id="cardname" type="text" class="form-control"
												placeholder="請輸入持卡人姓名">
										</div></td>
								</tr>
								<tr style="line-height: 60px;">
									<td>請輸入信用卡號碼:</td>
									<td>
										<div class="adjust form-row">
											<div style="width: 80px;">
												<input id="cardno1" maxlength="4" type="text" name=""
													class="form-control">
											</div>
											<div style="line-height: 40px;">-</div>
											<div style="width: 80px;">
												<input id="cardno2" maxlength="4" type="text" name=""
													class="form-control">
											</div>
											<div style="line-height: 40px">-</div>
											<div style="width: 80px;">
												<input id="cardno3" maxlength="4" type="text" name=""
													class="form-control">
											</div>
											<div style="line-height: 40px">-</div>
											<div style="width: 80px;">
												<input id="cardno4" maxlength="4" type="text" name=""
													class="form-control">
											</div>
										</div>
									</td>
								<tr style="line-height: 60px;">
									<td>請選擇卡片有效期限:</td>
									<td>
										<div class="form-row adjust">
											<select id="yyyy" class="form-control" style="width: 100px;">
												<option>請選擇</option>
												<option value="19">2019</option>
												<option value="20">2020</option>
												<option value="21">2021</option>
												<option value="22">2022</option>
												<option value="23">2023</option>
												<option value="24">2024</option>
												<option value="25">2025</option>
												<option value="26">2026</option>
												<option value="27">2027</option>
												<option value="28">2028</option>
												<option value="29">2029</option>
											</select> <span
												style="line-height: 40px; margin-left: 10px; margin-right: 10px;">年</span>
											<select id="mm" class="form-control" style="width: 100px;">
												<option>請選擇</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
											</select> <span style="line-height: 40px; margin-left: 10px">月</span>
										</div>
									</td>
								<tr style="line-height: 60px;">
									<td>請輸入卡片背面末三碼:</td>
									<td>
										<div class="adjust">
											<input id="pin" maxlength="3" style="width: 60px;" type="text" name=""
												class="form-control">
										</div>
									</td>
								</tr>
								<tr>
									<td>儲值金額:</td>
									<td>
										<div id="topup" class="adjust">
											<input class="radioadjust" type="radio" name="deposit_amo" value="two"> 200yo<br> 
											<input class="radioadjust" type="radio" name="deposit_amo" value="500"> 500yo<br>
                                            <input class="radioadjust" type="radio" name="deposit_amo" value="1000"> 1000yo<br>
                                            <input  class="radioadjust" type="radio" name="deposit_amo" value="5000"> 5000yo
										</div>
									</td>
								</tr>
							</table>
							<div style="text-align: center">
								<input type="hidden" name="action" value="insert">
								<%-- 				<input type="hidden" name="mem_no" value="<%=session.getAttribute("mem_no") %>"> --%>
								<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
								<input class="btn btn-secondary" type="button" value="儲值"  data-toggle="modal"
									data-target="#exampleModalCenter" style="width: 200px; margin-bottom:50px; margin-top:50px;">
							    <button type="button" onclick="autofill()" style="background-color:#fff; border:none"><img  src="<%=request.getContextPath()%>/back-end/portal/icon.jpeg" style="width:30px;"></button>
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
										<div class="modal-body" style="text-align: center;">是否同意進行扣款?</div>
										<div class="modal-footer" style="text-align: center;">
											<button type="button" class="btn btn-outline-secondary"
												data-dismiss="modal">不同意</button>								
												<button type="submit" class="btn btn-outline-info">同意</button>
	
										</div>
									</div>
								</div>
							</div>						
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script>
$(document).ready(function() {
	 if($('#isLogin').attr('name')!=""){
		 $('#isLogin').text('登出');
		 $('#isLogin').prev().attr('onclick','logoutbtns()');
	 }else{
		 $('#isLogin').text('登入');
		 $('#isLogin').prev().attr('onclick','loginbtns()');
	 }
});

function loginbtns(){
	location.href='<%=request.getContextPath()%>/front-end/member/login.jsp';
		}
		
function autofill(){
	var cardname = document.getElementById('cardname');
	var cardno1 = document.getElementById('cardno1');
	var cardno2 = document.getElementById('cardno2');
	var cardno3 = document.getElementById('cardno3');
	var cardno4 = document.getElementById('cardno4');
	var pin = document.getElementById('pin');
	
	cardname.value ='<%=memname%>';
	cardno1.value = '4266';
	cardno2.value = '5112';
	cardno3.value = '2365';
	cardno4.value = '7782';
	pin.value = '543';
	$("#cardtype option[value=Visa]").attr("selected",true);
	$("#yyyy option[value=25]").attr("selected",true);
	$("#mm option[value=8]").attr("selected",true);
	$("#topup input[value=5000]").attr("checked",true);
}              
	</script>
</body>
</html>