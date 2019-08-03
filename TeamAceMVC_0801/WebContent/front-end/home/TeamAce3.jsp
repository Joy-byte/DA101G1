<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mem2.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="com.itinerary_detail.model.*"%>
<%@ page import="java.util.*"%>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>

<html>
	<head>
		<title>TeamAce</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/common.css" media="all">
        <link rel="stylesheet" href="assets/css/style.css" media="all">
		<style type="text/css">
			.logo img{
				width: 20px;
				height: 30px;
			}
			.inner img{
				width: 250px;
				height: 230px;
			}
			.member img{
				width: 150px;
				height: 230px;
			}
			.member{
				text-align: left;
			}
			img:hover{
				cursor: pointer;
			}
			#tstory{
			width: 100%;
			height: 100%;
			background-color: rgba(50,50,50,0.7);
			position: absolute;
			top:0px;
			left: 0px;
			color: #fff;
			text-align: center;
			font-size: 80px;
			padding-top: 25%;
			box-sizing: border-box;
			z-index: 999;
			}
			
		</style>
								
	</head>
	<body>

		<!-- Header -->
			<header id="header">
				<div class="logo"><a href="TeamAce.jsp">Yo<img src="images/logo.jpg" ></a></div>
				<a href="#menu"><span>Menu</span></a><br>
				<div class="member">
					<img src="images/1.png"   onclick="show1()">
					<img src="images/2.png"   onclick="show2()">
					<img src="images/3.png"   onclick="show3()">
					<img src="images/4.png"   onclick="show4()">
					<img src="images/5.png"   onclick="show5()">
					<img src="images/6.png"   onclick="show6()">
					<img src="images/7.png"   onclick="show7()">										
				</div>				
			</header>
			<br><br>
			

		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li>${Name}</li>
					<li><a href="TeamAce.jsp">Home</a></li>
					<li><a href="../member/login.jsp">登入</a></li>
					<li><a href="../itinerary/Upload.jsp">發佈</a></li>
					<li><a href="../member/MemberAdd.jsp">註冊</a></li>
				</ul>
			</nav>

		<!-- Banner -->
		<!--
			Note: To show a background image, set the "data-bg" attribute below
			to the full filename of your image. This is used in each section to set
			the background image.
		-->
			<section id="banner" class="bg-img" data-bg="trip.jpg">
				<div id="tstory" style="visibility:hidden" >
					<video src="" autoplay="" id="ttstory" width="200px" 
						height="400px"  onclick="nextt()" ></video>
					<button onclick="webCtrl()" id="b1"></button>
				</div>
				<div class="inner">
					<header>
						<h1>Yo Yo Yo</h1>
					</header>
				</div>
				<a href="#one" class="more">Learn More</a>
			</section>

		<!-- One -->
<jsp:useBean id="dao" scope="page" class="com.landscape.model.LandscapeJDBCDAO" />
			<section id="one" class="wrapper post bg-img" data-bg="ocean.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h1><B>文青之旅</B></h1>
<!-- 												<select size="1" name="spot"> -->
<%-- 											         <c:forEach var="landscapeVO" items='${dao.getLs("LS00001")}'>  --%>
<%-- 											          <option value="${landscapeVO.landscape_name}">${landscapeVO.landscape_name} --%>
<%-- 											         </c:forEach>    --%>
<!-- 										       </select> -->
						
			
<!-- 								Itinerary_detailService itinerary_detailService = new Itinerary_detailService();  -->
<!-- 								List<Itinerary_detailVO> list = itinerary_detailService.getLas(); -->
<!-- 								pageContext.setAttribute("list",list); -->
		
						</header>
						<div class="content">
							<ul class="flex">
		                        <li>
		                            <div class="cp-unit cp-unit--l" style="top: 60px; left: 2px;">
		                                <a href="http://kumejima-nuchigusuitrip.com/happy-healthy/11.html">
		                                    <div class="category category--happy">
		                                        <h4>
		                                        <c:forEach var="itinerary_detailVO" items="${list}" begin="3" end="3">	
		                                        	<td>${itinerary_detailVO.landscape_name}</td>
<%-- 		                                        	<% String p = itinerary_detailVO.get%> --%>
		                                        </c:forEach>
		                                        </h4>
		                                    </div>
		                                <figure class="thumb m-circle">		                          
		                                    <span class="photo">
		                                        <span class="arrow">
<!-- 		                                         箭頭 -->
		                                        </span>
		                                     <img src="PictureReader?pic_no=PI00015"  alt="">
		                                    </span>
		                                </figure>
		                                </a>
		                            </div>
		                        </li>
		                        <li>
		                            <div class="cp-unit cp-unit--l" style="top: 60px; left: 2px;">
		                                <a href="http://kumejima-nuchigusuitrip.com/happy-healthy/11.html">
		                                    <div class="category category--happy">
		                                       <h4>
		                                       <c:forEach var="itinerary_detailVO" items="${list}" begin="2" end="2">	
		                                        	<td>${itinerary_detailVO.landscape_name}</td>
		                                        </c:forEach></h4>
		                                    </div>
		                                <figure class="thumb m-circle">		                          
		                                    <span class="photo">
		                                        <span class="arrow">
		                                        </span>
		                                     <img src="PictureReader?pic_no=PI00016" alt="">
		                                    </span>
		                                </figure>
		                                </a>
		                            </div>
		                        </li>
		                        <li>
		                            <div class="cp-unit cp-unit--l" style="top: 60px; left: 2px;">
		                                <a href="http://kumejima-nuchigusuitrip.com/happy-healthy/11.html">
		                                    <div class="category category--happy">
		                                       <h4>
		                                       <c:forEach var="itinerary_detailVO" items="${list}" begin="1" end="1">	
		                                        	<td>${itinerary_detailVO.landscape_name}</td>
		                                        </c:forEach></h4>
		                                    </div>
		                                <figure class="thumb m-circle">		                          
		                                    <span class="photo">
		                                        <span class="arrow">
		                                        </span>
		                                     <img src="PictureReader?pic_no=PI00017" alt="">
		                                    </span>
		                                </figure>
		                                </a>
		                            </div>
		                        </li>
		                        <li>
		                            <div class="cp-unit cp-unit--l" style="top: 60px; left: 2px;">
		                                <a href="http://kumejima-nuchigusuitrip.com/happy-healthy/11.html">
		                                    <div class="category category--happy">
		                                       <h4>
		                                       <c:forEach var="itinerary_detailVO" items="${list}" begin="0" end="0">	
		                                        	<td>${itinerary_detailVO.landscape_name}</td>
		                                        </c:forEach></h4>
		                                    </div>
		                                <figure class="thumb m-circle">		                          
		                                    <span class="photo">
		                                        <span class="arrow">
		                                        </span>
		                                     <img src="PictureReader?pic_no=PI00018" alt="">
		                                    </span>
		                                </figure>
		                                </a>
		                            </div>
		                        </li>
           				 </ul>  
						</div>
						<footer>
							<a href="generic.html" class="button alt">Learn More</a>
						</footer>
					</article>
				</div>
				<a href="#two" class="more">Learn More</a>
			</section>

		<!-- Two -->
			<section id="two" class="wrapper post bg-img" data-bg="eath.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h2>Mus elit a ultricies at</h2>
							<p>12.21.2016</p>
						</header>
						<div class="content">
							<p>Scelerisque enim mi curae erat ultricies lobortis donec velit in per cum consectetur purus a enim platea vestibulum lacinia et elit ante scelerisque vestibulum. At urna condimentum sed vulputate a duis in senectus ullamcorper lacus cubilia consectetur odio proin sociosqu a parturient nam ac blandit praesent aptent. Eros dignissim mus mauris a natoque ad suspendisse nulla a urna in tincidunt tristique enim arcu litora scelerisque eros suspendisse.</p>
						</div>
						<footer>
							<a href="generic.html" class="button alt">Learn More</a>
						</footer>
					</article>
				</div>
				<a href="#three" class="more">Learn More</a>
			</section>

		<!-- Three -->
			<section id="three" class="wrapper post bg-img" data-bg="banner4.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h2>Varius a cursus aliquet</h2>
							<p>11.11.2016</p>
						</header>
						<div class="content">
							<p>Scelerisque enim mi curae erat ultricies lobortis donec velit in per cum consectetur purus a enim platea vestibulum lacinia et elit ante scelerisque vestibulum. At urna condimentum sed vulputate a duis in senectus ullamcorper lacus cubilia consectetur odio proin sociosqu a parturient nam ac blandit praesent aptent. Eros dignissim mus mauris a natoque ad suspendisse nulla a urna in tincidunt tristique enim arcu litora scelerisque eros suspendisse.</p>
						</div>
						<footer>
							<a href="generic.html" class="button alt">Learn More</a>
						</footer>
					</article>
				</div>
				<a href="#four" class="more">Learn More</a>
			</section>

		<!-- Four -->
			<section id="four" class="wrapper post bg-img" data-bg="banner3.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h2>Luctus blandit mi lectus in nascetur</h2>
							<p>10.30.2016</p>
						</header>
						<div class="content">
							<p>Scelerisque enim mi curae erat ultricies lobortis donec velit in per cum consectetur purus a enim platea vestibulum lacinia et elit ante scelerisque vestibulum. At urna condimentum sed vulputate a duis in senectus ullamcorper lacus cubilia consectetur odio proin sociosqu a parturient nam ac blandit praesent aptent. Eros dignissim mus mauris a natoque ad suspendisse nulla a urna in tincidunt tristique enim arcu litora scelerisque eros suspendisse.</p>
						</div>
						<footer>
							<a href="generic.html" class="button alt">Learn More</a>
						</footer>
					</article>
				</div>
			</section>

		<!-- Footer -->
			<footer id="footer">
				<div class="inner">

					<h2>Contact Me</h2>

					<form action="#" method="post">

						<div class="field half first">
							<label for="name">Name</label>
							<input name="name" id="name" type="text" placeholder="Name">
						</div>
						<div class="field half">
							<label for="email">Email</label>
							<input name="email" id="email" type="email" placeholder="Email">
						</div>
						<div class="field">
							<label for="message">Message</label>
							<textarea name="message" id="message" rows="6" placeholder="Message"></textarea>
						</div>
						<ul class="actions">
							<li><input value="Send Message" class="button alt" type="submit"></li>
						</ul>
					</form>

					<ul class="icons">
						<li><a href="#" class="icon round fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon round fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon round fa-instagram"><span class="label">Instagram</span></a></li>
					</ul>

					<div class="copyright">
						&copy; Untitled. Design: <a href="https://templated.co">TEMPLATED</a>. Images: <a href="https://unsplash.com">Unsplash</a>.
					</div>

				</div>
			</footer>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>