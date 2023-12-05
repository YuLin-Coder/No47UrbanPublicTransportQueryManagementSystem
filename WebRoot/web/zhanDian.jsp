<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>站点查询</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link href="${ctx }/web/css/g.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/web/css/5grid/core.css" />
<link rel="stylesheet" href="${ctx }/web/css/5grid/core-desktop.css" />
<link rel="stylesheet" href="${ctx }/web/css/5grid/core-1200px.css" />
<link rel="stylesheet" href="${ctx }/web/css/5grid/core-noscript.css" />
<link rel="stylesheet" href="${ctx }/web/css/style-desktop.css" />
<script
	src="${ctx }/web/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=60&amp;mobileUI.openerWidth=52"></script>
<!--[if IE 9]><link rel="stylesheet" href="${ctx }/web/css/style-ie9.css" /><![endif]-->
<!-- 		<link href="${ctx }/web/raty/demo//stylesheets/labs.css" media="screen" rel="stylesheet" type="text/css"> -->
<link rel="stylesheet" href="${ctx }/web/css/btn.css" />
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
		<jsp:include page="./menu.jsp"><jsp:param value="index" name="type" />
		</jsp:include>
	</div>
	<!-- Main -->
	<div id="main-wrapper" class="subpage">
		<div class="5grid-layout" style="margin-top: 50px">
			<div class="row">
				<div class="3u" style="width: 100%">
					<input type="text" placeholder="上车站点或者起始位置" id="startSta" list="pastalist" size="30">
					<input type="text" placeholder="下车站点或者终点位置" id="endSta" list="pastalist" size="30">
					<select id="choseType">
						<option value="0">最少时间</option>
						<option value="1">最少换乘</option>
						<option value="2">最少步行</option>
						<option value="3">不乘地铁</option>
					</select>
					<a class="button blue" onclick="mysearch()">查询</a>
				</div>
				<article class="first" style="margin-top: 1px">
					<div id="l-map"></div>
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
	<!-- Footer -->
	<jsp:include page="./footer.jsp"></jsp:include>
	<script type="text/javascript">
		var map = new BMap.Map("l-map");
		function myFun(result) {
			cityName = result.name;
			map.centerAndZoom(cityName, 14);
			return cityName;
		}
		var myCity = new BMap.LocalCity();
		cityName = myCity.get(myFun);
		// 百度地图API功能
		map.addControl(new BMap.MapTypeControl());
		map.enableScrollWheelZoom(true);
	
		var routePolicy = [ BMAP_TRANSIT_POLICY_LEAST_TIME, BMAP_TRANSIT_POLICY_LEAST_TRANSFER,
			BMAP_TRANSIT_POLICY_LEAST_WALKING, BMAP_TRANSIT_POLICY_AVOID_SUBWAYS ];
		var transit = new BMap.TransitRoute(map, {
			renderOptions : {
				map : map,
				panel : "r-result"
			},
			policy : 0
		});
	
		function mysearch() {
			var startSta = $("#startSta").val();
			var endSta = $("#endSta").val();
			if (startSta != "" && endSta != "") {
				map.clearOverlays();
				var i = $("#choseType").val();
				search(startSta, endSta, routePolicy[i]);
			}
		}
		function search(startSta, endSta, route) {
			transit.setPolicy(route);
			transit.search(startSta, endSta);
		}
	</script>
</body>
</html>