<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
%>
<%-- <%	 
//  String mem_no = (session.getAttribute("mem_no")).toString();
// 	MemberService memSvc = new MemberService();
// 	MemberVO memberVO = memSvc.getOneMember(mem_no);
// 	pageContext.setAttribute("memberVO", memberVO);
 %> --%>
<html>

<head>
<!-- Required meta tags -->
<title>會員資料 - listOneMember.jsp</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link href='https://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style type="text/css">
.team-card-container {
        margin: 0 auto;
    }

    .team-card-container {
        width: 320px;
        height: 380px;
        background-color: #b3c6d5;
    }

    .team-member-profile-link {
        text-decoration: none;
    }

    .team-member-img {
        max-width: 320px;
        height: 290px;
        object-fit: cover;
        width: 100%;
    }

    figure {
        margin: 0;
        position: relative;
        overflow: hidden;
    }

    figure img.team-member-img {
        position: relative;
        display: block;
        min-height: 100%;
        max-width: 100%;
        opacity: 0.9;
    }

    figure .hover-area {
        position: absolute;
        backface-visibility: hidden;
        top: 0px;
        left: 0px;
        width: 100%;
        text-align: -webkit-center;
        opacity: 0;
        height: 100%;
    }

    .expertise {
        margin-top: 20px;
        font-size: 1.3em;
        font-family: 'Lora', serif;
        letter-spacing: 2px;
        font-weight: 700;
        text-transform: uppercase;
    }

    .expertises {
        margin-top: 0px;
        margin-bottom: 10px;
        padding: 10px;
        list-style: none;
        line-height: 1.5em;
        font-size: 1.2em;
        text-transform: capitalize;
    }

    .view-more {
        margin-top: 0px;
        width: 150px;
        border: 2px solid #fff;
        font-family: 'Roboto', Helvetica, sans-serif;
        padding: 15px;
        vertical-align: bottom;
    }

    .fa.fa-chevron-right.small {
        font-size: 13px;
    }

    figure .hover-area:hover {
        background-color: #0d004b;
        opacity: 0.85;
        color: #ffffff;
        transition: 0.4s ease-in-out;
    }

    .team-member-name {
        font-size: 1.4em;
        font-weight: 700;
        font-family: 'Laro', serif;
        color: #0d004b;
        padding: 25px 25px;
        padding-bottom: 3px;
    }

    .team-member-title {
        font-size: 0.85em;
        font-family: 'Roboto', Helvetica, serif;
        text-transform: uppercase;
        color: #ffffff;
        letter-spacing: 1.5px;
        padding: 0px 25px;

    }



    /* accordion css */
    .tax-icon {
        width: 48px;
        height: 48px;
        margin-right: 20px;
    }

    .attorneys-contact-text {
        color: #fff;
        text-transform: uppercase;
        font-size: 16px;
        letter-spacing: 1px;
        font-family: 'Roboto', Helvetica, sans-serif, Verdana;
        padding: 15px 0px;

    }

    .accordion-info {
        padding: 20px 30px;
        color: rgb(38, 43, 44);
        line-height: 1.5em;
        padding-top: 0;
    }

    .attorney-tax-list {
        display: inline-block;
        list-style-type: bullet;
        position: relative;
        left: 20px;
    }


    .attorney-tax-list-ri {
        float: right;
        position: relative;
        left: -60px;
    }


    /** =======================
Tiki tiki 
 ===========================*/


    .practice-name {
        position: relative;
    }

    .accordion-faq {
        width: 100%;
        max-width: 450px;
        list-style-type: none;
        margin: -5px auto 20px;
        background: rgb(196, 212, 223);
        padding-left: 0;
        border: 0;
        -webkit-border-radius: 4px;
        -moz-border-radius: 4px;
        border-radius: 4px;
    }

    .accordion-faq .link {
        cursor: pointer;
        display: block;
        font-family: 'Lora', serif;
        padding: 18px 15px 18px 30px;
        color: rgb(128, 132, 140);
        font-size: 21px;
        letter-spacing: 0.6px;
        font-weight: 800;
        border-bottom: 1px solid #CCC;
        position: relative;
        -webkit-transition: all 0.4s ease;
        -o-transition: all 0.4s ease;
        transition: all 0.4s ease;
    }

    .accordion-faq li:last-child .link {
        border-bottom: 0;
    }

    .accordion-faq i {
        position: absolute;
        top: 16px;
        left: 12px;
        font-size: 18px;
        color: #595959;
        -webkit-transition: all 0.4s ease;
        -o-transition: all 0.4s ease;
        transition: all 0.4s ease;
    }

    .accordion-faq i.fa-chevron-down {
        right: 12px;
        left: auto;
        font-size: 13px;
    }

    .accordion-faq.open .link {
        color: #fff;
    }

    .accordion-faq.open {
        background-color: #b3c6d5;
    }

    .link.open {
        background: #b3c6d5;
        border-bottom: 0;
        color: #ffffff;
    }

    .link.open i.fa-chevron-down:before {
        -webkit-transform: rotate(180deg);
        -ms-transform: rotate(180deg);
        -o-transform: rotate(180deg);
        position: absolute;
        left: -25px;
        top: 8px;
        color: #0d004b;
        transition-duration: 0.4s;
        transform: rotate(180deg);
    }

    .fa-chevron-down:before {
        content: "\f078";
        position: absolute;
        transition: 0.4s all ease;
        left: -25px;
        top: 8px;
        color: #0d004b;
        transform: rotate(360deg);
    }

    /**
 * Submenu
 -----------------------------*/
    .submenu {
        display: none;
        background: #b3c6d5;
        font-size: 14px;
    }

    .profile-info-container {
        height: 400px;
        background-color: #b3c6d5;
        width: initial;
        max-width: 350px;
        border-radius: 4px;
    }

    .member-name {
        font-family: 'Lora', serif;
        font-size: 1.6em;
        color: #0d004b;
        font-weight: 800;
        line-height: 1.4em;
        padding: 20px 15px;
        padding-left: 30px;
        padding-bottom: 0px;
    }

    .member-position {
        font-size: 0.85em;
        font-family: 'Roboto', Helvetica, serif;
        text-transform: uppercase;
        color: #ffffff;
        letter-spacing: 2.5px;
        padding: 0px 30px;
    }

    .practice-focus {
        font-size: 0.9em;
        font-family: 'Roboto', Helvetica, serif;
        color: #0d004b;
        padding: 10px 30px;
    }

    .practice-focus-title {
        font-weight: 700;
    }

    .profile-telephone-num {
        font-size: 0.9em;
        font-family: 'Roboto', Helvetica, serif;
        color: #0d004b;
        font-weight: 700;
        padding: 0px 30px;
    }

    .phone-tab {
        padding-right: 8px;
    }

    .email-name-member {
        font-family: 'Roboto', Helvetica, serif;
        color: #0d004b;
        font-size: 0.9em;
        padding: 10px 30px;
    }

    .email-member {
        text-decoration: none;
        color: #0d004b;
    }

    .email-bold {
        font-weight: 700;
    }
    .btn {
        width: 39%;
        margin: 18;
        font-family: 'Open Sans', sans-serif;
        font-size: 0.9em;
        transition: all 0.3s ease;
        border-radius: 5px;
    }

    .btn:hover {
        background: #69B692;
        border-color: #69B692;
        cursor: pointer;
    }
</style>
</head>

<body>
<h3>會員資料 - listOneMember.jsp</h3>
    <div class="team-card-container">
        <a href="#" class="team-member-profile-link">
            <figure class="team-card-img-wrapper">
                <img src="MemReader.do?mem_no=${memberVO.mem_no}" class="team-member-img">
                <figcaption class="hover-area">
                    <div class="expertise"> 你在看我嗎 </div>
                    <ul class="expertises">
                        <li><i class="fa fa-chevron-right small" aria-hidden="true"></i> 這 </li>
                        <li><i class="fa fa-chevron-right small" aria-hidden="true"></i> 是 </li>
                        <li><i class="fa fa-chevron-right small" aria-hidden="true"></i> 彩 </li>
                        <li><i class="fa fa-chevron-right small" aria-hidden="true"></i> 蛋 <br> ! </li>
                    </ul>
                    <div class="view-more"> 查看個人頁面 </div>
                </figcaption>
            </figure>
            <div class="team-member-text">
                <div class="team-member-name"> ${memberVO.mem_name} </div>
                <div class="team-member-title"> ${memberVO.mem_nickname} </div>
            </div>
        </a>
    </div>
    
    <div class="profile-info-container" style="margin:2rem auto">
        <div class="member-name"> ${memberVO.mem_name} </div>
        <div class="member-position"> ${memberVO.mem_no} </div>
        <div class="practice-focus">
            <span class="practice-focus-title"> 地址 </span> <br>
            	${memberVO.mem_adrs}
        </div>
        <div class="email-name-member">
            <span class="email-bold"> Email </span> <br>
            <a href="mailto:phernandez@mhlex.com?subject=Inquiry" class="email-member"> ${memberVO.mem_email} </a>
        </div>
        <div class="practice-focus">
            <span class="practice-focus-title"> 電話 </span> <br>
            ${memberVO.mem_tel}
        </div>
        <div class="practice-focus">
            <span class="practice-focus-title"> 生日 </span> <br>
            ${memberVO.mem_birthday}
        </div>
        <div class="practice-focus">
            <span class="practice-focus-title"> 點數餘額 </span> <br>
            ${memberVO.mem_amo}
        </div>
		<form method="post"	action="<%=request.getContextPath()%>/front-end/Member.do"
			style="margin-bottom: 0px;">
			<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> 
			<input type="hidden" name="action" value="getOne_For_Update">
			<input type="submit" value="修改" class="btn">
			<input type="button" value="回首頁" class="btn" onclick="location.href='<%=request.getContextPath()%>/home/TeamAce2.jsp'">
		</form>
    </div>
</body>
</html>