<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>线路查询</title>
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
		<jsp:include page="./menu.jsp"><jsp:param value="xianlu" name="type" />
		</jsp:include>
	</div>
	<link rel="stylesheet" href="${ctx }/web/css/btn.css" />
	<!-- Main -->
	<div id="main-wrapper" class="subpage">
		<div class="5grid-layout" style="margin-top: 50px">
			<div class="row">
				<div class="3u" style="width: 100%">
					<form action="${ctx}/com/xianlu.action" method="post">
						<input type="text" placeholder="输入车次名称" name="sid" value="${sid}">
						<input type="text" placeholder="输入站点名称" name="station" size="60" value="${station}" list="pastalist">
						<input type="submit" value="查询" class="button orange">
					</form>
				</div>
				<article class="first" style="margin-top: 1px; width: 100%">
					<table style="width: 100%" class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th>车次(线路)</th>
								<th>IC卡</th>
								<th>起点</th>
								<th>终点</th>
								<th>时间</th>
								<th>线路站点</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="item">
								<tr>
									<td>${item.sid }</td>
									<td>${item.iccard }</td>
									<td>${item.startSta.name }</td>
									<td>${item.endSta.name }</td>
									<td>${item.linetime }</td>
									<td>${item.stations }</td>
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