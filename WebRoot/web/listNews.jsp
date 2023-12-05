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
		<link rel="stylesheet" type="text/css" href="${ctx }/web/page/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx }/web/css/btn.css" />
		<link rel="stylesheet" type="text/css" href="${ctx }/web/page/simplePagination.css" />
		<script src="${ctx }/web/css/5grid/jquery.js"></script>
		<script src="${ctx }/web/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=60&amp;mobileUI.openerWidth=52"></script>
		<!--[if IE 9]><link rel="stylesheet" href="${ctx }/web/css/style-ie9.css" /><![endif]-->
		
		<script type="text/javascript">
		$(document).ready(function(){
		
			//$("#firstpane .menu_body:eq(0)").show();
			$("#firstpane h3.menu_head").click(function(){
				$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
				$(this).siblings().removeClass("current");
			});
			
			//$("#secondpane .menu_body:eq(0)").show();
			$("#secondpane h3.menu_head").mouseover(function(){
				$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
				$(this).siblings().removeClass("current");
			});
			
		});
		</script>
		<style type="text/css">
		.menu_list{width:268px;margin:0 auto;}
		.menu_head{height:47px;line-height:47px;padding-left:38px;font-size:14px;color:#525252;cursor:pointer;border:1px solid #e1e1e1;position:relative;margin:0px;font-weight:bold;background:#f1f1f1 url(images/pro_left.png) center right no-repeat;}
		.menu_list .current{background:#f1f1f1 url(images/pro_down.png) center right no-repeat;}
		.menu_body{line-height:38px;border-left:1px solid #e1e1e1;backguound:#fff;border-right:1px solid #e1e1e1;}
		.menu_body a{display:block;height:38px;line-height:38px;padding-left:38px;color:#777777;background:#fff;text-decoration:none;border-bottom:1px solid #e1e1e1;}
		.menu_body a:hover{text-decoration:none;}
		</style>
	</head>
	<body>

		<!-- Header -->

			<div id="header-wrapper">
				 <jsp:include page="./menu.jsp"><jsp:param value="news" name="type"/> </jsp:include> 
			</div>

		<!-- Main -->

			<div id="main-wrapper">
				<div class="5grid-layout">
					<div class="row">
						<div class="3u">
						
							<!-- Sidebar -->
							
								<section>
								</section>
								 
						</div>
					
							<!-- Content -->
								 <ul style="wdith:100%;;margin-top: 55px">
								 	<c:forEach items="${SESSION_PAGE_WEB.list }" var="item">
								 	<li style="wdith:100%;border-top: 1px solid #e5e5e5;;margin-top: 5px">
									 	<div style="float:left; display:inline;width: 15%">
									 		<img alt=""  width="150px" src="${ctx }/resource/${item.imgFile}">
									 	</div>
									 	<div style="float:left; display:inline;width: 80%">
									 		<a href="${ctx }/com/getNews.action?uid=${item.id}" target="blank">[${item.type }]<span style="color:#006030;font-size: 16px;font-weight: bold;">${item.title }</span></a>
												 		<br/>
												 		${item.note }
											<br/>
											${item.addDate }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.tags }
									 	</div>
								 	</li>
								 	<div style="clear: both"></div>
								 	</c:forEach>
								 	
								 </ul>
								<div class="pagination-holder clearfix" style="margin-top: 20px;float: right">
										<div id="light-pagination" class="pagination"></div>
								</div>
								 
						</div>
					</div>
				</div>
			  <jsp:include page="./footer.jsp"></jsp:include> 

	</body>
	<script type="text/javascript" src="${ctx }/web/page/jquery.simplePagination.js"></script>
<script type="text/javascript" >
$('#light-pagination').pagination({
	pages: ${SESSION_PAGE_WEB.totalPage},
	cssStyle: 'light-theme',
	displayedPages: 5,
	currentPage:${SESSION_PAGE_WEB.currentPageNumber},
	edges: 7,
	hrefTextSuffix:'&s_type=${type}',
// 	onPageClick:function(pageNumber, event){window.location="${ctx}/com/queryForum.do?pageNum="+pageNumber;}
});
</script>
</html>