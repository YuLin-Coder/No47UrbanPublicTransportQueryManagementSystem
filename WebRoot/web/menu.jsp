<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<script src="${ctx }/js/jquery-1.9.0.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="${ctx }/layer/skin/layer.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/web/css/form.css" />
<script type="text/javascript" src="${ctx }/layer/layer.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=d1B52oGmpS09oORvx0L5momtSS0B2fum"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx }/web/css/style.css" />
<header class="5grid-layout" id="site-header" style="width: 100%">
	<div class="row" style="width: 100%">
		<div class="12u" style="width: 100%">
			<div id="logo">
				<h1 class="mobileUI-site-name" style="font-size: 30px">${appTitle}</h1>
			</div>
			<nav class="mobileUI-site-nav" id="site-nav" style="float: left; text-align: left; margin-left: 230px">
				<ul style="font-size: 14px; float: left; text-align: left">
					<li id="menuli_gongjiao"><a href="${ctx}/com/gongjiao.action">公交查询</a></li>
					<li id="menuli_xianlu"><a href="${ctx}/com/xianlu.action">线路查询</a></li>
					<%-- <li id="menuli_news"><a href="${ctx}/com/findNews.action">新闻公告</a></li> --%>
					<li id="menuli_index"><a href="${ctx}/com/index.action">站点地图</a></li>
					<li id="menuli_line"><a href="${ctx}/com/line.action">线路地图</a></li>
				</ul>
			</nav>
			<nav class="mobileUI-site-nav" id="site-nav" style="float: right; text-align: right; margin-right: 100px">
				<ul style="font-size: 14px; float: left; text-align: left">
					<c:if test="${empty SESSION_BEAN.user }">
						<!-- 						<li><a href="javascript:;" onclick="tologin()">登录</a></li> -->
						<li><a href="${ctx }/index.jsp">登录</a></li>
						<li><a href="javascript:;" onclick="toreg()">注册</a></li>
					</c:if>
					<c:if test="${not empty SESSION_BEAN.user }">
						<li id="menuli_user"><span style="font-size: 16px; color: red">欢迎您:</span><a href="${ctx}/main.jsp"
							style="font-size: 15px; color: red">${SESSION_BEAN.user.user.userName }</a></li>
						<li><a href="${ctx}/com/userLogout.action">退出</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</header>
<div id="regbox" style="display: none; width: 700px">
	<form action="" method="post" class="basic-grey">
		<label>
			<span>*账号 :</span>
			<input id="regname" type="text" name="" placeholder="您的账号" />
		</label>
		<label>
			<span>*密码 :</span>
			<input id="regpwd" type="password" name="" placeholder="您的密码" />
		</label>
		<label>
			<span>*姓名 :</span>
			<input id="reguname" type="text" name="" placeholder="您的姓名" />
		</label>
		<label>
			<span>性别 :</span>
			<select id="reggender">
				<option value="1">男</option>
				<option value="0">女</option>
			</select>
		</label>
		<label>
			<span>联系电话 :</span>
			<input id="regphone" type="text" name="" maxlength="11" placeholder="您的联系电话" />
		</label>
		<label>
			<span>电子邮箱 :</span>
			<input id="regemail" type="email" name="" placeholder="您的电子邮箱" />
		</label>
		<label>
			<span>联系地址 :</span>
			<input id="regaddress" type="text" name="" placeholder="您的联系地址" />
		</label>
		<label>
			<span>验证码 :</span>
			<input type="text" id="checkcode_reg" name="checkcode" maxlength="4" size="4" style="width: 100px; float: left" />
			<img id="checkcode_reg_img" style="margin-left: 30px" height="40px" id="checkcode2"
				src="${pageContext.request.contextPath }/checkcode" title="点击更换" alt="验证码占位图"
				onclick="this.src = '${pageContext.request.contextPath }/checkcode?' + Math.random();" />
		</label>
		<div style="clear: both"></div>
		<label>
			<span>&nbsp;</span>
			<input type="button" class="button" onclick="doreg()" value="注册" />
			<input type="button" class="button" onclick="closepageii()" value="取消" style="margin-left: 100px" />
		</label>
	</form>
</div>
<div id="loginbox" style="display: none; width: 700px">
	<form action="${ctx }/com/userLogin.do" method="post" class="basic-grey">
		<label>
			<span>账号 :</span>
			<input id="loginname" type="text" name="name" placeholder="您的账号" />
		</label>
		<label>
			<span>密码 :</span>
			<input id="loginpwd" type="password" name="email" placeholder="您的密码" />
		</label>
		<label>
			<span>验证码 :</span>
			<input type="text" id="checkcode_login" name="checkcode" maxlength="4" size="4" style="width: 100px; float: left" />
			<img id="checkcode_login_img" style="margin-left: 30px" height="40px" id="checkcode2"
				src="${pageContext.request.contextPath }/checkcode" title="点击更换" alt="验证码占位图"
				onclick="this.src = '${pageContext.request.contextPath }/checkcode?' + Math.random();" />
		</label>
		<div style="clear: both"></div>
		<label>
			<span>&nbsp;</span>
			<input type="button" class="button" onclick="doLogin()" value="登录" />
			<input type="button" class="button" onclick="closepageii()" value="取消" style="margin-left: 100px" />
		</label>
	</form>
</div>
<%String type = request.getParameter("type");
%>
<script type="text/javascript">
<!--
var pageii;
$("li[id^='menuli_']").each(function(){
	  if($(this).attr("id")=="menuli_"+"<%=type%>"){
		  $(this).addClass("current_page_item");
	  }else{
		  $(this).removeClass("current_page_item");
	  }
});
function closepageii(){
	layer.close(pageii);
}
function tologin(){
	$("#checkcode_login_img").click();
	layer.close(pageii);
	pageii = $.layer({
	    type: 1,
	    title: '用户登录',
	    area: ['720', '310'],
	    border: [10, 0.3, '#000'],
	    shade: [0.3, '#000'],
	    closeBtn: [1, true],
	    shadeClose: true,
	    fadeIn: 300,
	    fix: true,
	    page: {dom: '#loginbox',},
	    close:function(){
	    }
	});
}
function toreg(){
	layer.close(pageii);
	$("#checkcode_reg_img").click();
	pageii = $.layer({
	    type: 1,
	    title: '用户注册',
	    area: ['720', '580'],
	    border: [10, 0.3, '#000'],
	    shade: [0.3, '#000'],
	    closeBtn: [1, true],
	    shadeClose: true,
	    fadeIn: 300,
	    fix: true,
	    closeBtn: [1, true],
	    page: {dom: '#regbox',},
	    close:function(){
	    	 
	    }
	});
}
function doLogin(){
	var username = $("#loginname").val();
	var password = $("#loginpwd").val();
	if(username==""){
		alert("请输入账号");return false;
	}
	if(password==""){
		alert("请输入密码");return false;
	}
	var checkcode = $("#checkcode_login").val();
	if(checkcode==""){
		alert("请输入验证码");return false;
	}
	$.ajax({
     		url:"${ctx }/com/userLogin.action",
     		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
     		type:"post",
     		dataType:"json",
     		data:{"name":username,"password":""+password,"checkcode":checkcode},//window.encodeURI(中文值)：对字符串进行编码
     		success:function(json){
     			if(json.msg=="成功"){
					layer.close(pageii);
					window.location.href="${ctx }/com/index.action";
     			}else{
     				alert(json.msg);
     			}
     		},
     		error:function(json){}
	});
}
function doreg(){
	var username = $("#regname").val();
	var password = $("#regpwd").val();
	var reguname = $("#reguname").val();
	var reggender = $("#reggender").val();
	var regphone = $("#regphone").val();
	var regemail = $("#regemail").val();
	var regaddress = $("#regaddress").val();
	if(username==""){
		alert("请输入账号");return false;
	}
	if(password==""){
		alert("请输入密码");return false;
	}
	if(reguname==""){
		alert("请输入姓名");return false;
	}
	var checkcode = $("#checkcode_reg").val();
	if(checkcode==""){
		alert("请输入验证码");return false;
	}
	if(regphone!=""){
		if(!checkphone(regphone)){
			alert("请输入正确的手机号码");return false;
		}
	}
	if(regemail!=""){
		if(!checkemail(regemail)){
			alert("请输入正确的电子邮箱");return false;
		}
	}
	$.ajax({
     		url:"${ctx }/com/userReg.action",
     		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
     		type:"post",
     		dataType:"json",
     		data:{"checkcode":checkcode,"regbean.user.uname":username,"regbean.user.userPassword":password,"regbean.user.userName":reguname,"regbean.user.userGender":reggender,"regbean.user.userPhone":regphone,"regbean.user.userEmail":regemail,"regbean.user.userAddress":regaddress},//window.encodeURI(中文值)：对字符串进行编码
     		success:function(json){
     			if(json.msg=="成功"){
					alert("恭喜您,注册成功");
					layer.close(pageii);
					location.reload();
     			}else{
     				alert(json.msg);
     			}
     		},
     		error:function(json){}
	});
}
function checkphone(obj){
   var reg = /^1[34578][0-9]{9}/;
    if(!reg.test(obj)){
       return false;
    }
    return true;
}
function checkemail(obj){
    var reg1 = /[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
    if(!reg1.test(obj)){
        return false;
    }
    return true;
}
$('#searchKey').keydown(function(e){
	if(e.keyCode==13){
	   $('#searchForm').submit(); //处理事件
	}
}); 
//-->
</script>