<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common/head.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${appTitle }</title>
<meta name="Keywords" content="${appTitle }"/>
<meta name="Description" content="${appTitle }"/> 
<%@ include file="./common/js.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wQiidqcchqojD6dsBxx9EAGyNiY6VFAm"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js"></script>
</head>
<body>
    <!--[if lte IE 7]>
        <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
    <![endif]-->
    <div id="bjui-window">
    <header id="bjui-header">
        <div class="bjui-navbar-header">
            <button type="button" class="bjui-navbar-toggle btn-default" data-toggle="collapse" data-target="#bjui-navbar-collapse">
            </button>
            <a class="bjui-navbar-logo" href="#" style="font-size: 20px;margin-top: 10px;margin-left: 20px;color:#fff">${appTitle }</a>
        </div>
        <nav id="bjui-navbar-collapse">
            <ul class="bjui-navbar-right">
                <li class="datetime"><div><span id="bjui-date"></span> <span id="bjui-clock"></span></div></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">我的账户 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
<!--                         <li><a href="changepwd.html" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="400" data-height="260">&nbsp;<span class="glyphicon glyphicon-lock"></span> 修改密码&nbsp;</a></li> -->
<!--                         <li><a href="#">&nbsp;<span class="glyphicon glyphicon-user"></span> 我的资料</a></li> -->
<!--                         <li class="divider"></li> -->
                        <li><a href="${ctx}/com/logout.action" class="red">&nbsp;<span class="glyphicon glyphicon-off"></span> 注销登陆</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="bjui-hnav">
            <button type="button" class="btn-default bjui-hnav-more-left" title="导航菜单左移"><i class="fa fa-angle-double-left"></i></button>
            <div id="bjui-hnav-navbar-box">
                <ul id="bjui-hnav-navbar">
                	<c:if test="${SESSION_BEAN.role=='SimpleUser' }">
                		<li class="active"><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-cog"></i> 投诉建议</a>
	                        <div class="items hide" data-noinit="true">
	                        
	                             <ul class="menu-items" data-faicon="table">
	                                <li><a href="${ctx}/sys/queryUserNote.action" data-options="{id:'mainqueryUserNote', faicon:'table','fresh':true}">投诉建议</a></li>
	                            </ul>
	                        </div>
	                    </li>
                	</c:if>
                	<c:if test="${SESSION_BEAN.role=='SysUser' }">
                    <li class="active"><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-cog"></i> 站点线路</a>
                        <div class="items hide" data-noinit="true">
                             <ul class="menu-items" data-faicon="table">
                                <li><a href="${ctx}/sys/queryStation.action" data-options="{id:'mainqueryStation', faicon:'table','fresh':true}">站点管理</a></li>
                                <li><a href="${ctx}/sys/queryLine.action" data-options="{id:'mainqueryLine', faicon:'table','fresh':true}">线路管理</a></li>
                                <li><a href="${ctx}/sys/queryBus.action" data-options="{id:'mainqueryBus', faicon:'table','fresh':true}">车辆管理</a></li>
                                <%-- <li><a href="${ctx}/sys/queryDriver.action" data-options="{id:'mainqueryDriver', faicon:'table','fresh':true}">司机管理</a></li>
                                <li><a href="${ctx}/sys/queryLineBus.action" data-options="{id:'mainqueryLineBus', faicon:'table','fresh':true}">车次管理</a></li> --%>
                            </ul>
                            
                        </div>
                    </li>
                    <li class=""><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-cog"></i> 系统管理</a>
                        <div class="items hide" data-noinit="true">
                        
                             <ul class="menu-items" data-faicon="table">
                                <li><a href="${ctx}/sys/querySimpleUser.action" data-options="{id:'mainquerySimpleUser', faicon:'table','fresh':true}">用户管理</a></li>
                                <%-- <li><a href="${ctx}/sys/queryUserNoteAll.action" data-options="{id:'mainqueryUserNote', faicon:'table','fresh':true}">投诉建议</a></li>
                                <li><a href="${ctx}/sys/queryLostProperty.action" data-options="{id:'mainqueryLostProperty', faicon:'table','fresh':true}">失物领取</a></li>
                                <li><a href="${ctx}/sys/queryNews.action" data-options="{id:'mainqueryNews', faicon:'table','fresh':true}">公告管理</a></li> --%>
                            </ul>
                            
                        </div>
                    </li>
                    <%-- <li class=""><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-database"></i> 数据库管理</a>
	                        <div class="items hide" data-noinit="true">
	                        
	                             <ul class="menu-items" data-faicon="table">
	                                <li><a href="${ctx}/sys/queryDatabaseFile.action" data-options="{id:'mainqueryDatabaseFile', faicon:'database','fresh':true}">数据库备份/还原</a></li>
	                            </ul>
	                        </div>
	                </li> --%>
                    </c:if>
                    
                    <c:if test="${not empty SESSION_BEAN.user }">
                    <li class=""><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-cog"></i> 个人信息</a>
	                        <div class="items hide" data-noinit="true">
	                        
	                             <ul class="menu-items" data-faicon="table">
	                                <li><a href="${ctx}/com/toSelf.action" data-options="{id:'toSelf', faicon:'table','fresh':true}">个人资料</a></li>
	                            </ul>
	                        </div>
	                </li>
	                </c:if>
                </ul>
            </div>
            <button type="button" class="btn-default bjui-hnav-more-right" title="导航菜单右移"><i class="fa fa-angle-double-right"></i></button>
        </div>
    </header>
    <div id="bjui-container">
        <div id="bjui-leftside">
            <div id="bjui-sidebar-s">
                <div class="collapse"></div>
            </div>
            <div id="bjui-sidebar">
                <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
                <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">
                </div>
            </div>
        </div>
        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li data-url="content.jsp"><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent">
                <div class="navtabPage unitBox">
                    <div class="bjui-pageContent" style="background:#FFF;">
                        Loading...
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer id="bjui-footer"><a href="https://armycodes.com/" target=_blank>Copyright &copy; 从戎源码网 from 2023.</a> &nbsp;&nbsp;<a href="./exit.jsp">返回首页</a>　
    </footer>
    </div>
</body>
</html>