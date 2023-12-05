<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>公交查询</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link href="${ctx }/web/css/g.css" rel="stylesheet" />
<link href="${ctx }/web/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/web/css/5grid/core.css" />
<link rel="stylesheet" href="${ctx }/web/css/5grid/core-desktop.css" />
<link rel="stylesheet" href="${ctx }/web/css/5grid/core-1200px.css" />
<link rel="stylesheet" href="${ctx }/web/css/5grid/core-noscript.css" />
<link rel="stylesheet" href="${ctx }/web/css/style-desktop.css" />
<script
	src="${ctx }/web/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=60&amp;mobileUI.openerWidth=52"></script>
<!--[if IE 9]><link rel="stylesheet" href="${ctx }/web/css/style-ie9.css" /><![endif]-->
<!-- 		<link href="${ctx }/web/raty/demo//stylesheets/labs.css" media="screen" rel="stylesheet" type="text/css"> -->
<style>
#l-map {
	height: 500px;
	width: 100%;
}

#r-result, #r-result table {
	width: 100%;
	font-size: 12px;
}
</style>
</head>
<body>
	<!-- Header -->
	<div id="header-wrapper">
		<jsp:include page="./menu.jsp"><jsp:param value="gongjiao" name="type" />
		</jsp:include>
	</div>
	<link rel="stylesheet" href="${ctx }/web/css/btn.css" />
	<!-- Main -->
	<div id="main-wrapper" class="subpage">
		<div class="5grid-layout" style="margin-top: 50px">
			<div class="row">
				<div class="3u" style="width: 100%">
					<form action="${ctx}/com/gongjiao.action" method="post">
						<input type="text" placeholder="输入出发站点" name="station1" value="${station1}" size="30" list="pastalist">
						<input type="text" placeholder="输入终点" name="station2" size="30" value="${station2}" list="pastalist">
						<input type="submit" value="查询" class="button orange">
					</form>
				</div>
				<article class="first" style="margin-top: 1px; width: 100%">
					<table style="width: 100%" class="table table-striped table-hover table-bordered">
						<tbody>
							<c:forEach items="${list }" var="item" varStatus="vitem">
								<tr>
									<td>方案${vitem.count}</td>
									<td>${item.distance }米</td>
									<td>${item.stationSize }站</td>
									<td>${item.path }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</article>
				<div class="clear:both"></div>
			</div>
			<div id="r-result"></div>
		</div>
	</div>
	<datalist id="pastalist">
		<c:forEach items="${staList}" var="fitem">
			<option>${fitem.name }</option>
		</c:forEach>
	</datalist>
	<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>