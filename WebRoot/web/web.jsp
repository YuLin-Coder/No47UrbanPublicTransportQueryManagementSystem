<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>${appTitle }</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href="${ctx }/web/css/g.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx }/web/css/5grid/core.css" />
		<link rel="stylesheet" href="${ctx }/web/css/5grid/core-desktop.css" />
		<link rel="stylesheet" href="${ctx }/web/css/5grid/core-1200px.css" />
		<link rel="stylesheet" href="${ctx }/web/css/5grid/core-noscript.css" />
		<link rel="stylesheet" href="${ctx }/web/css/style.css" />
		<link rel="stylesheet" href="${ctx }/web/css/style-desktop.css" />
		<script src="${ctx }/web/css/5grid/jquery.js"></script>
		<script src="${ctx }/web/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=60&amp;mobileUI.openerWidth=52"></script>
		<!--[if IE 9]><link rel="stylesheet" href="${ctx }/web/css/style-ie9.css" /><![endif]-->
		<link rel="stylesheet" type="text/css" href="${ctx }/web/photo/css/style.css" />
	</head>
	<body>

		<!-- Header -->

			<div id="header-wrapper">
				 <jsp:include page="./menu.jsp"><jsp:param value="index" name="type"/> </jsp:include> 
			</div>

			<div id="main-wrapper" style="">
				<div class="5grid-layout">
					
					<!-- Banner -->

						<div class="row">
							<div class="12u">
								<div id="banner">
									<div class="main_banner">
									<div class="main_banner_wrap">
										<canvas id="myCanvas" width="10" height="10"></canvas>
										<div class="main_banner_box" id="m_box">
											<a href="javascript:void(0)" class="banner_btn js_pre">
												<span class="banner_btn_arrow"><i></i></span>
											</a>
											<a href="javascript:void(0)" class="banner_btn btn_next js_next">
												<span class="banner_btn_arrow"><i></i></span>
											</a>
											<ul>
												<c:forEach items="${ilist }" var="iitem" varStatus="vsitem">
												<li id="imgCard${vsitem.index }">
													<a href="${iitem.imageUrl }"><span style="opacity:0;"></span></a>      
													<img src="${ctx }/resource/${iitem.imagePath}" alt="">
													<p style="bottom:0">${iitem.note }</p>
												</li> 
												</c:forEach>
											</ul>
											<!--火狐倒影图层-->
											<p id="rflt"></p>
											<!--火狐倒影图层-->
										</div>
										<!--序列号按钮-->
										<div class="btn_list">
											<span class="curr"></span><span></span><span></span><span></span><span></span>        
										</div>
									</div>
									</div>
<!-- 									<script src="${ctx }/web/photo/js/jquery.js"></script> -->
									<script src="${ctx }/web/photo/js/lanren.js"></script>
								</div>
							</div>
						</div>
						
						<div class="row">
						
							 <ul style="wdith:100%;">
							 	<c:forEach items="${indexNewsList }" var="item">
							 	<li style="wdith:100%">
								 	<div style="float:left; display:inline;width: 15%">
								 		<img alt="" height="150px" width="150px" src="${ctx }/resource/${item.imagePath}">
								 	</div>
								 	<div style="float:left; display:inline;width: 80%">
								 		<a href="${ctx}/com/getNews.action?uid=${item.id}" target="blank"><span style="color:#006030;font-size: 16px;font-weight: bold;">${item.title }</span></a>
											 		<br/>
											 		${item.note }
										<br/>
										${item.lastUser }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.lastDate }
								 	</div>
							 	</li>
							 	<div style="clear: both"></div>
							 	</c:forEach>
							 	
							 </ul>
							 	 
						
						</div>
						
						
				</div>
			</div>
			<script type="text/javascript">
				function tosearch(){
					if($("#searchKey").val()==''){
						alert("请输入搜索关键字");$("#searchKey").focus();
						return;
					}
					$("#searchForm").submit();
				}
			</script>
			
				<footer class="5grid-layout" id="site-footer">
					 
				</footer>
		<jsp:include page="./footer.jsp"></jsp:include>
	</body>
</html>