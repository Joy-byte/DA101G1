<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="com.itinerary_detail.model.*"%>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="java.util.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="pictureSvc" scope="page" class="com.picture.model.PictureService" />
<jsp:useBean id="videoSvc" scope="page" class="com.video.model.VideoService" />
<jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" />
<jsp:useBean id="itinerarySvc" scope="page" class="com.itinerary.model.ItineraryService" />
<jsp:useBean id="itinerary_detailSvc" scope="page" class="com.itinerary_detail.model.Itinerary_detailService" />


<!DOCTYPE html>
<html lang="ja">
<head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# website: http://ogp.me/ns/website#">    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=no">

<!--     <link rel="manifest" href="http://kumejima-nuchigusuitrip.com/assets/img/favicon/site.webmanifest"> -->
    <link rel="mask-icon" href="http://kumejima-nuchigusuitrip.com/assets/img/favicon/safari-pinned-tab.svg" color="#5bbad5">

    <title>首頁</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/common.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/style.css" media="all">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-125155046-1"></script>
    <script>
	    window.dataLayer = window.dataLayer || [];
	    function gtag(){dataLayer.push(arguments);}
	    gtag('js', new Date());
	    gtag('config', 'UA-125155046-1');
    </script>

    <style>

	#map-canvas { height: 400px; width:100%; margin: 0; padding: 0; }
    
    .category{
        background-color: gray;
        margin-left:10px; 
        border-radius: 50%;
        width:90px !important;
        height:90px !important;
        overflow: hidden;
    }
    
    .memPic img{
    	margin-left:15px;
        margin-top:20px; 
        border-radius: 50%;
        width:110px !important;
        height:110px !important;
        overflow: hidden;
    }
    
    
    .navbar {
	    z-index:100;
	}
	
	#headright button{
		margin-left: 35px;
		margin-top: 5px;
	}
	
	.photo img{
		height:245px;
		width:245px;
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
	
	.modal-backdrop.show {
      opacity: .2; 
	}
	
	#itineraryContent{
/* 			background-color: rgba(171,237,234,0.8); */
    }
    #itineraryContentB {
    		background-color: rgba(171,237,234,0.8);
		    max-width: 470px;
		    padding: 16px 0 20px;
		    margin: 0 auto;
		    text-align: center;
		    position: relative;
	}
	
	#bobo1{
		margin-left: 500px;
		margin-top: 300px;
	}
	
	#bobo2{
		margin-left: 700px;
		margin-top: 100px;
	}
	
	.t-container{
		font-size:18px;
	}
	
	.landscapeName:hover{
			cursor: pointer;
  			color:orange;
	}
	
	
/* 	@media (min-width: 576px) */
/* 	.mt-sm-0, .my-sm-0 { */
/* 		 margin-top: 0; */
/* 	} */

    </style>
    
</head>


<body>
	<div class="cover"></div>
	<div>
	<nav class="navbar navbar-expand-lg navbar-light bg-light"  >
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		    <a class="navbar-brand" href="#">Logo</a>
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item active">
		        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/member/login.jsp">首頁 <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/itinerary/Upload.jsp">發佈</a>
		      </li>
		      <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/game/gameroom.jsp">誰是臥底</a>
		      </li>
		    </ul>
		    <div class="form-inline my-2 my-lg-0" id="headright">
		    <c:if test="${memberVO.mem_name !=null}">
            	${memberVO.mem_name} ,你好 ! 點數::
        	</c:if>
		    	
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" >購物車</button>
		      <button class="btn btn-outline-success my-2 my-sm-0" onclick="loginbtns()">登入</button>
		    </div>
		  </div>
	</nav>
	</div>
	
			<!-- 	Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog modal-xl" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>

							      <div class="modal-body modal-xl"  id="itineraryBody">								     
							        <div id="itineraryContent">
							        	<img id="itineraryPic1" src="#"  alt="">
									    <div id="itineraryContentB">
									    </div>
									    <img id="itineraryPic2" src="#"  alt="">
									    <img id="itineraryPic3" src="#"  alt="">
									    <img id="itineraryPic4" src="#"  alt="">
									    <img id="itineraryPic5" src="#"  alt="">
									</div>
							      </div>
							       
									   <div id="triangle1"  class="obj obj-triangle-01 js-rellax" data-rellax-speed="-2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
		                               <div id="bobo1"  class="obj obj-circle-06 js-rellax" data-rellax-speed="-2.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
		                			   <div id="bobo2" class="obj obj-circle-07 js-rellax" data-rellax-speed="-.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-02.png" alt=""></div>
		                			   <div id="bobo3" class="obj obj-circle-08 js-rellax" data-rellax-speed="1.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>

							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="itineraryStart()">進行行程遊戲</button>
							      </div>
							      
							    </div>
							  </div>
					</div> 	
					
					
					
					<!-- 	landscapeModal -->
					<div class="modal fade" id="landscapeModal" tabindex="-1" role="dialog" aria-labelledby="landscapeModalLabel" aria-hidden="true">
							  <div class="modal-dialog modal-xl" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="landscapeModalLabel">Modal title</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>		        
							      </div>
							      
							      <div class="modal-body modal-xl" >		
							      		<div id="map-canvas"></div>						     
							       			 <div id="landscapeAllPic">
													
										    </div>
							      </div>
							      							       
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="itineraryStart()">進行行程遊戲</button>
							      </div>
							      
							    </div>
							  </div>
					</div> 	
	
	<div class="js-bg-section bg-section">
        <!--背景變換的圖-->
        <figure class="bg-section__list"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_slide-01.jpg" alt="" class="m-ofi"></figure>
        
        <figure class="bg-section__list"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_slide-02.jpg" alt="" class="m-ofi"></figure>
        
        <figure class="bg-section__list"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_slide-03.jpg" alt="" class="m-ofi"></figure>
    </div>

	<div class="js-opening-bg opening-bg">
		<img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_slide-blur.jpg" alt="" class="m-ofi">
	</div>

	<div class="st-container" style="position:relative;">
        <!-- 第一層白色透明底 -->
        <div style="width:100vw;height:1000px;top:930px;background-color:#fff;position:absolute;z-index:10;opacity: 0.5"></div>
        <!-- 第二層黑色透明底 -->
        <div style="width:100vw;height:1000px;top:1930px;background-color:#000;position:absolute;z-index:10;opacity: 0.5"></div>
        <!-- 第三層白色透明底 -->
        <div style="width:100vw;height:1160px;top:2930px;background-color:#fff;position:absolute;z-index:10;opacity: 0.5"></div>
        <header class="st-hdr" id="js-hdr">
            <div class="hdr__inner">
                <div class="hdr__block" style="width: 140px;">
                    <!-- 左方固定區塊內 --> 
                    <div class="memPic">
                    	<img src="images/2.png"onclick="show1()" >
                    	<img src="images/3.png">
                    	<img src="images/4.png">
                    	<img src="images/5.png">
                    	<img src="images/6.png">
                    	<img src="images/7.png">
                    </div>
                </div>
                <div class="tag-container js-tag-target">
                    <div class="tag-list js-sort-tag" id="js-scroller">
                       <!-- 切圖區塊，勿刪 -->
                    </div>
                </div>
            </div>
        </header>

		<main class="st-contents" style="position:relative;z-index:20">
		
		
		    <div class="m-scroll">scroll</div>
			<div class="opening m-vhmax" id="opening">
                <!-- 大logo圖區塊 -->
                <figure class="opening__logo"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_logo-opening.png" alt="久米島 ぬちぐすいな旅へ。"></figure>
                
                <div class="obj obj-triangle-01 js-rellax" data-rellax-speed="-2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-02 js-rellax" data-rellax-speed="1.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-02.png" alt=""></div>
                <div class="obj obj-triangle-03 js-rellax" data-rellax-speed="1.3"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-03.png" alt=""></div>
            </div>

            <section class="about  m-row--jc--center m-row--ai--center" id="about">
                <!-- 勿刪 -->
            </section>
				
 			<% 
 					String itineraryHeight[]={"85px","47px","130px","91px","20px","70px"};
 					String itineraryWidth[]={"5px","72px","94px","0px","32px","54px"};
 					int count = 0;
 			%>
			<div class="contents-container" id="js-contents" style="padding-left: 200px">
				<nav class="filter-nav is-open">
					<div class="filter-nav__block filter-nav__block--tag is-hidden">
						<div class="tag-list js-sort-tag" id="js-scroller-sp">
							<!--霧面背景切清晰，勿刪-->
						</div>
					</div>
				</nav>
                <!-- 6個<li>(行程)分一區塊 -->
                <div class="contents-list is-h-auto" id="sortmenu">
                	<div id="tstory" style="visibility:hidden" >
						<video src="" autoplay="" id="ttstory" width="200px" 
							height="400px"  onclick="nextt()" ></video>
						<button onclick="webCtrl()" id="b1">關</button>
					</div>
                    <ul class="m-row m-row--fw--wrap" id="js-container">
                        <!--行程泡泡-->
                        <c:forEach var="itineraryVO" items="${itinerarySvc.getLastF()}">
                        <li class="contents-list__unit">
                            <div class="cp-unit cp-unit--l" style="top: <%=itineraryHeight[count]%>; left: <%=itineraryWidth[count++]%>;">                                
                                    <div class="category category--happy">
                                        <!--放頭貼-->                                  
                                        <img src="MemReader.do?mem_no=${itineraryVO.mem_no}">                                    
                                    </div>
<!--                                    <a href="javascript: itineraryDetail(); " data-toggle="modal" data-target="basicModal"> -->
									<a data-toggle="modal" data-target="#exampleModal" onclick="itineraryDetail('${itineraryVO.itinerary_no}')">
                                    <figure class="thumb m-circle">
                                        <span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span>                                        
                                        <span class="photo">                                        	                                     	
                                            <span class="arrow"></span>
                                            <img src="PictureReader.do?pic_no= ${pictureSvc.getOnePic(itineraryVO.getItinerary_no()).pic_no}"  alt="">  
                                        </span>                                        
                                    </figure>
                                    </a>
                                    <div class="text m-row m-row--jc--center">
                                    <p>
                                        <span class="t-container">
                                        <c:forEach var="itinerary_detailVO" items='${itinerary_detailSvc.getLands(itineraryVO.getItinerary_no())}' varStatus="s"> 
												<span data-toggle="modal" data-target="#landscapeModal"  class="landscapeName" onclick="landscapeAll('${itinerary_detailVO.landscape_no}')">${ landscapeSvc.getOneLas(itinerary_detailVO.getLandscape_no()).landscape_name}	
													<c:if test="${!s.last}"><i class="fa fa-arrow-right"></i></c:if>
												</span>																										            													        												
										</c:forEach>                             
                                        </span>
                                    </p>
                                    </div>                     
                            </div>
                        </li>
                        </c:forEach>                            
                    </ul>                    			
                </div>
               	
                
					
                <!-- 切第二張背景區塊 -->
                <section class="accommodation js-setpos" id="accommodation">
                    <div class="contents-list is-h-auto" id="sortmenu">
                        <ul class="m-row m-row--fw--wrap" id="js-container">
                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 43px; left: 27px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/06.html"><div class="category category--healthy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-06.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span><br><span class="t-container"></span></p></div></a></div></li>

                             <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 100px; left: 54px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/06.html"><div class="category category--healthy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-06.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span><br><span class="t-container"></span></p></div></a></div></li>

                              <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 60px; left: 80px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/06.html"><div class="category category--healthy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-06.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span><br><span class="t-container"></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 80px; left: 0px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/04.html"><div class="category category--happy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-04.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 10px; left: 22px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/04.html"><div class="category category--happy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-04.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 60px; left: 60px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/04.html"><div class="category category--happy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-04.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>
                        </ul>
                    </div>
                </section>

                <!-- 切第三張背景區塊 -->
                <section class="access js-setpos" id="access">
                    <div class="contents-list is-h-auto" id="sortmenu">
                        <ul class="m-row m-row--fw--wrap" id="js-container">
                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 0px; left: 13px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/13.html"><div class="category category--happy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-13.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 44px; left: 60px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/12.html"><div class="category category--happy-healthy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-12.png" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 20px; left: 70px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/18.html"><div class="category category--happy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-18.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 20px; left: 29px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/15.html"><div class="category category--healthy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-15.png" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 85px; left: 29px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/18.html"><div class="category category--happy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-18.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 20px; left: 31px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/18.html"><div class="category category--happy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/tmb_contents-18.gif" alt=""></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span>IIIMisJ</span></span></p></div></a></div></li>
                        </ul>
                    </div>
                </section>
				
                <!-- 泡泡裝飾們 -->
                <div class="obj obj-triangle-01 js-rellax" data-rellax-speed="-2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-02 js-rellax" data-rellax-speed="1.5"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-04.png" alt=""></div>
                <div class="obj obj-triangle-03 js-rellax" data-rellax-speed="2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-03.png" alt=""></div>
                <div class="obj obj-triangle-04 js-rellax" data-rellax-speed="-1.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-05 js-rellax" data-rellax-speed="1.6"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-05.png" alt=""></div>
                <div class="obj obj-triangle-06 js-rellax" data-rellax-speed="-.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-06.png" alt=""></div>
                <div class="obj obj-circle-01 js-rellax" data-rellax-speed="-2.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-02 js-rellax" data-rellax-speed="-.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-02.png" alt=""></div>
                <div class="obj obj-circle-03 js-rellax" data-rellax-speed="1.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-04 js-rellax" data-rellax-speed="-1.4"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div>
                <div class="obj obj-circle-05 js-rellax" data-rellax-speed="3.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div>

                <div class="obj obj-triangle-07 js-rellax" data-rellax-speed="-2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-08 js-rellax" data-rellax-speed="1.5"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-04.png" alt=""></div>
<!--                 <div class="obj obj-triangle-09 js-rellax" data-rellax-speed="2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-03.png" alt=""></div> -->
                <div class="obj obj-triangle-10 js-rellax" data-rellax-speed="-1.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-11 js-rellax" data-rellax-speed="1.6"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-05.png" alt=""></div>
                <div class="obj obj-triangle-12 js-rellax" data-rellax-speed="-.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-06.png" alt=""></div>
                <div class="obj obj-circle-06 js-rellax" data-rellax-speed="-2.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-07 js-rellax" data-rellax-speed="-.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-02.png" alt=""></div>
                <div class="obj obj-circle-08 js-rellax" data-rellax-speed="1.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
<!--                 <div class="obj obj-circle-09 js-rellax" data-rellax-speed="-1.4"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div> -->
                <!-- <div class="obj obj-circle-10 js-rellax" data-rellax-speed="3.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div>

                <div class="obj obj-triangle-13 js-rellax" data-rellax-speed="-2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-14 js-rellax" data-rellax-speed="1.5"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-04.png" alt=""></div>
                <div class="obj obj-triangle-15 js-rellax" data-rellax-speed="2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-03.png" alt=""></div>
                <div class="obj obj-triangle-16 js-rellax" data-rellax-speed="-1.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-17 js-rellax" data-rellax-speed="1.6"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-05.png" alt=""></div>
                <div class="obj obj-triangle-18 js-rellax" data-rellax-speed="-.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-06.png" alt=""></div>
                <div class="obj obj-circle-11 js-rellax" data-rellax-speed="-2.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-12 js-rellax" data-rellax-speed="-.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-02.png" alt=""></div>
                <div class="obj obj-circle-13 js-rellax" data-rellax-speed="1.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-14 js-rellax" data-rellax-speed="-1.4"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div>
                <div class="obj obj-circle-15 js-rellax" data-rellax-speed="3.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div>

                <div class="obj obj-triangle-19 js-rellax" data-rellax-speed="-2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-20 js-rellax" data-rellax-speed="1.5"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-04.png" alt=""></div>
                <div class="obj obj-triangle-21 js-rellax" data-rellax-speed="2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-03.png" alt=""></div>
                <div class="obj obj-triangle-22 js-rellax" data-rellax-speed="-1.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-23 js-rellax" data-rellax-speed="1.6"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-05.png" alt=""></div>
                <div class="obj obj-triangle-24 js-rellax" data-rellax-speed="-.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-06.png" alt=""></div>
                <div class="obj obj-circle-16 js-rellax" data-rellax-speed="-2.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-17 js-rellax" data-rellax-speed="-.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-02.png" alt=""></div>
                <div class="obj obj-circle-18 js-rellax" data-rellax-speed="1.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-19 js-rellax" data-rellax-speed="-1.4"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div>
                <div class="obj obj-circle-20 js-rellax" data-rellax-speed="3.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div>

                <div class="obj obj-triangle-25 js-rellax" data-rellax-speed="-2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-26 js-rellax" data-rellax-speed="1.5"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-04.png" alt=""></div>
                <div class="obj obj-triangle-27 js-rellax" data-rellax-speed="2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-03.png" alt=""></div>
                <div class="obj obj-triangle-28 js-rellax" data-rellax-speed="-1.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
                <div class="obj obj-triangle-29 js-rellax" data-rellax-speed="1.6"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-05.png" alt=""></div>
                <div class="obj obj-triangle-30 js-rellax" data-rellax-speed="-.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-06.png" alt=""></div>
                <div class="obj obj-circle-21 js-rellax" data-rellax-speed="-2.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-22 js-rellax" data-rellax-speed="-.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-02.png" alt=""></div>
                <div class="obj obj-circle-23 js-rellax" data-rellax-speed="1.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
                <div class="obj obj-circle-24 js-rellax" data-rellax-speed="-1.4"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div>
                <div class="obj obj-circle-25 js-rellax" data-rellax-speed="3.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-01.png" alt=""></div> -->

			</div><!--class="contents-container"-->
		</main>

		<footer class="st-ftr">
            <div class="m-inner" style="padding: 0px">
                <div class="pagetop" id="js-pagetop"><a href="#">Page Top</a></div>
                <div class="ftr__note">
                    footer文字footer文字footer文字footer文字<br>
                    footer文字footer文字footer文字footer文字footer文字footer文字footer文字footer文字<br>
                    footer文字footer文字footer文字footer文字footer文字footer文字footer文字footer文字
                </div>
                <p class="copyright"><small>Copyright &copy; III-TeamAce</small></p>
            </div>
        </footer>
	</div><!-- class="st-container" -->




<script src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/jquery.min.js.下載"></script>
<script src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/vendor.bundle.js.下載"></script>
<script src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/main.bundle.js.下載"></script>
<script src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/home.bundle.js.下載"></script>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!-- 		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 			crossorigin="anonymous"></script> -->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous">
		</script>
		
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous">
		</script>
		
		<script type="text/javascript"
     	 	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC08hJMCtyO3EvPjS3C598ptqIz5k-wKY4">
  	  </script>
  	  
		<script>
				function loginbtns(){
					location.href='<%=request.getContextPath() %>/front-end/member/login.jsp';
				}
				
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
					gid('ttstory').src="VideoReader.do?vdo_no=${videoSvc.getOneVdo('IT00006').vdo_no}" ;
					gid('ttstory').start();				 
				}
				
				
				function itineraryDetail(itinerary_no){
					$.ajax({
						 type: "GET",
						 url: "ItineraryServlet.do",
		 				 data: creatQueryString(itinerary_no),
						 dataType: "json",
						 success: function (data){
							 clearSelect();
							 var y=1;
		 					$(data).each(function(i, item){
// 		 						console.log(item.pic_no)
		 						$('#itineraryBody').append(item.landscape_no);
		 						$('#itineraryPic'+ y++).attr('src','PictureReader.do?pic_no='+item.pic_no);
		 						$('#itineraryContentB').text(item.itinerary_content);
		 					});
					     },
			             error: function(){alert("AJAX-grade發生錯誤囉!")}
			         })
			         
			         function creatQueryString(itinerary_no){
						var queryString= {"action":"itineraryDetail","itinerary_no":itinerary_no};
						return queryString;
					}
					
					function clearSelect(){
// 						$('#itineraryBody').empty();
					}
				}
				
				

				function landscapeAll(landscapeNo){			 
					$.ajax({
						 type: "GET",
						 url: "ItineraryServlet.do",
		 				 data: creatQueryString(landscapeNo),
						 dataType: "json",
						 success: function (data){
							 clearSelect();
							
		 					$(data).each(function(i, item){
		 							$('#landscapeAllPic').append('<img src=PictureReader.do?pic_no='+item.pic_no+"><br>");
									landscapeLat = item.landscape_lat;
									landscapeLng = item.landscape_lng;
// 									console.log(item.pic_no);				
// 									console.log(landscapeLat);
// 									console.log(landscapeLng);
		 					});
		 					
		 					var mapOptions = {
							          center: { lat: landscapeLat, lng: landscapeLng},
							          zoom: 17
							};
							
							var map = new google.maps.Map(
				            document.getElementById('map-canvas'),
				            mapOptions);
							
							markerLatLng = new google.maps.LatLng({lat: landscapeLat, lng: landscapeLng});
					        marker = new google.maps.Marker({ 
						        position: markerLatLng,
					            map: map 
				     	    });
		 					
					     },
			             error: function(){alert("AJAX-grade發生錯誤囉!")}
			         })
			         
			         function creatQueryString(landscapeNo){
						var queryString= {"action":"landscapeAll","landscapeNo":landscapeNo};
						return queryString;
					}
					
					function clearSelect(){
//						$('#itineraryBody').empty();
					}
							    	        	
		     		        	        
				}

 
			      
		</script>	
</body>
</html>