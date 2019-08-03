<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
#headright button{
		margin-left: 35px;
		margin-top: 5px;
	}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light"  >
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		    <a class="navbar-brand" href="#">Logo</a>
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item active">
		        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/home/TeamAce.jsp">首頁 <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/itinerary/itineraryUpload.jsp">發佈</a>
		      </li>
		      <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/game/gameroom.jsp">誰是臥底</a>
		      </li>
		      <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/product/Shop.jsp">商城</a>
		      </li>
		      <div class="btn-group">
				  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background:#fff !important;">
				   在地旅伴 yo
				  </button>
				  <div class="dropdown-menu" id="dropdown-menu1">
				  <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guide/GuideAdd.jsp">成為在地旅伴</a>
				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guidetour/GuideTourList.jsp">伴遊行程</a>
				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/resorder/MemResOrder.jsp">我的預約訂單</a>
<!-- 				    <div class="dropdown-divider"></div> -->
					<c:if test="${guideSvc.getOneGuideByMemNo(memberVO.mem_no)!=null}">
				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guide/GuideMainPage.jsp">在地旅伴管理</a>
				  </c:if>
				  </div>
				</div>

		      <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${memberVO.mem_no}">個人頁面</a>
		      </li>
		    </ul>
		    <div class="form-inline my-2 my-lg-0" id="headright">
		    <c:if test="${memberVO.mem_name !=null}">
            	<a href="<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${memberVO.mem_no}">
            	 ${memberVO.mem_name} </a> ,你好 ! 點數:${memberVO.mem_amo}
        	</c:if>
		      <button class="btn btn-outline-success my-2 my-sm-0" onclick="loginbtns()"><b id="isLogin" name="${ memberVO.mem_name}" >登入</b></button>
		    </div>
		  </div>
	</nav>
</body>
</html>