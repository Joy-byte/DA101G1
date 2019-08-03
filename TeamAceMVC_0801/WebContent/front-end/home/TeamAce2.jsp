<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="com.itinerary_detail.model.*"%>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="java.util.*"%>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>

<html>
	<head>
		<title>TeamAce</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/home/assets/css/main.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/home/assets/css/common.css" media="all">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/home/assets/css/style.css" media="all">
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
				margin-top:100px;
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
			</header>
			<div class="member">
					<img src="images/1.png"onclick="show1()">
					<img src="images/2.png">
					<img src="images/3.png">
					<img src="images/4.png">
					<img src="images/5.png">
					<img src="images/6.png">
					<img src="images/7.png">										
				</div>	
			<br><br>
			
<% MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); %>
		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li>${memberVO.mem_name}</li>
					<li>${memberVO.mem_email}</li>
					
					<li><a href="<%=request.getContextPath() %>/front-end/member/login.jsp">登入</a></li>
					<li><a href="<%=request.getContextPath() %>/front-end/itinerary/Upload.jsp">發佈</a></li>
					<li><a href="<%=request.getContextPath() %>/front-end/member/MemberAdd.jsp">註冊</a></li>
					<li><a href="<%=request.getContextPath() %>/front-end/game/gameroom.jsp">誰是臥底</a></li>
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
					<button onclick="webCtrl()" id="b1">關</button>
				</div>
				<div class="inner">
					<header>
						<h1>Yo Yo Yo</h1>
					</header>
				</div>
				<a href="#one" class="more">Learn More</a>
			</section>

		<!-- One -->
								
<jsp:useBean id="pictureSvc" scope="page" class="com.picture.model.PictureService" />
<jsp:useBean id="videoSvc" scope="page" class="com.video.model.VideoService" />
<jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" />
<jsp:useBean id="itinerarySvc" scope="page" class="com.itinerary.model.ItineraryService" />
<jsp:useBean id="itinerary_detailSvc" scope="page" class="com.itinerary_detail.model.Itinerary_detailService" />

			<section id="one" class="wrapper post bg-img" data-bg="ocean.jpg">
				<div class="inner">
					<article class="box">
						<header>
							<h1><B>文青之旅</B></h1>
						</header>
						<div class="content">
							<ul class="flex">
							<c:forEach var="itineraryVO" items="${itinerarySvc.getLastF()}">	
		                        <li>
		                            <div class="cp-unit cp-unit--l" style="top: 60px; left: 2px;">
		                                <a href="http://kumejima-nuchigusuitrip.com/happy-healthy/11.html">
		                                    <div class="category category--happy">
		                                        <h4>		                                                                    															
												<c:forEach var="itinerary_detailVO" items='${itinerary_detailSvc.getLands(itineraryVO.getItinerary_no())}'>
																	${ landscapeSvc.getOneLas(itinerary_detailVO.getLandscape_no()).landscape_name}																	
												</c:forEach>
		                                        </h4>
		                                    </div>
		                                <figure class="thumb m-circle">		                          
		                                    <span class="photo">
		                                        <span class="arrow">
<!-- 		                                         箭頭 -->
		                                        </span>
		                                     <img src="PictureReader.do?pic_no= ${pictureSvc.getOnePic(itineraryVO.getItinerary_no()).pic_no}" alt="">    
<%-- 		                                       <video src="PictureReader.do?pic_no= ${pictureSvc.getOnePic(itineraryVO.getItinerary_no()).pic_no}" alt="">                            --%>
		                                    </span>		                                    
		                                </figure>
		                                </a>
		                            </div>
		                        </li>
		                      </c:forEach>
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
			<script src="<%=request.getContextPath() %>/front-end/home/assets/js/jquery.min.js"></script>
			<script src="<%=request.getContextPath() %>/front-end/home/assets/js/jquery.scrolly.min.js"></script>
			<script src="<%=request.getContextPath() %>/front-end/home/assets/js/jquery.scrollex.min.js"></script>
			<script src="<%=request.getContextPath() %>/front-end/home/assets/js/skel.min.js"></script>
			<script src="<%=request.getContextPath() %>/front-end/home/assets/js/util.js"></script>
			<script src="<%=request.getContextPath() %>/front-end/home/assets/js/main.js"></script>
			<script>
			function gid(id){
				return document.getElementById(id);
			}
			function webCtrl(){
				var targetArea=gid('tstory');
			 	targetArea.style.visibility='hidden';
			 	gid('ttstory').pause();
			 	count = 0;
			}
			
			function show1(){
				var targetArea=gid('tstory');
				targetArea.style.visibility='visible';
				gid('ttstory').class = 'a1';
				gid('ttstory').src="VideoReader.do?vdo_no=${videoSvc.getOneVdo('IT00013').vdo_no}" ;
				gid('ttstory').start();				 
			}
			</script>
	
	</body>
</html>