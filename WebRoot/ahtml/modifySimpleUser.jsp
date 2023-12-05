<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	function pic_upload_success(file, data) {
		var json = $.parseJSON(data)
		$(this).bjuiajax('ajaxDone', json)
		if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
			$('#j_custom_pic').val(json.filename).trigger('validate')
			$('#j_custom_span_pic').html(
					'<img src="'+ json.filename +'" width="100" />')
		}
	}
	function do_OK(json, $form) {
		console.log(json)
		console.log($form)
	}
	//护照有效日期  = 签发日期 + 10年
	$('#j_custom_issuedate').on('afterchange.bjui.datepicker',
			function(e, data) {
				var pattern = 'yyyy-MM-dd'
				var start = end = data.value

				end.setFullYear(start.getFullYear() + 10)
				end.setDate(start.getDate() - 1)

				$('#j_custom_indate').val(end.formatDate(pattern))
			})
</script>
<div class="bjui-pageContent">
	<form action="${ctx}/sys/update${actionclass1}.action" id="j_custom_form"
		data-toggle="validate" data-alertmsg="false">
		<input type="hidden" value="${modifybean.id}" name="id"/>
		<table class="table table-condensed table-hover" width="100%">
			<tbody>
				<tr>
					<td><label for="j_custom_name" class="control-label x85">登录名称：</label>
						<input type="text" name="user.uname"
						value="${modifybean.user.uname}" id="j_custom_name"
						data-rule="required" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname" class="control-label x85">密码：</label>
						<input type="password" name="user.userPassword"
						value="" id="j_custom_fname"
						data-rule="" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname" class="control-label x85">姓名：</label>
						<input type="text" name="user.userName"
						value="${modifybean.user.userName}" id="j_custom_fname"
						data-rule="required" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_name" class="control-label x85">性别：</label>
						<select name="user.userGender" value="${modifybean.user.uname}"
						id="j_custom_sale32143rew4r23" data-toggle="selectpicker" data-rule="required">
							<option value="0">女</option>
							<option value="1">男</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname" class="control-label x85">联系电话：</label>
						<input type="text" name="user.userPhone"
						value="${modifybean.user.userPhone}" id="j_custom_fname"
						data-rule="mobile" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname" class="control-label x85">邮箱：</label>
						<input type="text" name="user.userEmail"
						value="${modifybean.user.userEmail}" id="j_custom_fname"
						data-rule="email" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname" class="control-label x85">出生日期：</label>
						<input type="text" name="user.userBirth"
						value="${modifybean.user.userBirth}" id="j_custom_fname"
						readonly="readonly" data-toggle="datepicker" data-rule="date"
						size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname" class="control-label x85">联系地址：</label>
						<input type="text" name="user.userAddress"
						value="${modifybean.user.userAddress}" id="j_custom_fname" data-rule=""
						size="15"></td>
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
$("#j_custom_sale32143rew4r23").val("${modifybean.user.userGender}");
</script>