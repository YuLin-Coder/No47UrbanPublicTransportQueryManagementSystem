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
						<select name="bus.id" class="">
							<c:forEach items="${list2 }" var="item">
								<option value="${item.id }">${item.pai }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">司机：</label>
						<select name="driver.id" class="">
							<c:forEach items="${list3 }" var="item">
								<option value="${item.id }">${item.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">线路：</label>
						<select name="line.id" class="">
							<c:forEach items="${list1 }" var="item">
								<option value="${item.id }">${item.sid }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">出车时间：</label>
						<input type="text" name="chu" value="${modifybean.chu}" id="j_custom_fname<%=labelindex++%>" data-rule="required"
							size="55">
					</td>
				</tr>
				<tr>
					<td>
						<label for="j_custom_fname<%=labelindex%>" class="control-label x120">收车时间：</label>
						<input type="text" name="shou" value="${modifybean.shou}" id="j_custom_fname<%=labelindex++%>"
							data-rule="required" size="55">
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
