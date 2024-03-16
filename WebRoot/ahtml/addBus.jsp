<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	function pic_upload_success(file, data) {
		var json = $.parseJSON(data)
		$(this).bjuiajax('ajaxDone', json)
		if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
			$('#j_custom_pic').val(json.filename).trigger('validate')
			$('#j_custom_span_pic').html('<img src="' + json.filename + '" width="100" />')
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
<div class="bjui-pageContent">
	<%
		int labelindex = 1;
	%>
	<form action="${ctx}/sys/add${actionclass1}.action" id="j_custom_form" data-toggle="validate" data-alertmsg="false">
		<table class="table table-condensed table-hover" width="100%">
			<tbody>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">车牌号：</label>
						<input type="text" name="pai" value="${modifybean.pai}" id="j_custom_fname<%=labelindex++%>" data-rule="required"
							size="55">
					</td>
				</tr>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">车型：</label>
						<input type="text" name="type" value="${modifybean.type}" id="j_custom_fname<%=labelindex++%>"
							data-rule="required" size="55">
					</td>
				</tr>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">荷载人数：</label>
						<input type="text" name="wei" value="${modifybean.wei}" id="j_custom_fname<%=labelindex++%>"
							data-rule="required;integer" size="55">
					</td>
				</tr>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">是否空调车：</label>
						<select name="air"  id="j_custom_sale32143rew4r231"
								data-toggle="selectpicker" data-rule="required">
							<option value="是">是</option>
							<option value="否">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">投诉电话：</label>
						<input type="text" name="tel" value="${modifybean.tel}" id="j_custom_fname<%=labelindex++%>" data-rule=""
							size="11">
					</td>
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
