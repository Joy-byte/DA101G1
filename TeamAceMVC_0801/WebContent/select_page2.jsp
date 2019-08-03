<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title>TeamAce_Backend</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
.content {
  position: relative;
}

#collapsibleNavbar {
  position: absolute;
  z-index: 1;
}
</style>

</head>
<body bgcolor='white'>
	<div class="row justify-content-end">
	    <button class="navbar-toggler" type="button" data-toggle="collapse" 
				data-target="#collapsibleNavbar"><i class="fa fa-bars"></i>
				<span class="navbar-toggler-icon"></span>
		</button>
	</div>
	<div class="collapse navbar-collapse  " id="collapsibleNavbar">
    <div class="container-fluid content">
    	<div class="row justify-content-end" name="menuf">
    		<div class="col-sm-2">
    			<div class="accordion" id="accordionExample">
				  <div class="card">
				    <div class="card-header" id="headingOne">
				      <h2 class="mb-0">
				        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
				          景點管理
				        </button>
				      </h2>
				    </div>

				    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
				        <div class="list-group list-group-flush">
						  <a href="#" class="list-group-item list-group-item-action">
						    Cras justo odio
						  </a>
						  <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
						  <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
						  <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
						  <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="false">Vestibulum at eros</a>
						</div>
				    </div>
				  </div>
				  <div class="card">
				    <div class="card-header" id="headingTwo">
				      <h2 class="mb-0">
				        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
				          會員管理
				        </button>
				      </h2>
				    </div>
				    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
				      <div class="list-group list-group-flush">
						  <a href="#" class="list-group-item list-group-item-action">
						    Cras justo odio
						  </a>
						  <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
						  <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
						  <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
						  <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">Vestibulum at eros</a>
						</div>
				    </div>
				  </div>
				  <div class="card">
				    <div class="card-header" id="headingThree">
				      <h2 class="mb-0">
				        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
				          商品管理
				        </button>
				      </h2>
				    </div>
				    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
				      <div class="card-body">
				        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
				      </div>
				    </div>
				  </div>
				  <div class="card">
				    <div class="card-header" id="headingfor">
				      <h2 class="mb-0">
				        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#amos" aria-expanded="false" aria-controls="collapseThree">
				          行銷管理
				        </button>
				      </h2>
				    </div>
				    <div id="amos" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
				      <div class="card-body">
				        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
				   	   </div>
				 	   </div>
					  </div>
					</div>
    			</div>
    		</div>
    	</div>
	</div>
	
	<div class="col-sm-8">
			<div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
    				  <div class="card card-body">
    				  <table id="table-1">
   <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for TeamAce_Backend</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/landscape/landscape.jsp'>List</a> all Landscapes.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/landscape/LandscapeServlet.do" >
        <b>輸入景點編號:</b>
        <input type="text" name="landscape_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="landscapeSvc" scope="page" class="com.landscape.model.LandscapeService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/landscape/LandscapeServlet.do" >
       <b>選擇景點編號:</b>
       <select size="1" name="landscape_no">
         <c:forEach var="landscapeVO" items="${landscapeSvc.all}" > 
          <option value="${landscapeVO.landscape_no}">${landscapeVO.landscape_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/landscape/LandscapeServlet.do" >
       <b>選擇景點名:</b>
       <select size="1" name="landscape_no">
         <c:forEach var="landscapeVO" items="${landscapeSvc.all}" > 
          <option value="${landscapeVO.landscape_no}">${landscapeVO.landscape_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>景點管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/landscape/addLandscape.jsp'>Add</a> a new Landscape.</li>
</ul>
    				  </div>
    				</div>


    				<div class="collapse" id="collapseExample2">
    				  <div class="card card-body">
    				    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
    				  </div>
    				</div>
	</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>