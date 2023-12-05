<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	function pic_upload_success(file, data) {
		var json = $.parseJSON(data)
		$(this).bjuiajax('ajaxDone', json)
		if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
			$('#j_custom_pic').val(json.filename).trigger('validate')
			$('#j_custom_span_pic').html('<img src="'+ json.filename +'" width="100" />')
		}
	}
	function do_OK(json, $form) {
		console.log(json)
		console.log($form)
	}
	//护照有效日期  = 签发日期 + 10年
	$('#j_custom_issuedate').on('afterchange.bjui.datepicker', function(e, data) {
		var pattern = 'yyyy-MM-dd'
		var start = end = data.value

		end.setFullYear(start.getFullYear() + 10)
		end.setDate(start.getDate() - 1)

		$('#j_custom_indate').val(end.formatDate(pattern))
	})
</script>
<%
	int labelindex = 1;
%>
<div class="bjui-pageContent">
	<form action="${ctx}/com/modifySelf.action" id="j_custom_form" data-toggle="validate"
		data-alertmsg="false">
		<table class="table table-condensed table-hover" width="100%">
			<tbody>
				<tr>
					<td><label for="j_custom_name<%=labelindex%>" class="control-label x85">登录名称：</label> <input type="text"
						name="bean.uname" value="${SESSION_BEAN.user.user.uname}" id="j_custom_name<%=labelindex++%>"
						data-rule="required" size="15" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">密码：</label> <input
						type="password" name="bean.userPassword" value="" id="j_custom_fname<%=labelindex++%>" data-rule="" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">姓名：</label> <input type="text"
						name="bean.userName" value="${SESSION_BEAN.user.user.userName}" id="j_custom_fname<%=labelindex++%>"
						data-rule="required" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_sale32143rew4r23" class="control-label x85">性别：</label> <select
						name="bean.userGender" id="j_custom_sale32143rew4r23" data-toggle="selectpicker"
						data-rule="required">
							<option value="0">女</option>
							<option value="1">男</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">联系电话：</label> <input type="text"
						name="bean.userPhone" value="${SESSION_BEAN.user.user.userPhone}" id="j_custom_fname<%=labelindex++%>"
						data-rule="mobile" size="15" maxlength="11"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">邮箱：</label> <input type="text"
						name="bean.userEmail" value="${SESSION_BEAN.user.user.userEmail}" id="j_custom_fname"
						data-rule="email" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">出生日期：</label> <input type="text"
						name="bean.userBirth" value="${SESSION_BEAN.user.user.userBirth}" id="j_custom_fname"
						readonly="readonly" data-toggle="datepicker" data-rule="date" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">联系地址：</label> <input type="text"
						name="bean.userAddress" value="${SESSION_BEAN.user.user.userAddress}" id="j_custom_fname<%=labelindex++%>"
						data-rule="" size="55"></td>
				</tr>

			</tbody>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">取消</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>
<script type="text/javascript">
	$("#j_custom_sale32143rew4r23").val("${SESSION_BEAN.user.user.userGender}");
</script>