<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>${item.title }</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href="${ctx }/web/css/g.css" rel="stylesheet" />
		
		<link rel="stylesheet" href="${ctx }/web/css/5grid/core.css" />
		<link rel="stylesheet" href="${ctx }/web/css/5grid/core-desktop.css" />
		<link rel="stylesheet" href="${ctx }/web/css/5grid/core-1200px.css" />
		<link rel="stylesheet" href="${ctx }/web/css/5grid/core-noscript.css" />
		<link rel="stylesheet" href="${ctx }/web/css/style-desktop.css" />
		<script src="${ctx }/js/jquery-1.7.2.js" type="text/javascript"></script>
		<script src="${ctx }/web/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=60&amp;mobileUI.openerWidth=52"></script>
		<!--[if IE 9]><link rel="stylesheet" href="${ctx }/web/css/style-ie9.css" /><![endif]-->
<!-- 		<link href="${ctx }/web/raty/demo//stylesheets/labs.css" media="screen" rel="stylesheet" type="text/css"> -->
		<script src="${ctx }/chart/Chart.js" type="text/javascript"></script>
		<link rel="stylesheet" href="${ctx }/web/css/btn.css" />
<style>
.link-m1 {
	width: 200px;
}

.link-m1 ul li {
	float: left;
	margin: 2px 5px;
	list-style: none;
	display: block;
	background-color: #ECECFF;
}
</style>
</head>
	<body>
		<!-- Header -->
			<div id="header-wrapper">
				 <jsp:include page="./menu.jsp"><jsp:param value="news" name="type"/> </jsp:include> 
			</div>

		<!-- Main -->

			<div id="main-wrapper" class="subpage">
				<div class="5grid-layout">
					<div class="row" style="margin-top: 55px">
						<div class="3u" style="float: left">
							 <div class="link-m1">
								<ul>
								<c:forEach items="${tagList }" var="p">
								<li><a href="${ctx }/com/findNews.action?s_type=${item.type}&s_tags=${p}">${p }</a></li>
								</c:forEach>
								</ul>
   							</div>
						</div>
						<article class="first" >
							<div style="float: left; width: 70%">
							<h3 style="font-size: 20px;font-weight: bold;">[${item.type}] ${item.title}</h3>
							<h5>更新日期: ${item.addDate}</h5>
							<h5></h5>
							<h5 style="font-size: 15px">${item.content }</h5>
							 
							</div>
						</article>	
						<div class="clear:both"></div>
						 
					</div>
				</div>
				 
			</div>
		<!-- Footer -->
	<jsp:include page="./footer.jsp"></jsp:include> 
	 
	 <c:if test="${not empty jumptag }">
	 <script>
	$("html,body").animate({scrollTop:$("#${jumptag}").offset().top},50)
	 </script>
	 <%session.removeAttribute("jumptag"); %>
	 </c:if>
	</body>
	 
									
</html>