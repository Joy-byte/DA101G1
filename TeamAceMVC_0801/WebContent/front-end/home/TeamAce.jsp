<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="com.itinerary_detail.model.*"%>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="com.guide.model.*"%>
<%@ page import="com.video.model.*"%>
<%@ page import="java.util.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="pictureSvc" scope="page" class="com.picture.model.PictureService" />
<jsp:useBean id="videoSvc" scope="page" class="com.video.model.VideoService" />
<jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" />
<jsp:useBean id="itinerarySvc" scope="page" class="com.itinerary.model.ItineraryService" />
<jsp:useBean id="itinerary_detailSvc" scope="page" class="com.itinerary_detail.model.Itinerary_detailService" />
<jsp:useBean id="guideSvc" scope="page" class="com.guide.model.GuideService" />


<%
VideoService videoService = new VideoService();
List<VideoVO> itiList = videoService.getAll();
Collections.reverse(itiList);
Set<String> itmSet = new LinkedHashSet<>();
for(VideoVO itmList:itiList){
	itmSet.add(itmList.getMem_no());
	if(itmSet.size()>4){
		break;
	}
}

pageContext.setAttribute("itmSet", itmSet);


%>

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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
  

    <style>

	#map-canvas { height: 400px; width:90%; margin: 0; padding: 0; }
    
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
	    z-index:999 ;
	    position:fixed;
	    width:100%;
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
		font-size: 25px;
		padding-top: 10%;
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
	
 	#itineraryPic img{ 
 		width:80%; 
 		height:300px; 
 	} 
	
	#landscapeAllPic img{
		width:380px;
		height:300px;
	}
	
	#memberArea{
		margin-right:35px;
	}
	#Search{
		margin-right:35px;
	}
	
	#logo{
		width:auto;
		height:40px;
	}
	

/* 	#dropdown-menu1{ */
/* 			z-index:9999; */
/* 	} */
/* 	#opening{ */
/* 	 z-index:-1; */
/* 	} */

	
	
	
/* 	@media (min-width: 576px) */
/* 	.mt-sm-0, .my-sm-0 { */
/* 		 margin-top: 0; */
/* 	} */

    </style>
    
</head>


<body>
<%@ include file="/front-end/header.file" %>
<!-- <div> -->
<!-- 		<nav class="navbar navbar-expand-lg navbar-light bg-light"  > -->
<!-- 		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation"> -->
<!-- 		    <span class="navbar-toggler-icon"></span> -->
<!-- 		  </button> -->
<!-- 		  <div class="collapse navbar-collapse" id="navbarTogglerDemo01"> -->
<%-- 		    <a class="navbar-brand" href="#"><img id="logo" src="<%=request.getContextPath() %>/front-end/home/images/headerLogo.jpg"></a> --%>
<!-- 		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0"> -->
<!-- <!-- 		      <li class="nav-item active"> --> -->
<%-- <%-- 		        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/home/TeamAce.jsp">首頁 <span class="sr-only">(current)</span></a> --%> --%>
<!-- <!-- 		      </li> --> -->
<!-- 		      <li class="nav-item"> -->
<%-- 		        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/itinerary/itineraryUpload.jsp">發佈</a> --%>
<!-- 		      </li> -->
<!-- 		      <li class="nav-item"> -->
<%-- 			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/game/gameroom.jsp">誰是臥底</a> --%>
<!-- 		      </li> -->
<!-- 		      <li class="nav-item"> -->
<%-- 			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/product/Shop.jsp">商城</a> --%>
<!-- 		      </li> -->
<!-- 		      <div class="btn-group"> -->
<!-- 				  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background:#fff !important;"> -->
<!-- 				   在地旅伴 yo -->
<!-- 				  </button> -->
<!-- 				  <div class="dropdown-menu" id="dropdown-menu1"> -->
<%-- 				  <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guide/GuideAdd.jsp">成為在地旅伴</a> --%>
<%-- 				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guidetour/GuideTourList.jsp">伴遊行程</a> --%>
<%-- 				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/resorder/MemResOrder.jsp">我的預約訂單</a> --%>
<!-- <!-- 				    <div class="dropdown-divider"></div> --> -->
<%-- 					<c:if test="${guideSvc.getOneGuideByMemNo(memberVO.mem_no)!=null}"> --%>
<%-- 				    <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/guide/GuideMainPage.jsp">在地旅伴管理</a> --%>
<%-- 				  </c:if> --%>
<!-- 				  </div> -->
<!-- 				</div> -->
				
<!-- 		      <li class="nav-item"> -->
<%-- 			    <a class="nav-link" href="<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${memberVO.mem_no}">個人頁面</a> --%>
<!-- 		      </li> -->
<!-- 		    </ul> -->
<!-- 		    <div class="form-inline my-2 my-lg-0" id="headright"> -->
<!-- <!-- 		    <div id="Search"> --> -->
<!-- <!-- 		    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"  id="searchText"> --> -->
<!-- <!-- 		     <button class="btn btn-outline-success my-2 my-sm-0"  onclick="search()">Search</button> --> -->
<!-- <!-- 		     </div> --> -->
<!-- 		    <div id="memberArea"> -->
<%-- 			    <c:if test="${memberVO.mem_name !=null}"> --%>
<%-- 	            	<a href="<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${memberVO.mem_no}"> --%>
<%-- 	            	 ${memberVO.mem_name} </a> ,你好 ! 點數:${memberVO.mem_amo} --%>
<%-- 	        	</c:if> --%>
<!--         	</div> -->
<!--         	<div>	 -->
<%-- 		      <button class="btn btn-outline-success my-2 my-sm-0" onclick="loginbtns()"><b id="isLogin" name="${ memberVO.mem_name}" >登入</b></button> --%>
<!-- 		     </div> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 	</nav> -->
<!-- 	</div> -->
	<div class="cover"></div>
	
	
			<!-- 	Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog modal-xl" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel"><span id="itineraryTitle">Modal title</span></h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>

							      <div class="modal-body modal-xl"  id="itineraryBody">								     
							        <div id="itineraryContent">
							        	<img id="itineraryPic1" src="#"  alt=""  style="width:300px; height:120px; margin-left:190px; transform:rotate(-15deg);" >
							        	
									    <div id="itineraryContentB">
									    </div>
									    <p></p>
									    <div id="itineraryPic">
										    <img id="itineraryPic2" src="#"  alt=""  style="width:300px; height:120px; margin-left:800px; transform:rotate(15deg); ">
										    <img id="itineraryPic3" src="#"  alt=""  style="width:300px; height:140px; margin-left:100px; transform:rotate(-15deg);">
										    <img id="itineraryPic4" src="#"  alt=""  style="width:300px; height:150px; margin-left:100px; transform:rotate(15deg);">
										    <img id="itineraryPic5" src="#"  alt=""  style="width:300px; height:160px; margin-left:400px; transform:rotate(-15deg);">
									    </div>
									    
<!-- 		  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel"> -->
<!-- 		  <ol class="carousel-indicators" id="itineraryPicLi"> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li> -->
		    
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="4"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="5"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="6"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="7"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="8"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="9"></li> -->
<!-- 		    <li data-target="#carouselExampleIndicators" data-slide-to="10"></li> -->
<!-- <div class="carousel-item"> -->
<!-- 		      <img src="https://picsum.photos/1200/300?random=3" class="d-block w-100"> -->
<!-- 		    </div> -->

<!-- 		  </ol> -->
		  
<!-- 		  <div class="carousel-inner" id="itineraryPic"> -->
<!-- 		  <div id="itineraryPic"> -->
<!-- 		    <div class="carousel-item active" > -->
<!-- 		      <img src="#" class="d-block w-100" id="test1"> -->
<!-- 		    </div> -->
<!-- 		    <div class="carousel-item"> -->
<!-- 		      <img src="#" class="d-block w-100" id="test2"> -->
<!-- 		    </div> -->
<!-- 		    <div class="carousel-item"> -->
<!-- 		      <img src="#" class="d-block w-100" id="test3"> -->
<!-- 		    </div> -->
<!-- 		    <div class="carousel-item"> -->
<!-- 		      <img src="#" class="d-block w-100" id="test4"> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 		  </div> -->
<!-- 		  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev"> -->
<!-- 		    <span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
<!-- 		    <span class="sr-only">Previous</span> -->
<!-- 		  </a> -->
<!-- 		  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next"> -->
<!-- 		    <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
<!-- 		    <span class="sr-only">Next</span> -->
<!-- 		  </a> -->
		  
<!-- 		</div> -->
									</div>
							      </div>
							       
									   <div id="triangle1"  class="obj obj-triangle-01 js-rellax" data-rellax-speed="-2.0"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-triangle-01.png" alt=""></div>
		                               <div id="bobo1"  class="obj obj-circle-06 js-rellax" data-rellax-speed="-2.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>
		                			   <div id="bobo2" class="obj obj-circle-07 js-rellax" data-rellax-speed="-.8"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-02.png" alt=""></div>
		                			   <div id="bobo3" class="obj obj-circle-08 js-rellax" data-rellax-speed="1.2"><img src="<%=request.getContextPath() %>/front-end/home/TeamAceCSS/img_obj-circle-03.png" alt=""></div>

							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							        <button type="button" class="btn btn-primary"  onclick="itineraryAdd('${memberVO.mem_no}')" id="fav">加入我的最愛</button>
							        <button type="button" class="btn btn-success" data-dismiss="modal" onclick="itineraryStart('${memberVO.mem_no}')">進行行程遊戲</button>
							      </div>
							      
							    </div>
							  </div>
					</div> 	
					
					
					
					<!-- 	landscapeModal -->
					<div class="modal fade" id="landscapeModal" tabindex="-1" role="dialog" aria-labelledby="landscapeModalLabel" aria-hidden="true">
							  <div class="modal-dialog .modal-lg" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="landscapeModalLabel">Modal title</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>		        
							      </div>
							      
							      <div class="modal-body .modal-lg" >		
							      		<div id="map-canvas"></div>						     
							       			 <div id="landscapeAllPic">
													
										    </div>
							      </div>
							      							       
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>							    
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
                    			<c:forEach var="itmSet" items="${itmSet}" varStatus="s">
                    				<img src="MemReader.do?mem_no=${itmSet}"onclick="showVideo('${itmSet}')" >
                    				<c:if test="${s.count=='2'}"><img src="<%=request.getContextPath()%>/front-end/home/images/sun.jpg" onclick="showVideo('${itmSet}')" ></i></c:if>
                    			</c:forEach>						
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
                <figure class="opening__logo"><img src="images/TeamAceLogo.png"></figure>
                
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
						<video src="" autoplay="" id="ttstory" controls width="400px" 
						controls height="500px"  onclick="nextStory()" ></video>
						<button onclick="hideVideo()" id="b1">關</button>
					</div>
                    <ul class="m-row m-row--fw--wrap" id="js-container">
                        <!--行程泡泡-->
                        <c:forEach var="itineraryVO" items="${itinerarySvc.getLastF()}">
                        <li class="contents-list__unit">
                            <div class="cp-unit cp-unit--l" style="top: <%=itineraryHeight[count]%>; left: <%=itineraryWidth[count++]%>;">                                
                                    <div class="category category--happy">
                                        <!--放頭貼-->                                  
                                        <img src="MemReader.do?mem_no=${itineraryVO.mem_no}" onclick="location.href='<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${itineraryVO.mem_no}'">                                 
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
                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 43px; left: 27px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/06.html"><div class="category category--healthy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span>
                            <img src="https://picsum.photos/1200/300?random=3" class="d-block w-100"></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span></span></span><br><span class="t-container"></span></p></div></a></div></li>

                             <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 100px; left: 54px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/06.html"><div class="category category--healthy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span>
                             <img src="https://picsum.photos/1200/300?random=8" class="d-block w-100"></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span></span></span><br><span class="t-container"></span></p></div></a></div></li>

                              <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 60px; left: 80px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/06.html"><div class="category category--healthy">
                              <img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span>
                              <img src="https://picsum.photos/1200/300?random=7" class="d-block w-100"></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span></span></span><br><span class="t-container"></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 80px; left: 0px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/04.html"><div class="category category--happy">
                            <img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span>
                            <img src="https://picsum.photos/1200/300?random=6" class="d-block w-100"></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span></span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 10px; left: 22px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/04.html"><div class="category category--happy">
                            <img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span>
                            <img src="https://picsum.photos/1200/300?random=5" class="d-block w-100"></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span></span></span></p></div></a></div></li>

                            <li class="contents-list__unit"><div class="cp-unit cp-unit--l" style="top: 60px; left: 60px;"><a href="http://kumejima-nuchigusuitrip.com/happy-healthy/04.html"><div class="category category--happy"><img src="https://i.imgur.com/DgH8dwz.jpg"></div><figure class="thumb m-circle"><span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span><span class="photo"><span class="arrow"></span>
                            <img src="https://picsum.photos/1200/300?random=4" class="d-block w-100"></span></figure><div class="text m-row m-row--jc--center"><p><span class="t-container"><span></span></span></p></div></a></div></li>
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
  	  <script type="text/javascript" src="http://code.google.com/apis/gears/gears_init.js"></script>
  	  <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
  	  
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
					location.href='<%=request.getContextPath() %>/front-end/member/login.jsp';
				}
// 				function logoutbtns(){
// 					location.href='MemberLogout.do';
// 				}
				
				function gid(id){
					return document.getElementById(id);
				}
				
				function hideVideo(){
					var targetArea=gid('tstory');
					gid('ttstory').pause();
				 	targetArea.style.visibility='hidden';			 	
				 	vdoarr=[];
				 	vdoCount=0;
// 				 	$('#tstory').hide();		 	
// 				 	count = 0;
				}
				
				var vdoarr = [];
				function showVideo(memNo){
					$.ajax({
						 type: "GET",
						 url: "ItineraryServlet.do",
		 				 data: creatQueryString(memNo),
						 dataType: "json",
						 success: function (data){
							 console.log(data);						 
		 					$(data).each(function(i, item){
		 						if(i==0){
		 							var targetArea=gid('tstory');
			 						targetArea.style.visibility='visible';
			 						gid('ttstory').src="VideoReader.do?vdo_no="+item.vdo_no;
// 			 						gid('ttstory').start();	
		 						}else{
		 							vdoarr.push(item.vdo_no)						
		 						}	
		 						
		 					});
// 		 					$('#ttstory').attr('onclick','nextStory('+vdoarr+")");
					     },
			             error: function(){alert("AJAX-grade發生錯誤囉!")}
			         })
			         
			         function creatQueryString(memNo){
						var queryString= {"action":"videoShow","mem_no":memNo};
						return queryString;
					}
																
				}
				
				
				var vdoCount=0;
				function nextStory(){
// 					console.log(vdoCount)
// 					console.log(vdoarr.length)
					if(vdoCount>=vdoarr.length){
						$('#ttstory').attr('src','#');
						hideVideo();
					}else{
						console.log('WTF')
						$('#ttstory').attr('src','VideoReader.do?vdo_no='+vdoarr[vdoCount++])	
					}								
				}
				
				
				var itineraryStart_itinerary_no ; 
				function itineraryDetail(itinerary_no){
					$('#fav').text('加入我的最愛');
					itineraryStart_itinerary_no=itinerary_no;
					$.ajax({
						 type: "GET",
						 url: "ItineraryServlet.do",
		 				 data: creatQueryString(itinerary_no),
						 dataType: "json",
						 success: function (data){
// 							 console.log(data.length)
							$('#itineraryPic').hide();
							 clearSelect();
							 var y=1;
		 					$(data).each(function(i, item){
		 						
// 		 						if(i==0){
// 		 							$('#itineraryPic').append("<div class='carousel-item active'><img src=PictureReader.do?pic_no="+item.pic_no+" class=d-block w-100 ></div>");
// 		 							$('#itineraryPicLi').append("<li data-target=#carouselExampleIndicators data-slide-to="+y+ "class=active></li>");
// 		 						}else{
// 		 							$('#itineraryPicLi').append("<li data-target=#carouselExampleIndicators data-slide-to="+y+ "></li>");
// 		 							$('#itineraryPic').append("<div class='carousel-item'><img src=PictureReader.do?pic_no="+item.pic_no+" class=d-block w-100 ></div>");
// 		 						}
		 						$('#itineraryTitle').append(item.landscape_name);
		 						if( i != (data.length-1)){
		 							$('#itineraryTitle').append("<i class='fa fa-arrow-right'></i>");
		 						}
		 						
		 						// <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
		 						console.log(y)
		 						console.log(item.pic_no)
// 		 						$('#itineraryPic').append('<img id="itineraryPic"'+(y+1)+' src="#"  alt=""  style="width:300px; height:120px; margin-left:190px; transform:rotate(-15deg);">')
		 						if(y>0){
		 							$('#itineraryPic').show();
		 						}
		 											
// 		 						$('#itineraryPic'+ y).show();
		 						$('#itineraryPic'+ y++).attr('src','PictureReader.do?pic_no='+item.pic_no);
		 						
// 									$('#test'+ y++).attr('src','PictureReader.do?pic_no='+item.pic_no);

// 		 						$('#itineraryContentB').text(item.itinerary_content);
		 						if(item.question!=null){
		 							console.log(item.question);
		 							$('#itineraryContentB').append("<b >完成行程條件</b><br><b>1.回答問題:</b>"+item.question+"<br>"+
		 									"<b>2.到指定地點定位</b>")
		 						}
		 					});
					     },
			             error: function(){alert("AJAX-grade發生錯誤囉!")}
			         })
			         
			         function creatQueryString(itinerary_no){
						var queryString= {"action":"itineraryDetail","itinerary_no":itinerary_no};
						return queryString;
					}
					
					function clearSelect(){
						$('#itineraryContentB').empty();
						$('#itineraryPicLi').empty();
						$('#itineraryPic img').attr('src','#');
						$('#itineraryTitle').empty();
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
									$('#landscapeModalLabel').text(item.landscape_name);
// 									console.log(item.pic_no);				
// 									console.log(landscapeLat);
// 									console.log(landscapeLng);
		 					});
		 					
		 					var mapOptions = {
							          center: { lat: landscapeLat, lng: landscapeLng},
							          zoom: 15
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
						$('#landscapeAllPic').empty();
					}
							    	        	
		     		        	        
				}
				
				
				swal.setDefaults({
				    confirmButtonText: "確定",
				    cancelButtonText: "取消"
				});
				
				function itineraryStart(memNo){
					
					
					$.ajax({
						 type: "GET",
						 url: "ItineraryServlet.do",
		 				 data: creatQueryString(),
						 dataType: "json",
						 success: function (){
								 swal({
							            title: "行程遊戲已開始",
							            html: "是否前往",
							            type: "success", // type can be "success", "error", "warning", "info", "question"
							            	showCancelButton: true,
							        		showCloseButton: true,
							        }).then(
								        	   function (result) {
									                if (result) {

									                }
									            }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
									            		swal("取消", "", "error");
										        })
							 					
					     },
// 			             error: function(){alert("AJAX-grade發生錯誤囉!")}
			         })
			         
			         function creatQueryString(){
						var queryString= {"action":"itineraryStart","itineraryStart_itinerary_no":itineraryStart_itinerary_no
								,"mem_no": memNo};
						return queryString;
					}
					
					function clearSelect(){
// 						$('#landscapeAllPic').empty();
					}
				}
				
				
				
				var countdownnumber=5;
				var countdownid;
//	 			var isFirst; 
				
				function initial(itinerary_no,param_memNo){ countdownfunc(itinerary_no,param_memNo); }
				function countdownfunc(itinerary_no,param_memNo){		
				 var x=document.getElementById("countdown");
				 x.innerHTML=countdownnumber;
				 if (countdownnumber==0){ 
//				  alert("倒數結束");
				  clearTimeout(countdownid);
				 }else{
//	 					if(isFirst){
//	 						var itiNo = itinerary_no;
//	 						var paramNo = param_memNo;
//	 						isFirst = false;
//	 					}
				  countdownnumber--;
							  $.ajax({
									 type: "GET",
									 url: "ItineraryStartServlet.do",
									 data: creatQueryString(itinerary_no,param_memNo),
									 dataType: "json",
									 success: function (data){
										 clearSelect();
										$(data).each(function(i, item){
											console.log(item.misj);
										});
								
								     },
						            error: function(){alert("AJAX-grade發生錯誤囉!")}
						        })
						        
						        function creatQueryString(itiNo,memNo){
									var queryString= {"action":"theTime","itinerary_no":itinerary_no,"mem_no":param_memNo,"time":countdownnumber};
									return queryString;
								}
								
								function clearSelect(){
				
								}
								
				  if(countdownid){
				   clearTimeout(countdownid);
				  }
				  countdownid=setTimeout("countdownfunc("+"'"+itinerary_no+"'"+","+"'"+param_memNo+"'"+")",1000);
				 }
				}
				
				
				
				function itineraryAdd(memNo){
					$.ajax({
						 type: "GET",
						 url: "Itinerary_trackServlet.do",
		 				 data: creatQueryString(itineraryStart_itinerary_no),
						 dataType: "json",
						 success: function (data){
							 $('#fav').text('已加入我的最愛');
							 swal({
						            title: "已加入收藏",
						            html: "",
						            type: "success", // type can be "success", "error", "warning", "info", "question"
// 						            	showCancelButton: true,
						        		showCloseButton: true,
						        }).then(
							        	   function (result) {
								                if (result) {

								                }
								            }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
								            		swal("取消", "", "error");
									        })				
		 					$(data).each(function(i, item){
								
		 					});
					     },
			             error: function(){alert("AJAX-grade發生錯誤囉!")}
			         })
			         
			         function creatQueryString(itinerary_no){
						var queryString= {"action":"insertItinerary_track","itinerary_no":itineraryStart_itinerary_no,"mem_no": memNo};
						return queryString;
					}
					
				}
				
				
				
				
				//
				function search(){				
					console.log($('#searchText').val())
					if($('#searchText').val()!=""){
						$.ajax({
							 type: "GET",
							 url: "ItineraryServlet.do",
			 				 data: creatQueryString(itineraryStart_itinerary_no),
							 dataType: "json",
							 success: function (data){
								
// 								 <li class="contents-list__unit">
<%-- 		                            <div class="cp-unit cp-unit--l" style="top: <%=itineraryHeight[count]%>; left: <%=itineraryWidth[count++]%>;">                                 --%>
// 		                                    <div class="category category--happy">
// 		                                        <!--放頭貼-->                                  
<%-- 		                                        <img src="MemReader.do?mem_no=${itineraryVO.mem_no}" onclick="location.href='<%=request.getContextPath() %>/front-end/member/profile.jsp?mem_no=${itineraryVO.mem_no}'">                                  --%>
// 		                                    </div>
// 		<!--                                    <a href="javascript: itineraryDetail(); " data-toggle="modal" data-target="basicModal"> -->
// 											<a data-toggle="modal" data-target="#exampleModal" onclick="itineraryDetail('${itineraryVO.itinerary_no}')">
// 		                                    <figure class="thumb m-circle">
// 		                                        <span class="bg-morphing js-morphing" data-r="150"><canvas width="280" height="280"></canvas></span>                                        
// 		                                        <span class="photo">                                        	                                     	
// 		                                            <span class="arrow"></span>
// 		                                            <img src="PictureReader.do?pic_no= ${pictureSvc.getOnePic(itineraryVO.getItinerary_no()).pic_no}"  alt="">  
// 		                                        </span>                                        
// 		                                    </figure>
// 		                                    </a>
// 		                                    <div class="text m-row m-row--jc--center">
// 		                                    <p>
// 		                                        <span class="t-container">
// 		                                        <c:forEach var="itinerary_detailVO" items='${itinerary_detailSvc.getLands(itineraryVO.getItinerary_no())}' varStatus="s"> 
// 														<span data-toggle="modal" data-target="#landscapeModal"  class="landscapeName" onclick="landscapeAll('${itinerary_detailVO.landscape_no}')">${ landscapeSvc.getOneLas(itinerary_detailVO.getLandscape_no()).landscape_name}	
// 															<c:if test="${!s.last}"><i class="fa fa-arrow-right"></i></c:if>
// 														</span>																										            													        												
// 												</c:forEach>                             
// 		                                        </span>
// 		                                    </p>
// 		                                    </div>                     
// 		                            </div>
// 		                        </li>
		                        
		                        
								 $('#js-container').empty();
								 console.log(data)
			 					$(data).each(function(i, item){
			 						$('#js-container').append("<li class='contents-list__unit'>"+
			 								"<div class='cp-unit cp-unit--l' "+
			 								"<div class='category category--happy'>"+
			 								"<img src=MemReader.do?mem_no="+item.mem_no+
			 								"</div>"	+
			 								"<a data-toggle='modal' data-target='#exampleModal' onclick=itineraryDetail("+item.Itinerary_no+")>"+
			 								"<figure class='thumb m-circle'>"+
			 								"<span class='bg-morphing js-morphing' data-r='150'><canvas width='280' height='280'></canvas></span>"+
			 								"<span class='photo'>"+
			 								"<span class='arrow'></span>"+
			 								"<img src=PictureReader.do?pic_no=" +"PI00005"+">"+
			 								"</span>"+
			 								"</figure>"+
			 								"</a>"+
			 								"<div class='text m-row m-row--jc--center'>"+
			 								"<p>"+
			 								"<span class='t-container'>"+
			 								"<span data-toggle=modal data-target=#landscapeModal  class=landscapeName onclick='landscapeAll()'>"+
			 								"</span>"+
			 								"</p>"+
			 								" </div>"+
			 								"</div>"+
			 								"</li>")
			 						
			 					});
			 					
						     },
				             error: function(){alert("AJAX-grade發生錯誤囉!")}
				         })
				         
				         function creatQueryString(itinerary_no){
							var queryString= {"action":"search","searchText":$('#searchText').val()};
							return queryString;
						}
						
						
					}
					
					
				}

			      
		</script>	
</body>
</html>