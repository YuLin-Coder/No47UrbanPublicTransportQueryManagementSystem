<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageContent">
	<form action="${ctx}/sys/update${actionclass1}.action" id="j_custom_form"
		data-toggle="validate" data-alertmsg="false">
		<input type="hidden" value="${modifybean.id}" name="id"/>
		<table class="table table-condensed table-hover" width="100%">
			<tbody>

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

</script>
<script type="text/javascript">
	function pic_upload_success(file, data) {
		var json = $.parseJSON(data)
		$(this).bjuiajax('ajaxDone', json)
		if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
			$('#j_custom_pic').val(json.filename).trigger('validate')
			$('#j_custom_span_pic').html('<img src="resource/'+ json.filename +'" width="100" />')
			//$('#j_custom_span_pic').html(json.filename)
		}
	}
	function do_OK(json, $form) {
		console.log(json)
		console.log($form)
	}
</script>
